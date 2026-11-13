package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFrameTerminal extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final Color fondo=Color.BLACK;
	private static final Color texto=new Color(0, 255, 70);
	private static final Font fuente=new Font("Consolas", Font.PLAIN, 15);
	
	private static final String[] COMANDOS = { "help", "cl", "ls", "ls -a", "cd", "pwd", "cat",
			"grep", "find", "chmod +r", "cp" };
	//comandos
	private static final String[][] AYUDA = {
			{ "help", "muestra esta ayuda" },
			{ "cl", "limpia la pantalla" },
			{ "ls", "lista lo que hay en la carpeta actual" },
			{ "ls -a", "lista también los archivos ocultos" },
			{ "cd <carpeta>", "entra en una carpeta (cd .. para volver)" },
			{ "pwd", "muestra en qué carpeta estás" },
			{ "cat <archivo>", "muestra el contenido de un archivo" },
			{ "chmod +r <archivo>", "da permiso de lectura a un archivo" },
			//{ "cp <origen> <destino>", "copia un archivo" }
	};
	
	private JTextArea salida;
	private JTextField entrada;
	private JLabel prompt;
	private Map<String, List<String>> mapaArchivos;
	
	private String carpetaActual = "";
	private boolean permiso = false;
	

	public JFrameTerminal() {
		// TODO Auto-generated constructor stub
		cargarArchivos();
		crearComponentes();
		actualizarPosicion();
		mostrarMensajeInicial();
		eventos();
		configurarVentana();
		
	}
	
	private void crearComponentes() {
		salida = new JTextArea();
		salida.setEditable(false);
		salida.setBackground(fondo);
		salida.setForeground(texto);
		salida.setFont(fuente);
		salida.setLineWrap(true);
		salida.setWrapStyleWord(true);
		salida.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		prompt = new JLabel(" > ");
		prompt.setForeground(texto);
		prompt.setFont(fuente);

		entrada = new JTextField();
		entrada.setBackground(fondo);
		entrada.setForeground(texto);
		entrada.setCaretColor(texto);
		entrada.setFont(fuente);
		entrada.setBorder(null);

		JPanel panelEntrada = new JPanel(new BorderLayout());
		panelEntrada.setBackground(fondo);
		panelEntrada.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
		panelEntrada.add(prompt, BorderLayout.WEST);
		panelEntrada.add(entrada, BorderLayout.CENTER);

		JScrollPane scroll = new JScrollPane(salida);
		scroll.setBorder(null);

		this.add(scroll, BorderLayout.CENTER);
		this.add(panelEntrada, BorderLayout.SOUTH);
	}

	private void cargarArchivos() {
		mapaArchivos = JFrameExploradorArchivos.crearMapaArchivos();
	}
	
	
	private void mostrarMensajeInicial() {
		salida.append("=== TERMINAL SIS ===\n");
		salida.append("Comandos disponibles:\n");
		for (String c : COMANDOS) {
			salida.append("  - " + c + "\n");
		}
		salida.append("Escribe 'help' para ver los comandos disponibles.\n\n");
	}

	private void mostrarAyuda() {
		salida.append("Comandos disponibles:\n");
		for (String[] c : AYUDA) {
			salida.append(String.format("  %-26s %s\n", c[0], c[1]));
		}
		salida.append("\n");
	}

	private void eventos() {
		// Pulsar Enter en el campo de texto ejecuta el comando
		entrada.addActionListener(e -> {
			String comando = entrada.getText().trim();
			entrada.setText("");
			if (!comando.isEmpty()) {
				salida.append("> " + comando + "\n");
				ejecutarComando(comando.toLowerCase());
				salida.setCaretPosition(salida.getDocument().getLength()); // scroll al final
			}
		});
	}

	private void ejecutarComando(String comando) {
		String[] partes = comando.split(" ");  // parte el texto por los espacios
		String orden = partes[0];
		
		
		if (comando.equals("help")) {
			mostrarAyuda();
			
		} else if (comando.equals("cl")) {
			salida.setText("");
			mostrarMensajeInicial();
			
		}else if(comando.equals("pwd")) {
			salida.append("/" + carpetaActual + "\n\n");
			
		}else if (comando.equals("ls")) {
			if (carpetaActual.isEmpty()) {
				for (String carpeta : mapaArchivos.keySet()) {
					salida.append(carpeta + "/\n");
				}
			} else {
				for (String archivo : mapaArchivos.get(carpetaActual)) {
					salida.append(archivo + "\n");
				}
			}
			salida.append("\n");
			
		} else if (orden.equals("cd")) {
			if (partes.length < 2) {
				salida.append("cd: falta el nombre de la carpeta\n\n");
			} else if (partes[1].equals("..")) {
				carpetaActual = ""; // subir a la raíz
				actualizarPosicion();
			} else {
				String carpeta = buscarCarpeta(partes[1]);
				if (!carpetaActual.isEmpty() || carpeta == null) {
					salida.append("cd: " + partes[1] + ": no existe\n\n");
				} else {
					carpetaActual = carpeta; // entrar
					actualizarPosicion();
				}
			}
			
		}else if(comando.equals("ls -a")) {
			salida.append("lista archivos+ocultos\n");
			if (carpetaActual.isEmpty()) {
				for (String carpeta : mapaArchivos.keySet()) {
					salida.append(carpeta + "/\n");
				}
			} else {
				for (String archivo : mapaArchivos.get(carpetaActual)) {
					salida.append(archivo + "\n");
				}
				if(carpetaActual.equals("Documentos")) {
					salida.append("expediente \n");
				}
			}
			salida.append("\n");
			
			
			
		}else if(orden.equals("cat")){
			if(partes[1].equalsIgnoreCase("expediente") && permiso==false) {
				salida.append("Permiso denegado\n");
			}else if(partes[1].equalsIgnoreCase("expediente") && permiso==true) {
				this.setVisible(false);
				JOptionPane.showMessageDialog(JFrameTerminal.this, "¡Fichero conseguido!");
			}
			else{salida.append("TEXTO\n");} //RELLENAR
			
		}
		else if(orden.equals("chmod") && partes[1].equals("+r")) {
			if(partes[2].equalsIgnoreCase("expediente")) {
				permiso=true;
				salida.append("Permiso actualizado\n");
			}else {
				salida.append("El archivo ya se puede leer\n");
			}
		}
		
		//else if(cp -->copiar al pendrive?)
		
		
		// TODO: aquí irán los comandos que llevan a conseguir el fichero
		else {
			salida.append("Comando no encontrado: " + comando + "\n");
			salida.append("Escribe 'help' para ver los comandos disponibles.\n\n");
		}
	}
	
	
	private String buscarCarpeta(String nombre) {
		for (String carpeta : mapaArchivos.keySet()) {
			if(carpeta.equalsIgnoreCase(nombre)) {
				return carpeta;
			}
			
		}
		return null;
	}
	
	private void actualizarPosicion() {
		if (carpetaActual.isEmpty()) {
			prompt.setText(" / > ");
		} else {
			prompt.setText(" /" + carpetaActual + " > ");
		}
	}
	
	

	private void configurarVentana() {
		this.setTitle("Terminal");
		this.setSize(700, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no EXIT_ON_CLOSE, o cierra todo el juego
		this.setVisible(true);
		this.setResizable(false);
		entrada.requestFocusInWindow(); //IA
	}

}
