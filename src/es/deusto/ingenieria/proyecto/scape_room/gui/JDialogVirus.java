package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class JDialogVirus extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel panelFondo = new JPanel(new BorderLayout());
	private JPanel panelImagen = new JPanel();
	private JPanel panelBarratexto = new JPanel();
	private JProgressBar barraDescarga = new JProgressBar(0,100);
	private final ImageIcon warning = new ImageIcon("images/escritorio/warning_virus.png");
	private final static String TEXTO = """
		    <html>
		    <div style='text-align:center;'>
		    Espero que hayas llegado al ordenador a tiempo.<br>
		    Quiero que sepas que sabía que venían<br>
		    y que serías tú quien encontraría esta información.<br>
		    Coge lo que consideres, todo lo demás será destruido.<br>
		    BUENA SUERTE
		    </div>
		    </html>
		    """;
	
	public JDialogVirus() {
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setSize(new Dimension(650, 500));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panelFondo.setBackground(Color.black);
		JLabel imagen = new JLabel();
		imagen.setIcon(warning);
		panelImagen.add(imagen);
		panelImagen.setOpaque(false);
		
		barraDescarga.setPreferredSize(new Dimension(200,20));
		barraDescarga.setBackground(Color.black);
		barraDescarga.setForeground(Color.green);
		barraDescarga.setValue(0);
		barraDescarga.setBorder(BorderFactory.createLineBorder(Color.green));
		panelBarratexto.add(barraDescarga);
		panelBarratexto.setOpaque(false);
		panelBarratexto.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
		
		
		JLabel textoExplicacion = new JLabel(TEXTO);
		textoExplicacion.setForeground(Color.green);
		textoExplicacion.setFont(new Font("Consolas",Font.PLAIN, 16));
		textoExplicacion.setOpaque(false);
		textoExplicacion.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		
		panelBarratexto.add(textoExplicacion, JPanel.CENTER_ALIGNMENT);
		
		panelFondo.add(panelImagen, BorderLayout.NORTH);
		panelFondo.add(panelBarratexto, BorderLayout.CENTER);
		panelFondo.setBorder(BorderFactory.createLineBorder(Color.green, 1));
		
		this.setContentPane(panelFondo);
		this.setLocationRelativeTo(null);

		
		Thread hiloBarra = new Thread() {
			@Override
			public void run() {
				try {
					sleep(500);
				}catch(InterruptedException e) {
					return;
				}
				for(int i=0; i<=100; i++) {
					try {
						sleep(150);
					}catch(InterruptedException e) {
						break;
					}
					final int valor = i;
					SwingUtilities.invokeLater(()->barraDescarga.setValue(valor));
				}
				JDialogVirus.this.dispose();
		}
			
		};

		hiloBarra.start();
		
		this.setVisible(true);
	}

}
