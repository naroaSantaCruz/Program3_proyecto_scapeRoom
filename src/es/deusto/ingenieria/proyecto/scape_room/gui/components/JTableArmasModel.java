package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;

import es.deusto.ingenieria.proyecto.scape_room.domain.Arma;

public class JTableArmasModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private String[] header = {"Código", "Nombre", "Imagen", "Calibre", "Descripción"};
	private List<Arma> listaArmas = new ArrayList<Arma>();
	
	public JTableArmasModel(String rutaDatos) {
		File fich = new File(rutaDatos);
		try {
			Scanner leerDatos = new Scanner(fich);
			while(leerDatos.hasNextLine()) {
				String[] datos = leerDatos.nextLine().split(";");
				Arma nuevaArma = new Arma(datos[0], datos[1], datos[2], datos[3], datos[4]);
				this.listaArmas.add(nuevaArma);
			}
			leerDatos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		return listaArmas.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return header[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return listaArmas.get(rowIndex).getCodigo();
		}case 1: {
			return listaArmas.get(rowIndex).getNombre();
		}case 2: {
			return listaArmas.get(rowIndex).getRutaFoto();
		}case 3: {
			return listaArmas.get(rowIndex).getCalibre();
		}case 4: {
			return listaArmas.get(rowIndex).getDescripcion();
		}default:
			return null;
		}
		
	}

}
