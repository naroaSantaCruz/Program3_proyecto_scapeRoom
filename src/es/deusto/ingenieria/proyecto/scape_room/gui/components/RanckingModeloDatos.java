package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.proyecto.scape_room.domain.Partida;

public class RanckingModeloDatos extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	private List<Partida> partidas;
	private String[] cabeceras = {"#","JUGADOR","FECHA INICIO", "FECHA FIN", "TIEMPO JUGADO", "DIFICULTAD", "FINAL"};
	
	public RanckingModeloDatos(List<Partida> pasrtidas) {
		this.partidas = pasrtidas;
	}

	@Override
	public int getRowCount() {
		if(partidas == null) {
			return 0;
		}
		return partidas.size();
	}

	@Override
	public int getColumnCount() {
		return cabeceras.length;
	}

	@Override
	public String getColumnName(int column) {
		return cabeceras[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Partida partida = partidas.get(row);
		return switch (column) {
			case 0 -> row;
			case 1 -> partida.getNombreJugador();
			case 2 -> partida.getFechaInicio();
			case 3 -> partida.getFechaFin();
			case 4 -> partida;
			case 5 -> partida.getDificultad();
			case 6 -> partida.getTipoFinal();	
 
			default -> null;
		};
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
	
}
