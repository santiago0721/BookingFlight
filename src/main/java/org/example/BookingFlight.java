package org.example;




import java.util.*;
import java.util.stream.Collectors;

public class BookingFlight {
    public final List<String> emails = new ArrayList<>();
    public final List<String> passwords = new ArrayList<>();

    public final List<List<String>> Reservas = new ArrayList<>();
    public final FlightOffersSearch Api = new FlightOffersSearch();
    BookingFlight(){


        ///constructor (aqui deben ir los menus ---- recuerden hacerlo con ciclos while para que no termione el programa despues de una consulta)
        startMenu();
    }

    public void User(String correo ){
        //falta comprobar si el dato es nulo : si es nulo no se puede agregar a la lista y toca volver a llamar la funcion (se hace con un if)

        List<String> data = SearchParameters();
        List<String> reserva = Api.SearchFlightOffers(data);
        if (reserva != null){
            Reservas.add(Arrays.asList(
                    reserva.get(0),reserva.get(1),reserva.get(2),correo));

            System.out.println("---------------------------------------------");
            System.out.println("LA RESERVA FUE EXITOSA");}
            System.out.println("---------------------------------------------");

    }


    public static List<String> SearchParameters(){

        List<String> info = new ArrayList<>();
        System.out.println("Bienvenido");
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

    public void Register(String email, String password){
        if (!emailExists(email)){
            emails.add(email);
            passwords.add(password);
            System.out.println("---------------------------------------------");
            System.out.println("Registro exitoso");
            System.out.println("---------------------------------------------");

        }

    }

    public String Login(String email, String password){
        if (emailExists(email) && passwordMatch(email, password)){
            return email;
        }
        return null;

    }

    public List<List<String>> getUserReservations(String email) {
        return Reservas.stream()
                .filter(reserva -> reserva.get(3).equals(email))
                .collect(Collectors.toList());
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("------------------------------------------------------------");
            System.out.println("Bienvenido al sistema de reservas de vuelos");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Por favor, elige una opción: ");
            String option = scanner.nextLine();
            System.out.println("------------------------------------------------------------");

            switch (option) {
                case "1":
                    System.out.print("Por favor, introduce tu correo electrónico: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Por favor, introduce tu contraseña: ");
                    String loginPassword = scanner.nextLine();
                    if (Login(loginEmail, loginPassword) != null) {
                        System.out.println("---------------------------------------------");
                        System.out.println("Inicio de sesión exitoso!");
                        System.out.println("---------------------------------------------");
                        flightMenu(loginEmail);
                    } else {
                        System.out.println("Correo electrónico o contraseña incorrectos. Por favor, inténtalo de nuevo.");
                    }
                    break;
                case "2":
                    System.out.print("Por favor, introduce tu correo electrónico para registrarte: ");
                    String registerEmail = scanner.nextLine();
                    System.out.print("Por favor, introduce tu contraseña para registrarte: ");
                    String registerPassword = scanner.nextLine();
                    Register(registerEmail, registerPassword);
                    break;
                case "3":
                    System.out.println("Gracias por usar nuestro sistema de reservas de vuelos. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del menú.");
            }
        }
    }
    public void flightMenu(String correo) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("------------------------------------------------------------");
            System.out.println("------------------------------------------------------------");
            System.out.println("Usuario con el correo: " + correo);
            System.out.println("------------------------------------------------------------");
            System.out.println("Menú de vuelos");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Ver vuelos");
            System.out.println("3. cerrar sesion");
            System.out.println("4. Salir");
            System.out.print("Por favor, elige una opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    User(correo);
                    break;
                case "2":
                    List<List<String>> userReservations = getUserReservations(correo);
                    if (userReservations.isEmpty()) {
                        System.out.println("---------------------------------------------");
                        System.out.println("No hay reservas para el usuario: " + correo);
                        System.out.println("---------------------------------------------");
                    } else {
                        System.out.println("-----------------");
                        System.out.println("MIS VUELOS");
                        System.out.println("-----------------");
                        userReservations.forEach(reserva -> {
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Fecha del vuelo: " + reserva.get(0));
                            System.out.println("Precio total: " + reserva.get(1));
                            System.out.println("Cantidad de personas: " + reserva.get(2));
                            System.out.println("------------------------------------------------------------");
                        });
                    }
                    break;

                case "3":
                    startMenu();
                    break;

                case "4":
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Gracias por usar nuestro sistema de reservas de vuelos. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del menú.");
            }
        }
    }

}