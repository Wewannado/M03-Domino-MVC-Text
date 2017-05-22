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
import domino.vista.BotoFitxa;
import domino.vista.VistaGrafica;
import ia.Ia;
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
        //joc.actualitzarEstat();
        refresca();
        while (joc.getTorn() != 0 && vista.isCPUControlled(joc.getTorn())) {
            System.out.println("Li toca a la maquina:" + joc.getTorn());
            Ia ia = new Ia(joc, torn);
            ia.jugar();
            refresca();
        }

    }

    private void refresca() {
        joc.actualitzarEstat();
        if (joc.NUMJUGADORS == 2) {
            vista.actualitzarEstat(joc.getFitxesJugades(), joc.getJugadors()[0].getFitxes(), joc.getJugadors()[1].getFitxes());
        } else {
            vista.actualitzarEstat(joc.getFitxesJugades(), joc.getJugadors()[0].getFitxes(), joc.getJugadors()[1].getFitxes(), joc.getJugadors()[2].getFitxes(), joc.getJugadors()[3].getFitxes());
        }
//        mostrarFitxesConsola(joc.getFitxesJugades(), "Les fitxes jugades son:");
//        System.out.println("Torn:" + joc.getTorn() + "   Jugador: " + joc.getJugadors()[joc.getTorn()].getNom());
//        System.out.println(joc.getJugadors()[0].getFitxes());
//        System.out.println(joc.getJugadors()[1].getFitxes());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("btNoms")) {
            inicialitzaTauler();
            iniciaPartida();
        }
        if (!joc.isFinalitzat()) {
            if (ae.getActionCommand().equals("fitxa")) {
                BotoFitxa JBfitxa = (BotoFitxa) ae.getSource();
                int[] valorsFitxa = new int[2];
                valorsFitxa[0] = JBfitxa.getValue()[0];
                valorsFitxa[1] = JBfitxa.getValue()[1];

                //TODO la fitxa l'hem d¡btenir de l'array 
                //
                List<Fitxa> fitxes = joc.getJugadors()[joc.getTorn()].getFitxes();
                System.out.println("Torn del jugador:" + joc.getTorn());
                Fitxa fitxa = null;
                for (Fitxa fitxe : fitxes) {
                    if (Arrays.equals(fitxe.getValors(), valorsFitxa)) {
                        fitxa = fitxe;
                        System.out.println("Fitxa trobada");
                    }
                }
                if (fitxa == null) {
                    vista.mostrarMissatge("No es el teu torn.");
                } else {

                    //System.out.println("S'ha polsat una fitxa:" +fitxa.getText());  
                    if (torn.colocarUnaFitxa(fitxa, vista.demanarCostat())) {
                        refresca();
                        while (vista.isCPUControlled(joc.getTorn())&&!joc.isFinalitzat()) {
                            System.out.println("Li toca a la maquina");
                            Ia ia = new Ia(joc, torn);
                            ia.jugar();
                            refresca();
                        }
                    } else {
                        vista.mostrarMissatge("No es pot posar aquesta fitxa.");
                    }
                }
                if (joc.isFinalitzat()) {
                    vista.mostrarMissatge("Fi del joc. El guanyador es:" + joc.getGuanyador().getNom());
                }
            }
            if (ae.getActionCommand().equals("passar")) {
                System.out.println("Passem el torn.");
                torn.passar();
                joc.actualitzarEstat();
                System.out.println("El nou torn es:" + joc.getTorn());
                while (vista.isCPUControlled(joc.getTorn())&&!joc.isFinalitzat()) {
                            System.out.println("Li toca a la maquina");
                            Ia ia = new Ia(joc, torn);
                            ia.jugar();
                            refresca();
                        }
                if (joc.isFinalitzat()) {
                    vista.mostrarMissatge("Fi del joc. El guanyador es:" + joc.getGuanyador().getNom());
                }
            }
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
