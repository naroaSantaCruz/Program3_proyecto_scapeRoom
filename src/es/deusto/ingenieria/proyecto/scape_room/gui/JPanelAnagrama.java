package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class JPanelAnagrama extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String CONTRASENA = "REVOLUCION";

	private DefaultTableModel modelo;
	private JTable tabla;
	private int filaEncima = -1; // fila sobre la que está el ratón

	public JPanelAnagrama() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setVisible(false);

		String[][] frases = {
				{"restaurantes cerCa de mi"},          // C
				{"mejores series 2024"},
				{"hOrario de autobuses"},              // O
				{"caRtelera cines"},                   // R
				{"billetes de aVión"},                 // V
				{"sueños de la infancia"},
				{"sUdaderas baratas"},                 // U
				{"libro aLas de sangre"},              // L
				{"recEtas fáciles"},                   // E
				{"dónde está aragón"},
				{"ofertas de vIajes"},                 // I
				{"tiempo eN bilbao"},                  // N
				{"mejores películas de cOmedia"},      // O
				{"receta fácil salmón al horno"}
		};
		String[] columnas = { "Búsquedas recientes" };
		modelo = new DefaultTableModel(frases, columnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		
		Image imLupa = new ImageIcon("images/anagrama/lupa.png").getImage()
				.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		ImageIcon iconoLupa = new ImageIcon(imLupa);
		

		tabla = new JTable(modelo);
		tabla.setRowHeight(38);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setShowGrid(false);
		tabla.setIntercellSpacing(new Dimension(0, 0));
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tabla.setSelectionBackground(new Color(225, 238, 255));
		tabla.setSelectionForeground(Color.BLACK);
		tabla.setFocusable(false);

		// Cabecera
		JTableHeader cabecera = tabla.getTableHeader();
		cabecera.setFont(new Font("Segoe UI", Font.BOLD, 13));
		cabecera.setBackground(Color.WHITE);
		cabecera.setForeground(new Color(90, 90, 90));
		cabecera.setReorderingAllowed(false);

		// Renderer: margen, línea fina entre filas y resalte al pasar el ratón
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable t, Object valor,
					boolean seleccionada, boolean foco, int fila, int columna) {
				super.getTableCellRendererComponent(t, valor, seleccionada, foco, fila, columna);
				setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(235, 235, 235)),
						BorderFactory.createEmptyBorder(0, 14, 0, 14)));
				setIcon(iconoLupa);
				if (seleccionada) {
					setBackground(new Color(225, 238, 255));
				} else if (fila == filaEncima) {
					setBackground(new Color(243, 247, 252));
				} else {
					setBackground(Color.WHITE);
				}
				return this;
			}
		};
		tabla.getColumnModel().getColumn(0).setCellRenderer(render);

		tabla.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				filaEncima = tabla.rowAtPoint(e.getPoint());
				tabla.repaint();
			}
		});
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				filaEncima = -1;
				tabla.repaint();
			}
		});

		
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));

		this.add(scroll, BorderLayout.CENTER);
	}

	public boolean comprobar(String texto) {
		return texto.trim().equalsIgnoreCase(CONTRASENA);
	}
}