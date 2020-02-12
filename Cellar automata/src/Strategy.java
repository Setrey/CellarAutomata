

public class Strategy 
{
	char buffor;	// if buffor =='0' then empty space
	int  kMax;		
	
	
	public Strategy(double []pStrategy, int kMax)
	{
		double n=Algorithm.randomValue.nextDouble();
		
		if (n<pStrategy[0])
			buffor='P';
		else if(n>=pStrategy[0] && n<pStrategy[1])
			buffor='C';
		else if(n>=pStrategy[1] && n<pStrategy[2])
			buffor='D';
		else if(n>=pStrategy[2])
			buffor='K';
		else
			buffor='E';
		
		if (kMax>0)		//jesli kMax jest 0-7, to wtedy kConst=true
			this.kMax=kMax;
		else
		{
			kMax*=-1;
			this.kMax=Algorithm.randomValue.nextInt(kMax+1);
		}
	}
	
	public Strategy(char buffor, int kMax)
	{
		this.buffor=buffor;
		this.kMax=kMax;
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
