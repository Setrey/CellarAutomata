
public class Settings {
	//
	int numberOfRows; 			// ilosc wierszy
	int numberOfColumns; 		// ilosc kolumn
	int radiusOfNeighbor;		// promien sasiedztwa
	int qChanges; 				// parametr Q (zmiany w chwili Q)
	double probOfUnhabitedCell;	// prawdopodobieñstwo niezamieszkania komórki 
	double probOfInitCState;	// prawdopodobieñstwo komórek z stanem C		
	double probOfPayoffSharing;	// prawdopodobieñstwo dzielenia dochodu
	int seed;					// ziarno losowosci 
	int numberOfExperiments;	// liczba eksperymentów 
	int numberOfFrames;			// liczba klatek/kroków w eksperymencie
	PrisonerDilema pDSettings;	// obiekt z ustawieniami dylematu wieznia
	typeOfAgent agentType; 		// typ agenta
	double probOfAgentCA; 		// prawdopodobieñstwo na agenta CA (0) LA (1) lub mieszane gdy pomiedzy
	int historyLength;			// d³ugoœæ historii dla Agentów LA
	double epsilon;				// wartosc epsilon wybrania najlepszej historii
	Mutation mutation;			// obiekt z parametrami mutacji
	double probOfPcStrategy;	// prawdopodobieñstwo strategii Pc
	double probOfAllCStrategy; 	// prawdopodobieñstwo strategii AllC
	double probOfAllDStrategy;	// prawdopodobieñstwo strategii AllD
	double probOfKDStrategy;	// prawdopodobieñstwo strategii kD
	boolean isKConst;			// czy strategie kD maj¹ sta³¹ wartoœæ
	int kMax;					// maksymalne K w strategi kD
	double valueOfPc;			// wartosc Pc
	double deltaPc;				// odstêp od ustaalonego P_c
	
	boolean isQselected;		// Czy parametr Q jest zaznaczony
	boolean isDebugSelected;	// Czy opcja debug jest zaznaczona

	boolean isLA1;				// czy LA1
	boolean isLA2;				// czy LA2
	boolean isLA3;				// czy LA3
	
	void setDefault()
	{
		numberOfRows=50;
		numberOfColumns=50;
		radiusOfNeighbor=1;
		qChanges=1;
		probOfUnhabitedCell=0;
		probOfInitCState=0.5;
		probOfPayoffSharing=0;
		seed=0;
		numberOfExperiments=1;
		numberOfFrames=100;
		pDSettings=new PrisonerDilema();
		agentType=typeOfAgent.CA;
		probOfAgentCA=0.5;
		historyLength=8;
		epsilon=0.001;
		mutation=new Mutation();
		probOfAllCStrategy=0.25;
		probOfAllDStrategy=0.25;
		probOfKDStrategy=0.25;
		probOfPcStrategy=0.25;
		isKConst=false;
		kMax=3;
		valueOfPc=0.5;
		deltaPc=0;
		isQselected=true;
		isDebugSelected=false;
		isLA1=false;
		isLA2=false;
		isLA3=true;
	}
	
	public Settings ()
	{
		setDefault();
	}
}
