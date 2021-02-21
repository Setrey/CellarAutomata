
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
	OdchylenieStandardowe standardDeviation;
	
	Initialization []init;
	Run []run;
	Statistics statistics[];
	int period;
	Settings settings;
	Paint paint[][];
	
	
	List<String> testPlikLA;
	List<String> testPlikCA;
	List<String> testPlikMain;
	
	public Algorithm(Settings settings) throws IOException 
	{
		applySettings(settings);
	}
	
	public void Calculate()
	{
		for (int x=0 ;x<settings.numberOfExperiments ;x++)
		{
			FrameZero(x);
			
			for (int i=0; i<settings.numberOfFrames+1; i++)
			{	
				//System.out.println("krok " + (i+1));
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
				/*
				System.out.println("============");
				run[x].showSumWithoutBorder();
				*/
				//run[x].showSumWithBorder();
				run[x].contest();
				/*
				System.out.println("====-Sum--=======");
				run[x].showSumWithoutBorder();
				System.out.println("====-States--====");
				run[x].showStatesWithoutBorder();
				System.out.println("====-Strat---====");
				run[x].showStrategiesWithoutBorder();
				System.out.println("====-kValue--====");
				run[x].showKDValuesWithoutBorder();
				*/
				
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
				//System.out.println("---------- updateCellHistory()");
				
				run[x].updateCellHistory();
				//run[x].updateHistoryLookingAtNeighbours();
				
				//System.out.println("---------- changeStrategy()");
				if ((i)%settings.qChanges==0 && settings.isQselected)
				{
					run[x].changeStrategy();
					//run[x].showChangesWithoutBorder();
					run[x].updateStatisticsQChanges(run[x].temporary);
					run[x].countStatisticsQChanges();
				}
				else if (settings.numberOfRows==0||settings.numberOfColumns==0)
				{
					run[x].updateOneDimension();
				}
				
				testPlikLA.add("Zapis do historii");
				testPlikCA.add("Zmiana Strategii");
				testPlikMain.add("Zmiana Strategii");
				
				if (pTypAgentaCA>0)
					CASection(run, x, testPlikCA);

				CAiLASection(run, x, testPlikMain);
				//System.out.println("---------- checkKneighbours()");
				run[x].checkKneighbours();
				
				testPlikCA.add("Zmiana stanu");
				testPlikMain.add("Zmiana stanu");
				
				if(pTypAgentaCA<1)
					LASection(run,x,testPlikLA);
				else if (pTypAgentaCA>0)
					CASection(run, x, testPlikCA);

				CAiLASection(run, x, testPlikMain);
				if (i>0)
					paint[x][i]=new Paint(run[x],settings.isKConst);
				
				statistics[x].addRow(run[x], i);
				
				
				if ((i+1)%settings.qChanges==0 && settings.isQselected)
				{
					run[x].clearPayout();
				}
			}
			
			
			if (settings.isDebugSelected)
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
		standardDeviation.standardDeviation(statistics,settings.numberOfFrames);
	}
	
	public void FrameZero(int x)
	{
		run[x].insertEmptyCells(run[x].temporary);
		boolean isThereLA=this.LACheck(run[x]);
		
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
		
		// LA
		
		if(pTypAgentaCA<1 || isThereLA)
			for (int k=0; k<lengthOfHisotry; k++)
			{
				if (!isThereLA)
					run[x].newValueForLA(settings);
				else
					run[x].newValueForLA(run[x].historyStates[k]);
				run[x].contest();
				run[x].updateCellHistory();
				LASection(run,x,testPlikLA);
				if (pTypAgentaCA!=0)
				{
					CASection(run, x, testPlikCA);
					CAiLASection(run, x, testPlikMain);
				}
				run[x].clearPayout();
			}
		// CA
		if (pTypAgentaCA==1)
		{
			CASection(run, x, testPlikCA);
			CAiLASection(run, x, testPlikMain);
		}
		
		run[x].chooseBestStrategyForLACells(run[x].temporary);
		run[x].contest();
		
		paint[x][0]=new Paint(run[x],settings.isKConst);
		
		run[x].clearPayout();
		
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
						//
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
	
	public void createWindow()
	{
		new MyCanvasInWindow(paint,statistics,run,settings);
	}
	
	public void applySettings(Settings settings)
	{
		this.standardDeviation = new OdchylenieStandardowe();
		this.settings=settings;
		Algorithm.randomSeed= settings.seed;
		Algorithm.randomValue= new Random(Algorithm.randomSeed);
		
		la1=settings.isLA1;
		la2=settings.isLA2;
		la3=settings.isLA3;
		pOCp=settings.valueOfPc;
		
		this.lengthOfHisotry=settings.historyLength;
		// jesli typAgenta0 to prawdop = 1 jesli typagenta 1 to prawdop 0
		this.pTypAgentaCA=settings.probOfAgentCA;
		
		//jesli typ agenta CA (0) wtedy wartoœæ prawdopodobieñstwa 1 jak LA(1) to 0, a jak i to i to, wtedy <0:1]
		if (settings.agentType==typeOfAgent.CA)
			pTypAgentaCA=1;
		else if (settings.agentType==typeOfAgent.LA)
			pTypAgentaCA=0;
		
		this.init = new Initialization[settings.numberOfExperiments];
		this.run = new Run[settings.numberOfExperiments];
		
		for (int i=0 ; i<settings.numberOfExperiments;i++)
		{
			this.init[i]= new Initialization(settings);
			this.run[i]= new Run(init[i].getMatrix(),settings);
		}
		
		this.paint= new Paint[settings.numberOfExperiments][settings.numberOfFrames+1];
		
		
		this.statistics=new Statistics[settings.numberOfExperiments];
		for (int i=0 ; i<settings.numberOfExperiments;i++)
			statistics[i]= new Statistics(run[i],settings);
		
		this.testPlikCA = new ArrayList<>();
		this.testPlikLA = new ArrayList<>();
		this.testPlikMain = new ArrayList<>();
	}
	
	public boolean LACheck(Run run)
	{
		
		if (run.LACheck(run.temporary))
			return true;
		return false;
	}
	
	public String filling2(double string)
	{
		String doub= Double.toString(string);
		String format= String.format("%1$-5s",doub).replace(' ', '0');
		
		return format;
	}
	
	
	
}
