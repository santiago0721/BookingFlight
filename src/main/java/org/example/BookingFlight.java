package org.example;




import java.util.*;

public class BookingFlight {
    public final List<String> emails = new ArrayList<>();
    public final List<String> passwords = new ArrayList<>();

    public final List<List<String>> Reservas = new ArrayList<>();
    public final FlightOffersSearch Api = new FlightOffersSearch();
    BookingFlight(){

        ///constructor (aqui deben ir los menus ---- recuerden hacerlo con ciclos while para que no termione el programa despues de una consulta)
        User("prueba@gmail.com");
    }

    public void User(String correo ){
        //falta comprobar si el dato es nulo : si es nulo no se puede agregar a la lista y toca volver a llamar la funcion (se hace con un if)

        List<String> data = SearchParameters();
        List<String> reserva = Api.SearchFlightOffers(data);
        if (reserva != null){
        Reservas.add(Arrays.asList(
                reserva.get(0),reserva.get(1),reserva.get(2),correo));

        System.out.println("LA RESERVA FUE EXITOSA");}

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

    public Object Login(String email, String password){
        if (emailExists(email) && passwordMatch(email, password)){
            return email;
        }
        return false;

    }

}
