package es.deusto.ingenieria.proyecto.scape_room.main;


import java.util.ArrayList;

import es.deusto.ingenieria.proyecto.scape_room.domain.Dificultad;
import es.deusto.ingenieria.proyecto.scape_room.domain.Partida;
import es.deusto.ingenieria.proyecto.scape_room.gui.JFrameCrearCargarpartida;
import es.deusto.ingenieria.proyecto.scape_room.gui.JFrameEscritorio;
import es.deusto.ingenieria.proyecto.scape_room.gui.JFrameMenuPrincipal;

public class Main {

	public static void main(String[] args) {
		//JFrameMenuPrincipal ventanMenu = new JFrameMenuPrincipal();
		//JFrameEscritorio ventanaEscritorio = new JFrameEscritorio();
		ArrayList<Partida> partidasPrueba = cargarPartidasTest();
		JFrameCrearCargarpartida ventanaCargarPartida = new JFrameCrearCargarpartida(partidasPrueba);
		
	}

	
	public static ArrayList<Partida> cargarPartidasTest(){
		ArrayList<Partida> partidasPrueba = new ArrayList<Partida>();
		partidasPrueba.add(new Partida("Naroa", false, Dificultad.FACIL));
		partidasPrueba.add(new Partida("Aitor", true, Dificultad.NORMAL));
		partidasPrueba.add(new Partida("Leire", false, Dificultad.DIFICIL));
		partidasPrueba.add(new Partida("Unai", true, Dificultad.NORMAL));
		
		return partidasPrueba;
	}

}
