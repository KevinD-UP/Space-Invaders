package jeu;

import entites.*;
import ressources.Chrono;
import ressources.Clavier;
import ressources.Constantes;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {

    //VARIABLES
    public Vaisseau vaisseau = new Vaisseau();
    public GroupeAliens groupeAliens = new GroupeAliens();
    public TirVaisseau tirVaisseau = new TirVaisseau();

    public Chateau[] tabChateaux = new Chateau[4];

    public TirAlien tirAlien1, tirAlien2, tirAlien3;

    public Soucoupe soucoupe;

    private Font afficheScore = new Font("Arial", Font.PLAIN, 20);
    private Font afficheTexte = new Font("Arial", Font.PLAIN, 70);

    public int score;


    //CONSTRUCTEUR
    public Scene(){
        super();

        //Instanciation des châteaux
        for(int colonne = 0; colonne < 4; colonne++){
            this.tabChateaux[colonne] =
                new Chateau(Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU
                    + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU));
        }

        //Instanciation du clavier
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        //Instanciation du chrono
        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();

    }


    //METHODES

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        //Dessin du fond d'écran.
        g2.setColor(Color.black);
        g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);

        //Dessin ligne verte
        g2.setColor(Color.green);
        g2.fillRect(30, 530 ,535, 5 );

        //Affichage du score
        g.setFont(afficheScore);
        g.drawString("SCORE: " + score, 400, 25);

        //Dessin du vaisseau
        this.vaisseau.dessinVaisseau(g2);

        //Dessin des Aliens
        this.groupeAliens.dessinAlien(g2);

        //Dessin du tir vaisseau
        this.tirVaisseau.dessinTirVaisseau(g2);

        //Detection contact tir avec alien
        this.groupeAliens.tirVaisseauToucheAlien(this.tirVaisseau);

        //Dessin des chateaux
        for(int colonne=0; colonne < 4; colonne++){
            this.tabChateaux[colonne].dessinChateau(g2);
        }

        //Message de début
        if(Chrono.compteTours < 500){
            g.setFont(afficheTexte);
            g.drawString("Space Invaders" ,25 ,100);
        }

        //Détection contact tirVaisseau avec château
        this.tirVaisseau.tirVaisseauDetruitChateau(tabChateaux);

        //Dessin des tirs des aliens
        if(Chrono.compteTours % 500 == 0){
            tirAlien1 = new TirAlien(this.groupeAliens.choixAlienQuiTire());
        }
        if(this.tirAlien1 != null){
            this.tirAlien1.dessinTirAlien(g2);
            this.tirAlien1.tirAlienDetruitChateau(tabChateaux);
            if(this.tirAlien1.toucheVaisseau(vaisseau))
                this.vaisseau.setVivant(false);
        }
        if(Chrono.compteTours % 750 == 0){
            tirAlien2 = new TirAlien(this.groupeAliens.choixAlienQuiTire());
        }
        if(this.tirAlien2 != null){
            this.tirAlien2.dessinTirAlien(g2);
            this.tirAlien2.tirAlienDetruitChateau(tabChateaux);
            if(this.tirAlien2.toucheVaisseau(vaisseau))
                this.vaisseau.setVivant(false);
        }
        if(Chrono.compteTours % 900 == 0){
            tirAlien3 = new TirAlien(this.groupeAliens.choixAlienQuiTire());
        }
        if(this.tirAlien3 != null){
            this.tirAlien3.dessinTirAlien(g2);
            this.tirAlien3.tirAlienDetruitChateau(tabChateaux);
            if(this.tirAlien3.toucheVaisseau(vaisseau))
                this.vaisseau.setVivant(false);
        }

        //Dessin de la soucoupe
        if (Chrono.compteTours % 2500 == 0){
            soucoupe = new Soucoupe();
        }
        if(this.soucoupe != null){
            if(this.soucoupe.getxPos()>0){
                if(this.tirVaisseau.detruitSoucoupe(this.soucoupe)){
                    if(this.soucoupe.getDx() != 0){
                        this.score += Constantes.VALEUR_SOUCOUPE;
                    }
                    this.soucoupe.setDx(0);
                    this.soucoupe.setVivant(false);
                    this.soucoupe.musiqueSoucoupe.stop();
                    this.soucoupe.musiqueSoucoupe.play();
                }
                this.soucoupe.dessinSoucoupe(g2);
            }else{
                this.soucoupe = null;
            }
        }

        if(!this.vaisseau.isVivant()){
            g.setFont(afficheTexte);
            g.drawString("Git Gut Noob", 70, 100);
        }

        if(this.groupeAliens.getNombreAliens() == 0){groupeAliens = new GroupeAliens();}

        if(this.groupeAliens.positionAlienLePlusBas()> Constantes.Y_POS_VAISSEAU){this.vaisseau.destructionVaisseau();}

    }
}
