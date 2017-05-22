/*
 * Author Roger G. Coscojuela
 */
package domino.vista;

import domino.controlador.ControladorGrafic;
import domino.model.Fitxa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger G. Coscojuela
 */
public class VistaGrafica extends javax.swing.JFrame {

    ControladorGrafic control;
    Config config;

    PanellJugador pUser1;
    PanellJugador pUser2;
    PanellJugador pUser3;
    PanellJugador pUser4;
    Tauler tauler;

    /**
     * Creates new form VistaGrafica
     */
    public VistaGrafica() {
        incialitzaComponents();
    }

    public VistaGrafica(ControladorGrafic control) {
        this.control = control;
        config = new Config(control);
        incialitzaComponents();
    }

    private void incialitzaComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu1.setText("Joc");

        jMenuItem1.setText("Nou Joc");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNouJocActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);
        this.getContentPane().setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(700, 500));
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.pack();
    }

    public void actualitzarEstat(ArrayDeque<Fitxa> FitxesEntauler, List<Fitxa> jugador1, List<Fitxa> jugador2) {
        this.tauler.setFitxes(FitxesEntauler);
        this.tauler.refresca();
        this.pUser1.setFitxes(jugador1);
        this.pUser2.setFitxes(jugador2);
        this.pUser1.repinta();
        this.pUser2.repinta();
    }

    public void actualitzarEstat(ArrayDeque<Fitxa> FitxesEntauler, List<Fitxa> jugador1, List<Fitxa> jugador2, List<Fitxa> jugador3, List<Fitxa> jugador4) {
        this.tauler.setFitxes(FitxesEntauler);
        this.tauler.refresca();
        this.pUser1.setFitxes(jugador1);
        this.pUser2.setFitxes(jugador2);
        this.pUser3.setFitxes(jugador3);
        this.pUser4.setFitxes(jugador4);
        this.pUser1.repinta();
        this.pUser2.repinta();
        this.pUser3.repinta();
        this.pUser4.repinta();
        this.revalidate();
        this.repaint();
    }

    public void inicialitzarTauler() {

        //eliminem qualsevol rastre de joc anterior
        this.getContentPane().removeAll();

        //Creem un nou tauler
        tauler = new Tauler();

        //Crreem y posicionem els jugadors al tauler.
        if (getNomsJugadors().length == 2) {
            pUser1 = new PanellJugador(BorderLayout.SOUTH, false, true, control);
            pUser2 = new PanellJugador(BorderLayout.NORTH, config.getIA(), config.getMostrarFitxes(), control);
            pUser1.setBackground(Color.yellow);
            pUser2.setBackground(Color.BLUE);

            this.getContentPane().add(pUser1, BorderLayout.SOUTH);
            this.getContentPane().add(pUser2, BorderLayout.NORTH);
        } else {
            pUser1 = new PanellJugador(BorderLayout.SOUTH, false, true, control);
            pUser2 = new PanellJugador(BorderLayout.EAST, config.getIA(), config.getMostrarFitxes(), control);
            pUser3 = new PanellJugador(BorderLayout.NORTH, config.getIA(), config.getMostrarFitxes(), control);
            pUser4 = new PanellJugador(BorderLayout.WEST, config.getIA(), config.getMostrarFitxes(), control);

            pUser1.setBackground(Color.yellow);
            pUser2.setBackground(Color.BLUE);
            pUser3.setBackground(Color.RED);
            pUser4.setBackground(Color.CYAN);

            this.getContentPane().add(pUser1, BorderLayout.SOUTH);
            this.getContentPane().add(pUser2, BorderLayout.EAST);
            this.getContentPane().add(pUser3, BorderLayout.NORTH);
            this.getContentPane().add(pUser4, BorderLayout.WEST);
        }
        
        this.getContentPane().add(tauler, BorderLayout.CENTER);
        for (int i = 0; i < getNomsJugadors().length; i++) {
            setNomJugador(i, getNomsJugadors()[i]);
        }
        this.pack();
    }

    private void btNouJocActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        config.setVisible(true);
    }

    public String[] getNomsJugadors() {
        return config.getJugadors();
    }

    public boolean isCPUControlled(int Jugador) {
        boolean result = false;
        switch (Jugador) {
            case 0:
                result = pUser1.isIAControlled();
                break;
            case 1:
                result = pUser2.isIAControlled();
                break;
            case 2:
                result = pUser3.isIAControlled();
                break;
            case 3:
                result = pUser4.isIAControlled();
                break;
        }
        return result;
    }

    public void mostrarMissatge(String missatge) {
        JOptionPane.showMessageDialog(this,
                missatge);
    }

    public boolean demanarCostat() {
        Object[] options1 = {"Esquerra", "Dreta"};

        int dialogResult = JOptionPane.showOptionDialog(this,
                "A quin canto vols colocar la fitxa?",
                "La resposta al sentit de la vida l'univers i tot el que resta",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options1,
                options1[1]);
        return dialogResult == 0;
    }

    public void setFitxesJugador(int jugador, List<Fitxa> fitxes) {
        switch (jugador) {
            case 0:
                pUser1.setFitxes(fitxes);
                break;
            case 1:
                pUser2.setFitxes(fitxes);
                break;
            case 2:
                pUser3.setFitxes(fitxes);
                break;
            case 3:
                pUser4.setFitxes(fitxes);
                break;

        }
    }

    /**
     *
     * @param jugador valors de 0 a X
     * @param nom
     */
    public void setNomJugador(int jugador, String nom) {
        switch (jugador) {
            case 0:
                pUser1.setNomJugador(nom);
                System.out.println("Jugador " + jugador + " es:" + nom);
                break;
            case 1:
                pUser2.setNomJugador(nom);
                System.out.println("Jugador " + jugador + " es:" + nom);
                break;
            case 2:
                pUser3.setNomJugador(nom);
                System.out.println("Jugador " + jugador + " es:" + nom);
                break;
            case 3:
                pUser4.setNomJugador(nom);
                System.out.println("Jugador  " + jugador + " es:" + nom);
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGrafica().setVisible(true);
            }
        });
    }

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
}
