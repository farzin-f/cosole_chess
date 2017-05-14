public class Fou extends Piece {

    /**
     * Constructeur par paramètres (appelle le constructeur de Piece)
     * @param est_blanc détermine la couleur de la pièce
     * @param colonne colonne dans laquelle se trouve la pièce
     * @param ligne ligne à laquelle se trouve la pièce
     * @param echiquier échiquier sur lequel se trouve la pièce
     */
    public Fou (boolean est_blanc, int colonne, int ligne, Echiquier echiquier)  {
	super(est_blanc, colonne, ligne, echiquier);
    }
   
    /**
     * Représentation ASCII de la pièce
     * @return String contentant un caractère ASCII (majuscule si la pièce est blanche, minuscule sinon)
     */
    public String representationAscii() {
	if (estBlanc()) {
	    return "F";
	} else {
	    return "f";
	}
    }

    /**
     * Représentation Unicode de la pièce
     * @return String contenant un caractère Unicode
     */
    public String representationUnicode() {
	if (estBlanc()) {
	    return "♗";
	} else {
	    return "♝";
	}
    }
   
    /**
       Vérifie si le déplacement demandé est valide
       - Déplacement doit être diagonal
       - Fou ne peut passer par dessus une autre pièce
    */  
    boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {

	if (super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {

	    int colonneOrigine = getColonne();
	    int ligneOrigine = getLigne();
	    int deplHorizontal = nouvelle_colonne - getColonne();
	    int deplVertical = nouvelle_ligne - getLigne();

	    //Déplacement diagonal seulement
	    if (Math.abs(deplHorizontal) == Math.abs(deplVertical)) {
		
		return verifCheminVide(colonneOrigine, ligneOrigine, deplHorizontal, deplVertical);

	    }
	}
	return false;
    }
}
