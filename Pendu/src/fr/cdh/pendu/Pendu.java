package fr.cdh.pendu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Pendu {

	private static List<Character> mauvaisesLettres = new ArrayList<>();
	private static List<Character> bonnesLettres = new ArrayList<>();
	private static char[] aTrouver;
	private static final String FICHIER_MOTS = "./src/fr/cdh/pendu/dictionnaire.txt";
	private static final int NB_MOTS = 22506;
	private static Random rnd = new Random();

	public static int nbAlea(int max) {
		Random r = new Random();
		return r.nextInt(NB_MOTS);
	}

	public static char[] tirerMotRandom() throws IOException {
		int numMot = rnd.nextInt(NB_MOTS);
		try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS); Scanner scan = new Scanner(fichier)) {
			for (int i = 0; i <= numMot; i++)
				scan.nextLine();
			return scan.nextLine().toUpperCase().toCharArray();
		}
	}

	private static void afficherPendu(List<Character> mauvaisesLettres) {
		System.out.println();
		switch (mauvaisesLettres.size()) {
		default:
			System.out.println();
			break;
		case 1:
			System.out.println("|_____");
			break;
		case 2:
			System.out.printf("|%n|%n|%n|%n|____");
			break;
		case 3:
			System.out.printf("____%n|%n|%n|%n|%n|____");
			break;
		case 4:
			System.out.printf("____%n|  |%n|%n|%n|%n|____");
			break;
		case 5:
			System.out.printf("____%n|  |%n|  0%n|%n|%n|____");
			break;
		case 6:
			System.out.printf("____%n|  |%n|  0%n|  |%n|%n|____");
			break;
		case 7:
			System.out.printf("____%n|  |%n|  0%n| -|%n|%n|____");
			break;
		case 8:
			System.out.printf("____%n|  |%n|  0%n| -|-%n|%n|____");
			break;
		case 9:
			System.out.printf("____%n|  |%n|  0%n| -|-%n| /%n|____");
			break;
		case 10:
			System.out.println("_____");
			System.out.println("|  |");
			System.out.println("|  O");
			System.out.println("| -|-");
			System.out.println("| / \\");
			System.out.println("|_____");
			break;
		}
		System.out.println();
	}

	private static void afficherMot(char[] mot, List<Character> bonnesLettres) {
		for (int i = 0; i < mot.length; i++) {
			if (bonnesLettres.contains(mot[i])) {
				System.out.printf("%s ", mot[i]);
			} else {
				System.out.printf("_ ");
			}
		}
	}

	private static void jouer() throws IOException {
		aTrouver = tirerMotRandom();
		System.out.println(aTrouver);
		boolean fini = false;
		for (int i = 0; i < aTrouver.length; i++) {
			System.out.printf("_ ");
		}
		System.out.println();
		do {
			afficher();
			char lettre = proposerLettre();
			comparerLettres(aTrouver, lettre);
			afficherMot(aTrouver, bonnesLettres);

			if (mauvaisesLettres.size() == 10 || bonnesLettres.size() == aTrouver.length) {
				fini = true;
			}
		} while (!fini);
		if (mauvaisesLettres.size() == 10) {
			String s = new String(aTrouver);
			System.out.printf("%nDésolé vous avez perdu, le mot à trouver était : %s", s);
		} else {
			System.out.printf("%nBravo ! Vous avez gagné");
		}
	}

	private static void afficher() throws IOException {
		if (mauvaisesLettres.size() == 0) {
			System.out.printf("%nVous n'avez pas encore proposé de mauvaises lettres");
		} else {
			String s = mauvaisesLettres.size() < 2 ? "" : "s";
			System.out.printf("%nMauvaise%s lettre%s déja proposée%s : ", s, s, s);
			for (char lettre : mauvaisesLettres) {
				System.out.printf("%s, ", lettre);
			}
		}
		afficherPendu(mauvaisesLettres);
	}

	private static char proposerLettre() throws IOException {
		char proposition;
		System.out.printf("%nProposez une lettre%n");
		Scanner scan = new Scanner(System.in);
		proposition = scan.next().toUpperCase().charAt(0);
		return proposition;
	}

	private static boolean comparerLettres(char[] aTrouver, char lettre) throws IOException {
		boolean bonne = false;
		for (int i = 0; i < aTrouver.length; i++) {
			if (lettre == aTrouver[i]) {
				bonne = true;
			}
		}
		if (!bonne) {
			mauvaisesLettres.add(lettre);
			System.out.printf("Désolé, %s ne fait pas partie du mot à trouver%n", lettre);
		} else {
			System.out.printf("%s fait bien partie du mot à trouver%n", lettre);
			bonnesLettres.add(lettre);
		}
		return bonne;
	}

	
	public static void main(String[] args) throws IOException {
		jouer();
	}
}
