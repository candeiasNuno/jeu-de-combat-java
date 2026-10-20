abstract public class Combattant {
/* VARIABLES */
    protected String nom;
    protected int vie;
    protected int attaqueDeBase;
    protected int vitesse;
    protected int jaugeSpecial;
    protected boolean estVivant;

/* CONSTRUCTEUR */
    protected Combattant( String nom, int vie, int attaqueDeBase, int vitesse){
        this.nom = nom;
        this.vie = vie;
        this.attaqueDeBase = attaqueDeBase;
        this.vitesse = vitesse;
        this.jaugeSpecial = 0;
        this.estVivant = true;
    }

/* METHODES */
    public void lanceAttaqueDebase(Combattant cible){
        if(this.jaugeSpecial < 3){
            this.jaugeSpecial++;
        }
        cible.recevoirDegats(this.attaqueDeBase);
    }
    public void recevoirDegats(int degats){
        if(this.vie - degats <= 0){
            this.vie = 0;
            this.estVivant = false;
        }else{
            this.vie = this.vie - degats;
        }
    }
    abstract public void lanceAttaqueSpecial1(Combattant cible);
    abstract public void lanceAttaqueSpecial2(Combattant cible);
    abstract public void lanceAttaqueUltime(Combattant cible);


/* GETTER */
    public String getNom() { return nom; }
    public int getVie() { return vie; }
    public int getAttaqueDeBase() { return attaqueDeBase; }
    public int getVitesse() { return vitesse; }
    public int getJaugeSpecial() { return jaugeSpecial; }
    public boolean getEstVivant() { return estVivant; }
}
