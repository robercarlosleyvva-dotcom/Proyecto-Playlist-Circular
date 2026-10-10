public class ListaReproduccionCircular {
    // ATRIBUTOS
    private Nodo cabeza;      // Referencia al primer nodo de la lista
    private Nodo cola;        // Referencia al último nodo de la lista
    private Nodo actual;      // Referencia a la canción actual o seleccionada
    private int tamanio;      // Contador de la cantidad de canciones de la lista
    private int contadorId;   // Contador que incrementa para asignar un ID

    // Constructor
    public ListaReproduccionCircular() {
        this.cabeza = null;
        this.cola = null;
        this.actual = null;
        this.tamanio = 0;
        this.contadorId = 1;
    }

    // Métodos de verificación y consulta
    public boolean estaVacia() {
        return cabeza == null;
    }

    public int obtenerCantidad() {
        return tamanio;
    }

    public Cancion consultarActual() {
        if (estaVacia() || actual == null) {
            return null;
        }
        return actual.getCancion();
    }

    // Métodos de inserción compatibles con Main
    public int agregarInicio(String titulo, String artista, int duracion) {
        int id = contadorId++;
        Cancion c = new Cancion(id, titulo, artista, duracion);
        Nodo nuevo = new Nodo(c);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            actual = cabeza;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
        return id;
    }

    public int agregarFinal(String titulo, String artista, int duracion) {
        int id = contadorId++;
        Cancion c = new Cancion(id, titulo, artista, duracion);
        Nodo nuevo = new Nodo(c);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            actual = cabeza;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cola = nuevo;
        }
        tamanio++;
        return id;
    }

    // Búsqueda y selección
    public Cancion buscarCancion(int id) {
        if (estaVacia()) {
            return null;
        }
        Nodo temp = cabeza;
        do {
            if (temp.getCancion().getId() == id) {
                return temp.getCancion();
            }
            temp = temp.getSiguiente();
        } while (temp != cabeza);
        return null;
    }

    public boolean seleccionarCancion(int id) {
        if (estaVacia()) {
            return false;
        }
        Nodo temp = cabeza;
        do {
            if (temp.getCancion().getId() == id) {
                actual = temp;
                return true;
            }
            temp = temp.getSiguiente();
        } while (temp != cabeza);
        return false;
    }

    // Eliminación
    public boolean eliminarPorId(int id) {
        if (estaVacia()) {
            return false;
        }
        Nodo temp = cabeza;
        do {
            if (temp.getCancion().getId() == id) {
                if (tamanio == 1) {
                    cabeza = null;
                    cola = null;
                    actual = null;
                } else {
                    if (temp == actual) {
                        actual = temp.getSiguiente();
                    }
                    temp.getAnterior().setSiguiente(temp.getSiguiente());
                    temp.getSiguiente().setAnterior(temp.getAnterior());

                    if (temp == cabeza) {
                        cabeza = temp.getSiguiente();
                    }
                    if (temp == cola) {
                        cola = temp.getAnterior();
                    }
                }
                tamanio--;
                return true;
            }
            temp = temp.getSiguiente();
        } while (temp != cabeza);
        return false;
    }

    public void eliminarActual() {
        if (estaVacia() || actual == null) {
            return;
        }
        eliminarPorId(actual.getCancion().getId());
    }

    // Navegación
    public boolean avanzar() {
        if (estaVacia() || actual == null) {
            return false;
        }
        actual = actual.getSiguiente();
        return true;
    }

    public boolean retroceder() {
        if (estaVacia() || actual == null) {
            return false;
        }
        actual = actual.getAnterior();
        return true;
    }

    // Simulación de reproducción
    public void reproducir(int k) {
        if (estaVacia() || actual == null) {
            System.out.println("La lista está vacía. No hay canciones para reproducir.");
            return;
        }
        System.out.println("\n--- Simulando reproducción de " + k + " canciones ---");
        for (int i = 0; i < k; i++) {
            System.out.println("Reproduciendo: " + actual.getCancion());
            actual = actual.getSiguiente();
        }
    }

    // Verificación de integridad de los enlaces circulares dobles
    public boolean verificarIntegridad() {
        if (estaVacia()) {
            return cabeza == null && cola == null && tamanio == 0;
        }
        int cuenta = 0;
        Nodo temp = cabeza;
        do {
            if (temp.getSiguiente().getAnterior() != temp || temp.getAnterior().getSiguiente() != temp) {
                return false;
            }
            cuenta++;
            temp = temp.getSiguiente();
        } while (temp != cabeza);

        return cuenta == tamanio && cabeza.getAnterior() == cola && cola.getSiguiente() == cabeza;
    }

    // Mostrar lista en ambos sentidos
    public void mostrarAdelante() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo temp = cabeza;
        System.out.println("\n--- Lista hacia adelante ---");
        do {
            System.out.println(temp.getCancion());
            temp = temp.getSiguiente();
        } while (temp != cabeza);
    }

    public void mostrarAtras() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo temp = cola;
        System.out.println("\n--- Lista hacia atrás ---");
        do {
            System.out.println(temp.getCancion());
            temp = temp.getAnterior();
        } while (temp != cola);
    }
}