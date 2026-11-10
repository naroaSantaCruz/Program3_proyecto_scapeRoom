package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class JFrameExploradorArchivos extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pIzquierda;
	private JPanel pCentro;
	private JPanel pSuperior;
	private JPanel pBoton;
	private JPanel pBarraBusqueda;
	private JTree jTreeArchivos;
	
	private JButton btnSuperior;
	private JTextField barraBusqueda;
	
	private Map<String, List<String>> mapaTree;
	
	private ImageIcon flechaArriba = new ImageIcon("images/explorador_archivos/flecha-hacia-arriba.png");

	public JFrameExploradorArchivos() {
		this.setTitle("Explorador de archivos");
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize( new Dimension(900,700) );
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		
		pIzquierda = new JPanel();
		this.add(pIzquierda, BorderLayout.WEST);
		
		pCentro = new JPanel();
		this.add(pCentro, BorderLayout.CENTER);
		
		crearPanelSuperior();
		
		mapaTree = crearMapaArchivos();
		
		jTreeArchivos = crearTreeExplorador(mapaTree);
		pIzquierda.add(jTreeArchivos);
		
		this.setVisible(true);
	}
	
	private void crearPanelSuperior() {
		pSuperior = new JPanel( new GridLayout(1,2) );
		
		// Boton para acceder a carpeta superior
		btnSuperior = new JButton();
		Image imagenFlechaEscalada = flechaArriba.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
		btnSuperior.setIcon( new ImageIcon(imagenFlechaEscalada) );
		btnSuperior.setPreferredSize( new Dimension(20,20) );
		btnSuperior.setContentAreaFilled(false);
		btnSuperior.setBorderPainted(false);
		btnSuperior.setBorder(null);
		btnSuperior.setOpaque(false);
		btnSuperior.setFocusPainted(false);
		pBoton = new JPanel( new FlowLayout(FlowLayout.LEFT) );
		pBoton.add(btnSuperior);
		pBoton.setBorder( BorderFactory.createEmptyBorder(10, 20, 0, 0));
		
		// Barra busqueda
		pBarraBusqueda = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
		barraBusqueda = new JTextField();
		barraBusqueda.setPreferredSize( new Dimension( 200, 30) );
		pBarraBusqueda.setBorder( BorderFactory.createEmptyBorder(10, 0, 0, 20));
		pBarraBusqueda.add(barraBusqueda);
		
		// Añadir elementos a panel y al JFrame
		pSuperior.add(pBoton);
		pSuperior.add(pBarraBusqueda);
		this.add(pSuperior, BorderLayout.NORTH);
	}
	
	private JTree crearTreeExplorador(Map<String,List<String>> mapaTree) {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Este equipo");
		DefaultMutableTreeNode carpetaNode;
		for ( String carpeta: mapaTree.keySet() ) {
			carpetaNode = new DefaultMutableTreeNode(carpeta);
			rootNode.add(carpetaNode);
			for ( String subcarpeta: mapaTree.get(carpeta) ) {
				carpetaNode.add(new DefaultMutableTreeNode(subcarpeta));
			}
		}
		JTree treeArchivos = new JTree(rootNode);
		DefaultTreeCellRenderer rendererTree = new DefaultTreeCellRenderer() {
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				if ( value instanceof DefaultMutableTreeNode ) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
					String nombreNodo = (String) node.getUserObject();
					this.setFont( new Font("Arial", Font.BOLD, 14) );
					if ( nombreNodo.equals("Este equipo") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/escritorio.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.equals("Documentos") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/google-docs.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.equals("Escritorio") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/escritorio (1).png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.equals("Papelera") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/papelera-de-reciclaje.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.equals("Imágenes") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/imagen.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					}
				}
				return this;
			}
		};
		treeArchivos.setCellRenderer(rendererTree);
		return treeArchivos;
	}
	
	private Map<String,List<String>> crearMapaArchivos() {
		Map<String, List<String>> mapaTree = new HashMap<String, List<String>>();
		mapaTree.put("Escritorio", Arrays.asList("Papelera","Chat","Terminal"));
		mapaTree.put("Documentos", Arrays.asList("Doc1","Doc2","Doc3"));
		mapaTree.put("Imágenes", Arrays.asList("Imagen1","Imagen2","Imagen3"));
		mapaTree.put("Papelera", Arrays.asList("Archivo borrado 1"));
		return mapaTree;
	}
	
	public static void main(String[] args) {
		JFrameExploradorArchivos exploradorArchivos = new JFrameExploradorArchivos();
	}

}
