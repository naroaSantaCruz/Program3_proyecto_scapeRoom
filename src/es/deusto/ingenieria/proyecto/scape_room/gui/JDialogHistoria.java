package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import es.deusto.ingenieria.proyecto.scape_room.gui.components.JBotonLore;

public class JDialogHistoria extends JDialog{

	private String[] textoLore = {
			"Bienvenido, Agente.",
			"Como bien sabe, trabaja para el Servicio de Inteligecia Secreta (SIS) de su país, Basgiath.",
			"Hoy es un día complicado para usted, ya que es el encargado de investigar el asesinato de su "
			+ "compañero, Liam.",
			"Desde hace varias semanas ha trabajado con el en una investigación confidencial sobre la"
			+ " mafia Fjerdana, y sus negocios.",
			"¿Puede que Liam se pasara de la ralla investigando y por eso lo mataron?",
			"Eso es lo que debe averiguar.",
			"Analice bien el ordenador de su compañero, descubrá la verdad y entrege a su jefe"
			+ " los documentos que vea relevantes sobre esta investigación.",
			"Tenga cuidado, puede que su vida también peligre."
	};
	private int textoActual = 0;
	private JPanel textoFlotante;
	private JTextArea info;
	private JButton bAnterior;
	private JButton bContinuar;
	private JPanel panelBotones;

	public JDialogHistoria() {
		//configurar ventana principal
		setUndecorated(true);
		setTitle("SIS");
		setSize(450,250);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true); //hace que mientras salga esta pantalla las demas esten bloqueadas
		
		//Crear ventana
		textoFlotante = new JPanel(new BorderLayout());
		textoFlotante.setBackground(Color.BLACK);
		
		textoFlotante.setBorder(BorderFactory.createEmptyBorder(60,10,10,10));
		
		//Añadir texto al centro de la ventana
		info = new JTextArea();
		info.setText(textoLore[textoActual]);
		info.setFont(new Font("Consolas", Font.PLAIN, 15));
		info.setBackground(Color.BLACK);
		info.setForeground(Color.WHITE);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		textoFlotante.add(info, BorderLayout.CENTER);
		
		//Añadir botones
		bAnterior = new JBotonLore("anterior");
		bContinuar = new JBotonLore("continuar");
		
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
					info.setText(textoLore[textoActual]);
					bContinuar.setText("continuar");
				}
				
			}
		
		});
		
		//si hago click en el boton 'continuar' para al siguiente texto
		bContinuar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(bContinuar.getText().equals("START")) {
					JDialogHistoria.this.dispose();
				} else if(textoActual < textoLore.length-1) {
					textoActual++;
					info.setText(textoLore[textoActual]);
					
					if(textoActual == textoLore.length-1) {
						bContinuar.setText("START");
					} 
				}
			}
			
		});
	}
}
