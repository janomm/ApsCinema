/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.util.List;
import model.Filme;
import repositorio.RepositorioException;
import repositorio.RepositorioFilme;
import util.Console;
import view.menu.FilmeMenu;
/**
 * Classe Interface de Filmes
 * @author Julliano
 */
public class FilmeUI {
    private RepositorioFilme lista;
    
    /**
     * Método Construtor
     * @param lista 
     */
    public FilmeUI() {
        lista = new RepositorioFilme();
    }

    /**
     * Método de inicialização da interface
     */
    public void executar(){
        int opcao;
        do {
            try{
                System.out.println(FilmeMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case FilmeMenu.OP_NOVO:
                        insereFilme();
                        break;
                    case FilmeMenu.OP_REMOVER:
                        removeFilme();
                        break;
                    case FilmeMenu.OP_EDITAR:
                        editaFilme();
                        break;
                    case FilmeMenu.OP_LISTAR:
                        listaFilmes();
                        break;
                    case FilmeMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.err.println("Opção inválida-1.");
                }
            }
            catch(Exception ex){
                System.err.println("Opção inválida-2.");
                opcao= -1;
            }
        } while (opcao != FilmeMenu.OP_VOLTAR);
    }
    
    /**
     * Interface que executa a inclusão de um Filme
     */
    public void insereFilme() throws RepositorioException{
        String nome = Console.scanString("Nome: ");
        String genero = Console.scanString("Gênero: ");
        String sinopse = Console.scanString("Sinopse: ");
        try {
            if(nome.length() == 0 || genero.length() == 0|| sinopse.length() == 0){
                throw new RepositorioException("Valores não podem ser vazio.");
            }
            Filme filme = new Filme(lista.retornaCodigo(), nome, genero, sinopse);
            lista.salvar(filme);
        } catch (Exception e) {
            System.err.println("Erro na inserção.");
        }

    }
    
    /**
     * Interface que executa a exclusão de um filme
     */
    public boolean removeFilme(){
        listaFilmes();
        try{
            int codigo = Console.scanInt("Código: ");
            Filme filme = lista.buscaFilmePorCodigo(codigo);
            if(filme == null){
                System.err.println("Filme não cadastrado.");
                return false;
            } else {
                lista.deletar(filme);
                return true;
            }
        } catch (Exception ex){
            System.err.println("Código inválido");
            return false;
        }
    }
    
    /**
     * Interface que executa a edição de um filme
     */
    public boolean editaFilme(){
        listaFilmes();
        
        int codigo = Console.scanInt("Código: ");
        try {
            Filme filme = lista.buscaFilmePorCodigo(codigo);

            System.out.println("Digite os dados do filme que quer alterar [Vazio caso nao queira]");
            String genero = Console.scanString("Gênero: ");
            String sinopse = Console.scanString("Sinopse: ");
            
            if(!genero.isEmpty()){
                filme.setGenero(genero);
            }
            if(!sinopse.isEmpty()){
                filme.setSinopse(sinopse);
            }
            
            lista.atualizar(filme);
            System.out.println("Filme " + filme.getNome() + " atualizado com sucesso!");
            return true;
        } catch (RepositorioException ex) {
            System.err.println("Erro ao atualizar filme.");
        }
        return false;
    }
    
    /**
     * Método que lista um filme pelo código do mesmo
     * @param codigo 
     */
    public void listaFilme(int codigo){
        for (Filme filme : lista.listar()){
            if(filme.getCodigo().equals(codigo)){
                System.out.println("-----------------------------\n");
                System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                + String.format("%-20s", "|NOME") + "\t"
                + String.format("%-20s", "|GÊNERO") + "\t"
                + String.format("%-20s", "|SINOPSE"));
                System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
                + String.format("%-20s", "|" + filme.getNome()) + "\t"
                + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                + String.format("%-20s", "|" + filme.getSinopse()));
                
            }
        }
    }
    
    /**
     * Método que lista todos os filmes de uma lista
     * @param nome 
     */
    public void listaFilme(List<Filme> listafilme){
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
        + String.format("%-20s", "|NOME") + "\t"
        + String.format("%-20s", "|GÊNERO") + "\t"
        + String.format("%-20s", "|SINOPSE"));
        for(Filme filme : listafilme)
        System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
        + String.format("%-20s", "|" + filme.getNome()) + "\t"
        + String.format("%-20s", "|" + filme.getGenero()) + "\t"
        + String.format("%-20s", "|" + filme.getSinopse()));
    }
    
    /**
     * Método que lista todos os filmes
     */
    public void listaFilmes(){
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
        + String.format("%-20s", "|NOME") + "\t"
        + String.format("%-20s", "|GÊNERO") + "\t"
        + String.format("%-20s", "|SINOPSE"));
        for (Filme filme : lista.listar()){
            System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
            + String.format("%-20s", "|" + filme.getNome()) + "\t"
            + String.format("%-20s", "|" + filme.getGenero()) + "\t"
            + String.format("%-20s", "|" + filme.getSinopse()));
        }
    }
    
    
    
}
