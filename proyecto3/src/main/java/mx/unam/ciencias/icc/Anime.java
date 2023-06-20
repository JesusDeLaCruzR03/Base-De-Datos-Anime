package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Anime implements Registro<Anime, CampoAnime> {

    /* Nombre del estudiante. */
    private final StringProperty nombre;
    /* Nombre del creador del anime. */
    private final StringProperty creador;
    /* Nombre del país de Origen*/
    private final StringProperty pais;
    /* Fecha de publicación del anime*/
    private final IntegerProperty fecha;
    /* Calificación personal sobre el anime*/
    private final DoubleProperty calificacion;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param creador el número de cuenta del estudiante.
     * @param pais el promedio del estudiante.
     * @param fecha la edad del estudiante.
     * @param calificacion .
     */
    public Anime(String nombre,
                 String creador,
                 String pais,
                 int fecha,
                 double calificacion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.creador = new SimpleStringProperty(creador);
        this.pais = new SimpleStringProperty(pais);
        this.fecha = new SimpleIntegerProperty(fecha);
        this.calificacion = new SimpleDoubleProperty(calificacion);
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        // Aquí va su código.
        return nombre.get();
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        // Aquí va su código.
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        // Aquí va su código.
        return nombre;
    }

    /**
     * Regresa el creador del anime
     * @return el creador del anime 
     */
    public String getCreador() {
        return creador.get();
    }

    /**
     * Define el creador del anime
     * @param creador el nuevo creador del anime
     */
    public void setCreador(String creador) {
        this.creador.set(creador);
    }

    /**
     * Regresa la propiedad del creador
     * @return la propiedad del creador
     */
    public StringProperty creadorProperty() {
        return creador;
    }

    /**
     * Regresa el pais de origen del anime
     * @return el pais de origen
     */
    public String getPais() {
        return pais.get();
    }

    /**
     * Define el pais de origen del anime
     * @param pais el nuevo pais de origen del anime
     */
    public void setPais(String pais) {
        this.pais.set(pais);
    }

    /**
     * Regresa la propiedad del pais de origen del anime
     * @return la propiedad del pais de origen del anime
     */
    public StringProperty paisProperty() {
        return pais;
    }

    /**
     * Regresa la fecha de estreno del anime
     * @return la fecha de estreno del anime
     */
    public int getFecha() {
        return fecha.get();
    }

    /**
     * Define la fechga de estreno del anime
     * @param fecha del nuevo anime recibido
     */
    public void setFecha(int fecha) {
        this.fecha.set(fecha);
    }

    /**
     * Regresa la propiedad de la fecha de estreno del anime
     * @return la fecha de estreno del anime
     */
     public IntegerProperty fechaProperty() {
        return fecha;
     } 

    /**
     * Regresa la calificacion personal dada al anime
     * @return la calificacion del anime
     */
     public double getCalificacion() {
        return calificacion.get();
     }

     /**
      * Define la calificacion personal dada al anime
      * @param calificacion personal nueva dada al anime
      */
     public void setCalificacion(double calificacion) {
        this.calificacion.set(calificacion);
     }

     /**
      * Regresa la propiedad de la calificacion
      * @return la propiedad de la calificacion
      */
     public DoubleProperty calificacionProperty() {
        return calificacion;
     }


    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        return String.format("Nombre       : %s\n" +
                             "Creador      : %s\n" +
                             "Pais         : %s\n" +
                             "Fecha        : %d\n" +
                             "Calificacion : %2.2f",
                             nombre.get(), creador.get(), pais.get(), fecha.get(), calificacion.get());
}

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Anime))
            return false;
        Anime anime = (Anime)objeto;
        if (anime == null)
            return false;
        if(this.getNombre().equals(anime.getNombre()) && this.getCreador().equals(anime.getCreador()) && 
           this.getPais().equals(anime.getPais()) && this.getFecha() == anime.getFecha() && 
           this.getCalificacion() == anime.getCalificacion())
            return true;
        else 
            return false;
    }

    /**
     * Regresa el estudiante seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Anime#deseria}.
     * @return la seriación del estudiante en una línea de texto.
     */
    @Override public String seria() {
        return String.format("%s\t%s\t%s\t%d\t%2.2f\n", getNombre(), getCreador(), getPais(), getFecha(), getCalificacion());
    }

    /**
     * Deseria una línea de texto en las propiedades del estudiante. La
     * seriación producida por el método {@link Anime#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un estudiante.
     */
    @Override public void deseria(String linea) {
        // Aquí va su código.
        if (linea == null)
            throw new ExcepcionLineaInvalida("La linea es vacia");
        linea = linea.trim();
        if (linea.isEmpty())
            throw new ExcepcionLineaInvalida("La linea es vacia");
        String[] linearreglo =  linea.split("\t");
        if (linearreglo.length != 5)
            throw new ExcepcionLineaInvalida("la linea no tiene el numero de elementos exacto");
        setNombre(linearreglo [0]);
        setCreador(linearreglo [1]);
        setPais(linearreglo [2]);
        try {          
            setFecha(Integer.parseInt(linearreglo [3]));
            setCalificacion(Double.parseDouble(linearreglo [4]));
        } catch (NumberFormatException nfe){
            throw new ExcepcionLineaInvalida("algo salió mal");
        }
    }
        

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param anime el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    public void actualiza(Anime anime) {
        // Aquí va su código.
        if(anime == null)
            throw new IllegalArgumentException("El registro no es un Anime");
        this.setNombre(anime.getNombre());
        this.setCreador(anime.getCreador());
        this.setPais(anime.getPais());
        this.setFecha(anime.getFecha());
        this.setCalificacion(anime.getCalificacion());
    }

    /**
     * Nos dice si el estudiante casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean casa(CampoAnime campo, Object valor) {
        // Aquí va su código.
        if (!(campo instanceof CampoAnime))
            throw new IllegalArgumentException("El campo debe ser " +
                                               "CampoAnime");
        CampoAnime c = (CampoAnime)campo;
        switch (c) {
            case NOMBRE: 
                return casaValorNombre(valor);
            case CREADOR:
                return casaValorCreador(valor);
            case PAIS:
                return casaValorPais(valor);
            case FECHA:
                return casaValorFecha(valor);
            case CALIFICACION:
                return casaValorCalificacion(valor);
            default:
                return false;
        }
}

     private boolean casaValorNombre(Object o){
        if (!(o instanceof String)) return false;
        String v = (String) o;
        if (v.isEmpty()) return false;
        return getNombre().indexOf(v) != -1;
    }

    private boolean casaValorCreador(Object o) {
        if(!(o instanceof String)) return false;
        String v = (String) o;
        if(v.isEmpty()) return false;
        return getCreador().indexOf(v) != -1;
    }

    private boolean casaValorPais(Object o) {
        if(!(o instanceof String)) return false;
        String v = (String) o;
        if(v.isEmpty()) return false;
        return getCreador().indexOf(v) != -1;
    }

    private boolean casaValorFecha(Object o){
        if (!(o instanceof Integer)) return false;
        Integer v = (Integer) o;
        return getFecha() >= v.intValue();
    }

    private boolean casaValorCalificacion(Object o){
        if (!(o instanceof Double)) return false;
        Double v = (Double) o;
        return getCalificacion() >= v.doubleValue();
    }
       
}
