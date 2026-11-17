package combattants;

import moteur.RapportAction;

public class Zoner extends Combattant{
/* CONSTRUCTEUR */
    public Zoner(String nom){
        super(nom, 90, 8, 65, 80);
    }

/* METHODES */
    @Override public RapportAction lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            this.jaugeSpecial --;
            int degats = (int) (this.attaqueDeBase * 2.5);
            return new RapportAction(degats, 0, 0, 0, "Mine explosif",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            this.jaugeSpecial -= 2;
            int degats = (int) (this.attaqueDeBase * 1.5);
            int jaugeSpecialPerdu = 2;
            return new RapportAction(degats, 0, 0, jaugeSpecialPerdu, "perte d'energie",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            this.jaugeSpecial -= 3;
            int degats = (int) (this.attaqueDeBase * 3.5);
            int jaugeSpecialPerdu = 4;
            int degatsStun = cible.getStunMax();
            return new RapportAction(degats, degatsStun, 0, jaugeSpecialPerdu, "Aspiration d'energie",
                    null, null);
        }
        return null;
    }
    @Override public int estimationDegatInflige(int niveauAtt){
        int estimation = switch (niveauAtt) {
            case 1 -> 20;
            case 2 -> 40;
            case 3 -> 60;
            case 4 -> 80;
            default -> 0;
        };
        return estimation;
    }
    @Override public Combattant copieCombattant(){
        Zoner copie = new Zoner(this.nom);
        return copie;
    }
    @Override public String getArchetype() { return "Zoner";}
}
