/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 * Interface comum para todas as classes
 * @author mi
 * @param <T> 
 */
public interface Dao<T> {
    public void salvar(T model);
    public void deletar(T model);
    public void atualizar(T model);
    public List<T> listar();
    public T procurarPorId(int id);
    
}
