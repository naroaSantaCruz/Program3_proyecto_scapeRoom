package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JFrameModeloExpediente extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	public JPanel pPrincipal;
	public JPanel pArriba;
	public JPanel pBordeCentro;
	public JPanel pAbajo;
	public JPanel pCentro;
	
	public JLabel textoArriba;
	public JLabel subtextoArriba;
	
	public JButton bCerrar;
	
	
	
	public JFrameModeloExpediente() {
		crearPaneles();
		eventos();
		configurarVentana();
	}
	
	
	

	public void crearPaneles() {
		// TODO Auto-generated constructor stub
		pPrincipal=new JPanel(new BorderLayout());
		
		pArriba=new JPanel(new GridLayout(2,1,5,5));
		pArriba.setBackground(Color.black);
		pArriba.setPreferredSize(new Dimension(0, 80));
		pArriba.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.red)); //IA, para poner color solo en un borde

		pBordeCentro = new JPanel(new BorderLayout());
		pBordeCentro.setBackground(Color.black);
		pBordeCentro.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
		
		pAbajo=new JPanel(new BorderLayout());
		pAbajo.setPreferredSize(new Dimension(0, 40));
		pAbajo.setBackground(Color.gray);
		bCerrar=new JButton("Cerrar");
		bCerrar.setBackground(Color.gray);
		pAbajo.add(bCerrar,BorderLayout.EAST);
		
		pCentro=new JPanel(new GridLayout(1,2,5,5));
		pCentro.setBackground(Color.LIGHT_GRAY);
		pBordeCentro.add(pCentro,BorderLayout.CENTER);
		
		
		pPrincipal.add(pArriba,BorderLayout.NORTH);
		pPrincipal.add(pBordeCentro,BorderLayout.CENTER);
		pPrincipal.add(pAbajo,BorderLayout.SOUTH);
		
		
		textoArriba=new JLabel();
		subtextoArriba=new JLabel();
		pArriba.add(textoArriba);
		pArriba.add(subtextoArriba);
		
		
		
		
		
		
		this.add(pPrincipal);
	}
	
	
	private void eventos() {
		bCerrar.addActionListener(e->{
			dispose();
		});
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
