package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class JDialogTextoInicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelCentral;
	private JPanel barraSuperior;
	private JLabel tituloVentana;
	private JButton botonApagado;
	private JTextPane textoInicio;
	private Point pMouse;
	
	public JDialogTextoInicio(JFramePantallaInicio pantallaInicio) {
		
		// CONFIGURACIÓN VENTANA MINIMIZABLE
		this.setLayout(new BorderLayout());
		this.setSize(450,250);
		this.setLocationRelativeTo(pantallaInicio);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setModal(false);
		this.setUndecorated(true);
		
		// CONFIGURACIÓN PANEL CENTRAL
		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(20, 30, 55));
		
		// CONFIGURACIÓN BARRA SUPERIOR
		barraSuperior = new JPanel(new BorderLayout());
		barraSuperior.setBackground(new Color(20, 30, 55));
		
		tituloVentana = new JLabel();
		tituloVentana.setText("Instrucciones del juego");
		tituloVentana.setForeground(Color.WHITE);
		tituloVentana.setFont(new Font("Consolas", Font.PLAIN, 14));
		tituloVentana.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
		
		botonApagado = new JButton("X");
		botonApagado.setForeground(Color.WHITE);
		botonApagado.setBackground(Color.BLACK);
		botonApagado.setFont(new Font("Consolas", Font.PLAIN, 14));
		botonApagado.setBorderPainted(false);
		botonApagado.setFocusPainted(false);
		botonApagado.setPreferredSize(new Dimension(30,30));
		botonApagado.addActionListener( e -> setVisible(false));
		// hecho con IA
		barraSuperior.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pMouse = e.getPoint();
            }
        });
        barraSuperior.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point pPantalla = e.getLocationOnScreen();
                setLocation(pPantalla.x - pMouse.x, pPantalla.y - pMouse.y);
            }
        });
        
		barraSuperior.add(tituloVentana, BorderLayout.WEST);
		barraSuperior.add(botonApagado, BorderLayout.EAST);
				
		// CONFIGURACIÓN TEXTO
		textoInicio = new JTextPane();
		textoInicio.setContentType("text/html");
		textoInicio.setEditable(false);
		textoInicio.setOpaque(false);
		textoInicio.setFocusable(false);
		textoInicio.setSelectionColor(Color.WHITE);
		textoInicio.setSelectedTextColor(Color.BLACK);
		String contenidoHTML = "<html><body style='font-family: Consolas; font-size: 17pt; color: #ffffff;'>"
			    + "<b>¡Bienvenido jugador, ya estás dentro!</b> Tienes el control del ordenador de Lia, pero no hay tiempo que perder. "
			    + "Tu objetivo es el siguiente: hurgar en el sistema y extraer la información clave. Pero antes de empezar recuerda: <br><br>"
			    + "• <b>Explora el equipo:</b> Navega sin miedo por las carpetas y sus contenidos.<br>"
			    + "• <b>Rompe los cerrojos:</b> Te encontrarás con ficheros protegidos por claves."
			    + "Pero no te desesperes, todo lo que necesitas está ahí, igual es solo que no lo ves.<br>"
			    + "• <b>Decide que robar:</b> Cada vez que ecuentres un fichero valioso, tú decides si merece la pena llevártelo o no.<br>"
			    + "• <b>La huida:</b> En cuanto creas que has reunido tods los datos necesarios, extrae "
			    + "el pendrive para dar por terminada la misión. Una vez lo extraigas no hay vuelta atrás. <br><br>"
			    + "IMPORTANTE -> todo puedo ser una pista: <font color='#141e37'>Lia@!2461</font>"
			    + "</body></html>";
		textoInicio.setText(getName());
		textoInicio.setFont(new Font("Consolas", Font.PLAIN, 17));
		panelCentral.add(textoInicio);
		
		// AÑADIR AMBOS ELEMENTOS A LA VENTANA
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(barraSuperior, BorderLayout.NORTH);
	}

}
