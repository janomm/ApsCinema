/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Filme;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import repositorio.RepositorioVenda;
import view.menu.MainMenu;

import util.Console;

/**
 * Classe Interface Principal do Sistema
 * @author Julliano
 */
public class MainUI {
    private RepositorioFilme listaFilmes;
    private RepositorioSecao listaSecoes;
    private RepositorioSala listaSalas;
    private RepositorioVenda listaVendas;
    
    /**
     * Método Construtor
     */
    public MainUI() {
        listaFilmes = new RepositorioFilme();
        listaSecoes = new RepositorioSecao();
        listaSalas  = new RepositorioSala();
        listaVendas = new RepositorioVenda();
        
    }
    
    /**
     * Método de inicialização da interface
     */
    public void executar() {
        int opcao = 0;
        do {
            try{
                System.out.println(MainMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case MainMenu.OP_FILME:
                        new FilmeUI().executar();
                        break;
                    case MainMenu.OP_SALA:
                        new SalaUI().executar();
                        break;
                    case MainMenu.OP_SECAO:
                        new SecaoUI().executar();
                        break;
                    case MainMenu.OP_VENDA:
                        new VendaUI().executar();
                        break;
                    case MainMenu.OP_RELATORIOS:
                        new RelatoriosUI().executar();
                        break;
                    case MainMenu.OP_SAIR:
                        System.out.println("Aplicação finalizada!!!");
                        break;
                    default:
                        System.err.println("Opção inválida..");

                }
            }
            catch(Exception ex){
                System.err.println("Opção inválida.");
                opcao= -1;
            }
        } while (opcao != MainMenu.OP_SAIR);
    }
}
