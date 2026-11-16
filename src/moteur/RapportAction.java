package moteur;

public class RapportAction {
/* VARIABLES */
    private int degatsInfliges;
    private int degatsStun;
    private String texteVerdict;
    private String texteImpact;

/* CONSTRUCTEUR */
    public RapportAction(int degatsInfliges, int degatsStun, String texteVerdict, String texteImpact){
        this.degatsInfliges = degatsInfliges;
        this.degatsStun = degatsStun;
        this.texteVerdict = texteVerdict;
        this.texteImpact = texteImpact;
    }

/* GETTERS */
    public int getDegatsInfliges() { return degatsInfliges; }
    public int getDegatsStun() { return degatsStun; }
    public String getTexteVerdict() { return texteVerdict; }
    public String getTexteImpact() { return texteImpact; }
}
