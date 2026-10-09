import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final ListaReproduccion lista =
            new ListaReproduccion();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    agregarCancion(true);
                    break;

                case 2:
                    agregarCancion(false);
                    break;

                case 3:
                    buscarCancion();
                    break;

                case 4:
                    seleccionarCancion();
                    break;

                case 5:
                    eliminarPorId();
                    break;

                case 6:
                    eliminarActual();
                    break;

                case 7:
                    consultarActual();
                    break;

                case 8:
                    navegar(true);
                    break;

                case 9:
                    navegar(false);
                    break;

                case 10:
                    lista.mostrarAdelante();
                    lista.mostrarAtras();
                    break;

                case 11:
                    System.out.println(
                            "Cantidad de canciones: "
                                    + lista.obtenerCantidad());
                    break;

                case 12:
                    reproducirCanciones();
                    break;

                case 13:
                    System.out.println(
                            "Gracias por utilizar el gestor musical.");
                    break;

                default:
                    System.out.println(
                            "Opción inválida. Elige del 1 al 13.");
            }

            if (opcion >= 1 && opcion <= 12) {
                if (lista.verificarIntegridad()) {
                    System.out.println(
                            "[Verificación] Enlaces correctos. "
                                    + "Cantidad: "
                                    + lista.obtenerCantidad());
                } else {
                    System.out.println(
                            "[ERROR] Se detectó una inconsistencia.");
                }
            }

            System.out.println();

        } while (opcion != 13);} 

        teclado.close();

    }

    private static void mostrarMenu() {

        System.out.println("==================================");

        System.out.println("     GESTOR DE LISTAS MUSICALES");

        System.out.println("==================================");

        System.out.println("1. Agregar canción al inicio");

        System.out.println("2. Agregar canción al final");

        System.out.println("3. Buscar canción por ID");

        System.out.println("4. Seleccionar canción actual");

        System.out.println("5. Eliminar canción por ID");

        System.out.println("6. Eliminar canción actual");

        System.out.println("7. Consultar canción actual");

        System.out.println("8. Avanzar a la siguiente canción");

        System.out.println("9. Retroceder a la canción anterior");

        System.out.println("10. Mostrar lista en ambos sentidos");

        System.out.println("11. Consultar cantidad de canciones");

        System.out.println("12. Simular reproducciones");

        System.out.println("13. Salir");

        System.out.println("==================================");

    }

    private static void agregarCancion(boolean alInicio) {

        System.out.println("\n--- Nueva canción ---");

        String titulo = leerTexto("Título: ");

        String artista = leerTexto("Artista: ");

        int duracion = leerDuracion();

        try {

            int id;

            if (alInicio) {

                id = lista.agregarInicio(

                        titulo, artista, duracion);

            } else {

                id = lista.agregarFinal(

                        titulo, artista, duracion);

            }

            System.out.println(

                    "Canción agregada correctamente. ID: " + id);

            if (lista.consultarActual() != null) {

                System.out.println(

                        "Canción actual: " + lista.consultarActual());

            }

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

    private static void buscarCancion() {

        int id = leerEntero("Ingresa el ID que deseas buscar: ");

        Cancion cancion = lista.buscarCancion(id);

        if (cancion == null) {

            System.out.println(

                    "No existe una canción con ese ID.");

        } else {

            System.out.println("Canción encontrada:");

            System.out.println(cancion);

        }

    }

    private static void seleccionarCancion() {

        int id = leerEntero(

                "Ingresa el ID de la canción que deseas seleccionar: ");

        if (lista.seleccionarCancion(id)) {

            System.out.println("Canción actual seleccionada:");

            System.out.println(lista.consultarActual());

        } else {

            System.out.println(

                    "No existe esa canción. La lista no fue modificada.");

        }

    }

    private static void eliminarPorId() {

        int id = leerEntero("Ingresa el ID que deseas eliminar: ");

        if (lista.eliminarPorId(id)) {

            System.out.println("Canción eliminada correctamente.");

        } else {

            System.out.println(

                    "No existe ese ID. La lista no fue modificada.");

        }

    }

    private static void eliminarActual() {

        Cancion cancion = lista.consultarActual();

        if (cancion == null) {

            System.out.println(

                    "No hay una canción actual para eliminar.");

            return;

        }

        System.out.println("Eliminando: " + cancion);

        lista.eliminarActual();

        if (lista.estaVacia()) {

            System.out.println("La lista quedó vacía.");

        } else {

            System.out.println("Nueva canción actual:");

            System.out.println(lista.consultarActual());

        }

    }

    private static void consultarActual() {

        Cancion cancion = lista.consultarActual();

        if (cancion == null) {

            System.out.println(

                    "No hay ninguna canción seleccionada.");

        } else {

            System.out.println("Canción actual:");

            System.out.println(cancion);

        }

    }

    private static void navegar(boolean adelante) {

        boolean sePudoNavegar;

        if (adelante) {

            sePudoNavegar = lista.avanzar();

        } else {

            sePudoNavegar = lista.retroceder();

        }

        if (!sePudoNavegar) {

            System.out.println(

                    "No se puede navegar: la lista está vacía.");

        } else {

            System.out.println("Canción actual:");

            System.out.println(lista.consultarActual());

        }

    }

    private static void reproducirCanciones() {

        int k;

        do {

            k = leerEntero(

                    "¿Cuántas canciones deseas reproducir? ");

            if (k <= 0) {

                System.out.println(

                        "Debes ingresar un entero positivo.");

            }

        } while (k <= 0);

        lista.reproducir(k);

        if (!lista.estaVacia()) {

            System.out.println(

                    "\nSiguiente canción que correspondería reproducir:");

            System.out.println(lista.consultarActual());

        }

    }

    private static String leerTexto(String mensaje) {

        String texto;

        do {

            System.out.print(mensaje);

            texto = teclado.nextLine().trim();

            if (texto.isEmpty()) {

                System.out.println(

                        "El texto no puede estar vacío.");

            }

        } while (texto.isEmpty());

        return texto;

    }

    private static int leerDuracion() {

        int duracion;

        do {

            duracion = leerEntero(

                    "Duración en segundos (mayor que cero): ");

            if (duracion <= 0) {

                System.out.println(

                        "La duración debe ser mayor que cero.");

            }

        } while (duracion <= 0);

        return duracion;

    }

    private static int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String entrada = teclado.nextLine().trim();

            try {

                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {

                System.out.println(

                        "Entrada inválida. Escribe un número entero.");

            }

        }

    }

}
