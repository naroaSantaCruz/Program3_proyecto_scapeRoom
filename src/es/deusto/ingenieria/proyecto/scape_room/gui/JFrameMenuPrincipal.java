package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

public class JFrameMenuPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JButton bContinuarPartida;
	private JButton bNuevaPartida;
	private JButton bHistorialPartidas;
	private JButton bSalir;
	private JButton bConfiguracion;
	private JPanel panelDerecho;
	private JPanel panelTitulo;
	private JPanel panelBotones;
	private final ImageIcon IMAGEN_BOTON_ORI = new ImageIcon("images/menu_opciones_juego/boton_no_seleccionado.png");
	private final ImageIcon IMAGEN_BOTON_SEL_ORI = new ImageIcon("images/menu_opciones_juego/boton_seleccionado.png");
	private final Dimension dimensionBoton = new Dimension(300, 65);
	
	public JFrameMenuPrincipal() {
		//Escalar la imegn del boton:
		
		Image botonImgEscalada = IMAGEN_BOTON_ORI.getImage().getScaledInstance(480, 155, Image.SCALE_SMOOTH);
		ImageIcon IMAGEN_BOTON = new ImageIcon(botonImgEscalada);
		
		Image botonImgEscaladaSel = IMAGEN_BOTON_SEL_ORI.getImage().getScaledInstance(330, 125, Image.SCALE_SMOOTH);
		ImageIcon IMAGEN_BOTON_SEL = new ImageIcon(botonImgEscaladaSel);
		
		panelDerecho = new JPanel(new BorderLayout());
		panelDerecho.setOpaque(false);
		panelTitulo = new JPanel();
		panelTitulo.setOpaque(false);
		
		panelBotones = new JPanel();
		panelBotones.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		panelBotones.setOpaque(false);
		panelBotones.setBorder(BorderFactory.createEmptyBorder(100, 150, 30, 100));
		
		bContinuarPartida = new JButton();
		bContinuarPartida.setIcon(IMAGEN_BOTON);
		bContinuarPartida.setPreferredSize(dimensionBoton);
		bContinuarPartida.setBorder(null);
		bContinuarPartida.setContentAreaFilled(false);
		bContinuarPartida.setOpaque(false);
		bContinuarPartida.setText("CONTINUAR");
		bContinuarPartida.setFont(new Font("Bahnschrift",Font.BOLD,23));
		bContinuarPartida.setForeground(new Color(170,235,255));
		bContinuarPartida.setHorizontalTextPosition(JButton.CENTER);
		bContinuarPartida.setVerticalTextPosition(JButton.CENTER);
		
		//TODO hacer una clase boton nueva para configurarlo (meterle el listemer para mouseEntered y exited)
		bContinuarPartida.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO que te lleve a la ventana que toque
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				bContinuarPartida.setIcon(IMAGEN_BOTON_SEL);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				bContinuarPartida.setIcon(IMAGEN_BOTON);
			}
			
		});		
		
		panelBotones.add(bContinuarPartida);
		
		panelDerecho.add(panelBotones, BorderLayout.CENTER);
		panelDerecho.add(panelTitulo, BorderLayout.NORTH);
		
		JLabel imagenFondo = new JLabel();
		imagenFondo.setIcon(new ImageIcon("images/menu_opciones_juego/fondo_menu_principal.png"));
		imagenFondo.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		imagenFondo.setLayout(new BorderLayout());
		imagenFondo.add(panelDerecho,BorderLayout.WEST);
		
		//this.setUndecorated(true);
		this.setContentPane(imagenFondo);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

}
