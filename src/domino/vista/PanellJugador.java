/*
 * Author Roger G. Coscojuela
 */
package domino.vista;

import domino.controlador.ControladorGrafic;
import domino.model.Fitxa;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    private ImageIcon[] imatgesFitxesVertical = null;
    private ImageIcon[] imatgesFitxesHoritzontal = null;
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
    
    private void omplirArrayImatgesFitxes() {
    }
    
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
                userNomLabel.setRotation(utils.VerticalLabelUI.ROTATE_RIGHT);
                this.add(userNomLabel);
                this.add(panelFitxes);
            } else {
                userNomLabel.setRotation(utils.VerticalLabelUI.ROTATE_LEFT);
                this.add(panelFitxes);
                this.add(userNomLabel);
            }
        }
        
    }
    
    public void setNomJugador(String nom) {
        this.userNomLabel.setText(nom);
    }
    
    public void setFitxes(List<Fitxa> input) {
        fitxes = new ArrayList<>();
        //System.out.println("Añadida fitxa");
        for (Fitxa fitxa : input) {
            JButton JBfitxa = new JButton();
            JBfitxa.setActionCommand("fitxa");
            if (IAControlled == false) {
                JBfitxa.addActionListener(control);
            }
            if (posicio.equals(BorderLayout.SOUTH)) {
                JBfitxa.setText((fitxa.getValors()[0]) + ":" + fitxa.getValors()[1]);
            } else {
                if (MostrarFitxes) {
                    JBfitxa.setText((fitxa.getValors()[0]) + ":" + fitxa.getValors()[1]);
                } else {
                    JBfitxa.setText("");
                }
            }
            this.fitxes.add(JBfitxa);
        }
    }
    
    public void repinta() {
        this.panelFitxes.removeAll();
        System.out.println(this.userNomLabel.getText() + ": Repinto.");
        for (JButton fitxa : fitxes) {
            this.panelFitxes.add(fitxa);
        }
    }
    
}