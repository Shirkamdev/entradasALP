package interfaz;

import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Graphics;

import javax.swing.JPanel;


@SuppressWarnings("serial")
class JPanelBackGround extends JPanel
{
		//Imagen de fondo
		private Image background;
	 
		//Metodo que es llamado automaticamente cada vez que repinta
		public void paintComponent(Graphics g) {
	 
			/* Obtenemos el tama�o del panel para hacer que se ajuste a este
			cada vez que redimensionemos la ventana y se lo pasamos al drawImage */
			int width = this.getSize().width;
			int height = this.getSize().height;
	 
			// Mandamos que pinte la imagen en el panel
			if (this.background != null) {
				g.drawImage(this.background, 0, 0, width, height, null);
			}
	 
			super.paintComponent(g);
		}
	 
		// Metodo donde le pasaremos la direcci�n de la imagen a cargar.
		public void setBackGround(String imagePath) 
		{
			// Construimos la imagen y se la asignamos al atributo background.
			this.background = (new ImageIcon(imagePath)).getImage();
			repaint();
		}
}
