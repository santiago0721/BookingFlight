package org.example;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookingFlight {
    public final List<String> emails = new ArrayList<>();
    public final List<String> passwords = new ArrayList<>();

    BookingFlight(){

        FlightOffersSearch Api = new FlightOffersSearch();
        List<String> data = SearchParameters();


    }
    public static List<String> SearchParameters(){

        List<String> info = new ArrayList<>();
        System.out.println("bienvenido");
        Scanner scanner = new Scanner(System.in);
        List<String> data = Arrays.asList(
                "Ingrese las siglas del aeropuesto de origen:",
                "Ingrese las siglas del aeropuesto de destino:",
                "Ingrese la fecha de salida:",
                "Ingrese la fecha de llegada",
                "Ingrese la cantidad de personas"
                );

        for(String i :data) {
            System.out.println(i);
            info.add(scanner.nextLine());
        }

        return info;

    }

    public boolean emailExists(String email){
        return emails.stream().anyMatch(e -> e.equals(email));
    }

    public boolean passwordMatch(String email, String password){
        int index = emails.indexOf(email);

        if (index != -1){
            String storedPassword = passwords.get(index);
            return storedPassword.equals(password);
        }
        return false;
    }

    public boolean Register(String email, String password){
        if (!emailExists(email)){
            emails.add(email);
            passwords.add(password);
            System.out.println("Registro Exitoso");
        }
        return false;
    }

    public boolean Login(String email, String password){
        return emailExists(email) && passwordMatch(email, password);
    }

}
