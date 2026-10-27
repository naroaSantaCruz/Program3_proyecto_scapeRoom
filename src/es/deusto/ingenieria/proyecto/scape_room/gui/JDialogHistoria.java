package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import es.deusto.ingenieria.proyecto.scape_room.gui.components.BordePanelesMenu;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.BordeVentana;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.JBotonLore;

public class JDialogHistoria extends JDialog{

	private String[] textoLore = {
			"Bienvenido, Agente.",
			"Como bien sabe, trabaja para el Servicio de Inteligecia Secreta (SIS) de su país, Basgiath.",
			"Hoy es un día complicado para usted, ya que es el encargado de investigar el asesinato de su "
			+ "compañera, Lia.",
			"Desde hace varias semanas ha trabajado con el en una investigación confidencial sobre la"
			+ " mafia Fjerdana, y sus negocios.",
			"¿Puede que Lia se pasara de la ralla investigando y por eso la mataron?",
			"Eso es lo que debe averiguar.",
			"Analice bien el ordenador de su compañera, descubrá la verdad y entrege a su jefe"
			+ " los documentos que vea relevantes sobre esta investigación.",
			"Tenga cuidado, puede que su vida también peligre."
	};
	private int textoActual = 0;
	private JPanel textoFlotante;
	private JLabel info;
	private JButton bAnterior;
	private JButton bContinuar;
	private JPanel panelBotones;

	public JDialogHistoria() {
		//configurar ventana principal
		setUndecorated(true);
		setTitle("SIS");
		setSize(450,250);
		setLocationRelativeTo(null);
		setBackground(new Color(0,0,0,0));
		setResizable(false);
		setModal(true); //hace que mientras salga esta pantalla las demas esten bloqueadas
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Crear ventana
		textoFlotante = new JPanel(new BorderLayout());
		textoFlotante.setOpaque(false);
		textoFlotante.setBorder(new BordeVentana());
		
		//Añadir texto al centro de la ventana
		info = new JLabel();
		info.setText("<html><center>" + textoLore[textoActual] + "</center></html>");
		info.setFont(new Font("Consolas", Font.PLAIN, 17));
		info.setForeground(Color.WHITE);

		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setVerticalAlignment(SwingConstants.CENTER);
		info.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		textoFlotante.add(info, BorderLayout.CENTER);
		
		//Añadir botones
		bAnterior = new JBotonLore("anterior");
		bContinuar = new JBotonLore("continuar");
		bAnterior.setVisible(false);
		
		panelBotones = new JPanel(new BorderLayout());
		panelBotones.add(bAnterior, BorderLayout.WEST);
		panelBotones.add(bContinuar, BorderLayout.EAST);
		panelBotones.setOpaque(false);
		
		textoFlotante.add(panelBotones, BorderLayout.SOUTH);
		
		this.add(textoFlotante);
		
		eventosBoton();
		
		this.setVisible(true);
		
	}
	
	public void eventosBoton() {
		//si hago click en el boton 'anterior' muestra el texto anterior
		bAnterior.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(textoActual>0) {
					textoActual--;
					info.setText("<html><center>" + textoLore[textoActual] + "</center></html>");
					bContinuar.setText("continuar");
					if(textoActual==0) bAnterior.setVisible(false);
				} 
				
			}
		
		});
		
		//si hago click en el boton 'continuar' para al siguiente texto
		bContinuar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {				
				if(bContinuar.getText().equals("START")) {
					JDialogHistoria.this.dispose();
					JFramePantallaInicio pantantallaInicio = new JFramePantallaInicio();
					
				} else if(textoActual < textoLore.length-1) {
					textoActual++;
					info.setText("<html><center>" + textoLore[textoActual] + "</center></html>");
					bAnterior.setVisible(true);
					
					if(textoActual == textoLore.length-1) {
						bContinuar.setText("START");
					} 
				}
			}
			
		});
	}
}
