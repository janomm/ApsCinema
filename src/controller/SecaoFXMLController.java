/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Filme;
import model.Sala;
import model.Secao;
import model.SecaoView;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import repositorio.RepositorioVenda;
import util.DateUtil;

/**
 * FXML Controller class
 *
 * @author mi
 */
public class SecaoFXMLController implements Initializable {

    @FXML
    private TableView<SecaoView> tableViewSecao;
    @FXML
    private TableColumn<SecaoView, Integer> tableColumnSecao;
    @FXML
    private TableColumn<SecaoView, String> tableColumnFilme;
    @FXML
    private TableColumn<SecaoView, Integer> tableColumnSala;
    @FXML
    private TableColumn<SecaoView, String> tableColumnHora;
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private ComboBox<String> comboBoxFilme;
    @FXML
    private ComboBox<Integer> comboBoxSala;
    @FXML
    private TextField textFieldHora;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancelar;
    
    private ObservableList<SecaoView> observableListSecao;
    private List<SecaoView> listaSecaoView;
    private RepositorioSecao repositorioSecao = new RepositorioSecao();
    private RepositorioFilme repositorioFilme = new RepositorioFilme();
    private RepositorioSala repositorioSala = new RepositorioSala();
    private int tipo = 0;
    private RepositorioVenda repositorioVenda = new RepositorioVenda();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTableViewSecao();
        desabilitar(true);
        
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tableViewSecao.getSelectionModel().isEmpty()){
                    Secao secao = repositorioSecao.buscaPorNumero(tableViewSecao.getSelectionModel().getSelectedItem().getCodSecao());
                    if(!repositorioVenda.vendaExiste(secao)){
                        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfirmation.setTitle("Exclusão");
                        alertConfirmation.setHeaderText(secao.getCodigo().toString());
                        alertConfirmation.setContentText("Excluir a seção " + secao.getCodigo() + " das " + DateUtil.hourToString(secao.getHorario()) + " horas?");
                        Optional<ButtonType> result = alertConfirmation.showAndWait();
                        if(result.get() == ButtonType.OK){
                            try{
                                //repositorioSecao.deletar(secao);
                                carregaTableViewSecao();
                            } catch (Exception ex){
                                AlertError("Erro ao excluir seção " + secao.getCodigo());
                            }
                        }
                    } else {
                        AlertError("Exclusão inválida. Ingressos vendidos.");
                    }
                } else {
                    AlertError("Nenhuma Seção Selecionada.");
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
                    carregaComboFilme("");
                    carregaComboSala(0);
                } catch (Exception ex){
                    AlertError("Não é possivel incluir seção.");
                }
            }
        });
        
        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tableViewSecao.getSelectionModel().isEmpty()){
                    desabilitar(false);
                    desabilitarBotoes(true);
                    tipo = 2;
                    SecaoView secaoView = tableViewSecao.getSelectionModel().getSelectedItem();
                    Secao secao = repositorioSecao.buscaPorNumero(secaoView.getCodSecao());
                    carregaComboFilme(secaoView.getFilme());
                    carregaComboSala(secao.getNumSala());
                    textFieldHora.setText(DateUtil.hourToString(secao.getHorario()));
                } else {
                    AlertError("Necessário selecionar Seção.");
                }
            }
        });
        
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(textFieldHora.getText().length() < 1){
                    AlertError("Hora deve ser preenchida.");
                } else {
                    try {
                        Date hora = DateUtil.stringToHour(textFieldHora.getText());
                        int codigo = 0;
                        int codSala = 0;
                        int codfilme = 0;
                        Secao secao;
                        switch(tipo){
                            case 1:
                                codigo = repositorioSecao.retornaCodigo();
                                codSala = comboBoxSala.getValue();
                                codfilme = 0;
                                for(Filme f : repositorioFilme.buscaFilmePorNome(comboBoxFilme.getValue())){
                                    codfilme = f.getCodigo();
                                }
                                secao = new Secao(codigo, codSala, hora, codfilme);
                                repositorioSecao.salvar(secao);
                                retomarStatus();
                            break;
                            case 2:
                                codigo = tableViewSecao.getSelectionModel().getSelectedItem().getCodSecao();
                                codSala = comboBoxSala.getValue();
                                codfilme = 0;
                                for(Filme f : repositorioFilme.buscaFilmePorNome(comboBoxFilme.getValue())){
                                    codfilme = f.getCodigo();
                                }
                                secao = new Secao(codigo, codSala, hora, codfilme);
                                repositorioSecao.atualizar(secao);
                                retomarStatus();
                            break;
                        }
                        carregaTableViewSecao();                        
                    } catch (Exception e) {
                        AlertError("Hora inválida.");
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
    
    public void carregaTableViewSecao(){
        tableColumnSecao.setCellValueFactory(new PropertyValueFactory<>("codSecao"));
        tableColumnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnSala.setCellValueFactory(new PropertyValueFactory<>("numSala"));
        tableColumnFilme.setCellValueFactory(new PropertyValueFactory<>("filme"));
        
        listaSecaoView = retornaListaSecaoView();
        
        observableListSecao = FXCollections.observableArrayList(listaSecaoView);
        tableViewSecao.setItems(observableListSecao);
        
    }
    
    public void carregaComboFilme(String filme){
        String def = "";
        for(Filme f : repositorioFilme.listar()){
            if(def.length() == 0){
                def = f.getNome();
            }
            comboBoxFilme.getItems().remove(f.getNome());
            comboBoxFilme.getItems().add(f.getNome());
        }
        
        if(filme.length() > 0)
            comboBoxFilme.setValue(filme);
        else
            comboBoxFilme.setValue(def);
    }
    
    public void carregaComboSala(int codSala){
        int def = 0;
        for(Sala s : repositorioSala.listar()){
            if(def == 0){
                def = s.getNumero();
            }
            comboBoxSala.getItems().remove(s.getNumero());
            comboBoxSala.getItems().add(s.getNumero());
        }
        
        if(codSala > 0)
            comboBoxSala.setValue(codSala);
        else
            comboBoxSala.setValue(def);
    }
    
    public List<SecaoView> retornaListaSecaoView(){
        try {
            List<SecaoView> listaSecaoViewRetorno = new ArrayList<>();
            for(Secao secao : repositorioSecao.listar()){
               Filme filme = repositorioFilme.buscaFilmePorCodigo(secao.getCodFilme());
               Sala sala = repositorioSala.buscaSalaPorNumero(secao.getNumSala());
               SecaoView sv = new SecaoView();
               sv.setCodSecao(secao.getCodigo());
               sv.setHora(DateUtil.hourToString(secao.getHorario()));
               sv.setCodFilme(filme.getCodigo());
               sv.setFilme(filme.getNome());
               sv.setAssentos(sala.getAssentos());
               sv.setNumSala(sala.getNumero());
               listaSecaoViewRetorno.add(sv);
            }
            return listaSecaoViewRetorno;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void AlertError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(text);
        alert.setTitle("Erro");
        //alert.setContentText(text);
        alert.showAndWait();
    }
    
    public void desabilitar(boolean status){
        textFieldHora.setDisable(status);
        comboBoxFilme.setDisable(status);
        comboBoxSala.setDisable(status);
        btnOk.setDisable(status);
        btnCancelar.setDisable(status);
    }
    public void desabilitarBotoes(boolean status){
        btnIncluir.setDisable(status);
        btnExcluir.setDisable(status);
        btnEditar.setDisable(status);
        tableViewSecao.setDisable(status);
    }
    public void retomarStatus(){
        desabilitar(true);
        desabilitarBotoes(false);
        textFieldHora.setText("");
        comboBoxFilme.setValue(null);
        comboBoxSala.setValue(null);
        tipo = 0;
    }
}
