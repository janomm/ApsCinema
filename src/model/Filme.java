/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe Filme
 * @author Julliano
 */
public class Filme {
    private Integer codigo;
    private String nome;
    private String genero;
    private String sinopse;

    /**
     * Método Contrutor sem parâmetro
     */
    public Filme() {
    }

    /**
     * Método Contrutor
     * @param codigo
     * @param nome
     * @param genero
     * @param sinopse 
     */
    public Filme(Integer codigo, String nome, String genero, String sinopse) {
        this.codigo = codigo;
        this.nome = nome;
        this.genero = genero;
        this.sinopse = sinopse;
    }

    /**
     * Método getCodigo
     * @return 
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Método setCodigo
     * @param codigo 
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Método getNome
     * @return 
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método steNome
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método getGenero
     * @return 
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método setGenero
     * @param genero 
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Método getSinopse
     * @return 
     */
    public String getSinopse() {
        return sinopse;
    }

    /**
     * Método setSinopse
     * @param sinopse 
     */
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    
    /**
     * Imprime dados do Filme
     */
    public void imprimeFilme(){
        System.out.println("Código: " + this.codigo
                           + "\nNome: " + this.nome 
                           + "\nGênero: " + this.genero
                           + "\nSinopse: " + this.sinopse);
    }
    
}
