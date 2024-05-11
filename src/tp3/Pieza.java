package tp3;
public class Pieza {
    // True es buena, false es mala
    private double rnd;
    private boolean esBuena;

    public Pieza(){
        rnd = Math.random();
        esBuena =  rnd< 0.7;

    }

    public boolean esBuena(){
        return esBuena;
    }

    public String getRnd() {
        return String.format("%.2f",rnd);
    }
    

}
