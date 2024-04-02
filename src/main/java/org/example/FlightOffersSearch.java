package org.example;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;

public class FlightOffersSearch {

    FlightOffersSearch() {

    }


    public static List<String> showFlights(FlightOfferSearch[] flightOffersSearches,String cant_personas){

        for (int i=0;i<flightOffersSearches.length;i++){

            FlightOfferSearch oferta =flightOffersSearches[i];
            String horaVuelo = oferta.getItineraries()[0].getSegments()[0].getDeparture().getAt();
            String duracionVuelo = oferta.getItineraries()[0].getDuration();
            int asientosDisponibles = oferta.getNumberOfBookableSeats();
            String precioAsiento = oferta.getPrice().getTotal(); // Obtiene el precio total del asiento

            System.out.println("------------------------------------------------------------");
            System.out.println("vuelo #" + i+1);
            System.out.println("Hora del vuelo: " + horaVuelo);
            System.out.println("Duración del vuelo: " + duracionVuelo);
            System.out.println("Asientos disponibles: " + asientosDisponibles);
            System.out.println("Precio del asiento: " + precioAsiento);
            System.out.println("------------------------------------------------------------");

        }


        //manejo de excepciones
        System.out.println("seleccione la opcion que desea adquirir, sino desea adquirir ninguna opcion precione cualquier letra fuera del rango de 1-5");
        Scanner scanner = new Scanner(System.in);
        Integer opcion = Integer.parseInt(scanner.nextLine());

        FlightOfferSearch oferta =flightOffersSearches[opcion-1];

        return Arrays.asList(
                oferta.getItineraries()[0].getSegments()[0].getDeparture().getAt(),//trae la fecha del vuelo
                oferta.getPrice().getTotal(),//precio total en euros
                cant_personas//tra la cantidad de personas

        );

    }



    public static List<String> SearchFlightOffers(List<String> data) {

        try {
            System.out.println("Buscando ofertas de vuelo...");

            Amadeus amadeus = Amadeus
                    .builder("imWBP0J7o0xacrzLEGlGe7WT7HiSgluB", "4EiqNPCCJb2KJ6Rg")
                    .build();

            FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", data.get(0))
                            .and("destinationLocationCode", data.get(1))
                            .and("departureDate", data.get(2))
                            .and("returnDate", data.get(3))
                            .and("adults", Integer.parseInt(data.get(4)))
                            .and("max", 5));

            if (flightOffersSearches[0].getResponse().getStatusCode() != 200) {
                System.out.println("Código de estado incorrecto: " + flightOffersSearches[0].getResponse().getStatusCode());
                System.exit(-1);
            }

            List<String> a = showFlights(flightOffersSearches, data.get(4));

            return a;

        }catch (ResponseException e) {
            System.out.println("Error al buscar ofertas de vuelo: ");
            return null;
        }

    }
}