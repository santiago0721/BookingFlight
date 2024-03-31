package org.example;
import com.amadeus.*;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import java.util.*;

public class FlightOffersSearch {

    FlightOffersSearch(List<String> data) {
        try {
            SearchFlightOffers(data);
        } catch (ResponseException e) {
            System.out.println("Error al buscar ofertas de vuelo: " + e.getMessage());
        }
    }

    public static List<String> showFlights(FlightOfferSearch[] flightOffersSearches,String cant_personas){
        for (int i=0;i<flightOffersSearches.length;i++){
            FlightOfferSearch oferta =flightOffersSearches[i];
            String horaVuelo = oferta.getItineraries()[0].getSegments()[0].getDeparture().getAt();
            String duracionVuelo = oferta.getItineraries()[0].getDuration();
            int asientosDisponibles = oferta.getNumberOfBookableSeats();
            String precioAsiento = oferta.getPrice().getTotal();

            System.out.println("------------------------------------------------------------");
            System.out.println("vuelo #" + (i+1));
            System.out.println("Hora del vuelo: " + horaVuelo);
            System.out.println("Duración del vuelo: " + duracionVuelo);
            System.out.println("Asientos disponibles: " + asientosDisponibles);
            System.out.println("Precio del asiento: " + precioAsiento);
            System.out.println("------------------------------------------------------------");
        }

        System.out.println("seleccione la opcion que desea adquirir, sino desea adquirir ninguna opcion precione cualquier letra fuera del rango de 1-5");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int opcion;
        try {
            opcion = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La opción ingresada no es válida.");
        }

        if (opcion < 1 || opcion > flightOffersSearches.length) {
            throw new IllegalArgumentException("La opción seleccionada está fuera del rango.");
        }

        FlightOfferSearch oferta =flightOffersSearches[opcion-1];

        return Arrays.asList(
                oferta.getItineraries()[0].getSegments()[0].getDeparture().getAt(),
                oferta.getPrice().getTotal(),
                cant_personas
        );
    }

    public static void SearchFlightOffers(List<String> data) throws ResponseException {
        System.out.println("Buscando ofertas de vuelo...");

        Amadeus amadeus = Amadeus
                .builder("imWBP0J7o0xacrzLEGlGe7WT7HiSgluB","4EiqNPCCJb2KJ6Rg")
                .build();

        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", data.get(0))
                        .and("destinationLocationCode", data.get(1))
                        .and("departureDate", data.get(2))
                        .and("returnDate", data.get(3))
                        .and("adults",Integer.parseInt(data.get(4)))
                        .and("max", 5));

        if (flightOffersSearches == null || flightOffersSearches.length == 0) {
            throw new IllegalStateException("No se encontraron ofertas de vuelo.");
        }

        showFlights(flightOffersSearches,data.get(4));
    }
}
