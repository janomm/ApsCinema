/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import dao.SalaDao;
import dao.SecaoDao;
import dao.imp_BD.SecaoDaoBd;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Secao;
import util.DateUtil;

/**
 * Classe RepositorioSecao
 * @author Julliano
 */
public class RepositorioSecao {
    private SecaoDao secaoDao; 
    
    /**
     * Método Construtor
     */
    public RepositorioSecao(){
        secaoDao = new SecaoDaoBd();
    }

    /**
     * Metodo listar
     * @return 
     */
    public List<Secao> listar(){
        return (secaoDao.listar());
    }
    
    /**
     * Metodo busca seção por filme
     * @param filme
     * @return 
     */
    public List<Secao> buscaSecaoPorFilme(Filme filme){
        return(secaoDao.consultaPorFilme(filme));
    }

    /**
     * Metodo busca seção por sala
     * @param sala
     * @return 
     */
    public List<Secao> buscaSecaoPorSala(Sala sala){
        return(secaoDao.consultaPorSala(sala));
    }
    
    /**
     * Metodo busca seção por horario
     * @param horario
     * @return 
     */
    public List<Secao> buscaSecaoPorHorario(Date horario){
        return(secaoDao.consultaPorHora(horario));
    }
    
    /**
     * Metodo busca seção por numero
     * @param numero
     * @return 
     */
    public Secao buscaPorNumero(int numero){
        return (secaoDao.procurarPorId(numero));
    }

    /**
     * Metodo salvar
     * @param secao
     * @throws RepositorioException 
     */
    public void salvar(Secao secao) throws RepositorioException{
        this.validarCamposObrigatorios(secao);
        secaoDao.salvar(secao);
    }
    
    /**
     * Metodo deletar
     * @param secao
     * @throws RepositorioException 
     */
    public void deletar(Secao secao) throws RepositorioException{
        if(secao == null){
            throw new RepositorioException("Seção não encontrada.");
        }
        secaoDao.deletar(secao);
    }
    
    /**
     * Metodo atualizar
     * @param secao
     * @throws RepositorioException 
     */
    public void atualizar(Secao secao) throws RepositorioException{
        if(secao == null){
            throw new RepositorioException("Seção não encontrada.");
        }
        secaoDao.atualizar(secao);
    }
    
    /**
     * Metodo retora ultimo código
     * @return 
     */
    public int retornaCodigo(){
        int codigo = secaoDao.retornaUltimoCodigo();
        codigo++;
        return codigo;
    }
    
    /**
     * Metodo valida campos obrigatórios
     * @param secao
     * @throws RepositorioException 
     */
    public void validarCamposObrigatorios(Secao secao) throws RepositorioException{
        if(secao.getNumSala() == 0){
            throw new RepositorioException("Sala não encontrada.");
        }

        if(secao.getCodFilme() == 0){
            throw new RepositorioException("Filme não encontrado.");
        }
    }
    
    /**
     * Metodo que verifica se a seção já foi incluida
     * @param codigo
     * @return 
     */
    public boolean secaoExiste(int codigo){
        Secao secao = secaoDao.procurarPorId(codigo);
        return (secao != null);
    }
    
    
}
