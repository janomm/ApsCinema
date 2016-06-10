/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

import dao.SecaoDao;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Secao;
import util.DateUtil;

/**
 * classe que contém os métodos da interface SecaoDao
 * @author mi
 */
public class SecaoDaoBd extends DaoBd<Secao> implements SecaoDao{

    @Override
    public void salvar(Secao secao) {
        try {
            String sql = "insert into secao (codigo,numsala,horario,codfilme) values (?,?,?,?)";
            
            conectar(sql);
            comando.setInt(1, secao.getCodigo());
            comando.setInt(2, secao.getNumSala());
            comando.setString(3, DateUtil.hourToString(secao.getHorario()));
            comando.setInt(4, secao.getCodFilme());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao incluir seção.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
    }

    /**
     * Método salvar
     * @param secao 
     */
    @Override
    public void deletar(Secao secao) {
        try {
            String sql = "delete from secao where codigo = ?";
            
            conectar(sql);
            comando.setInt(1, secao.getCodigo());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Problema ao deletar seção.");
            throw new RuntimeException(e);
        } finally{
            fecharConexao();
        }
    }

    /**
     * Método atualizar
     * @param secao 
     */
    @Override
    public void atualizar(Secao secao) {
        try {
            String sql = "update secao set numsala=?, horario=?, codfilme=? where codigo=?";
            
            conectar(sql);
            comando.setInt(1, secao.getNumSala());
            comando.setString(2, DateUtil.hourToString(secao.getHorario()));
            comando.setInt(3, secao.getCodFilme());
            comando.setInt(4, secao.getCodigo());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Problema ao alterar a seção.");
            throw new RuntimeException(e);
        } finally{
            fecharConexao();
        }
    }

    /**
     * Método listar
     * @return 
     */
    @Override
    public List<Secao> listar() {
        List<Secao> listaSecoes = new ArrayList<>();
        String sql = "SELECT * FROM secao";
        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                int numsala = resultado.getInt("numsala");
                Date horario = DateUtil.stringToHour(resultado.getString("horario"));
                int codfilme = resultado.getInt("codfilme");
                
                Secao secao = new Secao(codigo, numsala, horario, codfilme);
                listaSecoes.add(secao);
                
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar seção.");
            throw new RuntimeException(e);
        }
        finally{
            fecharConexao();
        }
        
        return listaSecoes;
    }

    /**
     * Método procurar por id
     * @param id
     * @return 
     */
    @Override
    public Secao procurarPorId(int id) {
        Secao secao = null;
        String sql = "SELECT * FROM secao WHERE codigo = ?"; 
        try {
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                int numsala = resultado.getInt("numsala");
                Date horario = DateUtil.stringToHour(resultado.getString("horario"));
                int codfilme = resultado.getInt("codfilme");

                Secao s = new Secao(codigo, numsala, horario, codfilme);
                secao = s;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar seção.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return secao;
    }

    /**
     * Método consulta por filme
     * @param filme
     * @return 
     */
    @Override
    public List<Secao> consultaPorFilme(Filme filme) {
        List<Secao> listaSecoes = new ArrayList<>();
        String sql = "SELECT * FROM secao WHERE numsala=?";
        try {
            conectar(sql);
            comando.setInt(1, filme.getCodigo());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                int numsala = resultado.getInt("numsala");
                Date horario = DateUtil.stringToHour(resultado.getString("horario"));

                Secao secao = new Secao(codigo, numsala, horario, filme.getCodigo());
                listaSecoes.add(secao);
                
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar seção.");
            throw new RuntimeException(e);
        }
        finally{
            fecharConexao();
        }
        
        return listaSecoes;
        
    }

    /**
     * Método Consulta por sala
     * @param sala
     * @return 
     */
    @Override
    public List<Secao> consultaPorSala(Sala sala) {
        List<Secao> listaSecoes = new ArrayList<>();
        String sql = "SELECT * FROM secao WHERE numsala=?";
        try {
            conectar(sql);
            comando.setInt(1, sala.getNumero());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                int codfilme = resultado.getInt("codfilme");
                Date horario = DateUtil.stringToHour(resultado.getString("horario"));

                Secao secao = new Secao(codigo, sala.getNumero(), horario, codfilme);
                listaSecoes.add(secao);
                
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar seção.");
            throw new RuntimeException(e);
        }
        finally{
            fecharConexao();
        }
        
        return listaSecoes;
    }

    /**
     * Método consulta por hora
     * @param hora
     * @return 
     */
    @Override
    public List<Secao> consultaPorHora(Date hora) {
        List<Secao> listaSecoes = new ArrayList<>();
        String sql = "SELECT * FROM secao WHERE horario=?";
        try {
            conectar(sql);
            comando.setString(1, DateUtil.hourToString(hora));
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                int codfilme = resultado.getInt("codfilme");
                int numsala = resultado.getInt("horario");

                Secao secao = new Secao(codigo, numsala, hora, codfilme);
                listaSecoes.add(secao);
                
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar seção.");
            throw new RuntimeException(e);
        }
        finally{
            fecharConexao();
        }
        return listaSecoes;
    }

    /**
     * Método que retorna o ultimo codigo inserido
     * @return 
     */
    @Override
    public int retornaUltimoCodigo() {
        int codigo = 0;
        try {
            String sql = "select * from secao order by codigo desc limit 1";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                codigo = resultado.getInt("codigo");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar codigo.");
        } finally {
            fecharConexao();
        }
        return codigo;
    }
    
}
