

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {

	PrintWriter out;
	String standardDeviatation[];
	String line[];
	int index=0;
	
	double procentStanC[];
	//double procentStanC[];
	double dochod[];
	double procentCA[];
	double procentLA[];
	double procentdzieleniaDochodow[];
	double procentC[];
	double procentP[];
	double procentD[];
	double procentK[];
	double procentQChanges[];
	/*
	 (Run run, int r, int period, int qRounds, double p_niezam, typeOfAgent typ_agenta, double pTypAgentaCA
			,double pInitC, int kMax, boolean kConst, double []pStrategy, int lengthOfHisotry
			,Mutation mutation, double pPayoutSharing, double epsilon)	 
	 */
	public Statistics(Run run, Settings settings)
	{
		line= new String[settings.numberOfFrames+12];
		
		int period= settings.numberOfFrames+1; // dlatego ze trzeba zrobic stan 0 poczatkowy + zaczac normalnie iteracje do konca periodu.
		
			  procentStanC= new double [period];
			  //procentStanC= new double [2];
			  dochod= new double [period];
			  procentCA= new double [period];
			  procentLA= new double [period];
			  procentdzieleniaDochodow= new double [period];
			  procentC= new double [period];
			  procentP= new double [period];
			  procentD= new double [period];
			  procentK= new double [period];
			  procentQChanges= new double[period];
		
		for (int i=0 ; i<line.length; i++)
			line[i]=null;
		line[0]="# siatka wielkosci "+settings.numberOfRows+"x"+settings.numberOfColumns+" r="+settings.radiusOfNeighbor + " p_niezam=" +settings.probOfUnhabitedCell+ " q="+settings.qChanges;
		line[1]="# R()="+ settings.pDSettings.CC+" S="+settings.pDSettings.CD + " T(b)=" + settings.pDSettings.DC+" P()="+ settings.pDSettings.DD;
		line[2]="# typ_agenta="+settings.agentType+" p_typ_ag_ca="+settings.probOfAgentCA+" p_init_C="+settings.probOfInitCState+ " k_Max="+settings.kMax + " k_Const"+settings.isKConst;
		line[3]="# p_pC= "+settings.probOfPcStrategy+" p_AllC="+ settings.probOfAllCStrategy+" pAllD=" + settings.probOfAllDStrategy + " p_KD"+ settings.probOfKDStrategy;
		line[4]="# h="+settings.historyLength+ " epsilon=" + settings.epsilon + " p_wsp_dziel="+settings.probOfPayoffSharing;
		line[5]="# p_mut_reg_1="+settings.mutation.pMutationChangeStrategy+" p_mut_reg_2="+settings.mutation.pMutationChangePc +" p_mut_h="+ settings.mutation.pMutationHistory+ " p_mut_eps="+settings.mutation.pMutationEpsilon;
		line[6]="# c1="+settings.mutation.parameterIncMutation+" c2="+ settings.mutation.parameterHisotryMutation+" c3="+ settings.mutation.parameterEpsilonMutation;
		line[7]="# ziarno: "+ Algorithm.randomSeed;
		line[8]="#";
		line[9]="#";
		line[10]="# Nr Iteracji %komorekC SredniDochodAgenta %komorekCA %komorekLA %KomorekZeStanemCdlaCA "
				+ "%KomorekZeStanemCdlaLA %komorekDzielacychDochod %komorkiCAzAll-C %komorkiCAzPC %komorkiCAzAll-D %komorkiCAzk-D "
				+ "%sredniaParametruH %sredniaParametruEpsilon %sredniaWartoscParametruP_C %procentZmianWChwiliQ %CA0-D %CA1-D %CA2-D %CA3-D %CA4-D %CA5-D %CA6-D %CA7-D";
		this.index=11;
	}
	public void addRow (Run run, int index)
	{
		line[this.index+index]=index+" ";
		
		line[this.index+index]+=filling2(run.percentOfCStates)+" ";
		this.procentStanC [index]=run.percentOfCStates;
		
		line[this.index+index]+=filling2(((run.avarageIncome>1)?(1):(run.avarageIncome)))+" ";
		this.dochod [index]=((run.avarageIncome>1)?(1):(run.avarageIncome));
		
		line[this.index+index]+=filling2(run.percentOfCACell)+" ";
		this.procentCA [index]=run.percentOfCACell;
		
		line[this.index+index]+=filling2(run.percentOfLACell)+" ";
		this.procentLA [index]=run.percentOfLACell;
		
		line[this.index+index]+=filling2(run.percentOfCStatesInCACell)+" ";
		line[this.index+index]+=filling2(run.percentOfCStatesInLACell)+" ";
		line[this.index+index]+=filling2(run.percentOfSharingCell)+" ";
		this.procentdzieleniaDochodow [index]=run.percentOfSharingCell;
		
		line[this.index+index]+=filling2(run.percentOfAllCStrategy)+" ";
		this.procentC [index]=run.percentOfAllCStrategy;
		
		line[this.index+index]+=filling2(run.percentOfPcStrategy)+" ";
		this.procentP [index]=run.percentOfPcStrategy;
		
		line[this.index+index]+=filling2(run.percentOfallDStrategy)+" ";
		this.procentD [index]=run.percentOfallDStrategy;
		
		line[this.index+index]+=filling2(run.percentOfkDStrategy)+" ";
		this.procentK [index]=run.percentOfkDStrategy;
		
		line[this.index+index]+=filling2(run.avarageHParameter)+" ";
		
		line[this.index+index]+=filling2(run.avarageEpsParameter)+" ";
		line[this.index+index]+=filling2(run.avaragePcParameter)+" ";
		
		line[this.index+index]+=filling2(run.percentOfQChanges)+" ";
		this.procentQChanges[index]=run.percentOfQChanges;
		for (int i=0; i <run.percentOfkD.length-1;i++)
			line[this.index+index]+=filling2(run.percentOfkD[i])+" ";
		line[this.index+index]+=filling2(run.percentOfkD[7]);
		//System.out.println(line[this.index+index]);
	}
	public Double round2(Double val) {
	    return new BigDecimal(val.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}
	public Double round4(Double val) {
	    return new BigDecimal(val.toString()).setScale(4,RoundingMode.HALF_UP).doubleValue();
	}
	public void writeToFile(PrintWriter out, int index) throws FileNotFoundException
	{
		
		for (int i=0; i<line.length; i++)
		{
			if (i==11)
				out.println("# Eksperyment nr "+ index);
			out.println(line[i]);
			
		}
	}
	public String filling2(double string)
	{
		String doub= Double.toString(string);
		String format= String.format("%1$-4s",doub).replace(' ', '0');
		
		return format;
	}
	
}
