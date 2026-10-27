package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JDialogTextoInicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelCentral = new JPanel(new FlowLayout());
	private JTextField textoInicio = new JTextField();
	private JTextField textoPassword = new JTextField("password");
	
	public JDialogTextoInicio(JFramePantallaInicio pantallaInicio) {
		// CONFIGURACIÓN TEXTO
		textoInicio.setText("Bienvenido al juego... \n" + "Todo puede ser una pista: ");
		textoInicio.setForeground(Color.BLACK);
		textoInicio.setEditable(false);
		textoInicio.setBorder(null);
		textoInicio.setOpaque(false);
		panelCentral.add(textoInicio);
		textoPassword.setForeground(Color.WHITE);
		textoPassword.setSelectedTextColor(Color.BLACK);
		textoPassword.setBorder(null);
		textoPassword.setOpaque(false);
		textoPassword.setEditable(false);
		panelCentral.add(textoPassword);
		
		// CONFIGURACIÓN VENTANA MINIMIZABLE
		panelCentral.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setModal(false);
		this.setLayout(new BorderLayout());
		this.add(panelCentral,BorderLayout.CENTER);
	}

}
