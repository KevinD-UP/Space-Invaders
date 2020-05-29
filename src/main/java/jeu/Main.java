package jeu;

import ressources.Constantes;

import javax.swing.*;

public class Main {

    //VARIABLES
    public static Scene scene;
    public static boolean jeu = true;

    //METHODES

    public static void main(String[] args){

        JFrame fenetre = new JFrame("Space Invaders");
        fenetre.setSize(Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setAlwaysOnTop(true);

        scene = new Scene();
        fenetre.setContentPane(scene);
        fenetre.setVisible(true);



    }

}
