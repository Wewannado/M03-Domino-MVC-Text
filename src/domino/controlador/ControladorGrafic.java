/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.controlador;

import domino.model.Fitxa;
import domino.vista.VistaText;
import domino.model.Joc;
import domino.model.Torn;
import domino.vista.VistaGrafica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Roger G. Coscojuela
 */
public class ControladorGrafic implements ActionListener {

    private int nombre_jugadors;
    private VistaGrafica vista;
    private Joc joc;
    private Torn torn;

    public ControladorGrafic() {
        this.vista = new VistaGrafica(this);
        vista.setVisible(true);
    }

    /**
     * Inicialitza el tauler. Posa els jugadors a les posicions del tauler,
     * assigna els noms
     */
    private void inicialitzaTauler() {
        nombre_jugadors = vista.getNomsJugadors().length;
        joc = new Joc(nombre_jugadors, 28, 7);
        joc.iniciar(vista.getNomsJugadors());
        vista.inicialitzarTauler();
        for (int i = 0; i < nombre_jugadors; i++) {
            vista.setFitxesJugador(i, joc.getJugadors()[i].getFitxes());
        }
    }

    private void mostrarFitxesConsola(ArrayDeque<Fitxa> fitxes, String missatge) {
        System.out.println(missatge);
        for (Fitxa current : fitxes) {
            System.out.print("-["
                    + current.getValors()[0]
                    + ","
                    + current.getValors()[1]
                    + "]");
        }
        System.out.println("\n");
    }

    private void iniciaPartida() {
        torn = new Torn(joc);
        torn.inicial();
        joc.actualitzarEstat();
        refresca();
        
    }
    
    private void refresca(){
        joc.actualitzarEstat();
        if (joc.NUMJUGADORS == 2) {
            vista.actualitzarEstat(joc.getFitxesJugades(), joc.getJugadors()[0].getFitxes(), joc.getJugadors()[1].getFitxes());
        } else {
            vista.actualitzarEstat(joc.getFitxesJugades(), joc.getJugadors()[0].getFitxes(), joc.getJugadors()[1].getFitxes(), joc.getJugadors()[2].getFitxes(), joc.getJugadors()[3].getFitxes());
        }
        
        mostrarFitxesConsola(joc.getFitxesJugades(), "Les fitxes jugades son:");
        System.out.println("Torn:" + joc.getTorn() + "   Jugador: " + joc.getJugadors()[joc.getTorn()].getNom());
        System.out.println(joc.getJugadors()[0].getFitxes());
        System.out.println(joc.getJugadors()[1].getFitxes());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("btNoms")) {
            inicialitzaTauler();
            iniciaPartida();
        }
        if (ae.getActionCommand().equals("fitxa")) {
            JButton JBfitxa = (JButton) ae.getSource();
            int[] valorsFitxa= new int[2];
            valorsFitxa[0]=Integer.parseInt(JBfitxa.getText().split(":")[0]);
            valorsFitxa[1]=Integer.parseInt(JBfitxa.getText().split(":")[1]);
            Fitxa fitxa = new Fitxa(valorsFitxa);
            //System.out.println("S'ha polsat una fitxa:" +fitxa.getText());  
            System.out.println(torn.colocarUnaFitxa(fitxa,true));
            refresca();
            
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
