/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.vista;

import domino.model.Fitxa;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Roger G. Coscojuela
 */
public class VistaText {

    Scanner lector = new Scanner(System.in);

    public void Vista() {

    }

    public String[] demanarNomsJugadors(int jugadors) {
        String[] noms = new String[jugadors];
        final String TEXT = "Introdueix el nom del jugador";
        for (int i = 0; i < jugadors; i++) {
            noms[i] = demanarString(TEXT);
        }

        return noms;
    }

    public int entrarUnNumero(String missatje) {
        String misError = "Has d'entrar un valor numeric";
        int resultat = 0;
        boolean semafor = false;
        do {
            System.out.println(missatje);
            if (lector.hasNextInt()) {
                resultat = lector.nextInt();
                semafor = true;
            } else {
                System.out.println(misError);
            }
            lector.nextLine();
        } while (!semafor);
        return resultat;
    }

    public int entrarUnNumero(String missatge, int minim, int maxim) {
        String misError = "Has d'entrar un valor numeric";
        String misErrorOutOfRange = "El valor no es correspon a un numero de fitxa valid.";
        int resultat = 0;
        boolean semafor = false;
        do {
            System.out.println(missatge + "Valors entre " + minim + " i " + maxim + ".");
            if (lector.hasNextInt()) {
                resultat = lector.nextInt();
                if (resultat < minim || resultat > maxim) {
                    System.out.println(misErrorOutOfRange);
                } else {
                    semafor = true;
                }
            } else {
                System.out.println(misError);
            }
            lector.nextLine();
        } while (!semafor);
        return resultat;
    }

    private String demanarString(String missatge) {
        String resultat = "";
        boolean semafor = false;
        while (!semafor) {
            System.out.println(missatge);
            if (lector.hasNextLine()) {
                resultat = lector.nextLine();
                semafor = true;
            } else {
                //TODO
            }
        }
        return resultat;
    }

    public void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }

    public String mostrarMenu(String jugador) {
        System.out.println(jugador + ", que vols fer?\n"
                + "\n1. Passar torn"
                + "\n2. Tirar fitxa"
                + "\n3. Tirar dos dobles"
        );
        return lector.nextLine();
    }

    public void mostrarFitxes(List<Fitxa> fitxes, String missatge) {
        System.out.println(missatge);
        for (Fitxa current : fitxes) {
            System.out.print("-["
                    + current.getValors()[0]
                    + ","
                    + current.getValors()[1]
                    + "]");
        }
        System.out.println("");
    }

    public void mostrarFitxes(ArrayDeque<Fitxa> fitxes, String missatge) {
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

    public boolean demanarPosicio() {
        boolean semafor = false;
        boolean resultat=true;
        while (!semafor) {
            System.out.println("A quin cantó vols col·locar-la? Dreta=D, Esquerra=E");
            if (lector.hasNextLine()) {
                String aux = lector.nextLine();           
                
                if ("E".equals(aux) || "e".equals(aux) || "Esquerra".equals(aux)) {
                    resultat = true;
                    semafor = true;
                } else if ("D".equals(aux) || "d".equals(aux) || "Dreta".equals(aux)) {
                    resultat = false;
                    semafor = true;
                }
            }
        }
        return resultat;
    }
}
