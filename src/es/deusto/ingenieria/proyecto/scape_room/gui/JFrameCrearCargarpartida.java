package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.deusto.ingenieria.proyecto.scape_room.domain.Partida;


public class JFrameCrearCargarpartida extends JFrame{

	private static final long serialVersionUID = 1L;
	
	//TODO ver si los creo aqui o solo los declaro
	private ImageIcon ICONO_CARPETA = new ImageIcon("images/cargar_partidas/carpetaPartida.png");
	private ImageIcon ICONO_FONDO_PANEL = new ImageIcon("images/cargar_partidas/fondoPanel.png");
	
	private JPanel[] panelPartidas = new JPanel[6]; //hacerlo como tipo de panel para ponerle un fondo como a los botones
	private JPanel[] panelPartidaInfos = new JPanel[6];
	
	private JPanel panelCrearPartida = new JPanel();
	private JPanel panelCrearPartidaIcono = new JPanel();

	private JPanel panelCentral = new JPanel(new GridLayout(2,3,30,30)); 
	
	public JFrameCrearCargarpartida(ArrayList<Partida> partidasEmpezadas) {

		for (int i =0; i<=5; i++) {
			if (partidasEmpezadas.size()-1>=i) {
				Partida partida = partidasEmpezadas.get(i);
				//TODO hacer que la fecha se vea bonita
				JLabel fecha = new JLabel(partida.getFechaInicio().toString(), JLabel.CENTER);
				JLabel nombre = new JLabel(partida.getNombreJugador(), JLabel.CENTER);
				JLabel tiempoJugado = new JLabel(String.valueOf(partida.getTiempoJugado()), JLabel.CENTER);
				
				JPanel panelInfo = new JPanel(new GridLayout(3,1));
				panelInfo.add(fecha);
				panelInfo.add(nombre);
				panelInfo.add(tiempoJugado);
				panelInfo.setOpaque(false);
				
				panelPartidaInfos[i] = (panelInfo);
				
				JPanel panelPartidaIcono = new JPanel();
				panelPartidaIcono.setOpaque(false);
				JLabel labelCarpeta = new JLabel();
				labelCarpeta.setIcon(ICONO_CARPETA);
				panelPartidaIcono.add(labelCarpeta);
				
				JPanel panelPartida= new JPanel();
				panelPartida.add(panelPartidaIcono);
				panelPartida.add(panelInfo);
				panelPartida.setOpaque(false);
				panelPartida.setBorder(BorderFactory.createLineBorder(new Color(0,29,102), 9, true));
				
				panelPartidas[i] = (panelPartida);
				
				panelCentral.add(panelPartidas[i]);
				
				
			}
			else {
				if (i == 5) {
					crearPanelCrearPartida();
				}
				
			}
			
		}
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelCentral.setBackground(new Color(117, 146, 181));
		this.setContentPane(panelCentral);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	private void crearPanelCrearPartida() {
		ImageIcon iconoCrear = new ImageIcon("images/cargar_partidas/crearPartida.png");
		JLabel labelCrear = new JLabel();
		labelCrear.setIcon(iconoCrear);
		panelCrearPartidaIcono.add(labelCrear);
		panelCrearPartidaIcono.setOpaque(false);
		panelCrearPartida.add(panelCrearPartidaIcono);
		panelCrearPartida.setOpaque(false);
		panelCrearPartida.setBorder(BorderFactory.createLineBorder(new Color(0,29,102), 4, true));
		panelCentral.add(panelCrearPartida);
	}
}
