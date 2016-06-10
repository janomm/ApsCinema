/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

/**
 * Classe de exceções dos repositorios
 * @author mi
 */
public class RepositorioException extends Exception{
    public RepositorioException (String s){
        super(s);
    }
    
    /**
     * RepositorioException
     * @return 
     */
    public RepositorioException(String s, Throwable throwable){
        super(s,throwable);
    }

    /**
     * RepositorioException
     * @return 
     */
    public RepositorioException(Throwable throwable){
        super(throwable);
    }
    
    
}
