/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para efetuar a coneção com o banco Postgres
 * @author mi
 */
public class BDUtil {
    private final static String HOST = "localhost";
    private final static String PORT = "5432";
    private final static String BD = "postgres";
    private final static String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + BD;
    private final static String USUARIO = "postgres";
    private final static String SENHA = "postgres";
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e){
            System.err.println("Classe do driver não encontrada.");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("Erro de conexão com banco de dados.");
            throw new RuntimeException(e);
        }
        return conexao;
    }
    
}
