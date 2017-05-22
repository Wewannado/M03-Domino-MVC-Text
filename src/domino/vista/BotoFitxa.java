/*
 * Author Roger G. Coscojuela
 */
package domino.vista;

import javax.swing.JButton;

/**
 *
 * @author Roger G. Coscojuela
 */
public class BotoFitxa extends JButton {

    private int[] value;

    BotoFitxa() {
        super();
    }

    BotoFitxa(int[] value) {
        super();
        this.value = value;
    }
    
    public void setValue(int[] value){
        this.value=value;
    }
    
    public int[] getValue(){
        return value;
    }
}
