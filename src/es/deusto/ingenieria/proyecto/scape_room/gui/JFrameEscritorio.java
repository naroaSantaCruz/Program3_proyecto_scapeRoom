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
		private JLabel explorador_archivos;
		private JLabel chat;

		// Componentes de la barra
		private JButton bInicio;
		private JTextField barraBusqueda;
		private JPanel panelPendrive;
		private JLabel lblPendrive;
		private JLabel lblSinDispositivos;
		private JButton bPendrive;
		private JButton bFlecha;
		private JLabel reloj;
		private Timer timer;
		private JButton terminal;

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
		crearPanelPendrive();
		eventosBoton();
		configurarVentana();
		JDialogVirus virus = new JDialogVirus();
		//TODO usar hilos cuando sepamos
		Timer timer = new Timer(500, e -> {virus.setVisible(true);});
		timer.setRepeats(false);
		timer.start();
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
		
		Image imExplorador = new ImageIcon("images/escritorio/explorador_archivos.png").getImage()
				.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
		explorador_archivos=new JLabel("Explorador", new ImageIcon(imExplorador), SwingConstants.CENTER);
		explorador_archivos.setHorizontalTextPosition(SwingConstants.CENTER);
		explorador_archivos.setVerticalTextPosition(SwingConstants.BOTTOM);
		explorador_archivos.setForeground(Color.WHITE);
		explorador_archivos.setBounds(20, 110, 90, 80); // 90 px más abajo que la papelera (que está en y=20)
		imagenFondo.add(explorador_archivos);
		
		
		Image imChat = new ImageIcon("images/escritorio/chat.png").getImage()
				.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
		chat=new JLabel("Chat",new ImageIcon(imChat), SwingConstants.CENTER);
		chat.setHorizontalTextPosition(SwingConstants.CENTER);
		chat.setVerticalTextPosition(SwingConstants.BOTTOM);
		chat.setForeground(Color.WHITE);
		chat.setBounds(20, 200, 90, 80); // 90 px más abajo que el explorador de archivos (que está en y=110)
		imagenFondo.add(chat);

		
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
		
		//Barra búsqueda
		barraBusqueda = new JTextField(" Buscar");
		barraBusqueda.setPreferredSize(new Dimension(300,30));
		barraBusqueda.setForeground(Color.gray);
		barraBusqueda.setBackground(Color.white);
		barraBusqueda.setEditable(false);
		panelCentro.add(barraBusqueda);
		
		//Terminal
		Image imTerminal=new ImageIcon("images/escritorio/terminal.png").getImage()
				.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		terminal = new JButton(new ImageIcon(imTerminal));
		terminal.setPreferredSize(new Dimension(40,35));
		terminal.setFocusPainted(false);
		terminal.setContentAreaFilled(false);
		terminal.setBorder(null);
		terminal.setToolTipText("Terminal");
		
		panelCentro.add(terminal);
		
		
		//-----Crear JPanel DERECHA administrador tareas-----
		panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 5));
		panelDerecha.setOpaque(false);
		
		Image imFlecha = new ImageIcon("images/escritorio/flechaPendrive.png").getImage()
		        .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		bFlecha = new JButton(new ImageIcon(imFlecha));
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
	
	
	private void crearPanelPendrive() {
	    panelPendrive = new JPanel(new BorderLayout());
	    panelPendrive.setBackground(new Color(240,240,240));
	    panelPendrive.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    panelPendrive.setVisible(false);

	    Image imPendrive = new ImageIcon("images/escritorio/pendrive.png").getImage()
	            .getScaledInstance(24, 24, Image.SCALE_SMOOTH);
	    lblPendrive = new JLabel(" Pendrive", new ImageIcon(imPendrive), SwingConstants.LEFT);
	    lblPendrive.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	    bPendrive = new JBotonInicio("Extraer pendrive");
	    bPendrive.setVisible(false); // aparece al pulsar el pendrive

	    panelPendrive.add(lblPendrive, BorderLayout.NORTH);
	    panelPendrive.add(bPendrive, BorderLayout.CENTER);

	    imagenFondo.add(panelPendrive, 0);
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
				new JFrameChat();
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
		
		bFlecha.addActionListener(e -> {
		    if (panelPendrive.isVisible()) {
		        panelPendrive.setVisible(false);
		    } else {
		        panelPendrive.setBounds(imagenFondo.getWidth() - 220, imagenFondo.getHeight() - 90, 200, 80);
		        panelPendrive.setVisible(true);
		    }
		});

		// Al pulsar el pendrive, aparece "Extraer pendrive"
		lblPendrive.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        bPendrive.setVisible(true);
		        panelPendrive.revalidate();
		    }
		});

		// Al pulsar "Extraer pendrive": desaparece el pendrive
		bPendrive.addActionListener(e -> {
		    panelPendrive.removeAll();

		    lblSinDispositivos = new JLabel("No hay dispositivos", SwingConstants.CENTER);
		    lblSinDispositivos.setForeground(Color.GRAY);
		    panelPendrive.add(lblSinDispositivos, BorderLayout.CENTER);

		    panelPendrive.revalidate();
		    panelPendrive.repaint();

		    // TODO: marcar en la partida que el pendrive ya no está insertado
		});
		
		//OPCIONES MENÚ INICIO
		
		bApagar.addActionListener(e -> {
			menuDesplegable.setVisible(false);
			int r = JOptionPane.showConfirmDialog(JFrameEscritorio.this,
					"¿Quieres rendirte?", "Apagar", JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_OPTION) {
				JFrameEscritorio.this.dispose();
				new JFrameMenuPrincipal();
		}
	});

		bReiniciar.addActionListener(e -> {
			menuDesplegable.setVisible(false);
			int r = JOptionPane.showConfirmDialog(JFrameEscritorio.this,
					"¿Quieres reiniciar la partida?", "Apagar", JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_OPTION) {
				JFrameEscritorio.this.dispose();
				new JFrameEscritorio();
		}
			
	});

		bSuspender.addActionListener(e -> {
			//Pausar temporizador
			//menuDesplegable.setVisible(false);
			//System.out.println("Suspender");
	});

		bCerrarSesion.addActionListener(e -> {
			//Ir a la pantalla de inicio de sesion, guardando datos
			JFrameEscritorio.this.dispose();
			new JFrameMenuPrincipal();
	});
		//mouse listener para que se cambie el borde
		terminal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				terminal.setBorder(BorderFactory.createLineBorder(new Color(120, 160, 220)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				terminal.setBorder(null);
			}
		});
		
		//action listener para que vaya a el jframe
		terminal.addActionListener(e -> {
			new JFrameTerminal();
		});
		
		//EXPLORADOR ARCHIVOS
		explorador_archivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// efecto de "seleccionado" al pasar por encima
				explorador_archivos.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150)));
			
			}

			@Override
			public void mouseExited(MouseEvent e) {
				explorador_archivos.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // doble clic, como en Windows
					System.out.println("Explorador de archivos abierta");
					// aquí abrirás la ventana del explorador de archivos
				}
			}
		});
		
		chat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// efecto de "seleccionado" al pasar por encima
				chat.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150)));
			
			}

			@Override
			public void mouseExited(MouseEvent e) {
				chat.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // doble clic, como en Windows
					System.out.println("Chat abierto");
					// aquí abrirás la ventana de el chat
				}
			}
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
		this.setUndecorated(true); //para quitar a barra de título
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
