/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 * Classe Seção
 * @author Julliano
 */
public class Secao {
    private int codigo;
    private int numSala;
    private Date horario;
    private int codFilme;
    
    
    /**
     * Método Construtor sem parâmetro
     */
    public Secao() {
    }

    /**
     * Método Contrutor
     * @param sala
     * @param horario
     * @param filme
     * @param numero 
     */
    public Secao(Integer codigo, int numSala, Date horario, int codFilme) {
        this.codigo = codigo;
        this.numSala = numSala;
        this.horario = horario;
        this.codFilme = codFilme;
    }

    /**
     * Método getCodigo
     * @return 
     */
    public Integer getCodigo() {
        return codigo;
    }

   /**
     * Método setNumero
     * @return 
     */
    public void setNumero(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Método getNumSala
     * @return 
     */
    public int getNumSala() {
        return numSala;
    }

    /**
     * Método SetNumSala
     * @return 
     */
    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    /**
     * Método getHorario
     * @return 
     */
    public Date getHorario() {
        return horario;
    }

    /**
     * Método setHorario
     * @return 
     */
    public void setHorario(Date horario) {
        this.horario = horario;
    }

    /**
     * Método getCodFilme
     * @return 
     */
    public int getCodFilme() {
        return codFilme;
    }

    /**
     * Método setCodFilme
     * @return 
     */
    public void setCodFilme(int codFilme) {
        this.codFilme = codFilme;
    }

    
    
    
    
}
