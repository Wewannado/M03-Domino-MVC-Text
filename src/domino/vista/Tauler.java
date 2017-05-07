/*
 * Author Roger G. Coscojuela
 */
package domino.vista;

import domino.model.Fitxa;
import java.util.ArrayDeque;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Roger G. Coscojuela
 */
public class Tauler extends javax.swing.JPanel {
    
    ArrayDeque<Fitxa> fitxes;
    
    public Tauler() {
        
    }
    
    public void setFitxes(ArrayDeque<Fitxa> fitxes) {
        this.fitxes = fitxes;
    }
    
    public void refresca() {
        this.removeAll();
        System.out.println("Tauler: Repinto");
        for (Fitxa fitxe : fitxes) {
            JButton fitxa = new JButton();
            fitxa.setText((fitxe.getValors()[0]) + ":" + fitxe.getValors()[1]);
            this.add(fitxa);
        }
    }
}
