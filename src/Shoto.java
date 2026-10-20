public class Shoto extends Combattant {
/* CONSTRUCTEUR */
    public Shoto(String nom){
        super(nom, 100, 10, 50 );
    }
/* METHODES */
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
