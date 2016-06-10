/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 * Classe ManiMenu
 * @author Julliano
 */
public class MainMenu {
    

    public static final int OP_FILME = 1;
    public static final int OP_SALA = 2;
    public static final int OP_SECAO = 3;
    public static final int OP_VENDA = 4;
    public static final int OP_RELATORIOS = 5;
    public static final int OP_SAIR = 0;

    /**
     * Opções principais
     * @return 
     */
    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Filme\n"
                + "2- Menu Sala\n"
                + "3- Menu Seção\n"
                + "4- Menu Venda\n"
                + "5- Menu Relatórios\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}


