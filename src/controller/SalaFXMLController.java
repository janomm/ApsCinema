/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.Sala;
import model.Secao;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;

/**
 * FXML Controller class
 *
 * @author mi
 */
public class SalaFXMLController implements Initializable {

    @FXML
    private TableView<Sala> tableViewSala;
    
    @FXML
    private TableColumn<Sala, String> x;
    
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableColumn<Sala, String> tableColumnSala;
    @FXML
    private TableColumn<Sala, String> tableColumnAssentos;
    @FXML
    private TextField textFieldAssentos;
    
    
    private ObservableList<Sala> observableListSala;
    private List<Sala> listaSala;
    private RepositorioSala repositorioSala;
    private int tipo = 0;
    private RepositorioSecao repositorioSecao = new RepositorioSecao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        repositorioSala = new RepositorioSala();
        desabilitar(true);
        carregarTableViewSalas();
        
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tableViewSala.getSelectionModel().isEmpty()){
                    Sala s = tableViewSala.getSelectionModel().getSelectedItem();
                    if(repositorioSecao.buscaSecaoPorSala(s).isEmpty()){
                        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfirmation.setTitle("Exclusão");
                        alertConfirmation.setHeaderText(s.getNumero().toString());
                        alertConfirmation.setContentText("Excluir a sala " + s.getNumero().toString() + "?");
                        Optional<ButtonType> result = alertConfirmation.showAndWait();
                        if(result.get() == ButtonType.OK){
                            try{
                                repositorioSala.deletar(s);
                                carregarTableViewSalas();
                            } catch (Exception ex){
                                AlertError("Erro ao excluir a sala " + s.getNumero().toString());
                            }
                        }
                    } else {
                        AlertError("Seções relacionadas a sala.");
                    }
                } else {
                    AlertError("Nenhuma Sala Selecionada.");
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
                    AlertError("Não é possivel incluir sala.");
                }
            }
        });
        
        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tableViewSala.getSelectionModel().isEmpty()){
                    desabilitar(false);
                    desabilitarBotoes(true);
                    tipo = 2;
                    Sala s = tableViewSala.getSelectionModel().getSelectedItem();
                    textFieldAssentos.setText(s.getAssentos().toString());
                }
                else {
                    AlertError("Necessário selecionar Sala.");
                }
            }
        });
        
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                retomarStatus();
            }
        });
        
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(textFieldAssentos.getText().isEmpty()){
                    AlertError("Necessário preencher a quantidade de assentos.");
                } else {
                    int assentos = validaAssentos(textFieldAssentos.getText());
                    if(assentos < 1){
                        AlertError("Quantidade de assentos inválida.");
                    } else {
                        try {
                            Sala s;
                            switch(tipo){
                                case 1:
                                    s = new Sala(repositorioSala.retornaNumero(), assentos);
                                    repositorioSala.salvar(s);
                                    retomarStatus();
                                break;
                                case 2:
                                    s = new Sala(tableViewSala.getSelectionModel().getSelectedItem().getNumero(), assentos);
                                    repositorioSala.atualizar(s);
                                    retomarStatus();
                                break;
                                default:
                                    AlertError("Erro ao executar ação.");
                                break;
                            }
                            carregarTableViewSalas();
                        } catch (Exception e) {
                            if (tipo == 1 )
                                AlertError("Erro ao incluir sala");
                            else
                                AlertError("Erro ao alterar sala");
                        }
                    }
                }
            }
        });
    }
    
    public void desabilitar(boolean status){
        btnCancelar.setDisable(status);
        btnOk.setDisable(status);
        textFieldAssentos.setDisable(status);
    }
    
    public void desabilitarBotoes(boolean status){
        btnIncluir.setDisable(status);
        btnEditar.setDisable(status);
        btnExcluir.setDisable(status);
        tableViewSala.setDisable(status);
    }
    
    public void AlertError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(text);
        alert.setTitle("Erro");
        //alert.setContentText(text);
        alert.showAndWait();
    }
    
    public void retomarStatus(){
        desabilitar(true);
        desabilitarBotoes(false);
        textFieldAssentos.setText("");
        tipo = 0;
    }
    
    public void carregarTableViewSalas(){
        tableColumnSala.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableColumnAssentos.setCellValueFactory(new PropertyValueFactory<>("assentos"));

        listaSala = repositorioSala.listar();

        observableListSala = FXCollections.observableArrayList(listaSala);
        tableViewSala.setItems(observableListSala);
    }
    
    public int validaAssentos(String valor){
        int assentos = 0;
        try {
            assentos = Integer.parseInt(valor);
            return assentos;
        } catch (Exception e) {
            return 0;
        }
    }
}
