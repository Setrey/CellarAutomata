
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class MyCanvasInWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnRozpocznijAnimacje;
	private JLabel label;
	private int currentIndexLabel=0;
	private int currentIndexExperiment=0;
	private boolean isCellSelected=false;	// czy komórka jest zaznaczona
	private boolean tryToSelectCell=true;	// czy zaznaczaæ komórki
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JSpinner spinnerNumerKrokow;
	private JButton btnZatrzymajAnimacje;
	private JButton btnZapiszObraz;
	public boolean isItEnd=false;
	public OdchylenieStandardowe odchyl;
	private int TIMER_DELAY = 100;
    public Timer timer;
    private int NumerObrazu=0;

	Paint paint[][];
	Statistics stats[];
	int period;
	Run[] run;
	Settings settings;
	
	private JSpinner spinnerNumerEksperymentu;

	
	public void updateLabels()
	{
		//TODO zbudowanie ramki na komórce któr¹ chcemy podœwietliæ. 
		
		label.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageCALA,300,300)));
		label_1.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStates,300,300)));
		label_2.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStrategies,300,300)));
		label_3.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imagekD,300,300)));
		spinnerNumerKrokow.setValue((int) currentIndexLabel) ;
		spinnerNumerEksperymentu.setValue((int)currentIndexExperiment);
		currentIndexLabel++;
		
		
		if (currentIndexLabel==paint[0].length)
		{
			currentIndexLabel=0;
			currentIndexExperiment++;
			if (currentIndexExperiment==paint.length)
			{
				currentIndexExperiment--;
				isItEnd=true;
			}
		}
	}
	public MyCanvasInWindow(Paint[][] paint,Statistics[] stats, int period, Run[] run, Settings settings) {
		
		this.stats=stats;
		this.period=period;
		this.paint=paint;
		this.settings=settings;
		
		setVisible(true);
		setSize(390,650);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		btnZapiszObraz = new JButton("zapisz obrazy");
		btnZapiszObraz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("Image\\");
				try{
				    if(f.mkdir()) { 
				        System.out.println("Directory Created");
				    } else {
				        System.out.println("Directory is not created");
				    }
				} catch(Exception e){
				    e.printStackTrace();
				} 
				try {
					paint[currentIndexExperiment][currentIndexLabel].PrintToFile(new File("Image\\komorki_CA i LA"+NumerObrazu+".png"), new File("Image\\stany_komorek"+NumerObrazu+".png")
							, new File("Image\\strategie"+NumerObrazu+".png"), new File("Image\\output_strategie_kD"+NumerObrazu+".png"));
					NumerObrazu++;
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		timer = new Timer(TIMER_DELAY, e -> {
		    if (!isItEnd) {
		        updateLabels();
		        timer.restart(); //Do this check again after 1000ms
		    }
		    else 
		    	timer.stop();
		});
		btnRozpocznijAnimacje = new JButton("rozpocznij animacje");
		btnRozpocznijAnimacje.addActionListener(e -> timer.start());
		
		JLabel lblNumerEksperymentu = new JLabel("numer Eksperymentu:");
		
		JLabel lblNumerKroku = new JLabel("numer kroku:");
		
		spinnerNumerKrokow = new JSpinner();
		spinnerNumerKrokow.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				if ((int)spinnerNumerKrokow.getValue()<paint[0].length)
					currentIndexLabel=(int)spinnerNumerKrokow.getValue();
				else
					spinnerNumerKrokow.setValue(currentIndexLabel);
				
				isItEnd=false;
				
				label.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageCALA,300,300)));
				label_1.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStates,300,300)));
				label_2.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStrategies,300,300)));
				label_3.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imagekD,300,300)));
			}
		});
		spinnerNumerKrokow.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		
		spinnerNumerEksperymentu = new JSpinner();
		spinnerNumerEksperymentu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				if ((int)spinnerNumerEksperymentu.getValue()<paint.length)
					currentIndexExperiment=(int)spinnerNumerEksperymentu.getValue();
				else
					spinnerNumerEksperymentu.setValue(currentIndexExperiment);
				isItEnd=false;
				
				
				label.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageCALA,300,300)));
				label_1.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStates,300,300)));
				label_2.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStrategies,300,300)));
				label_3.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imagekD,300,300)));
			}
		});
		spinnerNumerEksperymentu.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		
		btnZatrzymajAnimacje = new JButton("zatrzymaj animacje");
		btnZatrzymajAnimacje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isItEnd=true;
			}
		});
		
		odchyl=new OdchylenieStandardowe();
		JButton btnZapiszStatystyki = new JButton("Zapisz statystyki");
		btnZapiszStatystyki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if(paint.length>1)
					{
						odchyl.standardDeviatation(stats, period);
						try(PrintWriter p = new PrintWriter("odchylenie_standardowe.txt")) {
							odchyl.writeToFile(p);
						}catch (FileNotFoundException e)
						{
						System.out.println("Error");
						}
					}
				else 
					try(PrintWriter p = new PrintWriter("odchylenie.txt")) {
						odchyl.writeToFile(p," ");
					}catch (FileNotFoundException e)
					{
					System.out.println("Error");
					}
				
				//int choosenFile=currentIndexExperiment;
				try(PrintWriter p = new PrintWriter("wyniki.txt")) {
					for (int i =0; i < stats.length ; i ++)
						stats[i].writeToFile(p, i);
					//stats[choosenFile].writeToFile(p);
				}catch (FileNotFoundException e)
				{
				System.out.println("Error");
				}
				
			}
		});
		
		JTextArea textAreaInformation = new JTextArea();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textAreaInformation, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(tabbedPane)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNumerKroku)
									.addComponent(lblNumerEksperymentu))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spinnerNumerEksperymentu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnRozpocznijAnimacje))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spinnerNumerKrokow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnZatrzymajAnimacje, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnZapiszStatystyki)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnZapiszObraz))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumerEksperymentu)
						.addComponent(spinnerNumerEksperymentu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRozpocznijAnimacje))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNumerKroku)
							.addComponent(spinnerNumerKrokow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnZatrzymajAnimacje))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnZapiszObraz)
						.addComponent(btnZapiszStatystyki))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textAreaInformation, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		currentIndexExperiment=0;
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Strategie", null, panel_2, null);
		
		label_2 = new JLabel(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStrategies,300,300)));
		/*
		 * LISTENER DO LABELA
		 * 
		
		 * 
		 * 
		 * 
		 */
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				isCellSelected=true;
				
				textAreaInformation.setText(arg0.getPoint().x + " " + arg0.getPoint().y + "\n"
				+"Komorka [" + getIndexX(arg0.getPoint().x )+ "]["+ getIndexY(arg0.getPoint().y )+"]");
				label.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageCALA,300,300,getSizeOfRectangleI(getIndexX(arg0.getPoint().x )),
						getSizeOfRectangleJ(getIndexY(arg0.getPoint().y )),
						getSizeOfSquareSide(),
						getSizeOfSquareSide())));
				label_2.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStrategies,300,300,getSizeOfRectangleI(getIndexX(arg0.getPoint().x )),
						getSizeOfRectangleJ(getIndexY(arg0.getPoint().y )),
						getSizeOfSquareSide(),
						getSizeOfSquareSide())));
				label_1.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStates,300,300,getSizeOfRectangleI(getIndexX(arg0.getPoint().x )),
						getSizeOfRectangleJ(getIndexY(arg0.getPoint().y )),
						getSizeOfSquareSide(),
						getSizeOfSquareSide())));
				label_3.setIcon(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imagekD,300,300,getSizeOfRectangleI(getIndexX(arg0.getPoint().x )),
						getSizeOfRectangleJ(getIndexY(arg0.getPoint().y )),
						getSizeOfSquareSide(),
						getSizeOfSquareSide())));
				
			}
		});
		panel_2.add(label_2);
		JPanel panel = new JPanel();
		tabbedPane.addTab("Komorki CA/LA", null, panel, null);
		label = new JLabel((new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageCALA,300,300))));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				spinnerNumerKrokow.getValue();
				spinnerNumerEksperymentu.getValue();
				
				textAreaInformation.setText(arg0.getPoint().x + " " + arg0.getPoint().y + "\n"
						+"Komorka [" + getIndexX(arg0.getPoint().x )+ "]["+ getIndexY(arg0.getPoint().y )+"]\n"
						+"Stan: ");
			}
		});
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Stany Komorek", null, panel_1, null);
		
		label_1 = new JLabel(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStates,300,300)));
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textAreaInformation.setText(arg0.getPoint().x + " " + arg0.getPoint().y + "\n"
						+"Komorka [" + getIndexX(arg0.getPoint().x )+ "]["+ getIndexY(arg0.getPoint().y )+"]");
			}
		});
		panel_1.add(label_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Strategie K-d", null, panel_3, null);
		
		label_3 = new JLabel(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imagekD,300,300)));
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textAreaInformation.setText(arg0.getPoint().x + " " + arg0.getPoint().y + "\n"
						+"Komorka [" + getIndexX(arg0.getPoint().x )+ "]["+ getIndexY(arg0.getPoint().y )+"]");
			}
		});
		panel_3.add(label_3);

		getContentPane().setLayout(groupLayout);
		initialize();
	}
	
	/*
	 * label_2.getMaximumSize().height - Max wysokosc (Y)
	 * label_2.getMaximumSize().width  - Max szerokosc (X)
	 */
	
	private int getIndexX(int x)
	{
		int eachCellWidth=label_2.getMaximumSize().width/settings.numberOfColumns;
		
		return (int)x/eachCellWidth;
	}
	
	private int getIndexY(int y)
	{
		int eachCellHeight=label_2.getMaximumSize().height/settings.numberOfRows;
		
		return (int)y/eachCellHeight;
	}
	
	private int getSizeOfRectangleI(int i)
	{
		int eachCellWidth=label_2.getMaximumSize().width/settings.numberOfColumns;
		return (int)i*eachCellWidth;
	}
	private int getSizeOfRectangleJ(int j)
	{
		int eachCellHeight=label_2.getMaximumSize().height/settings.numberOfRows;
		return (int)j*eachCellHeight;
	}
	private int getSizeOfSquareSide()
	{
		return (int) label_2.getMaximumSize().height/settings.numberOfRows;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}