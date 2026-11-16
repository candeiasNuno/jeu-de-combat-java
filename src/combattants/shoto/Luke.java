package combattants.shoto;
import combattants.Combattant;
import combattants.Shoto;
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

    @Override public int lanceAttaqueDebase(Combattant cible){
        if(this.jaugeSpecial < 3){
            this.jaugeSpecial++;
        }
        this.bonusAttaque += 2;
        cible.recevoirDegats(this.attaqueDeBase + this.bonusAttaque);
        return this.attaqueDeBase + this.bonusAttaque;
    }
    @Override public int lanceChope(Combattant cible){
        cible.recevoirDegats((int) (this.attaqueDeBase * 1.5));
        return (int) ((this.attaqueDeBase + this.bonusAttaque) * 1.5);
    }
    @Override public int lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            this.bonusAttaque += 3;
            cible.recevoirDegats((this.attaqueDeBase + this.bonusAttaque) * 2 );
            this.jaugeSpecial --;
            cible.recevoirStun(15);//Capacite Special1
            return (this.attaqueDeBase + this.bonusAttaque) * 2 ;
        }
        return 0;
    }
    @Override public int lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            this.bonusAttaque += 4;
            cible.recevoirDegats((this.attaqueDeBase + this.bonusAttaque) * 3);
            this.jaugeSpecial -= 2;
            this.recevoirJaugeSpecial(1);//capacite Special 2
            return (this.attaqueDeBase + this.bonusAttaque) * 3;
        }
        return 0;
    }
    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            this.bonusAttaque += 4;
            cible.recevoirDegats((int) ((this.attaqueDeBase + this.bonusAttaque) * 4.5));
            this.jaugeSpecial -= 3;
            this.jaugeStun = 0; //capacite ultime
            return (int) ((this.attaqueDeBase + this.bonusAttaque) * 4.5);
        }
        return 0;
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
