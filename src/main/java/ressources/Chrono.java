package ressources;

import jeu.Main;

public class Chrono implements Runnable{

    //VARIABLES
    private final int PAUSE = 5; //Temps d'attente entre 2 tours de boucle 5ms
    public static int compteTours = 0;

    //METHODES

    public void run(){
        while(Main.jeu){
            compteTours++;
            Main.scene.repaint(); //appel PaintComponent
            try {
                Thread.sleep(PAUSE); //temps de pause(5ms)
            }catch (InterruptedException e){

            }
        }
    }

}
