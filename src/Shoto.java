public class Shoto extends Combattant {
/* CONSTRUCTEUR */
    public Shoto(String nom){
        super(nom, 100, 10, 50, 100 );
    }

/* METHODES */
    @Override public int lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            cible.recevoirDegats(this.attaqueDeBase * 2);
            this.jaugeSpecial --;
            cible.recevoirStun(15);//Capacite Special1
            return this.attaqueDeBase * 2;
        }
        return 0;
    }
    @Override public int lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            cible.recevoirDegats(this.attaqueDeBase * 3);
            this.jaugeSpecial -= 2;
            this.recevoirJaugeSpecial(1);//capacite Special 2
            return this.attaqueDeBase * 3;
        }
        return 0;
    }
    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 4.5));
            this.jaugeSpecial -= 3;
            this.jaugeStun = 0; //capacite ultime
            return (int) (this.attaqueDeBase * 4.5);
        }
        return 0;
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

}
