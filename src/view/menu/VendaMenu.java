/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 * Classe VendaMenu
 * @author Julliano
 */
public class VendaMenu {
    public static final int OP_VENDA = 1;
    public static final int OP_LISTAVENDA = 2;
    public static final int OP_VOLTAR = 0;
    
    /**
     * Opções principais
     * @return 
     */
    public static String getOpcoes(){
        return("\n--------------------------------------\n"
                + "1- Incluir Venda\n"
                + "2- Lista Venda\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
