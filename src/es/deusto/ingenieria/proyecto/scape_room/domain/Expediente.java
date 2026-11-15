package es.deusto.ingenieria.proyecto.scape_room.domain;

public class Expediente {

	private String titulo;
	private String subtitulo;
	private String rutaFoto;
	private String nombre;
	private String info2;
	private String info3;
	private String info4;
	private String notas;

	public Expediente(String titulo, String subtitulo, String rutaFoto, String nombre,
			String info2, String info3, String info4, String notas) {
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.rutaFoto = rutaFoto;
		this.nombre = nombre;
		this.info2 = info2;
		this.info3 = info3;
		this.info4 = info4;
		this.notas = notas;
	}

	public String getTitulo() {
		return titulo; 
	}
	
	public String getSubtitulo() {
		return subtitulo; 
		}
	
	
	public String getRutaFoto() {
		return rutaFoto; 
		}
	
	public String getNombre() {
		return nombre; 
		}
	
	public String getInfo2() {
		return info2; 
		}
	
	public String getInfo3() { 
		return info3; 
		}
	
	public String getInfo4() { 
		return info4;
		}
	
	public String getNotas() {
		return notas; 
		}
	

	
	public static Expediente directorSis() {
		return new Expediente(
				"FICHA PENITENCIARIA Nº 23623",
				"Centro penitenciario · uso interno",
				"images/expediente/director_sis.png",
				"Lilith Sorrengail",
				"Condena: 12 años",
				"Ubicación: Módulo 4, celda 21",
				"Delito: [CENSURADO]",
				"Salió hace tres años. Fundó SIS justo después. ¿De dónde sacó el dinero?");
	}
}
