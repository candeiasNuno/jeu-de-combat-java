package donnees;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import combattants.Combattant;
import combattants.Shoto;
import combattants.Assasin;
import combattants.Grappler;
import combattants.shoto.Luke;
import moteur.Roster;
import combattants.Zoner;

public class Sauvegarde {
/* METHODES */
    public void sauvegardeRoster(Roster roster){
        Path chemin = Paths.get("sauvegarde_roster.csv");
        List<String> lignesAEcrire = new ArrayList<>();

        // Transformation des objets en format texte (CSV)
        for (Combattant c : roster.getRoster()){
            String ligne = c.getClass().getSimpleName() + ";" + c.getNom() + ";" + c.getVie() + ";" + c.getAttaqueDeBase() + ";"
                    + c.getVitesse() + ";" + c.getStunMax() + ";" + c.getJaugeSpecial() + ";" + c.getEstVivant();
            lignesAEcrire.add(ligne);
        }

        try {
            // Écrase l'ancien fichier (TRUNCATE) ou le crée (CREATE)
            Files.write(chemin, lignesAEcrire, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Roster sauvegardé avec succès !");
        } catch (IOException e){
            System.out.println("Erreur grave lors de la sauvegarde du roster : " + e.getMessage());
        }
    }
    public Roster chargerRoster(){
        Roster ros = new Roster();
        Path chemin = Paths.get("sauvegarde_roster.csv");

        try {
            List<String> lignesLues = Files.readAllLines(chemin);

            for(String ligne : lignesLues){
                // Découpage des statistiques séparées par des points-virgules
                String[] morceaux = ligne.split(";");

                String typeCombattant = morceaux[0];
                String nomCombattant = morceaux[1];
                String vieCombattant = morceaux[2];
                String attaqueDeBaseCombattant = morceaux[3];
                String vitesseCombattant = morceaux[4];
                String stunMax = morceaux[5];
                String jaugeSpecial = morceaux[6];
                String vivant = morceaux[7];

                Combattant combattant = null;

                // Instanciation dynamique selon la classe lue dans le fichier
                switch (typeCombattant){
                    case "Shoto":
                        combattant = new Shoto(nomCombattant);
                        break;
                    case "Assasin":
                        combattant = new Assasin(nomCombattant);
                        break;
                    case "Grappler":
                        combattant = new Grappler(nomCombattant);
                        break;
                    case "Zoner":
                        combattant = new Zoner(nomCombattant);
                        break;
                    case "Luke":
                        combattant = new Luke(Integer.parseInt(vieCombattant), Integer.parseInt(attaqueDeBaseCombattant), Integer.parseInt(vitesseCombattant),
                                Integer.parseInt(stunMax));
                        break;
                    default:
                        System.out.println("Classe inconnue : " + typeCombattant);
                        break;
                }

                if (combattant != null) {
                    ros.ajouterCombattant(combattant);
                }
            }
            System.out.println("Chargement réussi !");

        } catch (IOException e){
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        return ros;
    }
}