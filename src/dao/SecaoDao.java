/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Secao;

/**
 * Interface específica para a classe Secao
 * @author mi
 */
public interface SecaoDao extends Dao<Secao>{
    public List<Secao> consultaPorFilme(Filme filme);
    public List<Secao> consultaPorSala(Sala sala);
    public List<Secao> consultaPorHora(Date hora);
    public int retornaUltimoCodigo();
}
