package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import es.deusto.ingenieria.proyecto.scape_room.gui.components.JTableArmasModel;

public class JFrameDocArmasFj extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JTableArmasModel modelArmas = new JTableArmasModel("documents/armasFj.csv");
	private JTable tableArmasFj;
	private JScrollPane scrollTabla;

	public JFrameDocArmasFj() {
		tableArmasFj = new JTable(modelArmas);
		scrollTabla = new JScrollPane(tableArmasFj);
		scrollTabla.setBorder(new EmptyBorder(60,30,0,30));
		scrollTabla.setBackground(new Color(244,255,221));
		
		//renderer de la tabla
		TableCellRenderer armasRenderer = new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel result = new JLabel();
				result.setHorizontalAlignment(SwingConstants.CENTER);
				
				switch (column) {
				case 0: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
					break;
				} case 1: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
					break;
				} case 2: {
					ImageIcon foto1 = new ImageIcon(value.toString());
					Image redi = foto1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					ImageIcon foto2 = new ImageIcon(redi);
					result.setIcon(foto2);
					result.setText("");
					result.setToolTipText("Ver foto");
					break;
				} case 3: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
					break;
				} case 4: {
					result.setText(value.toString());
					result.setForeground(Color.DARK_GRAY);
					result.setFont(new Font("Verdana", Font.PLAIN, 16));
					break;
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
				
				result.setText(value.toString());
				result.setFont(new Font("Monospaced", Font.BOLD, 18));
				result.setForeground(Color.WHITE);
				result.setHorizontalAlignment(SwingConstants.CENTER);
				
				return result;
			}
		};
		
		tableArmasFj.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tableArmasFj.rowAtPoint(e.getPoint());
				int column = tableArmasFj.columnAtPoint(e.getPoint());
				if(column == 2) {
					JFrame fotoAmpliada = new JFrame();
					JLabel lblFoto = new JLabel();
					fotoAmpliada.setTitle(tableArmasFj.getValueAt(row, 1).toString());
					ImageIcon foto1 = new ImageIcon(tableArmasFj.getValueAt(row, column).toString());
					Image amp = foto1.getImage().getScaledInstance(500,500, Image.SCALE_SMOOTH);
					ImageIcon fotoAmp = new ImageIcon(amp);
					lblFoto.setIcon(fotoAmp);
					lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
					lblFoto.setVerticalAlignment(SwingConstants.CENTER);
					lblFoto.setBackground(Color.DARK_GRAY);
					lblFoto.setOpaque(true);
					fotoAmpliada.add(lblFoto,BorderLayout.CENTER);
					fotoAmpliada.setSize(new Dimension(600,600));
					fotoAmpliada.setLocationRelativeTo(null);
					fotoAmpliada.setVisible(true);
					fotoAmpliada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
			
		});
		
		tableArmasFj.setRowHeight(200);
		tableArmasFj.setDefaultRenderer(Object.class, armasRenderer);
		tableArmasFj.setBorder(new LineBorder(Color.BLACK));
		
		tableArmasFj.getTableHeader().setDefaultRenderer(armasHeaderRendere);
		tableArmasFj.getTableHeader().setBorder(new LineBorder(Color.BLACK));
		tableArmasFj.getTableHeader().setBackground(new Color(127,150,112));
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setSize(new Dimension(1300,700));
		this.setLayout(new BorderLayout());
		this.add(scrollTabla, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setTitle("VentaIlegalArmas_sis-fj.doc");
		this.setVisible(true);
	}

}
