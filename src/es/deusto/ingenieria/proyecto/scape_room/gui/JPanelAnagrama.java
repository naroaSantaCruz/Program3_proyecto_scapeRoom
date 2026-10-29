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
	private static final String contraseña="Andarna"; //PENSAR

	public JPanelAnagrama() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setVisible(false);
		
		String [][] frases= {{"restAurantes cerca de mi"},//A
				{"libro alas De sangre"}, //D
				{"billetes de avión"},
				{"caRtelera cines"}, //R
				{"receta facil salmóN al horno"}, //N
				{"estado california"},
				{"sueños de la infancia"},
				{"tiempo eN bilbao"}, //N
				{"recetas fáciles"},
				{"festivo mAñana"}, //A
				{"dónde está Aragón"}, //A
				{"mejores series 2024"}
				};
		String [] columnas= {"Búsquedas recientes"};
		modelo=new DefaultTableModel(frases,columnas) {
			@Override
			public boolean isCellEditable(int fila,int columna) {
				return false;
			}
		};
		tabla=new JTable(modelo);
		this.add(new JScrollPane(tabla),BorderLayout.CENTER);
		tabla.setRowHeight(30);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
	}
	
	public boolean comprobar(String texto) {
		return texto.trim().equalsIgnoreCase(contraseña);
	
	}	

}
