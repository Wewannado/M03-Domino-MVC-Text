/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.controlador;

import domino.model.Fitxa;
import domino.vista.Vista;
import domino.model.Joc;
import domino.model.Torn;


/**
 *
 * @author Roger G. Coscojuela
 */
public class ControladorText {

    private int nombre_jugadors;
    private Vista vista;
    private Joc joc;
    private Torn torn;

    public ControladorText() {
        this.vista = new Vista();
        inicialitza();
    }

    public void inicialitza() {
        this.nombre_jugadors = vista.entrarUnNumero("Introdueix el numero de Jugadors");
        this.joc = new Joc(nombre_jugadors, 28, 7);
        joc.iniciar(vista.demanarNomsJugadors(nombre_jugadors));
    }

    public void executa() {
        vista.mostrarMissatge("\n\nInici del joc\n");
        torn = new Torn(joc);
        torn.inicial();
        joc.actualitzarEstat();
        while (!joc.isFinalitzat()) {
            vista.mostrarMissatge("Torn:" + joc.getTorn() + "   Jugador: "+joc.getJugadors()[joc.getTorn()].getNom());
            mostrarTauler();
            respostaOpcioMenu();
            joc.actualitzarEstat();
        }
        vista.mostrarMissatge("Fi de la partida. El guanyador es: "+joc.getGuanyador().getNom());
    }

    public void respostaOpcioMenu() {
        String accio;
        boolean semafor=false;
        while (!semafor) {
            mostrarFitxesJugadorActual();
            accio = vista.mostrarMenu(joc.getJugadors()[joc.getTorn()].getNom());
            switch (accio) {

                case "1":
                    //Passa torn
                    torn.passar();
                    semafor=true;
                    break;
                case "2":
                    //tirar una fitxa
                    if(opcioTirarFitxa()){
                        semafor=true;
                    };
                    break;
                case "3":
                    if(opcioTirarDobles()){
                        semafor=true;
                    };
                    break;
                default:
                    vista.mostrarMissatge("Opció incorrecta");
                    break;
            }
        }


    }
    private void mostrarTauler(){
        vista.mostrarFitxes(joc.getFitxesJugades(),"Tauler actual:");
    }
    
    private void mostrarFitxesJugadorActual(){
        vista.mostrarFitxes(joc.getJugadors()[joc.getTorn()].getFitxes(),"Les teves fitxes són:");
    }
    private boolean opcioTirarDobles(){
        final int MINIM=1;
        final int MAXIM=joc.getJugadors()[joc.getTorn()].getFitxes().size()-1;
        Fitxa Doble1=joc.getJugadors()[joc.getTorn()].getFitxes().get(
                    vista.entrarUnNumero("Quin doble vols tirar?",
                    MINIM,MAXIM));
        boolean posicioDoble1=vista.demanarPosicio();
        Fitxa Doble2=joc.getJugadors()[joc.getTorn()].getFitxes().get(
                    vista.entrarUnNumero("Quin és el segon doble que vols tirar?",
                    MINIM,MAXIM));
        boolean posicioDoble2=vista.demanarPosicio();
        
        boolean semafor=torn.colocarDosDobles(Doble1, posicioDoble1, Doble2, posicioDoble2);
        if(!semafor){
            System.out.println("Error al posar els dobles");
        }
        else{
            System.out.println("-----------------------------------------------------");
        }
        return semafor;
    }
    
    
    
    private boolean opcioTirarFitxa(){
    final int MINIM=1;
    boolean posicioFitxa=vista.demanarPosicio();
    boolean semafor=torn.colocarUnaFitxa(joc.getJugadors()[joc.getTorn()].getFitxes().get(
                    vista.entrarUnNumero("Quina fitxa vols tirar?",
                    MINIM,
                    joc.getJugadors()[joc.getTorn()].getFitxes().size())-1),
                    posicioFitxa);
    if(!semafor){
        vista.mostrarMissatge("No es pot colocar aquesta fitxa.");
    }
    else{
        System.out.println("-----------------------------------------------------");
    }
    return semafor;
    }


}
