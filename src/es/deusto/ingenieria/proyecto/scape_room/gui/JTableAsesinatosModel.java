package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

import es.deusto.ingenieria.proyecto.scape_room.domain.Asesinato;

public class JTableAsesinatosModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Asesinato> asesinatos = new ArrayList<Asesinato>();
	private String[] headers = {"ENCARGADO","OBJETIVO","COD ARMA","LUGAR","FECHA","COMPLETADO","EXPEDIENTE"};

	public JTableAsesinatosModel(String ruta) {
		File fichero = new File(ruta);
		try {
			Scanner scanner = new Scanner(fichero);
			while(scanner.hasNextLine()) {
				String[] partes = scanner.nextLine().split(";");
				String asesino = partes[0];
				String muerto = partes[1];
				String cargo = partes[2];
				String codArma = partes[3];
				String lugar = partes[4];
				String fecha = partes[5];
				boolean completado;
				if ( partes[6].equals("completado") ) {
					completado = true;
				} else {
					completado = false;
				}
				Asesinato asesinato = new Asesinato(asesino,muerto,cargo,codArma,lugar,fecha,completado);
				this.asesinatos.add(asesinato);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		if ( this.asesinatos == null ) {
			return 0;
		} else {
			return this.asesinatos.size();
		}
	}

	@Override
	public int getColumnCount() {
		return this.headers.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Asesinato asesinato = this.asesinatos.get(row);
		if ( col == 0 ) {
			return asesinato.getAsesino();
		} else if ( col == 1 ) {
			return asesinato;
		} else if ( col == 2 ) {
			return asesinato.getCodArma();
		} else if ( col == 3 ) {
			return asesinato.getLugar();
		} else if ( col == 4 ) {
			return asesinato.getFecha();
		} else if ( col == 5 ) {
			if ( asesinato.isCompletado() ) {
				return "Completado";
			} else {
				return "No completado";
			}
		} else {
			return asesinato.getAsesino();
		}
	}

}
