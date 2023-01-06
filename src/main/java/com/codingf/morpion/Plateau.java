package com.codingf.morpion;
import com.codingf.morpion.Case;
import com.codingf.morpion.Joueur;

import java.util.Scanner;

public class Plateau {

    //Initialisation des variables
    Case[][] cases;
    Joueur joueur1;
    Joueur joueur2;
    Joueur joueurActuel;
    int taille;
    int compteurTour = 0;

    public Plateau(Joueur joueur1, Joueur joueur2, int taille) {
        //Constructor de la classe
        this.cases = new Case[taille][taille];
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurActuel = joueur2;
        this.taille = taille;

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.cases[i][j] = new Case(i, j, ".");
            }
        }
    }
    public void jouer(Scanner sc) {

        //Initialisation des variables
        this.afficher();
        boolean restart = true;
        boolean valide = true;

        while (restart) { //si le joueur veux rejouer


            for(int i = 0; i<=this.taille-1; i++){//pour chaque ligne du tableau
                for(int j = 0; j<=this.taille-1; j++){ //pour chaque case du tableau
                    //remise a 0 des valeurs du tableau
                    this.cases[i][j].valeur = ".";
                }
            }

            while(!this.grillePleine() || !this.verification()) { //tant que aucun joueur n'as gagné ou que la grille n'est pas pleine
                if(!verification() && !this.grillePleine()) { //si aucun joueur n'as gagné ou que la grille n'est pas pleine
                    if(this.joueurActuel.equals(this.joueur1)){ //si le joueur actuel est le joueur 1
                        //changé joueur actuel pour joueur 2
                        this.joueurActuel = this.joueur2;
                    }else{ //sinon si joueur actuel est le joueur 2
                        //changé joueur actuel pour joueur 1
                        this.joueurActuel = this.joueur1;
                    }


                    System.out.println("\nAu Tour de "+ this.joueurActuel.pseudo + " !\nA quelle ligne voulez-vous jouer ?");
                    int ligne = sc.nextInt();
                    System.out.println("\nA quelle colonne voulez-vous jouer ?");
                    int colonne = sc.nextInt();


                    if(!this.cases[ligne - 1][colonne - 1].valeur.equals(".")){ //si la case n'est pas vide
                        //mettre variables valide a false
                        valide = false;
                    }

                    while(!valide){ //si la case choisi par l'utilisateur n'est pas valide
                        //redemmander les lignes et les colonnes
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t***Coordonnées invalides, veuillez entrer une position qui n'a pas deja ete prise!***");
                        System.out.println("\nAu Tour de "+ this.joueurActuel.pseudo + " !\nA quelle ligne voulez-vous jouer ?");
                        ligne = sc.nextInt();
                        System.out.println("\nA quelle colonne voulez-vous jouer ?");
                        colonne = sc.nextInt();

                        if(!this.cases[ligne - 1][colonne - 1].valeur.equals(".")){ //verifier la validité des nouvelles coordonnées
                            valide = false;
                        }else{
                            valide = true;
                        }
                    }

                    this.cases[ligne - 1][colonne - 1].valeur = this.joueurActuel.caractere; //change la valeur de la case pour X ou O celon le joueur
                    this.compteurTour += 1;
                    this.afficher(); //affiche la grille

                }else{break;}
            }

            if (!this.verification() && this.grillePleine()) { //si aucun joueur n'as gagné et que la grille est pleine
                //affiche ex aequo
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t***Match nul !***");
                this.compteurTour = 0;
            } else if (this.verification()) { //Si un joueur a gagné
                //affiche message de félicitation
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t***Félicitation " + this.joueurActuel.pseudo + ", tu as gagné !***");
                this.compteurTour = 0;
            }

            //Demande si le joueur veux rejouez une partie
            System.out.println("\n\nVoulez vous recommencez ? \n1-Oui\n2-Non");
            int recommencer = sc.nextInt();

            if(recommencer == 1) { //si recommencer est egal a 1 -> rejouez
                restart = true;
                this.joueurActuel = this.joueur2;
            } else if (recommencer == 2) {//si recommencer est egal a 2 -> finir le programme
                restart = false;
            }
        }
    }
    public void afficher() {
        /*
         * Fonction qui affiche la grille du morpion (à jour par rapport au jeu).
         */
        for (int i = 0; i < this.taille; i++){ //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut au ligne
            for (int j = 0; j < this.taille; j++) { //une boucle avec j qui a pour valeur la taille du tableau, se qui equivaut au colonne (au case dans i)
                if(i==0){ //si 1 ligne
                    if(j==0){ //si premiere case
                        /*affiche (ex taille:3) soit le rendu dans la console est:
                        * ┌ ─ ─ ─ ┬ ───┬───┐
                        * │ . │
                        */
                        System.out.print("\t\t\t\t\t\t\t\t    ");
                        for(int y=1; y<=this.taille; y++){
                            System.out.printf(y+"   ");
                        }
                        System.out.print("\n\t\t\t\t\t\t\t\t  ┌───┬");
                        for(int x = 0; x < this.taille-2; x++){
                            System.out.print("───┬");
                        }

                        System.out.print("───┐\n");
                        System.out.print("\t\t\t\t\t\t\t\t"+(i+1)+" │ "+this.cases[i][j].valeur + " │");
                    }else { //sinon afficher les autres case

                        /*Affiche la suite d'au dessus soit le rendu dans la console est:
                        * ┌───┬───┬───┐
                        * │ . │ . │ . │
                        */
                        System.out.print(" "+this.cases[i][j].valeur + " │");
                    }
                }else{ //si c'est pas la premiere ligne affiche la suite
                    if(j==0){ //si premiere case
                        /*affiche (ex taille:3) soit le rendu dans la console est:
                         * ┌───┬───┬───┐
                         * │ . │ . │ . │
                         * ├───┼───┼───┤
                         * │ . │
                         */
                        System.out.print("\t\t\t\t\t\t\t\t  ├───┼");
                        for(int x = 0; x < this.taille-2; x++){
                            System.out.print("───┼");
                        }
                        System.out.print("───┤\n");
                        System.out.print("\t\t\t\t\t\t\t\t"+(i+1)+" │ "+this.cases[i][j].valeur + " │");
                    }else {
                        /*Affiche la suite d'au dessus soit le rendu dans la console est:
                         * ┌───┬───┬───┐
                         * │ . │ . │ . │
                         * ├───┼───┼───┤
                         * │ . │ . │ . │
                         * ├───┼───┼───┤
                         * │ . │ . │ . │
                         */
                        System.out.print(" "+this.cases[i][j].valeur + " │");
                    }
                }
            }
            //fait un retour a la ligne pour afficher la prochaine ligne
            System.out.print("\n");

        }
        /*Affiche la derniere ligne soit le rendu dans la console est:
         * ┌───┬───┬───┐
         * │ . │ . │ . │
         * ├───┼───┼───┤
         * │ . │ . │ . │
         * ├───┼───┼───┤
         * │ . │ . │ . │
         * └───┴───┴───┘
         */
        System.out.print("\t\t\t\t\t\t\t\t  └───┴");
        for(int x = 0; x < this.taille-2; x++){
            System.out.print("───┴");
        }
        System.out.print("───┘");
    }
    public boolean verifHorizontal() {
        /*
         * Fonction qui vérifie si un joueur à gagner dans le sens horizontal
         * return un booléen, true si un joueur à gagner et false si aucun joueur n'as aligner une ligne.
         */

        //Initialisation des variables compteurs
        int compteurO = 0;
        int compteurX = 0;

        for (int i = 0; i < this.taille; i++){ //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut au ligne
            for (int j = 0; j < this.taille; j++) { //une boucle avec j qui a pour valeur la taille du tableau, se qui equivaut au colonne (au case dans i)
                if (this.cases[i][j].valeur.equals(".")) { //si la case est vide on arrete
                    break;
                } else if (this.cases[i][j].valeur.equals(this.joueur1.caractere)) {//si la case est égal à un X
                    //Incrementation du compteur des X et remise à 0 du compteur des O
                    compteurX += 1;
                    compteurO = 0;
                } else if (this.cases[i][j].valeur.equals(this.joueur2.caractere)) { //si la case est égal à un O
                    //Incrementation du compteur des O et remise à 0 du compteur des X
                    compteurO += 1;
                    compteurX = 0;
                }
            }
            if(compteurX == this.taille || compteurO == this.taille){ //verifie si le joueur a gagner
                return true;
            }else{ //si le joueur n'as pas gagner, remise a 0 pour checker la prochaine ligne
                compteurO = 0;
                compteurX=0;
            }
        }
        return false;
    }
    public boolean verifVertical() {
        /*
         * Fonction qui vérifie si un joueur à gagner dans le sens Vertical
         * return un booléen, true si un joueur à gagner et false si aucun joueur n'as aligner une ligne.
         */

        int compteurO = 0;
        int compteurX = 0;

        for (int j = 0; j < this.taille; j++){ //une boucle avec j qui a pour valeur la taille du tableau, se qui equivaut aux colonnes (au case dans i)
            for (int i = 0; i < this.taille; i++) { //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut aux lignes

                if (this.cases[i][j].valeur.equals(".")) {//si la case est vide on arrete
                    break;
                } else if (this.cases[i][j].valeur.equals(this.joueur1.caractere)) {//si la case est égal à un X
                    //Incrementation du compteur des X et remise à 0 du compteur des O
                    compteurX += 1;
                    compteurO = 0;
                } else if (this.cases[i][j].valeur.equals(this.joueur2.caractere)) {//si la case est égal à un O
                    //Incrementation du compteur des O et remise à 0 du compteur des X
                    compteurO += 1;
                    compteurX = 0;
                }
            }
            if(compteurX == this.taille || compteurO == this.taille){ //verifie si le joueur a gagner
                return true;
            }else{ //si le joueur n'as pas gagner, remise a 0 pour checker la prochaine ligne
                compteurO = 0;
                compteurX=0;
            }
        }
        return false;
    }
    public boolean verifDiagonal(){
        /*
         * Fonction qui vérifie si un joueur à gagner dans le sens diagonal du haut à gauche au bas à droite
         * return un booléen, true si un joueur à gagner et false si aucun joueur n'as aligner une ligne.
         */

        //Initialisation des variables compteurs
        int i = 0;
        int compteurO = 0;
        int compteurX = 0;

        for (int j = 0; j < this.taille; j++) { //une boucle avec j qui a pour valeur la taille du tableau, se qui equivaut au case
            if (this.cases[i][j].valeur.equals(".")) { //si la case est vide on arrete
                return false;
            } else if (this.cases[i][j].valeur.equals(this.joueur1.caractere)) {//si la case est égal à un X
                //Incrementation du compteur des O et remise à 0 du compteur des O
                compteurX += 1;
                compteurO = 0;
                i+=1;
            } else if (this.cases[i][j].valeur.equals(this.joueur2.caractere)) {//si la case est égal à un O
                //Incrementation du compteur des O et remise à 0 du compteur des X
                compteurX = 0;
                compteurO += 1;
                i+=1;
            }
            if (compteurX == this.taille || compteurO == this.taille) {//verifie si le joueur a gagner
                return true;
            }
        }
        return false;
    }
    public boolean verifDiagonal2() {
        /*
         * Fonction qui vérifie si un joueur à gagner dans le sens diagonal du bas à gauche au haut à droite
         * return un booléen, true si un joueur à gagner et false si aucun joueur n'as aligner une ligne.
         */

        //Initialisation des variables
        int j = this.taille-1;
        int compteurO = 0;
        int compteurX = 0;

        for (int i = 0; i < this.taille; i++){ //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut au ligne
            if (this.cases[i][j].valeur.equals(".")) {//si la case est vide on arrete
                return false;
            } else if (this.cases[i][j].valeur.equals(this.joueur1.caractere)) {//si la case est égal à un X
                //Incrementation du compteur des O et remise à 0 du compteur des O
                compteurX += 1;
                compteurO = 0;
                j-=1;
            } else if (this.cases[i][j].valeur.equals(this.joueur2.caractere)) {//si la case est égal à un O
                //Incrementation du compteur des O et remise à 0 du compteur des X
                compteurO += 1;
                compteurX = 0;
                j-=1;
            }
            if (compteurX == this.taille || compteurO == this.taille) {//verifie si le joueur a gagner
                return true;
            }
        }
        return false;
    }
    public boolean verification(){
        /*
         * Fonction qui vérifie si un joueur à gagner
         * return un booléen, true si un joueur à gagner et false si aucun joueur n'as aligner une ligne.
         */
        if(verifDiagonal() ||verifDiagonal2()|| verifHorizontal() || verifVertical()){ //utilise les différentes fonctions de verification pour savoir si un joueur à gagné.
            return true;
        }
        return false;
    }
    public boolean grillePleine(){
        /*
         * Fonction qui vérifie si la grille est pleine
         * return un booléen, true si la grille est pleine et false si elle ne l'est pas.
         */
        int i=0;
        int j=0;
        while(i!=this.taille){
            while(j!=this.taille){
                if(this.cases[i][j].valeur.equals(".")){
                    return false;
                }
                j+=1;
            }
            i+=1;
            j=0;
        }
        return true;
    }
}
