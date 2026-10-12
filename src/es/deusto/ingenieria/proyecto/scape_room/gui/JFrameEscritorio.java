package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFrameEscritorio extends JFrame{

	private static final long serialVersionUID = 1L;

	public JFrameEscritorio() {
		// TODO Auto-generated constructor stub
		JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
		
		JLabel imagenFondo = new JLabel();
		imagenFondo.setIcon(new ImageIcon("images/escritorio/fondo_windows.jpg"));
		imagenFondo.setLayout(new BorderLayout());
		
		
		//barra administrador tareas
		JPanel barraAbajo = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
		JTextField barraBusqueda = new JTextField(" Buscar");
		barraBusqueda.setPreferredSize(new Dimension(300,30));
		barraBusqueda.setForeground(Color.gray);
		barraBusqueda.setBackground(Color.white);
		barraBusqueda.setEditable(false);
		
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
		barraAbajo.add(barraBusqueda);
		
		panelPrincipal.add(imagenFondo,BorderLayout.CENTER);
		panelPrincipal.add(barraAbajo,BorderLayout.SOUTH);
		
		add(panelPrincipal);
		
		setTitle("Escritorio");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
