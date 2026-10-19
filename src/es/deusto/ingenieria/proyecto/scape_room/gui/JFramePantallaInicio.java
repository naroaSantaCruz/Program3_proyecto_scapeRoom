package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFramePantallaInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel fondo;
	private ImageIcon imagenWindows = new ImageIcon("images/pantalla_inicio/fondo_inicio_windows.jpg");
	private Image imagenEscalada;
	private ImageIcon imagenUsuario = new ImageIcon("images/pantalla_inicio/icono_inicio_usuario.png");
	private JPanel panelCentral;
	private JLabel iconoUsuario;
	private JTextField usuarioTexto;
	private JTextField contraTexto;
	private JPanel footer;
	private JPanel otrosUsuariosPanel;
	private JPanel botonesApagadoPanel;
	private JLabel otrosUsuarios;
	private JLabel botonesApagado;
	
	public JFramePantallaInicio() {
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
		
		panelCentral = new JPanel(new GridBagLayout());
		panelCentral.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		
		iconoUsuario = new JLabel();
		iconoUsuario.setIcon(imagenUsuario);
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 15, 0);
		panelCentral.add(iconoUsuario, gbc);
		
		usuarioTexto = new JTextField("User name");
		usuarioTexto.setHorizontalAlignment(SwingConstants.CENTER);
		usuarioTexto.setPreferredSize(new Dimension(250, 35));
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 8, 0);
		panelCentral.add(usuarioTexto, gbc);
		contraTexto = new JTextField("Password");
		contraTexto.setHorizontalAlignment(SwingConstants.CENTER);
		contraTexto.setPreferredSize(new Dimension(250, 35));
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 0, 0);
		panelCentral.add(contraTexto, gbc);
		
		
		footer = new JPanel(new GridLayout(1,2));
		footer.setOpaque(false);
		otrosUsuariosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		otrosUsuariosPanel.setOpaque(false);
		otrosUsuarios = new JLabel();
		Image imagenPeque = imagenUsuario.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
		otrosUsuarios.setIcon(new ImageIcon(imagenPeque));
		otrosUsuariosPanel.add(otrosUsuarios);
		footer.add(otrosUsuariosPanel);
		
		
		
		fondo.add(panelCentral, BorderLayout.CENTER);
		fondo.add(footer, BorderLayout.SOUTH);
		
	}

	public static void main(String[] args) {
		JFramePantallaInicio pantallaInicio = new JFramePantallaInicio();
		pantallaInicio.setVisible(true);
	}

}
