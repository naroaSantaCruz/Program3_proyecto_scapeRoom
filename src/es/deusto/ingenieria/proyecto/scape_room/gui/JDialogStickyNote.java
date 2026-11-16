package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JDialogStickyNote extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal;
	private JTextArea texto;
	private JPanel pCentral;

	public JDialogStickyNote() {
		this.setLayout(new BorderLayout());
		this.setSize(275,250);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setModal(false);
		this.setUndecorated(true);
		
		pPrincipal = new JPanel(new BorderLayout());
		pPrincipal.setBackground(new Color(255, 238, 140));
		
		pCentral = new JPanel(new GridBagLayout());
		texto = new JTextArea();
		texto.setForeground(Color.BLACK);
		texto.setFont(new Font("Arial",Font.BOLD,30));
		texto.setOpaque(false);
		texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setPreferredSize(new Dimension(200,150));
        texto.setText("");
		pCentral.add(texto);
		pCentral.setBackground(new Color(255, 238, 140));
		
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		this.add(pPrincipal, BorderLayout.CENTER);
	}

	public void setTexto(boolean texto) {
		if ( texto ) {
			this.texto.setText("Huelga por la ropa desordenada");
		} else {
			this.texto.setText("Definición: Huelga\n\nAnagrama");
		}
	}
	
	/*public static void main(String[] args) {
		JDialogStickyNote nota = new JDialogStickyNote();
		nota.setTexto(true);
		nota.setVisible(true);
		JDialogStickyNote notaPista = new JDialogStickyNote();
		notaPista.setTexto(false);
		notaPista.setVisible(true);
	}*/

}
