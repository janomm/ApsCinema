/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

/**
 * Classe de tratamento de exceções
 * @author mi
 */
public class BDException extends RuntimeException{
    public BDException(String s){
        super(s);
    }
    
    public BDException(String s, Throwable throwable){
        super(s, throwable);
    }
    
    public BDException(Throwable throwable){
        super(throwable);
    }    
    
}    
