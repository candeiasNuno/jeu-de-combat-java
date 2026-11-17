package combattants;

import moteur.RapportAction;

public class Assasin extends Combattant{
/* CONSTRUCTEUR */
    public Assasin(String nom){
        super(nom, 80, 12, 85, 80);
        this.jaugeSpecial = 1;
    }

/* METHODES */
    @Override public RapportAction lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            this.jaugeSpecial --;
            int degats = this.attaqueDeBase * 2;
            int degatsStun = 20;
            return new RapportAction(degats, degatsStun, 0, 0, "Coup en traite",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            this.jaugeSpecial -= 2;
            int degats = (int) (this.attaqueDeBase * 2.5);
            int jaugeSpecialPerdu = 2;
            return new RapportAction(degats, 0, 0, jaugeSpecialPerdu, "Vol special",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            this.jaugeSpecial -= 3;
            int degatsTotaux = (int) ((this.attaqueDeBase * 3.5) + ((cible.getVieMax() - cible.getVie()) / 4));
            return new RapportAction(degatsTotaux, 0, 0, 0, "Aspiration d'ame",
                    null, null);
        }
        return null;
    }
    @Override public int estimationDegatInflige(int niveauAtt){
        int estimation = switch (niveauAtt) {
            case 1 -> this.attaqueDeBase;
            case 2 -> this.attaqueDeBase * 2;
            case 3 -> (int) (this.attaqueDeBase * 2.5);
            case 4 -> (int) (this.attaqueDeBase * 3.5);
            default -> 0;
        };
        return estimation;
    }
    @Override public Combattant copieCombattant(){
        Assasin copie = new Assasin(this.nom);
        return copie;
    }
    @Override public String getArchetype() {return "Assasin";}
}

