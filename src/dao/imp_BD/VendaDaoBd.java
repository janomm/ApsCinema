/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

import dao.VendaDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Secao;
import model.Venda;

/**
 * classe que contém os métodos da interface VendaDao
 * @author mi
 */
public class VendaDaoBd extends DaoBd<Venda> implements VendaDao{

    /**
     * método salvar
     * @param venda 
     */
    @Override
    public void salvar(Venda venda) {
        try {
            String sql = "insert into venda (codsecao,quantidade) values (? , ?)";
            
            conectar(sql);
            comando.setInt(1, venda.getCodSecao());
            comando.setInt(2, venda.getQuantidade());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao incluir venda.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
    }

    /**
     * método deletar
     * @param venda 
     */
    @Override
    public void deletar(Venda venda) {
        try {
            String sql = "delete from venda where codsecao = ?";
            
            conectar(sql);
            comando.setInt(1, venda.getCodSecao());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao deletar venda.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
    }

    /**
     * método atualizar
     * @param venda 
     */
    @Override
    public void atualizar(Venda venda) {
        try {
            String sql = "update venda set quantidade = ? where codsecao = ?";
            
            conectar(sql);
            comando.setInt(1, venda.getQuantidade());
            comando.setInt(2, venda.getCodSecao());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar venda.");
            throw new RuntimeException(e);
        }
    }

    /**
     * método listar
     * @return 
     */
    @Override
    public List<Venda> listar() {
        List<Venda> listavenda = new ArrayList<>();
        String sql = "select * from venda";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {                
                Venda venda = new Venda();
                venda.setCodSecao(resultado.getInt("codsecao"));
                venda.setQuantidade(resultado.getInt("quantidade"));
                listavenda.add(venda);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar vendas.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return listavenda;
    }

    /**
     * método procurar por id
     * @param codSecao
     * @return 
     */
    @Override
    public Venda procurarPorId(int codSecao) {
        Venda venda = null;
        String sql = "select * from venda where codsecao=?"; 
        try {
            conectar(sql);
            comando.setInt(1, codSecao);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                int quantidade = resultado.getInt("quantidade");
                venda = new Venda(codSecao, quantidade);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar sala.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return venda;
    }

    /**
     * método retorna a quantidade de assentos
     * @param secao
     * @return 
     */
    @Override
    public int retornaAssentos(Secao secao) {
        int assentos = 0;
        String sql = "select * from sala where numero = ?";
        try {
            conectar(sql);
            comando.setInt(1, secao.getNumSala());
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                assentos = resultado.getInt("assento");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar assentos.");
        } finally {
            fecharConexao();
        }
        return assentos;
    }

    /**
     * método que retorna a quantidade de assentos vendidos
     * @param secao
     * @return 
     */
    @Override
    public int retornaAssentosVenda(Secao secao) {
        int assentos = 0;
        String sql = "select * from venda where codsecao=?";
        try {
            conectar(sql);
            comando.setInt(1, secao.getCodigo());
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                assentos = resultado.getInt("quantidade");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar assentos.");
        } finally {
            fecharConexao();
        }
        return assentos;
    }
    
}
