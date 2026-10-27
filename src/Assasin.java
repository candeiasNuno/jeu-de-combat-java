public class Assasin extends Combattant{
/* CONSTRUCTEUR */
    public Assasin(String nom){
        super(nom, 80, 12, 85, 80);
        this.jaugeSpecial = 1;
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
            cible.recevoirDegats((int) (this.attaqueDeBase * 2.5));
            this.jaugeSpecial -= 2;
        }
        return (int) (this.attaqueDeBase * 2.5);
    }
    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 3.5));
            this.jaugeSpecial -= 3;
        }
        return (int) (this.attaqueDeBase * 3.5);
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
}

