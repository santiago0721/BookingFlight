package org.example;
import java.util.Scanner;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;

public class FlightOffersSearch {

    FlightOffersSearch() {
        try {
            SearchFlightOffers();
        } catch (ResponseException e) {
            System.out.println("Error al buscar ofertas de vuelo: " + e.getMessage());
        }
    }



    public static void SearchFlightOffers() throws ResponseException {
        System.out.println("Buscando ofertas de vuelo...");

        Amadeus amadeus = Amadeus
                .builder("imWBP0J7o0xacrzLEGlGe7WT7HiSgluB","4EiqNPCCJb2KJ6Rg")
                .build();

        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", "BOG")
                        .and("destinationLocationCode", "MDE")
                        .and("departureDate", "2024-05-01")
                        .and("returnDate", "2024-05-08")
                        .and("adults", 1));  // Solo se solicita una oferta de vuelo

        if (flightOffersSearches[0].getResponse().getStatusCode() != 200) {
            System.out.println("Código de estado incorrecto: " + flightOffersSearches[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        FlightOfferSearch oferta = flightOffersSearches[0];
        String horaVuelo = oferta.getItineraries()[0].getSegments()[0].getDeparture().getAt();
        String duracionVuelo = oferta.getItineraries()[0].getDuration();
        int asientosDisponibles = oferta.getNumberOfBookableSeats();
        String precioAsiento = oferta.getPrice().getTotal(); // Obtiene el precio total del asiento

        System.out.println("Hora del vuelo: " + horaVuelo);
        System.out.println("Duración del vuelo: " + duracionVuelo);
        System.out.println("Asientos disponibles: " + asientosDisponibles);
        System.out.println("Precio del asiento: " + precioAsiento);
    }
}
