/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Sala;

/**
 * Interface específica para a classe Sala
 * @author mi
 */
public interface SalaDao extends Dao<Sala>{
    public int retornaUltimoNumero();
}
