package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.border.AbstractBorder;

public class BordeVentana extends AbstractBorder{
	private final Image borde = new ImageIcon("images/cargar_partidas/fondopantallaCargaBasico.png").getImage();
	public BordeVentana() {

	}
	@Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        g.drawImage(borde, x, y, width, height, null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(45, 30, 45, 30);
    }

}
