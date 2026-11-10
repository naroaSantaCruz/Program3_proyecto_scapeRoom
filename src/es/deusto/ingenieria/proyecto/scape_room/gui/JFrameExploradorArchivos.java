package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFrameExploradorArchivos extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pIzquierda;
	private JPanel pCentro;
	private JPanel pSuperior;
	private JPanel pBoton;
	private JPanel pBarraBusqueda;
	
	private JButton btnSuperior;
	private JTextField barraBusqueda;
	
	private ImageIcon flechaArriba = new ImageIcon("images/explorador_archivos/flecha-hacia-arriba.png");

	public JFrameExploradorArchivos() {
		this.setTitle("Explorador de archivos");
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize( new Dimension(900,700) );
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		
		pIzquierda = new JPanel();
		this.add(pIzquierda, BorderLayout.WEST);
		
		pCentro = new JPanel();
		this.add(pCentro, BorderLayout.CENTER);
		
		crearPanelSuperior();
		
		this.setVisible(true);
	}
	
	private void crearPanelSuperior() {
		pSuperior = new JPanel( new GridLayout(1,2) );
		
		// Boton para acceder a carpeta superior
		btnSuperior = new JButton();
		Image imagenFlechaEscalada = flechaArriba.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
		btnSuperior.setIcon( new ImageIcon(imagenFlechaEscalada) );
		btnSuperior.setPreferredSize( new Dimension(20,20) );
		btnSuperior.setContentAreaFilled(false);
		btnSuperior.setBorderPainted(false);
		btnSuperior.setBorder(null);
		btnSuperior.setOpaque(false);
		btnSuperior.setFocusPainted(false);
		pBoton = new JPanel( new FlowLayout(FlowLayout.LEFT) );
		pBoton.add(btnSuperior);
		pBoton.setBorder( BorderFactory.createEmptyBorder(10, 20, 0, 0));
		
		// Barra busqueda
		pBarraBusqueda = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
		barraBusqueda = new JTextField();
		barraBusqueda.setPreferredSize( new Dimension( 200, 30) );
		pBarraBusqueda.setBorder( BorderFactory.createEmptyBorder(10, 0, 0, 20));
		pBarraBusqueda.add(barraBusqueda);
		
		// Añadir elementos a panel y al JFrame
		pSuperior.add(pBoton);
		pSuperior.add(pBarraBusqueda);
		this.add(pSuperior, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		JFrameExploradorArchivos exploradorArchivos = new JFrameExploradorArchivos();
	}

}
