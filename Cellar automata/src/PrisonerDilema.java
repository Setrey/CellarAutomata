public class PrisonerDilema {

	double CC;
	double CD;
	double DC;
	double DD;
	
	PrisonerDilema(double cc, double cd, double dc, double dd)
	{
		 CC=cc;//= 0.5;
		 CD=cd;//= 0;
		 DC=dc;//= 1.5;
		 DD=dd;//= 0.1;
	}
	
	public double confrontation (char agent, char Neighbour)
	{
		if (agent=='C')
			if (Neighbour=='D')
				return this.CD;
			else
				return this.CC;
		else
			if(Neighbour=='D')
				return this.DD;
			else
				return this.DC;
	}
}
