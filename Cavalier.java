public class Cavalier extends Piece {

  /**
    * Constructeur par paramètres (appelle le constructeur de Piece)
    * @param est_blanc détermine la couleur de la pièce
    * @param colonne colonne dans laquelle se trouve la pièce
    * @param ligne ligne à laquelle se trouve la pièce
    * @param echiquier échiquier sur lequel se trouve la pièce
  */
    public Cavalier (boolean est_blanc, int colonne, int ligne, Echiquier echiquier)  {
	super(est_blanc, colonne, ligne, echiquier);
    }
    
  /**
    * Représentation ASCII de la pièce
    * @return String contentant un caractère ASCII (majuscule si la pièce est blanche, minuscule sinon)
  */
    public String representationAscii() {
	if (estBlanc()) {
	    return "C";
	} else {
	    return "c";
	}
    }

  /**
    * Représentation Unicode de la pièce
    * @return String contenant un caractère Unicode
  */
    public String representationUnicode() {
	if (estBlanc()) {
	    return "♘";
	} else {
	    return "♞";
	}
    }

    /**
       Vérifie si le déplacement demandé est valide
       - Déplacement doit être en "L"
    */
    boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
	
	if (super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {
	
	int deplHorizontal = nouvelle_colonne - getColonne();
	int deplVertical = nouvelle_ligne - getLigne();
	
	//Déplacement en "L" (une case horizontale et deux verticales ou l'inverse)
	return (Math.abs(deplHorizontal) > 0 && 
		Math.abs(deplVertical) > 0 && 
		Math.abs(deplVertical) + Math.abs(deplHorizontal) == 3);
	}
	return false;
    }
}
