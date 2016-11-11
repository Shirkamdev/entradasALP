package decodificar;

import interfaz.MainFrame;
import decodificar.Decodificacion;
import decodificar.exceptions.CodFormatErrorDecodificacionException;
import decodificar.exceptions.FormatErrorDecodificacionException;
import decodificar.exceptions.NoCamDecodificacionException;
import processing.core.PApplet;
import processing.core.PImage;
import processing.video.Capture;
import qrcodeprocessing.Decoder;

/**
 * clase encargada de leer y sacar los codigos qr de las images que el mismo coge.
 * la idea por lo que lo modifico es que desde los argumentos reciba la webcam que se desea
 * que se obtiene de leerWebcams 
 * @author jaquer
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class Cam extends PApplet
{
	/**
	 * Zona donde vamos a poner la imagen de la webcam
	 */
	private static Capture cam;
	/**
	 * Objeto que se encarga de decodificar el string contenido en el QR
	 */
	private Decoder decoder;
	/**
	 * String de control que mostramos durante la captura
	 */
	private static String statusMsg = "Waiting for an image";
	/**
	 * Numero de la webcam a elegir
	 */
	private static int numWebcam = 0;

	/**
	 * Inicio del sketch de processing para capturar la webcam
	 * @param args
	 */
	public void setup() 
	{
		size(640, 480);

		String[] cameras = Capture.list();
		
		if(cameras.length > 0)
		{
			// The camera can be initialized directly using an 
			// element from the array returned by list():
			cam = new Capture(this, cameras[numWebcam]);
			cam.stop();
			cam.start(); 
			decoder= new Decoder(this);
		}
		else
		{
			MainFrame.setExcepError(new NoCamDecodificacionException().getMessage());
		}
	}      
		
	/**
	 * Metodo que detecta un evento de decodificacion y llama a los metodos adecuados
	 * @param decoder El objeto de decodificacion de QR
	 */
	public void decoderEvent(Decoder decoder)
	{
		statusMsg = decoder.getDecodedString();
		Decodificacion a = new Decodificacion(statusMsg);
		try
		{
			System.out.println("Comprobando el codigo leido");
			statusMsg=Integer.toString(a.decoderString());
		}
		catch(FormatErrorDecodificacionException ex)
		{
			MainFrame.setExcepError(ex.getMessage());
		}
		catch(CodFormatErrorDecodificacionException ex)
		{
			MainFrame.setExcepError(ex.getMessage());
		}
	}
	
	/**
	 * Metodo que trabaja con los eventos de captura de imagen, en este caso
	 * solo se dedica a leer las imagenes que llegan por la webcam
	 * @param video La webcam a leer
	 */
	public void captureEvent(Capture video) 
	{
	  video.read();
	}
	
	/**
	 * Parte donde trabajamos con las imagenes y llamamos a los metodos
	 */
	public void draw() 
	{
		background(0);
		if(cam != null)
		{
			// Display video
			image(cam, 0, 0);
			// Display status
			text(statusMsg, 10, height-4);
			// If we are currently decoding
			if (decoder.decoding()) 
			{
				// Display the image being decoded
				PImage show = decoder.getImage();
				image(show, 0, 0, show.width/4, show.height/4); 
				statusMsg = "Decoding image";
				for (int i = 0; i < (frameCount/2) % 10; i++) 
					statusMsg += ".";
			}
		}
	}
	
	/**
	 * Lector de eventos de pulsacion de teclado
	 */
	public void keyReleased() 
	{
		// Depending on which key is hit, do different things:
		 switch (key) 
		 {
		 	case ' ':        
		 		//Ponemos los errores de los anteriores procedimientos a 0
		 		MainFrame.setExcepError("");
		 		// Spacebar takes a picture and tests it:
			    // copy it to the PImage savedFrame:
			    PImage savedFrame = createImage(cam.width, cam.height, RGB);
			    savedFrame.copy(cam, 0, 0, cam.width, cam.height, 0, 0, cam.width, cam.height);
			    savedFrame.updatePixels();
			    // Decode savedFrame
			    decoder.decodeImage(savedFrame);
			    try
			    {
			    	MainFrame.setCodigoEntrada(Integer.parseInt(statusMsg));
			    }
			    catch(NumberFormatException ex)
			    {} 
			break;
			case 'f':    // f runs a test on a file
			    PImage preservedFrame = loadImage("qrcode.png");
			    // Decode file
			    decoder.decodeImage(preservedFrame);
			break;
		}
	}
	
	/**
	 * Metodo para parar la captura de la camara y que no se quede pillada en E/S
	 */
	public void stopCam()
	{
		cam.stop();
	}
	
	/**
	 * Metodo para poder elegir la webcam que vamos a poner
	 * @param num El indice de camara que queremos usar
	 */
	public static void setNumWebcam(int num)
	{
		numWebcam = num;
	}		
}
