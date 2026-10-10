public class Nodo {
    // Atributos
    private Cancion cancion;   // La canción que guarda este nodo
    private Nodo siguiente;    // Referencia al siguiente nodo
    private Nodo anterior;     // Referencia al nodo anterior

    // Constructor
    public Nodo(Cancion cancion) {
        this.cancion = cancion;
        this.siguiente = null;
        this.anterior = null;
    }

    // Getter y Setter de Cancion
    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    // Getter y Setter de Siguiente
    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    // Getter y Setter de Anterior
    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}
