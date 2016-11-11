package decodificar.exceptions;

/**
 * Excepcion lanzada cuando no existe ninguna camara desde la que sacar imagenes
 * @author Shirkam
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class NoCamDecodificacionException extends DecodificacionException 
{
	/**
	 * Constructor por defecto, invoca al constructor de Exception
	 */
	public NoCamDecodificacionException()
	{
		super();
	}
	
	/**
	 * Metodo que sobreescribe el metodo de Exception, nos da un error
	 * personalizado unico de esta excepcion
	 * @return El mensaje de error
	 */
	public String getMessage()
	{
		return "ERROR: Ninguna camara disponible para su captura.";
	}
}
