/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

import dao.SalaDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Sala;

/**
 * classe que contém os métodos da interface SalaDao
 * @author mi
 */
public class SalaDaoBd extends DaoBd<Sala> implements SalaDao{

    /**
     * Método salvar
     * @param sala 
     */
    @Override
    public void salvar(Sala sala) {
        try {
            String sql = "insert into sala (numero,assento) values (? , ?)";
            
            conectar(sql);
            comando.setInt(1, sala.getNumero());
            comando.setInt(2, sala.getAssentos());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao incluir sala.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
    }

    /**
     * Método deletar
     * @param sala 
     */
    @Override
    public void deletar(Sala sala) {
        try {
            String sql = "delete from sala where numero = ?";
            
            conectar(sql);
            comando.setInt(1, sala.getNumero());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao deletar sala.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
    }

    /**
     * Método atualizar
     * @param sala 
     */
    @Override
    public void atualizar(Sala sala) {
        try {
            String sql = "update sala set assento = ? where numero = ?";
            
            conectar(sql);
            comando.setInt(1, sala.getAssentos());
            comando.setInt(2, sala.getNumero());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar sala.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Método listar
     * @return 
     */
    @Override
    public List<Sala> listar() {
        List<Sala> listaSalas = new ArrayList<>();
        try {
            String sql = "select * from sala";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int numero = resultado.getInt("numero");
                int assento = resultado.getInt("assento");
                Sala sala = new Sala(numero, assento);
                listaSalas.add(sala);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar salas");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return listaSalas;
    }

    /**
     * Método procurar por id
     * @param numero
     * @return 
     */
    @Override
    public Sala procurarPorId(int numero) {
        Sala sala = new Sala();
        String sql = "select * from sala where numero=?"; 
        try {
            conectar(sql);
            comando.setInt(1, numero);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                int assento = resultado.getInt("assento");
                sala.setNumero(numero);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar sala.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return sala;
    }

    /**
     * Método de controle para retornar o ultimo numero inserido
     * @return 
     */
    @Override
    public int retornaUltimoNumero() {
        int numero = 0;
        try {
            String sql = "select * from sala order by numero desc limit 1";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                numero = resultado.getInt("numero");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar numero.");
        } finally {
            fecharConexao();
        }
        return numero;
    }
    
}
