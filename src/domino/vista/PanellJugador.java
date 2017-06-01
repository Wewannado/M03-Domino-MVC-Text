/*
 * Author Roger G. Coscojuela
 */
package domino.vista;

import domino.controlador.ControladorGrafic;
import domino.model.Fitxa;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.VerticalLabelUI;

/**
 *
 * @author Roger G. Coscojuela
 */
public class PanellJugador extends javax.swing.JPanel {

    private utils.VerticalLabelUI userNomLabel;
    private String posicio = null;
    private ArrayList<JButton> fitxes = null;
    private JPanel panelFitxes = null;
    private final boolean IAControlled;
    private final boolean MostrarFitxes;
    ControladorGrafic control;

    /**
     *
     * @param posicio la posicio (BorderLayout.North/South/East/West)
     * @param IAControlled
     * @param MostrarFitxes
     * @param control
     */
    public PanellJugador(String posicio, boolean IAControlled, boolean MostrarFitxes, ControladorGrafic control) {
        this.posicio = posicio;
        this.IAControlled = IAControlled;
        this.MostrarFitxes = MostrarFitxes;
        this.control = control;
        panelFitxes = new JPanel();
        inicialitza();
    }

    /**
     * Inicializa el panell. es crida automaticament al fer el constructor.
     */
    private void inicialitza() {
        userNomLabel = new VerticalLabelUI();
        //Si es un layout north o south, la posicio dels components es Y_AXIS
        if (posicio.equals(BorderLayout.NORTH) || posicio.equals(BorderLayout.SOUTH)) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            //El panell nord te el nom a dalt, i les fitxes a baix
            if (posicio.equals(BorderLayout.NORTH)) {
                this.add(userNomLabel);
                this.add(panelFitxes);
            } //El panell sud te el nom a baix, i les fitxes a dalt.
            else {
                this.add(panelFitxes);
                this.add(userNomLabel);
            }
        } else {

            panelFitxes.setLayout(new GridLayout(0, 1));
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            if (posicio.equals(BorderLayout.WEST)) {
                //Rotem els labels els jugadors est y oest.
                userNomLabel.setRotation(utils.VerticalLabelUI.ROTATE_RIGHT);
                this.add(userNomLabel);
                this.add(panelFitxes);
            } else {
                //Rotem els labels els jugadors est y oest.
                userNomLabel.setRotation(utils.VerticalLabelUI.ROTATE_LEFT);
                this.add(panelFitxes);
                this.add(userNomLabel);
            }
        }

    }

    /**
     * Estableix el nom del jugador que es mostrara al tauler.
     *
     * @param nom
     */
    public void setNomJugador(String nom) {
        this.userNomLabel.setText(nom);
    }

    /**
     * Assigna les fitxes a un jugador i les pinta per pantalla
     *
     * @param input llista de fitxes per pintar
     */
    public void setFitxes(List<Fitxa> input) {
        fitxes = new ArrayList<>();
        for (Fitxa fitxa : input) {
            //Les fitxes son implementacions exteses de JButton
            BotoFitxa JBfitxa = new BotoFitxa();
            //Totes les fitxes tenen el action command fitxa
            JBfitxa.setActionCommand("fitxa");
            if (IAControlled == false) {
                //El listener de les fitxes les te el control
                JBfitxa.addActionListener(control);
            }

            //Si a settings em seleccionat que es mostrin les fitxes, el panell s'haura incialitzat
            //amb mostrar fitxes a true
            if (MostrarFitxes) {
                JBfitxa.setValue(fitxa.getValors());
                ImageIcon icon;
                //Mostrem les fitxes, diferenciem si es tracta d'un panell nord o sud d'un est o oest per
                //mostrar les imatges en horitzontal o vertical.
                if (posicio.equals(BorderLayout.NORTH) || posicio.equals(BorderLayout.SOUTH)) {
                    icon = new ImageIcon("assets/" + fitxa.getValors()[0] + fitxa.getValors()[1] + "v.png");
                } else {
                    icon = new ImageIcon("assets/" + fitxa.getValors()[0] + fitxa.getValors()[1] + ".png");
                }
                JBfitxa.setIcon(icon);
                JBfitxa.setBorderPainted(false);
                JBfitxa.setContentAreaFilled(false);
                JBfitxa.setFocusPainted(false);
                JBfitxa.setOpaque(false);
                //Tenim la opcio de mostrar fitxes desactivada, per tant, carreguem les imatges en negre.
            } else {
                ImageIcon icon;
                if (posicio.equals(BorderLayout.NORTH) || posicio.equals(BorderLayout.SOUTH)) {
                    icon = new ImageIcon("assets/" + "blackBack" + "v.png");
                } else {
                    icon = new ImageIcon("assets/" + "blackBack" + ".png");
                }
                JBfitxa.setIcon(icon);
                JBfitxa.setBorderPainted(false);
                JBfitxa.setContentAreaFilled(false);
                JBfitxa.setFocusPainted(false);
                JBfitxa.setOpaque(false);

            }
            this.fitxes.add(JBfitxa);

        }
        //Si el jugador no esta controlat per la IA, afegim un botor de passar.
        if (!isIAControlled()) {
            JButton BotoPassar = new JButton();
            BotoPassar.setActionCommand("passar");
            BotoPassar.setText("Passar");
            BotoPassar.addActionListener(control);
            this.fitxes.add(BotoPassar);
        }
    }

    /**
     * Obliga a llegir de not l'array de fitxes i pintar les fitxes en pantalla.
     */
    public void repinta() {
        //Esborrem totes les fitxes del panell
        this.panelFitxes.removeAll();
        //Recorrem l'array y les reptintem de nou
        for (JButton fitxa : fitxes) {
            this.panelFitxes.add(fitxa);
        }
        //Invalidem el layout, per tal qu es repinti
        this.revalidate();
        this.repaint();
    }

    public boolean isIAControlled() {
        return IAControlled;
    }

}
