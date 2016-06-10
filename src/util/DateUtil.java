package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe DataUtil auxiliar para funções de validação de data e hora
 * @author lhries
 */
public class DateUtil {
    
    /**
     * Faz a conversão de uma String para um Date
     * @param data
     * @return
     * @throws ParseException 
     */
    public static Date stringToDate(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy").parse(data));
    }

    /**
     * Faz a conversão de um String pra um Date com Hora
     * @param data
     * @return
     * @throws ParseException 
     */
    public static Date stringToDateHour(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
    }
    
    /**
     * Faz a conversão de um Date para um String
     * @param data
     * @return 
     */
    public static String dateToString(Date data){
        return(new SimpleDateFormat("dd/MM/yyyy").format(data));
    }

    /**
     * Faz a conversão de um Date com Hora para um String
     * @param data
     * @return 
     */
    public static String dateHourToString(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataString = formatador.format(data);
        return(dataString);
    }
    
    /**
     * Faz a conversão de um String para uma Hora
     * @param hora
     * @return
     * @throws ParseException 
     */
    public static Date stringToHour(String hora) throws ParseException{
        return(new SimpleDateFormat("HH:mm").parse(hora));
    }
    
    /**
     * Faz a conversão de uma Hora para um String
     * @param data
     * @return 
     */
    public static String hourToString(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        String dataString = formatador.format(data);
        return(dataString);
    }
    
    /**
     * Verifica uma data
     * @param data
     * @return 
     */
    public static boolean verificaData(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4}"));
    }
    
    /**
     * Verifica uma data
     * @param data
     * @return 
     */
    public static boolean verificaHora(String hora)
    {
        try {
            Date h = new SimpleDateFormat("HH:mm").parse(hora);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Verifica um Date com Hora
     * @param data
     * @return 
     */
    public static boolean verificaDataHora(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}"));
    }
}
