/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Filme;
import model.Sala;
import model.Secao;
import model.SecaoView;
import model.Venda;
import model.VendaView;
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
public class VendaFXMLController implements Initializable {

    @FXML
    private TableView<VendaView> tableViewVenda;
    @FXML
    private TableColumn<VendaView, Integer> tableColumnSecao;
    @FXML
    private TableColumn<VendaView, String> tableColumnFilme;
    @FXML
    private TableColumn<VendaView, Integer> tableColumnSala;
    @FXML
    private TableColumn<VendaView, Integer> tableColumnAssentos;
    @FXML
    private TableColumn<VendaView, Integer> tableColumnVendidos;
    @FXML
    private TableColumn<VendaView, String> tableColumnHora;
    @FXML
    private Button btnVender;
    @FXML
    private Button btnEstornar;
    @FXML
    private TextField textFieldQuantidade;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancelar;
    
    private ObservableList<VendaView> observableListVenda;
    private List<VendaView> listaVendaView;
    private RepositorioVenda repositorioVenda = new RepositorioVenda();
    private RepositorioSecao repositorioSecao = new RepositorioSecao();
    private RepositorioFilme repositorioFilme = new RepositorioFilme();
    private RepositorioSala repositorioSala = new RepositorioSala();
    int tipo = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTableViewVenda();
        desabilitar(true);
        
        btnVender.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                carregaCampo();
                tipo = 1;
            }
        });
        
        btnEstornar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                carregaCampo();
                tipo = 2;
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
                if(textFieldQuantidade.getText().length() > 0){
                    try {
                        int quantidade = Integer.parseInt(textFieldQuantidade.getText());
                        VendaView vendaView = tableViewVenda.getSelectionModel().getSelectedItem();
                        Secao secao = repositorioSecao.buscaPorNumero(vendaView.getCodSecao());
                        Filme filme = repositorioFilme.buscaFilmePorCodigo(secao.getCodFilme());
                        Sala sala = repositorioSala.buscaSalaPorNumero(secao.getNumSala());
                        Venda venda;
                        if(quantidade > 0){
                            if(repositorioVenda.vendaExiste(secao)){
                                venda = new Venda(secao.getCodigo(), repositorioVenda.retornaAssentosVenda(secao));
                                if(tipo == 1){
                                    venda.setQuantidade(quantidade + venda.getQuantidade());
                                    if(venda.getQuantidade() < sala.getAssentos()){
                                        repositorioVenda.atualizar(venda);
                                    } else {
                                        AlertError("Ingressos esgotados.");
                                    }
                                } else {
                                    venda.setQuantidade(venda.getQuantidade() - quantidade);
                                    if(venda.getQuantidade() > 0){
                                        repositorioVenda.atualizar(venda);
                                    } else {
                                        AlertError("Quantidade inválida para estorno.");
                                    }
                                }
                            } else {
                                if(tipo == 1){
                                    venda = new Venda(secao.getCodigo(), quantidade);
                                    if(quantidade > sala.getAssentos()){
                                        AlertError("Quantidade informada maior que a disponível.");
                                    } else {
                                        repositorioVenda.salvar(venda);
                                    }
                                } else {
                                    AlertError("Não é possível realizar estorno.");
                                }
                            }
                        } else {
                            AlertError("Quantidade não pode ser negativa.");
                        }
                    } catch (Exception e) {
                        AlertError("Quantidade inválida.");
                    }
                } else {
                    AlertError("Quantidade inválida.");
                }
                retomarStatus();
                carregaTableViewVenda();
            }
        });
    }
    
    public void carregaCampo(){
        if(!tableViewVenda.getSelectionModel().isEmpty()){
            desabilitar(false);
            desabilitarBotoes(true);
            VendaView v = tableViewVenda.getSelectionModel().getSelectedItem();
            Secao s = repositorioSecao.buscaPorNumero(v.getCodSecao());
            textFieldQuantidade.setText("");
            /*if(repositorioVenda.vendaExiste(s)){
                textFieldQuantidade.setText(v.getQuantidade().toString());
            } else {
                textFieldQuantidade.setText("");
            }*/
        } else {
            AlertError("Necessário selecionar uma seção.");
        }
    }
    
    public void carregaTableViewVenda(){
        tableColumnSecao.setCellValueFactory(new PropertyValueFactory<>("codSecao"));
        tableColumnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnSala.setCellValueFactory(new PropertyValueFactory<>("numSala"));
        tableColumnFilme.setCellValueFactory(new PropertyValueFactory<>("filme"));
        tableColumnAssentos.setCellValueFactory(new PropertyValueFactory<>("assentos"));
        tableColumnVendidos.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        listaVendaView = retornaListaVendaView();
        
        observableListVenda = FXCollections.observableArrayList(listaVendaView);
        tableViewVenda.setItems(observableListVenda);
    }
    
    public List<VendaView> retornaListaVendaView(){
        try {
            List<VendaView> lista = new ArrayList<>();
            for(Secao secao : repositorioSecao.listar()){
               Filme filme = repositorioFilme.buscaFilmePorCodigo(secao.getCodFilme());
               Sala sala = repositorioSala.buscaSalaPorNumero(secao.getNumSala());
               VendaView vendaView = new VendaView();
               vendaView.setCodSecao(secao.getCodigo());
               vendaView.setHora(DateUtil.hourToString(secao.getHorario()));
               vendaView.setCodFilme(filme.getCodigo());
               vendaView.setFilme(filme.getNome());
               vendaView.setAssentos(sala.getAssentos());
               vendaView.setNumSala(sala.getNumero());
               if(repositorioVenda.vendaExiste(secao)){
                   vendaView.setQuantidade(repositorioVenda.retornaAssentosVenda(secao));
               }
               lista.add(vendaView);
            }
            return lista;
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
        textFieldQuantidade.setDisable(status);
        btnOk.setDisable(status);
        btnCancelar.setDisable(status);
    }
    
    public void desabilitarBotoes(boolean status){
        btnVender.setDisable(status);
        btnEstornar.setDisable(status);
        tableViewVenda.setDisable(status);
    }
    
    public void retomarStatus(){
        desabilitar(true);
        desabilitarBotoes(false);
        textFieldQuantidade.setText("");
        tipo = 0;
    }
    
}
