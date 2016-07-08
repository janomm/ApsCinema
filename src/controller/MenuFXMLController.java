/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mi
 */
public class MenuFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnMenuFilme;
    
    @FXML
    private Button btnMenuSala;
    
    @FXML
    private Button btnMenuVenda;
    
    @FXML
    private Button btnMenuSecao;
    
    Alert alert = new Alert(Alert.AlertType.ERROR);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnMenuFilme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/FilmeFXML.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException ex) {
                    alert.setHeaderText("Erro ao acessar Filmes.");
                    alert.showAndWait();
                }
            }
        });
        
        btnMenuSala.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/SalaFXML.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException ex) {
                    alert.setHeaderText("Erro ao acessar Filmes.");
                    alert.showAndWait();
                }
            }
        });
        
        btnMenuSecao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/SecaoFXML.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException ex) {
                    alert.setHeaderText("Erro ao acessar Seção.");
                    //alert.setHeaderText(ex.getMessage());
                    alert.showAndWait();
                }
            }
        });
        
        btnMenuVenda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/VendaFXML.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (Exception e) {
                    alert.setHeaderText("Erro ao acessar Venda.");
                    alert.showAndWait();
                }
            }
        });
    }
}
