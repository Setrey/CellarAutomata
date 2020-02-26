import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class Window {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnKomrkiLaiCA;
	private JRadioButton rdbtnTylkoKomrkiLa;
	private JRadioButton rdbtnTylkoKomrkiCa;
	private JSpinner SpinnerpTypAgCA;
	private JPanel LApanel;
	private JPanel CAPanel;
	private JSpinner spinnerRSasiedztwa;
	private JSpinner spinnerLiczbaKolumn;
	private JSpinner spinnerLiczbaWireszy;

	
	private JSpinner spinnerZiarno;
	private JSpinner spinnerStrategPc;
	private JSpinner spinnerStrategAllC;
	private JSpinner SpinnerStrategAllD;
	private JSpinner spinnerStrategkD;
	private JSpinner spinnerQ;
	private JSpinner spinnerp_niezam;
	private JSpinner spinnerp_init_C;
	private JSpinner spinnergot_wspol_doch;
	private JSpinner spinnerIloscExperymentow;
	private JSpinner spinnerR;
	private JSpinner spinnerS;
	private JSpinner spinnerT;
	private JSpinner spinnerP;
	private JSpinner spinnerHistoria;
	private JSpinner spinnerEpsilon;
	private JSpinner spinnerMutationHistoria;
	private JSpinner spinnerMutationc2;
	private JSpinner SpinnerMutationEpsilon;
	private JSpinner spinnerMutationc3;
	private JSpinner spinnerKMAxx;
	private JSpinner spinnerPc;
	private JSpinner spinnerMutStrateg;
	private JSpinner spinnerMutPc;
	private JSpinner spinnerMutC1;
	private JButton btnRozpocznijSymulacje;
	private JSpinner spinnerIloscKrokow;
	public Algorithm algo ;
	private JRadioButton rdbtnZiarno;
	private JRadioButton rdbtnGotWspDoch;
	private JRadioButton rbtnkConst;
	private JRadioButton rdbtnQ;
	private JRadioButton rdbtnDebug;
	private JSpinner spinnerDeltaPC;
	private JRadioButton rdbtnLa1;
	private JRadioButton rdbtnLa2;
	private JRadioButton rdbtnLa3;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tablica p\u0142atno\u015Bci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Parametry symulacji", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Rodzaj kom\u00F3rek w symulacji", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		CAPanel = new JPanel();
		CAPanel.setBorder(new TitledBorder(null, "Kom\u00F3rki CA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		LApanel = new JPanel();
		LApanel.setBorder(new TitledBorder(null, "Kom\u00F3rki LA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnRozpocznijSymulacje = new JButton("Symulacja");
		btnRozpocznijSymulacje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Settings settings= new Settings();
					
				if(rdbtnKomrkiLaiCA.isSelected())
					settings.agentType=typeOfAgent.CAnLA;
				else if (rdbtnTylkoKomrkiLa.isSelected())
					settings.agentType=typeOfAgent.LA;
				else if (rdbtnTylkoKomrkiCa.isSelected())
					settings.agentType=typeOfAgent.CA;
				
				if (!rdbtnGotWspDoch.isSelected())
					settings.probOfUnhabitedCell=0;
				else
					settings.probOfUnhabitedCell=(double)spinnergot_wspol_doch.getValue();
				
				
				if(rbtnkConst.isSelected())
					settings.isKConst=true;
				else
					settings.isKConst=false;
				
				
				if (!rdbtnZiarno.isSelected())
					settings.seed=new Random().nextInt();
				else 
					settings.seed=(int)spinnerZiarno.getValue();
				try 
				{
					settings.numberOfRows=(int)spinnerLiczbaWireszy.getValue();
					settings.numberOfColumns=(int) spinnerLiczbaKolumn.getValue();
					settings.radiusOfNeighbor=(int)spinnerRSasiedztwa.getValue();
					settings.qChanges=(int)spinnerQ.getValue();
					settings.numberOfFrames=(int)spinnerIloscKrokow.getValue();
					settings.probOfUnhabitedCell=(double)spinnerp_niezam.getValue();
					settings.probOfInitCState=(double)spinnerp_init_C.getValue();
					settings.numberOfExperiments=(int)spinnerIloscExperymentow.getValue();
					settings.pDSettings= 
							new PrisonerDilema((double)spinnerR.getValue(),
									(double)spinnerS.getValue(), 
									(double)spinnerT.getValue(), 
									(double)spinnerP.getValue());
					settings.probOfAgentCA=(double)SpinnerpTypAgCA.getValue();
					settings.historyLength=(int)spinnerHistoria.getValue();
					settings.epsilon=(double)spinnerEpsilon.getValue();
					settings.mutation= 
							new Mutation((double)spinnerMutStrateg.getValue(), 
									(double)spinnerMutPc.getValue(), 
									(double)spinnerMutationHistoria.getValue(), 
									(double)SpinnerMutationEpsilon.getValue(), 
									(double)spinnerMutC1.getValue(), 
									(int)spinnerMutationc2.getValue(), 
									(double)spinnerMutationc3.getValue());
					settings.probOfPcStrategy=(double)spinnerStrategPc.getValue();
					settings.probOfAllCStrategy=(double)spinnerStrategAllC.getValue();
					settings.probOfAllDStrategy=(double)SpinnerStrategAllD.getValue();
					settings.probOfKDStrategy=(double)spinnerStrategkD.getValue();
					settings.kMax=(int)spinnerKMAxx.getValue();
					settings.valueOfPc=(double)spinnerPc.getValue();
					settings.isQselected=(boolean)rdbtnQ.isSelected();
					settings.isDebugSelected=(boolean) rdbtnDebug.isSelected();
					settings.deltaPc=(double)spinnerDeltaPC.getValue();
					settings.isLA1=(boolean) rdbtnLa1.isSelected();
					settings.isLA2=(boolean) rdbtnLa2.isSelected();
					settings.isLA3=(boolean) rdbtnLa3.isSelected();
					
					algo= new Algorithm(settings);
				
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
				
			}
		});
		
		rdbtnDebug = new JRadioButton("debug");
		rdbtnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 266, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnRozpocznijSymulacje, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnDebug, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
								.addComponent(LApanel, 0, 0, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CAPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(CAPanel, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(LApanel, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRozpocznijSymulacje, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnDebug)))))
					.addGap(42))
		);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 67, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 37, 41, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("C");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblD = new JLabel("D");
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.insets = new Insets(0, 0, 5, 0);
		gbc_lblD.gridx = 4;
		gbc_lblD.gridy = 0;
		panel.add(lblD, gbc_lblD);
		
		JLabel lblC = new JLabel("C");
		GridBagConstraints gbc_lblC = new GridBagConstraints();
		gbc_lblC.insets = new Insets(0, 0, 5, 5);
		gbc_lblC.gridx = 0;
		gbc_lblC.gridy = 1;
		panel.add(lblC, gbc_lblC);
		
		JLabel lblR = new JLabel("R():");
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 1;
		gbc_lblR.gridy = 1;
		panel.add(lblR, gbc_lblR);
		
		spinnerR = new JSpinner();
		GridBagConstraints gbc_spinnerR = new GridBagConstraints();
		gbc_spinnerR.anchor = GridBagConstraints.WEST;
		gbc_spinnerR.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerR.gridx = 2;
		gbc_spinnerR.gridy = 1;
		panel.add(spinnerR, gbc_spinnerR);
		spinnerR.setModel(new SpinnerNumberModel(1.0,-99.99,99.99,0.01));
		
		JLabel lblS = new JLabel("S():");
		GridBagConstraints gbc_lblS = new GridBagConstraints();
		gbc_lblS.insets = new Insets(0, 0, 5, 5);
		gbc_lblS.gridx = 3;
		gbc_lblS.gridy = 1;
		panel.add(lblS, gbc_lblS);
		
		spinnerS = new JSpinner();
		spinnerS.setModel(new SpinnerNumberModel(0,-99.99,99.99,0.01));
		GridBagConstraints gbc_spinnerS = new GridBagConstraints();
		gbc_spinnerS.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerS.gridx = 4;
		gbc_spinnerS.gridy = 1;
		panel.add(spinnerS, gbc_spinnerS);
		
		JLabel lblD_1 = new JLabel("D");
		GridBagConstraints gbc_lblD_1 = new GridBagConstraints();
		gbc_lblD_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblD_1.gridx = 0;
		gbc_lblD_1.gridy = 2;
		panel.add(lblD_1, gbc_lblD_1);
		
		JLabel lblT = new JLabel("T(b):");
		GridBagConstraints gbc_lblT = new GridBagConstraints();
		gbc_lblT.insets = new Insets(0, 0, 0, 5);
		gbc_lblT.gridx = 1;
		gbc_lblT.gridy = 2;
		panel.add(lblT, gbc_lblT);
		
		spinnerT = new JSpinner();
		GridBagConstraints gbc_spinnerT = new GridBagConstraints();
		gbc_spinnerT.anchor = GridBagConstraints.WEST;
		gbc_spinnerT.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerT.gridx = 2;
		gbc_spinnerT.gridy = 2;
		panel.add(spinnerT, gbc_spinnerT);
		spinnerT.setModel(new SpinnerNumberModel(1.4,-99.99,99.99,0.01));
		
		JLabel lblNewLabel = new JLabel("P():");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		spinnerP = new JSpinner();
		spinnerP.setModel(new SpinnerNumberModel(0,-99.99,99.99,0.01));
		GridBagConstraints gbc_spinnerP = new GridBagConstraints();
		gbc_spinnerP.gridx = 4;
		gbc_spinnerP.gridy = 2;
		panel.add(spinnerP, gbc_spinnerP);
		
		spinnerHistoria = new JSpinner();
		spinnerHistoria.setEnabled(false);
		spinnerHistoria.setModel(new SpinnerNumberModel(new Integer(8), new Integer(1), null, new Integer(1)));
		
		JLabel lblHistoria = new JLabel("pamiec");
		
		spinnerEpsilon = new JSpinner();
		spinnerEpsilon.setEnabled(false);
		spinnerEpsilon.setModel(new SpinnerNumberModel(0.001,0,1,0.001));
		
		JLabel lblEpsilon = new JLabel("epsilon:");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "mutacja", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_LApanel = new GroupLayout(LApanel);
		gl_LApanel.setHorizontalGroup(
			gl_LApanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_LApanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LApanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
						.addGroup(gl_LApanel.createSequentialGroup()
							.addComponent(lblHistoria)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerHistoria, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_LApanel.createSequentialGroup()
							.addComponent(lblEpsilon)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerEpsilon, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_LApanel.setVerticalGroup(
			gl_LApanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LApanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LApanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerHistoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHistoria))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_LApanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerEpsilon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEpsilon))
					.addGap(18)
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		spinnerMutationHistoria = new JSpinner();
		spinnerMutationHistoria.setEnabled(false);
		spinnerMutationHistoria.setModel(new SpinnerNumberModel(0, 0, 1, 0.01));
		
		JLabel lblHistoria_1 = new JLabel("historia:");
		
		spinnerMutationc2 = new JSpinner();
		spinnerMutationc2.setEnabled(false);
		spinnerMutationc2.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		JLabel lblC_2 = new JLabel("c2:");
		
		SpinnerMutationEpsilon = new JSpinner();
		SpinnerMutationEpsilon.setEnabled(false);
		SpinnerMutationEpsilon.setModel(new SpinnerNumberModel(0, 0, 1, 0.01));
		
		spinnerMutationc3 = new JSpinner();
		spinnerMutationc3.setEnabled(false);
		spinnerMutationc3.setModel(new SpinnerNumberModel(0, 0, 1, 0.01));
		
		JLabel lblEpsilon_1 = new JLabel("epsilon:");
		
		JLabel lblC_3 = new JLabel("c3:");
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHistoria_1)
						.addComponent(lblC_2)
						.addComponent(lblEpsilon_1)
						.addComponent(lblC_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING, false)
						.addComponent(spinnerMutationc3, Alignment.TRAILING)
						.addComponent(SpinnerMutationEpsilon, Alignment.TRAILING)
						.addComponent(spinnerMutationc2, Alignment.TRAILING)
						.addComponent(spinnerMutationHistoria, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerMutationHistoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHistoria_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerMutationc2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblC_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(SpinnerMutationEpsilon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEpsilon_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerMutationc3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblC_3))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		LApanel.setLayout(gl_LApanel);
		
		spinnerStrategPc = new JSpinner();
		spinnerStrategPc.setModel(new SpinnerNumberModel(0.25, 0, 1, 0.01));
		spinnerStrategPc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double suma= (double)spinnerStrategAllC.getValue()+(double)SpinnerStrategAllD.getValue()+(double)spinnerStrategkD.getValue();
				if((suma+(double)spinnerStrategPc.getValue())>1)
				{
					spinnerStrategPc.setValue((double)1-suma);
				}
			}
		});
		
		JLabel label = new JLabel("strategia Pc:");
		
		spinnerStrategAllC = new JSpinner();
		spinnerStrategAllC.setModel(new SpinnerNumberModel(0.25, 0, 1, 0.01));
		spinnerStrategAllC.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double suma= (double)spinnerStrategPc.getValue()+(double)SpinnerStrategAllD.getValue()+(double)spinnerStrategkD.getValue();
				if((suma+(double)spinnerStrategAllC.getValue())>1)
				{
					
					spinnerStrategAllC.setValue((double)1-suma);
				}
			}
		});
		
		SpinnerStrategAllD = new JSpinner();
		SpinnerStrategAllD.setModel(new SpinnerNumberModel(0.25, 0, 1, 0.01));
		SpinnerStrategAllD.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double suma= (double)spinnerStrategPc.getValue()+(double)spinnerStrategAllC.getValue()+(double)spinnerStrategkD.getValue();
				if((suma+(double)SpinnerStrategAllD.getValue())>1)
				{
					SpinnerStrategAllD.setValue((double)1-suma);
				}
			}
		});
		
		spinnerStrategkD = new JSpinner();
		spinnerStrategkD.setModel(new SpinnerNumberModel(0.25, 0, 1, 0.01));
		spinnerStrategkD.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double suma= (double)spinnerStrategPc.getValue()+(double)spinnerStrategAllC.getValue()+(double)SpinnerStrategAllD.getValue();
				if((suma+(double)spinnerStrategkD.getValue())>1)
				{
					
					spinnerStrategkD.setValue((double)1-suma);
				}
			}
		});
		
		JLabel lblStrategiaAllc = new JLabel("strategia AllC:");
		
		JLabel lblStrategia = new JLabel("strategia AllD:");
		
		JLabel lblStrategiaKd = new JLabel("strategia k-D:");
		
		rbtnkConst = new JRadioButton("k_const");
		
		spinnerPc = new JSpinner();
		spinnerPc.setModel(new SpinnerNumberModel(0.5, 0, 1, 0.01));
		
		JLabel lblPWsppracy = new JLabel("p_C:");
		
		spinnerKMAxx = new JSpinner();
		spinnerKMAxx.setModel(new SpinnerNumberModel(3, 0, 7, 1));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "mutacja", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel deltaP_C = new JLabel("delta p_C:");
		
		spinnerDeltaPC = new JSpinner(new SpinnerNumberModel(0, 0, 1, 0.01));
		GroupLayout gl_CAPanel = new GroupLayout(CAPanel);
		gl_CAPanel.setHorizontalGroup(
			gl_CAPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CAPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addGroup(gl_CAPanel.createSequentialGroup()
							.addGroup(gl_CAPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblPWsppracy)
								.addComponent(rbtnkConst, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(deltaP_C))
							.addGap(18)
							.addGroup(gl_CAPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerDeltaPC, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerKMAxx, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerPc, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_CAPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerStrategPc, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CAPanel.createSequentialGroup()
							.addComponent(lblStrategiaAllc)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerStrategAllC, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CAPanel.createSequentialGroup()
							.addComponent(lblStrategia)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(SpinnerStrategAllD, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CAPanel.createSequentialGroup()
							.addComponent(lblStrategiaKd)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerStrategkD, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_CAPanel.setVerticalGroup(
			gl_CAPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CAPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerStrategPc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerStrategAllC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStrategiaAllc))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(SpinnerStrategAllD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStrategia))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerStrategkD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStrategiaKd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerKMAxx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rbtnkConst))
					.addGap(5)
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPWsppracy)
						.addComponent(spinnerPc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CAPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(deltaP_C)
						.addComponent(spinnerDeltaPC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
		);
		
		spinnerMutStrateg = new JSpinner();
		spinnerMutStrateg.setModel(new SpinnerNumberModel(0,0,1,0.01));
		
		JLabel lblZmianaStrateg = new JLabel("strategia:");
		
		spinnerMutPc = new JSpinner();
		spinnerMutPc.setModel(new SpinnerNumberModel(0,0,1,0.01));
		
		JLabel lblNewLabel_2 = new JLabel("parametr Pc:");
		
		spinnerMutC1 = new JSpinner();
		spinnerMutC1.setModel(new SpinnerNumberModel(0.05,0,1,0.01));
		
		JLabel lblC_1 = new JLabel("c1:");
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap(17, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblZmianaStrateg)
						.addComponent(lblNewLabel_2)
						.addComponent(lblC_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
						.addComponent(spinnerMutC1, Alignment.TRAILING)
						.addComponent(spinnerMutPc)
						.addComponent(spinnerMutStrateg, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerMutStrateg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblZmianaStrateg))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerMutPc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerMutC1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblC_1))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		CAPanel.setLayout(gl_CAPanel);
		
		rdbtnTylkoKomrkiCa = new JRadioButton("Tylko Kom\u00F3rki CA");
		rdbtnTylkoKomrkiCa.setSelected(true);
		rdbtnTylkoKomrkiCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTylkoKomrkiCa.isSelected())
				{
					SpinnerpTypAgCA.setEnabled(false);
					LApanel.setEnabled(false);
					CAPanel.setEnabled(true);
					spinnerStrategPc.setEnabled(true);
					spinnerPc.setEnabled(true);
					spinnerStrategAllC.setEnabled(true);
					SpinnerStrategAllD.setEnabled(true);
					spinnerStrategkD.setEnabled(true);
					spinnerKMAxx.setEnabled(true);
					rbtnkConst.setEnabled(true);
					spinnerMutStrateg.setEnabled(true);
					spinnerMutPc.setEnabled(true);
					spinnerMutC1.setEnabled(true);
					spinnerHistoria.setEnabled(false);
					spinnerEpsilon.setEnabled(false);
					spinnerMutationHistoria.setEnabled(false);
					spinnerMutationc2.setEnabled(false);
					SpinnerMutationEpsilon.setEnabled(false);
					spinnerMutationc3.setEnabled(false);
					rdbtnLa1.setEnabled(false);
					rdbtnLa2.setEnabled(false);
					rdbtnLa3.setEnabled(false);
				}
				else
				{
					LApanel.setEnabled(false);
					CAPanel.setEnabled(false);
					spinnerStrategPc.setEnabled(false);
					spinnerPc.setEnabled(false);
					spinnerStrategAllC.setEnabled(false);
					SpinnerStrategAllD.setEnabled(false);
					spinnerStrategkD.setEnabled(false);
					spinnerKMAxx.setEnabled(false);
					rbtnkConst.setEnabled(false);
					spinnerMutStrateg.setEnabled(false);
					spinnerMutPc.setEnabled(false);
					spinnerMutC1.setEnabled(false);
					spinnerHistoria.setEnabled(false);
					spinnerEpsilon.setEnabled(false);
					spinnerMutationHistoria.setEnabled(false);
					spinnerMutationc2.setEnabled(false);
					SpinnerMutationEpsilon.setEnabled(false);
					spinnerMutationc3.setEnabled(false);
				}
			}
		});
		buttonGroup.add(rdbtnTylkoKomrkiCa);
		
		rdbtnTylkoKomrkiLa = new JRadioButton("Tylko Kom\u00F3rki LA");
		rdbtnTylkoKomrkiLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTylkoKomrkiLa.isSelected())
				{
					SpinnerpTypAgCA.setEnabled(false);
					LApanel.setEnabled(true);
					CAPanel.setEnabled(false);
					spinnerStrategPc.setEnabled(false);
					spinnerPc.setEnabled(false);
					spinnerStrategAllC.setEnabled(false);
					SpinnerStrategAllD.setEnabled(false);
					spinnerStrategkD.setEnabled(false);
					spinnerKMAxx.setEnabled(false);
					rbtnkConst.setEnabled(false);
					spinnerMutStrateg.setEnabled(false);
					spinnerMutPc.setEnabled(false);
					spinnerMutC1.setEnabled(false);
					spinnerHistoria.setEnabled(true);
					spinnerEpsilon.setEnabled(true);
					spinnerMutationHistoria.setEnabled(true);
					spinnerMutationc2.setEnabled(true);
					SpinnerMutationEpsilon.setEnabled(true);
					spinnerMutationc3.setEnabled(true);
					rdbtnLa1.setEnabled(true);
					rdbtnLa2.setEnabled(true);
					rdbtnLa3.setEnabled(true);
				}
				else
				{
					LApanel.setEnabled(false);
					CAPanel.setEnabled(false);
					spinnerStrategPc.setEnabled(false);
					spinnerPc.setEnabled(false);
					spinnerStrategAllC.setEnabled(false);
					SpinnerStrategAllD.setEnabled(false);
					spinnerStrategkD.setEnabled(false);
					spinnerKMAxx.setEnabled(false);
					rbtnkConst.setEnabled(false);
					spinnerMutStrateg.setEnabled(false);
					spinnerMutPc.setEnabled(false);
					spinnerMutC1.setEnabled(false);
					spinnerHistoria.setEnabled(false);
					spinnerEpsilon.setEnabled(false);
					spinnerMutationHistoria.setEnabled(false);
					spinnerMutationc2.setEnabled(false);
					SpinnerMutationEpsilon.setEnabled(false);
					spinnerMutationc3.setEnabled(false);
					rdbtnLa1.setEnabled(false);
					rdbtnLa2.setEnabled(false);
					rdbtnLa3.setEnabled(false);
				}
			}
		});
		buttonGroup.add(rdbtnTylkoKomrkiLa);
		
		rdbtnKomrkiLaiCA = new JRadioButton("Kom\u00F3rki LA i CA");
		
		buttonGroup.add(rdbtnKomrkiLaiCA);
		
		rdbtnKomrkiLaiCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnKomrkiLaiCA.isSelected())
				{
					SpinnerpTypAgCA.setEnabled(true);
					CAPanel.setEnabled(true);
					spinnerStrategPc.setEnabled(true);
					spinnerPc.setEnabled(true);
					spinnerStrategAllC.setEnabled(true);
					SpinnerStrategAllD.setEnabled(true);
					spinnerStrategkD.setEnabled(true);
					spinnerKMAxx.setEnabled(true);
					rbtnkConst.setEnabled(true);
					spinnerMutStrateg.setEnabled(true);
					spinnerMutPc.setEnabled(true);
					spinnerMutC1.setEnabled(true);
					LApanel.setEnabled(true);
					spinnerHistoria.setEnabled(true);
					spinnerEpsilon.setEnabled(true);
					spinnerMutationHistoria.setEnabled(true);
					spinnerMutationc2.setEnabled(true);
					SpinnerMutationEpsilon.setEnabled(true);
					spinnerMutationc3.setEnabled(true);
					rdbtnLa1.setEnabled(true);
					rdbtnLa2.setEnabled(true);
					rdbtnLa3.setEnabled(true);
				}
				else
				{
					SpinnerpTypAgCA.setEnabled(false);
					LApanel.setEnabled(false);
					CAPanel.setEnabled(false);
					spinnerStrategPc.setEnabled(false);
					spinnerPc.setEnabled(false);
					spinnerStrategAllC.setEnabled(false);
					SpinnerStrategAllD.setEnabled(false);
					spinnerStrategkD.setEnabled(false);
					spinnerKMAxx.setEnabled(false);
					rbtnkConst.setEnabled(false);
					spinnerMutStrateg.setEnabled(false);
					spinnerMutPc.setEnabled(false);
					spinnerMutC1.setEnabled(false);
					spinnerHistoria.setEnabled(false);
					spinnerEpsilon.setEnabled(false);
					spinnerMutationHistoria.setEnabled(false);
					spinnerMutationc2.setEnabled(false);
					SpinnerMutationEpsilon.setEnabled(false);
					spinnerMutationc3.setEnabled(false);
				}
				
			}
		});
		
			
		SpinnerpTypAgCA = new JSpinner();
		SpinnerpTypAgCA.setModel(new SpinnerNumberModel(0.5,0,1,0.01));
		
		JLabel lblTypAgentaCa = new JLabel("typ Agenta CA:");
		
		rdbtnLa1 = new JRadioButton("LA1");
		rdbtnLa1.setEnabled(false);
		rdbtnLa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup_1.add(rdbtnLa1);
		
		rdbtnLa2 = new JRadioButton("LA2");
		rdbtnLa2.setEnabled(false);
		rdbtnLa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonGroup_1.add(rdbtnLa2);
		
		rdbtnLa3 = new JRadioButton("LA3");
		rdbtnLa3.setEnabled(false);
		rdbtnLa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rdbtnLa3.setSelected(true);
		buttonGroup_1.add(rdbtnLa3);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnKomrkiLaiCA)
						.addComponent(rdbtnTylkoKomrkiCa)
						.addComponent(rdbtnTylkoKomrkiLa)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblTypAgentaCa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SpinnerpTypAgCA, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addComponent(rdbtnLa1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnLa2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnLa3)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnTylkoKomrkiCa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnTylkoKomrkiLa)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnLa1)
						.addComponent(rdbtnLa2)
						.addComponent(rdbtnLa3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnKomrkiLaiCA)
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTypAgentaCa)
						.addComponent(SpinnerpTypAgCA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblLiczbaWierszy = new JLabel("liczba wierszy:");
		
		spinnerLiczbaWireszy = new JSpinner();
		spinnerLiczbaWireszy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (((Integer)spinnerLiczbaWireszy.getValue())==0 || ((Integer)spinnerLiczbaKolumn.getValue())==0)
				{
					spinnerRSasiedztwa.setEnabled(true);
				}
				else
					spinnerRSasiedztwa.setEnabled(false);
					
			}
		});
		spinnerLiczbaWireszy.setModel(new SpinnerNumberModel(50, 0, 1000, 1));
		
		spinnerLiczbaKolumn = new JSpinner();
		spinnerLiczbaKolumn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (((Integer)spinnerLiczbaKolumn.getValue())==0 || ((Integer)spinnerLiczbaWireszy.getValue())==0 )
				{
					spinnerRSasiedztwa.setEnabled(true);
				}
				else
					spinnerRSasiedztwa.setEnabled(false);
			}
		});
		spinnerLiczbaKolumn.setModel(new SpinnerNumberModel(50, 0, 1000, 1));
		
		JLabel lblLiczbaKolumn = new JLabel("liczba kolumn:");
		
		JLabel lblRozmiarSsiedztwa = new JLabel("r s\u0105siedztwa:");
		
		spinnerRSasiedztwa = new JSpinner();
		spinnerRSasiedztwa.setEnabled(false);
		spinnerRSasiedztwa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
			}
		});
		spinnerRSasiedztwa.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		spinnerp_niezam = new JSpinner();
		spinnerp_niezam.setModel(new SpinnerNumberModel(0, 0, 1, 0.01));
		
		JLabel lblPniezam = new JLabel("p_niezam:");
		
		JLabel lblPinitc = new JLabel("p_init_C:");
		
		spinnerp_init_C = new JSpinner();
		spinnerp_init_C.setModel(new SpinnerNumberModel(0.5,0,1,0.01));
		
		spinnergot_wspol_doch = new JSpinner();
		spinnergot_wspol_doch.setModel(new SpinnerNumberModel(0.1,0,1,0.01));
		spinnergot_wspol_doch.setEnabled(false);
		
		rdbtnGotWspDoch = new JRadioButton("got. wspol. doch.");
		rdbtnGotWspDoch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnGotWspDoch.isSelected())
					spinnergot_wspol_doch.setEnabled(true);
				else
					spinnergot_wspol_doch.setEnabled(false);
			}
		});
		
		spinnerQ = new JSpinner();
		spinnerQ.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		rdbtnZiarno = new JRadioButton("ziarno");
		rdbtnZiarno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnZiarno.isSelected())
				{
					spinnerZiarno.setEnabled(true);
				}
				else 
				{
					spinnerZiarno.setEnabled(false);
				}
			}
		});
		
		spinnerZiarno = new JSpinner();
		spinnerZiarno.setEnabled(false);
		
		spinnerIloscExperymentow = new JSpinner();
		spinnerIloscExperymentow.setModel(new SpinnerNumberModel(1, 1, 999, 1));
		
		JLabel lblIloEksperymentw = new JLabel("ilo\u015B\u0107 eksperyment\u00F3w:");
		
		spinnerIloscKrokow = new JSpinner();
		spinnerIloscKrokow.setModel(new SpinnerNumberModel(100, 1, 1000, 1));
		
		JLabel lblDlugoscRundy = new JLabel("ilosc krokow:");
		
		rdbtnQ = new JRadioButton("q");
		rdbtnQ.setSelected(true);
		rdbtnQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnQ.isSelected())
				{
					spinnerQ.setEnabled(true);
				}
				else
					spinnerQ.setEnabled(false);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblLiczbaWierszy)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerLiczbaWireszy, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPinitc)
								.addComponent(lblPniezam, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(lblLiczbaKolumn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addGap(7)
										.addComponent(lblRozmiarSsiedztwa)))
								.addComponent(rdbtnQ))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerp_init_C, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(spinnerp_niezam, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(spinnerLiczbaKolumn, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(spinnerQ, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(spinnerRSasiedztwa, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(rdbtnGotWspDoch)
									.addComponent(rdbtnZiarno))
								.addComponent(lblIloEksperymentw)
								.addComponent(lblDlugoscRundy))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerZiarno, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(spinnergot_wspol_doch, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(spinnerIloscExperymentow, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(spinnerIloscKrokow, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
					.addGap(40))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLiczbaWierszy)
						.addComponent(spinnerLiczbaWireszy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerLiczbaKolumn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLiczbaKolumn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerRSasiedztwa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRozmiarSsiedztwa))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnQ))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerp_niezam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPniezam))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerp_init_C, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPinitc))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnergot_wspol_doch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnGotWspDoch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerZiarno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnZiarno))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerIloscExperymentow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIloEksperymentw))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerIloscKrokow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDlugoscRundy))
					.addGap(4))
		);
		panel_1.setLayout(gl_panel_1);
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
