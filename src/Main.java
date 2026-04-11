import java.util.*;

import model.Annonces;
import model.FaavoriesExtendAnnonces;
import model.User;
import services.AdminService;
import services.AnnonceService;
import services.FavoriesServices;
import services.compteService;

public class Main {
    public static void main(String[] args) {

        compteService service = new compteService();
        AnnonceService annonceService=new AnnonceService();
        FavoriesServices favoriesServices=new FavoriesServices();
        AdminService adminService=new AdminService();

        Scanner sc=new Scanner(System.in);
        User userConncte= null;
        int choix=0;
        while (choix != 2) {
            try{
                System.out.println("************************* MENU de connexion ********************************************");
                System.out.println("Creer un compte    (1)");
                System.out.println("Se Connecter       (2)");
                System.out.print("saisir votre choix  :");

                choix = sc.nextInt();
                sc.nextLine();

                switch (choix) {
                    case 1://feha Register_User
                        System.out.println("***********************Enregistrer utilisateur********************************");
                        service.Register_User(sc);//Register_User
                        System.out.println("********************************************************************");
                        break;

                    case 2://Login+AjouterAnnonces+ChercherAnoonces+AjouterAnnoncesAuFavorie

                        boolean loginReussi=false;
                        while (!loginReussi){
                            try{

                                System.out.println("***********************Login********************************");
                                System.out.print("Saisir votre gmail :");
                                String gmail = sc.nextLine();

                                System.out.print("Saisir votre password :");
                                String password = sc.nextLine();
                                userConncte = service.Login(gmail, password); // Hna kn39lo  3LA l-user li rje3 men l-Login
                                if(userConncte!=null){
                                    loginReussi=true;
                                }

                            }
                            catch (Exception e){
                                System.out.println("Erreur lors de la connection. Veuillez réessayer plus tard!");
                            }
                        }
                        if (userConncte != null) {

                            if(userConncte.getRole().equals("Admin")){

                                System.out.println("\n**** MENU ADMIN ****");
                                int choixAdmin =0;

                                while(choixAdmin!=6){
                                    System.out.println("Liste de tous les utilisateurs   (1)");
                                    System.out.println("Liste de toutes les annonces     (2)");
                                    System.out.println("Chercher annonce                 (3)");
                                    System.out.println("Consulter Favoris                (4)");
                                    System.out.println("Se Deconnecter                   (5)");
                                    System.out.println("Arreter le programe              (6)");
                                    System.out.print("Choix : ");

                                    while (!sc.hasNextInt()) { // ma7ed input machi ra9m / hasNextInt = check if int
                                        System.out.println("Vous devez saisir un chiffre!!");
                                        System.out.print("Saisir votre choix: ");
                                        sc.next();
                                    }

                                    choixAdmin=sc.nextInt();
                                    sc.nextLine();
                                    switch (choixAdmin){
                                            case 1:
                                                        int nombreUser=adminService.CountUser();//function CountUser
                                                        System.out.println("IL y'a "+nombreUser+" utilisateurs Dans Notre Systeme ");
                                                        adminService.ListUser();//function ListUser
                                                        int ChoixAn =0;
                                                        while(ChoixAn!=3){
                                                            System.out.println("Consulter Annonce d'un utilisateur  (1) :");
                                                            System.out.println("Supprimer un utilisateur            (2) :");
                                                            System.out.println("Retour au menu principale           (3) :");
                                                            System.out.print("Entrer Votre choix :");

                                                            while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                System.out.println("Vous devez saisir un chiffre!!");
                                                                System.out.print("Saisir votre choix: ");
                                                                sc.next();
                                                            }
                                                            ChoixAn=sc.nextInt();
                                                            switch (ChoixAn){
                                                                case 1:
                                                                    System.out.print("Saisir Id pour Consulter les Annonces :");

                                                                    while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                        System.out.println("Vous devez saisir un chiffre!!");
                                                                        System.out.print("Saisir votre choix: ");
                                                                        sc.next();
                                                                    }

                                                                    int Id_ann=sc.nextInt();
                                                                    // 1. Kan-akhdou l'objet li rje3 men l-methode
                                                                    List<Annonces> listeAnn = annonceService.consulter_toutes_annonces_user(Id_ann);

                                                                    // 2. Kan-t-checkiw wach l9ah (machi null)
                                                                    if (listeAnn != null && !listeAnn.isEmpty()) {
                                                                        System.out.println("\n--- ✨ Détails de l'Annonce ---");
                                                                        for (Annonces a : listeAnn) {

                                                                            System.out.println("Id annonces            : " + a.getId_annonce());
                                                                            System.out.println("Titre                  : " + a.getTitre());
                                                                            System.out.println("Description            : " + a.getDescription());
                                                                            System.out.println("Prix                   : " + a.getPrix() + " DH");
                                                                            System.out.println("Type                   : " + a.getType());
                                                                            System.out.println("Telephone              : " + a.getTelephone());
                                                                            System.out.println("Ville                  : " + a.getIdVille());
                                                                            System.out.println("Date Publication       : " + a.getDate_publication());
                                                                            System.out.println("------------------------------------\n");

                                                                        }
                                                                        int ChoixAnn =0;
                                                                        while(ChoixAnn!=3) {

                                                                            System.out.println("Ajouter au  favories                (1) :");
                                                                            System.out.println("Supprimer une annonces  d'un user   (2) :");
                                                                            System.out.println("Retour au menu principale           (3) :");
                                                                            System.out.print("Entrer Votre choix :");

                                                                            while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                                System.out.println("Vous devez saisir un chiffre!!");
                                                                                System.out.print("Saisir votre choix: ");
                                                                                sc.next();
                                                                            }
                                                                            ChoixAnn = sc.nextInt();
                                                                            switch (ChoixAnn){
                                                                                case 1:
                                                                                    System.out.print("Saisir Id de l'annonce pour Ajouter Au Favories:");

                                                                                    while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                                        System.out.println("Vous devez saisir un chiffre!!");
                                                                                        System.out.print("Saisir votre choix: ");
                                                                                        sc.next();
                                                                                    }

                                                                                    int ajouAnnonces=sc.nextInt();
                                                                                    sc.nextLine();

                                                                                    favoriesServices.ajouter_favorie(ajouAnnonces,userConncte.getId());
                                                                                    break;
                                                                                case 2:
                                                                                        System.out.print("Saisir Id de l'annonce pour Supprimer Annonces User:");

                                                                                        while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                                            System.out.println("Vous devez saisir un chiffre!!");
                                                                                            System.out.print("Saisir votre choix: ");
                                                                                            sc.next();
                                                                                        }

                                                                                        int supp=sc.nextInt();
                                                                                        sc.nextLine();

                                                                                        System.out.print("☣️ Ecrire 'supprimerAnnoncesUser' pour Suppression : ");
                                                                                        String validerSuppression=sc.nextLine();
                                                                                        if(validerSuppression.equals("supprimerAnnoncesUser")){
                                                                                            annonceService.Supprimer_Annonces(supp);
                                                                                        }else{
                                                                                            System.out.println("Aucun Annonces Supprimer  ");
                                                                                        }

                                                                                    break;
                                                                                case 3:
                                                                                    choixAdmin =0;
                                                                                    break;
                                                                                default:
                                                                                    System.out.println("Choix Invalide !!!");
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }else {
                                                                        // Ila kant l-methode rej3at null (Id ma-kayench)
                                                                        System.out.println("⚠️ Erreur : Aucune annonce trouvée avec l'ID " + Id_ann);
                                                                    }

                                                                    break;
                                                                case 2:
                                                                    System.out.print("Saisir Id de l'utilisateur pour Supprimer :");

                                                                    while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                        System.out.println("Vous devez saisir un chiffre!!");
                                                                        System.out.print("Saisir votre choix: ");
                                                                        sc.next();
                                                                    }

                                                                    int supp=sc.nextInt();
                                                                    sc.nextLine();

                                                                    if(userConncte.getId()==supp){
                                                                        System.out.println("❌ Erreur : Vous ne pouvez pas supprimer votre propre compte administrateur !");
                                                                    }
                                                                    else{
                                                                        System.out.print("☣️ Ecrire 'supprimerUser' pour Suppression : ");
                                                                        String validerSuppression=sc.nextLine();
                                                                        if(validerSuppression.equals("supprimerUser")){
                                                                            adminService.SupprimerUser(supp);
                                                                        }else{
                                                                            System.out.println("Aucun user Supprimer  ");
                                                                        }
                                                                    }

                                                                    break;

                                                                case 3:
                                                                    choixAdmin =0;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                            case 2:

                                                        int nombreAnnonces=adminService.CountAnnonces();//function CountUser
                                                        System.out.println("IL y'a "+nombreAnnonces+" annonces Dans Notre Systeme ");
                                                        adminService.ListAnnonce();
                                                        int ChoixAnn =0;
                                                        while(ChoixAnn!=3) {

                                                            System.out.println("Ajouter au  favories                (1) :");
                                                            System.out.println("Supprimer une annonces  d'un user   (2) :");
                                                            System.out.println("Retour au menu principale           (3) :");
                                                            System.out.print("Entrer Votre choix :");

                                                            while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                System.out.println("Vous devez saisir un chiffre!!");
                                                                System.out.print("Saisir votre choix: ");
                                                                sc.next();
                                                            }
                                                            ChoixAnn = sc.nextInt();
                                                            switch (ChoixAnn){
                                                                case 1:
                                                                    System.out.print("Saisir Id de l'annonce pour Ajouter Au Favories:");

                                                                    while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                        System.out.println("Vous devez saisir un chiffre!!");
                                                                        System.out.print("Saisir votre choix: ");
                                                                        sc.next();
                                                                    }

                                                                    int ajouAnnonces=sc.nextInt();
                                                                    sc.nextLine();

                                                                    favoriesServices.ajouter_favorie(ajouAnnonces,userConncte.getId());
                                                                    break;
                                                                case 2:
                                                                    System.out.print("Saisir Id de l'annonce pour Supprimer Annonces User:");

                                                                    while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                                        System.out.println("Vous devez saisir un chiffre!!");
                                                                        System.out.print("Saisir votre choix: ");
                                                                        sc.next();
                                                                    }

                                                                    int supp=sc.nextInt();
                                                                    sc.nextLine();

                                                                    System.out.print("☣️ Ecrire 'supprimerAnnoncesUser' pour Suppression : ");
                                                                    String validerSuppression=sc.nextLine();
                                                                    if(validerSuppression.equals("supprimerAnnoncesUser")){
                                                                        annonceService.Supprimer_Annonces(supp);
                                                                    }else{
                                                                        System.out.println("Aucun Annonces Supprimer  ");
                                                                    }

                                                                    break;
                                                                case 3:
                                                                    choixAdmin =0;
                                                                    break;
                                                                default:
                                                                    System.out.println("Choix Invalide !!!");
                                                                    break;
                                                            }
                                                        }

                                                        break;
                                            case 3:
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
                                                        System.out.print("Saisir l'ID de l'annonce : ");

                                                        while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                            System.out.println("Vous devez saisir un chiffre!!");
                                                            System.out.print("Saisir votre choix: ");
                                                            sc.next();
                                                        }

                                                        int idAnnonceKhtara = sc.nextInt();
                                                        sc.nextLine();

                                                        // T-3eyyet l l-service jdid
                                                        FavoriesServices favService = new FavoriesServices();
                                                        favService.ajouter_favorie(idAnnonceKhtara, userConncte.getId());
                                                    }
                                                } else {
                                                    System.out.println("Aucune annonce trouvée pour : " + search);
                                                }

                                                break;
                                            case 4 :
                                                List<FaavoriesExtendAnnonces> mesFavs = favoriesServices.Consulter_list_favorie(userConncte.getId());

                                                if (mesFavs.isEmpty()) {
                                                    System.out.println("IL n'ya aucune favorie !");
                                                } else {
                                                    // Formatage dyal l-waqt
                                                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                                                    for (FaavoriesExtendAnnonces f : mesFavs) {
                                                        String dateStr = sdf.format(f.getDateAjoutFav()); // Smiya d l-getter li derti f l-Model

                                                        System.out.println("====================================");
                                                        System.out.println("ID                                      : " + f.getId_annonce());
                                                        System.out.println("Titre                                   : " + f.getTitre());
                                                        System.out.println("Description                             : " + f.getDescription());
                                                        System.out.println("Ajouté le                               : " + dateStr); // Ghadi t-ban b s-sway3 w d-dqayq
                                                        System.out.println("Type (vente - Location)                 : " + f.getType());
                                                        System.out.println("Telephone                               : " + f.getTelephone());
                                                        System.out.println("Prix                                    : " + f.getPrix() + " DH");

                                                        System.out.println("====================================");
                                                    }
                                                    System.out.print("Donne moi ID de l'annonce que vous voulez Supprimer :");

                                                    while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                        System.out.println("Vous devez saisir un chiffre!!");
                                                        System.out.print("Saisir votre choix: ");
                                                        sc.next();
                                                    }

                                                    int id=sc.nextInt();
                                                    sc.nextLine();
                                                    System.out.print  ("Vous etes sur que vous voulez supprimer cette favoris (Y/N) \uD83D\uDDD1\uFE0F :");
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
                                                }

                                                break;
                                            case 5://Deconnecter
                                                        System.out.print("Vous etes sur ? (Y/N) : ");
                                                        String input = sc.nextLine().toLowerCase();
                                                        int x = input.charAt(0);
                                                        if (x == 'y') {
                                                            System.out.println("Au revoir !!!");
                                                            choixAdmin = 10; // Force sortie de la boucle des annonces
                                                            userConncte = null; // Déconnexion
                                                        } else if (x == 'n') {
                                                            System.out.println("Vous étes encore connecté.");
                                                            choixAdmin = 0;
                                                            // rien à faire, reste dans la boucle
                                                        } else {
                                                            System.out.println("Vous devez choisir soit (Y/N)");
                                                        }
                                                        break;

                                            case 6://retour menu principal
                                                        System.out.println("Au revoir Admin 🙂 !!!");
                                                        choix = 0;
                                                        userConncte = null; // Déconnexion

                                                        break;

                                            default:
                                                System.out.println("Choix invalide !");
                                        }
                                }

                            }else{


                            System.out.println("Connexion réussie !");

                            //_______________________________________________________________________________________________
                            int choixAnnonce = 0;

                            while (choixAnnonce != 10) {

                                System.out.println("\n**** MENU PRINCIPAL ****");
                                System.out.println("Consulter Profil        (1)");
                                System.out.println("Modifier  Profil        (2)");
                                System.out.println("Ajouter annonce         (3)");
                                System.out.println("Modifier annonce        (4)");
                                System.out.println("Chercher annonce        (5)");
                                System.out.println("Supprimer annonce       (6)");
                                System.out.println("Consulter Favoris       (7)");
                                System.out.println("Supprimer Favoris       (8)");
                                System.out.println("Arreter le programe     (9)");
                                System.out.println("Se Deconnecter          (10)");

                                System.out.print("Choix : ");

                                while (!sc.hasNextInt()) { // ma7ed input machi ra9m / hasNextInt = check if int
                                    System.out.println("Vous devez saisir un chiffre!!");
                                    System.out.print("Saisir votre choix: ");
                                    sc.next();
                                }

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

                                            while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                System.out.println("Vous devez saisir un chiffre!!");
                                                System.out.print("Saisir votre choix: ");
                                                sc.next();
                                            }

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
                                                System.out.print("Saisir l'ID de l'annonce : ");

                                                while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                    System.out.println("Vous devez saisir un chiffre!!");
                                                    System.out.print("Saisir votre choix: ");
                                                    sc.next();
                                                }

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

                                    case 6: //---------Supprimer annonces ----------
                                        //Supprimer  annonce:
                                        if(userConncte != null){
                                            System.out.print("Entrez l'ID de l'annonce à Supprimer: ");

                                            while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                                System.out.println("Vous devez saisir un chiffre!!");
                                                System.out.print("Saisir votre choix: ");
                                                sc.next();
                                            }

                                            int idAnnonce = sc.nextInt();
                                            sc.nextLine(); // consommer le saut de ligne

                                            annonceService.Supprimer_Annonces(idAnnonce);
                                        }
                                        else {
                                            System.out.println("N'est pas Connecter !");
                                        }
                                        break;


                                    case 7:// -------- Consulter list favorie --------
                                        List<FaavoriesExtendAnnonces> mesFavs = favoriesServices.Consulter_list_favorie(userConncte.getId());

                                        if (mesFavs.isEmpty()) {
                                            System.out.println("IL n'ya aucune favorie !");
                                        } else {
                                            // Formatage dyal l-waqt
                                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                                            for (FaavoriesExtendAnnonces f : mesFavs) {
                                                String dateStr = sdf.format(f.getDateAjoutFav()); // Smiya d l-getter li derti f l-Model

                                                System.out.println("====================================");
                                                System.out.println("ID                                      : " + f.getId_annonce());
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
                                    case 8:// -------- Supprimer favorie  --------

                                        System.out.print("Donne moi ID de l'annonce que vous voulez Supprimer :");

                                        while (!sc.hasNextInt()) { // ma7ed input machi r9m
                                            System.out.println("Vous devez saisir un chiffre!!");
                                            System.out.print("Saisir votre choix: ");
                                            sc.next();
                                        }

                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print  ("Vous etes sur que vous voulez supprimer cette favoris (Y/N) \uD83D\uDDD1\uFE0F :");
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

                                    case 9://Deconnecter
                                        System.out.print("Vous etes sur ? (Y/N) : ");
                                        String input = sc.nextLine().toLowerCase();
                                        x = input.charAt(0);
                                        if (x == 'y') {
                                            System.out.println("Au revoir !!!");
                                            choixAnnonce = 10; // Force sortie de la boucle des annonces
                                            userConncte = null; // Déconnexion
                                        } else if (x == 'n') {
                                            System.out.println("Vous restez connecté.");
                                            choixAnnonce = 0;
                                            // rien à faire, reste dans la boucle
                                        } else {
                                            System.out.println("Vous devez choisir soit (Y/N)");
                                        }
                                        break;

                                    case 10://retour menu principal
                                        System.out.println("Au revoir !!!");
                                        choix = 0;
                                        userConncte = null; // Déconnexion

                                        break;

                                    default:
                                        System.out.println("Choix invalide !");
                                }
                            }

                        }
                        }
                        else {
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
                System.out.println("❌ Erreur: Saisir un numero valide !");
                sc.nextLine();
                continue; // Bach t-rje3 l l-bdya d l-loop bla ma t-kemmel l-switch
            };


        };
        sc.close();
    };
};