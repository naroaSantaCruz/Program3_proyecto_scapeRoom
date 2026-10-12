package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFrameEscritorio extends JFrame{

	private static final long serialVersionUID = 1L;

	public JFrameEscritorio() {
		// TODO Auto-generated constructor stub
		JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
		
		JLabel imagenFondo = new JLabel();
		imagenFondo.setIcon(new ImageIcon("images/escritorio/fondo_windows.jpg"));
		imagenFondo.setLayout(null);
		Image imPapelera = new ImageIcon("images/escritorio/papelera.png").getImage()
				.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
		JLabel papelera = new JLabel("Papelera",new ImageIcon(imPapelera),SwingConstants.CENTER);
		papelera.setHorizontalTextPosition(SwingConstants.CENTER);
		papelera.setVerticalTextPosition(SwingConstants.BOTTOM);
		papelera.setForeground(Color.white);
		papelera.setBounds(20, 20, 90, 80);
		
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
		
		imagenFondo.add(papelera);

		
		//barra administrador tareas
		JPanel barraAbajo = new JPanel(new BorderLayout());
		JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panelCentro.setOpaque(false);

		
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
		panelCentro.add(barraBusqueda);
		
		JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 5));
		panelDerecha.setOpaque(false);
		
		JButton bFlecha = new JButton("^"); //hacer algo con pendrive?
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
		
		barraAbajo.add(panelCentro, BorderLayout.CENTER);
		barraAbajo.add(panelDerecha, BorderLayout.EAST);
		
		//Fecha y hora
		/*JLabel reloj = new JLabel();
		Timer timer = new Timer(1000, e -> {
			LocalDateTime ahora = LocalDateTime.now();
			reloj.setText("<html><center>" + ahora.format(DateTimeFormatter.ofPattern("HH:mm"))
					+ "<br>" + ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</center></html>");
		});
		timer.setInitialDelay(0);
		timer.start();
		panelDerecha.add(reloj);*/
		
		
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
