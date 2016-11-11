package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class NoCamClosingWindow extends JFrame implements ActionListener
{
	private static final String[] ayudaText = {"ERROR:",
			  "El programa no detecta ninguna webcam.",
			  "Cierre esta ventana para terminar."};
	private static JLabel ayudas[] = new JLabel[ayudaText.length];
	
	//Da igual que el metodo sea publico, solo se puede acceder a la clase desde el paquete interfaz
		public NoCamClosingWindow()
		{
			super("No webcam disponible");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				System.exit(0);
			}
		}
}
