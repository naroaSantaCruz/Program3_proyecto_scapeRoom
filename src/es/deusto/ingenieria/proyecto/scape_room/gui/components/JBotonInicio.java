package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JBotonInicio extends JButton{

	private static final long serialVersionUID = 1L;
	private static final Color colorNormal = new Color(240, 240, 240);
	private static final Color colorSeleccionado = new Color(200, 220, 245);


	public JBotonInicio(String texto) {
		// TODO Auto-generated constructor stub
		this.setText(texto);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setBackground(colorNormal);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(colorSeleccionado);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(colorNormal);
			}
		});
		
	}

}
