public class Grappler extends Combattant{
/* VARIABLES */
    private int compteurCoups;

/* CONSTRUCTEUR */
    public Grappler(String nom){
        super(nom, 140, 15, 20, 130);
        this.compteurCoups = 0;
    }

/* METHODES */
    @Override public int lanceAttaqueDebase(Combattant cible){
        this.compteurCoups++;
        if(compteurCoups >=2 && this.jaugeSpecial < 3){
            this.jaugeSpecial++;
            this.compteurCoups = 0;
        }
        cible.recevoirDegats(this.attaqueDeBase);
        return this.attaqueDeBase;
    }
    @Override public int lanceChope(Combattant cible){
        cible.recevoirDegats(this.attaqueDeBase * 2);
        cible.recevoirStun(25);
        this.recevoirJaugeSpecial(1);
        return this.attaqueDeBase * 2;
    }
    @Override public int lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 1.6));
            this.jaugeSpecial --;
            cible.recevoirStun(40);
            return (int) (this.attaqueDeBase * 1.6);
        }
        return 0;
    }
    @Override public int lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 2.4));
            this.jaugeSpecial -= 2;
            this.soigne(10);
            return (int) (this.attaqueDeBase * 2.4);
        }
        return 0;
    }
    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            cible.recevoirDegats(this.attaqueDeBase * 4);
            this.jaugeSpecial -= 3;
            return this.attaqueDeBase * 4;
        }
        return 0;
    }
    @Override public int estimationDegatInflige(int niveauAtt){
        int estimation = switch (niveauAtt) {
            case 1 -> this.attaqueDeBase;
            case 2 -> (int) (this.attaqueDeBase * 1.6);
            case 3 -> (int) (this.attaqueDeBase * 2.4);
            case 4 -> this.attaqueDeBase * 4;
            default -> 0;
        };
        return estimation;
    }
    @Override public Combattant copieCombattant(){
        Grappler copie = new Grappler(this.nom);
        return copie;
    }
}

