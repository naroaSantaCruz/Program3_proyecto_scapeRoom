package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import es.deusto.ingenieria.proyecto.scape_room.gui.components.JBotonMenu;

public class JFrameMenuPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JButton bJugar;
	private JButton bHistorialPartidas;
	private JButton bSalir;
	private JButton bAyuda;
	private JButton bConfiguracion;
	private JPanel panelDerecho;
	private JPanel panelTitulo;
	private JPanel panelBotones;
	private JPanel panelFooter;
	
	
	private ImageIcon imagenConfig = new ImageIcon("images/menu_opciones_juego/configuracion_no_sel.png");
	private ImageIcon imagenConfigSel = new ImageIcon("images/menu_opciones_juego/config_escalada_sin_fondo.png");
	
	public JFrameMenuPrincipal() {
		crearComponentes();
		eventosDeBoton();
		configurarVentana();
	}
	
	private void crearComponentes() {
		//CREACIÓN DE LOS PANELES 
		panelDerecho = new JPanel(new BorderLayout());
		panelDerecho.setOpaque(false);
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(80, 100, 50, 50));
		
		panelTitulo = new JPanel();
		panelTitulo.setOpaque(false);
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		
		panelBotones = new JPanel(new GridLayout(4,1));
		panelBotones.setOpaque(false);
		
		panelFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelFooter.setOpaque(false);
		panelFooter.setBorder(BorderFactory.createEmptyBorder(50, 20, 30, 35));
		
		//CONTENIDO DEL PANEL DERECHO:
		//-----Imagen / texto del título-----
		titulo = new JLabel("Escribir aqui el título");
		titulo.setFont(new Font("Bahnschrift",Font.BOLD,30));
		titulo.setHorizontalAlignment(JLabel.CENTER);
	
		//-----Botones-----	
		bJugar = new JBotonMenu("JUGAR");
		bHistorialPartidas = new JBotonMenu("HISTORIAL");
		bSalir = new JBotonMenu("SALIR");
		bAyuda = new JBotonMenu("AYUDA");
		
		bConfiguracion = new JButton();
		bConfiguracion.setIcon(imagenConfig);
		bConfiguracion.setOpaque(false);
		bConfiguracion.setContentAreaFilled(false);
		bConfiguracion.setBorder(null);
		bConfiguracion.setPreferredSize(new Dimension(69,70));
		bConfiguracion.setToolTipText("Configuración");
		
		//-------Añadir los componentes a su panel------
		panelTitulo.add(titulo);
		
		panelBotones.add(bJugar);
		panelBotones.add(bHistorialPartidas);
		panelBotones.add(bAyuda);
		panelBotones.add(bSalir);
		
		panelDerecho.add(panelBotones, BorderLayout.CENTER);
		panelDerecho.add(panelTitulo, BorderLayout.NORTH);
		
		panelFooter.add(bConfiguracion);
	}
	
	private void configurarVentana() {
		//------Configuración de la ventana general -------
		JLabel imagenFondo = new JLabel();
		imagenFondo.setIcon(new ImageIcon("images/menu_opciones_juego/fondo_menu_principal.png"));
		imagenFondo.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		imagenFondo.setLayout(new BorderLayout());
		imagenFondo.add(panelDerecho,BorderLayout.WEST);
		imagenFondo.add(panelFooter,BorderLayout.SOUTH);
		
		this.setUndecorated(true);
		this.setContentPane(imagenFondo);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	private void eventosDeBoton() {
		bSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrameMenuPrincipal.this.dispose();
			}	
		});
		
		bConfiguracion.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				bConfiguracion.setIcon(imagenConfigSel);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				bConfiguracion.setIcon(imagenConfig);
			}
			
		});
		
		
	}

}
