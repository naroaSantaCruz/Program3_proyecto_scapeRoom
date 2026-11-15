package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrameModeloExpediente extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	public JPanel pPrincipal;
	public JPanel pArriba;
	public JPanel pCentro;
	public JPanel pAbajo;
	
	public JFrameModeloExpediente() {
		crearPaneles();
		configurarVentana();
	}
	
	
	

	public void crearPaneles() {
		// TODO Auto-generated constructor stub
		pPrincipal=new JPanel(new BorderLayout());
		pArriba=new JPanel(new BorderLayout());
		pCentro=new JPanel(new GridLayout());
		pAbajo=new JPanel(new BorderLayout());
		pPrincipal.add(pArriba,BorderLayout.NORTH);
		pPrincipal.add(pCentro,BorderLayout.CENTER);
		pPrincipal.add(pAbajo,BorderLayout.SOUTH);
		
		
		
		
		this.add(pPrincipal);
	}
	
	
	
	private void configurarVentana() {
		this.setTitle("Expediente");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no EXIT_ON_CLOSE
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JFrameModeloExpediente();

	}

}
