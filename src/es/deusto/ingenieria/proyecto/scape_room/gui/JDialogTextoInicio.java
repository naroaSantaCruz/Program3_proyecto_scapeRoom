package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class JDialogTextoInicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelCentral;
	private JPanel barraSuperior;
	private JLabel tituloVentana;
	private JButton botonApagado;
	private JTextPane textoInicio;
	private Point pMouse;
	private JLabel fondo;
	private Image imagenEscalada;
	private ImageIcon imagen = new ImageIcon("images/cargar_partidas/bordePanel.png");
	
	public JDialogTextoInicio(JFramePantallaInicio pantallaInicio) {
		
		// CONFIGURACIÓN VENTANA MINIMIZABLE
		this.setSize(700,475);
		this.setLocationRelativeTo(pantallaInicio);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setModal(false);
		this.setUndecorated(true);
		imagenEscalada = imagen.getImage().getScaledInstance(700,475,Image.SCALE_SMOOTH);
		fondo = new JLabel( new ImageIcon(imagenEscalada) );
		fondo.setLayout(new BorderLayout());
		this.add(fondo);
		
		// CONFIGURACIÓN PANEL CENTRAL
		panelCentral = new JPanel( new BorderLayout() );
		panelCentral.setOpaque(false);
		
		// CONFIGURACIÓN BARRA SUPERIOR
		barraSuperior = new JPanel(new BorderLayout());
		barraSuperior.setOpaque(false);
		
		tituloVentana = new JLabel();
		tituloVentana.setText("Instrucciones del juego");
		tituloVentana.setForeground(new Color(0, 0, 128));
		tituloVentana.setOpaque(false);
		tituloVentana.setFont(new Font("Consolas", Font.BOLD, 14));
		tituloVentana.setBorder(BorderFactory.createEmptyBorder(35, 40, 0, 0));
		
		botonApagado = new JButton();
		botonApagado.setText("X");
		botonApagado.setForeground(new Color(20, 30, 55));
		botonApagado.setBackground( new Color(0, 0, 128) );
		botonApagado.setOpaque(false);
		botonApagado.setFont(new Font("Consolas", Font.BOLD, 14));
		botonApagado.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 40));
		botonApagado.setBorderPainted(false);
		botonApagado.setFocusPainted(false);
		botonApagado.setPreferredSize(new Dimension(50,30));
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
		textoInicio.setFocusable(true);
		textoInicio.setSelectionColor(Color.WHITE);
		textoInicio.setSelectedTextColor(Color.BLACK);
		String contenidoHTML = "<html><body style='font-family: Consolas; font-size: 17pt; color: #000080;'>"
			    + "<b>¡Bienvenido jugador, ya estás dentro!</b> Tienes el control del ordenador de Lia, pero no hay tiempo que perder. "
			    + "Tu objetivo es el siguiente: hurgar en el sistema y extraer la información clave. Pero antes de empezar recuerda: <br><br>"
			    + "• <b>Explora el equipo:</b> Navega sin miedo por las carpetas y sus contenidos.<br>"
			    + "• <b>Rompe los cerrojos:</b> Te encontrarás con ficheros protegidos por claves."
			    + "Pero no te desesperes, todo lo que necesitas está ahí, igual es solo que no lo ves.<br>"
			    + "• <b>Decide que robar:</b> Cada vez que ecuentres un fichero valioso, tú decides si merece la pena llevártelo o no.<br>"
			    + "• <b>La huida:</b> En cuanto creas que has reunido tods los datos necesarios, extrae "
			    + "el pendrive para dar por terminada la misión. Una vez lo extraigas no hay vuelta atrás. <br><br>"
			    + "IMPORTANTE -> todo puedo ser una pista o no... <font color='#EAEAEA'>L1@!2461</font>"
			    + "</body></html>";
		textoInicio.setText(contenidoHTML);
		textoInicio.setPreferredSize(new Dimension(645, 400));
		textoInicio.setSelectedTextColor(Color.BLACK);
		textoInicio.setSelectionColor(Color.WHITE);
		textoInicio.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 10));
		panelCentral.add(textoInicio, BorderLayout.CENTER);
		
		// AÑADIR AMBOS ELEMENTOS A LA VENTANA
		fondo.add(panelCentral, BorderLayout.CENTER);
		fondo.add(barraSuperior, BorderLayout.NORTH);
	}

}
