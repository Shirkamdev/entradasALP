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
class HelpWindow extends JFrame implements ActionListener
{
	//Da igual que el metodo sea publico, solo se puede acceder a la clase desde el paquete interfaz
	public HelpWindow()
	{
		super("Ayuda del programa");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new GridBagLayout());
		String[] ayudaText = {"AYUDA DEL PROGRAMA:",
			  "·Para capturar un codigo QR pulsa espacio,"
			+ " tras unos momentos de procesamiento,",
			  "aparecerá la información pertinente.",
			  "·Si no funcionase bien la captura por webcam, "
			+ "puedes escribir el codigo de la ",
			  "entrada en el recuadro y darle al boton Comprobar codigo."};
		JLabel ayudas[] = new JLabel[ayudaText.length];
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 20, 10, 10);
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
			dispose();
		}
	}
}
