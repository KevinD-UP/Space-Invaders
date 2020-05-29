package entites;

import jeu.Main;
import ressources.Chrono;
import ressources.Constantes;

import javax.swing.*;
import java.awt.*;

public class Vaisseau extends Entite{

    //VARIABLES
    private int compteur = 0;

    //CONSTRUCTEURS
    public Vaisseau(){

        super.xPos = Constantes.X_POS_INIT_VAISSEAU;
        super.yPos = Constantes.Y_POS_VAISSEAU;
        super.largeur = Constantes.LARGEUR_VAISSEAU;
        super.hauteur = Constantes.HAUTEUR_VAISSEAU;
        super.dx = 0;
        super.dy = 0;

        super.strImg1 = "/images/vaisseau.png";
        super.strImg2 = "/images/vaisseauDetruit1.png";
        super.strImg3 = "/images/vaisseauDetruit2.png";

        super.ico = new ImageIcon((getClass().getResource(super.strImg1)));
        super.img = this.ico.getImage();
        super.vivant = true;
    }

    //METHODES
    public int deplacementVaisseau(){
        if(this.dx < 0){
            if(this.xPos > Constantes.LIMITE_GAUCHE_VAISSEAU){
                this.xPos = this.xPos + this.dx;
            }
        }else if(this.dx > 0){
            if(this.xPos+this.dx < Constantes.LIMITE_DROITE_VAISSEAU){
                this.xPos = this.xPos + this.dx;
            }
        }
        return this.xPos;
    }

    public void dessinVaisseau(Graphics g){
        if(!this.vivant)
            destructionVaisseau();
        g.drawImage(this.img, this.deplacementVaisseau(), this.yPos, null);
    }

    public void destructionVaisseau(){
        if(compteur < 300){
            if(Chrono.compteTours % 2 == 0){
                super.ico = new ImageIcon(getClass().getResource(super.strImg2));
            }else{
                super.ico = new ImageIcon(getClass().getResource(super.strImg3));
            }
            compteur++;
        }else{
              Main.jeu = false;
        }
        super.img = this.ico.getImage();
    }

}
