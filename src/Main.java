import java.util.*;

import model.Annonces;
import model.User;
import services.AnnonceService;
import services.compteService;

public class gMain {
    public static void main(String[] args) {

        compteService service = new compteService();
        AnnonceService annonceService=new AnnonceService();
        Scanner sc=new Scanner(System.in);
        User userConncte= null;
        int choix=0;
        while (choix != 5) {
            System.out.println("************************* MENU PRINCIPAL ********************************************");
            System.out.println("Creer un compte    (1)");
            System.out.println("Se Connecter       (2)");
            System.out.println("Consulter Profil   (3)");
            System.out.println("Modifier  Profil   (4)");
            System.out.println("Se Deconnecter     (5)");
            System.out.print("saisi votre choix  :");

            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("***********************Register User********************************");
                    service.Register_User(sc);//Register_User
                    System.out.println("********************************************************************");
                    break;
                case 2:
                    System.out.println("***********************Login********************************");
                    System.out.print("Saisi votre gmail :");
                    String gmail = sc.nextLine();
                    System.out.print("Saisi votre password :");
                    String password = sc.nextLine();
                    userConncte = service.Login(gmail, password); // Hna kn39lo  3LA l-user li rje3 men l-Login
                    if (userConncte != null) {

                        System.out.println("Connexion réussie !");

                        int choixAnnonce = 0;

                        while (choixAnnonce != 3) {

                            System.out.println("\n**** MENU ANNONCE ****");
                            System.out.println("Ajouter annonce      (1)");
                            System.out.println("Chercher annonce     (2)");
                            System.out.println("Retour menu principal(3)");
                            System.out.print("Choix : ");

                            choixAnnonce = sc.nextInt();
                            sc.nextLine();

                            switch (choixAnnonce) {

                                // -------- AJOUTER ANNONCE --------

                                case 1:

                                    System.out.print("Titre : ");
                                    String titre = sc.nextLine();

                                    System.out.print("Description : ");
                                    String description = sc.nextLine();

                                    System.out.print("Prix : ");
                                    double prix = sc.nextDouble();
                                    sc.nextLine();

                                    System.out.print("Telephone : ");
                                    String tel = sc.nextLine();

                                    System.out.print("Type : ");
                                    String type = sc.nextLine();

                                    System.out.print("Ville  (1=Oujda,2=Casablanca,3=Rabat,4=Tanger)  : ");
                                    int Idville = sc.nextInt();

                                    System.out.print("Ville  (1=Appartement,2=Villa,3=Terrain,4=Bureau)  : ");
                                    int IdCategorie = sc.nextInt();

                                    Annonces annonce = new Annonces(
                                            0,
                                            titre,
                                            description,
                                            prix,
                                            tel,
                                            type,

                                            null,
                                            userConncte.getId() ,// id user automatique
                                            Idville,
                                            IdCategorie

                                    );

                                    service.publier_annonce(annonce);

                                    break;

                                // -------- CHERCHER ANNONCE --------

                                case 2:

                                    System.out.print("Mot clé recherche : ");
                                    String search = sc.nextLine();

                                    List<Annonces> annonces = annonceService.chercher_annonce(search);

                                    int i = 0;

                                    while (i < annonces.size()) {

                                        Annonces a = annonces.get(i);

                                        System.out.println("ID : " + a.getId_annonce());
                                        System.out.println("Titre : " + a.getTitre());
                                        System.out.println("Prix : " + a.getPrix());
                                        System.out.println("Type : " + a.getType());
                                        System.out.println("------------------------");

                                        i++;
                                    }

                                    break;

                                case 3:
                                    System.out.println("Retour menu principal...");
                                    break;

                                default:
                                    System.out.println("Choix invalide !");
                            }
                        }

                    } else {
                        System.out.println("Email ou mot de passe incorrect !");
                    }
                    System.out.println("********************************************************************");
                    break;
                case 3:
                    if (userConncte != null) {
                        service.consulterProfil(userConncte);
                    } else {
                        System.out.println("N'est pas Connecter !");
                    }
                    break;
                case 4:
                    if(userConncte!=null){
                        service.modifier_info_perso(userConncte,sc);
                    }
                    else {
                        System.out.println("N'est pas Connecter !");
                    }
                    break;
                case 5:
                    System.out.print("Vous etes sur (Y/N) : ");
                    String input = sc.nextLine().toLowerCase();
                    char x = input.charAt(0);
                    if(x=='y'){
                        System.out.println("Au revoir !!!");
                        break;
                    }else if(x=='n'){
                        choix = 0;
                    }else{
                        System.out.print("Vous devez choisir soit (Y/N)");
                    }
                    break;

                default:
                    System.out.println("Choix Invalide!");
            }

            System.out.println("********************************************************************");
        }
        sc.close();


    }
}