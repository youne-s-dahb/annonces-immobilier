import java.util.*;

import services.compteService;

public class Main {
    public static void main(String[] args) {

        compteService service = new compteService();

        System.out.println("***********************Register User********************************");
            Scanner sc=new Scanner(System.in);
            service.Register_User(sc);
        System.out.println("********************************************************************");


    }
}