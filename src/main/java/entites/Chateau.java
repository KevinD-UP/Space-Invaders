package entites;

import ressources.Audio;
import ressources.Constantes;

import java.awt.*;

public class Chateau extends Entite{

    //VARIABLES
    private final int NBRE_LIGNES = Constantes.HAUTEUR_CHATEAU/Constantes.DIMENSION_BRIQUE;
    private final int NBRE_COLONNES = Constantes.LARGEUR_CHATEAU/Constantes.DIMENSION_BRIQUE;

    boolean tabChateau[][] = new boolean[NBRE_LIGNES][NBRE_COLONNES];

    //CONSTRUCTEURS
    public Chateau(int xPos){
        super.xPos = xPos;
        super.yPos = Constantes.Y_POS_CHATEAU;

        this.initTabChateau();
    }

    //METHODES
    public void initTabChateau(){
        for(int ligne = 0; ligne < NBRE_LIGNES; ligne++){
            for(int colonne = 0; colonne < NBRE_COLONNES; colonne++){
                tabChateau[ligne][colonne] = true;
            }
        }

        for(int colonne = 0; colonne < 6; colonne++){
            for (int ligne = 0; ligne<2; ligne++){
                tabChateau[ligne][colonne]=false;
                tabChateau[ligne][NBRE_COLONNES-colonne-1]=false;
            }
        }

        for(int colonne = 0; colonne < 4; colonne++){
            for (int ligne = 2; ligne < 4; ligne++){
                tabChateau[ligne][colonne]=false;
                tabChateau[ligne][NBRE_COLONNES-colonne-1]=false;
            }
        }

        for(int colonne = 0; colonne < 2; colonne++){
            for (int ligne = 4; ligne < 6; ligne++){
                tabChateau[ligne][colonne]=false;
                tabChateau[ligne][NBRE_COLONNES-colonne-1]=false;
            }
        }

        //ENTREE
        for(int ligne = 10; ligne < NBRE_LIGNES; ligne++){
            for(int colonne = 10; colonne < NBRE_COLONNES-10; colonne++){
                tabChateau[ligne][colonne] = false;
            }
        }

        for(int colonne = 12; colonne < NBRE_COLONNES - 12; colonne++){
            for (int ligne = 16; ligne < 10; ligne++){
                tabChateau[ligne][colonne]=false;
                tabChateau[ligne][NBRE_COLONNES-colonne-1]=false;
            }
        }
    }

    public void dessinChateau(Graphics g2){
        for(int ligne = 0; ligne < NBRE_LIGNES; ligne++){
            for(int colonne = 0; colonne < NBRE_COLONNES; colonne++){
                if(tabChateau[ligne][colonne] == true){
                    g2.setColor(Color.GREEN);
                }else{
                    g2.setColor(Color.BLACK);
                }
                g2.fillRect(this.xPos + Constantes.DIMENSION_BRIQUE*colonne, this.yPos + Constantes.DIMENSION_BRIQUE*ligne, Constantes.DIMENSION_BRIQUE, Constantes.DIMENSION_BRIQUE);
            }
        }
    }

    public int trouveColonneChateau(int xMissile){
        int colonne = -1;
        colonne = (xMissile - this.xPos)/Constantes.DIMENSION_BRIQUE;
        return colonne;
    }

    public int trouveBrique(int colonne){
        int ligne = NBRE_LIGNES-1;
        while(ligne >=0 && !tabChateau[ligne][colonne]){
            ligne--;
        }
        return ligne;
    }

    private void enleveBriques(int ligne, int colonne){
        for(int compteur = 0; compteur < 6; compteur++){
            if(ligne - compteur >= 0){
                tabChateau[ligne - compteur][colonne] = false;
                if(colonne < NBRE_COLONNES-1){
                    tabChateau[ligne-compteur][colonne + 1] = false;
                }
            }
        }
    }

    public void casseBrique(int xTir){
        Audio.playSound("/sons/sonCasseBrique.wav");
        int colonne = this.trouveColonneChateau(xTir);
        this.enleveBriques(trouveBrique(colonne), colonne);
    }

    public int trouveBriqueHaut(int colonne){
        int ligne  = 0;
        if(colonne != -1){
            while(ligne < NBRE_LIGNES && !tabChateau[ligne][colonne]){
                ligne++;
            }
        }
        return ligne;
    }

    private void enleveBriquesHaut(int ligne, int colonne){
        for(int compteur = 0; compteur < 6; compteur++){
            if(ligne + compteur < NBRE_LIGNES && colonne != -1){
                tabChateau[ligne+compteur][colonne] = false;
                if(colonne < NBRE_COLONNES - 1){
                    tabChateau[ligne + compteur][colonne + 1] = false;
                }
            }
        }
    }

    public void casseBriqueHaut(int xTir){
        Audio.playSound("/sons/sonCasseBrique.wav");
        int colonne = this.trouveColonneChateau(xTir);
        this.enleveBriquesHaut(trouveBriqueHaut(colonne), colonne);
    }

}
