package combattants;

import moteur.RapportAction;

public class Shoto extends Combattant {
/* CONSTRUCTEUR */
    public Shoto(String nom){
        super(nom, 100, 10, 50, 100 );
    }
    public Shoto(String nom, int vie,  int attaqueDeBase, int vitesse, int stunMax){
        super(nom, vie, attaqueDeBase, vitesse, stunMax);
    }

/* METHODES */
    @Override public RapportAction lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            this.jaugeSpecial --;
            int degats = this.attaqueDeBase * 2;
            int degatsStun = 15; //Capacite Special1
            return new RapportAction(degats, degatsStun, 0, 0, "Projectil",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            this.jaugeSpecial -= 2;
            int degats = this.attaqueDeBase * 3;
            this.recevoirJaugeSpecial(1); //capacite Special 2
            return new RapportAction(degats, 0, 0, 0, "rush kick",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            this.jaugeSpecial -= 3;
            int degats = (int) (this.attaqueDeBase * 4.5);
            this.jaugeStun = 0; //capacite ultime
            return new RapportAction(degats, 0, 0, 0, "Super projectil",
                    null, null);
        }
        return null;
    }
    @Override public int estimationDegatInflige(int niveauAtt){
        int estimation;
        switch (niveauAtt){
            case 1:
                estimation = this.attaqueDeBase;
                break;
            case 2:
                estimation = this.attaqueDeBase * 2;
                break;
            case 3:
                estimation = this.attaqueDeBase * 3;
                break;
            case 4:
                estimation = (int) (this.attaqueDeBase * 4.5);
                break;
            default:
                estimation = 0;
                break;
        }
        return (int) (estimation * 1.5); //Passif
    }
    @Override public Combattant copieCombattant(){
        Shoto copie = new Shoto(this.nom);
        return copie;
    }
    @Override public String getArchetype() { return "Shoto";}

}
