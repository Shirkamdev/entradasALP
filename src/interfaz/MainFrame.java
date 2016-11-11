package interfaz;

import interfaz.exceptions.ActionEventNotExistException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import decodificar.Cam;
import decodificar.Decodificacion;
import decodificar.Entrada;
import decodificar.LecturaBD;
import decodificar.exceptions.CodFormatErrorDecodificacionException;
import decodificar.exceptions.FormatErrorDecodificacionException;


/**
 * Clase de la ventana principal
 * @author Shirkam
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
{
	/**
	 * Codigo de la entrada que se acaba de leer por QR
	 */
	private static int codEntradaNum;
	
	
	/**
	 * Restricciones del admistrador
	 */
	private GridBagConstraints c = new GridBagConstraints();
	/**
	 * Panel para guardar la parte de qr
	 */
	private JPanel qrPanel = new JPanel();
	/**
	 * JPanel para la imagen de la webcam
	 */
	private static JPanel webCamImage = new JPanel();
	/**
	 * Panel para contener los botones
	 */
	private JPanel botones = new JPanel();
	//Botones de QR
	/**
	 * JLabel para escribir errores de lectura
	 */
	private JLabel errorLectura = new JLabel("");
	/**
	 * Campo para escribir el codigo de comprobacion si no es posible
	 * leer bien el QR
	 */
	private JTextField codigo = new JTextField();
	/**
	 * Boton para comprobar el codigo
	 */
	private JButton comprobarCod = new JButton("Comprobar codigo");
	/**
	 * Label para mostrar los errores de comprobacion
	 */
	private JLabel errorCod = new JLabel("");
	//Datos del usuario
	/**
	 * Grid que organiza los datos del usuario que se muestran
	 */
	private JPanel datos = new JPanel();
	/**
	 * Grid para los datos de la entrada
	 */
	private JPanel datos1 = new JPanel();
	/**
	 * Label que muestra el codigo de la entrada
	 */
	private static JLabel codEntrada = new JLabel("Codigo entrada: ");
	/**
	 * Label que muestra el nombre
	 */
	private static JLabel nombre = new JLabel("Nombre: ");
	/**
	 * Label que muestra el nick del usuario
	 */
	private static JLabel nick = new JLabel("Nick: ");
	/**
	 * Label que muestra el DNI del comprador
	 */
	private static JLabel dni = new JLabel("DNI: ");
	/**
	 * Label que muestra el equipo del comprador
	 */
	private static JLabel equipo = new JLabel("Equipo: ");
	/**
	 * Label que muestra donde va a sentarse el comprador
	 */
	private static JLabel sitio = new JLabel("Sitio: ");
	/**
	 * Grid para los datos de seguridad
	 */
	private JPanel datos2 = new JPanel();
	/**
	 * Label que muestra el texto aclarativo de seguridad
	 */
	private JLabel codSegText = new JLabel("Codigo de seguridad: ");
	/**
	 * Label que muestra el codigo de seguridad tal y como es
	 */
	private JLabel codSeg = new JLabel("");
	/**
	 *Panel para poner los errores
	 */
	private JPanel errores = new JPanel();
	/**
	 * Label que va a mostrar los errores de las excepciones y tal
	 */
	private static JLabel excepError = new JLabel("");
	/**
	 * Boton que permite cerrar la aplicacion con seguridad
	 */
	private JButton exit = new JButton("Cerrar aplicacion");
	/**
	 * Aplicacion de processing que se encarga de pasar las imagenes de una
	 * webcam y lee codigos qr cuando se pulsa INTRO
	 */
	private static processing.core.PApplet sketch;
	/**
	 * Barra donde vamos a mostrar las opciones del menu
	 */
	private JMenuBar menubar = new JMenuBar();
	/**
	 * Menu con ayuda sobre el programa y sus autores
	 */
	private JMenu help = new JMenu("Ayuda");
	/**
	 * Item del menu donde vamos a mostrar la ayuda de la app
	 */
	private JMenuItem ayuda = new JMenuItem("Ayuda");
	/**
	 * Item del menu donde vamos a mostrar informacion del programa y sus autores
	 */
	private JMenuItem about = new JMenuItem("Sobre la aplicacion");
	
	/**
	 * Constructor, crea la ventana principal.
	 */
	public MainFrame()
	{
		super("Entradas ALP");
		//setSize(640+300, 480+150);
		ImageIcon icon = new ImageIcon("Icon/LP1.png");
		setIconImage(icon.getImage());
		
		GridBagLayout ventana = new GridBagLayout();
		setLayout(ventana);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;

		//ponemos el menu de la app
		menu();
		//Imagen de la webcam
		webcamImage();
		//Organizacion de los botones y errores de lectura
		lectCodSec();
		//Datos de la entrada
		datosEntrada();
		//Boton de cerrar la aplicacion
		exitButtonPos();
		//Errores de la aplicacion
		erroresPos();
		
		//Ponemos los comandos de accion para los botones
		actionCommands();
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Metodo que pone el menu de la aplicacion
	 */
	private final void menu()
	{
		//Ponemos el menu de seleccion de cams y de ayuda arriba
		help.setMnemonic(KeyEvent.VK_H);
		help.getAccessibleContext().setAccessibleDescription("Muestra la ayuda e "
				+ "informacion sobre el programa.");
		ayuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		ayuda.getAccessibleContext().setAccessibleDescription("Muestra la ayuda del programa.");
		ayuda.addActionListener(this);
		ayuda.setActionCommand("ayuda");
		about.getAccessibleContext().setAccessibleDescription("Muestra informacion sobre el programa");
		about.addActionListener(this);
		about.setActionCommand("about");
		
		help.add(ayuda);
		help.add(about);
		menubar.add(help);
		setJMenuBar(menubar);
	}
	
	/**
	 * Metodo que pone el panel donde va la imagen de la webcam
	 */
	private final void webcamImage()
	{
		sketch = new Cam();
		webCamImage.add(sketch);
		sketch.init();
		
		GridBagLayout qr = new GridBagLayout();
		qrPanel.setLayout(qr);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 640;
		c.ipady = 480;
		qrPanel.add(webCamImage, c);
	}
	
	/**
	 * Metodo que pone los botones de lectura de codigo
	 * y el label de errores
	 */
	private final void lectCodSec()
	{
		//Organizamos los botones
		GridLayout botonesQr = new GridLayout(5, 1);
		botones.setLayout(botonesQr);
		botones.add(errorLectura);
		botones.add(codigo);
		botones.add(comprobarCod);
		botones.add(errorCod);
		
		//Actualizamos las constraint
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 20);
		//Ponemos los botones en la ventana
		qrPanel.add(botones, c);
		
		//Ponemos el panel del qr en su sitio
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(qrPanel, c);
	}
	
	/**
	 * Metodo que coloca los datos de las entradas leidas
	 */
	private final void datosEntrada()
	{
		//Datos de la entrada
		GridLayout datosGrid = new GridLayout(1, 2);
		datos.setLayout(datosGrid);
		
		GridLayout personal = new GridLayout(6, 1);
		datos1.setLayout(personal);
		datos1.add(codEntrada);
		datos1.add(nombre);
		datos1.add(nick);
		datos1.add(dni);
		datos1.add(equipo);
		datos1.add(sitio);
				
		GridLayout seguridad = new GridLayout(2, 1);
		datos2.setLayout(seguridad);
		datos2.add(codSegText);
		datos2.add(codSeg);
				
		datos.add(datos1);
		datos.add(datos2);
		//Los vamos a poner debajo
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 10;
		c.insets = new Insets(0, 20, 0, 0);
		add(datos, c);
	}
	
	/**
	 * Metodo que coloca el boton de cerrado
	 */
	private final void exitButtonPos()
	{
		//Boton de cerrar la aplicacion
		JPanel exitPanel = new JPanel();
		FlowLayout flowPanel = new FlowLayout();
		exitPanel.setLayout(flowPanel);
		exitPanel.add(exit);
		
		//Los vamos a poner debajo
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.CENTER;
		add(exitPanel, c);
	}
	
	/**
	 * Metodo que coloca el label de errores
	 */
	private final void erroresPos()
	{
		//Errores
		FlowLayout erroresLayout = new FlowLayout(FlowLayout.CENTER);
		errores.setLayout(erroresLayout);
		errores.add(excepError);
		//Ponemos lo errores debajo del todo
		c.gridx = 0;
		c.gridy = 4;
		add(errores, c);
	}
	
	/**
	 * Metodo encargado de poner a los botones sus respectivos comandos de accion
	 */
	private final void actionCommands()
	{
		comprobarCod.setActionCommand("comprobarCod");
		comprobarCod.addActionListener(this);
		exit.setActionCommand("cerrar");
		exit.addActionListener(this);
	}
	
	/**
	 * Clase que salta cuando se pulsa un boton
	 * @param ev El evento (pulsacion de boton) que ha ocurrido
	 */
	public void actionPerformed(ActionEvent ev)
	{
		excepError.setText("");	
		String command = ev.getActionCommand();
		
		switch(command)
		{
			case "comprobarCod":
				if(codigo.getText().length() > 0)
				{
					Decodificacion dec = new Decodificacion(codigo.getText());
					try
					{
						//El codigo que haya en la entrada tiene que ser el codigo completo
						//con la cdificacion estandar
						setCodigoEntrada(dec.decoderString());
					}
					catch(FormatErrorDecodificacionException ex)
					{setExcepError(ex.getMessage());}
					catch(CodFormatErrorDecodificacionException ex)
					{setExcepError(ex.getMessage());}
				}
				break;
			case "cerrar":
				//Cerramos la webcam
				sketch.exit();
				sketch.stop();
				//cerramos la aplicacion
				System.exit(0);
				break;
			case "ayuda":
				new HelpWindow();
				break;
			case "about":
				new AboutWindow();
				break;
			default:
				excepError.setText(new ActionEventNotExistException().getMessage());
		}
	}
	
	/**
	 * Metodo para actualizar los datos personales que aparecen en la ventana
	 * desde otra clase. A partir de ese codigo se llama a la base de datos para
	 * conseguir el resto de valores.
	 * @param codEntradaNew El codigo de entrada leido desde otra clase.
	 */
	public static void setCodigoEntrada(int codEntradaNew)
	{
		codEntradaNum = codEntradaNew;
		codEntrada.setText("Codigo entrada: "
				+codEntradaNum);
		LecturaBD bd = new LecturaBD();
		Entrada participante = bd.getParticipante(Decodificacion.getCodedString(codEntradaNum));
		if(participante != null)
		{
			nombre.setText("Nombre: " + participante.getNombre());
			nick.setText("Nick: " + participante.getNick());
			dni.setText("DNI: " + participante.getDni());
			equipo.setText("Equipo: " + participante.getEquipo());
			if(participante.getSitio().length() > 0)
				sitio.setText("Sitio: " + participante.getSitio());
			else
				sitio.setText("Sitio: -ninguno-");
		}
	}
	
	/**
	 * Metodo para actualizar los mensajes de error desde otras clases.
	 * @param error Los mensajes de error a poner
	 */
	public static void setExcepError(String error)
	{
		excepError.setText(error);
	}
	
	
}
