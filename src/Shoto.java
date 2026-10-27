public class Shoto extends Combattant {
/* CONSTRUCTEUR */
    public Shoto(String nom){
        super(nom, 100, 10, 50 );
    }
/* METHODES */
    @Override public int lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            cible.recevoirDegats(this.attaqueDeBase * 2);
            this.jaugeSpecial --;
        }
        return this.attaqueDeBase*2;
    }

    @Override public int lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            cible.recevoirDegats(this.attaqueDeBase * 3);
            this.jaugeSpecial -= 2;
        }
        return this.attaqueDeBase*3;
    }

    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            cible.recevoirDegats(this.attaqueDeBase * 5);
            this.jaugeSpecial -= 3;
        }
        return this.attaqueDeBase*5;
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
                estimation = this.attaqueDeBase * 5;
                break;
            default:
                estimation = 0;
                break;
        }
        return estimation;
    }

}
