package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class AboutWindow extends JFrame implements ActionListener
{
	private static final String[] ayudaText = {"Lector de entradas QR",
			  "Version: 1.0.0",
			  "Autor: Shirkam",
			  "Mencion especial a Jaquer por ayudar con la entrada",
			  "    de la webcam."};
	private static JLabel ayudas[] = new JLabel[ayudaText.length];
	
	//Da igual que el metodo sea publico, solo se puede acceder a la clase desde el paquete interfaz
		public AboutWindow()
		{
			super("Sobre el programa");
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setLayout(new GridBagLayout());
			
			
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(5, 20, 5, 5);
			c.gridx=0;
			JButton cerrar = new JButton("Cerrar");
			
			for(int i=0; i<ayudaText.length; i++)
			{
				c.gridy=i;
				ayudas[i] = new JLabel(ayudaText[i]);
				add(ayudas[i], c);
			}
			
			c.anchor = GridBagConstraints.CENTER;
			c.gridy = ayudaText.length;
			c.ipadx=40;
			add(cerrar, c);
			
			try
			{
				c.gridx=2;
				c.gridy=0;
				c.gridheight=ayudaText.length+1;
				//Leemos la imagen
				BufferedImage myPicture = ImageIO.read(new File("Icon/LP1_160x300.png"));
				//La ponemos en una etiqueta
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				add(picLabel, c);
			}
			catch(IOException ex)
			{}
			
			cerrar.addActionListener(this);
			cerrar.setActionCommand("cerrar");
			
			pack();
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent ev)
		{
			String command;
			command = ev.getActionCommand();
			
			if(command.equals("cerrar"))
			{
				dispose();
			}
		}
}
