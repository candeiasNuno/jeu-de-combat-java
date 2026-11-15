package combattants;

public class Zoner extends Combattant{
/* CONSTRUCTEUR */
    public Zoner(String nom){
        super(nom, 90, 8, 65, 80);
    }

/* METHODES */
    @Override public int lanceAttaqueSpecial1(Combattant cible){
        if (this.jaugeSpecial >= 1 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 2.5));
            this.jaugeSpecial --;
            return ((int) (this.attaqueDeBase * 2.5)) ;
        }
        return 0;
    }
    @Override public int lanceAttaqueSpecial2(Combattant cible){
        if (this.jaugeSpecial >= 2 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 1.5));
            cible.consomation(2);
            this.jaugeSpecial -= 2;
            return (int) (this.attaqueDeBase * 1.5);
        }
        return 0;
    }
    @Override public int lanceAttaqueUltime(Combattant cible){
        if (this.jaugeSpecial >= 3 ){
            cible.recevoirDegats((int) (this.attaqueDeBase * 3.5));
            cible.consomation(4);
            cible.recevoirStun(cible.getStunMax());
            this.jaugeSpecial -= 3;
            return (int) (this.attaqueDeBase * 3.5);
        }
        return 0;
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
}
