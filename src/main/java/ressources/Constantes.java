package ressources;

public abstract class Constantes {

    //Dimension de la fenêtre
    public static final int LARGEUR_FENETRE = 600;
    public static final int HAUTEUR_FENETRE = 600;
    public static final int MARGE_FENETRE = 50;

    //Dimension du vaisseau
    public static final int LARGEUR_VAISSEAU = 39;
    public static final int HAUTEUR_VAISSEAU = 24;

    //Position initiale du vaisseau
    public static final int X_POS_INIT_VAISSEAU = (LARGEUR_FENETRE - LARGEUR_VAISSEAU)/2;
    public static final int Y_POS_VAISSEAU = 490;

    //unité de déplacement du vaisseau
    public static final int DX_VAISSEAU = 1;

    //Limite de déplacemnt du vaisseau
    public final static int LIMITE_GAUCHE_VAISSEAU = 60;
    public final static int LIMITE_DROITE_VAISSEAU = 500;

    //Dimension de l'alien
    public static final int LARGEUR_ALIEN = 33;
    public static final int HAUTEUR_ALIEN = 25;

    //Paramètre de position des aliens
    public static final int ALT_INIT_ALIEN = 120;
    public static final int X_POS_INIT_ALIEN = 29 + MARGE_FENETRE;
    public static final int ECART_LIGNES_ALIEN = 40;
    public static final int ECART_COLONNES_ALIEN = 10;

    //Unité de déplacement de l'alien
    public static final int DX_ALIEN = 2;
    public static final int DY_ALIEN = 20;
    public final static int VITESSE_ALIEN = 1;

    //Nombre total d'alien
    public final static int NOMBRE_ALIEN = 50;

    //Dimension du tir
    public final static int LARGEUR_TIR_VAISSEAU = 3;
    public final static int HAUTEUR_TIR_VAISSEAU = 13;

    //Unité de déplacement du tir
    public final static int DY_TIR_VAISSEAU = 2;

    //Dimension de la brique
    public static final int DIMENSION_BRIQUE = 2;

    //Dimension du château
    public static final int LARGEUR_CHATEAU = 72;
    public static final int HAUTEUR_CHATEAU = 54;

    //Paramètres de position des châteaux
    public static final int Y_POS_CHATEAU = 400;
    public static final int X_POS_INIT_CHATEAU = 39;
    public static final int ECART_CHATEAU = 42;

    //Dimension du tir alien
    public final static int LARGEUR_TIR_ALIEN = 5;
    public final static int HAUTEUR_TIR_ALIEN= 15;

    //Unité de déplacement du tir alien
    public final static int DY_TIR_ALIEN= 3;

    //Caractéristique de la soucoupe
    public static final int X_POS_INIT_SOUCOUPE = LARGEUR_FENETRE;
    public static final int Y_POS_SOUCOUPE = 50;
    public static final int LARGEUR_SOUCOUPE = 42;
    public static final int HAUTEUR_SOUCOUPE = 22;
    public static final int DX_SOUCOUPE = 1;

    //Point attribué
    public static final int VALEUR_ALIEN_HAUT = 50;
    public static final int VALEUR_ALIEN_MILIEU = 40;
    public static final int VALEUR_ALIEN_BAS = 20;
    public static final int VALEUR_SOUCOUPE = 100;

}
