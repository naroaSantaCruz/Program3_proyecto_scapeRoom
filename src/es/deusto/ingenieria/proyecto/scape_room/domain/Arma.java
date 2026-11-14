package es.deusto.ingenieria.proyecto.scape_room.domain;

public class Arma {

	private String codigo;
	private String nombre;
	private String rutaFoto;
	private String calibre;
	private String descripcion;
	
	public Arma(String codigo, String nombre, String rutaFoto, String calibre, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.rutaFoto = rutaFoto;
		this.calibre = calibre;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	public String getCalibre() {
		return calibre;
	}

	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
