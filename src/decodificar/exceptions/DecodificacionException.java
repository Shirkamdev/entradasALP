package decodificar.exceptions;

/**
 * Superclase de las excepciones de decodificacion
 * @author Shirkam
 * @version 0.0.0
 */
public class DecodificacionException extends Exception
{
	/**
	 * Identificador de la excepcion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor que invoca al de la clase Exception
	 */
	public DecodificacionException()
	{
		super();
	}
	
	/**
	 * Nos da el mensaje de error
	 * @return el mensaje de error
	 */
	public String getMessage()
	{
		return "Error en la decodificacion"; 
	}
}
