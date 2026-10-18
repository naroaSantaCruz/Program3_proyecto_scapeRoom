package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JBotonMenu extends JButton{

	private static final long serialVersionUID = 1L;
	
	private final ImageIcon IMAGEN_BOTON_ORI = new ImageIcon("images/menu_opciones_juego/boton_no_seleccionado.png");
	private final ImageIcon IMAGEN_BOTON_SEL_ORI = new ImageIcon("images/menu_opciones_juego/boton_seleccionado.png");
	
	private final Dimension dimensionBoton = new Dimension(300, 65);
	
	private final Font FUENTE = new Font("Bahnschrift",Font.BOLD,23);
	private final Color COLOR = new Color(170,235,255);
	private ImageIcon imagenBoton;
	private ImageIcon imagenBotonSel;
	
	
	public JBotonMenu(String texto) {
		redimensionarImagenes();
		this.setIcon(imagenBoton);
		this.setPreferredSize(dimensionBoton);
		this.setBorder(null);
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setText(texto);
		this.setFont(FUENTE);
		this.setForeground(COLOR);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
		
		//TODO hacer una clase boton nueva para configurarlo (meterle el listemer para mouseEntered y exited)
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(imagenBotonSel);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(imagenBoton);
			}
			
		});
	}
	
	private void redimensionarImagenes() {
		Image botonImgEscalada = IMAGEN_BOTON_ORI.getImage().getScaledInstance(480, 155, Image.SCALE_SMOOTH);
		imagenBoton = new ImageIcon(botonImgEscalada);
		
		Image botonImgEscaladaSel = IMAGEN_BOTON_SEL_ORI.getImage().getScaledInstance(330, 125, Image.SCALE_SMOOTH);
		imagenBotonSel = new ImageIcon(botonImgEscaladaSel);
	}

}
