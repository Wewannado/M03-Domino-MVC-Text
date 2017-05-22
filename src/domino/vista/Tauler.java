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
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        this.removeAll();
        System.out.println("Tauler: Repinto");
        int count = 0;
        JPanel northJpanel = new JPanel();
        northJpanel.setBackground(this.getBackground());
        JPanel eastJpanel = new JPanel();
        eastJpanel.setBackground(this.getBackground());
        eastJpanel.setLayout(new BoxLayout(eastJpanel, BoxLayout.Y_AXIS));
        JPanel southJpanel = new JPanel();
        southJpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southJpanel.setBackground(this.getBackground());
        southJpanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JPanel westJpanel = new JPanel();
        westJpanel.setBackground(this.getBackground());
        westJpanel.setLayout(new BoxLayout(westJpanel, BoxLayout.Y_AXIS));
        for (Fitxa fitxe : fitxes) {
            BotoFitxa fitxa = new BotoFitxa();
            fitxa.setValue(fitxe.getValors());
            //fitxa.setText((fitxe.getValors()[0]) + ":" + fitxe.getValors()[1]);

            if (count < 7) {
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[0] + fitxe.getValors()[1] + ".png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                northJpanel.add(fitxa);

            } else if (count < 12) {
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[0] + fitxe.getValors()[1] + "v.png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                eastJpanel.add(fitxa);
            } else if (count < 18) {
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[1] + fitxe.getValors()[0] + ".png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                southJpanel.add(fitxa);
            } else {
                ImageIcon icon = new ImageIcon("assets/" + fitxe.getValors()[0] + fitxe.getValors()[1] + "v.png");
                fitxa.setIcon(icon);
                fitxa.setBorderPainted(false);
                fitxa.setContentAreaFilled(false);
                fitxa.setFocusPainted(false);
                fitxa.setOpaque(false);
                westJpanel.add(fitxa);
            }

            count++;

        }
        this.add(northJpanel, BorderLayout.NORTH);
        this.add(eastJpanel, BorderLayout.EAST);
        this.add(southJpanel, BorderLayout.SOUTH);
        this.add(westJpanel, BorderLayout.WEST);
        this.revalidate();
        this.repaint();
    }
}
