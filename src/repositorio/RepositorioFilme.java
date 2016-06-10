/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import dao.FilmeDao;
import dao.imp_BD.FilmeDaoBd;
import java.util.ArrayList;
import java.util.List;
import model.Filme;

/**
 * Classe RepositorioFilme
 * @author Julliano
 */
public class RepositorioFilme {
    private FilmeDao filmeDao;
    
    /**
     * Método Construtor
     */ 
    public RepositorioFilme(){
        filmeDao = new FilmeDaoBd();
    }
    
    /**
     * Método salvar
     * @param filme
     * @throws RepositorioException 
     */
    public void salvar(Filme filme) throws RepositorioException{
        this.validarCamposObrigatorios(filme);
        this.validarNomeExistente(filme.getNome());
        filmeDao.salvar(filme);
    }
    
    /**
     * Método listar
     * @param filme
     * @throws RepositorioException 
     */
    public List<Filme> listar(){
        return (filmeDao.listar());
    }
    
    /**
     * Método deletar
     * @param filme
     * @throws RepositorioException 
     */
    public void deletar(Filme filme) throws RepositorioException{
        if(filme == null){
            throw new RepositorioException("Filme não encontrado.");
        }
        filmeDao.deletar(filme);
    }
    
    /**
     * Método atualizar
     * @param filme
     * @throws RepositorioException 
     */
    public void atualizar(Filme filme) throws RepositorioException{
        if(filme == null){
            throw new RepositorioException("Filme não encontrado.");
        }
        this.validarCamposObrigatorios(filme);
        filmeDao.atualizar(filme);
    }
    
    /**
     * Mpetodo retorna filme por codigo
     * @param codigo
     * @return
     * @throws RepositorioException 
     */
    public Filme buscaFilmePorCodigo(int codigo) throws RepositorioException{
        if(codigo == 0){
            throw new RepositorioException("Código não informado.");
        }
        Filme filme = filmeDao.procurarPorId(codigo);
        if(filme == null){
            throw new RepositorioException("Filme não encontrado.");
        }
        return filme;
    }
    
    /**
     * Metodo que retorna filme por nome
     * @param nome
     * @return
     * @throws RepositorioException 
     */
    public List<Filme> buscaFilmePorNome(String nome) throws RepositorioException{
        if(nome.length() == 0){
            throw new RepositorioException("Nome não informado");
        }
        return (filmeDao.BuscaPorNome(nome));
    }
    
    /**
     * Metodo que valida campos obrigatórios
     * @param f
     * @throws RepositorioException 
     */
    public void validarCamposObrigatorios(Filme f) throws RepositorioException{
        if(f.getCodigo()== 0){
            throw new RepositorioException("Código não informado");
        }
        
        if(f.getNome().length() == 0){
            throw new RepositorioException("Nome não informado");
        }
    }
    
    /**
     * Metodo que retorna se existe um filme com mesmo nome
     * @param nome
     * @throws RepositorioException 
     */
    public void validarNomeExistente(String nome) throws RepositorioException{
        if(!buscaFilmePorNome(nome).isEmpty()){
            throw new RepositorioException("Filme já cadastrado.");
        }
    }
    
    /**
     * Metodo retorna codigo
     * @return 
     */
    public int retornaCodigo(){
        int codigo = filmeDao.retornaUltimoCodigo();
        codigo++;
        return codigo;
    }
    
    /**
     * Metodo que retorna se existe o filme cadastrado
     * @param id
     * @return 
     */
    public boolean filmeExiste(int id){
        Filme filme = filmeDao.procurarPorId(id);
        return (filme != null);
    }

    
}
