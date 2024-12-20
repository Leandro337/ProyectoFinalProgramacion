package co.edu.uniquindio.market_place.viewcontroller;

import co.edu.uniquindio.market_place.controller.IniciarSesionController;
import co.edu.uniquindio.market_place.model.Administrador;
import co.edu.uniquindio.market_place.model.Usuario;
import co.edu.uniquindio.market_place.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IniciarSesionViewController {

    private IniciarSesionController iniciarSesionController;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    private Button registrarButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField contraseñaTxt;

    @FXML
    private TextField usuarioTxt;

    @FXML
    void onIniciarSesion(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    void onRegistrar(ActionEvent event) {
        registrarse();
    }

    @FXML
    void initialize() {
        iniciarSesionController = new IniciarSesionController();

        // Usuarios de prueba
        IniciarSesionController.registrarVendedor(new Vendedor("Juan", "Pérez", "12345678", "Calle 1", "Juanes", "111"));
        IniciarSesionController.registrarVendedor(new Vendedor("María", "López", "87654321", "Calle 2", "Mar", "222"));
        IniciarSesionController.registrarAdministrador(new Administrador("Admin", "Principal", "00000001", "Oficina 1", "Jorge", "123", "123"));
    }

    private void iniciarSesion() {
        String usuario = usuarioTxt.getText();
        String contrasenia = contraseñaTxt.getText();
        if (validar(usuario, contrasenia)) {
            Usuario usuarioActual = iniciarSesionController.iniciarSesion(usuario, contrasenia);
            if (usuarioActual != null) {
                showMessage("Inicio de sesión", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
                cargarMarketPlace(usuarioActual); // Llamamos al método para cargar la ventana correspondiente
            } else {
                showMessage("Error", "Error, el usuario no existe", Alert.AlertType.ERROR);
            }
        } else {
            showMessage("Error", "Ingrese todos los datos", Alert.AlertType.ERROR);
        }
    }

    // Método para redirigir según el rol del usuario
    private void cargarMarketPlace(Usuario usuarioActual) {
        try {
            FXMLLoader loader;
            Parent root;

            // Verificamos el tipo de usuario
            if (usuarioActual instanceof Vendedor) {
                loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/market_place/MiPerfil.fxml"));
                root = loader.load();
                MiPerfilViewController miPerfilViewController = loader.getController();
                miPerfilViewController.setUsuarioActual((Vendedor) usuarioActual);
            } else if (usuarioActual instanceof Administrador) {
                loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/market_place/PerfilAdministrador.fxml"));
                root = loader.load();
                PerfilAdministradorViewController perfilAdministradorViewController = loader.getController();
                perfilAdministradorViewController.setUsuarioActual((Administrador) usuarioActual);
            } else {
                showMessage("Error", "Tipo de usuario no reconocido", Alert.AlertType.ERROR);
                return;
            }

            // Cambiar la escena actual
            Scene scene = new Scene(root);
            Stage stage = (Stage) contraseñaTxt.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(usuarioActual.getNombre());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validar(String usuario, String contrasenia) {
        if (usuario == null || usuario.isEmpty()) {
            return false;
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            return false;
        }
        return true;
    }

    private void showMessage(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void registrarse() {
        try {
            // Cargar el archivo FXML de la ventana de registro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/market_place/Registrar.fxml"));
            Parent root = loader.load();

            // Crear una nueva ventana (Stage) para la ventana de registro
            Stage stage = new Stage();
            stage.setTitle("Registrar");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
