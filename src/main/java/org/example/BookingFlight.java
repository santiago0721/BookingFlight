package org.example;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookingFlight {

    BookingFlight(){

        List<String> data = SearchParameters();
        FlightOffersSearch Api = new FlightOffersSearch(data);


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
                );//"2024-05-08"

        for(String i :data) {
            System.out.println(i);
            info.add(scanner.nextLine());
        }

        return info;


    }

}
