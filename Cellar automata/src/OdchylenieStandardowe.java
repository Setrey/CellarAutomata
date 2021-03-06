import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OdchylenieStandardowe {
	
	String standardDeviatation[];
	
	double deviationStateC=0;
	double avarageStatesC=0;
	double deviationIncome=0;
	double avarageIncome=0;
	double deviationPercentCA=0;
	double avaragePercentCA=0;
	double deviationPercentLA=0;
	double avaragePercentLA=0;
	double deviationSharing=0;
	double avarageSharing=0;
	double deviationPercentC=0;
	double avaragePercentC=0;
	double deviationPercentP=0;
	double avaragePercentP=0;
	double deviationPercentD=0;
	double avaragePercentD=0;
	double deviationPercentK=0;
	double avaragePercentK=0;
	double deviationQChanges=0;
	double avarageQChanges=0;
	
	public void standardDeviation(Statistics stats[], int period)
	{
		standardDeviatation= new String[period+3];
		standardDeviatation[0]="#liczba eksperymentów: "+stats.length;
		standardDeviatation[1]="# nrIte, %OSstanówC, SrStanówC, %OSsredniDochod, SrDochod, %OSkomorekCA, SrKomorekCA, %OSkomorekLA, SrKomorekLA, %OSkomorekDzielacychDochod, SrKomDzielDoch, %OSkAllC, SrKAllC %OSkPC, SrKPC, %OSkAllD, SrKAllD, %OSkK-D, SrKK-D,%OSzmianWChwiliQ, SrZmianWChwiliQ";
		
		
		
		for (int i=2; i<period+3;i++)
		{
			double sredniaProcentStanC=0;
			double sredniadochod=0;
			double sredniaProcentCA=0;
			double sredniaProcentLA=0;
			double sredniaProcentC=0;
			double sredniaProcentP=0;
			double sredniaProcentD=0;
			double sredniaProcentK=0;
			double sredniaProcentDzielonyDochod=0;
			double sredniaProcentQZmian=0;
			/*
			double kwadratProcentStanC=0;
			double kwadratdochod=0;
			double kwadratProcentCA=0;
			double kwadratProcentLA=0;
			double kwadratProcentC=0;
			double kwadratProcentP=0;
			double kwadratProcentD=0;
			double kwadratProcentK=0;
			double kwadratProcentDzielonyDochod=0;
			double kwadratProcentQZmian=0;
			*/
			//System.out.println("obliczanie sredniej z eksperymentów:");
			for (int j=0; j<stats.length; j++)
			{
				sredniaProcentStanC+=round4(stats[j].procentStanC[i-2]);
				//System.out.println(stats[j].dochod[i-2]);
				 sredniadochod+=round4(stats[j].dochod[i-2]);
				 //System.out.println("zmienna sredni dochod:" +sredniadochod);
				 sredniaProcentCA+=round4(stats[j].procentCA[i-2]);
				 sredniaProcentLA+=round4(stats[j].procentLA[i-2]);
				 sredniaProcentC+=round4(stats[j].procentC[i-2]);
				 sredniaProcentP+=round4(stats[j].procentP[i-2]);
				 sredniaProcentD+=round4(stats[j].procentD[i-2]);    
				 sredniaProcentK+=round4(stats[j].procentK[i-2]);
				 sredniaProcentDzielonyDochod+=round4(stats[j].procentdzieleniaDochodow[i-2]);
				 sredniaProcentQZmian+=round4(stats[j].procentQChanges[i-2]);
/*
				 kwadratProcentStanC+=round2(Math.pow(stats[j].procentStanC[i-2], 2));
				 kwadratdochod+=round2(Math.pow(stats[j].dochod[i-2], 2));
				 kwadratProcentCA+=round2(Math.pow(stats[j].procentCA[i-2], 2));
				 kwadratProcentLA+=round2(Math.pow(stats[j].procentLA[i-2], 2));
				 kwadratProcentC+=round2(Math.pow(stats[j].procentC[i-2], 2));
				 kwadratProcentP+=round2(Math.pow(stats[j].procentP[i-2], 2));
				 kwadratProcentD+=round2(Math.pow(stats[j].procentD[i-2], 2));
				 kwadratProcentK+=round2(Math.pow(stats[j].procentK[i-2], 2));
				 kwadratProcentDzielonyDochod+=round2(Math.pow(stats[j].procentdzieleniaDochodow[i-2], 2));
				 kwadratProcentQZmian+=round2(Math.pow(stats[j].procentQChanges[i-2], 2));
				 */	 
			}
			
			 //System.out.println("podzielic na "+stats.length);
			sredniaProcentStanC=round2(sredniaProcentStanC/=stats.length);
			sredniadochod=round2(sredniadochod/=stats.length);
			 //System.out.println(sredniadochod);
			 sredniaProcentCA=round2(sredniaProcentCA/=stats.length);
			 sredniaProcentLA=round2(sredniaProcentLA/=stats.length);
			 sredniaProcentC=round2(sredniaProcentC/=stats.length);
			 sredniaProcentP=round2(sredniaProcentP/=stats.length);
			 sredniaProcentD=round2(sredniaProcentD/=stats.length);
			 sredniaProcentK=round2(sredniaProcentK/=stats.length);
			 sredniaProcentDzielonyDochod=round2(sredniaProcentDzielonyDochod/=stats.length);
			 sredniaProcentQZmian=round2(sredniaProcentQZmian/=stats.length);
			 /*
			System.out.println("podzielic na "+stats.length);
			 sredniaProcentStanC/=stats.length;
			 sredniadochod/=stats.length;
			 System.out.println(sredniadochod);
			 sredniaProcentCA/=stats.length;
			 sredniaProcentLA/=stats.length;
			 sredniaProcentC/=stats.length;
			 sredniaProcentP/=stats.length;
			 sredniaProcentD/=stats.length;
			 sredniaProcentK/=stats.length;
			 sredniaProcentDzielonyDochod/=stats.length;
			 sredniaProcentQZmian/=stats.length;
			*/
			double odchylenieProcentStanC=0;
			double odchyleniedochod=0;
			double odchylenieProcentCA=0;
			double odchylenieProcentLA=0;
			double odchylenieProcentC=0;
			double odchylenieProcentP=0;
			double odchylenieProcentD=0;
			double odchylenieProcentK=0;
			double odchylenieProcentDzielonyDochod=0;
			double odchylenieProcentQZmian=0;
			//System.out.println("liczenie odchylenia standardowego:");
			for (int j=0; j<stats.length; j++)
			{
				odchylenieProcentStanC+=Math.pow(stats[j].procentStanC[i-2]- sredniaProcentStanC,2);
				/*
				System.out.println("zmienna przed: "+odchyleniedochod);
				System.out.println("srednia= "+sredniadochod + " dochod[i-2]= "+ stats[j].dochod[i-2]);
				System.out.println("wynik odejmowania: "+ (stats[j].dochod[i-2]- sredniadochod));
				System.out.println("potega: "+ (Math.pow(stats[j].dochod[i-2]- sredniadochod, 2)));
				*/
				odchyleniedochod+=Math.pow(stats[j].dochod[i-2]- sredniadochod, 2);
				//System.out.println("zmienna po: "+odchyleniedochod);
				odchylenieProcentCA+=Math.pow(stats[j].procentCA[i-2]- sredniaProcentCA, 2);
				odchylenieProcentLA+=Math.pow(stats[j].procentLA[i-2]- sredniaProcentLA, 2);
				odchylenieProcentC+=Math.pow(stats[j].procentC[i-2]- sredniaProcentC, 2);
				odchylenieProcentP+=Math.pow(stats[j].procentP[i-2]- sredniaProcentP, 2);
				odchylenieProcentD+=Math.pow(stats[j].procentD[i-2]- sredniaProcentD, 2);
				odchylenieProcentK+=Math.pow(stats[j].procentK[i-2]- sredniaProcentK, 2);
				odchylenieProcentDzielonyDochod+=Math.pow(stats[j].procentdzieleniaDochodow[i-2]- sredniaProcentDzielonyDochod, 2);
				odchylenieProcentQZmian+=Math.pow(stats[j].procentQChanges[i-2]- sredniaProcentQZmian, 2);
			}
			standardDeviatation[i]=(i-2)+" ";
			
			standardDeviatation[i]+=" ";
			
			 deviationStateC=round4(odchylenie (odchylenieProcentStanC,stats.length));
			 avarageStatesC=round4(sredniaProcentStanC);
			 
			 standardDeviatation[i]+=filling2(deviationStateC)+" ";
			 standardDeviatation[i]+=filling2(avarageStatesC)+" ";

			 deviationIncome=round4(odchylenie (odchyleniedochod,stats.length));
			 avarageIncome=round4(sredniadochod);
			 
			 standardDeviatation[i]+=filling2(deviationIncome)+" ";
			 standardDeviatation[i]+=filling2(avarageIncome)+" ";
			 
			 deviationPercentCA=round4(odchylenie (odchylenieProcentCA,stats.length));
			 avaragePercentCA=round4(sredniaProcentCA);
			 
			 standardDeviatation[i]+=filling2(deviationPercentCA)+" ";
			 standardDeviatation[i]+=filling2(avaragePercentCA)+" ";
			 
			 deviationPercentLA=round4(odchylenie (odchylenieProcentLA,stats.length));
			 avaragePercentLA=round4(sredniaProcentLA);
			 
			 standardDeviatation[i]+=filling2(deviationPercentLA)+" ";
			 standardDeviatation[i]+=filling2(avaragePercentLA)+" ";
			 
			 deviationSharing=round4(odchylenie (odchylenieProcentDzielonyDochod,stats.length));
			 avarageSharing=round4(sredniaProcentDzielonyDochod);
			 
			 standardDeviatation[i]+=filling2(deviationSharing)+" ";
			 standardDeviatation[i]+=filling2(avarageSharing)+" ";
			 
			 deviationPercentC=round4(odchylenie (odchylenieProcentC,stats.length));
			 avaragePercentC=round4(sredniaProcentC);
			 
			 standardDeviatation[i]+=filling2(deviationPercentC)+" ";
			 standardDeviatation[i]+=filling2(avaragePercentC)+" ";
			 
			 deviationPercentP=round4(odchylenie (odchylenieProcentP,stats.length));
			 avaragePercentP=round4(sredniaProcentP);
			 
			 standardDeviatation[i]+=filling2(deviationPercentP)+" ";
			 standardDeviatation[i]+=filling2(avaragePercentP)+" ";
			 
			 deviationPercentD=round4(odchylenie (odchylenieProcentD,stats.length));
			 avaragePercentD=round4(sredniaProcentD);
			 
			 standardDeviatation[i]+=filling2(deviationPercentD)+" ";
			 standardDeviatation[i]+=filling2(avaragePercentD)+" ";
			 
			 deviationPercentK=round4(odchylenie (odchylenieProcentK,stats.length));
			 avaragePercentK=round4(sredniaProcentK);
			 
			 standardDeviatation[i]+=filling2(deviationPercentK)+" ";
			 standardDeviatation[i]+=filling2(avaragePercentK)+" ";
			 
			 deviationQChanges=round4(odchylenie (odchylenieProcentQZmian,stats.length));
			 avarageQChanges=round4(sredniaProcentQZmian);
			 
			 standardDeviatation[i]+=filling2(deviationQChanges)+" ";
			 standardDeviatation[i]+=filling2(avarageQChanges)+" ";
			//System.out.println(" finalnie "+filling2(round4(odchylenie (odchyleniedochod,stats.length)))+" ");
			
		}
	}
	private double odchylenie(double licznikOdchylenia, double m)
	{
		return (double) Math.sqrt(licznikOdchylenia/m);
	}
	
	public Double round2(Double val) {
	    return new BigDecimal(val.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}
	
	public Double round4(Double val) {
	    return new BigDecimal(val.toString()).setScale(4,RoundingMode.HALF_UP).doubleValue();
	}
	public void writeToFile(PrintWriter out) throws FileNotFoundException
	{
		for (int i=0; i<standardDeviatation.length; i++)
			out.println(standardDeviatation[i]);
	}

	public String filling2(double string)
	{
		String doub= Double.toString(string);
		String format= String.format("%1$-5s",doub).replace(' ', '0');
		
		return format;
	}
	
	public void writeToFile(PrintWriter out,String s) throws FileNotFoundException
	{
			out.println(s);
	}
	
}
