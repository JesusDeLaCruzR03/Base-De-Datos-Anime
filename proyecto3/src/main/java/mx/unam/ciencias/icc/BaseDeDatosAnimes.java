package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosAnimes
    extends BaseDeDatos<Anime, CampoAnime> {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override public Anime creaRegistro() {
        // Aquí va su código.
        return new Anime(null, null, null, 0, 0.0);
    }
}
