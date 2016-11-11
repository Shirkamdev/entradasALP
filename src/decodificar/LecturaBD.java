package decodificar;

import interfaz.MainFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturaBD 
{
	private Connection conn;
	private final String url = "jdbc:sqlite:db/ParticipantesALP.db";
	private final String driver = "org.sqlite.JDBC";
	
	public LecturaBD()
	{
		conn = null;
	}
	
	public final void startConnection()
	{
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url);
			conn.setAutoCommit(false);
			System.out.println("Opened DB succesfully.");
			System.out.println("····················");
			
		}
		catch(InstantiationException ex)
		{System.err.println(ex.getMessage());}
		catch(IllegalAccessException ex)
		{System.err.println(ex.getMessage());}
		catch(ClassNotFoundException ex)
		{System.err.println(ex.getMessage());}
		catch(SQLException ex)
		{MainFrame.setExcepError("ERROR: No ha sido posible conectar con la base de datos.");}
	}
	
	/**
	 * El metodo devuelve una cadena de strings que contiene la informacion sobre el participante
	 * pedido, si no lo encuentra, devuelve strings nulos
	 * @param codEntradaToFind El codigo de la entrada a encontrar
	 * @return El resultado de la busqueda
	 */
	public final Entrada getParticipante(String codEntradaToFind)
	{
		//Inicializamos una entrada a nulo para devolverla si no hay resultados
		Entrada entrada = null;
		
		try
		{
			//stm = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM participantes WHERE codEntrada = ?;");
			stmt.setString(1,  codEntradaToFind);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String codEntrada = rs.getString("codEntrada");
				String nombre = rs.getString("nombre");
				String nick = rs.getString("nick");
				String dni = rs.getString("dni");
				String equipo = rs.getString("equipo");
				String sitio = rs.getString("sitio");
				
				try
				{
					entrada = new Entrada(new Decodificacion(codEntrada).decoderString(),
						 nombre, nick, dni, equipo, sitio);
				}
				catch(Exception e)
				{MainFrame.setExcepError("ERROR: La base de datos contiene un código no válido.");}
				
				System.out.println("CodEntrada: " + codEntrada);
				System.out.println("Nombre: " + nombre);
				System.out.println("Nick: " + nick);
				System.out.println("DNI: " + dni);
				System.out.println("Equipo: " + equipo);
				System.out.println("--------------------");
			}
			
			rs.close();
			stmt.close();
			
			return entrada;
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getClass().getName()+":"+ex.getMessage());
			return entrada;
		}
	}
	
	public final void closeConnection()
	{
		try
		{
			conn.commit();
			conn.close();
		}
		catch(SQLException ex)
		{}
	}
}
