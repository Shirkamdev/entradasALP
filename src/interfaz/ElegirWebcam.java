package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import decodificar.Cam;
import decodificar.exceptions.NoCamDecodificacionException;

@SuppressWarnings("serial")
public class ElegirWebcam extends JFrame implements ActionListener
{
	private static String[] cameras;
	private final String[] noCam = {"No webcam"};
	private JLabel selecText = new JLabel("Elije webcam: ");
	private static JComboBox<String> camList;
	private JButton select = new JButton("Seleccionar");
	private static JLabel excepError = new JLabel("");
	
	public ElegirWebcam()
	{
		super("Elegir webcam");
		ImageIcon icon = new ImageIcon("Icon/LP1.png");
		setIconImage(icon.getImage());
		setLayout(new GridLayout(4, 1));
		
		//Probamos a ver si se nos ha pasado una lista de webcams
		try
		{
			camList = new JComboBox<String>(cameras);
		}
		//Si la lista esta vacia, recojemos la excepcion e inicializamos la lista con una camara predeterminada
		catch(java.lang.NullPointerException ex)
		{
			camList = new JComboBox<String>(noCam);
			excepError.setText("Actualizando lista de webcams...");
		}
		camList.setEditable(false);
		icon = new ImageIcon("Icon/webcam.png");
		select.setIcon(icon);
		
		add(selecText);
		add(camList);
		add(select);
		add(excepError);
		
		setSize(325, 175);
		setVisible(true);

		select.addActionListener(this);
		select.setActionCommand("seleccionar");
	}
	
	public static void setWebcamList(String[] camerasList)
	{
		cameras = camerasList;
		if(camerasList.length > 0)
			excepError.setText("Lista de webcams actualizada.");
		else
			excepError.setText(new NoCamDecodificacionException().getMessage());
		
		camList = new JComboBox<String>(cameras);
	}

	public void actionPerformed(ActionEvent ev) 
	{
		String command = ev.getActionCommand();
		if(command.equals("seleccionar"))
		{
			if(excepError.getText().equals("Lista de webcams actualizada."))
			{
				Cam.setNumWebcam(camList.getSelectedIndex());
				new MainFrame();
				dispose();
			}
			else
			{
				new NoCamClosingWindow();
			}
		}
	}
	
	public static void setExcepError(String error)
	{
		excepError.setText(error);
	}
}
