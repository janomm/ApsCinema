/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mi
 */
public class VendaView extends SecaoView{
    private Integer quantidade;

    public VendaView(Integer quantidade, int codSecao, String hora, int numSala, int assentos, int codFilme, String filme) {
        super(codSecao, hora, numSala, assentos, codFilme, filme);
        this.quantidade = quantidade;
    }

    public VendaView() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
