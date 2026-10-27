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
            cible.recevoirStun(20);
            return this.attaqueDeBase *2 ;
        }
        return 0;
    }
    @Override public int lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 2.5));
            cible.consomation(2);
            this.jaugeSpecial -= 2;
            return (int) (this.attaqueDeBase * 2.5);
        }
        return 0;
    }
    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            int degatsTotaux = (int) ((this.attaqueDeBase * 3.5) + ((cible.getVieMax() - cible.getVie()) / 4));
            cible.recevoirDegats(degatsTotaux);
            this.jaugeSpecial -= 3;
            return degatsTotaux;
        }
        return 0;
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

