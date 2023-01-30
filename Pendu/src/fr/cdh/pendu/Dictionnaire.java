package fr.cdh.pendu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Dictionnaire {

	public static final String FICHIER_MOTS = ".src/fr/cdh/maximot2/dictionnaire.txt";
	public static final int NB_MOTS = 22506;
	public static Random rnd = new Random();
	
	public static int nbAlea(int max) {
		Random r = new Random();
		return r.nextInt(NB_MOTS);
	}
	
	private static char[] tirerMotRandom() throws IOException {
		int numMot = rnd.nextInt(NB_MOTS);
		try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS);
			Scanner scan = new Scanner(fichier)) {
				for (int i = 0; i <= numMot; i++)
						scan.nextLine();
				return scan.nextLine().toUpperCase().toCharArray();
		}
	}
	
}
