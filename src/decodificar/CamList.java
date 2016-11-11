package decodificar;

import interfaz.ElegirWebcam;
import decodificar.exceptions.NoCamDecodificacionException;
import processing.core.PApplet;
import processing.video.Capture;

/**
 * clase encargada de leer y sacar los codigos qr de las images que el mismo coge.
 * la idea por lo que lo modifico es que desde los argumentos reciba la webcam que se desea
 * que se obtiene de leerWebcams 
 * @author jaquer
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class CamList extends PApplet
{
	private ElegirWebcam window;
	
	/**
	 * Inicio del sketch de processing para capturar la webcam
	 * @param args
	 */
	public void setup() 
	{
		String[] cameras = Capture.list();
		
		if(cameras.length > 0)
		{
			String [] aux = new String[cameras.length];
			for(int i=0; i<aux.length; i++)
			{
				aux[i] = getCamInformation(cameras[i]);
			}
			ElegirWebcam.setWebcamList(aux);
		}
		else
		{
			ElegirWebcam.setExcepError(new NoCamDecodificacionException().getMessage());
		}
		
		window = new ElegirWebcam();
		
		stop();
	}      
	
	/**
	 * Este metodo no hace nada porque la clase solo sirve para actualizar la lista de webcams
	 */
	public void draw() 
	{}
	
	
	
	/**
	 * Buscamos en el string que nos pasan la informacion relevante para que sea más facil
	 * de leer las webcam.
	 * @param cam Toda la informacion de la camara
	 * @return El string con el resultado mas legible
	 */
	private String getCamInformation(String cam)
	{
		//11:name=ASUS USB2.0 Webcam,size=1280x720,fps=30
		String information="";
		String[] parts = cam.split(",");
		if(parts.length == 3)
		{
			information += parts[0].substring(5); //El 5 es la posicion donde empieza el nombre
			information += ", ";				  //de la cam
			information += parts[1].substring(5); //El 5 es donde empieza la resolucion de la cam
			information += ", ";
			information += parts[2].substring(4);
			information += "fps";
			
		}
		
		return information;
	}
		
}
