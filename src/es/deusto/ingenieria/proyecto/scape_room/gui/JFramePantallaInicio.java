package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JFramePantallaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel fondo;
	private ImageIcon imagenWindows = new ImageIcon("images/pantalla_inicio/fondo_inicio_windows.jpg");
	private Image imagenEscalada;
	private ImageIcon imagenUsuario = new ImageIcon("images/pantalla_inicio/circulo-de-usuarios.png");
	private JPanel panelCentral;
	private JLabel iconoUsuario;
	private JTextField usuarioTexto;
	private JPasswordField contraTexto;
	private JPanel footer;
	private JPanel otrosUsuariosPanel;
	private JPanel botonesApagadoPanel;
	private JLabel otrosUsuarios;
	private JButton botonApagado;
	private ImageIcon imagenBotonAgado = new ImageIcon("images/pantalla_inicio/boton-de-encendido (1).png");
	private JButton botonApagar = new JButton("Apagar");
	private JButton botonReiniciar = new JButton("Reiniciar");
	private JButton botonSuspender = new JButton("Suspender");
	private JPanel menuDesplegable;
	private JPanel panelDerecha;
	private JPanel panelVacioDerecha;
	private JPanel panelVacioIzquierda;
	private ImageIcon imagenVentanaTexto = new ImageIcon("images/pantalla_inicio/maximizar.png");
	private JButton botonVentana;
	private JDialogTextoInicio ventanaTextoInicio;
	
	public JFramePantallaInicio() {
		
		// CONFIGURACIÓN DEL FONDO
		this.setTitle("Pantalla Inicio");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        int altoPantalla = tamanoPantalla.height;
 
		imagenEscalada = imagenWindows.getImage().getScaledInstance(
                anchoPantalla, 
                altoPantalla, 
                Image.SCALE_SMOOTH
        );
		
		fondo = new JLabel(new ImageIcon(imagenEscalada));
		this.setContentPane(fondo);
		fondo.setLayout(new BorderLayout());
		
		
		// CONFIGURACIÓN DEL PANEL CENTRAL
		panelCentral = new JPanel(new GridBagLayout());
		panelCentral.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		
		iconoUsuario = new JLabel();
		Image imagenUsuarioEscalada = imagenUsuario.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
		iconoUsuario.setIcon(new ImageIcon(imagenUsuarioEscalada));
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 15, 0);
		panelCentral.add(iconoUsuario, gbc);
		
		usuarioTexto = crearCampoTextoUsuario();
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 8, 0);
		panelCentral.add(usuarioTexto, gbc);
		contraTexto = crearCampoContrasena();
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 0, 0);
		panelCentral.add(contraTexto, gbc);
		usuarioTexto.addActionListener(e -> validarYEntrar());
	    contraTexto.addActionListener(e -> validarYEntrar());
		
	    
	    // CONFIGURACIÓN MENÚ DESPLEGABLE CON OPCIONES APAGAR...
	    menuDesplegable = new JPanel();
	    menuDesplegable.setLayout(new GridLayout(3,1));
	    menuDesplegable.add(botonApagar);
	    menuDesplegable.add(botonReiniciar);
	    menuDesplegable.add(botonSuspender);
	    panelDerecha = new JPanel(new BorderLayout());
	    panelDerecha.add(menuDesplegable,BorderLayout.SOUTH);
	    menuDesplegable.setOpaque(false);
	    panelDerecha.setOpaque(false);
	    botonApagar.addActionListener(e -> {
	    	int opcion = JOptionPane.showConfirmDialog(JFramePantallaInicio.this, "¿Quieres rendirte?",
	    			"Apagar",JOptionPane.YES_NO_OPTION);
	    	if ( opcion == JOptionPane.YES_OPTION ) {
	    		new JFrameMenuPrincipal();
	    		this.dispose();
	    	}
	    });
	    botonReiniciar.addActionListener(e -> {
	    	int opcion = JOptionPane.showConfirmDialog(JFramePantallaInicio.this, "¿Quieres reiniciar?",
	    			"Reiniciar", JOptionPane.YES_NO_OPTION);
	    	if ( opcion == JOptionPane.YES_OPTION ) {
	    		new JFramePantallaInicio();
	    		this.dispose();
	    	}
	    });
	    panelVacioDerecha = new JPanel();
	    panelVacioDerecha.setOpaque(false);
	    panelVacioDerecha.setPreferredSize(menuDesplegable.getPreferredSize());
	    panelDerecha.add(panelVacioDerecha, BorderLayout.CENTER);
	    panelVacioIzquierda = new JPanel();
	    panelVacioIzquierda.setOpaque(false);
	    panelVacioIzquierda.setPreferredSize(menuDesplegable.getPreferredSize());
	    menuDesplegable.setVisible(false);
		
	    
		// CONFIGURACIÓN DEL FOOTER
		footer = new JPanel(new BorderLayout());
		footer.setOpaque(false);
		
		otrosUsuariosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		otrosUsuariosPanel.setOpaque(false);
		otrosUsuarios = new JLabel();
		Image imagenPeque = imagenUsuario.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
		otrosUsuarios.setIcon(new ImageIcon(imagenPeque));
		otrosUsuarios.setText("User");
		otrosUsuarios.setForeground(Color.WHITE);
		otrosUsuarios.setFont(new Font("Arial", Font.BOLD, 20));
		otrosUsuarios.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		otrosUsuarios.setIconTextGap(10);
		otrosUsuariosPanel.add(otrosUsuarios);
		footer.add(otrosUsuariosPanel, BorderLayout.WEST);
		
		botonesApagadoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		botonesApagadoPanel.setOpaque(false);
		botonApagado = new JButton();
		botonApagado.setContentAreaFilled(false);
		botonApagado.setBorderPainted(false);
		botonApagado.setBorder(null);
		Image imagenPeque2 = imagenBotonAgado.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
		botonApagado.setIcon(new ImageIcon(imagenPeque2));
		botonApagado.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		footer.add(botonesApagadoPanel, BorderLayout.EAST);
		botonApagado.addActionListener(e -> {
			menuDesplegable.setVisible(!menuDesplegable.isVisible());
		});
		
		botonVentana = new JButton();
		ventanaTextoInicio = new JDialogTextoInicio(this);
		//ventanaTextoInicio.setVisible(true);
		botonVentana.addActionListener(e -> {
			ventanaTextoInicio.setVisible(!ventanaTextoInicio.isVisible());
		});
		Image imagenPeque3 = imagenVentanaTexto.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
		botonVentana.setIcon(new ImageIcon(imagenPeque3));
		botonesApagadoPanel.add(botonVentana);
		botonesApagadoPanel.add(botonApagado);
		
		
		// AÑADIR AMBOS PANELES CENTRALES, LATERALES Y FOOTER
		fondo.add(panelVacioIzquierda,BorderLayout.WEST);
	    fondo.add(panelDerecha,BorderLayout.EAST);
		fondo.add(panelCentral, BorderLayout.CENTER);
		fondo.add(footer, BorderLayout.SOUTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public JTextField crearCampoTextoUsuario() {
		JTextField campoUsuario = new JTextField();
		campoUsuario.setPreferredSize(new Dimension(250, 35));
		campoUsuario.setLayout(new BorderLayout());
		JLabel textoFondo = new JLabel("User name");
		campoUsuario.setFont(textoFondo.getFont());
		textoFondo.setForeground(Color.GRAY);
		textoFondo.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		campoUsuario.add(textoFondo,BorderLayout.CENTER);
		campoUsuario.getDocument().addDocumentListener( new DocumentListener() {
			/**
			 * Compruba si el texto dónde escribir el usuario está "vacio" o no se ha editado. Si está vació devuelve el texto
			 * predeterminado del texto de fondo (hace visible ese JLabel), si en el campo de texto se ha escrito algo se oculta
			 * el JLabel del texto de fondo y se visualiza el texto que esté escribiendo el usuario.
			 */
			public void comprobarSiTextoVacio() {
				textoFondo.setVisible(campoUsuario.getText().isEmpty());
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				comprobarSiTextoVacio();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				comprobarSiTextoVacio();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				comprobarSiTextoVacio();
			}
		});
		textoFondo.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
		        campoUsuario.requestFocusInWindow();
		    }
		});
		return campoUsuario;
	}
	
	public JPasswordField crearCampoContrasena() {
		JPasswordField contra = new JPasswordField();
		contra.setPreferredSize(new Dimension(250, 35));
	    contra.setLayout(new BorderLayout());
	    JLabel textoFondo = new JLabel("Password");
	    contra.setFont(textoFondo.getFont());
	    textoFondo.setForeground(Color.GRAY);
	    textoFondo.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
	    contra.add(textoFondo,BorderLayout.CENTER);
	    contra.getDocument().addDocumentListener(new DocumentListener() {
	    	private void comprobarSiTextoVacio() {
	            // Se usa getPassword().length == 0 para saber si está vacío
	            boolean estaVacio = contra.getPassword().length == 0;
	            textoFondo.setVisible(estaVacio);
	        }
			@Override
			public void removeUpdate(DocumentEvent e) {
				comprobarSiTextoVacio();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				comprobarSiTextoVacio();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				comprobarSiTextoVacio();
			}
		});
	    textoFondo.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            contra.requestFocusInWindow();
	        }
	    });
		return contra;
	}
	
	public void validarYEntrar() {
		String usuarioIngresado = usuarioTexto.getText().trim();
	    char[] contraChars = contraTexto.getPassword();
	    String contraIngresada = new String(contraChars);
	    if (usuarioIngresado.isEmpty() || contraIngresada.length() == 0) {
	        JOptionPane.showMessageDialog(this, "Introduce valores en los campos del usuario y la contraseña",
	        		"Campos incompletos",JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    if (usuarioIngresado.equals("user") && contraIngresada.equals("password")) {
	        JFrameEscritorio escritorio = new JFrameEscritorio();
	        this.dispose();
	        
	    } else {
	        JOptionPane.showMessageDialog(this,"Usuario o contraseña incorrectos","Error de autenticación",JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public static void main(String[] args) {
		JFramePantallaInicio ventanaInicio = new JFramePantallaInicio();
	}

}
