package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * Classe Console auxiliar para funções de leitura de variáveis
 * @author 631510361
 */
public class Console {
	
    /**
     * Leitura de String
     * @param out
     * @return 
     */
    public static String scanString(Object out)
    {
            System.out.print(out);
            Scanner scanner = new Scanner (System.in);
            return(scanner.nextLine());
    }

    /**
     * Leitura de um Integer
     * @param out
     * @return 
     */
    public static int scanInt(Object out)
    {
            System.out.print(out);
            Scanner scanner = new Scanner (System.in);
            return(scanner.nextInt());		
    }

    /**
     * Leitura de um Double
     * @param out
     * @return 
     */
    public static double scanDouble(Object out)
    {
            System.out.print(out);
            Scanner scanner = new Scanner (System.in);
            return(scanner.nextDouble());		
    }

    /**
     * Leitura de um Float
     * @param out
     * @return 
     */
    public static float scanFloat(Object out)
    {
            System.out.print(out);
            Scanner scanner = new Scanner (System.in);
            return(scanner.nextFloat());		
    }

    /**
     * Leitura de um Boolean
     * @param out
     * @return 
     */
    public static boolean scanBoolean(Object out)
    {
            System.out.print(out);
            Scanner scanner = new Scanner (System.in);
            return(scanner.nextBoolean());		
    }

    /**
     * Leitura de um Char
     * @param out
     * @return 
     */
    public static char scanChar(Object out)
    {
            System.out.print(out);
            Scanner scanner = new Scanner (System.in);
            return(scanner.next().charAt(0));				
    }

}
