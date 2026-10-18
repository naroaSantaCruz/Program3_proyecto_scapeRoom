package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.border.AbstractBorder;

public class BordePanelesMenu extends AbstractBorder {
	private Image borde;
	
	public BordePanelesMenu() {
		ImageIcon imagenBorde = new ImageIcon("images/cargar_partidas/bordePanel.png");
		borde = imagenBorde.getImage();
	}
	
	//Citar IA
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		 g.drawImage(borde, x, y, width, height, null);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		 return new Insets(20, 20, 20, 20);
	}
	
	
}
