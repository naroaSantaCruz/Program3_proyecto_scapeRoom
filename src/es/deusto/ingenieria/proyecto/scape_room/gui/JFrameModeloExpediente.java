package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.deusto.ingenieria.proyecto.scape_room.domain.Expediente;

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
	
	public JPanel pfoto;
	public JPanel pInfo;
	
	public JLabel lNombre;
	public JLabel lInfo2;
	public JLabel lInfo3;
	public JLabel lInfo4;
	
	public JPanel pDatos;
	public JPanel pNotas;
	
	public JLabel lNotas;
	public JLabel lFoto;
	
	public JFrameModeloExpediente(Expediente expediente) {
		crearPaneles();
		eventos();
		cargarDatos(expediente);
		configurarVentana();
	}
	
	
	

	public void crearPaneles() {
		// TODO Auto-generated constructor stub
		pPrincipal=new JPanel(new BorderLayout());
		
		pArriba=new JPanel(new GridLayout(2,1,5,5));
		pArriba.setBackground(Color.black);
		pArriba.setPreferredSize(new Dimension(0, 80));
		pArriba.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 5, 0, Color.red),
				BorderFactory.createEmptyBorder(0, 22, 0, 22))); //IA, para poner color solo en un borde

		pBordeCentro = new JPanel(new BorderLayout());
		pBordeCentro.setBackground(Color.black);
		pBordeCentro.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
		
		pAbajo=new JPanel(new BorderLayout());
		pAbajo.setPreferredSize(new Dimension(0, 40));
		pAbajo.setBackground(Color.gray);
		bCerrar=new JButton("Cerrar");
		bCerrar.setBackground(Color.gray);
		pAbajo.add(bCerrar,BorderLayout.EAST);
		
		pCentro=new JPanel(new BorderLayout(20,0));
		pCentro.setBackground(Color.LIGHT_GRAY);
		pCentro.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
		pfoto=new JPanel(new BorderLayout());
		//añadir foto en border.north
		pfoto.setBackground(Color.WHITE);
		pfoto.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(207, 201, 182)),
				BorderFactory.createEmptyBorder(8, 8, 0, 8)));
		pfoto.setPreferredSize(new Dimension(170, 210));
		
		lFoto=new JLabel("FOTO", SwingConstants.CENTER);
		lFoto.setOpaque(true);
		lFoto.setBackground(Color.gray);
		lFoto.setPreferredSize(new Dimension(154, 154));
		pfoto.add(lFoto, BorderLayout.NORTH);

		JPanel contenedorFoto = new JPanel(new BorderLayout());
		contenedorFoto.setOpaque(false);
		contenedorFoto.add(pfoto, BorderLayout.NORTH);
		pCentro.add(contenedorFoto, BorderLayout.WEST);
		
		pDatos=new JPanel(new GridLayout(2,1,3,3));
		pDatos.setOpaque(false);
		
		pInfo=new JPanel(new GridLayout(4,1,1,1));
		pInfo.setOpaque(false);
		lNombre=new JLabel("NOMBRE");
		lInfo2=new JLabel("Info2");
		lInfo3=new JLabel("Info3");
		lInfo4=new JLabel("Info4");
		pInfo.add(lNombre);
		pInfo.add(lInfo2);
		pInfo.add(lInfo3);
		pInfo.add(lInfo4);
		pNotas=new JPanel(new BorderLayout());
		pNotas.setOpaque(false);
		lNotas=new JLabel("NOTAS");
		pNotas.add(lNotas);
		
		
		
		pDatos.add(pInfo);
		pDatos.add(pNotas);
		pCentro.add(pDatos, BorderLayout.CENTER);
		pBordeCentro.add(pCentro,BorderLayout.CENTER);
		
		
		pPrincipal.add(pArriba,BorderLayout.NORTH);
		pPrincipal.add(pBordeCentro,BorderLayout.CENTER);
		pPrincipal.add(pAbajo,BorderLayout.SOUTH);
		
		
		textoArriba=new JLabel("EXPEDIENTE Nº 042");
		textoArriba.setForeground(Color.WHITE);
		textoArriba.setFont(new Font("Consolas", Font.BOLD, 20));
		subtextoArriba=new JLabel("Acceso restringido · solo personal autorizado");
		subtextoArriba.setForeground(new Color(141, 141, 152));
		subtextoArriba.setFont(new Font("Consolas", Font.PLAIN, 11));
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

	
	public void cargarDatos(Expediente e) {
		textoArriba.setText(e.getTitulo());
		subtextoArriba.setText(e.getSubtitulo());
		lNombre.setText(e.getNombre());
		lInfo2.setText(e.getInfo2());
		lInfo3.setText(e.getInfo3());
		lInfo4.setText(e.getInfo4());
		lNotas.setText("<html>" + e.getNotas() + "</html>"); // el html hace que el texto largo baje de línea

		// Foto escalada al hueco de la polaroid
		lFoto.setText("");
		lFoto.setIcon(new ImageIcon(new ImageIcon(e.getRutaFoto()).getImage()
				.getScaledInstance(154, 154, Image.SCALE_SMOOTH)));
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Expediente prueba = new Expediente(
				"FICHA PENITENCIARIA Nº 0931",
				"Centro penitenciario · uso interno",
				"images/expedientes/jefe.png",
				"Ernesto Valdés Mora",
				"Condena: 12 años",
				"Ubicación: Centro penitenciario norte",
				"Delito: [CENSURADO]",
				"Salió hace tres años. Fundó la empresa justo después.");
		new JFrameModeloExpediente(prueba);

	}

}
