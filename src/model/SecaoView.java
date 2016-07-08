/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author mi
 */
public class SecaoView {
    private int codSecao;
    private String hora;
    private int numSala;
    private int assentos;
    private int codFilme;
    private String filme;

    public SecaoView() {
    }

    public SecaoView(int codSecao, String hora, int numSala, int assentos, int codFilme, String filme) {
        this.codSecao = codSecao;
        this.hora = hora;
        this.numSala = numSala;
        this.assentos = assentos;
        this.codFilme = codFilme;
        this.filme = filme;
    }

    public int getCodSecao() {
        return codSecao;
    }

    public void setCodSecao(int codSecao) {
        this.codSecao = codSecao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public int getAssentos() {
        return assentos;
    }

    public void setAssentos(int assentos) {
        this.assentos = assentos;
    }

    public int getCodFilme() {
        return codFilme;
    }

    public void setCodFilme(int codFilme) {
        this.codFilme = codFilme;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }
    
    
            
    
}
