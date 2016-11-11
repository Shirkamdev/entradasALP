package interfaz.exceptions;

/**
 * Excepcion lanzada cuando no existe ningun boton que corresponda a la accion producida
 * @author Shirkam
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class ActionEventNotExistException extends InterfaceException
{
	/**
	 * Constructor por defecto, invoca al de Exception
	 */
	public ActionEventNotExistException()
	{
		super();
	}
	
	/**
	 * Nos da un mensaje de error personalizado sobre lo que ha ocurrido
	 * @return El mensaje de error
	 */
	public String getMessage()
	{
		return "ERROR: El evento recibido no se corresponde con ninguno de los botones";
	}
}
