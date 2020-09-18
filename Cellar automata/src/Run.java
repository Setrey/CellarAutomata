import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Run {


	public Cell[][] temporary;
	public PrisonerDilema pD;
	private int kTolerance= 0;
	public int r=1;
	public int xRange=0;
	public int yRange=0;
	
	public boolean itIsWideArray=false; // true- NxM false- 1xN or 2xN
	private boolean itIsRangeY;
	public boolean oneDimension=false;
	public int		oneDimensionIndex=0;
	
	char[][][] historyStates;
	
	//wszystie percent parametry- najpierw sa zliczane jako counter
	//pozniej wyliczany zostaje procent
	
	public double	percentOfCStates=0;
	
	public double	avarageIncome=0;
	
	public double		CounterOfLACell=0;
	public double		CounterOfCACell=0;
	public double		CounterOfPcCell=0;
	
	public double		percentOfCACell=0;
	public double		percentOfLACell=0;
	
	public double		percentOfCStatesInCACell=0;
	public double		percentOfCStatesInLACell=0;
	
	public double		percentOfSharingCell=0;
	public double		percentOfAllCStrategy=0;
	public double		percentOfPcStrategy=0;
	public double		percentOfallDStrategy=0;
	public double		percentOfkDStrategy=0;
	
	//Ten Parametr nie znajduje sie w update statistics, poniewa¿ zostaje zapisany w momencie zmiany strategii...
	public double		percentOfQChanges=0;
	
	public double		kwadratStanówC=0;
	public double		kwadratDochodów=0;
	public double 		kwadratKomorekCA=0;
	public double		kwadratKomorekLA=0;
	public double		kwadratKomorekWspoldzielacych=0;
	public double		kwadratkomorekC=0;
	public double		kwadratkomorekP=0;
	public double		kwadratkomorekD=0;
	public double		kwadratkomorekK=0;
	
	
	public double		avarageHParameter=0;
	public double		avarageEpsParameter=0;
	public double		avaragePcParameter=0;
	
	public double		[]percentOfkD=new double[8];
	
	public double 		maxRangeOfPayOut=0;
	
	//public Run (Cell[][] cell, PrisonerDilema pd, int lengthOfCycle, int kTolerance, int r, boolean isItOneDimension)
	public Run (Cell[][] cell, Settings settings)
	{
		xRange=cell.length;
		yRange=cell[0].length;
		this.r=settings.radiusOfNeighbor;
		//System.out.println("rangeX: " + xRange + " rangeY: " + yRange);
		this.kTolerance=settings.kMax;
		this.oneDimension=((xRange==1 || yRange==1)?(true):(false));
		this.pD= settings.pDSettings;
		
		if(pD.CC>maxRangeOfPayOut)
			maxRangeOfPayOut=pD.CC;
		if(pD.CD>maxRangeOfPayOut)
			maxRangeOfPayOut=pD.CD;
		if(pD.DD>maxRangeOfPayOut)
			maxRangeOfPayOut=pD.DD;
		if(pD.DC>maxRangeOfPayOut)
			maxRangeOfPayOut=pD.DC;
		//Matrix
		if (xRange==1 || xRange==2)
		{
			itIsWideArray=false;
			itIsRangeY=true;
		}
		else if (yRange==1 || yRange==2)
		{
			itIsWideArray=false;
			itIsRangeY=false;
		}
		else if (yRange>2 && xRange>2 && !this.oneDimension)
			itIsWideArray=true;
		
		if (itIsWideArray)
		{
			this.temporary= new Cell[xRange+2][yRange+2];
			for (int i=0; i<xRange; i++)
				for(int j=0; j<yRange; j++)
					this.temporary[i+1][j+1]=cell[i][j];
		}
		else if (oneDimension)
		{
			this.temporary=new Cell[cell.length][cell[0].length+(2*r)];
			for (int i=0; i<this.temporary.length; i++)
				for(int j=0; j<this.temporary[0].length-(2*r);j++)
					this.temporary[i][j+this.r]=cell[i][j];
		}
		else 
		{
			this.temporary= new Cell[xRange+((!itIsRangeY)?2:0)][yRange+((itIsRangeY)?2:0)];
			
			for (int i=0; i<xRange; i++)
				for(int j=0; j<yRange; j++)
					this.temporary[i+((!itIsRangeY)?1:0)][j+((itIsRangeY)?1:0)]=cell[i][j];
		}
		this.rewriteEdges();
		//this.lengthOfCycle=lengthOfCycle;
	}
	
	private void rewriteEdges()
	{
		if (itIsWideArray) 
		{
				for (int i=0; i <yRange; i++)
				{
					this.temporary[0][i+1]=this.temporary[xRange][i+1];
					this.temporary[xRange+1][i+1]=this.temporary[1][i+1];
				}
				for(int i=0; i <xRange; i++)
				{
					this.temporary[i+1][0]=this.temporary[i+1][yRange];
					this.temporary[i+1][yRange+1]=this.temporary[i+1][1];
				}
				this.temporary[0][0]=this.temporary[xRange][yRange];
				this.temporary[xRange+1][yRange+1]=this.temporary[1][1];
				this.temporary[0][yRange+1]=this.temporary[xRange][1];
				this.temporary[xRange+1][0]=this.temporary[1][yRange];
		}
		else if (oneDimension)
		{
			for(int i=0; i<this.temporary.length; i++)
				for(int j=r; j<(2*r);j++)
					for (int k=j-r; k<this.r; k++)
					{
						
						this.temporary[i][k]=this.temporary[i][this.temporary[i].length-(2*r)+k];
						int x=this.temporary[i].length;
						this.temporary[i][x+k-r]=this.temporary[i][j+k];
					}
		}
		else
		{
			if(itIsRangeY)
			{
				this.temporary[0][0]=temporary[0][yRange];
				this.temporary[0][yRange+1]=temporary[0][1];
				if(xRange==2)
				{
					this.temporary[1][0]=temporary[1][yRange];
					this.temporary[1][yRange+1]=temporary[1][1];
				}
			}
			else 
			{
				this.temporary[0][0]=temporary[xRange][0];
				this.temporary[xRange+1][0]=temporary[1][0];
				if(yRange==2)
				{
					this.temporary[0][1]=temporary[xRange][1];
					this.temporary[xRange+1][1]=temporary[1][1];
				}
			}
		}
	}

	public void copyTable(Cell[][] tableToCopy, Cell[][] tableFromCopy)
	{
		for (int i=0; i<tableFromCopy.length; i++)
			for(int j=0; j<tableFromCopy[i].length; j++)
			{
				tableToCopy[i][j]=new Cell();
				tableToCopy[i][j].copyCell(tableFromCopy[i][j]);
			}
	}
	
	public void chooseBestStrategyForLACells( Cell [][]cell)
	{
		for (int i =0 ; i<cell.length ;i++)
			for (int j =0 ; j<cell[i].length ; j++)
				cell[i][j].chooseBestStrategyForLACell();
	}
	
	public void checkKneighbours()
	{
		Cell[][] tablicaPomocnicza=new Cell[temporary.length][temporary[0].length];
		
		copyTable(tablicaPomocnicza,temporary);
			
		if (itIsWideArray)
		{
			for (int i=1; i<temporary.length-1; i++)
				for(int j=1; j<temporary[i].length-1; j++)
					
					if(temporary[i][j].learningAutomata)
						;//tablicaPomocnicza[i][j].chooseBestStrategyForLACell();
					else
						if (temporary[i][j].strategy.buffor=='K')
						{
							int counter=0;
							for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów
						        for (int l=j-1; l<j+2; l++)
						          if (!(k==i && l==j)&&(temporary[k][l].state=='D'))
						            counter+=1;
							if (counter>temporary[i][j].strategy.kMax)
								tablicaPomocnicza[i][j].state='D';
							else
								tablicaPomocnicza[i][j].state='C';
						}
						else if (temporary[i][j].strategy.buffor=='P')
						{
								double n =Algorithm.randomValue.nextDouble();
								n=Algorithm.randomValue.nextDouble();
								double m =temporary[i][j].pParameter;
								
									if (n<m)
									{
										tablicaPomocnicza[i][j].state='C';
									}
									else
									{
										tablicaPomocnicza[i][j].state='D';
									}
									
						}
						else if (temporary[i][j].strategy.buffor=='D')
							tablicaPomocnicza[i][j].state='D';
						else if (temporary[i][j].strategy.buffor=='C')
							tablicaPomocnicza[i][j].state='C';
		}
		else if (oneDimension)
		{
			for (int i=0; i<temporary.length; i++)
				for(int j=r; j<temporary[i].length-r; j++)
					if(temporary[i][j].learningAutomata)
						;//tablicaPomocnicza[i][j].chooseBestStrategyForLACell();
					else 
						if(temporary[i][j].strategy.buffor=='K')
					{
						int counter=0;
						for (int k=j-r ; k<j+r+1; k++)
							if(k!=j && temporary[i][k].strategy.buffor=='D')
								counter++;
						if(counter>kTolerance)
							tablicaPomocnicza[i][j].state='D';
						else
							tablicaPomocnicza[i][j].state='C';
					}
					else if (temporary[i][j].strategy.buffor=='P')
					{
							double n =Algorithm.randomValue.nextDouble();
							if (n<temporary[i][j].pParameter)
								tablicaPomocnicza[i][j].state='C';
							else
								tablicaPomocnicza[i][j].state='D';
					}
					else if (temporary[i][j].strategy.buffor=='D')
						tablicaPomocnicza[i][j].state='D';
					else if (temporary[i][j].strategy.buffor=='C')
						tablicaPomocnicza[i][j].state='C';
		}
		else
		{
			if (temporary.length==1)
			{
				for (int j=1; j<temporary[0].length-1; j++)
				{	
					if(temporary[0][j].learningAutomata)
						;//tablicaPomocnicza[0][j].chooseBestStrategyForLACell();
					else 
						if (temporary[0][j].strategy.buffor=='K')
					{
						int counter=0;
						if (temporary[0][j-1].strategy.buffor=='D')
							counter++;
						if(temporary[0][j+1].strategy.buffor=='D')
							counter++;
						
						if (counter>kTolerance)
							tablicaPomocnicza[0][j].state='D';
						else
							tablicaPomocnicza[0][j].state='C';
					}
					else if (temporary[0][j].strategy.buffor=='P')
					{
							double n =Algorithm.randomValue.nextDouble();
							if (n<temporary[0][j].pParameter)
								tablicaPomocnicza[0][j].state='C';
							else
								tablicaPomocnicza[0][j].state='D';
					}
					else if (temporary[0][j].strategy.buffor=='D')
						tablicaPomocnicza[0][j].state='D';
					else if (temporary[0][j].strategy.buffor=='C')
						tablicaPomocnicza[0][j].state='C';
				}
			}
			else if (temporary.length==2)
			{
				for (int i=0; i<2; i++)
					for (int j=1; j<temporary[0].length-1; j++)
					{
						if(temporary[i][j].learningAutomata)
							;//tablicaPomocnicza[i][j].chooseBestStrategyForLACell();
						else 
							if (temporary[i][j].strategy.buffor=='K')
						{
							int counter=0;
							for (int k=i-1 ;k<i+2; k++)
								for (int l=j-1 ; l<j+2; l++)
									if (!(k==i && l==j) && (k>=0 && k<=1) &&(temporary[k][l].strategy.buffor=='D'))
							            counter+=1;
								
							if (counter>kTolerance)
								tablicaPomocnicza[i][j].state='D';
							else
								tablicaPomocnicza[i][j].state='C';
						}
						else if (temporary[i][j].strategy.buffor=='P')
						{
								double n =Algorithm.randomValue.nextDouble();
								if (n<temporary[i][j].pParameter)
									tablicaPomocnicza[i][j].state='C';
								else
									tablicaPomocnicza[i][j].state='D';
						}
						else if (temporary[i][j].strategy.buffor=='D')
							tablicaPomocnicza[i][j].state='D';
						else if (temporary[i][j].strategy.buffor=='C')
							tablicaPomocnicza[i][j].state='C';
					}
			}
			if (temporary[0].length==1)
			{
				for (int i=1; i<temporary.length-1; i++)
				{
					if(temporary[i][0].learningAutomata)
						;//tablicaPomocnicza[i][0].chooseBestStrategyForLACell();
					else 
						if (temporary[i][0].strategy.buffor=='K')
					{
						int counter=0;
						if (temporary[i-1][0].strategy.buffor=='D')
							counter++;
						if(temporary[i+1][0].strategy.buffor=='D')
							counter++;
						
						if (counter>kTolerance)
							tablicaPomocnicza[i][0].state='D';
						else
							tablicaPomocnicza[i][0].state='C';
					}
					else if (temporary[i][0].strategy.buffor=='P')
					{
							double n =Algorithm.randomValue.nextDouble();
							if (n<temporary[i][0].pParameter)
								tablicaPomocnicza[i][0].state='C';
							else
								tablicaPomocnicza[i][0].state='D';
					}
					else if (temporary[i][0].strategy.buffor=='D')
						tablicaPomocnicza[i][0].state='D';
					else if (temporary[i][0].strategy.buffor=='C')
						tablicaPomocnicza[i][0].state='C';
				}
			}
			else if (temporary[0].length==2)
			{
				for (int i=1; i<temporary.length-1; i++)
					for (int j=0; j<2; j++)
					{
						if(temporary[i][j].learningAutomata)
							;//tablicaPomocnicza[i][j].chooseBestStrategyForLACell();
						else 
							if (temporary[i][j].strategy.buffor=='K')
						{
							int counter=0;
							for (int k=i-1 ;k<i+2; k++)
								for (int l=j-1 ; l<j+2; l++)
									if (!(k==i && l==j) && (l>=0 && l<=1) &&(temporary[k][l].strategy.buffor=='D'))
							            counter+=1;
								
							if (counter>kTolerance)
								tablicaPomocnicza[i][j].state='D';
							else
								tablicaPomocnicza[i][j].state='C';
						}
						else if (temporary[i][j].strategy.buffor=='P')
						{
								double n =Algorithm.randomValue.nextDouble();
								if (n<temporary[i][j].pParameter)
									tablicaPomocnicza[i][j].state='C';
								else
									tablicaPomocnicza[i][j].state='D';
						}
						else if (temporary[i][j].strategy.buffor=='D')
							tablicaPomocnicza[i][j].state='D';
						else if (temporary[i][j].strategy.buffor=='C')
							tablicaPomocnicza[i][j].state='C';
					}
			}
		}
		for (int i=0; i<tablicaPomocnicza.length; i++)
			for(int j=0; j<tablicaPomocnicza[i].length; j++)
			{
				temporary[i][j]=tablicaPomocnicza[i][j];
				//System.out.println(temporary[i][j].changedStrategy);
			}
	}
	
	public void updateStatistics(Cell cell)
	{
		
		if (!cell.cellEmpty)
		{
				this.avarageIncome+=cell.sumPerRound;
				
				if (cell.isStrategyChanged())
				{
				
					this.percentOfQChanges+=1;
				}
			//	//	//	// LA
			if(cell.learningAutomata)
			
			{
				this.CounterOfLACell+=1;
				this.percentOfLACell+=1;
				if (cell.state=='C')
				{
					this.percentOfCStatesInLACell+=1;
					this.percentOfCStates++;
				}
	
				this.avarageHParameter+=cell.lengthOfHistory;
				this.avarageEpsParameter+=cell.epsilon;
			}
			else	//	// CA
			{
				this.CounterOfCACell+=1;
				this.percentOfCACell+=1;
				if (cell.state=='C')
				{
					this.percentOfCStates++;
					this.percentOfCStatesInCACell+=1;
				}
				
				if (cell.strategy.buffor=='C')
					this.percentOfAllCStrategy+=1;
				else if (cell.strategy.buffor=='P')
				{
					this.CounterOfPcCell+=1;
					this.percentOfPcStrategy+=1;
					//System.out.println(this.avaragePcParameter+ " <avgPc || cell.pOfCoopMin> "+ cell.pOfCoopMin);
					this.avaragePcParameter+=cell.pParameter;
				}
				else if (cell.strategy.buffor=='D')
					this.percentOfallDStrategy+=1;
				else if (cell.strategy.buffor=='K')
					this.percentOfkDStrategy+=1;
			}
			
			if (cell.sharingPayout)
				this.percentOfSharingCell+=1;
			
			for (int i=0; i<percentOfkD.length;i++)
			{
				if(cell.strategy.buffor=='K' && cell.strategy.kMax==i)
					this.percentOfkD[i]+=1;
			}
		}
	}
	public void updateStatisticsQChanges(Cell cell)
	{
		if (!cell.cellEmpty)
			if (cell.isStrategyChanged())
				this.percentOfQChanges+=1;
	}
	public void updateStatisticsQChanges(Cell [][]cell)
	{
		for (int i =1 ; i<cell.length-1;i++)
			for(int j=1; j<cell[i].length-1;j++)
				updateStatisticsQChanges(cell[i][j]);
	}
	
	public void updateStatistics(Cell [][]cell)
	
	{
		for (int i =1 ; i<cell.length-1;i++)
			for(int j=1; j<cell[i].length-1;j++)
				updateStatistics(cell[i][j]);
	}
	
	public void insertEmptyCells(Cell [][]cell)
	{
		for (int i =1 ; i<cell.length-1;i++)
			for(int j=1; j<cell[i].length-1;j++)
				insertEmptyCell(cell[i][j]);
		
		this.rewriteEdges();
	}
	
	public void insertEmptyCell(Cell cell)
	{
		if (cell.strategy.buffor=='E' && cell.state=='E')
		{
			cell.cellEmpty=true;
		}
	}
	
	public void resetStatistics()
	{
		this.CounterOfCACell=0;
		this.CounterOfLACell=0;
		this.CounterOfPcCell=0;
		this.percentOfCStates=0;
		this.avarageIncome=0;
		this.percentOfCACell=0;
		this.percentOfLACell=0;
		this.percentOfCStatesInCACell=0;
		this.percentOfCStatesInLACell=0;
		this.percentOfSharingCell=0;
		this.percentOfAllCStrategy=0;
		this.percentOfPcStrategy=0;
		this.percentOfallDStrategy=0;
		this.percentOfkDStrategy=0;
		this.avarageHParameter=0;
		this.avarageEpsParameter=0;
		this.avaragePcParameter=0;
		this.percentOfQChanges=0;
		for(int i=0; i<this.percentOfkD.length; i++)
			this.percentOfkD[i]=0;
		/* resetoowanie sumPerRound do statystyk jest w countSum();
		/*
		for(int i=0; i<this.temporary.length; i++)
			for (int j=0; j<this.temporary[i].length; j++)
				this.temporary[i][j].sumPerRound=0;
		*/
	}

	
	
	public void countStatistics()
	{
		
		int allCells=this.xRange*this.yRange;
		
		this.percentOfCStates=round2((this.percentOfCStates/allCells));
		this.kwadratStanówC=Math.pow((this.percentOfCStates/allCells),2);
		this.kwadratStanówC=Math.round((this.kwadratStanówC)*100);
		this.kwadratStanówC/=100;
		
		
		this.avarageIncome=round2((((this.avarageIncome)/(this.CounterOfCACell+this.CounterOfLACell))));
		this.kwadratDochodów=Math.pow((this.avarageIncome/(this.CounterOfCACell+this.CounterOfLACell)),2);
		this.kwadratDochodów=Math.round((this.kwadratDochodów)*100);
		this.kwadratDochodów/=100;
		
		this.percentOfCACell=round2((this.CounterOfCACell/allCells));
		
		this.kwadratKomorekCA=Math.pow((this.percentOfCACell/allCells),2);
		this.kwadratKomorekCA=Math.round((this.kwadratKomorekCA)*100);
		this.kwadratKomorekCA/=100;
//		
		this.percentOfLACell=round2(this.CounterOfLACell/allCells);

		this.kwadratKomorekLA=Math.pow((this.percentOfLACell/allCells),2);
		this.kwadratKomorekLA=Math.round((this.kwadratKomorekLA)*100);
		this.kwadratKomorekLA/=100;
		
		if(this.CounterOfCACell!=0)
			this.percentOfCStatesInCACell=round2(this.percentOfCStatesInCACell/this.CounterOfCACell);
		else
			this.percentOfCStatesInCACell=0;
		
		this.percentOfCStatesInLACell=(Math.round((this.percentOfCStatesInLACell/this.CounterOfLACell)*100));
		this.percentOfCStatesInLACell/=100;
		
		
			
		this.percentOfSharingCell=round2(this.percentOfSharingCell/allCells);

		this.kwadratKomorekWspoldzielacych=Math.pow((this.percentOfSharingCell/allCells),2);
		this.kwadratKomorekWspoldzielacych=Math.round(this.kwadratKomorekWspoldzielacych*100);
		this.kwadratKomorekWspoldzielacych/=100;
		
		int KDStrategyCounter=(int)this.percentOfkDStrategy;

		if(this.CounterOfCACell!=0)
		{
			this.percentOfAllCStrategy=round2(this.percentOfAllCStrategy/this.CounterOfCACell);
			
			this.kwadratkomorekC=Math.pow((this.percentOfAllCStrategy/this.CounterOfCACell),2);
			this.kwadratkomorekC=Math.round(this.kwadratkomorekC*100);
			this.kwadratkomorekC/=100;
		
			this.percentOfPcStrategy=round2(this.percentOfPcStrategy/this.CounterOfCACell);
		
			this.kwadratkomorekP=Math.pow((this.percentOfPcStrategy/this.CounterOfCACell),2);
			this.kwadratkomorekP=Math.round(this.kwadratkomorekP*100);
			this.kwadratkomorekP/=100;
			
			this.percentOfallDStrategy=round2(this.percentOfallDStrategy/this.CounterOfCACell);
		
			this.kwadratkomorekD=Math.pow((this.percentOfallDStrategy/this.CounterOfCACell),2);
			this.kwadratkomorekD=Math.round(this.kwadratkomorekD*100);
			this.kwadratkomorekD/=100;
			
			this.percentOfkDStrategy=round2(this.percentOfkDStrategy/this.CounterOfCACell);

			this.kwadratkomorekK=Math.pow((this.percentOfkDStrategy/this.CounterOfCACell),2);
			this.kwadratkomorekK=Math.round(this.kwadratkomorekK*100);
			this.kwadratkomorekK/=100;
		}
		else
		{
			this.percentOfAllCStrategy=0;
			this.kwadratkomorekC=0;
			
			this.percentOfkDStrategy=0;
			this.kwadratkomorekK=0;
			
			this.percentOfallDStrategy=0;
			this.kwadratkomorekD=0;
			
			this.percentOfPcStrategy=0;
			this.kwadratkomorekP=0;
		}

		
		
		this.avarageHParameter=((this.CounterOfLACell!=0)?(round2(this.avarageHParameter/this.CounterOfLACell)):0.0);
	
		this.avarageEpsParameter=((this.CounterOfLACell!=0)?(round4(this.avarageEpsParameter/this.CounterOfLACell)):0.0);
		//System.out.println(this.avaragePcParameter+ " <- avaragePcParameter || counterOfPcCells ->"+ this.CounterOfPcCell);
		this.avaragePcParameter=((this.CounterOfPcCell!=0)?(round2(this.avaragePcParameter/this.CounterOfPcCell)):0.0);
		
		// percentOfQChanges ¿eby zlicza³o liczbê zmian musi zostaæ przesuniête na koniec funkcji algorithm
		//this.percentOfQChanges=round2(this.percentOfQChanges/(this.CounterOfLACell+this.CounterOfCACell));
		
		for(int i=0; i<this.percentOfkD.length; i++)
		{
			this.percentOfkD[i]=((this.percentOfkD[i]==0 || this.percentOfkDStrategy==0)?(0):round2(this.percentOfkD[i]/KDStrategyCounter));
		}
	}
	
	public void countStatisticsQChanges()
	{
		int allCells=this.xRange*this.yRange;
		this.percentOfQChanges=round2(this.percentOfQChanges/(allCells));
	}
	
	public void contest ()
	{	
		if (itIsWideArray)
		{
			for (int i=1; i<this.temporary.length-1; i++)
				for(int j=1; j<this.temporary[i].length-1; j++)
					if (!this.temporary[i][j].cellEmpty)
					{
						this.temporary[i][j].sumPerRound=0;
					double counterSum=0;
					double notEmptyNeighbours=0;
					for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów agenta
					{
				        for (int l=j-1; l<j+2; l++)
				        {
				        	if (!(k==i && l==j) && !this.temporary[k][l].cellEmpty)
				        	{
				        		notEmptyNeighbours++;
				        		if(this.temporary[i][j].sharingPayout && this.temporary[k][l].sharingPayout)
				        		{
				        			counterSum+=(pD.confrontation(this.temporary[i][j].state, this.temporary[k][l].state)/2);
				        			counterSum+=(pD.confrontation(this.temporary[k][l].state, this.temporary[i][j].state)/2);
				        		}
				        		else
				        		{
				        			//System.out.println(this.temporary[i][j].state + " -> " + this.temporary[k][l].state);
				        			counterSum+=pD.confrontation(this.temporary[i][j].state, this.temporary[k][l].state);
				        			//System.out.println(counterSum);
				        		}
				        	}
				        }
					}
					counterSum=round2(counterSum);
					if(notEmptyNeighbours!=0)
						counterSum/=notEmptyNeighbours;
					/*
					if(i==4 && j == 4 )
					{
					System.out.println(i + " " + j + " " + counterSum);
					System.out.println(i + " " + j + " " + (this.temporary[i][j].sum));
					}
					*/
					this.temporary[i][j].sum+=round3(counterSum);
					//System.out.println(i + " " + j + " " + (this.temporary[i][j].sum));
					this.temporary[i][j].sumPerRound+=round3(counterSum);
					}
		}
		/*
		else if (oneDimension)
		{
			for (int i=0; i<temporary.length-1; i++)
				for(int j=r; j<temporary[i].length-r; j++)
					if (!temporary[i][j].cellEmpty)
					{
						double counterSum=0;
						for (int k=j-r; k<j+r+1; k++)
							if(k!=j && !temporary[i][k].cellEmpty)
								if(temporary[i][j].sharingPayout&&temporary[i][k].sharingPayout)
								{
									counterSum+=(pD.confrontation(temporary[i][j].state, temporary[i][k].state)/2);
									temporary[i][k].sum+=(pD.confrontation(temporary[i][j].state, temporary[i][k].state)/2);
								}
								else
									counterSum+=pD.confrontation(temporary[i][j].state, temporary[i][k].state);
						counterSum/=r*2;
						temporary[i][j].sum=counterSum;
						//this.updateStatistics(temporary[i][j]);
					}
		}
		else if((temporary.length==1 || temporary.length==2)&&!oneDimension)
		{
			for (int i=0; i<temporary.length; i++)
				for (int j=1; j<temporary[0].length-1; j++)
					if (!temporary[i][j].cellEmpty)
					{
					double counterSum=0;
					for (int k=i-1 ;k<i+2; k++)
						for (int l=j-1 ; l<j+2; l++)
							if (!(k==i && l==j) && (k>=0 && k<=temporary.length-1) && (l>=0 && l<=1) && !temporary[k][l].cellEmpty)
								if(temporary[i][j].sharingPayout && temporary[k][l].sharingPayout)
				        		{
				        			counterSum+=(pD.confrontation(temporary[i][j].state, temporary[k][l].state)/2);
				        			temporary[k][l].sum+=(pD.confrontation(temporary[i][j].state, temporary[k][l].state)/2);
				        		}
				        		else
				        			counterSum+=pD.confrontation(temporary[i][j].state, temporary[k][l].state);
					if(temporary.length==1)
						counterSum/=2;
					if(temporary.length==2)
						counterSum/=5;
					temporary[i][j].sum=counterSum;
					//this.updateStatistics(temporary[i][j]);
					}
		}
		else if((temporary[0].length==1 || temporary[0].length==2)&&!oneDimension)
		{
			for (int i=1; i<temporary.length; i++)
				for (int j=0; j<temporary[i].length; j++)
					if (!temporary[i][j].cellEmpty)
					{
					double counterSum=0;
					for (int k=i-1 ;k<i+2; k++)
						for (int l=j-1 ; l<j+2; l++)
							if (!(k==i && l==j) && (k>=0 && k<=temporary.length-1) && (l>=0 && l<=temporary[0].length-1) && !temporary[k][l].cellEmpty)
								if(temporary[i][j].sharingPayout && temporary[k][l].sharingPayout)
				        		{
				        			counterSum+=(pD.confrontation(temporary[i][j].state, temporary[k][l].state)/2);
				        			temporary[k][l].sum+=(pD.confrontation(temporary[i][j].state, temporary[k][l].state)/2);
				        		}
				        		else
				        			counterSum+=pD.confrontation(temporary[i][j].state, temporary[k][l].state);
					if (temporary[0].length==1)
						counterSum/=2;
					if (temporary[0].length==2)
						counterSum/=5;
					temporary[i][j].sum=counterSum;
					//this.updateStatistics(temporary[i][j]);
					}
		}
		*/
		//this.countStatistics();
	}

	public void newValueForLA(Settings settings)
	{
		if(itIsWideArray)
		{
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
				if (temporary[i][j].learningAutomata)
				{
					
					temporary[i][j].state=((Algorithm.randomValue.nextBoolean())? 'C' : 'D');
					temporary[i][j].sum=0;
				}
		}
		else if(oneDimension)
		{
			for (int i=0; i<temporary.length-1; i++)
				for(int j=r; j<temporary[i].length-r; j++)
					if (temporary[i][j].learningAutomata)
					{
						double sum=0;
						for (int x=0 ;x <8;x++)
							sum+=maxRangeOfPayOut*Algorithm.randomValue.nextDouble();
					temporary[i][j].addToHistory(((Algorithm.randomValue.nextBoolean())? 'C' : 'D'), sum); //
					}
		}
		else
		{
			if (temporary.length==1 || temporary.length==2)
			{
				for (int i=0; i<temporary.length; i++)
					for (int j=1; j<temporary[0].length-1; j++)
						if (temporary[i][j].learningAutomata)
						{
							double sum=0;
							for (int x=0 ;x <8;x++)
								sum+=maxRangeOfPayOut*Algorithm.randomValue.nextDouble();
						temporary[i][j].addToHistory(((Algorithm.randomValue.nextBoolean())? 'C' : 'D'), sum); //
						}
			}
			else if (temporary[0].length==1 || temporary[0].length==2)
			{
				for (int i=1; i<temporary.length-1; i++)
					for (int j=0; j<temporary[0].length; j++)
						if (temporary[i][j].learningAutomata)
						{
							double sum=0;
							for (int x=0 ;x <8;x++)
								sum+=maxRangeOfPayOut*Algorithm.randomValue.nextDouble();
						temporary[i][j].addToHistory(((Algorithm.randomValue.nextBoolean())? 'C' : 'D'), sum); //
						}
			}
		}
	}
	
	public void newValueForLA(char [][] states)
	{
		if(itIsWideArray)
		{
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
			{
				if (this.temporary[i][j].strategy.buffor=='L')
				{
					this.temporary[i][j].learningAutomata=true;
					
					if (temporary[i][j].learningAutomata)
					{
						
						temporary[i][j].state=states[i-1][j-1];
						temporary[i][j].sum=0;
					}
				}
				else
					this.temporary[i][j].learningAutomata=false;
			}
		}
	}
	
	
	public void updateCellHistory()
	{
		if(itIsWideArray)
		{
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
			if (temporary[i][j].learningAutomata)
			{
				double bestInNeighbour=round3(temporary[i][j].sum);
				temporary[i][j].addToHistory(temporary[i][j].state, bestInNeighbour);
			}
		}
		else if(oneDimension)
		{
			for (int i=0; i<temporary.length-1; i++)
				for(int j=r; j<temporary[i].length-r; j++)
				if (temporary[i][j].learningAutomata)
				{
					double bestInNeighbour=(temporary[i][j].sum*8);
					temporary[i][j].addToHistory(temporary[i][j].state, bestInNeighbour);
				}
		}
		else
		{
			if (temporary.length==1 || temporary.length==2)
			{
				for (int i=0; i<temporary.length; i++)
					for (int j=1; j<temporary[0].length-1; j++)
						if (temporary[i][j].learningAutomata)
							{
								double bestInNeighbour=(temporary[i][j].sum*8);
								temporary[i][j].addToHistory(temporary[i][j].state, bestInNeighbour);
							}
			}
			else if (temporary[0].length==1 || temporary[0].length==2)
			{
				for (int i=1; i<temporary.length-1; i++)
					for (int j=0; j<temporary[0].length; j++)
						if (temporary[i][j].learningAutomata)
							{
								double bestInNeighbour=(temporary[i][j].sum*8);
								temporary[i][j].addToHistory(temporary[i][j].state, bestInNeighbour);
							}
			}
		}
	}
	
	
	public void updateHistoryLookingAtNeighbours()
	{
		if(itIsWideArray)
		{
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
			if (temporary[i][j].learningAutomata)
			{
				double bestInNeighbour=-1;
				Strategy state=new Strategy('B',-1);
				for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów
			        for (int l=j-1; l<j+2; l++)
			          if (/*!(k==i && l==j) && */temporary[k][l].sum>bestInNeighbour)
			          {
			        	  state=temporary[k][l].strategy;
			        	  bestInNeighbour=temporary[k][l].sum;
			        	  
			          }
				bestInNeighbour*=8;
				temporary[i][j].addToHistory(state.buffor, bestInNeighbour);
			}
		}
		else if(oneDimension)
		{
			for (int i=0; i<temporary.length-1; i++)
				for(int j=r; j<temporary[i].length-r; j++)
				if (temporary[i][j].learningAutomata)
				{
					double bestInNeighbour=-1;
					Strategy state=new Strategy('B',-1);
					for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów 
			          if (/*k!=j &&*/ temporary[i][k].sum>bestInNeighbour)
			          {
			        	  state=temporary[i][k].strategy;
			        	  bestInNeighbour=temporary[i][k].sum;
			          }
					temporary[i][j].addToHistory(state.buffor, bestInNeighbour);
				}
		}
		else
		{
			if (temporary.length==1 || temporary.length==2)
			{
				for (int i=0; i<temporary.length; i++)
					for (int j=1; j<temporary[0].length-1; j++)
						if (temporary[i][j].learningAutomata)
							{
								double bestInNeighbour=-1;
								Strategy state=new Strategy('B',-1);
								for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów
							        for (int l=j-1; l<j+2; l++)
							        	if (/*!(k==i && l==j) && */(k>=0 && k<=1)&& temporary[k][l].sum>bestInNeighbour)
							        	{
								        	  state=temporary[k][l].strategy;
								        	  bestInNeighbour=temporary[k][l].sum;
								        }
								temporary[i][j].addToHistory(state.buffor, bestInNeighbour);
							}
			}
			else if (temporary[0].length==1 || temporary[0].length==2)
			{
				for (int i=1; i<temporary.length-1; i++)
					for (int j=0; j<temporary[0].length; j++)
						if (temporary[i][j].learningAutomata)
							{
								double bestInNeighbour=-1;
								Strategy state=new Strategy('B',-1);
								for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów
							        for (int l=j-1; l<j+2; l++)
							        	if (/*!(k==i && l==j) && */(l>=0 && l<=1)&& temporary[k][l].sum>bestInNeighbour)
							        	{
								        	  state=temporary[k][l].strategy;
								        	  bestInNeighbour=temporary[k][l].sum;
								        }
								temporary[i][j].addToHistory(state.buffor, bestInNeighbour);
							}
			}
		}
	}

	public void changeStrategy()
	{
		Cell[][] tempo2=new Cell[temporary.length][temporary[0].length];
		//przepisywanie do nowej tablicy 
		this.copyTable(tempo2, temporary);
		/*
		for (int i=0; i<temporary.length; i++)
			for(int j=0; j<temporary[i].length; j++)
			{
				tempo2[i][j]=new Cell();
				new Cell().copyCell(tempo2[i][j],temporary[i][j]);
			}
		*/
	if(itIsWideArray)
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
				if(!temporary[i][j].cellEmpty)
				{
					boolean hasChanged=false;
					Cell tempo= new Cell();
					
					new Cell().copyCell(tempo,temporary[i][j]);
					
					for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów
				        for (int l=j-1; l<j+2; l++)
				          if (!(k==i && l==j) && temporary[k][l].sum>tempo.sum)
				          {
				        	
				        	  hasChanged=true;
				        	  new Cell().copyCell(tempo, temporary[k][l]);
				          }
					//System.out.println("==== between ====");
					if (hasChanged)
						if(tempo.learningAutomata)
							if(temporary[i][j].learningAutomata)
							{
								tempo.changedStrategy=true;
								new Cell().copyCell(tempo2[i][j], tempo);
								
							}
							else
							{
								tempo.changedStrategy=true;
								new Cell().copyCell(tempo2[i][j],tempo);
							}
						else
							if(temporary[i][j].learningAutomata)
							{
								tempo.changedStrategy=true;
								new Cell().copyCell(tempo2[i][j],tempo);
							}
							else
							{
								tempo.changedStrategy=true;
								new Cell().copyCell(tempo2[i][j],tempo);
							}
					//System.out.println();
				}
	/*
	System.out.println(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	
	for (int a=1; a<temporary.length-1; a++)
	{
		for(int b=1; b<temporary[a].length-1; b++)
		{
			System.out.print(tempo2[a][b].state);
			tempo2[a][b].printHistory();
			System.out.print(" || ");
			
		}
		System.out.println();
	}
	*/
	
		// przepisanie tablicy tempo2 do glownej
	//System.out.println("======================");
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
			{
				//System.out.println("  "+tempo2[i][j].history.size());
				//System.out.println(">>>>"+tempo2[i][j].changedStrategy);
				new Cell().copyCell(temporary[i][j],tempo2[i][j]);
				
			}
		//System.out.println(" = = = = = = = = =");
		this.rewriteEdges();
		/*
	System.out.println("=========TEMPORARY=============");
		for (int a=1; a<temporary.length-1; a++)
		{
			for(int b=1; b<temporary[a].length-1; b++)
			{
				System.out.print(temporary[a][b].state);
				temporary[a][b].printHistory();
				System.out.print(" || ");
				
			}
			System.out.println();
		}
		*/
	}
	
	public void clearPayout()
	{
		for (int i=1; i<temporary.length-1; i++)
			for(int j=1; j<temporary[i].length-1; j++)
				temporary[i][j].clearCell();
		rewriteEdges();
	}
	
	public void showMatrix()
	{
		
		System.out.println(' ');
		for (int i=0; i<temporary.length; i++)
		{
			for(int j=0; j<temporary[i].length; j++)
				temporary[i][j].showStrategy();
			System.out.println(' ');
		}
	}
	
	public void mutation()
	{
		for (int i=0; i<temporary.length; i++)
			for(int j=0; j<temporary[i].length; j++)
				temporary[i][j].Mutation();
	}
	
	public void updateOneDimension()
	{
		for (int i=oneDimensionIndex ;i<temporary.length;i++)
			for(int j=0;j<temporary[i].length;j++)
			{
				temporary[i+1][j]=temporary[i][j];
			}
		oneDimensionIndex++;
	}
	
	public boolean LACheck(Cell[][]cell)
	{
		for (int i=1; i<cell.length-1; i++)
			for(int j=1; j<cell[i].length-1; j++)
			{
				if (cell[i][j].strategy.buffor=='L')
					return true;
			}
		return false;
	}
	
	public Double round2(Double val) {
		/*NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(Double.parseDouble((nf.format(val)).replace(",", ".")));
		return Double.parseDouble((nf.format(val)).replace(",", "."));
		*/
	    return new BigDecimal(val.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}
	public Double round3(Double val) {
	    return new BigDecimal(val.toString()).setScale(3,RoundingMode.HALF_UP).doubleValue();
	}
	public Double round4(Double val) {
	    return new BigDecimal(val.toString()).setScale(4,RoundingMode.HALF_UP).doubleValue();
	}
	
	public String filling3(double string)
	{
		String doub= Double.toString(string);
		String format= String.format("%1$-6s",doub).replace(' ', '0');
		
		return format;
	}
	
	public String filling2(double string)
	{
		String doub= Double.toString(string);
		String format= String.format("%1$-5s",doub).replace(' ', '0');
		
		return format;
	}
	
	public void showPayoffsWithBorder()
	{
		for (int i=0; i<temporary.length; i++)
		{		for(int j=0; j<temporary[i].length; j++)
			{
				System.out.print(this.temporary[i][j].payout+ " ");
			}
		System.out.println();
		}
	}
	
	public void showStatesWithBorder()
	{
		for (int i=0; i<temporary.length; i++)
		{		for(int j=0; j<temporary[i].length; j++)
			{
				System.out.print(this.temporary[i][j].state+ " ");
			}
		System.out.println();
		}
	}
	
	public void showStatesWithoutBorder()
	{
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print(this.temporary[i][j].state+ " ");
			}
		System.out.println();
		}
	}
	
	public void showPayoffsWithoutBorder()
	{
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print(this.temporary[i][j].payout+ " ");
			}
		System.out.println();
		}
	}
	
	public void showSharingwithoutBorder()
	{
		System.out.println("--sharing--");
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print(((this.temporary[i][j].sharingPayout)?(1):(0))+ " ");
			}
		System.out.println();
		}
	}
	
	public void showSumWithoutBorder()
	{
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print(this.temporary[i][j].sum+ " ");
			}
		System.out.println();
		}
	}
	public void showSumWithBorder()
	{
		for (int i=0; i<temporary.length; i++)
		{		for(int j=0; j<temporary[i].length; j++)
			{
				System.out.print(this.temporary[i][j].sum+ " ");
			}
		System.out.println();
		}
	}
	
	public void showHistoryWithoutBorder()
	{
		for (int i=1 ; i< temporary.length-1; i++)
		{
			for (int j=1; j<this.temporary[0].length-1;j++)
			{	
				String firstLine="";
				firstLine+="[";
				if (this.temporary[i][j].learningAutomata)
					firstLine+=this.temporary[i][j].getHistory();
				else
				{	
					for (int abc=0; abc<2; abc++)
						firstLine+="[X,X.XXX]";
				}
				firstLine+="] ";
				System.out.print(firstLine);
			}
			System.out.println();
		}
	}
	
	public void showChangesWithoutBorder()
	{
		System.out.println("--Changes--");
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print((this.temporary[i][j].changedStrategy)?("1 "):("0")+ " ");
			}
		System.out.println();
		}
	}
	public void showStrategiesWithoutBorder()
	{
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print(((!this.temporary[i][j].learningAutomata)?(this.temporary[i][j].strategy.buffor):("L"))+ " ");
			}
		System.out.println();
		}
	}
	
	public void showKDValuesWithoutBorder()
	{
		for (int i=1; i<temporary.length-1; i++)
		{		for(int j=1; j<temporary[i].length-1; j++)
			{
				System.out.print(((!this.temporary[i][j].learningAutomata)?(this.temporary[i][j].strategy.kMax):("L"))+ " ");
			}
		System.out.println();
		}
	}
	
}


/* // Bez zawijania
else
{
	for (int i=1; i<temporary.length-1; i++)
		for(int j=1; j<temporary[i].length-1; j++)
		{
			if (temporary[i][j].strategy.buffor=='K')
			{
				int counter=0;
				for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów
			        for (int l=j-1; l<j+2; l++)
			          if (!(k==i && l==j)&&
			        		  !(k==0 || k==temporary.length-1)&&
			        		  !(l==0 || l==temporary.length-1)&&
			        		  (temporary[k][l].strategy.buffor=='D'))
			            counter+=1;
				if (counter>kTolerance)
					temporary[i][j].strategy.bufforK='D';
				else
					temporary[i][j].strategy.bufforK='C';
			}
		}
	for (int i=1; i<temporary.length-1; i++)
		for(int j=1; j<temporary[i].length-1; j++)
		{
			double counterSum=0;
			for (int k=i-1; k<i+2; k++)  // iteracja s¹siadów agenta
		        for (int l=j-1; l<j+2; l++)
		        	if (!(k==i && l==j)&&
			        		  !(k==0 || k==temporary.length-1)&&
			        		  !(l==0 || l==temporary.length-1))
		        		counterSum+=pD.confrontation(temporary[i][j].getStrategy(), temporary[k][l].getStrategy());
			temporary[i][j].setSum(counterSum);
		}
}
*/
