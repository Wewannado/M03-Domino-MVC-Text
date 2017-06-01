/*
 * Author Roger G. Coscojuela
 */
package ia;

import com.sun.media.jfxmedia.logging.Logger;
import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Torn;
import java.util.List;

/**
 *
 * @author Roger G. Coscojuela
 */
public class Ia {

    //Basic IA for Domino Game.
    Joc joc;
    Torn torn;

    public Ia(Joc joc, Torn torn) {
        this.joc = joc;
        this.torn = torn;
    }

    /**
     * Cridant a aquest metode, la IA intenta llençar una fitxa al tauler, si no
     * pot, crida automaticament al metode passar de joc.
     */
    public void jugar() {
        List<Fitxa> llistaFitxes = joc.getJugadors()[joc.getTorn()].getFitxes();
        Boolean haTirat = false;
        for (Fitxa llistaFitxe : llistaFitxes) {
            if (torn.colocarUnaFitxa(llistaFitxe, true)) {
                haTirat = true;
                Logger.logMsg(Logger.DEBUG, "IA:" + joc.getTorn() + "-> Tiro Fitxa: " + llistaFitxe.toString());
                break;
            } else if (torn.colocarUnaFitxa(llistaFitxe, true)) {
                haTirat = true;
                Logger.logMsg(Logger.DEBUG, "IA:" + joc.getTorn() + "-> Tiro Fitxa: " + llistaFitxe.toString());
                break;
            }
        }
        //Si la IA a intentat tirar totes les fitxes que te hi no ho ha aconseguit, passa el torn
        if (!haTirat) {
            torn.passar();
            Logger.logMsg(Logger.DEBUG, "IA:" + joc.getTorn() + "-> Passo");
            System.out.println();
        }

    }

}
