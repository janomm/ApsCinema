/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 * Classe SalaMenu
 * @author Julliano
 */
public class SalaMenu {
    public static final int OP_NOVO = 1;
    public static final int OP_REMOVER = 2;
    public static final int OP_EDITAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_VOLTAR = 0;
    
    /**
     * Opções principais
     * @return 
     */
    public static String getOpcoes(){
        return("\n--------------------------------------\n"
                + "1- Incluir Sala\n"
                + "2- Remover Sala\n"
                + "3- Editar Sala\n"
                + "4- Listar Salas\n"
                + "0- Voltar"
                + "\n--------------------------------------");
        
    }
}
