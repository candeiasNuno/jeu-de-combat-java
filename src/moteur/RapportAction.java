package moteur;

public class RapportAction {
/* VARIABLES */
    private int degatsInfliges;
    private int degatsStun;
    private int jaugeSpeGagne;
    private int jaugeSpePerdue;
    private String nomAttaque;
    private String texteVerdict;
    private String texteImpact;

/* CONSTRUCTEUR */
    public RapportAction(int degatsInfliges, int degatsStun, int jaugeSpeGagne, int jaugeSpePerdue,String nomAttaque,
                         String texteVerdict, String texteImpact){
        this.degatsInfliges = degatsInfliges;
        this.degatsStun = degatsStun;
        this.jaugeSpeGagne = jaugeSpeGagne;
        this.jaugeSpePerdue = jaugeSpePerdue;
        this.nomAttaque = nomAttaque;
        this.texteVerdict = texteVerdict;
        this.texteImpact = texteImpact;
    }

/* GETTERS */
    public int getDegatsInfliges() { return degatsInfliges; }
    public int getDegatsStun() { return degatsStun; }
    public String getTexteVerdict() { return texteVerdict; }
    public String getTexteImpact() { return texteImpact; }
    public int getJaugeSpeGagne(){ return jaugeSpeGagne; }
    public int getJaugeSpePerdue() { return jaugeSpePerdue; }
    public String getNomAttaque() { return nomAttaque; }
}
