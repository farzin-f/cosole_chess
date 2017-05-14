import java.io.*;

public class Echiquier {
    
    //Tableau de deux dimensions contenant les cases de l'échiquier
    private Piece[][] tableau_de_jeu;

    //Tableaux d'une dimension contenant les pièces capturées
    private Piece[] blancs_captures;
    private Piece[] noirs_captures;

    //Nombre de pièces capturées
    private int pCaptBlanc;
    private int pCaptNoire;

    /**
     * Constructeur sans paramètres
     */
    public Echiquier() {

    	this.pCaptBlanc = 0;
    	this.pCaptNoire = 0;
		
    	blancs_captures = new Piece[16];
    	noirs_captures = new Piece[16];

	//Création des pièces de jeu
    	Piece tourB1 = new Tour(true, 0, 0, this);
    	Piece cavalB1 = new Cavalier(true, 1, 0, this);
    	Piece fouB1 = new Fou(true, 2, 0, this);
    	Piece dameB = new Dame(true, 3, 0, this);
    	Piece roiB = new Roi(true, 4, 0, this);
    	Piece fouB2 = new Fou(true, 5, 0, this);
    	Piece cavalB2 = new Cavalier(true, 6, 0, this);
    	Piece tourB2 = new Tour(true, 7, 0, this);

    	Piece pionB1 = new Pion(true, 0, 1, this);
    	Piece pionB2 = new Pion(true, 1, 1, this);
    	Piece pionB3 = new Pion(true, 2, 1, this);
    	Piece pionB4 = new Pion(true, 3, 1, this);
    	Piece pionB5 = new Pion(true, 4, 1, this);
    	Piece pionB6 = new Pion(true, 5, 1, this);
    	Piece pionB7 = new Pion(true, 6, 1, this);
    	Piece pionB8 = new Pion(true, 7, 1, this);


    	Piece tourN1 = new Tour(false, 0, 7, this);
    	Piece cavalN1 = new Cavalier(false, 1, 7, this);
    	Piece fouN1 = new Fou(false, 2, 7, this);
    	Piece dameN = new Dame(false, 3, 7, this);
    	Piece roiN = new Roi(false, 4, 7, this);
    	Piece fouN2 = new Fou(false, 5, 7, this);
    	Piece cavalN2 = new Cavalier(false, 6, 7, this);
    	Piece tourN2 = new Tour(false, 7, 7, this);

    	Piece pionN1 = new Pion(false, 0, 6, this);
    	Piece pionN2 = new Pion(false, 1, 6, this);
    	Piece pionN3 = new Pion(false, 2, 6, this);
    	Piece pionN4 = new Pion(false, 3, 6, this);
    	Piece pionN5 = new Pion(false, 4, 6, this);
    	Piece pionN6 = new Pion(false, 5, 6, this);
    	Piece pionN7 = new Pion(false, 6, 6, this);
    	Piece pionN8 = new Pion(false, 7, 6, this);
		
	//Initialisation du tableau de jeu
    	this.tableau_de_jeu = new Piece[][] {
	    {tourB1, cavalB1, fouB1, dameB, roiB, fouB2, cavalB2, tourB2},
	    {pionB1, pionB2, pionB3, pionB4, pionB5, pionB6, pionB7, pionB8},
    	    {null,null,null,null,null,null,null,null},
    	    {null,null,null,null,null,null,null,null},
    	    {null,null,null,null,null,null,null,null},
    	    {null,null,null,null,null,null,null,null},
 	    {pionN1, pionN2, pionN3, pionN4, pionN5, pionN6, pionN7, pionN8},
   	    {tourN1, cavalN1, fouN1, dameN, roiN, fouN2, cavalN2, tourN2}
    	};
    }

    /**
     * Vérifie si une case fait partie de l'échiquier
     * @param colonne numéro de la colonne
     * @param ligne numéro de la ligne
     * @return boolean - true si la case fait partie de l'échiquier
     */
    public boolean caseValide(int colonne, int ligne) {

	return ((colonne >= 0) && (colonne < 8) && 
		(ligne >= 0) && (ligne < 8));

    }

    /**
     * Vérifie quelle pièce se trouve a une case donnée
     * @param colonne numéro de la colonne
     * @param ligne numéro de la ligne
     * @return objet Piece représentant la pièce se trouvant a la case donnée
     */
    public Piece examinePiece(int colonne, int ligne) {

	return this.tableau_de_jeu[ligne][colonne];
 
    }

    /**
     * Prends la pièce à une case donnée (met le tableau de jeu à null)
     * @param colonne numéro de la colonne
     * @param ligne numéro de la ligne
     * @return objet Piece représentant la pièce prise
     */
    public Piece prendsPiece(int colonne, int ligne) {

	Piece piecePrise = this.tableau_de_jeu[ligne][colonne];
	this.tableau_de_jeu[ligne][colonne] = null;
	return piecePrise;

    }

    /**
     * Pose la pièce a une case donnée
     * @param p pièce a poser
     */    
    public void posePiece(Piece p) {
	tableau_de_jeu[p.getLigne()][p.getColonne()] = p;
    }

    /**
     * Capture la pièce à une case donnée
     * @param colonne numéro de la colonne
     * @param ligne numéro de la ligne
     */
    public void capturePiece(int colonne, int ligne) {

	//Prendre la pièce et marquer comme capturée
	Piece pieceCapturee = prendsPiece(colonne, ligne);
	pieceCapturee.meSuisFaitCapture();
	
	//Ajouter au tableau des pièces capturées
	if (pieceCapturee.estBlanc()) {
	    
	    blancs_captures[this.pCaptBlanc] = pieceCapturee;
	    ++this.pCaptBlanc;

	} else {

	    noirs_captures[this.pCaptNoire] = pieceCapturee;
	    ++this.pCaptNoire;
	    
	}
    }

    /**
     * Affiche l'état actuel de l'échiquier en représentation ASCII
     */
    public void afficheAscii()
    {
	
	System.out.println();
	System.out.print("Les noirs ont capturé: ");

	for (int i = 0; i < 16; ++i) {
	    if (blancs_captures[i] != null) {
		System.out.print(blancs_captures[i].representationAscii()+" ");
	    }
	}
	
	System.out.println();
	System.out.println();
	System.out.println("   a b c d e f g h");
	System.out.println("   ---------------");


	for(int i = 7; i >= 0; --i)  {

	    System.out.print((i+1)+"| ");

	    for(int j = 0; j < 8; ++j) {

		if (tableau_de_jeu[i][j] != null) {
		    System.out.print(this.tableau_de_jeu[i][j].representationAscii() + " ");
		} else {
		    System.out.print("  ");
		}
	    }
	    System.out.println("|"+ (i+1));
	}

	System.out.println("   ---------------");
	System.out.println("   a b c d e f g h");
	System.out.println();
	System.out.println();

	System.out.print("Les blancs ont capturé: ");

	for (int i = 0; i < 16; ++i) {
	    if (noirs_captures[i] != null) {
		System.out.print(noirs_captures[i].representationAscii()+" ");
	    }
	}

	System.out.println();
	System.out.println();

    }

    /**
     * Affiche l'état actuel de l'échiquier en représentation Unicode
     */
    public void afficheUnicode()
    {

	PrintStream sysout = null;
	try {
	    sysout = new PrintStream(System.out, true, "UTF-8");   
	}
	catch(UnsupportedEncodingException e) {} 

	System.out.println();
	System.out.print("Les noirs ont capturé: ");

	for (int i = 0; i < 16; ++i) {
	    if (blancs_captures[i] != null) {
		sysout.print(blancs_captures[i].representationUnicode()+" "); 
	    }
	}
		
	System.out.println();
	System.out.println();
	System.out.println("   a   b   c   d   e   f   g   h");
	System.out.println();
	sysout.print(" ┌");
	
	for(int t = 0; t < 7; ++t) {
	    sysout.print("───┬");
	}
	sysout.print("───┐");
	System.out.println();
	
	for(int i = 7; i >= 0; --i) {

	    System.out.print(i+1);

	    for(int j = 0; j < 8; ++j) {
		    
		if (tableau_de_jeu[i][j] != null) {
		    sysout.print("│ " + this.tableau_de_jeu[i][j].representationUnicode() + " ");
		} else {
		    sysout.print("│   ");
		}
	    }

	    sysout.println("│"+(i+1));

	    if (i > 0) {
		sysout.print(" ├");
		for(int m = 0; m < 7; ++m) {
		    sysout.print("───┼");
		}	  
		sysout.print("───┤");
		System.out.println();
		    
	    }
	}
	sysout.print(" └");
	
	for(int b = 0; b < 7; ++b) {
	    sysout.print("───┴");
	}
		
	sysout.println("───┘");

	System.out.println();
	System.out.println("   a   b   c   d   e   f   g   h");
	System.out.println();
	System.out.println();

	System.out.print("Les blancs ont capturé: ");
	for (int i = 0; i < 16; ++i) {
	    if (noirs_captures[i] != null) {
		sysout.print(noirs_captures[i].representationUnicode()+" ");
	    }
	}

	System.out.println();
	System.out.println();
    }
  
    /**
     * Affiche l'état actuel de l'échiquier en représentation ASCII
     * Indique les déplacements possibles par un "x"
     * Indique les captures possibles par un "X"
     * @param p pièce dont les deplacements possibles sont affichés
     */  
    public void affichePossiblesAscii(Piece p) {

	Position[] tabDeplPossibles = JeuEchec.deplacementsPossibles(p);
	System.out.println();
	System.out.print("Les noirs ont capturé: ");
	for (int i = 0; i < 16; ++i) {
	    if (blancs_captures[i] != null) {
		System.out.print(blancs_captures[i].representationAscii()+" ");
	    }
	}
	
	System.out.println();
	System.out.println();
	System.out.println("   a b c d e f g h");
	System.out.println("   ---------------");


	for(int i = 7; i >= 0; --i)  {

	    System.out.print((i+1)+"| ");

	    for(int j = 0; j < 8; ++j) {
		boolean possible = JeuEchec.verifPosition(tabDeplPossibles, i, j);
		if (tableau_de_jeu[i][j] != null && possible) {
		    
		    System.out.print(this.tableau_de_jeu[i][j].representationAscii() + "X");
		} else if (tableau_de_jeu[i][j] != null && !possible) {
		    System.out.print(this.tableau_de_jeu[i][j].representationAscii() + " ");
		} else if (tableau_de_jeu[i][j] == null && possible) {
		    System.out.print("x ");
		} else if (tableau_de_jeu[i][j] == null && !possible) {
		    System.out.print("  ");
		}
	    }
	    
	    System.out.println("|"+ (i+1));
	}

	System.out.println("   ---------------");
	System.out.println("   a b c d e f g h");
	System.out.println();
	System.out.println();

	System.out.print("Les blancs ont capturé: ");
	for (int i = 0; i < 16; ++i) {
	    if (noirs_captures[i] != null) {
		System.out.print(noirs_captures[i].representationAscii()+" ");
	    }
	}

	System.out.println();
	System.out.println();
    }

    /**
     * Affiche l'état actuel de l'échiquier en représentation Unicode
     * Indique les déplacements possibles par un "x"
     * Indique les captures possibles par un "X"
     * @param p pièce dont les deplacements possibles sont affichés
     */  
    public void affichePossiblesUnicode(Piece p)
    {
	PrintStream sysout = null;
	try {
	    sysout = new PrintStream(System.out, true, "UTF-8");   
	}
	catch(UnsupportedEncodingException e) {} 

	Position[] tabDeplPossibles = JeuEchec.deplacementsPossibles(p);
	System.out.println();
	System.out.print("Les noirs ont capturé: ");
	for (int i = 0; i < 16; ++i) {
	    if (blancs_captures[i] != null) {
		sysout.print(blancs_captures[i].representationUnicode()+" "); 
	    }
	}
		
	System.out.println();
	System.out.println();
	System.out.println("   a   b   c   d   e   f   g   h");
	System.out.println();
	sysout.print(" ┌");
	
	for(int t = 0; t < 7; ++t) {
	    sysout.print("───┬");
	}
	sysout.print("───┐");
	System.out.println();
	
       	for(int i = 7; i >= 0; --i) {

	    System.out.print(i+1);
	    
	    for(int j = 0; j < 8; ++j) {
		
		boolean possible = JeuEchec.verifPosition(tabDeplPossibles, i, j);		    
		if (tableau_de_jeu[i][j] != null && possible) {
		    
		    sysout.print("│" + this.tableau_de_jeu[i][j].representationUnicode() + " X");
		} else if (tableau_de_jeu[i][j] != null && !possible) {
		    sysout.print("│ " + this.tableau_de_jeu[i][j].representationUnicode() + " ");
		} else if (tableau_de_jeu[i][j] == null && possible) {
		    sysout.print("│ x ");
		} else if (tableau_de_jeu[i][j] == null && !possible) {
		    sysout.print("│   ");
		}
	    }  
	    
	    sysout.println("│"+(i+1));
	    if (i > 0) {
		sysout.print(" ├");
		
		for(int m = 0; m < 7; ++m) {
		    sysout.print("───┼");
		}	  
		sysout.print("───┤");
		sysout.println();
		
	    }
	}
	sysout.print(" └");
	for(int b = 0; b < 7; ++b) {
	    sysout.print("───┴");
	}
	sysout.println("───┘");

	System.out.println();
	System.out.println("   a   b   c   d   e   f   g   h");
	System.out.println();
	System.out.println();
	
	System.out.print("Les blancs ont capturé: ");
	for (int i = 0; i < 16; ++i) {
	    if (noirs_captures[i] != null) {
		sysout.print(noirs_captures[i].representationUnicode()+" ");
	    }
	}

	System.out.println();
	System.out.println();
    }

}


