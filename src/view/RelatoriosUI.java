/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import model.Filme;
import model.Sala;
import model.Secao;
import model.Venda;
import repositorio.RepositorioException;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import repositorio.RepositorioVenda;
import util.Console;
import util.DateUtil;
import view.menu.RelatorioMenu;

/**
 * Classe Interface de Relatórios
 * @author Julliano
 */
public class RelatoriosUI {
    private RepositorioVenda listaVendas;
    private RepositorioSecao listaSecoes;
    private RepositorioSala listaSalas;
    private RepositorioFilme listaFilmes;

    /**
     * Método Construtor
     * @param listaVenda
     * @param listaSecao
     * @param listaSala
     * @param listaFilme 
     */
    public RelatoriosUI() {
        listaVendas = new RepositorioVenda();
        listaSecoes = new RepositorioSecao();
        listaSalas = new RepositorioSala();
        listaFilmes = new RepositorioFilme();
    }

    /**
     * Método de inicialização da interface
     */
    public void executar(){
        int opcao = 0;
        do {
            try{
                System.out.println(RelatorioMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case RelatorioMenu.OP_VENDAFILME:
                        relatorioVendaFilme();
                        break;
                    case RelatorioMenu.OP_VENDAHORARIO:
                        relatorioVendaHorario();
                        break;
                    case RelatorioMenu.OP_VENDASALA:
                        relatorioVendaSala();
                        break;
                    case RelatorioMenu.OP_VENDASECOES:
                        relatorioVendaSecoes();
                        break;
                    default:
                        System.err.println("Opção inválida..");
                }
            }
            catch(Exception ex){
                System.err.println("Opção inválida.");
                opcao= -1;
            }
        } while (opcao != RelatorioMenu.OP_VOLTAR);
    }
    
    /**
     * Interface que lista o relatório de vendas para um filme
     * @return 
     */
    public void relatorioVendaFilme(){
        try {
            new FilmeUI().listaFilmes();
            String nome = Console.scanString("Filme: ");
            if(listaFilmes.buscaFilmePorNome(nome).isEmpty()){
                throw new RuntimeException("Filme não encontrado.");
            }
            listarVendasPorFilme(nome);
        } catch (Exception e) {
            System.err.println("Filme não encontrado.");
        }
    }
    
    /**
     * Metodo que lista as vendas por filme
     * @param nome
     * @throws RepositorioException 
     */
    public void listarVendasPorFilme(String nome) throws RepositorioException{
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "SEÇÃO") + "\t"
                         + String.format("%-20s", "|FILME") + "\t"
                         + String.format("%-10s", "|SALA") + "\t"
                         + String.format("%-10s", "|HORÁRIO") + "\t"
                         + String.format("%-10s", "|VENDIDOS") + "\t");
        for (Venda venda : listaVendas.listar()){
            Secao secao = listaSecoes.buscaPorNumero(venda.getCodSecao());
            Filme filme = listaFilmes.buscaFilmePorCodigo(secao.getCodFilme());
            Sala sala = listaSalas.buscaSalaPorNumero(secao.getNumSala());
            if(filme.getNome().equalsIgnoreCase(nome)){
                System.out.println(String.format("%-10s", secao.getCodigo()) + "\t"
                + String.format("%-20s", "|" + filme.getNome()) + "\t"
                + String.format("%-10s", "|" + sala.getNumero()) + "\t"
                + String.format("%-10s", "|" + DateUtil.hourToString(secao.getHorario())) + "\t"
                + String.format("%-10s", "|" + venda.getQuantidade()) + "\t");
            }
        }
    }
    
    /**
     * Interface que lista o relatório de vendas por horário de seção
     * @return 
     */
    public void relatorioVendaHorario(){
        try{
            String h = Console.scanString("Digite a Hora: ");
            Date hora = DateUtil.stringToHour(h);
            listaVendaPorHorario(hora);
        } catch (Exception ex){
            System.err.println("Hora Inválida.");
        }
        
    }
    
    /**
     * Lista todas as vendas para a hora informada
     * @param hora 
     */
    public void listaVendaPorHorario(Date hora) throws RepositorioException{
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "SEÇÃO") + "\t"
                         + String.format("%-20s", "|FILME") + "\t"
                         + String.format("%-10s", "|SALA") + "\t"
                         + String.format("%-10s", "|HORÁRIO") + "\t"
                         + String.format("%-10s", "|VENDIDOS") + "\t");
        for (Venda venda : listaVendas.listar()){
            Secao secao = listaSecoes.buscaPorNumero(venda.getCodSecao());
            Filme filme = listaFilmes.buscaFilmePorCodigo(secao.getCodFilme());
            Sala sala = listaSalas.buscaSalaPorNumero(secao.getNumSala());
            String horaSecao = DateUtil.hourToString(secao.getHorario());
            String horaDigitada = DateUtil.hourToString(hora);
            
            //if(DateUtil.hourToString(secao.getHorario()) == DateUtil.hourToString(hora)){
            if(horaSecao.equals(horaDigitada)){
                System.out.println(String.format("%-10s", secao.getCodigo()) + "\t"
                + String.format("%-20s", "|" + filme.getNome()) + "\t"
                + String.format("%-10s", "|" + sala.getNumero()) + "\t"
                + String.format("%-10s", "|" + DateUtil.hourToString(secao.getHorario())) + "\t"
                + String.format("%-10s", "|" + venda.getQuantidade()) + "\t");
            }
        }
    }
    
    /**
     * Interface que lista o relatório de vendas por sala
     * @return 
     */
    public void relatorioVendaSala(){
        try {
            new SalaUI().listaSala();
            int numero = Console.scanInt("Selecione o número: ");
            Sala sala = listaSalas.buscaSalaPorNumero(numero);
            if(sala == null){
                throw new RuntimeException("Sala não encontrada.");
            }
            listaVendaPorSala(sala);
            
        } catch (Exception e) {
            System.err.println("Sala Inválida.");
        }
    }
    
    /**
     * Metodo que lista as vendas por sala
     * @param s
     * @throws RepositorioException 
     */
    public void listaVendaPorSala(Sala s) throws RepositorioException{
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "SEÇÃO") + "\t"
                         + String.format("%-20s", "|FILME") + "\t"
                         + String.format("%-10s", "|SALA") + "\t"
                         + String.format("%-10s", "|HORÁRIO") + "\t"
                         + String.format("%-10s", "|VENDIDOS") + "\t");
        for (Venda venda : listaVendas.listar()){
            Secao secao = listaSecoes.buscaPorNumero(venda.getCodSecao());
            Filme filme = listaFilmes.buscaFilmePorCodigo(secao.getCodFilme());
            Sala sala = listaSalas.buscaSalaPorNumero(secao.getNumSala());
            if(sala.getNumero() == s.getNumero()){
                System.out.println(String.format("%-10s", secao.getCodigo()) + "\t"
                + String.format("%-20s", "|" + filme.getNome()) + "\t"
                + String.format("%-10s", "|" + sala.getNumero()) + "\t"
                + String.format("%-10s", "|" + DateUtil.hourToString(secao.getHorario())) + "\t"
                + String.format("%-10s", "|" + venda.getQuantidade()) + "\t");
            }
        }
    }
    
    /**
     * Interface que lista o relatório de vendas para uma seção
     * @return 
     */
    public void relatorioVendaSecoes(){
        try {
            new SecaoUI().listaSecao();
            int numero = Console.scanInt("Selecione a seção: ");
            Secao secao = listaSecoes.buscaPorNumero(numero);
            if(secao == null){
                throw new RuntimeException("Seção não encontrada.");
            }
            listaVendaSecao(secao);
        } catch (Exception e) {
            System.err.println("Seção Inválida.");
        }
    }
    
    /**
     * lista as vendas de uma seção
     * @param secao 
     */
    public void listaVendaSecao(Secao s) throws RepositorioException{
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "SEÇÃO") + "\t"
                         + String.format("%-20s", "|FILME") + "\t"
                         + String.format("%-10s", "|SALA") + "\t"
                         + String.format("%-10s", "|HORÁRIO") + "\t"
                         + String.format("%-10s", "|VENDIDOS") + "\t");
        for (Venda venda : listaVendas.listar()){
            Secao secao = listaSecoes.buscaPorNumero(venda.getCodSecao());
            Filme filme = listaFilmes.buscaFilmePorCodigo(secao.getCodFilme());
            Sala sala = listaSalas.buscaSalaPorNumero(secao.getNumSala());
            if(secao.getCodigo() == s.getCodigo()){
                System.out.println(String.format("%-10s", secao.getCodigo()) + "\t"
                + String.format("%-20s", "|" + filme.getNome()) + "\t"
                + String.format("%-10s", "|" + sala.getNumero()) + "\t"
                + String.format("%-10s", "|" + DateUtil.hourToString(secao.getHorario())) + "\t"
                + String.format("%-10s", "|" + venda.getQuantidade()) + "\t");
            }
        }
    }
}
