import java.util.*;

import model.Annonces;
import model.FaavoriesExtendAnnonces;
import model.User;
import services.AnnonceService;
import services.FavoriesServices;
import services.compteService;

public class Main {
    public static void main(String[] args) {

        compteService service = new compteService();
        AnnonceService annonceService=new AnnonceService();
        FavoriesServices favoriesServices=new FavoriesServices();

        Scanner sc=new Scanner(System.in);
        User userConncte= null;
        int choix=0;
        while (choix != 2) {
            try{
                System.out.println("************************* MENU PRINCIPAL ********************************************");
                System.out.println("Creer un compte    (1)");
                System.out.println("Se Connecter       (2)");
                System.out.print("saisi votre choix  :");

                choix = sc.nextInt();
                sc.nextLine();

                switch (choix) {
                    case 1://feha Register_User
                        System.out.println("***********************Register User********************************");
                        service.Register_User(sc);//Register_User
                        System.out.println("********************************************************************");
                        break;

                    case 2://Login+AjouterAnnonces+ChercherAnoonces+AjouterAnnoncesAuFavorie

                        System.out.println("***********************Login********************************");
                        System.out.print("Saisi votre gmail :");
                        String gmail = sc.nextLine();
                        System.out.print("Saisi votre password :");
                        String password = sc.nextLine();
                        userConncte = service.Login(gmail, password); // Hna kn39lo  3LA l-user li rje3 men l-Login
                        if (userConncte != null) {

                            System.out.println("Connexion réussie !");

                            int choixAnnonce = 0;

                            while (choixAnnonce != 8) {

                                System.out.println("\n**** MENU ANNONCE ****");
                                System.out.println("Consulter Profil     (1)");
                                System.out.println("Modifier  Profil     (2)");
                                System.out.println("Ajouter annonce      (3)");
                                System.out.println("Modifier annonce     (4)");
                                System.out.println("Chercher annonce     (5)");
                                System.out.println("Consulter Favoris    (6)");
                                System.out.println("Supprimer Favoris    (7)");
                                System.out.println("Se Deconnecter       (8)");
                                System.out.println("Retour menu principal(9)");

                                System.out.print("Choix : ");

                                choixAnnonce = sc.nextInt();
                                sc.nextLine();

                                switch (choixAnnonce) {

                                    case 1:
                                        //ConsulterProfil
                                        if (userConncte != null) {
                                            service.consulterProfil(userConncte);

                                        } else {
                                            System.out.println("N'est pas Connecter !");
                                        }
                                        break;
                                    case 2:
                                        //ModifierINfoUSer
                                        if(userConncte!=null){
                                            service.modifier_info_perso(userConncte,sc);
                                        }
                                        else {
                                            System.out.println("N'est pas Connecter !");
                                        }
                                        break;


                                    case 3: // -------- AJOUTER ANNONCE --------

                                        if (userConncte != null) {
                                            service.publier_annonce(userConncte , sc);

                                        } else {
                                            System.out.println("N'est pas Connecter !");
                                        }
                                        break;

                                    case 4:
                                        //Modifier annonce:
                                        if(userConncte != null){
                                            System.out.print("Entrez l'ID de l'annonce à modifier: ");
                                            int idAnnonce = sc.nextInt();
                                            sc.nextLine(); // consommer le saut de ligne

                                            annonceService.modifier_annonce(userConncte, sc, idAnnonce);
                                        }
                                        else {
                                            System.out.println("N'est pas Connecter !");
                                        }
                                        break;


                                    case 5:// -------- CHERCHER ANNONCE --------

                                        System.out.print("Mot clé recherche : ");
                                        String search = sc.nextLine();

                                        List<Annonces> annonces = annonceService.chercher_annonce(search);

                                        int i = 0;

                                        while (i < annonces.size()) {

                                            Annonces a = annonces.get(i);

                                            System.out.println("------------------------");
                                            System.out.println("ID : " + a.getId_annonce());
                                            System.out.println("Titre : " + a.getTitre());
                                            System.out.println("Prix : " + a.getPrix());
                                            System.out.println("Type : " + a.getType());
                                            System.out.println("------------------------");

                                            i++;
                                        }
                                        if (!annonces.isEmpty()) { //AjouterAnnoncesFavoris
                                            System.out.println("\n(1) Ajouter une annonce aux favoris");
                                            System.out.println("(2) Retour");
                                            System.out.print("Votre choix : ");
                                            int optionFav = sc.nextInt();
                                            sc.nextLine(); // bach n-khwiw l-buffer

                                            if (optionFav == 1) {
                                                System.out.print("Saisi l'ID de l'annonce : ");
                                                int idAnnonceKhtara = sc.nextInt();
                                                sc.nextLine();

                                                // T-3eyyet l l-service jdid
                                                FavoriesServices favService = new FavoriesServices();
                                                favService.ajouter_favorie(idAnnonceKhtara, userConncte.getId());
                                            }
                                        } else {
                                            System.out.println("Aucune annonce trouvée pour : " + search);
                                            choixAnnonce=0;
                                        }

                                        break;
                                    case 6:// -------- Consulter list favorie --------
                                        List<FaavoriesExtendAnnonces> mesFavs = favoriesServices.Consulter_list_favorie(userConncte.getId());

                                        if (mesFavs.isEmpty()) {
                                            System.out.println("IL n'ya aucn favoris !");
                                        } else {
                                            // Formatage dyal l-waqt
                                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                                            for (FaavoriesExtendAnnonces f : mesFavs) {
                                                String dateStr = sdf.format(f.getDateAjoutFav()); // Smiya d l-getter li derti f l-Model

                                                System.out.println("====================================");
                                                System.out.println("ID                                      : " + f.getIdFavorie());
                                                System.out.println("Titre                                   : " + f.getTitre());
                                                System.out.println("Description                             : " + f.getDescription());
                                                System.out.println("Ajouté le                               : " + dateStr); // Ghadi t-ban b s-sway3 w d-dqayq
                                                System.out.println("Type (vente - Location)                 : " + f.getType());
                                                System.out.println("Telephone                               : " + f.getTelephone());
                                                System.out.println("Prix                                    : " + f.getPrix() + " DH");

                                                System.out.println("====================================");
                                            }
                                        }
                                        break;
                                    case 7:// -------- Supprimer favorie  --------

                                        System.out.print("Donne moi ID d'annonce que tu peux Supprimer :");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print  ("Vous etes sur que tu peux supprimer cette favoris (Y/N) \uD83D\uDDD1\uFE0F :");
                                        String y_n=sc.nextLine().toLowerCase();
                                        char x = y_n.charAt(0);
                                        if(x=='y'){
                                            favoriesServices.Supprimer_favorie(id);
                                            break;
                                        }else if(x=='n'){
                                            choix = 0;
                                        }else{
                                            System.out.print("Vous devez choisir soit (Y/N)");
                                        }
                                        break;

                                    case 8://Deconnecter
                                        System.out.print("Vous etes sur ? (Y/N) : ");
                                        String input = sc.nextLine().toLowerCase();
                                        x = input.charAt(0);
                                        if (x == 'y') {
                                            System.out.println("Au revoir !!!");
                                            choixAnnonce = 8; // Force sortie de la boucle des annonces
                                            userConncte = null; // Déconnexion
                                        } else if (x == 'n') {
                                            System.out.println("Vous restez connecté.");
                                            // rien à faire, reste dans la boucle
                                        } else {
                                            System.out.println("Vous devez choisir soit (Y/N)");
                                        }
                                        break;

                                    case 9://retour menu principal
                                        System.out.println("Retour menu principal...");
                                        choixAnnonce = 8; //force la sortie de la boucle des annonces
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

                    default:
                        System.out.println("Choix Invalide!");
                }

                System.out.println("********************************************************************");
            }
            catch (InputMismatchException e) {
                System.out.println("❌ Erreur: Donne moi un numero valide !");
                sc.nextLine();
                continue; // Bach t-rje3 l l-bdya d l-loop bla ma t-kemmel l-switch
            };




        };
        sc.close();
    };
};