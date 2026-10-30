abstract public class Combattant {
/* VARIABLES */
    protected String nom;
    protected int vie;
    protected int attaqueDeBase;
    protected int vitesse;
    protected int jaugeSpecial;
    protected boolean estVivant;
    protected int vieMax;
    protected int stunMax;
    protected int jaugeStun;
    protected boolean estEtourdi;

/* CONSTRUCTEUR */
    protected Combattant( String nom, int vie, int attaqueDeBase, int vitesse, int stunMax){
        this.nom = nom;
        this.vie = vie;
        this.attaqueDeBase = attaqueDeBase;
        this.vitesse = vitesse;
        this.jaugeSpecial = 0;
        this.estVivant = true;
        this.vieMax = vie;
        this.stunMax = stunMax;
        this.estEtourdi = false;
        this.jaugeStun = 0;
    }

/* METHODES */
    public int lanceAttaqueDebase(Combattant cible){
        if(this.jaugeSpecial < 3){
            this.jaugeSpecial++;
        }
        cible.recevoirDegats(this.attaqueDeBase);
        return this.attaqueDeBase;
    }
    public int lanceChope(Combattant cible){
        cible.recevoirDegats((int) (this.attaqueDeBase * 1.5));
        return (int) (this.attaqueDeBase * 1.5);
    }
    public void recevoirDegats(int degats){
        if(this.vie - degats <= 0){
            this.vie = 0;
            this.estVivant = false;
        }else{
            this.vie = this.vie - degats;
        }
    }
    public void recevoirJaugeSpecial(int nb){
        this.jaugeSpecial = Math.min(3, this.jaugeSpecial + nb);
    }
    public void recevoirStun(int degatsStun){
        if(this.jaugeStun + degatsStun < stunMax){
            this.jaugeStun += degatsStun;
        } else{
            this.estEtourdi = true;
            this.jaugeStun = 0;
        }
    }
    public String etatsCombattant(){
        if(this.estEtourdi){
            return "ETOURDI";
        } else{
            return "NORMAL";
        }
    }
    public void desactiveStun(){
        this.estEtourdi = false;
    }
    public void consomation(int niveau){
        this.jaugeSpecial = Math.max(0, this.jaugeSpecial - (niveau - 1));
    }
    public void soigne(int nb){
        if(this.vie + nb > this.vieMax){
            this.vie = this.vieMax;
        } else{
            this.vie += nb;
        }
    }
    abstract public int lanceAttaqueSpecial1(Combattant cible);
    abstract public int lanceAttaqueSpecial2(Combattant cible);
    abstract public int lanceAttaqueUltime(Combattant cible);
    abstract public int estimationDegatInflige(int niveauAtt);
    abstract public Combattant copieCombattant();


/* GETTER */
    public String getNom() { return nom; }
    public int getVie() { return vie; }
    public int getAttaqueDeBase() { return attaqueDeBase; }
    public int getVitesse() { return vitesse; }
    public int getJaugeSpecial() { return jaugeSpecial; }
    public boolean getEstVivant() { return estVivant; }
    public boolean isEstVivant() { return estVivant; }
    public int getVieMax() { return vieMax; }
    public int getStunMax() { return stunMax;}
    public int getJaugeStun() { return jaugeStun;}
    public boolean isEstEtourdi() { return estEtourdi; }

}
