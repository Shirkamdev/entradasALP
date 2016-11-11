package decodificar;

/**
 * Clase que almacena los datos leidos en una etiqueta
 * y calcula el codigo de seguridad
 * @author Shirkam
 * @version 1.0.0
 */
public class Entrada 
{
	/**
	 * El codigo de la entrada
	 */
	private int codEntrada;
	/**
	 * Nombre del comprador
	 */
	private String nombre;
	/**
	 * El nick del comprador
	 */
	private String nick;
	/**
	 * El dni del comprador
	 */
	private String dni;
	/**
	 * El equipo del participante
	 */
	private String equipo;
	/**
	 * Donde se va a sentar el parcipante
	 */
	private String sitio;
	
	/**
	 * Inicializa los atributos del obejeto
	 * @param cod Codigo de la entrada
	 * @param nom Nombre del participante
	 * @param nick Nick del participante
	 * @param dni DNI del participante
	 * @param equipo El equipo del participante
	 * @param sitio Donde se va a sentar el participante
	 */
	public Entrada(int cod, String nom, String nick, String dni, String equipo, String sitio)
	{
		this.codEntrada = cod;
		this.nombre = nom;
		this.nick = nick;
		this.dni = dni;
		this.equipo = equipo;
		this.sitio = sitio;
	}
	
	/**
	 * Calcula un codigo de seguridad para poner en las torres
	 * de los participantes a partir de algunos atributos del
	 * objeto
	 * @return El string del codigo de seguridad
	 */
	public String getSecurityCode()
	{
		//A partir del dni, se le pone el codEntrada en HEX
		String code = "";
		
		code += dni + calculateHex();
		
		return code;
	}

	/**
	 * Convierte un numero a hexadecimal
	 * @return El String con el numero en hexadecimal
	 */
	private String calculateHex()
	{
		String hexNumber = "";
		
		hexNumber = Integer.toHexString(codEntrada);
		
		return hexNumber;
	}	

	
	/**
	 * @return El codigo de la entrada que representa el objeto
	 */
	public int getCodEntrada() 
	{
		return codEntrada;
	}
	
	/**
	 * @return El nombre del comprador de la entrada
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @return El nick del comprador de la entrada
	 */
	public String getNick() 
	{
		return nick;
	}


	/**
	 * @return El DNI del comprador de la entrada
	 */
	public String getDni() 
	{
		return dni;
	}
	
	/**
	 * @return El equipo del comprador de la entrada
	 */
	public String getEquipo()
	{
		return equipo;
	}
	
	/**
	 * @return Donde se va a sentar 
	 */
	public String getSitio()
	{
		return decodSitio();
	}
	
	private String decodSitio()
	{
		String sitDecod;
		
		//Todos los sitios en la bd tienen el mismo formato M00F(3)00S(6)00
		sitDecod = "Mesa " + Integer.parseInt(sitio.substring(1, 3)) + " Fila "
				+ Integer.parseInt(sitio.substring(4, 6)) + " Silla " 
				+Integer.parseInt(sitio.substring(7));
		
		return sitDecod;
	}
	
	
}
