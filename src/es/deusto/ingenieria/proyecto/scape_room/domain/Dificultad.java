package es.deusto.ingenieria.proyecto.scape_room.domain;

import java.awt.Color;

import javax.swing.ImageIcon;

//Codigo extraido de una fuente externa (no IA):
//Fuente: resoluciones de examenes del curso de ALUD (concretamente Parcial 2025)
//Código modificado para adaptarse a los valores requeridos

public enum Dificultad {
	FACIL(Color.green),
    DIFICIL(Color.orange),
    MEDIA(Color.red);
	
	private Color color;
	
	private Dificultad(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public ImageIcon getIcon() {
		return switch (this) {
			case FACIL -> new ImageIcon("images/menu_opciones_juego/dificultadFacil.png");
			case DIFICIL -> 	new ImageIcon("images/menu_opciones_juego/dificultadDificil.png");
			default -> new ImageIcon("images/menu_opciones_juego/dificultadMedia.png");					
		};				
	}
}
