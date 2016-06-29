package com.testecommaven.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author lhries
 */
public class DateUtil {
    
    public static Date stringToDate(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy").parse(data));
    }

    public static Date stringToDateHour(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
    }
    
    public static String dateToString(Date data){
        return(new SimpleDateFormat("dd/MM/yyyy").format(data));
    }
    
    public static String dateToStringFormatBD(Date data){
        return(new SimpleDateFormat("yyyy/MM/dd").format(data));
    }
    
    public static LocalDate dateToLocalDate(Date date){    	
    	return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String dateHourToString(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String dataString = formatador.format(data);
        return(dataString);
    }
    
    public static String getHourFromDateToString(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        String dataString = formatador.format(data);
        return(dataString);
    }
    
    public static boolean verificaData(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4}"));
    }
    public static boolean verificaDataHora(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}"));
    }
}
