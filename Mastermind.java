import java.util.Scanner;

public class Mastermind {

	public Mastermind() {		
		
		play();
	}

	private void play() {
		
		System.out.println("Rappel : V -> OK | O -> Ailleurs | X -> NON");
		System.out.println("Bienvenue au jeu du Mastermind !");
		int theLengthCode = lengthCode();
		int tabRandomCode[] = tableauRandomCode(theLengthCode);
		
		boolean win = false;
		int nbEssai = 1;
		
		while(win == false && nbEssai<=8) {
			
			System.out.println("Essai n° " + nbEssai);
			int theUserCode = getUserCode(theLengthCode);
			
			int tabUserCode[] = tableauUserCode(theLengthCode, theUserCode);
			
			win = comparateCodes(tabUserCode, tabRandomCode, theLengthCode);
			System.out.println("");

			nbEssai++;
		}
		
		if(win == true) {
			
			System.out.println("Félicitations, vous venez de trouver le code en " + nbEssai + " essais !");
			System.out.println("Le code était bien : " + afficherTableauRandom(tabRandomCode));
		}
		if(nbEssai > 8 || win==false) {
			System.out.println("Vous n'avez pas réussi à trouver le code !");
			System.out.println("Le code était : " + afficherTableauRandom(tabRandomCode));
		}
		
		System.out.println("Voulez-vous rejouer ? (Oui ou Non)");
		Scanner scan = new Scanner(System.in);
		String saisie =scan.nextLine();
		
		rejouer(saisie);
	}
	
	private int randomNumber() {
		
		int randomNumber = (int)Math.floor(Math.random() * 10);
		return randomNumber;
	}
	
	private int lengthCode() {
		
		System.out.println("Combien de chiffres voulez-vous dans le code ?");
		Scanner scan = new Scanner(System.in);
		int saisie =scan.nextInt();
		
		return saisie;
	}
	
	private int[] tableauRandomCode(int lCode) {
		
		int i;
		int tab[] = new int[lCode];
		
		for(i = 0; i <= lCode-1; i++) {
			
			tab[i] = randomNumber();
			System.out.print(tab[i]);
			
		}
		
		return tab;
	}
	
	private int getUserCode(int lCode) {
		
		Scanner scan = new Scanner(System.in);
		String saisie =scan.nextLine();
		int userCode = 0;
		
		if(saisie.length() == lCode) {			
			userCode = Integer.parseInt(saisie);			
		}
		else if(saisie.length() != lCode) {
			String resaisie="";
			while(resaisie.length() != lCode) {
			
				System.out.println("Veuillez saisir un code à " + lCode + " chiffres !");
				resaisie = scan.nextLine();
			}
			userCode = Integer.parseInt(resaisie);
		}
		
		return userCode;	
	}
	
	private int[] tableauUserCode(int lCode, int uCode) {
		
		int i;
		int tab[] = new int[lCode];
		for(i = 0; i <= lCode-1; i++) {
			
			tab[i] = (int) ((uCode / Math.pow(10, (lCode-1 - i))) % 10);
			//System.out.print(tab[i]);

		}
		
		return tab;
	}
	
	private boolean comparateCodes(int[] tabUser, int[] tabRandom, int lCode) {
		
		int i, j;
		int ThelCode = lCode - 1;
		String lettre = "";
		boolean game = false;
		boolean finalGame = true;
		
		for(i = 0; i <= ThelCode; i++) {
						
			if(tabRandom[i] != tabUser[i]){
					
				lettre = "X";
				game = false;
			}
			for (j=0; j <= ThelCode; j++) {
				
				if(tabRandom[j] == tabUser[i]) {
					lettre = "O";
					game = false;
				}				
			}
			if(tabRandom[i] == tabUser[i]) {
					
				lettre = "V";
				game = true;
			}
			
			if (game == false) {
				finalGame = false;
			}
		
			System.out.print(lettre);
			
		}
		
		return finalGame;
	}
	
	private String afficherTableauRandom(int[] tableau) {
		
		String liste = "";
        for (int i = 0; i < tableau.length; i++) {
          liste += (tableau[i] + "");
        }
        return liste;
	}
	
	private void rejouer(String reponse) {
		
		Scanner scan = new Scanner(System.in);
		if(!reponse.equals("Oui") && !reponse.equals("Non")) {	
			while(!reponse.equals("Oui") && !reponse.equals("Non")) {
			
				System.out.println("Veuillez saisir Oui ou Non !");
				reponse = scan.nextLine();
			}
		}
		else if(reponse.equals("Oui")) {			
			play();			
		}
		else if(reponse.equals("Non")) {			
			scan.close();				
			System.exit(0);
		
		}
	}
	
}