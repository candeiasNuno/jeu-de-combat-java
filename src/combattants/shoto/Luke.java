package combattants.shoto;
import combattants.Combattant;
import combattants.Shoto;
import moteur.RapportAction;

//Passif Luke : Bonus d'attaque a chaque attaque réussit des la 1er attaque
    //Attaque de base : +2
    //Special 1 : +3
    //Special 2 : +4
    //Ultime : +5
public class Luke extends Shoto {
/* CONSTRUCTEUR */
    public Luke(int vie,  int attaqueDeBase, int vitesse, int stunMax){
        super("LUKE", vie, attaqueDeBase, vitesse, stunMax);
    }

/* METHODES */

    @Override public RapportAction lanceAttaqueDebase(Combattant cible){
        if(this.jaugeSpecial < 3){
            this.recevoirJaugeSpecial(1);
        }
        this.bonusAttaque += 2; //a voir pour mettre le mesage personaliser
        int degats = this.attaqueDeBase + this.bonusAttaque;
        return new RapportAction(degats, 0, 0, 0, "SAND BLAST",
                null, null);
    }
    @Override public RapportAction lanceChope(Combattant cible){
        int degats = (int) (this.attaqueDeBase * 1.5) + this.bonusAttaque;
        return new RapportAction(degats, 0, 0, 0, "Placage au Sol",
                null, null);
    }
    @Override public RapportAction lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            this.jaugeSpecial --;
            this.bonusAttaque += 3;
            int degats = (this.attaqueDeBase + this.bonusAttaque) * 2 ;
            int degatsStun = 15;//Capacite Special1
            return new RapportAction(degats, degatsStun, 0, 0, "Vulcan Blast",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            this.bonusAttaque += 4;
            this.jaugeSpecial -= 2;
            int degats = (this.attaqueDeBase + this.bonusAttaque) * 3;
            this.recevoirJaugeSpecial(1); //capacite Special 2
            return new RapportAction(degats, 0, 0, 0, "Eraser",
                    null, null);
        }
        return null;
    }
    @Override public RapportAction lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            this.bonusAttaque += 4;
            this.jaugeSpecial -= 3;
            this.jaugeStun = 0; //capacite ultime
            int degats = (int) ((this.attaqueDeBase + this.bonusAttaque) * 4.5);
            return new RapportAction(degats, 0, 0, 0, "Pale Rider",
                    null, null);
        }
        return null;
    }
    @Override public int estimationDegatInflige(int niveauAtt){
        int estimation;
        switch (niveauAtt){
            case 1:
                estimation = this.attaqueDeBase + this.bonusAttaque;
                break;
            case 2:
                estimation = (this.attaqueDeBase + this.bonusAttaque) * 2;
                break;
            case 3:
                estimation = (this.attaqueDeBase + this.bonusAttaque) * 3;
                break;
            case 4:
                estimation = (int) ((this.attaqueDeBase + this.bonusAttaque) * 4.5);
                break;
            default:
                estimation = 0;
                break;
        }
        return (int) (estimation * 1.5); //Passif
    }
    @Override public Combattant copieCombattant(){
        Luke luke = new Luke(this.vieMax, this.attaqueDeBase, this.vitesse, this.stunMax);
        return luke;
    }
}
