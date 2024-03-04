package com.example.practica7javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnPonerEstilo;

    @FXML
    private Button btnQuitarEstilo;

    @FXML
    private TableColumn<?, ?> colIngredientes;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private ImageView ivFoto;

    @FXML
    private Label lbUsuario;
    @FXML
    private Label labelNombre;

    @FXML
    private TextField tfUsuario;

    @FXML
    private TableView<Ingrediente> tvTable;

    private String rutaAbsoluta = new File("src\\main\\resources").getAbsolutePath();

    ObservableList<Ingrediente> listaIngredientes;
    ObservableList<Usuario> listaUsuarios;

    @FXML
    void Iniciar(ActionEvent event) {
        int id= Integer.parseInt(tfUsuario.getText());
        for (Usuario user:listaUsuarios) {
            if(user.getId()==id){
                labelNombre.setText("Elige tus ingredientes: "+user.getNombre());
                ivFoto.setImage(new Image(user.getUrlImagen()));

            }
        }


    }

    @FXML
    void PonerEstilo(ActionEvent event) {

    }

    @FXML
    void QuitarEstilo(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaUsuarios=FXCollections.observableArrayList(
                new Usuario(1,"Maria",rutaAbsoluta+"\\image\\chica1.png"),
                new Usuario(2,"Andres",rutaAbsoluta+"\\image\\icono1.png"),
                new Usuario(3,"Belen",rutaAbsoluta+"\\image\\icono2.png"),
                new Usuario(4,"Raul",rutaAbsoluta+"\\image\\icono3.png")

        );
        listaIngredientes= FXCollections.observableArrayList(
                new Ingrediente("Champiñones",2),
                new Ingrediente("Bacon",3),
                new Ingrediente("Queso",4),
                new Ingrediente("Atun",6),
                new Ingrediente("Anchoa",6)

        );

        colIngredientes.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));


        tvTable.setItems(listaIngredientes);
        tvTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
}
