package decodificar.exceptions;

/**
 * Excepcion lanzada cuando lo leido del QR no corresponde con el
 * formato standar que se utiliza en la aplicacion
 * @author Shirkam
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class FormatErrorDecodificacionException extends DecodificacionException
{
	/**
	 * Constructor por defecto, invoca al de Exception
	 */
	public FormatErrorDecodificacionException()
	{
		super();
	}
	
	/**
	 * Nos da un mensaje de error personalizado, para saber que ha ido mal
	 * @return El mensaje de error
	 */
	public String getMessage()
	{
		return "ERROR: El codigo no tiene un formato correcto, la entrada puede ser falsa.";
	}
}
