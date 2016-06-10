/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe Sala
 * @author Julliano
 */
public class Sala {
    private Integer numero;
    private Integer assentos;

    /**
     * Método Contrutor sem parâmetros
     */
    public Sala() {
    }

    /**
     * Método Contrutor
     * @param numero
     * @param assentos 
     */
    public Sala(Integer numero, Integer assentos) {
        this.numero = numero;
        this.assentos = assentos;
    }

    /**
     * Método getNumero
     * @return 
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Método setNumero
     * @param numero 
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Método getAssentos
     * @return 
     */
    public Integer getAssentos() {
        return assentos;
    }

    /**
     * Método setAssentos
     * @param assentos 
     */
    public void setAssentos(Integer assentos) {
        this.assentos = assentos;
    }
    
    
    
    
}
