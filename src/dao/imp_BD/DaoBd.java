/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

import dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe abtrata Dao para todas as tabelas
 * @author mi
 */
public abstract class DaoBd<T> implements Dao<T>{
    protected Connection conexao;
    protected PreparedStatement comando;
    
    /**
     * Conecta ao banco d dados.
     * @param sql
     * @return
     * @throws SQLException 
     */
    public Connection conectar(String sql) throws SQLException{
        conexao = BDUtil.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }
    
    /**
     * Conecta ao banco de dados obtendo o id da conexão
     * @param sql
     * @throws SQLException 
     */
    public void conectarObtendoId(String sql) throws SQLException{
        conexao = BDUtil.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * fechar a conexão com o banco de dados
     */
    public void fecharConexao(){
        try {
            if(comando != null){
                conexao.close();
            }
            
            if(conexao != null){
                conexao.close();
            }
        } catch (Exception e) {
            System.err.println("Erro ao encerrar a conexão.");
            throw new BDException(e);
        }
    }
}
