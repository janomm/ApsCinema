/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe Venda
 * @author Julliano
 */
public class Venda {
    private Integer codSecao;
    private Integer quantidade;

    /**
     * Método Construtor sem parâmetro
     */
    public Venda() {
    }

    /**
     * Método Construtor
     * @param secao
     * @param quantidade 
     */
    public Venda(Integer codSecao, Integer quantidade) {
        this.codSecao = codSecao;
        this.quantidade = quantidade;        
    }

    /**
     * Método getCodSecao
     * @return 
     */
    public Integer getCodSecao() {
        return codSecao;
    }

    /**
     * Método setCodSecao
     * @return 
     */
    public void setCodSecao(Integer codSecao) {
        this.codSecao = codSecao;
    }

    /**
     * Método getQuantidade
     * @return 
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Método setQuantidade
     * @return 
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


}
