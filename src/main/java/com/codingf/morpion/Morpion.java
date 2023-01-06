package com.codingf.morpion;

import java.util.Scanner;

public class Morpion {
    public static void main(String[] args) {

        //Demandes les pseudos
        Scanner scan = new Scanner(System.in);
        System.out.println("\nJoueur 1 : Quel pseudo voulez-vous ?");
        String pseudoJoueur1 = scan.next();
        System.out.println("Joueur 2 : Quel pseudo voulez-vous ?");
        String pseudoJoueur2 = scan.next();

        //creation des 2 joueurs avec les pseudos donnés précédement
        Joueur joueur1 = new Joueur(pseudoJoueur1, "X");
        Joueur joueur2 = new Joueur(pseudoJoueur2, "O");

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