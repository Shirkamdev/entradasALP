package decodificar;

import decodificar.exceptions.CodFormatErrorDecodificacionException;
import decodificar.exceptions.FormatErrorDecodificacionException;

/**
 * Clase encargada de Decodificar los codigos de la entrada
 * @author jaquer
 * @version 1.0.0
 */

public class Decodificacion 
{
	/**
	 * Valor del primo que multiplica
	 */
	private static final int R = 97453;
	/**
	 * Valor del primo que suma
	 */
	private static final int S = 85361;


	/**
	 * Valor del caracter de inicio,medio y fin
	 */
	private static final char InicioMedioFinal ='#';
	/**
	 * valor del primer carcter
	 */
	private static final char PrimeraLetra = 'A';
	/**
	 * valor del segundo carcter
	 */
	private static final char SegundaLetra = 'L';
	/**
	 * valor del tercer carcter
	 */
	private static final char TerceraLetra = 'P';


	/**
	 * Valor del codigo pasado
	 */
	private String codigo;


	/**
	 * Crea la clase decodifacion con el string pasado
	 * @param s El valor del codigo a decodificar
	 */
	public Decodificacion(String s)
	{
		this.codigo=s;
	}
	/**
	 * Decodifica el string pasado al constructor 
	 * @return Devuelve el valor del codigo despues que se haya decodificado
	 * @throws FormatErrorDecodificacionException Excepcion lanzada cuando el codigo leido
	 * no tiene el formato correcto
	 * @throws CodFormatErrorDecodificacionException Excepcion lanzada cuando el numero de entrada no
	 * tiene el formato corrrecto
	 */
	public int decoderString() throws FormatErrorDecodificacionException, CodFormatErrorDecodificacionException
	{
		if(!ChequearFormato())
		{
			throw new FormatErrorDecodificacionException();
		}
		if(ChequearCodigo()==-1)
		{
			throw new CodFormatErrorDecodificacionException();
		}
	
		return ChequearCodigo();
	}
	
	/**
	 * A partir de un numero de entrada completamente valido, 
	 * te pasa el valor codificado según el formato estandar
	 * @param codEntrada El numero para codificar
	 * @return La cadena codificada
	 */
	public static final String getCodedString(int codEntrada)
	{
		return "#ALP#"+(codEntrada*R+S)+"#";
	}
	
	/**
	 * Chequea que el formato del codiogo sea correcto #ALP#-------#
	 * @return devuelve true si esta bien formateado
	 */
	private boolean ChequearFormato()
	{
		String cadena="";	
	
		if(codigo.charAt(0)==InicioMedioFinal && codigo.charAt(1)==PrimeraLetra 
				&& codigo.charAt(2)== SegundaLetra && codigo.charAt(3)==TerceraLetra 
				&& codigo.charAt(4)==InicioMedioFinal) // comprueba que la cadena empieza bien 
		{
			cadena=codigo.substring(5);
			if(cadena.charAt(cadena.length()-1)==InicioMedioFinal)
			{
				return true;
			}
		}
		return false;	
	}

	/**
	 * Chequea que el codigo(ya retiradas las cabeceras) sea correcto
	 * @return devuelve el codigo si esta bien (codigo>0) y si esta mal devuelve -1
	 */
	private int ChequearCodigo(){
		String aux;
		int auxInt;
	
		aux=SacarString();
		System.out.println(aux);
		try
		{
			auxInt=Integer.parseInt(aux);
			auxInt=auxInt-S;
			if(auxInt%R==0)
			{
				return auxInt/R;
			}
			else
			{
				return -1;
			}
		}
		catch(NumberFormatException ex)
		{
			return -1;
		}	
	}
	
	/**
	 * Metodo auxiliar que saca el string Quitandole las cabeceras
	 * @return devuleve el String sin las cabeceras
	 */
	private String SacarString()
	{
		String cadena="";
	
	
		int tam=0;
		tam=codigo.length();
		tam--;
		cadena=codigo.substring(5,tam);
		
		return cadena;
	}


}
