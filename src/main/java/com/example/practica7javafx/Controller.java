package com.example.practica7javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnComprar;

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
    ArrayList<Ingrediente> ingredientesSelecionados=new ArrayList<>();
    int precioTotal;

    @FXML
    void Calcular(ActionEvent event) {
        precioTotal=0;
        for(Ingrediente ingrediente:ingredientesSelecionados){
            precioTotal+= ingrediente.getPrecio();
        }
        CrearAlerta("INFORMATION","Precio Total","El precio de la Pizza es: "+precioTotal+"€");
        btnComprar.setDisable(false);
        tvTable.getSelectionModel().clearSelection();  //limpiar la tabla de los elementos selecionados
        ingredientesSelecionados.clear(); //vaciar el ArrayList

    }

    @FXML
    void Comprar(ActionEvent event) {
        int id= Integer.parseInt(tfUsuario.getText());
        Usuario usuario=new Usuario();
        for(Usuario u:listaUsuarios){
            if(u.getId()==id){
                usuario=u;
                break;
            }
        }
        if(usuario.getCredito()<precioTotal){
            CrearAlerta("ERROR","ERROR","EL valor de la pizza selecionado supera el credito del cliente");

        }else{
            Pizza pizza=new Pizza(usuario,precioTotal);
           // CrearAlerta("INFORMATION","Compra Correcta","El pedido ha sido realizado");

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view2.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Pedido");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                                stage.showAndWait();

            }catch (Exception e){
                e.printStackTrace();

            }

        }
        btnCalcular.setDisable(true);
        btnComprar.setDisable(true);


    }

    @FXML
    void Iniciar(ActionEvent event) {
        boolean encontrado=false;
        int id= Integer.parseInt(tfUsuario.getText());
        for (Usuario user:listaUsuarios) {
            if(user.getId()==id){
                labelNombre.setFont(new Font(35));
                labelNombre.setText("Elige tus ingredientes: "+user.getNombre());
                ivFoto.setImage(new Image(user.getUrlImagen()));
                encontrado=true;
                btnCalcular.setDisable(false);

            }
        }
        if(!encontrado){
            CrearAlerta("INFORMATION","Usuario","Usuario no registrado en la base de datos");
            tfUsuario.setText("");
            labelNombre.setFont(new Font(40));
            labelNombre.setText("Elige tus ingredientes: ");
            ivFoto.setImage(null);
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
        btnCalcular.setDisable(true);
        btnComprar.setDisable(true);
        listaUsuarios=FXCollections.observableArrayList(
                new Usuario(1,"Maria",rutaAbsoluta+"\\image\\chica1.png",3),
                new Usuario(2,"Andres",rutaAbsoluta+"\\image\\icono1.png",12),
                new Usuario(3,"Belen",rutaAbsoluta+"\\image\\icono2.png",14),
                new Usuario(4,"Raul",rutaAbsoluta+"\\image\\icono3.png",16)

        );
        listaIngredientes= FXCollections.observableArrayList(
                new Ingrediente("Champiñones",2),
                new Ingrediente("Bacon",3),
                new Ingrediente("Queso",4),
                new Ingrediente("Atun",6),
                new Ingrediente("Anchoa",6)

        );

        tvTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colIngredientes.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));


        tvTable.setItems(listaIngredientes);
        tvTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Ingrediente>() {
            @Override
            public void onChanged(Change<? extends Ingrediente> change) {
               while (change.next()){
                    if(change.wasAdded()){
                       ingredientesSelecionados.addAll(change.getAddedSubList());
                    }
                    if(change.wasRemoved()){
                        ingredientesSelecionados.removeAll(change.getRemoved());
                    }
                }


            }
        });


    }
    private void CrearAlerta(String tipoAlerta, String titulo, String mensaje) {
        Alert alert=new Alert(Alert.AlertType.valueOf(tipoAlerta));
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
