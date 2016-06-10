/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Filme;

/**
 * Interface específica para a classe Filme
 * @author mi
 */
public interface FilmeDao extends Dao<Filme>{
    public List<Filme> BuscaPorNome(String nome);
    public int retornaUltimoCodigo();
    
}
