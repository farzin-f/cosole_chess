//IFT1025 - Automn2015, Devoir1, Jeu d'echec
//Autheurs: Catherine Laprise et Farzin Faridfar

import java.util.Scanner;
import java.io.*;

public class JeuEchec {

    static boolean joueurBlanc = true;
    static boolean encodageAscii;
    
    public static void main (String[] args) {
	Scanner scanEncodage = new Scanner(System.in);
	Scanner scanDepl = new Scanner (System.in);

	Echiquier echiquier = new Echiquier();
	
	//Si encodage pas précisé, demander au joueur de l'entrer
	if (args.length > 0) {
	    setEncodage(args[0]);
	} else {
	    System.out.println( "Veuillez préciser l'encodage (ascii ou unicode): ");
	    setEncodage(scanEncodage.next());
	}
	
       	affichage(echiquier);
	
       	while (true) {
  
	    //Vérifier à qui c'est le tour
	    if (joueurBlanc) {
		System.out.print("Joueur blanc? ");
	    } else {
		System.out.print("Joueur noir? ");
	    }
	    
	    //Entrée du déplacement
	    String depl = scanDepl.nextLine();
	    
	    if (depl.equals("")) { //Aucune entrée
	     
		affichage(echiquier);

	    } else if (depl.length() == 2) { //Entrée d'une case seuleent

		//Vérifie si l'entrée est valide (lettre, chiffre)
		if (entreeValide(depl)) {
		    int col = conversionCol(depl.charAt(0));
		    int li = conversionLi(depl.charAt(1));
		    
		    //Vérifie si la case est existante
		    if (echiquier.caseValide(col, li)) {

			Piece pieceAVerifier = echiquier.examinePiece(col, li);
			
			//Afficher un message d'erreur si la case est vide
			if (pieceAVerifier == null) {
			    System.out.println("Case vide");
			    continue;
			}
			
			//Afficher les déplacements possibles
			if (encodageAscii) {
			    echiquier.affichePossiblesAscii(pieceAVerifier);
			} else {
			    echiquier.affichePossiblesUnicode(pieceAVerifier);
			}
			
		    } else {
			System.out.println("Case invalide");
			continue;
		    }
		} else {
		    System.out.println("Entrée invalide; veuillez entrer une position de la forme colonne(lettre) ligne(chiffre) (ex: c3).");
		    continue;
		}
		
	    } else if (depl.length() == 5) {//Entrée de deux cases

		//Vérifier si les entrées sont valides
		if (entreeValide(depl.substring(0,2)) &&
		    Character.isWhitespace(depl.charAt(2)) &&
		    entreeValide(depl.substring(3))) {
		    
		    int colOrigine = conversionCol(depl.charAt(0));
		    int liOrigine = conversionLi(depl.charAt(1));
		    int colDestination  = conversionCol(depl.charAt(3));
		    int liDestination = conversionLi(depl.charAt(4));

		    Piece pieceADeplacer;

		    //Vérifie si la case de départ est valide
		    if (echiquier.caseValide(colOrigine, liOrigine)) {
			pieceADeplacer = echiquier.examinePiece(colOrigine, liOrigine);
		    } else {
			System.out.println("Ce déplacement n'est pas valide.");
			continue;
		    }

		    /*Vérifie si:
		      - il y a une pièce a la case de départ
		      - la pièce est de la couleur du joueur courant
		      - le déplacement demandé est valide*/
		    if (pieceADeplacer != null && 
			pieceADeplacer.estBlanc() == joueurBlanc &&
			pieceADeplacer.deplacementValide(colDestination, liDestination)) {
			
			pieceADeplacer.deplace(colDestination, liDestination);
			
			joueurBlanc = !joueurBlanc;
			
		    } else {
			System.out.println("Ce déplacement n'est pas valide.");
		    }
		} else {
		    System.out.println("Entrée invalide; veuillez entrer une position source et destination de la forme colonne(lettre) ligne(chiffre) (ex: c3 d5).");
		}
		
	    } else {
	    System.out.println("Entrée invalide; veuillez entrer une position source et destination de la forme colonne(lettre) ligne(chiffre) (ex: c3 d5).");
	    }
	}
    }

    /**
     * Affiche le gagnant de la partie et termine l'exécution
     */
    public static void finPartie() {
	System.out.print("La partie est terminée. ");
	if (joueurBlanc) {
	    System.out.println("Les blancs ont gagné.");
	} else {
	    System.out.println("Les noirs ont gagné.");
	}
	System.exit(0);
    }

    /**
     * Vérifie si une entrée est valide (lettre + chiffre)
     * @param position position entrée par l'utilisateur
     * @return boolean - true si l'entrée est valide
     */
    public static boolean entreeValide (String position) {
	return (Character.isLetter(position.charAt(0)) &&
		Character.isDigit(position.charAt(1)));
    }
    
    /**
     * Convertit le caractère colonne en entier de 0 à 8
     * @param col caractère colonne entré par l'utilisateur
     * @return entier de 0 à 8 représentant la colonne
     */
    public static int conversionCol(char col) {
	return (int)Character.toLowerCase(col)-97;
    }

    /**
     * Convertit le caractère ligne en entier de 0 à 8
     * @param li caractère ligne entré par l'utilisateur
     * @return entier de 0 à 8 représentant la ligne
     */
    public static int conversionLi(char li) {
	return (int)li-49;
    }

    /**
     * Détermine l'encodage utilisé selon l'entrée de l'utilisateur
     * @param encodage encodage entré par l'utilisateur
     */
    public static void setEncodage(String encodage) {
	Scanner scan = new Scanner(System.in);

	if (encodage.equalsIgnoreCase("ascii")) {
	    encodageAscii = true;
	} else if (encodage.equalsIgnoreCase("unicode")) {
	    encodageAscii = false;
	} else {
	    System.out.println( "Veuillez préciser l'encodage (ascii ou unicode): ");
	    setEncodage(scan.next());
	}
    }

    /**
     * Appelle la fonction d'affichage de l'échiquier selon l'encodage choisi
     * @param echiquier objet Echiquier à afficher
     */
    public static void affichage(Echiquier echiquier) {
	
	if (encodageAscii) {
	    echiquier.afficheAscii();
	} else {
	    echiquier.afficheUnicode();
	}
    }
		
    /**
     * Affiche un message lorsqu'une pièce est capturée
     * @param pieceDeplacee pièce qui effectue la capture
     * @param pieceCapturee pièce qui est capturée
     * @param colonne colonne où la pièce est capturée
     * @param ligne ligne où la pièce est capturée
     */
    public static void afficheCapture (Piece pieceDeplacee, Piece pieceCapturee, int colonne, int ligne) {
	
	PrintStream sysout = null;
	try {
	    sysout = new PrintStream(System.out, true, "UTF-8");   
	}
	catch(UnsupportedEncodingException e) {} 

	char colCapture = (char)(colonne+97);
	int liCapture = ligne+1;

	
	if (encodageAscii) {

	    System.out.println(pieceDeplacee.representationAscii()+" a capturé "+
			   pieceCapturee.representationAscii() + " en " + 
			   colCapture + liCapture);
	    System.out.println();

		} else {

	    sysout.println(pieceDeplacee.representationUnicode()+" a capturé "+
			   pieceCapturee.representationUnicode() + " en " + 
			   colCapture + liCapture);
	    System.out.println();

		}
	
    }

    /**
     * Trouve les déplacements possibles pour une pièce donnée
     * @param p pièce pour laquelle on vérifie les déplacements
     * @return tableau des positions où les déplacements sont possibles
     */
    public static Position[] deplacementsPossibles (Piece p) {

	Position[] tab = new Position[63];
	int t = 0;

	for (int i = 0; i < 8; ++i) {

	    for (int j = 0; j < 8; ++j) {
		
		if (p.deplacementValide(i, j)) {
		    tab[t] = new Position(j, i);
		    ++t;
		}
		 
	    }
	}

	return tab;
    }

    /**
     * Vérifie si une position donnée est dans le tableau des déplacements possibles
     * @param tab tableau contenant les déplacements possibles
     * @param colonne colonne de la position à vérifier
     * @param ligne ligne de la position à vérifier
     */
    public static boolean verifPosition(Position[] tab, int colonne, int ligne) {
	Position posVerifiee = new Position(colonne,ligne);

	for (int i = 0; i < 64; ++i) {

	    if (tab[i] == null) {
		return false;
	    } else {
		if (tab[i].colonne == posVerifiee.colonne && 
		    tab[i].ligne == posVerifiee.ligne) {
		    return true;
		}
	    }
	}
	return false;
    }
	
}
	    
