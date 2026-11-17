package combattants;
import moteur.RapportAction;
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
    protected int bonusAttaque;

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
        this.bonusAttaque = 0;
    }

/* METHODES */
    public RapportAction lanceAttaqueDebase(Combattant cible){
        this.recevoirJaugeSpecial(1);
        if(this.jaugeSpecial < 3){
            this.recevoirJaugeSpecial(1);
        }
        return new RapportAction(this.attaqueDeBase, 0, 0, 0, "Coup de Poing",
                null, null);
    }
    public RapportAction lanceChope(Combattant cible){
        int degats = (int) (this.attaqueDeBase * 1.5);
        return new RapportAction(degats, 0, 0, 0, "Chope", null, null);
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
    public void reinitialiseBonusAttaque(){ this.bonusAttaque = 0; }
    abstract public RapportAction lanceAttaqueSpecial1(Combattant cible);
    abstract public RapportAction lanceAttaqueSpecial2(Combattant cible);
    abstract public RapportAction lanceAttaqueUltime(Combattant cible);
    abstract public int estimationDegatInflige(int niveauAtt);
    abstract public Combattant copieCombattant();
    abstract public String getArchetype();


/* GETTER */
    public String getNom() { return this.nom; }
    public int getVie() { return this.vie; }
    public int getAttaqueDeBase() { return this.attaqueDeBase; }
    public int getVitesse() { return this.vitesse; }
    public int getJaugeSpecial() { return this.jaugeSpecial; }
    public boolean getEstVivant() { return this.estVivant; }
    public int getVieMax() { return this.vieMax; }
    public int getStunMax() { return this.stunMax;}
    public int getJaugeStun() { return this.jaugeStun;}
    public boolean isEstEtourdi() { return this.estEtourdi; }
    public int getBoostAttaque(){ return this.bonusAttaque; }

/* METHODES UTIL RAPPORT ACTION */
    public RapportAction lanceAttaque( Combattant cible, int niveauAttaque){
    RapportAction rapport = null;
    switch (niveauAttaque){
        case 1:
            rapport = this.lanceAttaqueDebase(cible);
            break;
        case 2:
            rapport = this.lanceAttaqueSpecial1(cible);
            break;
        case 3:
            rapport = this.lanceAttaqueSpecial2(cible);
            break;
        case 4:
            rapport = this.lanceAttaqueUltime(cible);
            break;
        default:
            System.out.println("Erreur lance attaque");
            break;
    }
    return rapport;
}
    public RapportAction duelVitesse(Combattant cible, int niveauAtt, int niveauCible){
        int degats;
        int degatsStun;
        String verdict;
        String impact;
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        if(this.vitesse > cible.getVitesse()){
            degats = this.lanceAttaque(cible, niveauAtt);
            degatsStun = 0;
            verdict = "> Verdict        : [COUNTER HIT !] " + nomAttaquant + " est plus rapide que " + nomCible + " !";
            impact = "> Impact         : " + nomCible + " perd " + degats + " PV !";
        } else if (this.vitesse < cible.getVitesse()){
            degats = cible.lanceAttaque(this, niveauCible);
            degatsStun = 0;
            verdict = "> Verdict        : [COUNTER HIT !] " + nomCible + " est plus rapide que " + nomAttaquant + " !";
            impact = "> Impact         : " + nomAttaquant + " perd " + degats + " PV !";
        } else{
            degats = 0;
            degatsStun = 0;
            verdict = "> Verdict        : [EGALITE !] " + nomAttaquant + " et " + nomCible + " ont la meme vitesse d'attaque ! Ils se sont neutralise !";
            impact = "> Impact         : Les 2 combattant ne subisse aucun degats !";
        }
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }

/* METHODES RAPPORT ACTION */
    public RapportAction resoudreAttaqueVsGarde(int niveauAtt, Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.getNom().toUpperCase();
        RapportAction rapport = this.lanceAttaque(cible, niveauAtt);
        int degats = 0;
        int degatsStun = rapport.getDegatsInfliges();
        String verdict = "> Verdict        : [GARDE PARFAITE !] " + nomCible + " bloque parfaitement " + rapport.getNomAttaque() +
                " de " + nomAttaquant + " !";
        String impact = "> Impact         : " + nomCible + " gagne 1 de jauge Special et subit " + degatsStun +
                " de degats de stun.";
        return new RapportAction(degats, degatsStun, 0, 0, null, verdict, impact);

    }
    public RapportAction resoudreAttaqueVsChope(int niveauAtt, Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = this.lanceAttaque(cible, niveauAtt);
        int degatsStun = 0;
        String verdict = "> Verdict        : [COUNTER HIT - INTERROMPU !] " + nomAttaquant + " frappe " + nomCible +
                " au moment ou il tentait une Chope ! ";
        String impacte = "> Impact         : " + nomCible + " perd " + degats + " PV !";
        return new RapportAction(degats, degatsStun, verdict, impacte, 0);
    }
    public RapportAction resoudreAttaqueVsAttaque(int niveauAttAttaquant, Combattant cible, int niveauAttCible){
        int degats;
        int degatsStun;
        String verdict;
        String impact;
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        if(niveauAttAttaquant > niveauAttCible){
            degats = this.lanceAttaque(cible, niveauAttAttaquant);
            degatsStun = 0;
            verdict = "> Verdict        : [CRUNCH - ECRASEMENT !] " + nomAttaquant + " ecrase l'attaque de " + nomCible + " !";
            impact = "> Impact         : " + nomCible + " perd " + degats + " PV !";
        } else if( niveauAttAttaquant < niveauAttCible){
            degats = cible.lanceAttaque(this, niveauAttCible);
            degatsStun = 0;
            verdict = "> Verdict        : [CRUNCH - ECRASEMENT !] " + nomCible + " ecrase l'attaque de " + nomAttaquant + " !";
            impact = "> Impact         : " + nomAttaquant + " perd " + degats + " PV !";
        } else{
            return this.duelVitesse(cible, niveauAttAttaquant, niveauAttCible);
        }
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }
    public RapportAction resoudreAttaqueVsStun(int niveauAtt, Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = this.lanceAttaque(cible, niveauAtt);
        int degatsStun = 0;
        String verdict = "> Verdict        : [FREE HIT !] " + nomAttaquant + " frappe " + nomCible + " pendant qu'il est stun !!";
        String impact = "> Impact         : " + nomCible + " perd " + degats + " PV !";
        return new RapportAction(degats, degatsStun, verdict, impact, 0);

    }

    public RapportAction resoudreGardeVsChope(Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = cible.lanceChope(this);
        int degatsStun = 0;
        String verdict = "> Verdict        : [BRISE-GARDE !] " + nomCible + " attrape " + nomAttaquant + " a travers sa Garde et l'envoi au sol !";
        String impact = "> Impact         : " + nomAttaquant + " perd " + degats + " PV !";
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }
    public RapportAction resoudreGardeVsGarde(Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = 0;
        int degatsStun = 0;
        String verdict = "> Verdict        : [OBSERVATION !] " + nomAttaquant + " et " + nomCible + " reste les 2 en garde et s'observe !";
        String impact = "> Impact         : Les 2 combattant ne subisse aucun degats !";
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }
    public RapportAction resoudreGardeVsStun(Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = 0;
        int degatsStun = 0;
        String verdict = "> Verdict        : [OCCASION MANQUE !] " + nomAttaquant + " observe " + nomCible + " alors qu'il est stun !";
        String impact = "> Impact         : Les 2 combattant ne subisse aucun degats !";
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }

    public RapportAction resoudreChopeVSChope(Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = 0;
        int degatsStun = 0;
        String verdict = "> Verdict        : [TECH - ANNULATION !] " + nomAttaquant + " et " + nomCible
                + " tente une chope en meme temps et se neutralise !";
        String impact = "> Impact         : Les 2 chope s'annule !";
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }
    public RapportAction resoudreChopeVsStun(Combattant cible){
        String nomCible = cible.getNom().toUpperCase();
        String nomAttaquant = this.nom.toUpperCase();
        int degats = lanceChope(cible);
        int degatsStun = 0;
        String verdict = "> Verdict        : [FREE CHOPE !] " + nomAttaquant + " chope " + nomCible + " pendant qu'il est stun !!";
        String impact = "> Impact         : " + nomCible + " perd " + degats + " PV !";
        return new RapportAction(degats, degatsStun, verdict, impact, 0);
    }
}
