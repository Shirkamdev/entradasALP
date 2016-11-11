package main;

import decodificar.CamList;

/**
 * Clase que llama a la GUI del programa
 * @author Shirkam
 * @version 1.0.0
 */
public class MainClass 
{
	/**
	 * Main de la clase, desde donde se empieza a iniciar el programa
	 * @param args Los argumentos de inicio, por ahora no tienen efecto alguno 
	 */
	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() 
		{
            public void run() 
            {
                new CamList().init();
            }
        });
	}
}
