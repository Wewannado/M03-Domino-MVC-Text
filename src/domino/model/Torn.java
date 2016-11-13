package domino.model;

public class Torn {
    private Joc joc;


    public Torn(Joc joc) {
        this.joc = joc;
    }

    public boolean colocarUnaFitxa(Fitxa f, boolean extrem) {
        boolean correcte = false;
        if (comprovaFitxa(f, extrem)) {
            if (extrem) {
                joc.getFitxesJugades().addFirst(f);
            } else {
                joc.getFitxesJugades().addLast(f);
            }
            joc.getJugadors()[joc.getTorn()].colocarFitxa(f);
            joc.setComptPassar(0);
            correcte = true;
        }
        return correcte;
    }

    public boolean colocarDosDobles(Fitxa d1, boolean extrem1, Fitxa d2,
            boolean extrem2) {
        boolean correcte = false;

        if (isDoble(d1) && isDoble(d2)) {
            correcte = colocarUnaFitxa(d1, extrem1)
                    && colocarUnaFitxa(d2, extrem2);
        }
         this.joc.setComptPassar(0);
        return correcte;
    }
    public void passar(){
        joc.setComptPassar( joc.getComptPassar()+1);
    }

    public void inicial() {
        joc.getFitxesJugades().add(joc.getFitxaInicial());
        joc.getJugadors()[joc.getTorn()].colocarFitxa(joc.getFitxaInicial());
    }

    private boolean isDoble(Fitxa f) {
        return f.getValors()[0] == f.getValors()[1];
    }

    private boolean comprovaFitxa(Fitxa f, boolean extrem) {
        boolean correcte = false;
        if (extrem) {
            if (f.getValors()[1] == joc.getFitxesJugades().getFirst().getValors()[0]) {
                correcte = true;
            } else if (f.getValors()[0] == joc.getFitxesJugades().getFirst().getValors()[0]) {
                f.canviarOrientacio();
                correcte = true;
            }
        } else {
            if (f.getValors()[0] == joc.getFitxesJugades().getLast().getValors()[1]) {
                correcte = true;
            } else if (f.getValors()[1] == joc.getFitxesJugades().getLast().getValors()[1]) {
                f.canviarOrientacio();
                correcte = true;
            }
        }
        return correcte;
    }
}
