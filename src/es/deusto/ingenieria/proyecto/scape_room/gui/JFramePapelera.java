package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JFramePapelera extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal;
	private JPanel pArriba;
	private JPanel pCentral;
	
	private JLabel labelTitulo;
	private JButton btnArchivo;
	private JPanel pArchivo;
	private JLabel labelArchivo;
	
	private ImageIcon imagenPapelera = new ImageIcon("images/escritorio/papelera.png");
	private ImageIcon imagenArbol = new ImageIcon("images/explorador_archivos/familia_fjerda.jpg");
	
	private String rutaImagen = "images/explorador_archivos/familia_fjerda.jpg";

	public JFramePapelera() {
		pPrincipal = new JPanel( new BorderLayout() );
		pArriba = new JPanel( new FlowLayout(FlowLayout.LEFT) );
		pCentral = new JPanel( new FlowLayout(FlowLayout.LEADING) );
		
		labelTitulo = new JLabel("Papelera");
		Image imagenPapeleraEscalada = imagenPapelera.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		labelTitulo.setIcon( new ImageIcon(imagenPapeleraEscalada) );
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		
		pArriba.add(labelTitulo);
		pArriba.setBackground(new Color(220, 220, 220));
		
		pArchivo = new JPanel( new FlowLayout() );
		pArchivo.setOpaque(false);
		btnArchivo = new JButton();
		Image imagenArbolEscalada = imagenArbol.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		btnArchivo.setIcon( new ImageIcon(imagenArbolEscalada) );
		btnArchivo.addActionListener( e -> new JFrameVisualizarImagenes(rutaImagen, this));
		btnArchivo.setOpaque(false);
		btnArchivo.setBackground(Color.white);
		pArchivo.add(btnArchivo);
		labelArchivo = new JLabel("familia_fjerda.jpg");
		labelArchivo.setFont(new Font("Arial", Font.BOLD, 12));
		pArchivo.setPreferredSize(new Dimension(110,100));
		pArchivo.add(labelArchivo);
		pCentral.add(pArchivo);
		
		this.setTitle("Papelera");
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize( new Dimension(900,700) );
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		
		pPrincipal.add(pArriba, BorderLayout.NORTH);
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		this.add(pPrincipal, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFramePapelera papelera = new JFramePapelera();
	}

}
