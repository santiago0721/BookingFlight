package org.example;
import java.util.*;

public class BookingFlight {

    BookingFlight(){
        try {
            List<String> data = SearchParameters();
            FlightOffersSearch Api = new FlightOffersSearch(data);
        } catch (Exception e) {
            System.out.println("Error al crear la reserva de vuelo: " + e.getMessage());
        }
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
            String input = scanner.nextLine();
            if (input == null || input.isEmpty()) {
                throw new IllegalArgumentException("El valor ingresado no puede estar vacío.");
            }
            info.add(input);
        }

        return info;
    }
}
