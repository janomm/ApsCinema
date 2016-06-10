/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Secao;
import repositorio.RepositorioException;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import util.Console;
import util.DateUtil;
import view.menu.SecaoMenu;

/**
 * Classe Interface de Seção
 * @author Julliano
 */
public class SecaoUI {
    private RepositorioSecao listaSecao;
    private RepositorioSala listaSalas;
    private RepositorioFilme listaFilmes;

    /**
     * Método Construtor
     * @param listaSecao
     * @param listaSala
     * @param listaFilme 
     */
    public SecaoUI() {
        this.listaSecao = new RepositorioSecao();
        this.listaSalas = new RepositorioSala();
        this.listaFilmes = new RepositorioFilme();
    }
    
    /**
     * Método de inicialização da interface
     */
    public void executar(){
        int opcao = 0;
        do {
            try{
                System.out.println(SecaoMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case SecaoMenu.OP_NOVO:
                        insereSecao();
                        break;
                    case SecaoMenu.OP_REMOVER:
                        removeSecao();
                        break;
                    case SecaoMenu.OP_EDITAR:
                        editaSecao();
                        break;
                    case SecaoMenu.OP_LISTAR:
                        listaSecao();
                        break;
                    case SecaoMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.err.println("Opção inválida.!.");
                }
            }
            catch(Exception ex){
                System.err.println("Opção inválida!. " + opcao);
                opcao= -1;
            }
        } while (opcao != SecaoMenu.OP_VOLTAR);
    }
    
    /**
     * Interface que executa a inserção de uma seção
     * @return 
     */
    public void insereSecao(){
        try {
            listaFilmes();
            int codfilme = Console.scanInt("Digite o Código do Filme: ");
            Filme filme = listaFilmes.buscaFilmePorCodigo(codfilme);
            listaSala();
            int numsala = Console.scanInt("Digite o numero da sala: ");
            Sala sala = listaSalas.buscaSalaPorNumero(numsala);
            String h = Console.scanString("Digite o horário: ");
            Date horario = DateUtil.stringToHour(h);
            Secao secao = new Secao(listaSecao.retornaCodigo(), sala.getNumero(), horario, filme.getCodigo());
            
            listaSecao.salvar(secao);
            
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("Erro na digitação dos dados.");
        }
    }
    
    /**
     * Interface que executa a exclusão de uma seção
     * @return 
     */
    public void removeSecao() throws RepositorioException{
        listaSecao();
        try {
            int numero = Console.scanInt("Digite o numero da Seção: ");
            Secao secao = listaSecao.buscaPorNumero(numero);
            listaSecao.deletar(secao);
        } catch (Exception e) {
            throw new RuntimeException("Erro na digitação da seção.");
        }
    }
    
    /**
     * Metodo que edita a seção
     * @throws RepositorioException 
     */
    public void editaSecao() throws RepositorioException{
        listaSecao();
        try{
            int numero = Console.scanInt("Digite o código da seção: ");
            Secao secao = listaSecao.buscaPorNumero(numero);
            if(secao == null){
                throw new RuntimeException("Seção não encontrada.");
            } else{
                selecionaAlteracao(secao);
            }
            //listaSecao.atualizar(secao);
        } catch (Exception ex){
            System.err.println("Seção inválida.");
        } finally {
            
        }
    }
    
    /**
     * Metodo que inicializa as alterações
     * @param secao 
     */
    public void selecionaAlteracao(Secao secao){
        int opcao = 0;
        do {
            try{
                System.out.println(SecaoMenu.getOpcoesAlteracao());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case SecaoMenu.OP_ALTERAFILME:
                        alteraFilme(secao);
                        break;
                    case SecaoMenu.OP_ALTERASALA:
                        alteraSala(secao);
                        break;
                    case SecaoMenu.OP_ALTERAHORA:
                        alteraHorario(secao);
                        break;
                    case SecaoMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.err.println("Opção inválida..");
                }
            } catch(Exception ex){
                System.err.println("Opção inválida.");
                opcao= -1;
            }
        } while (opcao != SecaoMenu.OP_VOLTAR);
    }
    
    /**
     * Metodo que lista filme
     */
    public void listaFilmes(){
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
        + String.format("%-20s", "|NOME") + "\t"
        + String.format("%-20s", "|GÊNERO") + "\t"
        + String.format("%-20s", "|SINOPSE"));
        for (Filme filme : listaFilmes.listar()){
            System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
            + String.format("%-20s", "|" + filme.getNome()) + "\t"
            + String.format("%-20s", "|" + filme.getGenero()) + "\t"
            + String.format("%-20s", "|" + filme.getSinopse()));
        }
    }
    
    /**
     * Metodo que lista as salas
     */
    public void listaSala(){
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                         + String.format("%-10s", "|ASSENTOS"));
        for (Sala sala : listaSalas.listar()){
            System.out.println(String.format("%-10s", sala.getNumero()) + "\t"
            + String.format("%-10s", "|" + sala.getAssentos()));
        }
    }
    
    /**
     * Interface que lista todas as seções cadastradas
     */
    public void listaSecao() throws RepositorioException{
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "SEÇÃO") + "\t"
                         + String.format("%-20s", "|FILME") + "\t"
                         + String.format("%-10s", "|SALA") + "\t"
                         + String.format("%-10s", "|HORÁRIO") + "\t");
        for (Secao secao : listaSecao.listar()){
            Filme filme = listaFilmes.buscaFilmePorCodigo(secao.getCodFilme());
            Sala sala = listaSalas.buscaSalaPorNumero(secao.getNumSala());
            System.out.println(String.format("%-10s", secao.getCodigo()) + "\t"
            + String.format("%-20s", "|" + filme.getNome()) + "\t"
            + String.format("%-10s", "|" + sala.getNumero()) + "\t"
            + String.format("%-10s", "|" + DateUtil.hourToString(secao.getHorario())) + "\t");
        }
    }
    
    /**
     * Metodo que altera o filme
     * @param secao 
     */
    public void alteraFilme(Secao secao){
        try {
            listaFilmes();
            int codfilme = Console.scanInt("Selecione o novo filme: ");
            Filme filme = listaFilmes.buscaFilmePorCodigo(codfilme);
            if(filme == null){
                throw new RuntimeException("Filme não encontrado");
            } else {
                secao.setCodFilme(codfilme);
                listaSecao.atualizar(secao);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na digitação do filme.");
        }
    }
    
    /**
     * Metodo que altera a sala
     * @param secao 
     */
    public void alteraSala(Secao secao){
        try {
            listaSala();
            int codsala = Console.scanInt("Selecione a nova sala: ");
            Sala sala = listaSalas.buscaSalaPorNumero(codsala);
            if(sala == null){
                throw new RuntimeException("Sala não encontrada.");
            } else {
                secao.setNumSala(codsala);
                listaSecao.atualizar(secao);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro na digitação da sala.");
        }
    }
    
    /**
     * Metodo que altera o horario
     * @param secao 
     */
    public void alteraHorario(Secao secao){
        try {
            String h = Console.scanString("Digite novo horário: ");
            Date horario = DateUtil.stringToHour(h);
            secao.setHorario(horario);
            listaSecao.atualizar(secao);
            
        } catch (Exception e) {
            throw new RuntimeException("Erro na digitação do horário.");
        }
    }
    
    
}
