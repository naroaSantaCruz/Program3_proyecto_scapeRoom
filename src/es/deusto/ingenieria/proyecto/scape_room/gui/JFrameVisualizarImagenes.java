package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JFrameVisualizarImagenes extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;
	private JLabel labelImagen;
	private int width;
	private int height;

	public JFrameVisualizarImagenes( String ruta, JFrame panel ) {
		String[] partesRuta = ruta.split("/");
		String nombreFoto = partesRuta[partesRuta.length-1];
		
		imagen = new ImageIcon(ruta);
		labelImagen = new JLabel();
		labelImagen.setIcon(imagen);
		
		width = imagen.getIconWidth();
		height = imagen.getIconHeight();
		
		this.setTitle(nombreFoto);
		this.setSize(new Dimension(width,height));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout( new BorderLayout() );
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.add(labelImagen, BorderLayout.CENTER);
	}
	
	public void cambiarTamanoPantalla( int width, int height ) {
		Image imagenEscalada = this.imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		this.imagen.setImage(imagenEscalada);
		this.setSize(new Dimension(width, height));
	}
	
	public static void main(String[] args) {
		JFrameVisualizarImagenes panelImagen = new JFrameVisualizarImagenes("images/pantalla_inicio/fondo_inicio_windows.jpg", null );
		panelImagen.cambiarTamanoPantalla(800, 800);
	}

}
