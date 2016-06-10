/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Secao;
import model.Venda;

/**
 * Interface específica para a classe Venda
 * @author mi
 */
public interface VendaDao extends Dao<Venda>{
    public int retornaAssentos(Secao secao);
    public int retornaAssentosVenda(Secao secao);
    
}
