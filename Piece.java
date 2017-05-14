public class Piece {

    //Attributs
    private boolean est_blanc;
    private boolean est_capture;

    //Position de la pièce
    private int colonne;
    private int ligne;

    private Echiquier echiquier;

    /**
     * Constructeur par paramètres
     * @param est_blanc détermine la couleur de la pièce
     * @param colonne colonne dans laquelle se trouve la pièce
     * @param ligne ligne à laquelle se trouve la pièce
     * @param echiquier échiquier sur lequel se trouve la pièce
     */
    public Piece (boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
	this.est_blanc = est_blanc;
	this.colonne = colonne;
	this.ligne = ligne;
	this.echiquier = echiquier;
	this.est_capture = false;
    }
 
    /**
     * Vérifie si la pièce est blanche
     *@return true si la pièce est blanche       
     */ 
    public boolean estBlanc(){
	return est_blanc;
    }

    /**
     * Vérifie si la pièce est noire
     *@return true si la pièce est noire       
     */     
    public boolean estNoir(){
	return !est_blanc;
    }
  
    /**
     * Vérifie si la pièce a été capturée
     *@return true si la pièce a été capturée       
     */ 
    public boolean estCapture() {
	return est_capture;
    }
  
    /**
     * Trouve la ligne où se trouve la pièce
     *@return le numéro de la ligne     
     */ 
    public int getLigne() {
	return ligne;
    }

    /**
     * Trouve la colonne où se trouve la pièce
     *@return le numéro de la colonne     
     */  
    public int getColonne() {
	return colonne;
    }
  
    /**
     * Trouve l'échiquier où se trouve la pièce
     *@return l'objet Echiquier correspondant a l'échiquier     
     */
    public Echiquier getEchiquier() {
	return echiquier;
    }

    /**
     * Marque une pièce comme étant capturée
     */
    void meSuisFaitCapture() {
	est_capture = true;
    }

    /**
     * Assigne une pièce a un échiquier
     */
    void setEchiquier(Echiquier echiquier) {
	this.echiquier = echiquier;
    }

    /**
       Vérifie si le déplacement demandé est valide
       - La case de destination doit être valide
       - Si la case est occupée, la capture doit être possible
       - La piece ne doit pas avoir été capturée
    */  
    boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
	
	if (!echiquier.caseValide(nouvelle_colonne, nouvelle_ligne)) {
	    return false;
	}

	//La case doit être libre, sinon la capture doit être possible
	Piece p = echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne);
	boolean deplPossible = ((p == null) || 
				(p.estBlanc() == this.estNoir()));

	//Valide si pièce non capturée, et capture possible si case occupée
	return (!(this.estCapture()) && deplPossible);
    }

    /**
     * Déplace une pièce vers une nouvelle case
     * Si la case est occupée, la pièce s'y trouvant est capturée
     */
    void deplace (int nouvelle_colonne, int nouvelle_ligne) {
		
	Piece pieceADeplacer = echiquier.prendsPiece(this.colonne, this.ligne);
	Piece pieceACapturer = null;
	
	//Capture la pièce si case occupée
	if (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne)!= null) 
	    {
		pieceACapturer = echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne);
		echiquier.capturePiece(nouvelle_colonne, nouvelle_ligne);
	    
	    }

	//Changer coordonnées de la pièce
	colonne = nouvelle_colonne;
	ligne = nouvelle_ligne;

	echiquier.posePiece(this);
	
	JeuEchec.affichage(echiquier);

	//Si une pièce a été capturée, afficher un message
	if (pieceACapturer != null) {
	    JeuEchec.afficheCapture(pieceADeplacer, pieceACapturer, nouvelle_colonne, nouvelle_ligne);
	}

	//Si un roi a été capturé, la partie est terminée
	if (pieceACapturer instanceof Roi) {
	    JeuEchec.finPartie();
	}
    }

    /**
     * Retourne un String contenant les attributs de la pièce
     * @return String contenant les attributs de la pièce
     */
    public String toString() {

	return ("est_blanc: " + est_blanc +
		", est_capture: " + est_capture +
		", colonne: " + colonne +
		", ligne: " + ligne);

    }

    /**
     * Représentation ASCII de la pièce
     * @return String contentant un caractère ASCII (majuscule si la pièce est blanche, minuscule sinon)
     */  
    public String representationAscii() {
	if (est_blanc) {
	    return "B";
	} else {
	    return "N";
	}
    }

    /**
     * Représentation Unicode de la pièce
     * @return String contenant un caractère Unicode
     */
    public String representationUnicode() {
	if (est_blanc) {
	    return "B";
	} else {
	    return "N";
	}
    }

    /**
     * Vérifie si le chemin entre deux cases est libre (toutes les cases sont libres)
     * @return boolean - true si toutes les cases sont libres
     */    
    boolean verifCheminVide(int colOrigine, int liOrigine, int deplHor, int deplVer) {
	
	//Sens du déplacement
	int sensDeplHor = trouverSensDepl(deplHor);
	int sensDeplVer = trouverSensDepl(deplVer);

	//Vérifie si chaque case entre l'origine et la destination est libre
	for (int i = 1; i < Math.abs(deplHor) || i < Math.abs(deplVer); i++) {

	    if (echiquier.examinePiece(colOrigine + (i * sensDeplHor), 
				       liOrigine + (i * sensDeplVer)) != null)
		{
		    return false;
		}
	}

	return true;
    }

    /**
     * Trouve le sens d'un déplacement 
     * @return un entier représentant le sens du déplacement (-1 = bas/gauche, 1 = haut/droite)
     */
    int trouverSensDepl(int depl) {
	
	if (depl == 0) {
	    return 0;
	} else {
	    return depl / Math.abs(depl);
	}
    }
  
}

