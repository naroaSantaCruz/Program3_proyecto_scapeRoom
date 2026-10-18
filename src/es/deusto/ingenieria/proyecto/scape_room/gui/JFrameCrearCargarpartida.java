package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingUtilities;

import es.deusto.ingenieria.proyecto.scape_room.domain.Partida;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.BordePanelesMenu;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.BordeVentana;
import es.deusto.ingenieria.proyecto.scape_room.gui.components.JBotonMenu;


public class JFrameCrearCargarpartida extends JFrame{
	
	//TODO hacer que se pueda detectar si se hace click fuera de los paneles

	private static final long serialVersionUID = 1L;
	private final Font FUENTE_PARTIDA = new Font("Bahnschrift",Font.BOLD,23);
	private final Color COLOR_TEXTO = new Color(170,235,255);
	
	//TODO ver si los creo aqui o solo los declaro
	private ImageIcon ICONO_CARPETA = new ImageIcon("images/cargar_partidas/carpetaPartida.png");
	private ImageIcon ICONO_SEPARADOR = new ImageIcon("images/cargar_partidas/separador2.png");
	private ImageIcon FONDO = new ImageIcon("images/cargar_partidas/fondoPantallaMenu.png");
	
	private JPanel[] panelPartidas = new JPanel[6];
	private JPanel[] panelPartidaInfos = new JPanel[6];
	
	private JPanel panelCrearPartida = new JPanel(new BorderLayout());
	
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelCentral = new JPanel(new GridLayout(2,3,60,35)); 
	
	private JPanel panelFooter = new JPanel();
	private JBotonMenu bVolver;
	private JBotonMenu bEliminarPartida;
	private JBotonMenu bCargarPartida;

	
	private ArrayList<Partida> partidasEmpezadas;

	
	
	public JFrameCrearCargarpartida(ArrayList<Partida> partidasEmpezadas) {
		this.partidasEmpezadas = partidasEmpezadas;
		panelesPartidasEmpezadas(this.partidasEmpezadas);
		botonesFooter();
				
		panelCentral.setBorder(BorderFactory.createEmptyBorder(100, 80, 30, 80));
		panelCentral.setOpaque(false);
		//panelPrincipal.setBackground(new Color(1, 51, 105));
		panelPrincipal.setOpaque(false);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelPrincipal.add(panelFooter, BorderLayout.SOUTH);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));
		
		JLabel fondo = new JLabel();
		fondo.setIcon(FONDO);
		fondo.setLayout(new BorderLayout());
		fondo.add(panelPrincipal);
		fondo.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		
		ponerEscchadoresBotones();
		
		this.setContentPane(fondo);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		

	}
	private void crearPanelCrearPartida() {
		ImageIcon iconoCrear = new ImageIcon("images/cargar_partidas/crearPartida.png");
		JLabel labelCrear = new JLabel();
		labelCrear.setIcon(iconoCrear);
		labelCrear.setHorizontalAlignment(JLabel.CENTER);
		panelCrearPartida.add(labelCrear, BorderLayout.CENTER);
		panelCrearPartida.setBackground(new Color(43,104,157));
		panelCrearPartida.setOpaque(false);
		panelCrearPartida.setBorder(new BordePanelesMenu());
		panelCrearPartida.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO QUE TE LLEVE A CREAR PARTIDA

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelCrearPartida.setOpaque(true);
				panelCrearPartida.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelCrearPartida.setOpaque(false);
				panelCrearPartida.repaint();
			}
			
		});
		panelCentral.add(panelCrearPartida);
	}
	private void panelesPartidasEmpezadas(ArrayList<Partida> partidasEmpezadas) {
		for (int i =0; i<=5; i++) {
			if (partidasEmpezadas.size()-1>=i) {
				Partida partida = partidasEmpezadas.get(i);
				JLabel fecha = new JLabel("Fecha de creación: "+partida.getFechaInicio().getYear()+"-"+partida.getFechaInicio().getMonth().getValue()
										+"-"+partida.getFechaInicio().getDayOfMonth()+" / "+partida.getFechaInicio().getHour()+":"+partida.getFechaInicio().getMinute(), JLabel.CENTER);
				JLabel nombre = new JLabel("Jugador: "+partida.getNombreJugador(), JLabel.CENTER);
				JLabel tiempoJugado = new JLabel("Tiempo jugado: "+String.valueOf(partida.getTiempoJugado()), JLabel.CENTER);
				
				editarVisualizacionLabel(fecha);
				editarVisualizacionLabel(nombre);
				editarVisualizacionLabel(tiempoJugado);
				
				JPanel panelInfo = new JPanel(new GridLayout(3,1));
				panelInfo.add(fecha);
				panelInfo.add(nombre);
				panelInfo.add(tiempoJugado);
				panelInfo.setOpaque(false);
				
				
				panelPartidaInfos[i] = (panelInfo);
				
				JLabel separador = new JLabel();
				separador.setIcon(ICONO_SEPARADOR);
				
				
				JPanel panelPartidaIcono = new JPanel();
				panelPartidaIcono.setOpaque(false);
				JLabel labelCarpeta = new JLabel();
				labelCarpeta.setIcon(ICONO_CARPETA);
				panelPartidaIcono.add(labelCarpeta);
				
				JPanel panelPartida= new JPanel();
				panelPartida.add(panelPartidaIcono);
				panelPartida.add(separador);
				panelPartida.add(panelInfo);
				panelPartida.setOpaque(false);
				panelPartida.setBorder(new BordePanelesMenu());
				panelPartida.setBackground(new Color(43,104,157));
				
				panelPartida.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						bCargarPartida.setVisible(true);
						bEliminarPartida.setVisible(true);
						panelPartida.setOpaque(true);
						panelPartida.repaint();
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						panelPartida.setOpaque(true);
						panelPartida.repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panelPartida.setOpaque(false);
						panelPartida.repaint();
					}
					
					
				});
				
				panelPartidas[i] = (panelPartida);
				
				panelCentral.add(panelPartidas[i]);
				
				
			}
			else {
				if (i == 5) {
					crearPanelCrearPartida();
				}
				
			}
			
		}
	}
	
	private void editarVisualizacionLabel(JLabel label) {
		label.setFont(new Font("Bahnschrift",Font.BOLD,16));
		label.setForeground(COLOR_TEXTO);
		
	}
	private void botonesFooter() {
		bVolver = new JBotonMenu("VOLVER");
		bEliminarPartida = new JBotonMenu("ELIMINAR");
		bEliminarPartida.setVisible(false);
		bCargarPartida = new JBotonMenu("CARGAR");
		bCargarPartida.setVisible(false);
		
		panelFooter.setOpaque(false);
		panelFooter.setBorder(BorderFactory.createEmptyBorder(0, 5, 30, 5));
		panelFooter.add(bVolver);
		panelFooter.add(bEliminarPartida);
		panelFooter.add(bCargarPartida);
		
	}
	
	private void ponerEscchadoresBotones() {
		bVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame menuPrinciapl = new JFrameMenuPrincipal();
				JFrameCrearCargarpartida.this.dispose();
			}
			
		});
		bCargarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrameEscritorio ventanaEscritorio = new JFrameEscritorio();
				JFrameCrearCargarpartida.this.dispose();
			}
		});
		bEliminarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO pensar como se va a borrar la partida
			}
			
		});
	}
}
	
