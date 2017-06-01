/*
 * Author Roger G. Coscojuela
 */
package domino.vista;

import domino.model.Fitxa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.util.ArrayDeque;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Roger G. Coscojuela
 */
public class Tauler extends javax.swing.JPanel {

    ArrayDeque<Fitxa> fitxes;

    public Tauler() {
        this.setLayout(new BorderLayout());
    }

    public void setFitxes(ArrayDeque<Fitxa> fitxes) {
        this.fitxes = fitxes;
    }

    public void refresca() {
        this.setBackground(Color.DARK_GRAY);
        //Esborrem cualsevol objecte que hi hagi al layout.
        this.removeAll();

        //Inserim els panells nord, sud,  est y oest.
        JPanel northJpanel = new JPanel();
        JPanel eastJpanel = new JPanel();
        JPanel southJpanel = new JPanel();
        JPanel westJpanel = new JPanel();
        northJpanel.setBackground(this.getBackground());
        eastJpanel.setBackground(this.getBackground());
        //El layout est té un Layout BoxLayout intern.
        eastJpanel.setLayout(new BoxLayout(eastJpanel, BoxLayout.Y_AXIS));
        //El panell sud te un FlowLayout, i li indiquem que els elements els alineem a la dreta
        southJpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southJpanel.setBackground(this.getBackground());
        //Li indiquem que a l'hora d'afegir components, els afegim de dreta a esquerra
        southJpanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //El layout oest es igual que el est
        westJpanel.setBackground(this.getBackground());
        westJpanel.setLayout(new BoxLayout(westJpanel, BoxLayout.Y_AXIS));

        int count = 0;
        //Posem les fitxes en els diferents layouts, les set primeres es situen 
        //al panell superior, les seguents fins la 12 al panell est
        //Al panell inferior, les fitxes de la 12 a a 19
        //Finalment, les fitxes que queden es coloquen a la esquerra
        for (Fitxa fitxe : fitxes) {
            BotoFitxa fitxa = new BotoFitxa();
            fitxa.setValue(fitxe.getValors());
            if (count < 7) {
                //Es carrega l'icona adecuat segons el valor de la fitxa
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[0] + fitxe.getValors()[1] + ".png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                northJpanel.add(fitxa);

            } else if (count < 12) {
                //Es carrega l'icona adecuat segons el valor de la fitxa
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[0] + fitxe.getValors()[1] + "v.png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                eastJpanel.add(fitxa);
            } else if (count < 19) {
                //Es carrega l'icona adecuat segons el valor de la fitxa
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[1] + fitxe.getValors()[0] + ".png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                southJpanel.add(fitxa);
            } else {
                //Es carrega l'icona adecuat segons el valor de la fitxa
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[1] + fitxe.getValors()[0] + "v.png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                westJpanel.add(fitxa, 0);
            }

            count++;

        }
        //S'afegeixen els panells al layout.
        this.add(northJpanel, BorderLayout.NORTH);
        this.add(eastJpanel, BorderLayout.EAST);
        this.add(southJpanel, BorderLayout.SOUTH);
        this.add(westJpanel, BorderLayout.WEST);
        //Forcem amb revalidate a que es recalculin tots els elements (funciona conjuntament amb repaint)
        this.revalidate();
        this.repaint();
    }
}
