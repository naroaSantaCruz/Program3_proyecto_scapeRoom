package es.deusto.ingenieria.proyecto.scape_room.domain;

import java.awt.Color;

import javax.swing.ImageIcon;

//Codigo extraido de una fuente externa (no IA):
//Fuente: resoluciones de examenes del curso de ALUD (concretamente Parcial 2025)
//Código modificado para adaptarse a los valores requeridos

public enum TipoFinal {
	SIS,FJERDA,CARCEL,MUERTE,FUGA;

	
	public ImageIcon getIcon() {
		return switch (this) {
			case SIS -> new ImageIcon("images/menu_opciones_juego/finalSIS.png");
			case FJERDA -> 	new ImageIcon("images/menu_opciones_juego/finalFjerda.png");
			case CARCEL -> new ImageIcon("images/menu_opciones_juego/finalCarcel.png");
			case MUERTE -> 	new ImageIcon("images/menu_opciones_juego/finalMuerte.png");
			
			default -> new ImageIcon("images/menu_opciones_juego/finalFuga.png");					
		};				
	}
}
