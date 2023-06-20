package mx.unam.ciencias.icc.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.CampoAnime;

/**
 * Clase para diálogos con formas de búsquedas de estudiantes.
 */
public class DialogoBuscaAnime extends Stage {

    /* Vista de la forma para realizar búsquedas de estudiantes. */
    private static final String BUSCA_ANIME_FXML =
        "fxml/forma-busca-anime.fxml";

    /* El controlador. */
    private ControladorFormaBuscaAnime controlador;

    /**
     * Define el estado inicial de un diálogo para búsquedas de estudiantes.
     * @param escenario el escenario al que el diálogo pertenece.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoBuscaAnime(Stage escenario) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador = new FXMLLoader(
            cl.getResource(BUSCA_ANIME_FXML));
        AnchorPane cristal = (AnchorPane)cargador.load();

        setTitle("Buscar animes");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);
        controlador = cargador.getController();
        controlador.setEscenario(this);
        setOnShown(w -> controlador.defineFoco());
        setResizable(false);
    }

    /**
     * Nos dice si el usuario activó el botón de aceptar.
     * @return <code>true</code> si el usuario activó el botón de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() {
        return controlador.isAceptado();
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoAnime getCampo() {
        return controlador.getCampo();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        return controlador.getValor();
    }
}
