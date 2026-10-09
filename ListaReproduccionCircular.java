public class ListaReproduccionCircular{
    //ATRIBUTOS
    private Nodo cabeza; //Referencia al primer nodo de la lista
    private Nodo cola;  //referencia al utlimo nodo de la lista
    private Nodo actual;//referencia a la cancion que se esta reproduciendo o seleccionada
    private int tamanio; //Contador de la cantidad de canciones de la lista
    private int contadorId; //Contador que incrementa para asignar un ID


    

    //Constructor, no se le dan parametros porque inicia con valores vacios.
public ListaReproduccionCircular(){
    this.cabeza = null;
    this.cola= null;
    this.actual = null;
    this.tamanio = 0;
    this.contadorId= 1;

    

}

    //Metodos de verificacion
public boolean estaVacia(){
    return  cabeza==null;
}
public int getTamanio(){
    return tamanio;
}
public Cancion getCancionActual(){
    if(estaVacia()){return null;}
    return actual.getCancion();
}
public void agregarCancion(Cancion c ){
    Nodo nuevo = new Nodo(c);
    if(estaVacia()){
        cabeza= nuevo;
        cola = nuevo;
        actual = cabeza;
        cabeza.setSiguiente(cola);
        cabeza.setAnterior(cola);
        actual = cabeza;
    } else{
        cola.setSiguiente(nuevo);
        nuevo.setAnterior(cola);
        nuevo.setSiguiente(cabeza);
        cabeza.setAnterior(nuevo);
        cola=nuevo;
        tamanio++;
    contadorId++;
    }
}
public void mostrarLista() {
    if (estaVacia()) {
        System.out.println("La lista está vacía.");
        return;
    }
    Nodo temp = cabeza;
    do {
        System.out.println(temp.getCancion().getTitulo() + " - " +
                           temp.getCancion().getArtista());
        temp = temp.getSiguiente();
    } while (temp != cabeza);
}

public void reproducirSiguiente() {
    if (!estaVacia()) {
        actual = actual.getSiguiente();
        System.out.println("Reproduciendo: " + actual.getCancion().getTitulo());
    }
}

public void mostrarActual(){
    if(estaVacia()){
        System.out.println("La lista esta vacia, no hay una cancion actual.");
    }
    else{
        System.out.println("Estas escuchando " + actual.getCancion());
    }
}
public Cancion buscarPorId(int id) {
    if (estaVacia()) {
        System.out.println("La lista está vacía.");
        return null;
    }

    Nodo actualBusqueda = cabeza;
    do {
        if (actualBusqueda.getCancion().getId() == id) {
            System.out.println("Canción encontrada: " + actualBusqueda.getCancion());
            return actualBusqueda.getCancion();
        }
        actualBusqueda = actualBusqueda.getSiguiente();
    } while (actualBusqueda != cabeza);

    System.out.println("No se encontró ninguna canción con el ID: " + id);
    return null;
}
public void eliminarPorId(int id) {
    if (estaVacia()) {
        System.out.println("La lista está vacía.");
        return;
    }

    Nodo actualBusqueda = cabeza;
    do {
        if (actualBusqueda.getCancion().getId() == id) {
            // CASO 1: Es el único nodo en la lista
            if (tamanio == 1) {
                cabeza = null;
                cola = null;
                actual = null;
            } else {
                // CASO 2: Si el nodo a eliminar es la canción actual, movemos actual antes de desconectar
                if (actualBusqueda == actual) {
                    actual = actualBusqueda.getSiguiente();
                }
                
                // Reconectar los nodos adyacentes para aislar el nodo actualBusqueda
                actualBusqueda.getAnterior().setSiguiente(actualBusqueda.getSiguiente());
                actualBusqueda.getSiguiente().setAnterior(actualBusqueda.getAnterior());

                // Actualizar cabeza o cola si correspondía a un extremo
                if (actualBusqueda == cabeza) {
                    cabeza = actualBusqueda.getSiguiente();
                }
                if (actualBusqueda == cola) {
                    cola = actualBusqueda.getAnterior();
                }
            }
            tamanio--;
            System.out.println("Canción con ID " + id + " eliminada correctamente.");
            return;
        }
        actualBusqueda = actualBusqueda.getSiguiente();
    } while (actualBusqueda != cabeza);

    System.out.println("No se encontró ninguna canción con el ID: " + id);
}
public void eliminarCancionActual() {
    if (estaVacia() || actual == null) {
        System.out.println("La lista está vacía. No hay canción actual para eliminar.");
        return;
    }
    eliminarPorId(actual.getCancion().getId());
}
}