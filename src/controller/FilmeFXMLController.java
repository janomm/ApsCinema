/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cinema.Cinema;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Filme;
import model.Secao;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSecao;

/**
 * FXML Controller class
 *
 * @author mi
 */
public class FilmeFXMLController implements Initializable {

    @FXML
    private TableView<Filme> tableViewFilmes;
    @FXML
    private TableColumn<Filme, String> tableColumnFilme;
    @FXML
    private TableColumn<Filme, String> tableColumnGenero;
    @FXML
    private TableColumn<Filme, String> tableColumnSinopse;
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField textFieldFilme;
    @FXML
    private TextField textFieldGenero;
    @FXML
    private TextField textFieldSinopse;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancelar;
    
    private ObservableList<Filme> observableListFilme;
    private List<Filme> listaFilme;
    private RepositorioFilme repositorioFilme;
    private int tipo = 0;
    
    private RepositorioSecao repositorioSecao = new RepositorioSecao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        repositorioFilme = new RepositorioFilme();
        carregarTableViewFilmes();
        desabilitar(true);
        
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tableViewFilmes.getSelectionModel().isEmpty()){
                    Filme f = tableViewFilmes.getSelectionModel().getSelectedItem();
                    if(repositorioSecao.buscaSecaoPorFilme(f).isEmpty()){
                        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfirmation.setTitle("Exclusão");
                        alertConfirmation.setHeaderText(f.getNome());
                        alertConfirmation.setContentText("Excluir o filme " + f.getNome() + "?");
                        Optional<ButtonType> result = alertConfirmation.showAndWait();
                        if(result.get() == ButtonType.OK){
                            try{
                                repositorioFilme.deletar(f);
                                carregarTableViewFilmes();
                            } catch (Exception ex){
                                AlertError("Erro ao excluir filme " + f.getNome());
                            }
                        }
                    } else {
                        AlertError("Seções associadas ao Filme.");
                    }
                } else {
                    AlertError("Nenhum Filme Selecionado.");
                }
            }
        });
        
        btnIncluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    desabilitar(false);
                    desabilitarBotoes(true);
                    tipo = 1;
                } catch (Exception ex){
                    AlertError("Não é possivel incluir filme.");
                }
            }
        });
        
        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tableViewFilmes.getSelectionModel().isEmpty()){
                    desabilitar(false);
                    desabilitarBotoes(true);
                    tipo = 2;
                    Filme f = tableViewFilmes.getSelectionModel().getSelectedItem();
                    textFieldFilme.setText(f.getNome());
                    textFieldGenero.setText(f.getGenero());
                    textFieldSinopse.setText(f.getSinopse());
                }
                else {
                    AlertError("Necessário selecionar Filme.");
                }
            }
        });
        
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(textFieldFilme.getText().isEmpty() || 
                        textFieldGenero.getText().isEmpty() || 
                        textFieldSinopse.getText().isEmpty()){
                    AlertError("Necessário preencher todos os campos.");
                } else {
                    try {
                        Filme f;
                        switch (tipo){
                            case 1:
                                f = new Filme(repositorioFilme.retornaCodigo(), textFieldFilme.getText(), textFieldGenero.getText(), textFieldSinopse.getText());
                                repositorioFilme.salvar(f);
                                retomarStatus();
                            break;
                            case 2:
                                f = new Filme(tableViewFilmes.getSelectionModel().getSelectedItem().getCodigo(), textFieldFilme.getText(), textFieldGenero.getText(), textFieldSinopse.getText());
                                repositorioFilme.atualizar(f);
                                retomarStatus();
                            break;
                            default:
                                AlertError("Erro ao executar ação.");
                            break;
                        }
                        carregarTableViewFilmes();
                    } catch (Exception e) {
                        if(tipo == 1)
                            AlertError("Erro ao incluir filme " + textFieldFilme.getText());
                        else
                            AlertError("Erro ao alterar filme " + textFieldFilme.getText());
                    }
                }
            }
        });
        
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                retomarStatus();
            }
        });
    }
    
    public void carregarTableViewFilmes(){
        tableColumnFilme.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tableColumnSinopse.setCellValueFactory(new PropertyValueFactory<>("sinopseee"));

        listaFilme = repositorioFilme.listar();

        observableListFilme = FXCollections.observableArrayList(listaFilme);
        tableViewFilmes.setItems(observableListFilme);
    }
    
    public void AlertError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(text);
        alert.setTitle("Erro");
        //alert.setContentText(text);
        alert.showAndWait();
    }
    
    public void desabilitar(boolean status){
        textFieldFilme.setDisable(status);
        textFieldGenero.setDisable(status);
        textFieldSinopse.setDisable(status);
        btnOk.setDisable(status);
        btnCancelar.setDisable(status);
    }
    public void desabilitarBotoes(boolean status){
        btnIncluir.setDisable(status);
        btnExcluir.setDisable(status);
        btnEditar.setDisable(status);
        tableViewFilmes.setDisable(status);
    }
    public void retomarStatus(){
        desabilitar(true);
        desabilitarBotoes(false);
        textFieldFilme.setText("");
        textFieldGenero.setText("");
        textFieldSinopse.setText("");
        tipo = 0;
    }
}
