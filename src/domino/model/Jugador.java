package domino.model;

import java.util.ArrayList;
import java.util.List;

public  class Jugador {
    private String nom;
    private List<Fitxa> fitxes;
    private boolean IAControlled;

    public Jugador(String nom) {      
        this.nom = nom;
        fitxes = new ArrayList();
    } 
    
      public Jugador( String nom, List<Fitxa> Fitxes, boolean IAControlled) {      
        this.nom = nom;
        this.fitxes=Fitxes;
        this.IAControlled=IAControlled;
    }   

    public String getNom() {
        return nom;
    }

    public boolean isIAControlled() {
        return IAControlled;
    }
    
    

    public List<Fitxa> getFitxes() {
       return fitxes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFitxes(ArrayList<Fitxa> fitxes) {
        this.fitxes = fitxes;
    }
    
    public boolean colocarFitxa(Fitxa f){
        return this.fitxes.remove(f);
    }

    @Override
    public String toString() {
        return "Jugador{ nom=" + nom + ", fitxes=" + fitxes + '}';
    }
    

}
