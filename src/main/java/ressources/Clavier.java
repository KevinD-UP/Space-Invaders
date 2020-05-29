package ressources;

import jeu.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {


    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(Main.scene.vaisseau.isVivant()) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Main.scene.vaisseau.setDx(Constantes.DX_VAISSEAU);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Main.scene.vaisseau.setDx(-Constantes.DX_VAISSEAU);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!Main.scene.tirVaisseau.isVaisseauTire()) {
                    Audio.playSound("/sons/sonTirVaisseau.wav");
                    Main.scene.tirVaisseau.setyPos(Constantes.Y_POS_VAISSEAU - Constantes.HAUTEUR_TIR_VAISSEAU);
                    Main.scene.tirVaisseau.setxPos(Main.scene.vaisseau.getxPos() + Constantes.LARGEUR_VAISSEAU / 2 - 1);
                    Main.scene.tirVaisseau.setVaisseauTire(true);
                }
            }
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.vaisseau.setDx(0);
    }
}
