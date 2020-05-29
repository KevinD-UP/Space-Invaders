package entites;

import jeu.Main;
import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GroupeAliens {

    //VARIABLES
    private Alien tabAlien[][] = new Alien [5][10];
    private boolean vaADroite, pos1;
    private int vitesse;

    private int[] tabAlienMort = {-1, -1}; //Emplacement alien mort

    Random hasard = new Random();

    private int nombreAliens = Constantes.NOMBRE_ALIEN;
    private int compteurSonAlien = 0;

    //CONSTRUCTEUR
    public GroupeAliens(){
        this.initTableauALiens();
        this.vaADroite = true;
        this.pos1 = true;
        this.vitesse = Constantes.VITESSE_ALIEN;
    }

    private void initTableauALiens() {
        for(int colonne = 0; colonne < 10; colonne++){
            this.tabAlien[0][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN +
                    (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) *
                            colonne, Constantes.ALT_INIT_ALIEN,
                    "/images/alienHaut1.png",
                    "/images/alienHaut2.png");
            for(int ligne = 1; ligne < 3 ; ligne++){
                this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN +
                        (Constantes.LARGEUR_ALIEN+Constantes.ECART_COLONNES_ALIEN) *
                                colonne,
                        Constantes.ALT_INIT_ALIEN
                                + Constantes.ECART_LIGNES_ALIEN
                                * ligne, "/images/alienMilieu1.png",
                        "/images/alienMilieu2.png");
            }
            for(int ligne = 3; ligne < 5; ligne++){
                this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN +
                        (Constantes.LARGEUR_ALIEN+Constantes.ECART_COLONNES_ALIEN) *
                                colonne,
                        Constantes.ALT_INIT_ALIEN
                                + Constantes.ECART_LIGNES_ALIEN
                                * ligne, "/images/alienBas1.png",
                        "/images/alienBas2.png");
            }
        }
    }

    public void dessinAlien(Graphics g){
        if(Chrono.compteTours % (100-10*this.vitesse) == 0){
            this.deplacementAliens();
        }
        for(int colonne = 0; colonne < 10; colonne++){
            for(int ligne  = 0; ligne < 5; ligne++){
                if(this.tabAlien[ligne][colonne] != null) {
                    this.tabAlien[ligne][colonne].choixImage(pos1);
                    g.drawImage(this.tabAlien[ligne][colonne].getImg(),
                            this.tabAlien[ligne][colonne].getxPos(),
                            this.tabAlien[ligne][colonne].getyPos(), null);
                }
            }
        }
    }

    private boolean toucheBordGauche(){
        boolean reponse = false;
        for(int colonne = 0; colonne < 10; colonne ++){
            for(int ligne = 0; ligne < 5; ligne++){
                if(this.tabAlien[ligne][colonne] != null){
                    if(this.tabAlien[ligne][colonne].getxPos() < Constantes.MARGE_FENETRE) {
                        reponse = true;
                        break;
                    }
                }
            }
        }
        return reponse;
    }
    private boolean toucheBordDroit(){
        boolean reponse = false;
        for(int colonne = 0; colonne < 10; colonne ++){
            for(int ligne = 0; ligne < 5; ligne++){
                if(this.tabAlien[ligne][colonne] != null) {
                    if (this.tabAlien[ligne][colonne].getxPos() >
                            Constantes.LARGEUR_FENETRE - Constantes.LARGEUR_ALIEN - Constantes.DX_ALIEN - Constantes.MARGE_FENETRE) {
                        reponse = true;
                        break;
                    }
                }
            }
        }
        return reponse;
    }

    public void alienTourneEtDescend(){
        if(this.toucheBordDroit()){
            for(int colonne = 0; colonne < 10; colonne++){
                for(int ligne = 0; ligne < 5; ligne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne].setyPos((this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN));
                    }
                }
            }
            this.vaADroite = false;
            if(this.vitesse < 9){this.vitesse++;}
        }else{
            if(this.toucheBordGauche()){
                for(int colonne = 0; colonne < 10; colonne++){
                    for(int ligne = 0; ligne < 5; ligne++) {
                        if (this.tabAlien[ligne][colonne] != null) {
                            this.tabAlien[ligne][colonne].setyPos((this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN));
                        }
                    }
                }
                this.vaADroite = true ;
                if(this.vitesse < 9){this.vitesse++;}
            }
        }
    }

    public void deplacementAliens(){
        if(this.tabAlienMort[0] != -1){
            elimineAlienMort(tabAlienMort);
            tabAlienMort[0] = -1;
        }
        if(this.vaADroite){
            for(int colonne = 0; colonne < 10; colonne++){
                for(int ligne = 0; ligne < 5; ligne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne].setxPos((this.tabAlien[ligne][colonne].getxPos() + Constantes.DX_ALIEN));
                    }
                }
            }
        }else{
            for(int colonne = 0; colonne < 10; colonne++){
                for(int ligne = 0; ligne < 5; ligne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne].setxPos((this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN));
                    }
                }
            }
        }
        this.joueSonAlien();
        this.compteurSonAlien++;
        if(this.pos1){this.pos1 = false;}
        else{this.pos1 = true;}

        this.alienTourneEtDescend();
    }

    public void tirVaisseauToucheAlien(TirVaisseau tirVaisseau) {
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {
                if (this.tabAlien[ligne][colonne] != null) {
                    if (tirVaisseau.tueAlien(this.tabAlien[ligne][colonne])) {
                        this.tabAlien[ligne][colonne].vivant = false;
                        tirVaisseau.yPos = -1;
                        this.tabAlienMort[0] = ligne;
                        this.tabAlienMort[1] = colonne;
                        if(ligne == 0){
                            Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_HAUT;
                        }else if(ligne > 0 && ligne < 3){
                            Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_MILIEU;
                        }else{
                            Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_BAS;
                        }
                        break;
                    }
                }
            }
        }
    }

    private void elimineAlienMort(int[] tabAlienMort){
        this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
        this.nombreAliens--;
    }

    public int[] choixAlienQuiTire(){
        int positionAlien[] = {-1, -1};
        if(this.nombreAliens != 0){
            do{
                int colonne = hasard.nextInt(10);
                for(int ligne = 4; ligne >=0; ligne--){
                    if(tabAlien[ligne][colonne] != null){
                        positionAlien[0] = this.tabAlien[ligne][colonne].getxPos();
                        positionAlien[1] = this.tabAlien[ligne][colonne].getyPos();
                        break;
                    }
                }
            }while(positionAlien[0] == -1);
        }
        return positionAlien;
    }

    private void joueSonAlien(){
        int compteur = this.compteurSonAlien % 4;
        if(compteur == 0)
            Audio.playSound("/sons/sonAlien1.wav");
        else if (compteur == 1)
            Audio.playSound("/sons/sonAlien2.wav");
        else if (compteur == 2)
            Audio.playSound("/sons/sonAlien3.wav");
        else
            Audio.playSound("/sons/sonAlien4.wav");
    }

    public int getNombreAliens(){return nombreAliens;}

    public int positionAlienLePlusBas(){
        int posBas =0, posBasFinal = 0;
        for(int colonne = 1; colonne < 10; colonne++){
            for(int ligne = 4; ligne>=0; ligne--){
                if(this.tabAlien[ligne][colonne] != null){
                    posBas = this.tabAlien[ligne][colonne].yPos + this.tabAlien[ligne][colonne].hauteur;
                    break;
                }
            }
            if(posBas > posBasFinal){posBasFinal = posBas;}
        }
        return posBasFinal;
    }

}
