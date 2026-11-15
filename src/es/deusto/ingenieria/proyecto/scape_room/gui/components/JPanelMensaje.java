package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JPanelMensaje extends JPanel{

	private static final long serialVersionUID = 1L;

	private JLabel usuario;
	private JLabel burbuja;
	private ImageIcon burbujaKorp = new ImageIcon("images/chat_fjerda/burbujaKorp.png");
	private ImageIcon burbujaLiam = new ImageIcon("images/chat_fjerda/burbujaLiam.png");
	private Image imagenRedi;
	
	private String[] nombresUsuarios = {"GhostKorp_Fj", "Me"};

	public JPanelMensaje(int codUsuario, String[] mensaje) {
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		usuario = new JLabel(nombresUsuarios[codUsuario]);
		usuario.setOpaque(false);
		usuario.setFont(new Font("Monospaced", Font.BOLD, 13));
		usuario.setForeground(Color.LIGHT_GRAY);
		burbuja = new JLabel();
		burbuja.setOpaque(false);
		burbuja.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		//resize burbuja dependiendo del numero de lineas
		int h = 40*mensaje.length;
		int l = 10*mensaje[0].length();
		
		//montar el mensaje
		String mensajeText = "<html>";
		for (int i = 0; i < mensaje.length; i++) {
			mensajeText += mensaje[i];
			if(i < mensaje.length-1) {
				mensajeText += "<br>";
			} 
		} 
		mensajeText += "</html>";
		
		if(codUsuario == 0) {
			imagenRedi = burbujaKorp.getImage().getScaledInstance(l, h, Image.SCALE_SMOOTH);
			burbuja.setForeground(Color.BLACK);
			burbuja.setHorizontalAlignment(SwingConstants.LEFT);
			burbuja.setBorder(new EmptyBorder(0,40,2,0));
			usuario.setHorizontalAlignment(SwingConstants.LEFT);
			usuario.setBorder(new EmptyBorder(0,40,0,0));
		} else if(codUsuario==1) {
			imagenRedi = burbujaLiam.getImage().getScaledInstance(l, h, Image.SCALE_SMOOTH);
			burbuja.setForeground(Color.WHITE);
			burbuja.setHorizontalAlignment(SwingConstants.RIGHT);
			burbuja.setBorder(new EmptyBorder(0,0,2,40));
			usuario.setHorizontalAlignment(SwingConstants.RIGHT);
			usuario.setBorder(new EmptyBorder(0,0,0,40));
		}
		
		ImageIcon imagen = new ImageIcon(imagenRedi);
		
		//añadir foto y texto
		burbuja.setIcon(imagen);
		burbuja.setText(mensajeText);
		burbuja.setHorizontalTextPosition(JLabel.CENTER);
		burbuja.setVerticalTextPosition(JLabel.CENTER);
		
		//añadir elementos al panel principal
		this.add(burbuja, BorderLayout.CENTER);
		this.add(usuario, BorderLayout.SOUTH);
		
	}
}
