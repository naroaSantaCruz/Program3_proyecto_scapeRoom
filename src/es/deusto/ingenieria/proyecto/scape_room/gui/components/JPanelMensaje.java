package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelMensaje extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel usuario;
	private JLabel texto;
	private JPanel burbuja;
	
	private String[] nombresUsuarios = {"GhostKorp_Fj", "Me"};

	public JPanelMensaje(int codUsuario, String mensaje) {
		usuario = new JLabel(nombresUsuarios[codUsuario]);
		texto = new JLabel(mensaje);
		burbuja = new JPanel();
	}

}
