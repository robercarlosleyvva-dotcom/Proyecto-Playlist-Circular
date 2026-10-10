public class Cancion {

    private final int id;
    private final String titulo;
    private final String artista;
    private final int duracion;

    public Cancion(int id, String titulo, String artista, int duracion) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser positivo.");
        }

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "El título no puede estar vacío.");
        }

        if (artista == null || artista.isBlank()) {
            throw new IllegalArgumentException(
                    "El artista no puede estar vacío.");
        }

        if (duracion <= 0) {
            throw new IllegalArgumentException(
                    "La duración debe ser mayor que cero.");
        }

        this.id = id;
        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracion;
    }
    

    public String obtenerDuracionFormateada() {
        int minutos = duracion / 60;
        int segundos = duracion % 60;

        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Título: " + titulo
                + " | Artista: " + artista
                + " | Duración: " + obtenerDuracionFormateada()
                + " (" + duracion + " segundos)";
    }
}
