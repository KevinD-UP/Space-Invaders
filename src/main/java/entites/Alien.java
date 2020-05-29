package entites;

import ressources.Constantes;

import javax.swing.*;

public class Alien extends Entite{

    //VARIABLES

    //CONSTRUCTEUR

    public Alien(int xPos, int yPos, String strImg1, String strImg2){

        super.xPos = xPos;
        super.yPos = yPos;
        super.largeur = Constantes.LARGEUR_ALIEN;
        super.hauteur = Constantes.HAUTEUR_ALIEN;
        super.dx = 0;
        super.dy = 0;
        super.vivant = true;
        super.strImg1 = strImg1;
        super.strImg2 = strImg2;
        super.strImg3 = "/images/alienMeurt.png";

        super.ico = new ImageIcon((getClass().getResource(super.strImg1)));
        super.img = this.ico.getImage();
    }

    //METHODES
    public void choixImage(boolean pos1){
        if(this.vivant){
            if(pos1){
                super.ico = new ImageIcon(getClass().getResource(strImg1));
            }else{
                super.ico = new ImageIcon(getClass().getResource(strImg2));
            }
        }else {
            super.ico = new ImageIcon(getClass().getResource(strImg3));
        }
        super.img = this.ico.getImage();
    }

}
