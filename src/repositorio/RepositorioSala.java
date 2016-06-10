/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import dao.SalaDao;
import dao.imp_BD.SalaDaoBd;
import java.util.ArrayList;
import java.util.List;
import model.Sala;

/**
 * Classe RepositorioSala
 * @author Julliano
 */
public class RepositorioSala {
    private SalaDao salaDao;
    
    /**
     * Método Construtor
     */
    public RepositorioSala(){
        salaDao = new SalaDaoBd();
    }
    
    /**
     * Metodo listar
     * @return 
     */
    public List<Sala> listar(){
        return (salaDao.listar());
    }
    
    /**
     * Metodo que biusca sala por nome
     * @param numero
     * @return 
     */
    public Sala buscaSalaPorNumero(int numero){
        Sala sala = salaDao.procurarPorId(numero);
        return sala;
    }
    
    /**
     * Metodo que inclui a sala
     * @param sala
     * @throws RepositorioException 
     */
    public void salvar(Sala sala) throws RepositorioException{
        this.validarCamposObrigatorios(sala);
        salaDao.salvar(sala);
    }
    
    /**
     * Metodo deletar
     * @param sala
     * @throws RepositorioException 
     */
    public void deletar(Sala sala) throws RepositorioException{
        if(sala == null){
            throw new RepositorioException("Sala não encontrada.");
        }
        salaDao.deletar(sala);
    }
    
    /**
     * Metodo atualizar
     * @param sala
     * @throws RepositorioException 
     */
    public void atualizar(Sala sala) throws RepositorioException{
        if(sala == null){
            throw new RepositorioException("Sala não encontrada.");
        }
        salaDao.atualizar(sala);
    }
    
    /**
     * Metodo que retorna o numero
     * @return 
     */
    public int retornaNumero(){
        int numero = salaDao.retornaUltimoNumero();
        numero++;
        return numero;
    }
    
    /**
     * Metodo  valida campos obrigatórios
     * @param sala
     * @throws RepositorioException 
     */
    public void validarCamposObrigatorios(Sala sala) throws RepositorioException{
        if(sala.getAssentos() == 0){
            throw new RepositorioException("Assentos não informado");
        }
    }
    
    /**
     * Metodo que retorna se a sala já foi cadastrada
     * @param numero
     * @return 
     */
    public boolean salaExiste(int numero){
        Sala sala = salaDao.procurarPorId(numero);
        return (sala != null);
    }
}
