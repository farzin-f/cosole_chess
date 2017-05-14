public class Pion extends Piece {

    /**
     * Constructeur par paramètres (appelle le constructeur de Piece)
     * @param est_blanc détermine la couleur de la pièce
     * @param colonne colonne dans laquelle se trouve la pièce
     * @param ligne ligne à laquelle se trouve la pièce
     * @param echiquier échiquier sur lequel se trouve la pièce
     */
    public Pion (boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
	super(est_blanc, colonne, ligne, echiquier);
    }

    /**
     * Représentation ASCII de la pièce
     * @return String contentant un caractère ASCII (majuscule si la pièce est blanche, minuscule sinon)
     */
    public String representationAscii() {
	if (estBlanc()) {
	    return "P";
	} else {
	    return "p";
	}
    }

    /**
     * Représentation Unicode de la pièce
     * @return String contenant un caractère Unicode
     */
    public String representationUnicode() {
	if (estBlanc()) {
	    return "♙";
	} else {
	    return "♟";
	}
    }

    /**
       Vérifie si le déplacement demandé est valide
       - S'il n'y a pas de capture, le déplacement doit être vertical
       - Les pions ne peuvent pas reculer
       - Le premier déplacement peut etre de 2
       - Les captures doivent etre faites en diagonale
    */
    boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {

	if (super.deplacementValide(nouvelle_colonne, nouvelle_ligne)) {

	    int deplHorizontal = nouvelle_colonne - getColonne();
	    int deplVertical = nouvelle_ligne - getLigne();
	    
	    //Si le pion ne prend pas de pièce
	    if (getEchiquier().examinePiece(nouvelle_colonne, nouvelle_ligne) == null) {

		//Pas de déplacement horizontal permis
		if (deplHorizontal != 0) {
		    return false;
		}
		
		//Premier déplacement autorisé à 2
		if (estBlanc()) {
		    
		    if (this.getLigne() == 1) {
			return (deplVertical == 2 || deplVertical == 1);
		    } else {
			return (deplVertical == 1);
		    }		
		        
		} else {
		    
		    if (this.getLigne() == 6) {
			return (deplVertical == -2 || deplVertical == -1);
		    } else {
			return (deplVertical == -1);
		    }
		}
		 		
	    } else {

		//Si le pion prend une pièce
		if (Math.abs(deplHorizontal) == Math.abs(deplVertical)) {
		    
		    if (estBlanc()) {
			return (deplVertical == 1);
		    } else {
			return (deplVertical == -1);
		    }

		} else {
		    return false;
		}
	    }
	}
	return false;
    }

}
