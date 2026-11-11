package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import es.deusto.ingenieria.proyecto.scape_room.domain.Dificultad;
import es.deusto.ingenieria.proyecto.scape_room.domain.Partida;
import es.deusto.ingenieria.proyecto.scape_room.domain.TipoFinal;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.RanckingModeloDatos;

public class RanckingPartidas extends JFrame{
	private List<Partida> partidas;
	private JTable tabla;
	private final Color COLOR_CABECERA = new Color(170,235,255);
	private final Color COLOR_FONDO = new Color(43,104,157);
	
	public RanckingPartidas() {
		this.partidas = generarPartidasEJemplo();
		RanckingModeloDatos modelo = new RanckingModeloDatos(partidas);
		tabla = new JTable(modelo);
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				table.getTableHeader().setBackground(table.getBackground());
				
				JLabel result = new JLabel(value.toString());
				result.setHorizontalAlignment(JLabel.CENTER);
				result.setFont(new Font("Bahnschrift",Font.BOLD,22));
				result.setForeground(COLOR_CABECERA);

				JPanel panelR = new JPanel();
				panelR.add(result);
				panelR.setOpaque(true);
				panelR.setBackground(COLOR_FONDO);
				panelR.setBorder(BorderFactory.createLineBorder(COLOR_CABECERA, 2));
				JPanel panelBorde = new JPanel();
				panelBorde.add(panelR);
				panelBorde.setBorder(BorderFactory.createLineBorder(COLOR_FONDO, 1));
				panelBorde.setBackground(COLOR_FONDO.darker());
				
				return panelBorde;
			}
			
		};
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				table.setShowGrid(false);
				table.setBackground(COLOR_FONDO.darker());
				table.setRowHeight(50);
				table.getColumnModel().getColumn(0).setPreferredWidth(2);
				
				JLabel result = new JLabel();
				result.setBackground(COLOR_FONDO);
				result.setOpaque(true);
				result.setHorizontalAlignment(JLabel.CENTER);
				result.setFont(new Font("Bahnschrift",Font.BOLD,17));
				
			
				if (column == 0) {
					int clasificacion = (int)row+1;
					result.setText(String.valueOf(clasificacion));
					if (row <= 2) {
						result.setIcon(new ImageIcon("images/menu_opciones_juego/top"+clasificacion+"Icono.png"));
					}
				}
				if (column == 1) {
					result.setText(value.toString());
				}
				else if(column == 2 || column == 3) {
					result.setText(((LocalDateTime)value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm")));
				}		
				else if(column == 4) {
					result.setText(String.valueOf(((Partida)value).getTiempoJugado())+" minutos");
					if(((Partida)value).getTiempoTotal() != ((Partida)value).getTiempoJugado()) {
						result.setForeground(COLOR_CABECERA.darker());
					}
				}
				else if(column == 5) {
					Dificultad dificultad = (Dificultad) value;
					JPanel panelDif = new JPanel(new GridLayout(2,1,4,0));
					panelDif.setBackground(COLOR_FONDO);
					
					JLabel icono = new JLabel();
					icono.setIcon(dificultad.getIcon());
					JLabel nombre = new JLabel(dificultad.toString());
					nombre.setFont(new Font("Bahnschrift",Font.ITALIC,10));
					nombre.setForeground(Color.lightGray);
					
					panelDif.add(icono);
					panelDif.add(nombre);
					
					return panelDif;
				}
				else if(column == 6) {
					TipoFinal tipo = (TipoFinal) value;
					result.setIcon(tipo.getIcon());
				}
				
				return result;
			}
			
		};
		
		//Código obtenido de una fuente externa (IA generativa):
		//IAG: Copilot
		//Código no modificado
		
		TableRowSorter<RanckingModeloDatos> sorter =
		        new TableRowSorter<>(modelo);

		sorter.setComparator(4, (o1, o2) -> {
		    Partida p1 = (Partida) o1;
		    Partida p2 = (Partida) o2;

		    return Integer.compare(
		            p1.getTiempoJugado(),
		            p2.getTiempoJugado()
		    );
		});

		// Orden inicial por tiempo jugado ascendente
		sorter.setSortKeys(List.of( new RowSorter.SortKey(4, SortOrder.ASCENDING)));

		tabla.setRowSorter(sorter);
		sorter.sort();
		
		tabla.getTableHeader().setDefaultRenderer(headerRenderer);
		tabla.setDefaultRenderer(Object.class, renderer);  
		JScrollPane panelScroll = new JScrollPane(tabla);
		                                                    
		//TODO meter la tabla en un scroll panel
		this.add(tabla.getTableHeader(), BorderLayout.NORTH);
		this.add(panelScroll, BorderLayout.CENTER);
		
		this.setTitle("RANKING");
		this.setVisible(true);
		this.setSize(new Dimension(1300,550));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
		
		
	}
	
	private List<Partida> generarPartidasEJemplo(){
		
		//Código obtenido de fuentes externas (IA generativa):
		//IA: Copilot
		//Código no modificado
		//COdigo provisional usado para pruebas
		List<Partida> partidas = new ArrayList<>();

		Partida p1 = new Partida("Ane", false, Dificultad.FACIL);
		p1.setFechaInicio(LocalDateTime.of(2026, 10, 5, 18, 0));
		p1.setFechaFin(LocalDateTime.of(2026, 10, 5, 18, 22));
		p1.setTiempoJugado(22);
		p1.setCompletada(true);
		partidas.add(p1);

		Partida p2 = new Partida("Jon", true, Dificultad.MEDIA);
		p2.setFechaInicio(LocalDateTime.of(2026, 10, 8, 19, 45));
		p2.setFechaFin(LocalDateTime.of(2026, 10, 8, 20, 15));
		p2.setTiempoJugado(30);
		p2.setCompletada(true);
		partidas.add(p2);

		Partida p3 = new Partida("Naroa", false, Dificultad.DIFICIL);
		p3.setFechaInicio(LocalDateTime.of(2026, 10, 12, 19, 0));
		p3.setFechaFin(LocalDateTime.of(2026, 10, 12, 19, 41));
		p3.setTiempoJugado(41);
		p3.setCompletada(true);
		partidas.add(p3);

		Partida p4 = new Partida("Goizane", true, Dificultad.MEDIA);
		p4.setFechaInicio(LocalDateTime.of(2026, 10, 15, 17, 7));
		p4.setFechaFin(LocalDateTime.of(2026, 10, 15, 17, 35));
		p4.setTiempoJugado(28);
		p4.setCompletada(true);
		partidas.add(p4);

		Partida p5 = new Partida("Naia", false, Dificultad.FACIL);
		p5.setFechaInicio(LocalDateTime.of(2026, 10, 18, 15, 54));
		p5.setFechaFin(LocalDateTime.of(2026, 10, 18, 16, 12));
		p5.setTiempoJugado(18);
		p5.setCompletada(true);
		partidas.add(p5);

		Partida p6 = new Partida("Iker", true, Dificultad.DIFICIL);
		p6.setFechaInicio(LocalDateTime.of(2026, 10, 22, 20, 21));
		p6.setFechaFin(LocalDateTime.of(2026, 10, 22, 21, 5));
		p6.setTiempoJugado(44);
		p6.setCompletada(true);
		partidas.add(p6);

		Partida p7 = new Partida("Mikel", false, Dificultad.MEDIA);
		p7.setFechaInicio(LocalDateTime.of(2026, 10, 25, 18, 52));
		p7.setFechaFin(LocalDateTime.of(2026, 10, 25, 19, 18));
		p7.setTiempoJugado(26);
		p7.setCompletada(true);
		partidas.add(p7);

		Partida p8 = new Partida("Leire", true, Dificultad.FACIL);
		p8.setFechaInicio(LocalDateTime.of(2026, 10, 27, 18, 22));
		p8.setFechaFin(LocalDateTime.of(2026, 10, 27, 18, 42));
		p8.setTiempoJugado(20);
		p8.setCompletada(true);
		partidas.add(p8);

		Partida p9 = new Partida("Unai", false, Dificultad.DIFICIL);
		p9.setFechaInicio(LocalDateTime.of(2026, 10, 29, 19, 51));
		p9.setFechaFin(LocalDateTime.of(2026, 10, 29, 20, 30));
		p9.setTiempoJugado(39);
		p9.setCompletada(true);
		partidas.add(p9);

		Partida p10 = new Partida("Maddi", true, Dificultad.MEDIA);
		p10.setFechaInicio(LocalDateTime.of(2026, 11, 2, 17, 34));
		p10.setFechaFin(LocalDateTime.of(2026, 11, 2, 17, 58));
		p10.setTiempoJugado(24);
		p10.setCompletada(true);
		partidas.add(p10);
		
		p1.setTipoFinal(TipoFinal.SIS);
		p2.setTipoFinal(TipoFinal.CARCEL);
		p3.setTipoFinal(TipoFinal.FUGA);
		p4.setTipoFinal(TipoFinal.MUERTE);
		p5.setTipoFinal(TipoFinal.SIS);
		p6.setTipoFinal(TipoFinal.FJERDA);
		p7.setTipoFinal(TipoFinal.FUGA);
		p8.setTipoFinal(TipoFinal.MUERTE);
		p9.setTipoFinal(TipoFinal.CARCEL);
		p10.setTipoFinal(TipoFinal.SIS);
		
		return partidas;
	}

	public static void main(String[] args) {
		RanckingPartidas r = new RanckingPartidas();
	}
}

