import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	public double pParameter;
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
		public History (History history)
		{
			this.state=history.state;
			this.result=history.result;
		}
		public char getState()
		{
			return state;
		}
		public double getResult()
		{
			return result;
		}
	}
	
	public LinkedList<History> history;
	/*(double pInitC,double []pStrategy, double pEmpty, int lengthOfHistory, double p_typ_ag_ca,
				double pPayoutSharing, int kMax, double pOfCoopMin, double epsilon, Mutation mutation, double deltapC)*/
	
	public Cell ()
	{
		history= new LinkedList<History>();
	}
	public Cell(Settings settings)
	{
		double n = Algorithm.randomValue.nextDouble();
		
		this.kMax=settings.kMax;
		if (n<settings.probOfUnhabitedCell)
		{
			this.strategy=new Strategy ('E',10);
			this.cellEmpty=true;
			
		}
		else {
			this.pParameter=0;
			//System.out.println(this.mutation.pMutationChangeStrategy);
			this.lengthOfHistory=settings.historyLength;	 
			n=Algorithm.randomValue.nextDouble();
			
			this.mutation = new Mutation(mutation);
			
			if (n<settings.probOfInitCState)
				state='C';
			else
				state='D';
			
			n=Algorithm.randomValue.nextDouble();
			
			//		CA or LA
			
			if(settings.agentType==typeOfAgent.CA)
				this.learningAutomata=false;
			else if (settings.agentType==typeOfAgent.LA)
				this.learningAutomata=true;
			else if (settings.agentType==typeOfAgent.CAnLA)
			{
				if (n<settings.probOfAgentCA)
					this.learningAutomata=false;
				else 
					this.learningAutomata=true;
			}
			
			n=Algorithm.randomValue.nextDouble();
			
			if (n<settings.probOfPayoffSharing)
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
				strategy=new Strategy(settings);
			
			this.epsilon=settings.epsilon;
			
			if (strategy.buffor=='P')
			{ 
				if(settings.deltaPc==0)
					this.pParameter=settings.valueOfPc;
				else
				{
					double minRange = settings.valueOfPc- settings.deltaPc;
					if (minRange<0)
						minRange=0;
					double maxRange = settings.valueOfPc+ settings.deltaPc;
					if (maxRange>1)
						maxRange=1;
					
					double randomValue = minRange + (maxRange - minRange) * Algorithm.randomValue.nextDouble();
					
					this.pParameter=randomValue;
				}
			}
		}
	}
	public void printHistory()
	{
		for (int i=0 ; i<this.history.size(); i++)
			System.out.print("["+ history.get(i).getState()+","+history.get(i).getResult()+"]");
	}
	public void newStrategy(Settings settings)
	{
		strategy=new Strategy(settings);
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
		//System.out.print(this.strategy.buffor+ " ");	
	}
	public char getStrategy()
	{
		if (this.strategy.buffor=='P')
		{	
			double n =Algorithm.randomValue.nextDouble();
			if (n<pParameter)
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
			//System.out.println("index"+ index);
			if (Algorithm.randomValue.nextDouble()<this.epsilon)
			{ 		
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
				//System.out.println(index+" " + this.history.size());
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
					this.pParameter+=this.mutation.parameterIncMutation;
				}
				else
				{
					this.pParameter-=this.mutation.parameterIncMutation;
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
	/*/
	  	System.out.print("[");
			for (int i =0 ; i<dest.history.size(); i++)
				System.out.print("["+dest.history.get(i).state+","+dest.history.get(i).result+"]");
		System.out.print("]");
	 */
	public void copyCell ( Cell dest, Cell temp)
	{
		dest.state=temp.state;
		dest.strategy=temp.strategy;
		dest.sum=temp.sum;
		System.out.println("   dest size: "+dest.history.size() + " temp size: " +temp.history.size());
		dest.history.clear();
		System.out.println("   dest size: "+dest.history.size() + " temp size: " +temp.history.size());
		/*
		List<History> cloned = new LinkedList<>(temp.history);
		
		dest.history= (LinkedList<History>) cloned;
		*/
		
		
		for (int i =0; i<temp.history.size(); i++)
		{
			double result1=temp.history.get(i).result;
			char state1=temp.history.get(i).state;
			
			dest.history.add(new History (state1,result1));
		}
		
		System.out.println("|||dest size: "+dest.history.size() + " temp size: " +temp.history.size());
		dest.previousState= temp.previousState;
		dest.sumCounter=temp.sumCounter;
		dest.sumPerRound=temp.sumPerRound;
		dest.mutation=temp.mutation;
		dest.lengthOfHistory=temp.lengthOfHistory;
		dest.pParameter=temp.pParameter;
		dest.pStrategy=temp.pStrategy;
		dest.changedStrategy=temp.changedStrategy;
		dest.cellEmpty=temp.cellEmpty;
		dest.epsilon=temp.epsilon;
		dest.kMax=temp.kMax;
		dest.learningAutomata=temp.learningAutomata;
		dest.kTolerance=temp.kTolerance;
		dest.sharingPayout=temp.sharingPayout;
		
	}
}
