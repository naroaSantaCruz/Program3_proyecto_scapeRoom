package es.deusto.ingenieria.proyecto.scape_room.domain;

public class Asesinato {
	
	private String asesino; 
	private String muerto;
	private String cargo;
	private String codArma;
	private String lugar;
	private String fecha;
	private boolean completado;

	public Asesinato(String asesino, String muerto, String cargo, String codArma, String lugar, String fecha,
			boolean completado) {
		this.asesino = asesino;
		this.cargo = cargo;
		this.muerto = muerto;
		this.codArma = codArma;
		this.lugar = lugar;
		this.fecha = fecha;
		this.completado = completado;
	}

	public String getAsesino() {
		return asesino;
	}

	public void setAsesino(String asesino) {
		this.asesino = asesino;
	}

	public String getMuerto() {
		return muerto;
	}

	public void setMuerto(String muerto) {
		this.muerto = muerto;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getCodArma() {
		return codArma;
	}

	public void setCodArma(String codArma) {
		this.codArma = codArma;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	@Override
	public String toString() {
		return "Asesinato encargado a: " + asesino +
				"\n Objetivo: " + cargo + " " + muerto +
				"\n Arma para el trabajo: " + codArma +
				"\n Lugar y fecha: " + lugar + " - " + fecha +
				"\n ¿Completado?" + completado;
	}

}
