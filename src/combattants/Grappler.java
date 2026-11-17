package combattants;
import moteur.RapportAction;

public class Grappler extends Combattant{
/* VARIABLES */
    private int compteurCoups;

/* CONSTRUCTEUR */
    public Grappler(String nom){
        super(nom, 140, 15, 20, 130);
        this.compteurCoups = 0;
    }

/* METHODES */
    @Override public RapportAction lanceAttaqueDebase(Combattant cible){
        this.compteurCoups++;
        if(compteurCoups >=2 && this.jaugeSpecial < 3){
            this.recevoirJaugeSpecial(1);
            this.compteurCoups = 0;
        }
        return new RapportAction(this.attaqueDeBase, 0, 0, 0, "Gros coup de poing",
                null, null);
    }
    @Override public RapportAction lanceChope(Combattant cible){
        int degats = this.attaqueDeBase * 2;
        int degatsStun = 25;
        this.recevoirJaugeSpecial(1);
        return new RapportAction(degats, degatsStun, 0, 0, "Super Chope",
                null, null);
    }
    @Override public RapportAction lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            this.jaugeSpecial --;
            int degats = (int) (this.attaqueDeBase * 1.6);
            int degatsStun = 40;
            return new RapportAction(degats, degatsStun, 0, 0, "Special 1",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            int degats = (int) (this.attaqueDeBase * 2.4);
            this.jaugeSpecial -= 2;
             this.soigne(10);
            return new RapportAction(degats, 0, 0, 0, "Special 2",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            int degats = this.attaqueDeBase * 4;
            this.jaugeSpecial -= 3;
            return new RapportAction(degats, 0, 0, 0, "Ultime",
                    null, null);
        }
        return null;
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
    @Override public String getArchetype() { return "Grappler";}
}

