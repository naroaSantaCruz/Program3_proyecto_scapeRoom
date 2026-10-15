package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class JFrameCrearCargarpartida {
	//TODO ver si los creo aqui o solo los declaro
	private ArrayList<ArrayList<String>> partidasEmpezadas;
	private JPanel[] panelPartida = new JPanel[6]; //hacerlo como tipo de panel para ponerle un fondo como a los botones
	private JPanel[] panelPartidaIcono = new JPanel[6];
	private JPanel[] panelPartidaInfo = new JPanel[6];
	
	private JPanel panelCentral = new JPanel(new GridLayout(2,3,10,10)); 
	
	public JFrameCrearCargarpartida() {
		cargarPartidasEmpezadas();
		for (int i =0; i<5; i++) {
			if (partidasEmpezadas.size()-1<=i) {
				ArrayList<String> partidaInfo = partidasEmpezadas.get(i);
				JLabel fecha = new JLabel(partidaInfo.get(0));
				JLabel nombre = new JLabel(partidaInfo.get(1));
				JLabel tiempoJugado = new JLabel(partidaInfo.get(2));
				panelPartidaInfo[i].add(fecha);
				panelPartidaInfo[i].add(nombre);
				panelPartidaInfo[i].add(tiempoJugado);
				
				
				panelPartida[i].add(panelPartidaIcono[i]);
				panelPartida[i].add(panelPartidaInfo[i]);
				
				panelCentral.add(panelPartida[i]);
				
				
			}
			else {
				if (i == 5) {
					//TODO Poner de icono el de crear y añadir, igual dejo el de crear ya configurado?
					panelCentral.add(panelPartida[i]);
				}
				
			}
			
		}
		
		
		
	}
	
	//Ayudita de la IA mirar como poner
	private void cargarPartidasEmpezadas(){
		ArrayList<ArrayList<String>> partidasEmpezadas = new ArrayList<ArrayList<String>>();
		try{ BufferedReader bffRead = new BufferedReader(new FileReader("src/es/deusto/ingenieria/proyecto/scape_room/io/partidasCargables.txt"));
			
		String linea = bffRead.readLine();
			
			while (linea != null) {
				
				String[] lineaDividida = linea.split(";");
				ArrayList<String> informacionPartida = new ArrayList<String>();	
				
				informacionPartida.add(lineaDividida[0]);
				informacionPartida.add(lineaDividida[1]);
				informacionPartida.add(lineaDividida[2]);
				
				partidasEmpezadas.add(informacionPartida);
				
				linea = bffRead.readLine();
			}
		this.partidasEmpezadas = partidasEmpezadas;
		
		}catch (IOException e) {
			System.err.println("No se ha podido encontrar el archivo");
		}

	}
}
