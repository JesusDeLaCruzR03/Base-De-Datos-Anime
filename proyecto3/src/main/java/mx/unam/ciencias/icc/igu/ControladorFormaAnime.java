package mx.unam.ciencias.icc.igu;

/**
 * Clase abstracta para controladores del contenido de diálogo con formas con
 * datos de estudiantes que se aceptan o rechazan.
 */
public abstract class ControladorFormaAnime extends ControladorForma {

    /** El valor del nombre. */
    protected String nombre;
    /** El valor del creador del anime. */
    protected String creador;
    /** El valor del pais de origen del anime. */
    protected String pais;
    /** El valor de la fecha de estreno del anime. */
    protected int fecha;
    /** El valor de la calificacion personal al anime. */
    protected double calificacion;

    /**
     * Verifica que el nombre sea válido.
     * @param nombre el nombre a verificar.
     * @return <code>true</code> si el nombre es válido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaNombre(String nombre) {
        if (nombre == null || nombre.isEmpty())
            return false;
        this.nombre = nombre;
        return true;
    }

    protected boolean verificaCreador(String creador) {
        if(creador == null || creador.isEmpty())
            return false;
        this.creador = creador;
        return true;
    }

    protected boolean verificaPais(String pais) {
        if(pais == null || pais.isEmpty())
            return false;
        this.pais = pais;
        return true;
    }

    protected boolean verificaFecha(String fecha) {
        if(fecha == null || fecha.isEmpty())
            return false;
        try{
            this.fecha = Integer.parseInt(fecha);
        }catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    protected boolean verificaCalificacion(String calificacion) {
        if(calificacion == null || calificacion.isEmpty())
            return false;
        try{
            this.calificacion = Double.parseDouble(calificacion);
        }catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
