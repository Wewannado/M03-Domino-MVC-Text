/*
 * Author Roger G. Coscojuela
 */
package ia;

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

    public void jugar() {
        List<Fitxa> llistaFitxes = joc.getJugadors()[joc.getTorn()].getFitxes();
        Boolean haTirat = false;
        for (Fitxa llistaFitxe : llistaFitxes) {
            if (torn.colocarUnaFitxa(llistaFitxe, true)) {
                haTirat = true;
                System.out.println("IA:"+joc.getTorn() +"Tiro Fitxa:"+ llistaFitxe.toString());
                break;
            } else if (torn.colocarUnaFitxa(llistaFitxe, true)) {
                haTirat = true;
                System.out.println("IA:"+joc.getTorn() +"Tiro Fitxa:"+ llistaFitxe.toString());
                break;
            }
        }
        if (!haTirat) {
            torn.passar();
            System.out.println(joc.getTorn() +"Passo");
        }
        
    }

}
