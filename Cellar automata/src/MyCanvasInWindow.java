
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
	private JSpinner spinnerNumerEksperymentu;

	
	public void updateLabels()
	{
	
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
	/**
	 * Create the application.
	 */
	public MyCanvasInWindow(Paint[][] paint,Statistics[] stats, int period) {
		this.stats=stats;
		this.period=period;
		this.paint=paint;
		setVisible(true);
		setSize(430,529);
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
				
				int choosenFile=currentIndexExperiment;
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnZapiszStatystyki)
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
							.addComponent(btnZapiszObraz)))
					.addGap(52))
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
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZapiszObraz)
						.addComponent(btnZapiszStatystyki))
					.addContainerGap())
		);
		
		currentIndexExperiment=0;
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Strategie", null, panel_2, null);
		
		label_2 = new JLabel(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStrategies,300,300)));
		panel_2.add(label_2);
		JPanel panel = new JPanel();
		tabbedPane.addTab("Komorki CA/LA", null, panel, null);
		label = new JLabel((new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageCALA,300,300))));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Stany Komorek", null, panel_1, null);
		
		label_1 = new JLabel(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imageStates,300,300)));
		panel_1.add(label_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Strategie K-d", null, panel_3, null);
		
		label_3 = new JLabel(new ImageIcon(new Paint().getScaledImage(paint[currentIndexExperiment][currentIndexLabel].imagekD,300,300)));
		panel_3.add(label_3);

		getContentPane().setLayout(groupLayout);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}