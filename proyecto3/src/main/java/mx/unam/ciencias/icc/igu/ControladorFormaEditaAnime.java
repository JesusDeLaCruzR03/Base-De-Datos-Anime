package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import mx.unam.ciencias.icc.Anime;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * estudiantes.
 */
public class ControladorFormaEditaAnime
    extends ControladorFormaAnime {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el nombre del creador. */
    @FXML private EntradaVerificable entradaCreador;
    /* La entrada verificable para el pais de origen. */
    @FXML private EntradaVerificable entradaPais;
    /* La entrada verificable para la fecha de estreno del anime. */
    @FXML private EntradaVerificable entradaFecha;
    /* La entrada verificable para la calificacion personal del anime. */
    @FXML private EntradaVerificable entradaCalificacion;

    /* El anime creado o editado. */
    private Anime anime;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaCreador.setVerificador(c -> verificaCreador(c));
        entradaPais.setVerificador(p -> verificaPais(p));
        entradaFecha.setVerificador(f -> verificaFecha(f));
        entradaCalificacion.setVerificador(ca -> verificaCalificacion(ca));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaAnime());
        entradaCreador.textProperty().addListener(
            (o, v, n) -> verificaAnime());
        entradaPais.textProperty().addListener(
            (o, v, n) -> verificaAnime());
        entradaFecha.textProperty().addListener(
            (o, v, n) -> verificaAnime());
        entradaCalificacion.textProperty().addListener(
            (o, v, n) -> verificaAnime());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaAnime();
        aceptado = true;
        escenario.close();
    }

    /* Actualiza al estudiante, o lo crea si no existe. */
    private void actualizaAnime() {
        if (anime != null) {
            anime.setNombre(nombre);
            anime.setCreador(creador);
            anime.setPais(pais);
            anime.setFecha(fecha);
            anime.setCalificacion(calificacion);
        } else {
            anime = new Anime(nombre, creador, pais, fecha, calificacion);
        }
    }

    /**
     * Define el estudiante del diálogo.
     * @param estudiante el nuevo estudiante del diálogo.
     */
    public void setAnime(Anime anime) {
        this.anime = anime;
        if (anime == null)
            return;
        this.anime = new Anime(null, null, null, 0, 0);
        this.anime.actualiza(anime);
        entradaNombre.setText(anime.getNombre());
        String c = String.format("%s", anime.getCreador());
        entradaCreador.setText(anime.getCreador());
        String p = String.format("%s", anime.getPais());
        entradaPais.setText(p);
        entradaFecha.setText(String.valueOf(anime.getFecha()));
    }

    /**
     * Regresa el estudiante del diálogo.
     * @return el estudiante del diálogo.
     */
    public Anime getAnime() {
        return anime;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaAnime() {
        boolean n = entradaNombre.esValida();
        boolean c = entradaCreador.esValida();
        boolean p = entradaPais.esValida();
        boolean f = entradaFecha.esValida();
        boolean ca = entradaCalificacion.esValida();
        botonAceptar.setDisable(!n || !c || !p || !f || !ca);
    }

    @Override protected boolean verificaFecha(String fecha){
        return super.verificaFecha(fecha) && this.fecha >= 1000 && this.fecha <= 2023;
    }

    @Override protected boolean verificaCalificacion(String calificacion){
        return super.verificaCalificacion(calificacion) && this.calificacion >= 0.0 && this.calificacion <= 10.0;
    }
}
