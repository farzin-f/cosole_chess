public class Roi extends Piece {

    /**
     * Constructeur par paramètres (appelle le constructeur de Piece)
     * @param est_blanc détermine la couleur de la pièce
     * @param colonne colonne dans laquelle se trouve la pièce
     * @param ligne ligne à laquelle se trouve la pièce
     * @param echiquier échiquier sur lequel se trouve la pièce
     */
    public Roi(boolean est_blanc, int colonne, int ligne, Echiquier echiquier)  {
	super(est_blanc, colonne, ligne, echiquier);
    }
    
    /**
     * Représentation ASCII de la pièce
     * @return String contentant un caractère ASCII (majuscule si la pièce est blanche, minuscule sinon)
     */
    public String representationAscii() {
	if (estBlanc()) {
	    return "R";
	} else {
	    return "r";
	}
    }

    /**
     * Représentation Unicode de la pièce
     * @return String contenant un caractère Unicode
     */
    public String representationUnicode() {
	if (estBlanc()) {
	    return "♔";
	} else {
	    return "♚";
	}
    }

    /**
       Vérifie si le déplacement demandé est valide
       - Déplacement doit être horizontal, vertical ou diagonal
       - Une case à la fois seulement
    */
    boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
	
	if (super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
	
	    int deplHorizontal = nouvelle_colonne - getColonne();
	    int deplVertical = nouvelle_ligne - getLigne();
	    
	    //Déplacement d'une case horizontale, verticale ou diagonale
	    return (Math.abs(deplHorizontal) == 1 || 
		    Math.abs(deplVertical) == 1);
	}
	return false;
    }
}
