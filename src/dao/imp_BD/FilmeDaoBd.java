/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp_BD;

import dao.FilmeDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Filme;

/**
 * classe que contém os métodos da interface FilmeDao
 * @author mi
 */
public class FilmeDaoBd extends DaoBd<Filme> implements FilmeDao{

    /**
     * método salvar
     * @param filme 
     */
    @Override
    public void salvar(Filme filme) {
        try {
            String sql = "insert into filme (codigo,nome,genero,sinopse) values (?,?,?,?)";
            
            conectar(sql);
            comando.setInt(1, filme.getCodigo());
            comando.setString(2, filme.getNome().toUpperCase());
            comando.setString(3, filme.getGenero());
            comando.setString(4, filme.getSinopse());
            comando.executeUpdate();

        } catch (Exception e) {
            System.err.println("Problema ao incluir filme.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
    }

    /**
     * método deletar
     * @param filme 
     */
    @Override
    public void deletar(Filme filme) {
        try {
            String sql = "delete from filme where codigo = ?";
            
            conectar(sql);
            comando.setInt(1, filme.getCodigo());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Problema ao deletar filme.");
            throw new RuntimeException(e);
        } finally{
            fecharConexao();
        }
    }

    /**
     * método atualizar
     * @param filme 
     */
    @Override
    public void atualizar(Filme filme) {
        try {
            String sql = "update filme set nome=?, genero=?, sinopse=? where codigo=?";
            
            conectar(sql);
            comando.setString(1, filme.getNome().toUpperCase());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopse());
            comando.setInt(4, filme.getCodigo());
            comando.executeUpdate();
        } catch (Exception e) {
            System.err.println("Problema ao alterar o filme.");
            throw new RuntimeException(e);
        } finally{
            fecharConexao();
        }
    }

    /**
     * método listar
     * @return 
     */
    @Override
    public List<Filme> listar() {
        List<Filme> listaFilmes = new ArrayList<>();

        String sql = "SELECT * FROM filme";
        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                
                Filme filme = new Filme(codigo, nome, genero, sinopse);
                listaFilmes.add(filme);
                
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar filmes.");
            throw new RuntimeException(e);
        }
        finally{
            fecharConexao();
        }
        
        return listaFilmes;
    }

    /**
     * método procurar por id
     * @param id
     * @return 
     */
    @Override
    public Filme procurarPorId(int id) {
        Filme filme = new Filme();
        String sql = "select * from filme where codigo=?"; 
        try {
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                String nome = resultado.getString("nome");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                filme.setCodigo(id);
                filme.setNome(nome);
                filme.setGenero(genero);
                filme.setSinopse(sinopse);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar filme.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return filme;
    }

    /**
     * método Buscar por nome
     * @param nome
     * @return 
     */
    @Override
    public List<Filme> BuscaPorNome(String nome) {
        List<Filme> listafilme = new ArrayList();
        Filme filme = null;
        String sql = "SELECT * FROM filme WHERE nome = ?";
        try {
            conectar(sql);
            comando.setString(1, nome.toUpperCase());
            
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                int id = resultado.getInt("codigo");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");
                filme = new Filme(id, nome, genero, sinopse);
                listafilme.add(filme);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar filme por nome.");
            throw new RuntimeException(e);
        }
        finally{
            fecharConexao();
        }
        return listafilme;
        
    }

    /**
     * método para retornar po ultimo codigo
     * @return 
     */
    @Override
    public int retornaUltimoCodigo(){
        int codigo = 0;
        String sql = "select * from filme order by codigo desc limit 1";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()) {
                codigo = resultado.getInt("codigo");
                
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar ultimo codigo.");
            throw new RuntimeException(e);
        } finally {
            fecharConexao();
        }
        return codigo;
    }
}
