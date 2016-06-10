/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import view.menu.VendaMenu;

/**
 * Classe Interface de Vendas
 * @author Julliano
 */
public class VendaUI {
    private RepositorioSecao listaSecao;
    private RepositorioSala listaSalas;
    private RepositorioFilme listaFilmes;
    private RepositorioVenda listaVenda;

    /**
     * Método Construtor
     * @param listaSecao
     * @param listaSala
     * @param listaFilme 
     */
    public VendaUI() {
        this.listaSecao = new RepositorioSecao();
        this.listaSalas = new RepositorioSala();
        this.listaFilmes = new RepositorioFilme();
        this.listaVenda = new RepositorioVenda();
    }
    
    
    /**
     * Metodo que inicializa as vendas
     */
    public void executar(){
        int opcao = 0;
        do {
            try{
                try{
                    System.out.println(VendaMenu.getOpcoes());
                    opcao = Console.scanInt("Digite sua opção:");
                    switch (opcao) {
                        case VendaMenu.OP_VENDA:
                            vendaSecao();
                            break;
                        case VendaMenu.OP_LISTAVENDA:
                            listaVenda();
                            break;
                        case VendaMenu.OP_VOLTAR:
                            System.out.println("Retornando ao menu principal..");
                            break;
                        default:
                            System.err.println("Opção inválida..");
                    }
                }catch(Exception ex){
                    System.err.println("Opção inválida.");
                    opcao= -1;
                }
            }
            catch(Exception ex){
                System.err.println("Opção inválida.");        
            }
        } while (opcao != VendaMenu.OP_VOLTAR);
    }
    
    /**
     * Interface que executa a inserção de uma venda
     * @return 
     */
    public void vendaSecao() throws RepositorioException{
        /*SecaoUI secaoUI = new SecaoUI();
        secaoUI.listaSecao();*/
        new SecaoUI().listaSecao();
        try {
            int codsecao = Console.scanInt("Selecione a Seção: ");
            Secao secao = listaSecao.buscaPorNumero(codsecao);
            if(secao == null){
                throw new RepositorioException("Seção não encontrada.");
            } else {
                int assentoTotal = listaVenda.retornaTotalAssentosSecao(secao);
                int assentoDisponivel = 0;
                int assentos = Console.scanInt("Quantidade de assentos: ");
                boolean existe = listaVenda.vendaExiste(secao);
                if (existe) {
                    assentoDisponivel = listaVenda.retornaAssentosVenda(secao);
                    assentos = assentos + assentoDisponivel;
                    if(assentos <= assentoTotal){
                        Venda venda = new Venda(secao.getCodigo(), assentos);
                        listaVenda.atualizar(venda);
                    } else {
                        throw new RuntimeException("Quantidade informada é superior a disponível.");
                    }
                } else {
                    if(assentos <= assentoTotal){
                        Venda venda = new Venda(secao.getCodigo(), assentos);
                        listaVenda.salvar(venda);
                    } else {
                        throw new RuntimeException("Quantidade informada é superior a disponível.");
                    }
                }
            } 
        } catch (Exception e) {
            throw new RuntimeException("Valor digitado inválido");
        }
    }
    
    /**
     * Interface que lista todas as vendas de ingressos
     */
    public void listaVenda() throws RepositorioException{
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "SEÇÃO") + "\t"
                         + String.format("%-20s", "|FILME") + "\t"
                         + String.format("%-10s", "|SALA") + "\t"
                         + String.format("%-10s", "|HORÁRIO") + "\t"
                         + String.format("%-10s", "|VENDIDOS") + "\t");
        for (Venda venda : listaVenda.listar()){
            Secao secao = listaSecao.buscaPorNumero(venda.getCodSecao());
            Filme filme = listaFilmes.buscaFilmePorCodigo(secao.getCodFilme());
            Sala sala = listaSalas.buscaSalaPorNumero(secao.getNumSala());
            System.out.println(String.format("%-10s", secao.getCodigo()) + "\t"
            + String.format("%-20s", "|" + filme.getNome()) + "\t"
            + String.format("%-10s", "|" + sala.getNumero()) + "\t"
            + String.format("%-10s", "|" + DateUtil.hourToString(secao.getHorario())) + "\t"
            + String.format("%-10s", "|" + venda.getQuantidade()) + "\t");
        }
    }
    
    
}
