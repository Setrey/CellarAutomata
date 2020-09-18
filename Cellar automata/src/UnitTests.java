import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import org.junit.jupiter.api.Test;


class UnitTests {


	 @Test
	 	public void test01() throws IOException {
		String testName="test1_CA";
		 
		Settings ustawienia=new Settings();
		ustawienia.numberOfColumns=5;
		ustawienia.numberOfRows=5;
		ustawienia.numberOfExperiments=1;
		ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
		
		
		Algorithm algorithm= new Algorithm(ustawienia);
		
		// E-Empty C- cooperate , D- defects
		char [][] tablicaStanów= {
				{'C','C','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'}};
		
		// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
		char [][] tablicaTaktyk= {
				{'C','C','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'}};
		
		int [][] kTolerancji= {
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}};
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		
		assertEquals(1,algorithm.run[0].percentOfCStates);
		assertEquals(1,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(1,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(1,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=1;
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		assertEquals(1,algorithm.run[0].percentOfCStates);
		assertEquals(1,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(1,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(1,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
	}
	 
	 @Test
		public void test02() throws IOException {
		 	String testName="test2_CA";
		 	
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk= {
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'}};
			
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].temporary[1][2].payout);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		}
	 	
	 @Test
		public void test03() throws IOException {
		 String testName="test3_CA";
		 
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','D','D','D','D'},
					{'D','C','D','D','D'},
					{'C','D','C','D','D'},
					{'D','C','D','C','D'},
					{'D','D','D','D','D'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk= {
					{'C','D','D','D','D'},
					{'D','C','D','D','D'},
					{'C','D','C','D','D'},
					{'D','C','D','C','D'},
					{'D','D','D','D','D'}};
			
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.31,algorithm.run[0].avarageIncome);
			assertEquals(0.24,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.24,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.24,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.76,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=0; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.31,algorithm.run[0].avarageIncome);
			assertEquals(0.24,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.24,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.24,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.76,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.84,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.11,algorithm.run[0].avarageIncome);
			assertEquals(0.08,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.08,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.08,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.92,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.84,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=2; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		}
	 
	@Test
		public void test04() throws IOException {
			String testName="test4_CA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','D','C','D','C'},
					{'D','D','C','C','D'},
					{'D','D','C','C','C'},
					{'D','D','D','D','C'},
					{'C','C','C','D','D'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk= {
					{'C','D','C','D','C'},
					{'D','D','C','C','D'},
					{'D','D','C','C','C'},
					{'D','D','D','D','C'},
					{'C','C','C','D','D'}};
			
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			algorithm.run[0].showPayoffsWithoutBorder();
			algorithm.run[0].showStrategiesWithoutBorder();
			assertEquals(0.48,algorithm.run[0].percentOfCStates);
			assertEquals(0.58,algorithm.run[0].avarageIncome);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.48,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.48,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.52,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=0; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.48,algorithm.run[0].percentOfCStates);
			assertEquals(0.58,algorithm.run[0].avarageIncome);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.48,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.48,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.52,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			algorithm.run[0].showPayoffsWithBorder();
			algorithm.run[0].showStrategiesWithoutBorder();
			System.out.println(">"+algorithm.run[0].percentOfQChanges);
			assertEquals(0.76,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
	
			ustawienia.numberOfFrames=1; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		}
		
		@Test
		public void test05() throws IOException {
		String testName="test5_CA";
		Settings ustawienia=new Settings();
		ustawienia.numberOfColumns=5;
		ustawienia.numberOfRows=5;
		ustawienia.numberOfExperiments=1;
		ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
		
		
		Algorithm algorithm= new Algorithm(ustawienia);
		
		// E-Empty C- cooperate , D- defects
		char [][] tablicaStanów= {
				{'C','C','C','C','C'},
				{'C','C','D','C','C'},
				{'C','C','D','C','C'},
				{'C','C','D','C','C'},
				{'C','C','C','C','C'}};
		
		// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
		char [][] tablicaTaktyk= {
				{'C','C','C','C','C'},
				{'C','C','D','C','C'},
				{'C','C','D','C','C'},
				{'C','C','D','C','C'},
				{'C','C','C','C','C'}};
		
		int [][] kTolerancji= {
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}};
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.88,algorithm.run[0].percentOfCStates);
		assertEquals(0.92,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.88,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.88,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.12,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		//assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
	
		ustawienia.numberOfFrames=0;
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}	
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.88,algorithm.run[0].percentOfCStates);
		assertEquals(0.92,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.88,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.88,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.12,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0.52,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=1; 
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.4,algorithm.run[0].percentOfCStates);
		assertEquals(0.46,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.4,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.4,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.6,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		System.out.println(algorithm.run[0].percentOfQChanges);
		assertEquals(0.6,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=2; 
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.8,algorithm.run[0].percentOfCStates);
		assertEquals(0.86,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.8,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.8,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0.4,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=3; 
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.4,algorithm.run[0].percentOfCStates);
		assertEquals(0.46,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.4,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.4,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.6,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0.6,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
	}
		
		@Test
		public void test06() throws IOException {
		String testName="test6_CA";
		Settings ustawienia=new Settings();
		ustawienia.numberOfColumns=5;
		ustawienia.numberOfRows=5;
		ustawienia.numberOfExperiments=1;
		ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
		
		
		Algorithm algorithm= new Algorithm(ustawienia);
		
		// E-Empty C- cooperate , D- defects
		char [][] tablicaStanów= {
				{'C','C','D','D','C'},
				{'D','C','D','D','C'},
				{'C','C','C','C','C'},
				{'C','D','C','C','C'},
				{'C','C','C','D','C'}};
		
		// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
		char [][] tablicaTaktyk= {
				{'K','C','D','D','C'},
				{'K','C','K','D','C'},
				{'C','C','K','K','C'},
				{'C','D','K','C','C'},
				{'C','C','K','D','C'}};
		
		int [][] kTolerancji= {
				{1,0,0,0,0},
				{1,0,3,0,0},
				{0,0,2,3,0},
				{0,0,3,0,0},
				{0,0,3,0,0}};
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showStatesWithoutBorder();
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.72,algorithm.run[0].percentOfCStates);
		assertEquals(0.8,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.72,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.52,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.28,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		//assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0.29,algorithm.run[0].percentOfkD[1]);
		assertEquals(0.14,algorithm.run[0].percentOfkD[2]);
		assertEquals(0.57,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
	
		ustawienia.numberOfFrames=0;
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}	
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showStatesWithoutBorder();
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.72,algorithm.run[0].percentOfCStates);
		assertEquals(0.8,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.72,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.52,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.28,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0.8,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0.29,algorithm.run[0].percentOfkD[1]);
		assertEquals(0.14,algorithm.run[0].percentOfkD[2]);
		assertEquals(0.57,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=1; 
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showStatesWithoutBorder();
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		System.out.println(algorithm.run[0].percentOfCStates);
		assertEquals(0,algorithm.run[0].percentOfCStates);
		assertEquals(0,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.56,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.44,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0.82,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0.18,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=2; 
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0,algorithm.run[0].percentOfCStates);
		assertEquals(0,algorithm.run[0].avarageIncome);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.56,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.44,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0.82,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0.18,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
	}
		
		@Test
		public void test07() throws IOException {
		String testName="test7_CA";
		Settings ustawienia=new Settings();
		ustawienia.numberOfColumns=5;
		ustawienia.numberOfRows=5;
		ustawienia.numberOfExperiments=1;
		ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
		
		
		Algorithm algorithm= new Algorithm(ustawienia);
		
		// E-Empty C- cooperate , D- defects
		char [][] tablicaStanów= {
				{'C','C','D','D','C'},
				{'C','C','C','D','C'},
				{'C','C','C','C','C'},
				{'C','D','C','C','C'},
				{'C','C','C','D','C'}};
		
		// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
		char [][] tablicaTaktyk= {
				{'K','C','D','D','C'},
				{'K','C','K','D','C'},
				{'C','C','K','K','C'},
				{'C','D','K','C','C'},
				{'C','C','K','D','C'}};
		
		int [][] kTolerancji= {
				{1,0,0,0,0},
				{1,0,3,0,0},
				{0,0,2,3,0},
				{0,0,3,0,0},
				{0,0,3,0,0}};
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showStatesWithoutBorder();
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		System.out.println(algorithm.run[0].avarageIncome);
		assertEquals(0.86,algorithm.run[0].avarageIncome);
		assertEquals(0.8,algorithm.run[0].percentOfCStates);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.8,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.52,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.28,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		//assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0.29,algorithm.run[0].percentOfkD[1]);
		assertEquals(0.14,algorithm.run[0].percentOfkD[2]);
		assertEquals(0.57,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
	
		ustawienia.numberOfFrames=0;
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}	
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showStatesWithoutBorder();
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		assertEquals(0.86,algorithm.run[0].avarageIncome);
		assertEquals(0.8,algorithm.run[0].percentOfCStates);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0.8,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0.52,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.28,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0.8,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(0.29,algorithm.run[0].percentOfkD[1]);
		assertEquals(0.14,algorithm.run[0].percentOfkD[2]);
		assertEquals(0.57,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		ustawienia.numberOfFrames=1; 
		
		algorithm= new Algorithm(ustawienia);
		
		for (int i=0 ; i < ustawienia.numberOfRows ; i++)
			for (int j=0; j<ustawienia.numberOfColumns; j++)
			{
				algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
				algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
			}
		
		algorithm.Calculate();
		
		System.out.println(testName+" "+ustawienia.numberOfFrames);
		algorithm.run[0].showStatesWithoutBorder();
		algorithm.run[0].showPayoffsWithoutBorder();
		algorithm.run[0].showStrategiesWithoutBorder();
		System.out.println(algorithm.run[0].percentOfCStates);
		assertEquals(0,algorithm.run[0].avarageIncome);
		assertEquals(0,algorithm.run[0].percentOfCStates);
		assertEquals(1,algorithm.run[0].percentOfCACell);
		assertEquals(0,algorithm.run[0].percentOfLACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
		assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
		assertEquals(0,algorithm.run[0].percentOfSharingCell);
		assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
		assertEquals(0,algorithm.run[0].percentOfPcStrategy);
		assertEquals(0.84,algorithm.run[0].percentOfallDStrategy);
		assertEquals(0.16,algorithm.run[0].percentOfkDStrategy);
		assertEquals(0,algorithm.run[0].avarageHParameter);
		assertEquals(0,algorithm.run[0].avarageEpsParameter);
		assertEquals(0,algorithm.run[0].avaragePcParameter);
		assertEquals(0,algorithm.run[0].percentOfQChanges);
		assertEquals(0,algorithm.run[0].percentOfkD[0]);
		assertEquals(1,algorithm.run[0].percentOfkD[1]);
		assertEquals(0,algorithm.run[0].percentOfkD[2]);
		assertEquals(0,algorithm.run[0].percentOfkD[3]);
		assertEquals(0,algorithm.run[0].percentOfkD[4]);
		assertEquals(0,algorithm.run[0].percentOfkD[5]);
		assertEquals(0,algorithm.run[0].percentOfkD[6]);
		assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
		
	}
		@Test
		public void test08() throws IOException {
			String testName="test8_CA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','D','D','C','C'},
					{'C','E','D','E','C'},
					{'C','C','E','C','C'},
					{'C','D','E','D','C'},
					{'D','D','E','D','C'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk= {
					{'C','D','D','C','C'},
					{'C','E','K','E','C'},
					{'C','C','E','C','C'},
					{'C','D','E','K','C'},
					{'D','K','E','K','C'}};
			
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,3,0,0},
					{0,0,0,0,0},
					{0,0,0,1,0},
					{0,4,0,2,0}};
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.71,algorithm.run[0].avarageIncome);
			assertEquals(0.48,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.6,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.6,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[3]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		
			ustawienia.numberOfFrames=0;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}	
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.71,algorithm.run[0].avarageIncome);
			assertEquals(0.48,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.6,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.6,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.64,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[3]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			algorithm.run[0].showStatesWithoutBorder();
			algorithm.run[0].showKDValuesWithoutBorder();
			algorithm.run[0].showStrategiesWithoutBorder();
			assertEquals(0.47,algorithm.run[0].avarageIncome);
			assertEquals(0.32,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.4,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.35,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.25,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.4,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.72,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0.75,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.13,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.13,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=2; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.76,algorithm.run[0].avarageIncome);
			assertEquals(0.56,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.7,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.65,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.15,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.64,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(1,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=3; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.26,algorithm.run[0].avarageIncome);
			assertEquals(0.16,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.2,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.2,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.3,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.5,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.72,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(1,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=4; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0.25,algorithm.run[0].avarageIncome);
			assertEquals(0.16,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.2,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.2,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.6,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.6,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(1,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=5; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0.8,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.6,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.4,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(1,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		}
		
		@Test
		public void test09() throws IOException {
			String testName="test9_CA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'E','C','E','D','E'},
					{'C','E','C','E','D'},
					{'D','D','C','C','C'},
					{'D','C','E','E','C'},
					{'C','C','D','D','D'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk= {
					{'E','C','E','D','E'},
					{'C','E','K','E','K'},
					{'K','D','K','K','K'},
					{'K','C','E','E','C'},
					{'C','C','D','K','D'}};
			
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,4,0,2},
					{2,0,3,4,2},
					{2,0,0,0,0},
					{0,0,0,5,0}};
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.64,algorithm.run[0].avarageIncome);
			assertEquals(0.4,algorithm.run[0].percentOfCStates);
			assertEquals(0.72,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.56,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.33,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.22,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.44,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.5,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.13,algorithm.run[0].percentOfkD[3]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[4]);
			assertEquals(0.13,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			
			ustawienia.numberOfFrames=0;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.64,algorithm.run[0].avarageIncome);
			assertEquals(0.4,algorithm.run[0].percentOfCStates);
			assertEquals(0.72,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.56,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.33,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.22,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.44,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.6,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.5,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.13,algorithm.run[0].percentOfkD[3]);
			assertEquals(0.25,algorithm.run[0].percentOfkD[4]);
			assertEquals(0.13,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0.72,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.61,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.39,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(1,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			

			
		}
		
		@Test
		public void test10() throws IOException {
			String testName="test10_CA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','C','C','D','D'},
					{'D','C','D','C','C'},
					{'D','C','C','D','C'},
					{'C','C','C','C','D'},
					{'C','C','C','C','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk= {
					{'C','K','K','K','K'},
					{'D','K','K','K','K'},
					{'D','K','K','D','K'},
					{'C','C','C','K','K'},
					{'C','C','C','K','K'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji= {
					{0,2,3,2,3},
					{0,2,3,2,3},
					{0,2,0,0,2},
					{0,0,0,2,2},
					{0,0,0,3,3}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu={
					{1,1,1,1,0},
					{0,0,1,1,0},
					{0,0,1,0,1},
					{0,1,0,1,1},
					{0,0,1,0,0}};
			
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.8,algorithm.run[0].avarageIncome);
			assertEquals(0.72,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.72,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.48,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.28,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.12,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.6,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0.07,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.53,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.4,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			
			ustawienia.numberOfFrames=0;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.8,algorithm.run[0].avarageIncome);
			assertEquals(0.72,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.72,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			System.out.println(algorithm.run[0].percentOfSharingCell);
			assertEquals(0.48,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.28,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.12,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.6,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.8,algorithm.run[0].percentOfQChanges);
			assertEquals(0.07,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.53,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.4,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.16,algorithm.run[0].avarageIncome);
			assertEquals(0.12,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.12,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.12,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.56,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.32,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.8,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(1,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			

			ustawienia.numberOfFrames=2;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.68,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.32,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(1,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
		}
		
		@Test
		public void test11() throws IOException {
			String testName="test11_CA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=1;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','D','D','C','D'},
					{'C','C','C','C','D'},
					{'D','C','C','C','D'},
					{'D','D','C','C','C'},
					{'D','C','C','D','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk= {
					{'K','D','D','K','D'},
					{'K','C','C','C','D'},
					{'K','K','C','C','D'},
					{'D','D','K','K','K'},
					{'D','K','K','K','K'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji= {
					{2,0,0,4,0},
					{3,0,0,0,0},
					{2,2,0,0,0},
					{0,0,3,3,3},
					{0,4,3,3,2}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu={
					{0,0,0,0,1},
					{0,0,1,0,1},
					{0,1,1,1,0},
					{0,1,1,1,0},
					{0,1,1,1,1}};
			
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.7,algorithm.run[0].avarageIncome);
			assertEquals(0.6,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.6,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.52,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.2,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.32,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.48,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.33,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.5,algorithm.run[0].percentOfkD[3]);
			assertEquals(0.17,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=0; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			
			assertEquals(0.7,algorithm.run[0].avarageIncome);
			assertEquals(0.6,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.6,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.52,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.2,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.32,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.48,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			System.out.println(algorithm.run[0].percentOfQChanges);
			assertEquals(0.8,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0.33,algorithm.run[0].percentOfkD[2]);
			assertEquals(0.5,algorithm.run[0].percentOfkD[3]);
			assertEquals(0.17,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0.29,algorithm.run[0].avarageIncome);
			assertEquals(0.24,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.24,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.32,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.24,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.72,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0.04,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.92,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(1,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=2; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0.86,algorithm.run[0].avarageIncome);
			assertEquals(0.8,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.8,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.8,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.8,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.4,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=3; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
									}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0.46,algorithm.run[0].avarageIncome);
			assertEquals(0.4,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.4,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.4,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.4,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.6,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.6,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=4; 
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0.86,algorithm.run[0].avarageIncome);
			assertEquals(0.8,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0.8,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0.8,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.8,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.2,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.4,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		}
		@Test
		public void test12() throws IOException {
			String testName="test1_LA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5; 	// liczba kolumn
			ustawienia.numberOfRows=5;		// liczba wierszy
			ustawienia.numberOfExperiments=1;	// liczba eksperymentów
			
			ustawienia.agentType=typeOfAgent.CAnLA;	// typ agenta
			ustawienia.historyLength=2;				// dlugosc agenta
			ustawienia.epsilon=0;					// wartosc epsilon
			ustawienia.probOfAgentCA=0.5;			// prawdopodobienstwo Agenta CA (opcjonalnie)
			
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','D','D','C','D'},
					{'C','C','C','C','D'},
					{'D','C','C','C','D'},
					{'D','D','C','C','C'},
					{'D','C','C','D','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk= {
					{'L','D','D','L','D'},
					{'L','C','C','C','D'},
					{'L','L','C','C','D'},
					{'D','D','L','L','L'},
					{'D','L','L','L','L'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu={
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			char [][][] stanyPamieci= {
					{
						{'C','D','D','C','D'},
						{'C','C','C','C','D'},
						{'C','C','C','C','D'},
						{'D','D','C','C','C'},
						{'D','C','C','C','C'}},
					{
						{'D','D','D','D','D'},
						{'D','C','C','C','D'},
						{'D','D','C','C','D'},
						{'D','D','D','D','D'},
						{'D','D','D','D','D'}}
					
			};
			/*
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.25,algorithm.run[0].avarageIncome);
			assertEquals(0.2,algorithm.run[0].percentOfCStates);
			assertEquals(0.52,algorithm.run[0].percentOfCACell);
			assertEquals(0.48,algorithm.run[0].percentOfLACell);
			assertEquals(0.38,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.38,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.62,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			*/
			ustawienia.numberOfFrames=0;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.77,algorithm.run[0].avarageIncome);
			assertEquals(0.68,algorithm.run[0].percentOfCStates);
			assertEquals(0.52,algorithm.run[0].percentOfCACell);
			assertEquals(0.48,algorithm.run[0].percentOfLACell);
			assertEquals(0.38,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(1,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.38,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.62,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.8,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(1,algorithm.run[0].percentOfCACell);
			assertEquals(0,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(0,algorithm.run[0].avarageHParameter);
			assertEquals(0,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
		}
		@Test
		public void test13() throws IOException {
			String testName="test2_CAnLA";
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5; 	// liczba kolumn
			ustawienia.numberOfRows=5;		// liczba wierszy
			ustawienia.numberOfExperiments=1;	// liczba eksperymentów
			ustawienia.agentType=typeOfAgent.CAnLA;	// typ agenta
			ustawienia.historyLength=2;				// dlugosc agenta
			ustawienia.probOfAgentCA=0.5;			// prawdopodobienstwo Agenta CA (opcjonalnie)
			
			ustawienia.epsilon=1;					// wartosc epsilon
			ustawienia.isLA1=true;
			ustawienia.isLA3=false;
			
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów= {
					{'C','D','D','C','D'},
					{'C','C','C','C','D'},
					{'D','C','C','C','D'},
					{'D','D','C','C','C'},
					{'D','C','C','D','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk= {
					{'L','D','D','L','D'},
					{'L','C','C','C','D'},
					{'L','L','C','C','D'},
					{'D','D','L','L','L'},
					{'D','L','L','L','L'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu={
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			char [][][] stanyPamieci= {
					{
						{'C','D','D','C','D'},
						{'C','C','C','C','D'},
						{'C','C','C','C','D'},
						{'D','D','C','C','C'},
						{'D','C','C','C','C'}},
					{
						{'D','D','D','D','D'},
						{'D','C','C','C','D'},
						{'D','D','C','C','D'},
						{'D','D','D','D','D'},
						{'D','D','D','D','D'}}
					
			};
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.25,algorithm.run[0].avarageIncome);
			assertEquals(0.2,algorithm.run[0].percentOfCStates);
			assertEquals(0.52,algorithm.run[0].percentOfCACell);
			assertEquals(0.48,algorithm.run[0].percentOfLACell);
			assertEquals(0.38,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.38,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.62,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			//assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=0;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			System.out.println(algorithm.run[0].avarageIncome);
			assertEquals(0.25,algorithm.run[0].avarageIncome);
			assertEquals(0.2,algorithm.run[0].percentOfCStates);
			assertEquals(0.52,algorithm.run[0].percentOfCACell);
			assertEquals(0.48,algorithm.run[0].percentOfLACell);
			assertEquals(0.38,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.38,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.62,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.92,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=1;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.63,algorithm.run[0].avarageIncome);
			assertEquals(0.56,algorithm.run[0].percentOfCStates);
			assertEquals(0.68,algorithm.run[0].percentOfCACell);
			assertEquals(0.32,algorithm.run[0].percentOfLACell);
			assertEquals(0.35,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(1,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.35,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.65,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.8,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=2;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.26,algorithm.run[0].avarageIncome);
			assertEquals(0.2,algorithm.run[0].percentOfCStates);
			assertEquals(0.84,algorithm.run[0].percentOfCACell);
			assertEquals(0.16,algorithm.run[0].percentOfLACell);
			assertEquals(0.24,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0.24,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0.76,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.88,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=3;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0.64,algorithm.run[0].percentOfCACell);
			assertEquals(0.36,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=4;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0.42,algorithm.run[0].avarageIncome);
			assertEquals(0.36,algorithm.run[0].percentOfCStates);
			assertEquals(0.64,algorithm.run[0].percentOfCACell);
			assertEquals(0.36,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(1,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(1,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0.96,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=5;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0,algorithm.run[0].percentOfCACell);
			assertEquals(1,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			
			ustawienia.numberOfFrames=6;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(0,algorithm.run[0].avarageIncome);
			assertEquals(0,algorithm.run[0].percentOfCStates);
			assertEquals(0,algorithm.run[0].percentOfCACell);
			assertEquals(1,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
			
			ustawienia.numberOfFrames=7;
			
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu[i][j]==1)?(true):(false));
					
					algorithm.run[0].historyStates=stanyPamieci;
				}
			
			algorithm.Calculate();
			
			System.out.println(testName+" "+ustawienia.numberOfFrames);
			assertEquals(1,algorithm.run[0].avarageIncome);
			assertEquals(1,algorithm.run[0].percentOfCStates);
			assertEquals(0,algorithm.run[0].percentOfCACell);
			assertEquals(1,algorithm.run[0].percentOfLACell);
			assertEquals(0,algorithm.run[0].percentOfCStatesInCACell);
			assertEquals(1,algorithm.run[0].percentOfCStatesInLACell);
			assertEquals(0,algorithm.run[0].percentOfSharingCell);
			assertEquals(0,algorithm.run[0].percentOfAllCStrategy);
			assertEquals(0,algorithm.run[0].percentOfPcStrategy);
			assertEquals(0,algorithm.run[0].percentOfallDStrategy);
			assertEquals(0,algorithm.run[0].percentOfkDStrategy);
			assertEquals(2,algorithm.run[0].avarageHParameter);
			assertEquals(1,algorithm.run[0].avarageEpsParameter);
			assertEquals(0,algorithm.run[0].avaragePcParameter);
			assertEquals(0,algorithm.run[0].percentOfQChanges);
			assertEquals(0,algorithm.run[0].percentOfkD[0]);
			assertEquals(0,algorithm.run[0].percentOfkD[1]);
			assertEquals(0,algorithm.run[0].percentOfkD[2]);
			assertEquals(0,algorithm.run[0].percentOfkD[3]);
			assertEquals(0,algorithm.run[0].percentOfkD[4]);
			assertEquals(0,algorithm.run[0].percentOfkD[5]);
			assertEquals(0,algorithm.run[0].percentOfkD[6]);
			assertEquals(0,algorithm.run[0].percentOfkD[7]);
		}
		
		@Test
		public void test14() throws IOException {
			
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=3;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów0= {
					{'C','C','C','C','C'},
					{'C','C','C','C','C'},
					{'C','C','C','C','C'},
					{'C','C','C','C','C'},
					{'C','C','C','C','C'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk0= {
					{'C','C','C','C','C'},
					{'C','C','C','C','C'},
					{'C','C','C','C','C'},
					{'C','C','C','C','C'},
					{'C','C','C','C','C'}};
			
			int [][] kTolerancji0= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			char [][] tablicaStanów1= {
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk1= {
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'},
					{'D','D','D','D','D'}};
			
			int [][] kTolerancji1= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			char [][] tablicaStanów2= {
					{'C','D','D','D','D'},
					{'D','C','D','D','D'},
					{'C','D','C','D','D'},
					{'D','C','D','C','D'},
					{'D','D','D','D','D'}};
			
			// E-Empty C- All-C, D- All-D, K- k tolerancji P- Probability of C
			char [][] tablicaTaktyk2= {
					{'C','D','D','D','D'},
					{'D','C','D','D','D'},
					{'C','D','C','D','D'},
					{'D','C','D','C','D'},
					{'D','D','D','D','D'}};
			
			int [][] kTolerancji2= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			/*
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
				}
			
			
			algorithm.Calculate();
			
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.41,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.44,algorithm.standardDeviation.avarageIncome);
			assertEquals(1,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.41,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.59,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0,algorithm.standardDeviation.avarageSharing);
			assertEquals(0,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.4263,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.418,algorithm.standardDeviation.deviationIncome);
			assertEquals(0,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.4263,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.4263,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0,algorithm.standardDeviation.deviationPercentK);
			System.out.println(algorithm.standardDeviation.deviationSharing);
			assertEquals(0,algorithm.standardDeviation.deviationSharing);
			assertEquals(0,algorithm.standardDeviation.deviationQChanges);
			*/
			
			ustawienia.numberOfFrames=0;		
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
				}
			
			
			algorithm.Calculate();
			
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.41,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.44,algorithm.standardDeviation.avarageIncome);
			assertEquals(1,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.41,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.59,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0,algorithm.standardDeviation.avarageSharing);
			assertEquals(0.28,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.4263,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.418,algorithm.standardDeviation.deviationIncome);
			assertEquals(0,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.4263,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.4263,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0,algorithm.standardDeviation.deviationPercentK);
			assertEquals(0,algorithm.standardDeviation.deviationSharing);
			assertEquals(0.396,algorithm.standardDeviation.deviationQChanges);
			
			
			ustawienia.numberOfFrames=1;		
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
				}
			
			
			algorithm.Calculate();
			
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.36,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.37,algorithm.standardDeviation.avarageIncome);
			assertEquals(1,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.36,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.64,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0,algorithm.standardDeviation.avarageSharing);
			assertEquals(0.28,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.4537,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.4477,algorithm.standardDeviation.deviationIncome);
			assertEquals(0,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.4537,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.4537,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0,algorithm.standardDeviation.deviationPercentK);
			assertEquals(0,algorithm.standardDeviation.deviationSharing);
			assertEquals(0.396,algorithm.standardDeviation.deviationQChanges);
			
			ustawienia.numberOfFrames=2;		
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
				}
			
			
			algorithm.Calculate();
			
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.33,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.33,algorithm.standardDeviation.avarageIncome);
			assertEquals(1,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.33,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.67,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0,algorithm.standardDeviation.avarageSharing);
			assertEquals(0,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.4714,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.4714,algorithm.standardDeviation.deviationIncome);
			assertEquals(0,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.4714,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.4714,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0,algorithm.standardDeviation.deviationPercentK);
			assertEquals(0,algorithm.standardDeviation.deviationSharing);
			assertEquals(0,algorithm.standardDeviation.deviationQChanges);
		}
		
		@Test
		public void test15() throws IOException {
			
			Settings ustawienia=new Settings();
			ustawienia.numberOfColumns=5;
			ustawienia.numberOfRows=5;
			ustawienia.numberOfExperiments=4;
			ustawienia.numberOfFrames=0; //pierwszy krok- pierwsza klatka
			ustawienia.historyLength=2;
			
			ustawienia.isLA1=true;
			ustawienia.isLA3=false;
			
			Algorithm algorithm= new Algorithm(ustawienia);
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów0= {
					{'C','C','C','D','D'},
					{'D','C','D','C','C'},
					{'D','C','C','D','C'},
					{'C','C','C','C','D'},
					{'C','C','C','C','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk0= {
					{'C','K','K','K','K'},
					{'D','K','K','K','K'},
					{'D','K','K','D','K'},
					{'C','C','C','K','K'},
					{'C','C','C','K','K'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji0= {
					{0,2,3,2,3},
					{0,2,3,2,3},
					{0,2,0,0,2},
					{0,0,0,2,2},
					{0,0,0,3,3}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu0={
					{1,1,1,1,0},
					{0,0,1,1,0},
					{0,0,1,0,1},
					{0,1,0,1,1},
					{0,0,1,0,0}};
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów1= {
					{'C','D','D','C','D'},
					{'C','C','C','C','D'},
					{'D','C','C','C','D'},
					{'D','D','C','C','C'},
					{'D','C','C','D','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk1= {
					{'K','D','D','K','D'},
					{'K','C','C','C','D'},
					{'K','K','C','C','D'},
					{'D','D','K','K','K'},
					{'D','K','K','K','K'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji1= {
					{2,0,0,4,0},
					{3,0,0,0,0},
					{2,2,0,0,0},
					{0,0,3,3,3},
					{0,4,3,3,2}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu1={
					{0,0,0,0,1},
					{0,0,1,0,1},
					{0,1,1,1,0},
					{0,1,1,1,0},
					{0,1,1,1,1}};
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów2= {
					{'C','D','D','C','D'},
					{'C','C','C','C','D'},
					{'D','C','C','C','D'},
					{'D','D','C','C','C'},
					{'D','C','C','D','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk2= {
					{'L','D','D','L','D'},
					{'L','C','C','C','D'},
					{'L','L','C','C','D'},
					{'D','D','L','L','L'},
					{'D','L','L','L','L'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji2= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu2={
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			char [][][] stanyPamieci2= {
					{
						{'C','D','D','C','D'},
						{'C','C','C','C','D'},
						{'C','C','C','C','D'},
						{'D','D','C','C','C'},
						{'D','C','C','C','C'}},
					{
						{'D','D','D','D','D'},
						{'D','C','C','C','D'},
						{'D','D','C','C','D'},
						{'D','D','D','D','D'},
						{'D','D','D','D','D'}}
					
			};
			
			// E-Empty C- cooperate , D- defects
			char [][] tablicaStanów3= {
					{'C','D','D','C','D'},
					{'C','C','C','C','D'},
					{'D','C','C','C','D'},
					{'D','D','C','C','C'},
					{'D','C','C','D','C'}};
			
			// E-Empty, L- Komórka LA, C- All-C, D- All-D, K- k tolerancji P- strategia Pc 
			char [][] tablicaTaktyk3= {
					{'L','D','D','L','D'},
					{'L','C','C','C','D'},
					{'L','L','C','C','D'},
					{'D','D','L','L','L'},
					{'D','L','L','L','L'}};
			
			// wartosci tolerancji dla strategii K-D
			int [][] kTolerancji3= {
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			
			// 0-No share 1-Share
			int [][] dzielenieDochodu3={
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0},
					{0,0,0,0,0}};
			char [][][] stanyPamieci3= {
					{
						{'C','D','D','C','D'},
						{'C','C','C','C','D'},
						{'C','C','C','C','D'},
						{'D','D','C','C','C'},
						{'D','C','C','C','C'}},
					{
						{'D','D','D','D','D'},
						{'D','C','C','C','D'},
						{'D','D','C','C','D'},
						{'D','D','D','D','D'},
						{'D','D','D','D','D'}}
					
			};
			/*
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu0[i][j]==1)?(true):(false));
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					algorithm.run[1].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu1[i][j]==1)?(true):(false));
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
					algorithm.run[2].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu2[i][j]==1)?(true):(false));
					algorithm.run[2].historyStates=stanyPamieci2;
					
					algorithm.run[3].temporary[i+1][j+1].state=tablicaStanów3[i][j];
					algorithm.run[3].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk3[i][j];
					algorithm.run[3].temporary[i+1][j+1].strategy.kMax=kTolerancji3[i][j];
					algorithm.run[3].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu3[i][j]==1)?(true):(false));
					algorithm.run[3].temporary[i+1][j+1].epsilon=1;
					algorithm.run[3].historyStates=stanyPamieci3;
										
				}
			
			
			algorithm.Calculate();
			System.out.println(algorithm.run[3].percentOfCStates);
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.43,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.5,algorithm.standardDeviation.avarageIncome);
			assertEquals(0.76,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0.24,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.31,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.42,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0.27,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0.25,algorithm.standardDeviation.avarageSharing);
			assertEquals(0,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.2339,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.2525,algorithm.standardDeviation.deviationIncome);
			assertEquals(0.24,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0.24,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.0755,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.2121,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0.2733,algorithm.standardDeviation.deviationPercentK);
			assertEquals(0.2504,algorithm.standardDeviation.deviationSharing);
			assertEquals(0,algorithm.standardDeviation.deviationQChanges);
			
			*/
			ustawienia.numberOfFrames=0;		
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu0[i][j]==1)?(true):(false));
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					algorithm.run[1].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu1[i][j]==1)?(true):(false));
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
					algorithm.run[2].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu2[i][j]==1)?(true):(false));
					algorithm.run[2].historyStates=stanyPamieci2;
					
					algorithm.run[3].temporary[i+1][j+1].state=tablicaStanów3[i][j];
					algorithm.run[3].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk3[i][j];
					algorithm.run[3].temporary[i+1][j+1].strategy.kMax=kTolerancji3[i][j];
					algorithm.run[3].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu3[i][j]==1)?(true):(false));
					algorithm.run[3].temporary[i+1][j+1].epsilon=1;
					algorithm.run[3].historyStates=stanyPamieci3;
										
				}
			
			algorithm.Calculate();
			
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.55,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.63,algorithm.standardDeviation.avarageIncome);
			assertEquals(0.76,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0.24,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.31,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.42,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0.27,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0.25,algorithm.standardDeviation.avarageSharing);
			assertEquals(0.83,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.2066,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.2224,algorithm.standardDeviation.deviationIncome);
			assertEquals(0.24,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0.24,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.0755,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.2121,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0.2733,algorithm.standardDeviation.deviationPercentK);
			assertEquals(0.2504,algorithm.standardDeviation.deviationSharing);
			assertEquals(0.052,algorithm.standardDeviation.deviationQChanges);
			
			
			ustawienia.numberOfFrames=1;		
			algorithm= new Algorithm(ustawienia);
			
			for (int i=0 ; i < ustawienia.numberOfRows ; i++)
				for (int j=0; j<ustawienia.numberOfColumns; j++)
				{
					algorithm.run[0].temporary[i+1][j+1].state=tablicaStanów0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk0[i][j];
					algorithm.run[0].temporary[i+1][j+1].strategy.kMax=kTolerancji0[i][j];
					algorithm.run[0].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu0[i][j]==1)?(true):(false));
					
					algorithm.run[1].temporary[i+1][j+1].state=tablicaStanów1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk1[i][j];
					algorithm.run[1].temporary[i+1][j+1].strategy.kMax=kTolerancji1[i][j];
					algorithm.run[1].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu1[i][j]==1)?(true):(false));
					
					algorithm.run[2].temporary[i+1][j+1].state=tablicaStanów2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk2[i][j];
					algorithm.run[2].temporary[i+1][j+1].strategy.kMax=kTolerancji2[i][j];
					algorithm.run[2].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu2[i][j]==1)?(true):(false));
					algorithm.run[2].historyStates=stanyPamieci2;
					
					algorithm.run[3].temporary[i+1][j+1].state=tablicaStanów3[i][j];
					algorithm.run[3].temporary[i+1][j+1].strategy.buffor=tablicaTaktyk3[i][j];
					algorithm.run[3].temporary[i+1][j+1].strategy.kMax=kTolerancji3[i][j];
					algorithm.run[3].temporary[i+1][j+1].sharingPayout=((dzielenieDochodu3[i][j]==1)?(true):(false));
					algorithm.run[3].temporary[i+1][j+1].epsilon=1;
					algorithm.run[3].historyStates=stanyPamieci3;
										
				}
			
			
			algorithm.Calculate();
			
			System.out.println(algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.23,algorithm.standardDeviation.avarageStatesC);
			assertEquals(0.27,algorithm.standardDeviation.avarageIncome);
			assertEquals(0.92,algorithm.standardDeviation.avaragePercentCA);
			assertEquals(0.08,algorithm.standardDeviation.avaragePercentLA);
			assertEquals(0.18,algorithm.standardDeviation.avaragePercentC);
			assertEquals(0,algorithm.standardDeviation.avaragePercentP);
			assertEquals(0.73,algorithm.standardDeviation.avaragePercentD);
			assertEquals(0.09,algorithm.standardDeviation.avaragePercentK);
			assertEquals(0.08,algorithm.standardDeviation.avarageSharing);
			assertEquals(0.63,algorithm.standardDeviation.avarageQChanges);
			
			assertEquals(0.2086,algorithm.standardDeviation.deviationStateC);
			assertEquals(0.2318,algorithm.standardDeviation.deviationIncome);
			assertEquals(0.1386,algorithm.standardDeviation.deviationPercentCA);
			assertEquals(0.1386,algorithm.standardDeviation.deviationPercentLA);
			assertEquals(0.1309,algorithm.standardDeviation.deviationPercentC);
			assertEquals(0,algorithm.standardDeviation.deviationPercentP);
			assertEquals(0.1645,algorithm.standardDeviation.deviationPercentD);
			assertEquals(0.1338,algorithm.standardDeviation.deviationPercentK);
			assertEquals(0.1386,algorithm.standardDeviation.deviationSharing);
			assertEquals(0.3670,algorithm.standardDeviation.deviationQChanges);
			
		}
		
}
