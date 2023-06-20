package mx.unam.ciencias.icc.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.Anime;

/**
 * Clase para diálogos con formas para editar estudiantes.
 */
public class DialogoEditaAnime extends Stage {

    /* Vista de la forma para agregar/editar estudiantes. */
    private static final String EDITA_ANIME_FXML =
        "fxml/forma-edita-anime.fxml";

    /* El controlador. */
    private ControladorFormaEditaAnime controlador;

    /**
     * Define el estado inicial de un diálogo para estudiante.
     * @param escenario el escenario al que el diálogo pertenece.
     * @param estudiante el estudiante; puede ser <code>null</code> para agregar
     *                   un estudiante.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoEditaAnime(Stage escenario,
                             Anime anime) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador =
            new FXMLLoader(cl.getResource(EDITA_ANIME_FXML));
        AnchorPane cristal = (AnchorPane)cargador.load();

        if (anime == null)
            setTitle("Agregar anime");
        else
            setTitle("Editar anime");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);

        controlador = cargador.getController();
        controlador.setEscenario(this);
        controlador.setAnime(anime);
        if (anime == null)
            controlador.setVerbo("Agregar");
        else
            controlador.setVerbo("Actualizar");

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
     * Regresa el estudiante del diálogo.
     * @return el estudiante del diálogo.
     */
    public Anime getAnime() {
        return controlador.getAnime();
    }
}
