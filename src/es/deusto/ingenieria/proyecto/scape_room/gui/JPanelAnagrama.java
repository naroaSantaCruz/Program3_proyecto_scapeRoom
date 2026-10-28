package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class JPanelAnagrama extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JTable tabla;
	private int filaArrastrada=-1;

	public JPanelAnagrama() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setVisible(false);
		
		String [][] frases= {{"Cosas baratas"},{"Oftalmólogo"},
				{"Cines Artea"},{"Hacer mermelada"},{"Estado California"},
				{"Sueños de la infancia"},
				{"Tiempo en Bilbao"},
				{"Recetas fáciles"},
				{"Mapa del metro"},
				{"Noticias de hoy"},
				{"Mejores series 2024"},
				{"Billetes de avión"}};
		String [] columnas= {"Búsquedas"};
		modelo=new DefaultTableModel(frases,columnas) {
			@Override
			public boolean isCellEditable(int fila,int columna) {
				return false;
			}
		};
		tabla=new JTable(modelo);
		this.add(new JScrollPane(tabla),BorderLayout.CENTER);
		tabla.setTableHeader(null);
		tabla.setRowHeight(30);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		MouseAdapter arrastre=new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				filaArrastrada = tabla.rowAtPoint(e.getPoint());
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int destino = tabla.rowAtPoint(e.getPoint());
				if (filaArrastrada >= 0 && destino >= 0 && destino != filaArrastrada) {
					modelo.moveRow(filaArrastrada, filaArrastrada, destino);
					filaArrastrada = destino;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				filaArrastrada = -1;
			}
		};
		
		tabla.addMouseListener(arrastre);
		tabla.addMouseMotionListener(arrastre);
		
	}
	

}
