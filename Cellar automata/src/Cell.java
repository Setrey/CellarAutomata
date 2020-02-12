import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

public class Cell {
	public char previousState;
	public char state;
	public Strategy strategy;
	public double sum=0;
	public double sumPerRound=0;
	public double sumCounter=0;
	public boolean cellEmpty=false;
	public int kTolerance;
	
	public boolean changedStrategy=false;
	public boolean learningAutomata=false;
	public boolean sharingPayout=false;
	public int lengthOfHistory;
	public double pOfCoopMin;
	
	public double epsilon;
	public Mutation mutation=new Mutation();
	private double []pStrategy;
	private int kMax;
	public class History{
		public char state='E';
		public double result;
		
		public History (char state, double result)
		{
			this.state=state;
			this.result=result;
		}
	}
	
	public LinkedList<History> history;
	
	public Cell(double pInitC,double []pStrategy, double pEmpty, int lengthOfHistory, double p_typ_ag_ca,
				double pPayoutSharing, int kMax, double pOfCoopMin, double epsilon, Mutation mutation, double deltapC)
	{
		double n = Algorithm.randomValue.nextDouble();
		this.pStrategy=pStrategy;
		this.kMax=kMax;
		if (n<pEmpty)
		{
			this.strategy=new Strategy ('E',10);
			this.cellEmpty=true;
			
		}
		else {
			this.pOfCoopMin=0;
			//System.out.println(this.mutation.pMutationChangeStrategy);
			this.lengthOfHistory=lengthOfHistory;	 
			n=Algorithm.randomValue.nextDouble();
			
			this.mutation = new Mutation(mutation);
			
			if (n<pInitC)
				state='C';
			else
				state='D';
			
			n=Algorithm.randomValue.nextDouble();
			
			if (n<p_typ_ag_ca)
				this.learningAutomata=false;
			else 
				this.learningAutomata=true;
			
			n=Algorithm.randomValue.nextDouble();
			
			if (n<pPayoutSharing)
				this.sharingPayout=true;
			else
				this.sharingPayout=false;
			
			history= new LinkedList<History>();
			if(this.learningAutomata)
				if (this.state=='C')
					this.strategy=new Strategy('C',10);
				else
					this.strategy=new Strategy('D',10);
			else
				strategy=new Strategy(pStrategy,kMax);
			
			this.epsilon=epsilon;
			
			
			if (strategy.buffor=='P')
			{ //double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				this.pOfCoopMin=pOfCoopMin;
				/*
				double randomValue= (pOfCoopMin-deltapC) + (pOfCoopMin+deltapC)* Algorithm.randomValue.nextDouble();
				if (randomValue>1)
					randomValue=1;
				else if (randomValue<0)
					randomValue=0;
				this.pOfCoopMin=randomValue;
				*/
				//this.pOfCoopMin=pOfCoopMin; 
			}
		}
	}
	
	public void newStrategy()
	{
		strategy=new Strategy(pStrategy,kMax);
	}
	
	public Cell(Cell cell)
	{
		this.state=cell.state;
		this.strategy=cell.strategy;
		this.sum=cell.sum;
	}
	public void clearCell()
	{
		this.sum=0;
	}
	public void showStrategy()
	{
		System.out.print(this.strategy.buffor+ " ");	
	}
	public char getStrategy()
	{
		if (this.strategy.buffor=='P')
		{	
			double n =Algorithm.randomValue.nextDouble();
			if (n<pOfCoopMin)
				return 'C';
			else
				return 'D';
		}
			
			return this.strategy.buffor;
	}
	
	
	public String getHistory(int historyLength)
	{
		String historia="";
		for (int i=0; i<historyLength; i++)
		{
			
			historia+="[";
			
			if(i<history.size())
				historia+=history.get(i).state;
			else
				historia+="-";
			
			historia+=",";
			
			if(i<history.size())
				historia+=round2(history.get(i).result/8);
			else
				historia+="-.--";
			
			historia+="]";
		}
		return historia;
	}
	public void chooseBestStrategyForLACell()
	{
		if (this.learningAutomata)
		{
			int index=0;
			double sum=0;
			for (int i=0 ;i<this.history.size(); i++)
			{
				if (this.history.get(i).result>sum)
				{
					index=i;
					sum=this.history.get(i).result;
				}
			}		//Jesli random mniejszy od epsilona to zmiana stanu
			if (Algorithm.randomValue.nextDouble()<this.epsilon)
			{ 		// IF ITS WIDE ARRAY
				if(Algorithm.la1)
					if (this.state=='C')
						this.state='D';
					else
						this.state='C';
				else if (Algorithm.la2)
					if(this.history.getFirst().state=='C')
						this.state='D';
					else
						this.state='C';
					
				else if (Algorithm.la3)
					if(Algorithm.randomValue.nextBoolean())
						this.state='C';
					else
						this.state='D';
			}
			else 
			{
			this.previousState=this.state;
			this.state=this.history.get(index).state;
			this.sum=round2((this.history.get(index).result/8));
			}
		}
	}
	
	public Double round2(Double val) {
	    return new BigDecimal(val.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}
	
	public void addToHistory(char state, double result)
	{
		//System.out.println("addHistory przed:"+this.lengthOfHistory+" "+this.history.size());
		if (this.learningAutomata && this.history.size()<lengthOfHistory)
		{
			//System.out.println("AAA");
			history.addFirst(new History(state,result));
		}
		else if (this.learningAutomata && this.history.size()>=lengthOfHistory)
		{
			//System.out.println("AAAAA");
			history.addFirst(new History(state,result));
			history.removeLast();
		}
		//System.out.println("addHistory po:"+this.lengthOfHistory+" "+this.history.size());
	}
	
	public History getLastStrategy()
	{
		return history.getLast();
	}
	
	int maxHistoryBound=16;
	int minHistoryBound=1;
	
	double maxEpsilonBound=1;
	double minEpsilonBound=0;
	
	public void Mutation()
	{
		
		
		if (this.learningAutomata)
		{
			if (Algorithm.randomValue.nextDouble()<this.mutation.pMutationHistory)
			{
				if (Algorithm.randomValue.nextBoolean()==true)
				{
					if(this.history.size()<maxHistoryBound)
					{
						//System.out.println("+Przed: "+ this.lengthOfHistory+" "+this.history.size());
						this.lengthOfHistory+=this.mutation.parameterHisotryMutation;
						this.history.addLast(new History('X',0.0));
						//System.out.println("+Po: "+ this.lengthOfHistory+" "+this.history.size());
					}
				}
				else
				{
					if (this.history.size()>minHistoryBound)
					{
						//System.out.println("-Przed: "+ this.lengthOfHistory+" "+this.history.size());
						this.lengthOfHistory-=this.mutation.parameterHisotryMutation;
						this.history.removeLast();
						//System.out.println("-Po: "+ this.lengthOfHistory+" "+this.history.size());
					}
				}
			}
			if(Algorithm.randomValue.nextDouble()<this.mutation.parameterEpsilonMutation)
			{
				if (Algorithm.randomValue.nextBoolean()==true)
					this.epsilon+=this.mutation.parameterEpsilonMutation;
				else
					if(this.epsilon-this.mutation.parameterEpsilonMutation>0)
						this.epsilon-=this.mutation.parameterEpsilonMutation;
					else
						this.epsilon=0;
			}
			/*
			if (Algorithm.randomValue.nextDouble()<this.epsilon)
				if (this.state=='C')
					this.state='D';
				else
					this.state='C';
					*/
			/*
			if (Algorithm.randomValue.nextDouble()<this.epsilon)
			{ 		// IF ITS WIDE ARRAY
				if(Algorithm.la1)
					if (this.state=='C')
						this.state='D';
					else
						this.state='C';
				else if (Algorithm.la2)
					if(this.previousState=='C')
						this.state='D';
					else
						this.state='C';
					
				else if (Algorithm.la3)
					if(Algorithm.randomValue.nextBoolean())
						this.state='C';
					else
						this.state='D';
						*/
				/*
				int index=Algorithm.randomValue.nextInt(this.history.size());
				this.state=this.history.get(index).state;
				this.sum=round2((this.history.get(index).result/8));
				*/
			//}
		}
		else if (!this.learningAutomata)
		{
			if (Algorithm.randomValue.nextDouble()<this.mutation.pMutationChangeStrategy && this.mutation.pMutationChangeStrategy!=0.0)
			{
				char [] tab= {'P','C','D','K'};
				this.strategy.buffor=MutationRecurtion(tab);
			}
			if (Algorithm.randomValue.nextDouble()<this.mutation.pMutationChangePc)
			{// TODO kontrola wartoœci skrajnych 
				if (Algorithm.randomValue.nextBoolean()==true)
				{
					this.pOfCoopMin+=this.mutation.parameterIncMutation;
				}
				else
				{
					this.pOfCoopMin-=this.mutation.parameterIncMutation;
				}
			}
				
		}
		
	}
	
	public char MutationRecurtion(char[] tab)
	{
		char strategy=tab[Algorithm.randomValue.nextInt(tab.length)];
		
		if(this.state==strategy)
			MutationRecurtion(tab);
		
		return strategy;
	}
	
	/*public void changeStrategy()
	{
		if (this.learningAutomata && this.history.size()==this.lengthOfHistory)
		{
			int		indexOfBestStrategy=0;
			double	theBestStrategy=0;
			
			for (int i=0 ; i <this.history.size(); i++)
			{
				if (this.history.get(i).result>theBestStrategy)
				{
					theBestStrategy=this.history.get(i).result;
					indexOfBestStrategy=i;
				}
			}
			//Mutation epsilon
			if (Algorithm.randomValue.nextDouble()<this.epsilon)
			{
				changeStrategyRecurtion(indexOfBestStrategy, this.history.size());
			}
			this.strategy=this.history.get(indexOfBestStrategy).strategy;
			this.state=this.history.get(indexOfBestStrategy).strategy.buffor;
			
		}
	}*/

	public boolean isStrategyChanged()
	{
		if(changedStrategy)
		{
			changedStrategy=false;
			return true;
		}
		else
			return false;
	}
	
	public int changeStrategyRecurtion(int index, int sizeOfList)
	{
		int i=-1;
		
		if (Algorithm.randomValue.nextInt(sizeOfList)==index)
			i=changeStrategyRecurtion(index,sizeOfList);
		
		return i; 
	}
	public Cell ()
	{
		
	}
	public void copyCell ( Cell dest, Cell temp)
	{
		dest.state=temp.state;
		dest.strategy=temp.strategy;
		dest.sum=temp.sum;
		
		dest.previousState= temp.previousState;
		dest.sumCounter=temp.sumCounter;
		dest.sumPerRound=temp.sumPerRound;
		dest.mutation=temp.mutation;
		dest.changedStrategy=temp.changedStrategy;
		dest.lengthOfHistory=temp.lengthOfHistory;
		dest.pOfCoopMin=temp.pOfCoopMin;
		dest.pStrategy=temp.pStrategy;
		dest.cellEmpty=temp.cellEmpty;
		dest.epsilon=temp.epsilon;
		dest.kMax=temp.kMax;
		dest.history=temp.history;
		dest.learningAutomata=temp.learningAutomata;
		dest.kTolerance=temp.kTolerance;
		dest.sharingPayout=temp.sharingPayout;
		
	}
}
