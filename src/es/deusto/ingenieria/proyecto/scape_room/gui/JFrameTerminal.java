package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFrameTerminal extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final Color fondo=Color.BLACK;
	private static final Color texto=new Color(0, 255, 70);
	private static final Font fuente=new Font("Consolas", Font.PLAIN, 15);
	
	//comandos
	private static final String[] COMANDOS = { "help", "cl", "ls","ls -a","cd","pwd","cat",
			"grep","find","chmod +r","cp"};
	
	private JTextArea salida;
	private JTextField entrada;
	private JLabel prompt;
	

	public JFrameTerminal() {
		// TODO Auto-generated constructor stub
		crearComponentes();
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

	private void mostrarMensajeInicial() {
		salida.append("=== TERMINAL SIS ===\n");
		salida.append("Comandos disponibles:\n");
		for (String c : COMANDOS) {
			salida.append("  - " + c + "\n");
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
		if (comando.equals("help")) {
			mostrarMensajeInicial();
		} else if (comando.equals("cl")) {
			salida.setText("");
			mostrarMensajeInicial();
		}
		// TODO: aquí irán los comandos que llevan a conseguir el fichero
		else {
			salida.append("Comando no encontrado: " + comando + "\n");
			salida.append("Escribe 'help' para ver los comandos disponibles.\n\n");
		}
	}

	private void configurarVentana() {
		this.setTitle("Terminal");
		this.setSize(700, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no EXIT_ON_CLOSE, o cierra todo el juego
		this.setVisible(true);
		entrada.requestFocusInWindow();
	}
	
	


}
