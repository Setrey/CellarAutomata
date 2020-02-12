
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Algorithm {
	static double pOCp=0;
	static Random randomValue;
	static int randomSeed;
	static Cell[] cells=new Cell[5];
	static boolean la1;
	static boolean la2;
	static boolean la3;
	double pTypAgentaCA;
	int lengthOfHisotry;
	
	public Algorithm(int xRange1,int yRange1, int rSasiedztwa, int Q, int iloscKrokow
			,double prawdopodbienstwoNiezamieszk, double pInitCState
			, double gotWspoldzieleniaDochodow
			,int ziarno, int iloscExperymentow1, double RR,double SS,double TT,double PP
			,int typAgentaKomorki, double prawdopAgentaCA, int historia, double epsilonn
			,double mutHistoria
			,int mutC2, double mutEpsilon, double mutc3, double stratPc
			,double stratAllC, double stratAllD,double stratkD,boolean kConstt
			,int kMaxx, double pPc, double mutStrateg, double mutParametr
			,double mutC1, boolean Qselected, boolean debugSelected, double deltaPC
			,boolean LA1, boolean LA2, boolean LA3) throws IOException 
	{
		
		
		Paint paint[][];
		Algorithm.randomSeed= ziarno ;
		Algorithm.randomValue= new Random(Algorithm.randomSeed);
		int xRange=xRange1;
		int yRange=yRange1;
		int m=iloscExperymentow1;
		int r=rSasiedztwa;
		
		int period=iloscKrokow;
		int qRounds=Q;
		
		la1=LA1;
		la2=LA2;
		la3=LA3;
		double pInitC=pInitCState;
		pOCp=pPc;
		
		double pEmpty=prawdopodbienstwoNiezamieszk;
		
		double pPC=stratPc;
		double pAllC=stratAllC;
		double pAllD=stratAllD;
		double pKD=stratkD;
		
		double pStrategy []= {pPC,pAllC+pPC,pAllC+pPC+pAllD,pKD};
		/*int*/ this.lengthOfHisotry=historia;
		// jesli typAgenta0 to prawdop = 1 jesli typagenta 1 to prawdop 0
		int typAgenta=typAgentaKomorki; // CA //i LA
		/*double*/ this.pTypAgentaCA=prawdopAgentaCA;
		
		
		double pPayoutSharing=gotWspoldzieleniaDochodow;
		//jesli kconst nieprawda to kMax*=-1;
		boolean kConst=kConstt;
		int kMax=kMaxx;
		
		double pC=pPc;
		double deltapC=deltaPC;
		double epsilon=epsilonn;
		
		double	pMutationChangeStrategy=mutStrateg;
		double	pMutationChangePc=mutParametr;
		double	pMutationHistory=mutHistoria;
		double	pMutationEpsilon=mutEpsilon;
		double	parameterIncMutation=mutC1;
		int		parameterHisotryMutation=mutC2;
		double	parameterEpsilonMutation=mutc3;

		//tablica p³atnoœci
		double R=RR;	//=1.0;
		double S=SS;		//=0.0;
		double T=TT;	//=1.4;
		double P=PP;		//=0.0;
		
		PrisonerDilema pd= new PrisonerDilema(R,S,T,P);
		
		if (!kConst)
			kMax*=-1;
		
		//jesli typ agenta CA (0) wtedy wartoœæ prawdopodobieñstwa 1 jak LA(1) to 0, a jak i to i to, wtedy <0:1]
		if (typAgenta==0)
			pTypAgentaCA=1;
		else if (typAgenta==1)
			pTypAgentaCA=0;
		Mutation mutation= new Mutation(pMutationChangeStrategy,pMutationChangePc,pMutationHistory,pMutationEpsilon
				,parameterIncMutation,parameterHisotryMutation,parameterEpsilonMutation);
		

		Initialization []init = new Initialization[m];
		Run []run = new Run[m];
		
		for (int i=0 ; i<m;i++)
		{
		init[i]= new Initialization(xRange,yRange,period,pInitC,pStrategy,pEmpty,lengthOfHisotry,pTypAgentaCA,pPayoutSharing
				,kMax,pC,epsilon, mutation,deltapC);
		//init.showMatrix();
		run[i]= new Run(init[i].getMatrix(),pd,qRounds,kMax,r,((xRange==0||yRange==0)?true:false));
		}
		//paint = new Paint (run,kConst);
		
		paint= new Paint[m][period+1];
		
		Statistics statistics[];
		statistics=new Statistics[m];
		for (int i=0 ; i<m;i++)
			statistics[i]= new Statistics(run[i],r,period, qRounds,pEmpty,typAgenta,pTypAgentaCA, pInitC,kMax,kConst,pStrategy,lengthOfHisotry,mutation, pPayoutSharing);
		
		List<String> testPlikLA = new ArrayList<>();
		List<String> testPlikCA = new ArrayList<>();
		List<String> testPlikMain = new ArrayList<>();
		
		for (int x=0 ;x<m ;x++)
		{
			
			
			testPlikLA.add("Eksperyment nr " + x);
			testPlikCA.add("Eksperyment nr " + x);
			testPlikMain.add("Eksperyment nr " + x);
			testPlikLA.add("# StanKomórki,ŒredniDochódZRozgrywek,DzielenieDochodow(Tak(1) czy Nie(0)),[Historia]");
			testPlikCA.add("# StanKomórki,StrategiaKomórki,DzielenieDochodow(Tak(1) czy Nie(0)),ŒredniDochódZRozgrywek");
			testPlikMain.add("# StanKomórki,RodzajKomórki(CA (0) czy LA (1)),DzielenieDochodow(Tak(1) czy Nie(0)),ŒredniDochódZRozgrywek");
			testPlikLA.add("------------------");
			testPlikCA.add("------------------");
			testPlikMain.add("------------------");
			testPlikLA.add("t=0");
			testPlikCA.add("t=0");
			testPlikMain.add("t=0");
			
			// 24-11-2019 zmiana
			
			//statistics[x].addRow(run[x], 0);
			// LA
			
			if(pTypAgentaCA<1)
				for (int k=0; k<lengthOfHisotry; k++)
				{
					run[x].newValueForLA(pC);
					run[x].countSum();
					run[x].updateCellHistory();
					LASection(run,x,testPlikLA);
					if (pTypAgentaCA!=0)
					{
						CASection(run, x, testPlikCA);
						CAiLASection(run, x, testPlikMain);
					}
						
				}
			// CA
			if (pTypAgentaCA==1)
			{
				CASection(run, x, testPlikCA);
				CAiLASection(run, x, testPlikMain);
			}
			// zmiana 24-11-2019
			run[x].countSum();
			run[x].updateStatistics(run[x].temporary);
			run[x].countStatistics();
			statistics[x].addRow(run[x], 0);
			
			paint[x][0]=new Paint(run[x],kConst);
			//System.out.println(" -----");
			for (int i=0; i<period; i++)
			{	
				testPlikLA.add("------------------");
				testPlikCA.add("------------------");
				testPlikMain.add("------------------");
				testPlikLA.add("t="+(i+1));
				testPlikCA.add("t="+(i+1));
				testPlikMain.add("t="+(i+1));
				
				
				run[x].resetStatistics();
				
				run[x].chooseBestStrategyForLACells(run[x].temporary);
				
				testPlikLA.add("Stan na podstawie historii");
				
				if(pTypAgentaCA<1)
					LASection(run,x,testPlikLA);
				
				//System.out.println(i + " " + i + " " + i);
				run[x].countSum();
				run[x].updateStatistics(run[x].temporary);
				run[x].countStatistics();
				
				testPlikLA.add("Po rozgrywce i przed mutacja");
				testPlikCA.add("Po rozgrywce i przed mutacja");
				testPlikMain.add("Po rozgrywce i przed mutacja");
				
				if(pTypAgentaCA<1)
					LASection(run,x,testPlikLA);
				else if (pTypAgentaCA>0)
					CASection(run, x, testPlikCA);

				CAiLASection(run, x, testPlikMain);
				
				
				run[x].mutation();
				
				testPlikLA.add("Stan po mutacji");
				testPlikCA.add("Stan po mutacji");
				testPlikMain.add("Stan po mutacji");
				
				if(pTypAgentaCA<1)
					LASection(run,x,testPlikLA);
				else if (pTypAgentaCA>0)
					CASection(run, x, testPlikCA);

				CAiLASection(run, x, testPlikMain);
				
				run[x].updateCellHistory();
				//run[x].updateHistoryLookingAtNeighbours();
				
				
				if ((i+1)%qRounds==0 && Qselected)
				{
					run[x].changeStrategy();
				}
				else if (xRange==0||yRange==0)
				{
					run[x].updateOneDimension();
				}
				
				testPlikLA.add("Zapis do historii");
				testPlikCA.add("Zmiana Strategii");
				testPlikMain.add("Zmiana Strategii");
				
				if (pTypAgentaCA>0)
					CASection(run, x, testPlikCA);

				CAiLASection(run, x, testPlikMain);
				
				run[x].checkKneighbours();
				
				testPlikCA.add("Zmiana stanu");
				testPlikMain.add("Zmiana stanu");
				
				if(pTypAgentaCA<1)
					LASection(run,x,testPlikLA);
				else if (pTypAgentaCA>0)
					CASection(run, x, testPlikCA);

				CAiLASection(run, x, testPlikMain);
				
				paint[x][i+1]=new Paint(run[x],kConst);
				statistics[x].addRow(run[x], i+1);
				
				run[x].clearAllCells();
				if ((i+1)%qRounds==0 && Qselected)
				{
					//System.out.println("? ");
					/*
					for (int a =0 ; a<run[x].temporary.length ;a++)
					{
						for (int s =0 ; s <run[x].temporary[a].length ;s++)
						{
							System.out.print(run[x].temporary[a][s].sum);
							System.out.print(" ");
						}
						System.out.println(" ");
					}
					*/
					
					/*
					for (int a =0 ; a<run[x].temporary.length ;a++)
					{
						for (int s =0 ; s <run[x].temporary[a].length ;s++)
						{
							System.out.print(run[x].temporary[a][s].sum);
							System.out.print(" ");
						}
						System.out.println(" ");
					}
					*/
				}
			}
			
			
			if (debugSelected)
			{
				try(PrintWriter p = new PrintWriter("test LA.txt")) 
				{
					for (int j=0; j<testPlikLA.size(); j++)
						p.println(testPlikLA.get(j));
				}catch (FileNotFoundException e)
				{
				System.out.println("Error");
				}
				try(PrintWriter p = new PrintWriter("test CA.txt")) 
				{
					for (int j=0; j<testPlikCA.size(); j++)
						p.println(testPlikCA.get(j));
				}catch (FileNotFoundException e)
				{
				System.out.println("Error");
				}
				try(PrintWriter p = new PrintWriter("test LA i CA.txt")) 
				{
					for (int j=0; j<testPlikMain.size(); j++)
						p.println(testPlikMain.get(j));
				}catch (FileNotFoundException e)
				{
				System.out.println("Error");
				}
				/*
				testPlikCA.clear();
				testPlikLA.clear();
				testPlikMain.clear();
				*/
			}
		}
		new MyCanvasInWindow(paint,statistics,period);
	}
	
	public void LASection(Run run[], int x, List<String> testPlikLA)
	{
		
			
				for (int i=1 ; i< run[x].temporary.length-1; i++)
				{
					String firstLine="";
					for (int j=1; j<run[x].temporary[0].length-1;j++)
					{	
						firstLine+=run[x].temporary[i][j].state+","+filling2(round2(run[x].temporary[i][j].sum))+","
								+ ((run[x].temporary[i][j].sharingPayout==true)? 1:0)
								+ ",[";
						if (run[x].temporary[i][j].learningAutomata)
							firstLine+=run[x].temporary[i][j].getHistory(lengthOfHisotry);
						else
						{	
							for (int abc=0; abc<lengthOfHisotry; abc++)
								firstLine+="[X,X.XX]";
						}
						firstLine+="] ";
					}
					testPlikLA.add(firstLine);
				}
				testPlikLA.add(" ");
	}
	public Double round2(Double val) {
	    return new BigDecimal(val.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}


	public void CASection(Run run[], int x, List<String> testPlikCA)
	{
			for(int i=1; i<run[x].temporary.length-1;i++)
			{
				String firstLine="";
				for (int j=1; j<run[x].temporary[0].length-1;j++)
				{
					firstLine+=run[x].temporary[i][j].state+",";
					if (run[x].temporary[i][j].learningAutomata)
						firstLine+="XX";
					else
					{
						String string;
						if (run[x].temporary[i][j].strategy.buffor=='K')
						{
							string="K"+run[x].temporary[i][j].strategy.kMax;
						}
						else 
						{
							string=Character.toString(run[x].temporary[i][j].strategy.buffor)+" ";
						}
						
						firstLine+=string;
					}
					firstLine+=","+((run[x].temporary[i][j].sharingPayout==true)? 1:0);
					firstLine+=","+filling2(round2(run[x].temporary[i][j].sum));
					firstLine+=" ";
				}
				testPlikCA.add(firstLine);
			}
			testPlikCA.add("");
	}
	public void CAiLASection(Run run[], int x, List<String> testPlikMain)
	{
		for (int i=1; i <run[x].temporary.length-1; i++)
		{
			String firstLine="";
			for(int j=1; j< run[x].temporary[i].length-1; j++)
			{
				firstLine+=run[x].temporary[i][j].state+",";
				firstLine+=((run[x].temporary[i][j].learningAutomata==true)? 0:1)+","+((run[x].temporary[i][j].sharingPayout==true)? 1:0)+","+filling2(round2(run[x].temporary[i][j].sum))+ " ";
			}
			testPlikMain.add(firstLine);
		}
		testPlikMain.add("");
	}
	public String filling2(double string)
	{
		String doub= Double.toString(string);
		String format= String.format("%1$-5s",doub).replace(' ', '0');
		
		return format;
	}
	
	
}
