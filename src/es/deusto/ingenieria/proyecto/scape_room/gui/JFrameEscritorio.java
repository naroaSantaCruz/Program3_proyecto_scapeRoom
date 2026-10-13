package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import es.deusto.ingenieria.proyecto.scape_room.gui.components.JBotonInicio;

public class JFrameEscritorio extends JFrame{

	private static final long serialVersionUID = 1L;
		// Paneles
		private JPanel panelPrincipal;
		private JLabel imagenFondo;
		private JPanel barraAbajo;
		private JPanel panelIzq;
		private JPanel panelCentro;
		private JPanel panelDerecha;
		private JPanel menuDesplegable;

		// Componentes del escritorio
		private JLabel papelera;

		// Componentes de la barra
		private JButton bInicio;
		private JTextField barraBusqueda;
		private JButton bFlecha;
		private JLabel reloj;
		private Timer timer;

		// Botones del menú de inicio
		private JButton bApagar;
		private JButton bReiniciar;
		private JButton bSuspender;
		private JButton bCerrarSesion;

	
	public JFrameEscritorio() {
		// TODO Auto-generated constructor stub
		crearFondo();
		crearBarraAbajo();
		crearMenuInicio();
		eventosBoton();
		configurarVentana();
	}

	
	//----------------------------------
	//FONDO Y ESCRITORIO
	//----------------------------------
		
	private void crearFondo() {
		panelPrincipal = new JPanel(new BorderLayout());
		imagenFondo = new JLabel();
		imagenFondo.setIcon(new ImageIcon("images/escritorio/fondo_windows.jpg"));
		imagenFondo.setLayout(null);
		
		//PAPELERA
		Image imPapelera = new ImageIcon("images/escritorio/papelera.png").getImage()
				.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
		papelera = new JLabel("Papelera",new ImageIcon(imPapelera),SwingConstants.CENTER);
		papelera.setHorizontalTextPosition(SwingConstants.CENTER);
		papelera.setVerticalTextPosition(SwingConstants.BOTTOM);
		papelera.setForeground(Color.white);
		papelera.setBounds(20, 20, 90, 80);
		
		
		imagenFondo.add(papelera);

	}
	
	// -----------------------
	// BARRA ADMINISTRADOR TAREAS (inicio | apps | opciones)
	// -----------------------
	
	private void crearBarraAbajo() {
		
		barraAbajo = new JPanel(new BorderLayout());
		
		//-----Crear JPanel IZQUIERDA administrador de tareas-----
		panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
		panelIzq.setOpaque(false);
		
		Image imgLogo = new ImageIcon("images/escritorio/logo_windows.png").getImage()
						.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		bInicio = new JButton(new ImageIcon(imgLogo));
		bInicio.setFocusPainted(false);
		bInicio.setContentAreaFilled(false);
		bInicio.setBorderPainted(false);
		
		panelIzq.add(bInicio);
		
		
		//-----Crear JPanel CENTRO administrador de tareas-----
		panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panelCentro.setOpaque(false);
		
		barraBusqueda = new JTextField(" Buscar");
		barraBusqueda.setPreferredSize(new Dimension(300,30));
		barraBusqueda.setForeground(Color.gray);
		barraBusqueda.setBackground(Color.white);
		barraBusqueda.setEditable(false);
		
		panelCentro.add(barraBusqueda);
		
		
		
		//-----Crear JPanel DERECHA administrador tareas-----
		panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 5));
		panelDerecha.setOpaque(false);
		
		bFlecha = new JButton("^"); //hacer algo con pendrive-->abrir menu, expulsar pendrive
		bFlecha.setFocusPainted(false);
		bFlecha.setContentAreaFilled(false);
		bFlecha.setBorderPainted(false);
		panelDerecha.add(bFlecha);
		
		String [] nombres= {"wifi","volumen","bateria"};
		for (String nombre : nombres) {
			Image img = new ImageIcon("images/escritorio/" + nombre + ".png").getImage()
					.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			JLabel icono = new JLabel(new ImageIcon(img));
			icono.setToolTipText(nombre); // texto al pasar el ratón
			panelDerecha.add(icono);
		}
		
		//Fecha y hora
		reloj = new JLabel();
		timer = new Timer(1000, e -> {
			LocalDateTime ahora = LocalDateTime.now();
			reloj.setText("<html><center>" + ahora.format(DateTimeFormatter.ofPattern("HH:mm"))
					+ "<br>" + ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</center></html>");
		});
		timer.setInitialDelay(0);
		timer.start();
		panelDerecha.add(reloj);
		
		//----Montar la barra----
		barraAbajo.add(panelIzq,BorderLayout.WEST);
		barraAbajo.add(panelCentro, BorderLayout.CENTER);
		barraAbajo.add(panelDerecha, BorderLayout.EAST);
		
	}
	
	// ----------------------------------------
	// MENÚ DE INICIO (apagar, reiniciar...)
	// ----------------------------------------
	
	private void crearMenuInicio() {
		
		menuDesplegable = new JPanel(new GridLayout(4,1,0,2));
		menuDesplegable.setBackground(new Color(240,240,240));
		menuDesplegable.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		menuDesplegable.setVisible(false);
		
		
		bApagar = new JBotonInicio("Apagar");
		bReiniciar = new JBotonInicio("Reiniciar");
		bSuspender = new JBotonInicio("Suspender");
		bCerrarSesion = new JBotonInicio("Cerrar sesión");
		
		
		menuDesplegable.add(bApagar);
		menuDesplegable.add(bReiniciar);
		menuDesplegable.add(bSuspender);
		menuDesplegable.add(bCerrarSesion);

		imagenFondo.add(menuDesplegable, 0);
		
	}
	
	// ----------------------------
	// EVENTOS
	// ----------------------------
	
	private void eventosBoton() {
		
		//PAPELERA
		
		papelera.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			// efecto de "seleccionado" al pasar por encima
			papelera.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150)));
		
		}

		@Override
		public void mouseExited(MouseEvent e) {
			papelera.setBorder(null);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { // doble clic, como en Windows
				System.out.println("Papelera abierta");
				// aquí abrirás la ventana de la papelera
			}
		}
	});
		
		//BARRA BUSQUEDA
		
		barraBusqueda.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			// al pasar por encima de la barra
			barraBusqueda.setBackground(new Color(230, 240, 255));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// al salir de la barra
			barraBusqueda.setBackground(Color.WHITE);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// al pulsar
			System.out.println("Barra pulsada");
			//abrir pestaña?
		}
		
	});
		
		
		//MENÚ DESPLEGABLE (botón logo)
		
		bInicio.addActionListener(e -> {
			if (menuDesplegable.isVisible()) {
				menuDesplegable.setVisible(false);
			} else {
				menuDesplegable.setBounds(10, imagenFondo.getHeight() - 150, 180, 140);
				menuDesplegable.setVisible(true);
		}
	});
			
		
		// FLECHA PENDRIVE
		
		bFlecha.addActionListener(ev -> {
			System.out.println("Flecha pulsada");
			// TODO pendrives
	});
		
		
		//OPCIONES MENÚ INICIO
		
		bApagar.addActionListener(e -> {
			menuDesplegable.setVisible(false);
			int r = JOptionPane.showConfirmDialog(JFrameEscritorio.this,
					"¿Quieres apagar el equipo?", "Apagar", JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_OPTION) {
				//poner advertencia?
				//System.exit(0);
		}
	});

		bReiniciar.addActionListener(e -> {
			//Mirar q hacer
			//JFrameEscritorio.this.dispose();
			//new JFrameEscritorio();
	});

		bSuspender.addActionListener(e -> {
			//Mirar q hacer
			//menuDesplegable.setVisible(false);
			//System.out.println("Suspender");
	});

		bCerrarSesion.addActionListener(e -> {
			//Ir a la pantalla de inicio de sesion
			JFrameEscritorio.this.dispose();
			new JFrameMenuPrincipal();
	});
}


	// --------------------------------
	// VENTANA
	// --------------------------------
	
	private void configurarVentana() {
		panelPrincipal.add(imagenFondo,BorderLayout.CENTER);
		panelPrincipal.add(barraAbajo,BorderLayout.SOUTH);
		this.add(panelPrincipal);
		
		this.setTitle("Escritorio");
		// this.setUndecorated(true); para quitar a barra de título
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
