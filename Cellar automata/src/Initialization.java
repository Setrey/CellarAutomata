
public class Initialization {

	Cell[][] cell;
	private int rangeX;
	private int rangeY;
	
	/*(int rangeX, int rangeY, int period,double pInitC,double[] pStrategy
	, double pEmpty, int lengthOfHistory, double p_typ_ag_ca,double pPayoutSharing,int kMax
	, double pOfCoopMin, double epsilon, Mutation mutation, double deltapC)*/
	
	public Initialization(Settings settings)
	{
		
		this.rangeX=settings.numberOfRows;
		this.rangeY=settings.numberOfColumns;
		if( rangeX!=0 && rangeY!=0)
		{
			cell= new Cell[rangeX][rangeY];
			
			for (int i=0; i<rangeX; i++)
				for(int j=0; j<rangeY; j++)
				{
					//cell[i][j]= new Cell(pInitC,pStrategy,pEmpty,lengthOfHistory,p_typ_ag_ca,pPayoutSharing,kMax, pOfCoopMin,epsilon, mutation,deltapC);
					cell[i][j]= new Cell(settings);
				}
			
		}
		else
		{
			if (rangeX==0)
				cell= new Cell[settings.numberOfFrames][rangeY];
			else
				cell= new Cell[settings.numberOfFrames][rangeX];
			
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
		if(this.rangeX!=0 && this.rangeY!=0)
			for (int i=0; i<rangeX; i++)
			{
				for(int j=0; j<rangeY; j++)
					cell[i][j].showStrategy();
				System.out.println(' ');
			}
	}
	public Cell[][] getMatrix()
	{
		return cell;
	}
}
