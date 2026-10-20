import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Sauvegarde {

    public void sauvegardeRoster(Roster roster){
        Path chemin = Paths.get("sauvegarde_roster.csv");
        List<String> lignesAEcrire = new ArrayList<>();

        // Transformation des objets en format texte (CSV)
        for (Combattant c : roster.getRoster()){
            String ligne = c.getClass().getSimpleName() + ";" + c.getNom() + ";" + c.getVie() + ";" + c.getAttaqueDeBase() + ";"
                    + c.getVitesse() + ";" + c.getJaugeSpecial() + ";" + c.getEstVivant();
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