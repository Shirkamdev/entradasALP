package interfaz.exceptions;

/**
 * Superclase de los errores de interfaz
 * @author Shirkam
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InterfaceException extends Exception
{
	/**
	 * Constructor por defecto, invoca al de Exception
	 */
	public InterfaceException()
	{
		super();
	}
	
	/**
	 * Nos da un mensaje de error personalizado
	 * @return El error
	 */
	public String getMessage()
	{
		return "Error en la interfaz.";
	}
}
