package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Estudiante}.
 */
public enum CampoAnime {

    /** El nombre del estudiante. */
    NOMBRE,
    /** El número de cuenta del estudiante. */
    CREADOR,
    /** El promedio del estudiante. */
    PAIS,
    /** Fecha de estreno del anime*/
    FECHA,
    /** La edad del estudiante. */
    CALIFICACION;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        // Aquí va su código.
        switch(this){
            case NOMBRE      : return "Nombre";
            case CREADOR     : return "Creador";
            case PAIS        : return "Pais";
            case FECHA       : return "Fecha";
            case CALIFICACION: return "Calificacion";
            default: return "";
        }
    }
}
