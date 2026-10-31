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

public class JDialogTextoInicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelCentral;
	private JPanel barraSuperior;
	private JLabel tituloVentana;
	private JButton botonApagado;
	private JLabel textoInicio = new JLabel();
	private Point pMouse;
	
	public JDialogTextoInicio(JFramePantallaInicio pantallaInicio) {
		
		// CONFIGURACIÓN VENTANA MINIMIZABLE
		this.setLayout(new BorderLayout());
		this.setSize(450,250);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setModal(false);
		this.setUndecorated(rootPaneCheckingEnabled);
		
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.BLACK);
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(panelCentral, BorderLayout.CENTER);
		
		barraSuperior = new JPanel(new BorderLayout());
		barraSuperior.setBackground(Color.BLACK);
		tituloVentana = new JLabel();
		tituloVentana.setText("Instrucciones del juego");
		tituloVentana.setForeground(Color.WHITE);
		tituloVentana.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
		botonApagado = new JButton("X");
		botonApagado.setForeground(Color.WHITE);
		botonApagado.setBackground(Color.BLACK);
		botonApagado.setBorderPainted(false);
		botonApagado.setBorder(null);
		botonApagado.setFocusPainted(false);
		botonApagado.setPreferredSize(new Dimension(30,30));
		botonApagado.addActionListener( e -> dispose());
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
		this.add(barraSuperior, BorderLayout.NORTH);
		
				
		// CONFIGURACIÓN TEXTO
		String texto = "Bienvenido al juego... \n" + "Todo puede ser una pista: ";
		textoInicio.setText("<html><center>" + texto + "</center></html>");
		textoInicio.setForeground(Color.WHITE);
		textoInicio.setBorder(null);
		textoInicio.setOpaque(false);
		textoInicio.setFont(new Font("Consolas", Font.PLAIN, 17));
		panelCentral.add(textoInicio);
	}

}
