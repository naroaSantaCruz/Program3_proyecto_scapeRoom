package es.deusto.ingenieria.proyecto.scape_room.domain;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Partida {
	//Información necesaria para la creación de la partida
	private int tiempoTotal;
	private Dificultad dificultad;
	private int tiempoJugado;
	private boolean completada;
	private String nombreJugador;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private boolean saltarIntro;
	
	//Información para el transcurso de la partida
	
	//Información para el guardado de la partida
	

	public Partida(String nombreJugador, boolean saltarIntro, Dificultad dificultad) {
		super();
		setNombreJUgador(nombreJugador);
		this.saltarIntro = saltarIntro;
		this.dificultad = dificultad;
		setTiempoTotal();
		this.tiempoJugado = 0;
		this.fechaInicio = LocalDateTime.now();
		this.completada = false;
		
	}

	public int getTiempoTotal() {
		return tiempoTotal;
	}

	private void setTiempoTotal() {
		//ponerlo en vase a la dificultad
		this.tiempoTotal = 30;
	}

	public int getTiempoJugado() {
		return tiempoJugado;
	}

	public void setTiempoJugado(int tiempoJugado) {
		this.tiempoJugado = tiempoJugado;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJUgador(String nombreJugador) {
		if (nombreJugador.contains(";")) {
			JOptionPane.showMessageDialog(null, "Tu nombre de registro para la partida no puede contener el caracter --> ;");
		}
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public boolean isSaltarIntro() {
		return saltarIntro;
	}

	public void registrarPartidaEmpezada() {
		if (!this.completada) {
			try {
				BufferedWriter bff = new BufferedWriter(new FileWriter("src/es/deusto/ingenieria/proyecto/scape_room/io/partidasCargables.txt",
														true));
				
				bff.write(fechaInicio.toString()+";"+nombreJugador+";"+tiempoJugado);
				bff.newLine();
				bff.close();
				
			} catch (IOException e) {
				System.err.println("No se ha encontrado el fichero");
			}
		}
	}
	
}



