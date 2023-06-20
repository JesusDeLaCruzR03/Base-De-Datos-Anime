package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoAnime;

/**
 * Clase para el controlador del contenido del diálogo para buscar estudiantes.
 */
public class ControladorFormaBuscaAnime
    extends ControladorFormaAnime {

    /* El combo del campo. */
    @FXML private ComboBox<CampoAnime> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Verifica el valor. */
    private boolean verificaValor(String valor) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:  return verificaNombre(valor);
        case CREADOR: return verificaCreador(valor);
        case PAIS: return verificaPais(valor);
        case FECHA: return verificaFecha(valor);
        case CALIFICACION: return verificaCalificacion(valor);
        default: return false; // No puede ocurrir
        }
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un carácter";
            break;
        case CREADOR: 
            m = "Buscar por creador necesita al menos un carácter";
        case PAIS:
            m = "Buscar por pais necesita al menos un carácter";
        case FECHA:
            m = "Buscar por fecha necesita un número entre 1000 y 2023"; 
        case CALIFICACION:
            m = "Buscar por calificacion necesita un número entre 0.0 y 10.0";
        }
        return new Tooltip(m);
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:   return entradaValor.getText();
        case CREADOR: return entradaValor.getText();
        case PAIS: return entradaValor.getText();
        case FECHA: return Integer.parseInt(entradaValor.getText());
        case CALIFICACION: return Double.parseDouble(entradaValor.getText());
        default: return entradaValor.getText(); // No puede ocurrir.
        }
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoAnime getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
