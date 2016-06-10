/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import dao.VendaDao;
import dao.imp_BD.VendaDaoBd;
import java.util.ArrayList;
import java.util.List;
import model.Secao;
import model.Venda;

/**
 * Classe RepositorioVenda
 * @author Julliano
 */
public class RepositorioVenda {
    private VendaDao vendaDao;

    /**
     * Método Contrutor
     */
    public RepositorioVenda() {
        vendaDao = new VendaDaoBd();
    }
    
    /**
     * Metodo listar
     * @return 
     */
    public List<Venda> listar(){
        return (vendaDao.listar());
    }
    
    /**
     * Metodo salvar
     * @param venda
     * @throws RepositorioException 
     */
    public void salvar(Venda venda) throws RepositorioException{
        this.validarCamposObrigatorios(venda);
        vendaDao.salvar(venda);
    }
    
    /**
     * Metodo deletar
     * @param venda
     * @throws RepositorioException 
     */
    public void deletar(Venda venda) throws RepositorioException{
        if(venda == null){
            throw new RepositorioException("Venda não encontrada.");
        }
        vendaDao.deletar(venda);
    }
    
    /**
     * Metodo atualizar
     * @param venda
     * @throws RepositorioException 
     */
    public void atualizar(Venda venda) throws RepositorioException{
        if(venda == null){
            throw new RepositorioException("Venda não encontrada.");
        }
        vendaDao.atualizar(venda);
    }

    /**
     * Metodo valida campos obrigatórios
     * @param venda
     * @throws RepositorioException 
     */
    public void validarCamposObrigatorios(Venda venda) throws RepositorioException{
        if(venda.getCodSecao() == 0){
            throw new RepositorioException("Seção não informada na venda.");
        }
        if(venda.getQuantidade() <= 0){
            throw new RepositorioException("Quantidade informada na venda é inválida.");
        }
    }
    
    /**
     * retorna se existe uma venda com a seção informada
     * @param numero
     * @return 
     */
    public boolean vendaExiste(Secao secao){
        Venda venda = vendaDao.procurarPorId(secao.getCodigo());
        return (venda != null);
    }
    
    /**
     * Metodo retorna quantidade de assentos na seção
     * @param secao
     * @return
     * @throws RepositorioException 
     */
    public int retornaTotalAssentosSecao(Secao secao) throws RepositorioException{
        int quantidade = vendaDao.retornaAssentos(secao);
        if(quantidade == 0){
            throw new RepositorioException("Erro ao retornar a quantidade de assentos");
        }
        return quantidade;
    }
    
    /**
     * Metodo retorna a quantoidade de assentos vendidos
     * @param secao
     * @return
     * @throws RepositorioException 
     */
    public int retornaAssentosVenda(Secao secao) throws RepositorioException{
        int quantidade = 0;
        if(quantidade < 0){
            throw new RepositorioException("Erro ao retornar a quantidade de assentos.");
        }
        quantidade = vendaDao.retornaAssentosVenda(secao);
        return quantidade;
    }
}
