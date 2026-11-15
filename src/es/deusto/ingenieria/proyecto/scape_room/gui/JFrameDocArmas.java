package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class JFrameDocArmas extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTableArmasModel modelArmas = new JTableArmasModel("documents/armasSis.csv");
	private JTable tablaArmasSis;
	private JScrollPane scrollTabla;
	private String[] header = {"Código", "Nombre", "Imagen", "Calibre", "Descripción"};

	public JFrameDocArmas() {
		
		tablaArmasSis = new JTable(modelArmas);
		scrollTabla = new JScrollPane(tablaArmasSis);
		scrollTabla.setBorder(new EmptyBorder(30,30,0,30));
		
		//renderer de la tabla
		TableCellRenderer armasRenderer = new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel result = new JLabel();
				
				switch (column) {
				case 0: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
				} case 1: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
				} case 2: {
					ImageIcon foto1 = new ImageIcon(value.toString());
					Image redi = foto1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					ImageIcon foto2 = new ImageIcon(redi);
					result.setIcon(foto2);
				} case 3: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
				} case 4: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
				}
				}
				return result;
			}
		};
		
		//renderer de la cabecera
		TableCellRenderer armasHeaderRendere = new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel result = new JLabel();
				
				return result;
			}
		};
		
		tablaArmasSis.setRowHeight(200);
		tablaArmasSis.setDefaultRenderer(Object.class, armasRenderer);
		
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(1300,700));
		this.setLayout(new BorderLayout());
		this.add(scrollTabla, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setTitle("Registro_armas_SIS.doc");
		this.setVisible(true);
	}

}
