

public class Strategy 
{
	char buffor;	// if buffor =='0' then empty space
	int  kMax;		
	
	/*double []pStrategy, int kMax*/
	public Strategy(Settings settings)
	{
		double n=Algorithm.randomValue.nextDouble();
		
		if (n>=0 && n<settings.probOfPcStrategy)
			buffor='P';
		else if(n>=settings.probOfPcStrategy && n<settings.probOfAllCStrategy+settings.probOfPcStrategy)
			buffor='C';
		else if(n>=settings.probOfAllCStrategy+settings.probOfPcStrategy && n<settings.probOfAllCStrategy+settings.probOfPcStrategy+settings.probOfAllDStrategy)
			buffor='D';
		else if(n>=settings.probOfAllCStrategy+settings.probOfPcStrategy+settings.probOfAllDStrategy && n<=1)
			buffor='K';
		else
			buffor='E';
		
		if (settings.isKConst)		//jesli kMax jest 0-7, to wtedy kConst=true
			this.kMax=settings.kMax;
		else
			this.kMax=Algorithm.randomValue.nextInt(settings.kMax+1);
	}
	
	public Strategy(char buffor, int kMax)
	{
		this.buffor=buffor;
		this.kMax=kMax;
	}
	public Strategy()
	{
		this.buffor='-';
		this.kMax=0;
	}
	public void display()
	{
		System.out.print(buffor);
	}
	public char getStrategy()
	{
		return buffor;
	}
}
