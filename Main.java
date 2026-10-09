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

        } while (opcion != 13);
