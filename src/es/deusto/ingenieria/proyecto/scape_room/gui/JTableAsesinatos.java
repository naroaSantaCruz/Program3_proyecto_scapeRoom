package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import es.deusto.ingenieria.proyecto.scape_room.domain.Asesinato;
import es.deusto.ingenieria.proyecto.scape_room.domain.Expediente;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.JFrameModeloExpediente;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.JTableAsesinatosModel;

public class JTableAsesinatos extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	
	private String RUTA_FICHERO = "documents/asesinatosSIS.csv";
	
	private Font fuenteHeaders = new Font("Arial",Font.BOLD,17);
	private Font fuenteTexto = new Font("Arial",Font.BOLD,14);

	public JTableAsesinatos() {
		JTableAsesinatosModel modelo = new JTableAsesinatosModel(RUTA_FICHERO);
		tabla = new JTable(modelo);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				tabla.getTableHeader().setPreferredSize(new Dimension(0,50));
				JLabel titulo = new JLabel(value.toString());
				titulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				titulo.setBorder(BorderFactory.createLineBorder(new Color(58, 58, 64), 4));
				titulo.setBackground(new Color(95, 18, 26));
				titulo.setForeground(new Color(242, 242, 242));
				titulo.setFont(fuenteHeaders);
				titulo.setHorizontalAlignment(JLabel.CENTER);
				titulo.setOpaque(true);
				return titulo;
			}
		};
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel result = new JLabel(value.toString());
				result.setFont(fuenteTexto);
				result.setForeground(new Color(218, 218, 222));
				result.setHorizontalAlignment(JLabel.CENTER);
				result.setOpaque(true);
		
				if ( column == 1 ) {
					Asesinato asesinato = (Asesinato) value;
					JPanel panel = new JPanel(new GridLayout(2,1,0,0));
					JLabel cargo = new JLabel(asesinato.getCargo());
					JLabel nombre = new JLabel(asesinato.getMuerto());
					cargo.setFont(new Font("Arial",Font.BOLD,14));
					nombre.setFont(new Font("Arial",Font.ITALIC,13));
					cargo.setForeground(new Color(215, 85, 95));
					nombre.setForeground(Color.WHITE);
					panel.add(cargo);
					panel.add(nombre);
					panel.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
					
					if ( isSelected ) {
						panel.setBackground(tabla.getSelectionBackground());
						panel.setBackground(tabla.getSelectionBackground());
					} else {
						if ( row % 2 == 0 ) {
							panel.setBackground(new Color(26, 26, 28));
						} else {
							panel.setBackground(new Color(38, 38, 42));
						}
					}
					
					return panel;
				} else if ( column == 2 ) {
					result.setForeground(new Color(215, 85, 95));
				} else if ( column == 5 ) {
					JPanel panel = new JPanel();
					panel.setLayout(new GridBagLayout());
					JLabel label = new JLabel(value.toString());
					label.setText(label.getText().toUpperCase());
					if ( value.toString().equals("Completado") ) {
						label.setBackground(new Color(46, 125, 50));
					} else {
						label.setBackground(new Color(183, 28, 28));
					}
					label.setOpaque(true);
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setPreferredSize(new Dimension(150,30));
					label.setForeground(new Color(218, 218, 222));
					label.setBorder(BorderFactory.createCompoundBorder(
						    BorderFactory.createLineBorder(Color.BLACK, 3),
						    BorderFactory.createEmptyBorder(4, 10, 4, 10)));
					panel.add(label);
					panel.setBackground(new Color(38, 38, 42));
					
					if ( isSelected ) {
						panel.setBackground(tabla.getSelectionBackground());
						panel.setBackground(tabla.getSelectionBackground());
					} else {
						if ( row % 2 == 0 ) {
							panel.setBackground(new Color(26, 26, 28));
						} else {
							panel.setBackground(new Color(38, 38, 42));
						}
					}
					
					return panel;
				} else if ( column == 6 ) {
					JPanel panel = new JPanel(new GridBagLayout());
					JButton btnExpediente = new JButton();
					ImageIcon imagenBtn = new ImageIcon("images/pantalla_inicio/icono_inicio_usuario.png");
					Image imagenEscalada = imagenBtn.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
					btnExpediente.setIcon(new ImageIcon(imagenEscalada));
					btnExpediente.setBorderPainted(false);
					btnExpediente.setBorder(null);
					btnExpediente.setOpaque(false);
					btnExpediente.setFocusPainted(false);
					btnExpediente.addActionListener( e -> crearExpediente(value.toString()) );
					panel.add(btnExpediente);
					if ( isSelected ) {
						panel.setBackground(tabla.getSelectionBackground());
						panel.setBackground(tabla.getSelectionBackground());
					} else {
						if ( row % 2 == 0 ) {
							panel.setBackground(new Color(26, 26, 28));
						} else {
							panel.setBackground(new Color(38, 38, 42));
						}
					}
					return panel;
				}
				
				if ( isSelected ) {
					result.setBackground(tabla.getSelectionBackground());
					result.setBackground(tabla.getSelectionBackground());
				} else {
					if ( row % 2 == 0 ) {
						result.setBackground(new Color(26, 26, 28));
					} else {
						result.setBackground(new Color(38, 38, 42));
					}
				}
				
				return result;
			}
		};
		
		tabla.setSelectionBackground(new Color(135, 32, 44));
		tabla.setSelectionForeground(Color.WHITE);
		tabla.setShowGrid(true);
		tabla.setGridColor(new Color(58, 58, 64));
		tabla.setRowHeight(60);
		tabla.getTableHeader().setDefaultRenderer(headerRenderer);
		tabla.setDefaultRenderer(Object.class, renderer);
		
		JScrollPane scrollPanel = new JScrollPane(tabla);
		
		this.setSize(new Dimension(1000,500));
		this.setLayout(new BorderLayout());
		this.add(tabla.getTableHeader(),BorderLayout.NORTH);
		this.add(scrollPanel,BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	private void crearExpediente( String asesino ) {
		System.out.println("boton pulsado");
		int numRandomExpediente = (int) (Math.random() * 9000) + 1000;
		int numRandomCarcel = (int) (Math.random() * 25) + 2;
		Expediente expediente = new Expediente(
				"FICHA PENITENCIARIA Nº " + numRandomExpediente,
				"Centro penitenciario · uso interno",
				"images/pantalla_inicio/icono_inicio_usuario.png",
				asesino,
				"Condena: " + numRandomCarcel + "años",
				"Ubicación: Módulo 4, celda 21",
				"Delito: [CENSURADO]",
				"");
		new JFrameModeloExpediente(expediente);
	}
	
	public static void main(String[] args) {
		JTableAsesinatos asesinatos = new JTableAsesinatos();
	}

}
