import java.util.*;

import services.compteService;

public class Main {
    public static void main(String[] args) {

        compteService service = new compteService();
        Scanner sc=new Scanner(System.in);
        System.out.println("*************************SAISI VOTRE CHOIX ********************************************");
        System.out.println("Creer un compte (1)");
        System.out.println("Se Connecter    (2)");
        System.out.print("saisi votre choix '1' ou '2' :");
        int choix =sc.nextInt();
        sc.nextLine();
        switch (choix){
            case 1:
                System.out.println("***********************Register User********************************");
                        service.Register_User(sc);//Register_User
                System.out.println("********************************************************************");
                break;
            case 2:
                System.out.println("***********************Login********************************");
                            System.out.print("Saisi votre gmail :");
                            String gmail=sc.nextLine();
                            System.out.print("Saisi votre password :");
                            String password=sc.nextLine();
                            service.Login(gmail,password); //Login
                System.out.println("********************************************************************");
                break;
            default:

                System.out.println("Choix Invalide !");
                System.out.println("Creer un compte (1)");
                System.out.println("Se Connecter  (2)");
                System.out.print("saisi votre choix '1' ou '2' :");
                int choix_ =sc.nextInt();
                sc.nextLine();
        }

        System.out.println("********************************************************************");
        sc.close();


    }
}