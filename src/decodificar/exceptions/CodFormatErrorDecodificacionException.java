package decodificar.exceptions;

/**
 * Excepcion lanzada cuando el codigo que se ha leido de una entrada no tiene el formato
 * correcto
 * @author Shirkam
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class CodFormatErrorDecodificacionException extends DecodificacionException
{
	/**
	 * Construcor por defecto, invoca al constructor de Exception
	 */
	public CodFormatErrorDecodificacionException()
	{
		super();
	}
	
	/**
	 * Metodo que nos da el mensaje de error de la excepcion
	 * @return Devuelve el mensaje de error de la excepcion
	 */
	public String getMessage()
	{
		return "ERROR: El numero de entrada no tiene un formato valido, puede ser falsa.";
	}
}
