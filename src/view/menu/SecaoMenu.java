/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 * Classe SecaoMenu
 * @author Julliano
 */
public class SecaoMenu {
    public static final int OP_NOVO = 1;
    public static final int OP_REMOVER = 2;
    public static final int OP_EDITAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_VOLTAR = 0;
    
    public static final int OP_ALTERAFILME = 1;
    public static final int OP_ALTERASALA = 2;
    public static final int OP_ALTERAHORA = 3;
    
    /**
     * Opções principais
     * @return 
     */
    public static String getOpcoes(){
        return("\n--------------------------------------\n"
                + "1- Incluir Seção\n"
                + "2- Remover Seção\n"
                + "3- Editar Seção\n"
                + "4- Listar Seções\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }

    /**
     * Opções de Alteração
     * @return 
     */
    public static String getOpcoesAlteracao(){
        return("\n--------------------------------------\n"
                + "1- Altera Filme\n"
                + "2- Altera Sala\n"
                + "3- Altera Hora\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
    
    
    
}
