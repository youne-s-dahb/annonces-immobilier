import java.util.*;

import model.User;
import services.compteService;

public class Main {
    public static void main(String[] args) {

        compteService service = new compteService();
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
                        System.out.println("Connecté avec succès");
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