package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechaUtils {

	/**
	 * 
	 * @param dia 1 al 31, depende del mes
	 * @param mes 1 al 12
	 * @param anio 1970 en adelante
	 * @return Un objeto del tipo java.util.Date representando la fecha ingresada
	 */
	public static Date getFecha(Integer dia, Integer mes, Integer anio){
		Calendar c = Calendar.getInstance();		
		c.set(anio, mes-1, dia);
		return c.getTime();
	}
	
	public static Date getManana(){
		Calendar c = Calendar.getInstance();		
		c.setTime(new Date(System.currentTimeMillis()));
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
		return c.getTime();
	}
	
	public static Date getManana(Date fecha){
		Calendar c = Calendar.getInstance();		
		c.setTime(fecha);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
		return c.getTime();
	}
	
	public static Date getFechaHoraCero(Date fecha){
		Calendar c = Calendar.getInstance();		
		c.setTime(fecha);
		
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int ano = c.get(Calendar.YEAR);
		
		c.set(ano, mes-1, dia, 0, 0, 0);
		
		return c.getTime();
	}
	
	/**
	 * 
	 * @param fecha
	 * @return Retorna La misma fecha pero solo con year, month, day
	 */
	public static Date getFecha(Date fecha){
		Calendar c = Calendar.getInstance();		
		c.setTime(fecha);
		return FechaUtils.getFecha(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
	}
	
	public static String getFechaPattern(Date date,String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
}
