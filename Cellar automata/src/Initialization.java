
public class Initialization {

	Cell[][] cell;
	private int x;
	private int y;
	
	/*(int rangeX, int rangeY, int period,double pInitC,double[] pStrategy
	, double pEmpty, int lengthOfHistory, double p_typ_ag_ca,double pPayoutSharing,int kMax
	, double pOfCoopMin, double epsilon, Mutation mutation, double deltapC)*/
	
	public Initialization(Settings settings)
	{
		
		this.x=settings.numberOfRows;
		this.y=settings.numberOfColumns;
		
		if( x!=0 && y!=0)
		{
			cell= new Cell[x][y];
			
			for (int i=0; i<x; i++)
				for(int j=0; j<y; j++)
				{
					//cell[i][j]= new Cell(pInitC,pStrategy,pEmpty,lengthOfHistory,p_typ_ag_ca,pPayoutSharing,kMax, pOfCoopMin,epsilon, mutation,deltapC);
					cell[i][j]= new Cell(settings);
				}
			
		}
		else
		{
			if (x==0)
				cell= new Cell[settings.numberOfFrames][y];
			else
				cell= new Cell[settings.numberOfFrames][x];
			
				for (int i=0; i<cell.length; i++)
					for(int j=0; j<cell[i].length; j++)
					{
						//cell[i][j]= new Cell(pInitC,pStrategy,1,lengthOfHistory,p_typ_ag_ca,pPayoutSharing,kMax, pOfCoopMin,epsilon, mutation,deltapC);
						cell[i][j]= new Cell(settings);
					}
				
				for(int j=0; j<cell[0].length; j++)
					//cell[0][j]= new Cell(pInitC,pStrategy,pEmpty,lengthOfHistory,p_typ_ag_ca,pPayoutSharing,kMax, pOfCoopMin,epsilon, mutation,deltapC);
					cell[0][j]= new Cell(settings);
		}
		;
	}
	
	public void showMatrix()
	{
		if(this.x!=0 && this.y!=0)
			for (int i=0; i<x; i++)
			{
				for(int j=0; j<y; j++)
					cell[i][j].showStrategy();
				System.out.println(' ');
			}
	}
	public Cell[][] getMatrix()
	{
		return cell;
	}
}
