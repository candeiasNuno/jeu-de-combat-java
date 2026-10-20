public class Grappler extends Combattant{
    /* VARIABLES */
    private int compteurCoups;

    public Grappler(String nom){
        super(nom, 150, 15, 20);
        this.compteurCoups = 0;
    }

    /* METHODES */
    @Override public void lanceAttaqueDebase(Combattant cible){
        this.compteurCoups++;
        if(compteurCoups >=2 && this.jaugeSpecial < 3){
            this.jaugeSpecial++;
            this.compteurCoups = 0;
        }
        cible.recevoirDegats(this.attaqueDeBase);
    }

    @Override public void lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            cible.recevoirDegats(this.attaqueDeBase * 2);
            this.jaugeSpecial --;
        }
    }

    @Override public void lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            cible.recevoirDegats(this.attaqueDeBase * 3);
            this.jaugeSpecial -= 2;
        }
    }

    @Override public void lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            cible.recevoirDegats(this.attaqueDeBase * 5);
            this.jaugeSpecial -= 3;
        }
    }
}

