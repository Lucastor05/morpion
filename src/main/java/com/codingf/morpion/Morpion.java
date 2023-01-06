package com.codingf.morpion;

import java.util.Scanner;

public class Morpion {
    public static void main(String[] args) {


        System.out.println("Vous êtes sur le point de jouer au Morpion." +
                "\n\nLes règles du jeu sont simples: Allignez votre signe sur toute la longueur du tableau en ligne, colonnes, ou en diagonales. \n" +
                "\n                   Bonne partie!");


        //Demandes les pseudos
        Scanner scan = new Scanner(System.in);
        Scanner scanCara1 = new Scanner(System.in);
        Scanner scanCara2 = new Scanner(System.in);
        System.out.println("\nJoueur 1 : Quel pseudo voulez-vous ?");
        String pseudoJoueur1 = scan.next();
        System.out.println("Joueur 2 : Quel pseudo voulez-vous ?");
        String pseudoJoueur2 = scan.next();

        String cara1 = "X";
        String cara2 = "O";

        //Demandes les pseudos
        System.out.println("\n\nVoulez vous personnaliser vos symboles ? \n1-Oui\n2-Non");
        int choix = scan.nextInt();


        if(choix == 1){
            boolean caraBon = false;

            while(!caraBon){
                System.out.println("Joueur 1 : Quel symbole voulez-vous ?");
                cara1 = scanCara1.nextLine();

                if(cara1 == "." || cara1.length() != 1){
                    System.out.println("\nSymbole non valide! Veuillez réessayer !");
                    caraBon = false;
                }else{
                    caraBon = true;
                }

            }

            caraBon = false;

            while(!caraBon) {
                while (!caraBon) {
                    System.out.println("Joueur 2 : Quel symbole voulez-vous ?");
                    cara2 = scanCara2.nextLine();

                    if (cara2 == "." || cara2.length() != 1) {
                        caraBon = false;
                    } else {
                        caraBon = true;
                    }

                }

                if (cara1.equals(cara2)) {
                    System.out.println("\nSymbole non valide! Veuillez réessayer !");
                    caraBon = false;
                } else {
                    caraBon = true;
                }
            }
        }

        //creation des 2 joueurs avec les pseudos donnés précédement
        Joueur joueur1 = new Joueur(pseudoJoueur1, cara1);
        Joueur joueur2 = new Joueur(pseudoJoueur2, cara2);

        //demande la taille voulue du tableau
        System.out.println("Quel taille voulez-vous que le morpion fasse ? (taille de base -> 3)");
        int taille = scan.nextInt();

        //creation du jeu avec les joueurs et la taille
        Plateau tab = new Plateau(joueur1, joueur2, taille);

        //lancement du jeu
        tab.jouer(scan);

        //fermeture du scan
        scan.close();
    }
}