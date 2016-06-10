/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.SalaDao;
import model.Sala;
import repositorio.RepositorioSala;
import util.Console;
import view.menu.SalaMenu;
/**
 * Classe Interface de Salas
 * @author Julliano
 */
public class SalaUI {
    private RepositorioSala lista;

    /**
     * Método Construtor
     * @param lista 
     */
    public SalaUI() {
        lista = new RepositorioSala();
    }
    
    /**
     * Método de inicialização da interface
     */
    public void executar(){
        int opcao = 0;
        do {
            try{
                System.out.println(SalaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case SalaMenu.OP_NOVO:
                        insereSala();
                        break;
                    case SalaMenu.OP_REMOVER:
                        removeSala();
                        break;
                    case SalaMenu.OP_EDITAR:
                        editaSala();
                        break;
                    case SalaMenu.OP_LISTAR:
                        listaSala();
                        break;
                    case SalaMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.err.println("Opção inválida..");
                }
            }
            catch(Exception ex){
                System.err.println("Opção inválida.");
                opcao= -1;
            }
        } while (opcao != SalaMenu.OP_VOLTAR);
    }
    
    /**
     * Interface que executa a inserção de uma sala
     */
    public void insereSala(){
        int assentos = Console.scanInt("Assentos: ");
        try{
                Sala sala = new Sala(lista.retornaNumero(), assentos);
                lista.salvar(sala);
        } catch (Exception e){
            System.err.println("Quantidade Inválida.");
        }
    }
    
    /**
     * Interface que executa a remoção de uma sala
     */
    public void removeSala(){
        listaSala();
        try{
            Integer numero = Console.scanInt("Número: ");
            Sala sala = lista.buscaSalaPorNumero(numero);
            if(sala == null){
                System.err.println("Sala não Cadastrada.");
            } else {
                 lista.deletar(sala);
            }
        } catch(Exception ex){
            System.err.println("Quantidade Inválida.");
        }
    }
    
    /**
     * Interface que lista todas ass salas
     */
    public void listaSala(){
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                         + String.format("%-10s", "|ASSENTOS"));
        for (Sala sala : lista.listar()){
            System.out.println(String.format("%-10s", sala.getNumero()) + "\t"
            + String.format("%-10s", "|" + sala.getAssentos()));
        }
    }
    
    /**
     * Interface que lista a sala por numero
     * @param numero 
     */
    public void listaSala(Integer numero){
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                         + String.format("%-10s", "|ASSENTOS"));
        for (Sala sala : lista.listar()){
            if(sala.getNumero().equals(numero)){
                System.out.println(String.format("%-10s", sala.getNumero()) + "\t"
                + String.format("%-10s", "|" + sala.getAssentos()));
            }
        }
    }
    
    /**
     * Interface que executa a edição de uma sala
     */
    public void editaSala(){
        listaSala();
        try{
            int numero = Console.scanInt("Numero: ");
            Sala sala = lista.buscaSalaPorNumero(numero);
            if(sala == null){
                System.err.println("Sala não Cadastrada.");
            } else {
                int assentos = Console.scanInt("Assentos: ");
                if(assentos > 0){
                    sala.setAssentos(assentos);
                    lista.atualizar(sala);
                } else {
                    System.err.println("Quantidade de assentos inválida. Alteração não Realizada.");
                }
            }
        } catch (Exception e){
            System.err.println("Número inválido.");
        }
    }
}