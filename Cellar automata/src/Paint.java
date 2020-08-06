import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paint {
	
	public BufferedImage imageCALA;
	public BufferedImage imageStates;
	public BufferedImage imageStrategies;
	public BufferedImage imagekD;
	public BufferedImage imageOfKpalette;
	public int			areaForText=0;
	private int squareInPalette=30;
	public double sum[][];
	public Paint(Run run, boolean kConst)
	{
		imageCALA = new BufferedImage(run.yRange+areaForText, run.xRange, BufferedImage.TYPE_INT_ARGB);
		//System.out.println("imageCALA: H:"+ imageCALA.getWidth()+ " W:" + imageCALA.getHeight()+" runX:" + run.xRange+ " runY:"+ run.yRange);
		imageStates= new BufferedImage(run.yRange+areaForText, run.xRange, BufferedImage.TYPE_INT_ARGB);
		imageStrategies= new BufferedImage(run.yRange+areaForText, run.xRange, BufferedImage.TYPE_INT_ARGB);
		imagekD= new BufferedImage(run.yRange+areaForText, run.xRange, BufferedImage.TYPE_INT_ARGB);
		imageOfKpalette= new BufferedImage(squareInPalette*8,squareInPalette , BufferedImage.TYPE_INT_ARGB);
		sum=new double[run.xRange][run.yRange];
			int o=1;
			for (int i=0; i<run.xRange; i++)
				for ( int j=0 ; j<run.yRange+areaForText; j++)
				{
					sum[i][j]=run.temporary[i][j].sum;
					if (run.temporary[i+o][j+o].cellEmpty)
					{
						imageCALA.setRGB(j, i, Color.WHITE.getRGB());
						imageStates.setRGB(j, i, Color.WHITE.getRGB());
						imageStrategies.setRGB(j, i, Color.WHITE.getRGB());
						imagekD.setRGB(j, i, Color.WHITE.getRGB());
					}
					else {
						if ( run.temporary[i+o][j+o].learningAutomata)
							imageStrategies.setRGB(j, i, Color.MAGENTA.getRGB());
						
						if ( !run.temporary[i+o][j+o].learningAutomata && run.temporary[i+o][j+o].sharingPayout) 
							imageCALA.setRGB(j, i, Color.RED.getRGB());
						if ( run.temporary[i+o][j+o].state=='C')
							imageStates.setRGB(j, i, Color.RED.getRGB());
						if ( run.temporary[i+o][j+o].strategy.buffor=='C' &&!run.temporary[i+o][j+o].learningAutomata)
							imageStrategies.setRGB(j, i, Color.RED.getRGB());
						
						
						if (!run.temporary[i+o][j+o].learningAutomata && !run.temporary[i+o][j+o].sharingPayout)
							imageCALA.setRGB(j, i, Color.ORANGE.getRGB());
						if (run.temporary[i+o][j+o].strategy.buffor=='P' && !run.temporary[i+o][j+o].learningAutomata)
							imageStrategies.setRGB(j, i, Color.ORANGE.getRGB());
						
						if (run.temporary[i+o][j+o].strategy.buffor=='P'
								||run.temporary[i+o][j+o].strategy.buffor=='D'
								||run.temporary[i+o][j+o].strategy.buffor=='C')
							imagekD.setRGB(j, i, Color.BLACK.getRGB());
						else if (run.temporary[i+o][j+o].strategy.buffor=='K')
							for (int c=0; c<8; c++)
							{
								if (run.temporary[i+o][j+o].strategy.kMax==c)
								{
									
									int con =20;
									int g = 85+(7*con);
									int b = 40;
									int p = (Color.ORANGE.getAlpha()<<24) | (Color.ORANGE.getRed()<<16)
									| (g-(con*c)<<8) | b;	
									
									imagekD.setRGB(j, i, p);
								}
							}
						else 
							imagekD.setRGB(j, i, Color.WHITE.getRGB());
							
						if (run.temporary[i+o][j+o].learningAutomata && run.temporary[i+o][j+o].sharingPayout)
							imageCALA.setRGB(j, i, Color.BLUE.getRGB());
						if (run.temporary[i+o][j+o].state=='D')
							imageStates.setRGB(j, i, Color.BLUE.getRGB());
						if (run.temporary[i+o][j+o].strategy.buffor=='D' && !run.temporary[i+o][j+o].learningAutomata)
						{
							imageStrategies.setRGB(j, i, Color.BLUE.getRGB());
						}
						
						if (run.temporary[i+o][j+o].learningAutomata && !run.temporary[i+o][j+o].sharingPayout)
							imageCALA.setRGB(j, i, Color.GREEN.getRGB());
						if (run.temporary[i+o][j+o].strategy.buffor=='K' &&!run.temporary[i+o][j+o].learningAutomata)
							imageStrategies.setRGB(j, i, Color.GREEN.getRGB());
						
					}
				}
		createPalette();
	}
	public void createPalette()
	{
	
		for (int c=0; c<8; c++)
			for (int i=0; i <squareInPalette; i++)
				for (int j=0; j<squareInPalette*8; j++)
				{
					int con =20;
					int g = 85+(7*con);
					int b = 40;
					
					if (j>squareInPalette*c && (j<=(squareInPalette*(c+1))))
					{
						int p = (Color.ORANGE.getAlpha()<<24) | (Color.ORANGE.getRed()<<16)
								| (g-(con*c)<<8) | b;		
					
						imageOfKpalette.setRGB(j, i , p);
					}
				}
	}

	public Paint() {
		// TODO Auto-generated constructor stub
	}

	public BufferedImage getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	public BufferedImage getScaledImage(Image srcImg, int w, int h, int i, int j, int x, int y){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.setColor(Color.WHITE);
	    g2.drawRect(i, j, x, y);
	    g2.dispose();
	    return resizedImg;
	}
	
	public void PrintToFile(File fCALA, File fStates, File fStrategies,File fkD) throws IOException
	{
		
		ImageIO.write(getScaledImage(imageCALA,300,300), "png", fCALA);
		ImageIO.write(getScaledImage(imageStates,300,300), "png", fStates);
		ImageIO.write(getScaledImage(imageStrategies,300,300), "png", fStrategies);
		ImageIO.write(getScaledImage(imagekD,300,300), "png", fkD);
		ImageIO.write(getScaledImage(imageOfKpalette, squareInPalette*7,squareInPalette),"png", new File("paleta kBarw.png"));
	}
}
