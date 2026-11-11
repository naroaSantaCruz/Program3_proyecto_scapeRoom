package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class JFrameExploradorArchivos extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pIzquierda;
	private JPanel pCentro;
	private JPanel pSuperior;
	private JPanel pBoton;
	private JPanel pBarraBusqueda;
	private JTree jTreeArchivos;
	private MouseAdapter dobleClickNodo;
	
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
		
		pIzquierda = new JPanel(new BorderLayout());
		this.add(pIzquierda, BorderLayout.WEST);
		
		pCentro = new JPanel(new BorderLayout());
		pCentro.setPreferredSize(new Dimension(650,600));
		pCentro.setBackground(Color.WHITE);
		this.add(pCentro, BorderLayout.CENTER);
		
		crearPanelSuperior();
		
		mapaTree = crearMapaArchivos();
		
		jTreeArchivos = crearTreeExplorador(mapaTree);
		dobleClickNodo = dobleClick(jTreeArchivos,pCentro);
		jTreeArchivos.addMouseListener(dobleClickNodo);
		JScrollPane scrollPane = new JScrollPane(jTreeArchivos);
		scrollPane.setPreferredSize(new Dimension(250,600));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pIzquierda.add(scrollPane,BorderLayout.CENTER);
		
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
		btnSuperior.addActionListener( e -> subirDeNivel(jTreeArchivos,pCentro));
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
		treeArchivos.setRowHeight(40);
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
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/carpeta.png");
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
					} else if ( nombreNodo.contains("Doc") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/google-docs.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.toLowerCase().contains("borrado") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/borrar.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.toLowerCase().contains("imagen") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/imagen.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.equals("Chat") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/escritorio/chat.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else if ( nombreNodo.equals("Terminal") ) {
						ImageIcon imagenEscalada = new ImageIcon("images/escritorio/terminal.png");
						imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
						this.setIcon(imagenEscalada);
					} else {
						ImageIcon imagenEscalada = new ImageIcon("images/explorador_archivos/expediente.png");
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
		mapaTree.put("Documentos", Arrays.asList("Doc1","Doc2","Doc3","Doc4"));
		mapaTree.put("Imágenes", Arrays.asList("Imagen1","Imagen2","Imagen3","Imagen4"));
		mapaTree.put("Papelera", Arrays.asList("Archivo borrado 1","Archivo borrado 2"));
		return mapaTree;
	}
	
	private void subirDeNivel(JTree tree,JPanel panel) {
		// código generado con ayuda de IA
		TreePath rutaNodoSeleccionado = tree.getSelectionPath(); // cogemos la ruta del nodo actualmente seleccionado
		if ( rutaNodoSeleccionado != null ) {
			DefaultMutableTreeNode nodoActual = (DefaultMutableTreeNode) rutaNodoSeleccionado.getLastPathComponent(); // cogemos nodo actual
			DefaultMutableTreeNode nodoParent = (DefaultMutableTreeNode) nodoActual.getParent(); // cogemos parent del nodo actual
			if ( nodoParent != null ) { // si el nodo parent no es null significa que no estamos en el valor "este equipo",podemos usar el boton correctamente
				TreePath rutaNodoParent = new TreePath(nodoParent.getPath()); // cogemos ruta del nodo parent
				tree.setSelectionPath(rutaNodoParent); // sustituimos la seleccion anterior por la seleccion nueva del parent
				tree.scrollPathToVisible(rutaNodoParent); // visualizamos el cambio
			}
			// misma lógica para visualizar nuevo arbol en el panel central
			panel.removeAll();
			JTree nuevoTree = new JTree(nodoParent);
			JScrollPane scrollPane = new JScrollPane(nuevoTree);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			panel.add(nuevoTree,BorderLayout.CENTER);
			panel.revalidate();
            panel.repaint();
		}
	}
	
	private MouseAdapter dobleClick(JTree tree,JPanel panel) {
		MouseAdapter click = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 1 ) {
					TreePath rutaNodoSelec = tree.getPathForLocation(e.getX(), e.getY());
					if ( rutaNodoSelec != null ) {
						DefaultMutableTreeNode nodoSelec = (DefaultMutableTreeNode) rutaNodoSelec.getLastPathComponent();
						if ( !nodoSelec.isLeaf() ) {
							panel.removeAll(); // limpia los elementos que ahora mismo están en el panel, para no sobreescribir elementos
							JTree nuevoTree = new JTree(nodoSelec);
							JScrollPane scrollPane = new JScrollPane(nuevoTree);
							scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
							scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
							panel.add(nuevoTree,BorderLayout.CENTER);
							// vuelve a pintar los elementos en el panel
							panel.revalidate();
	                        panel.repaint();
						}
					}
				} else if ( e.getClickCount() == 2 ) {
					TreePath rutaNodoSelec = tree.getPathForLocation(e.getX(), e.getY());
					if ( rutaNodoSelec != null ) {
						DefaultMutableTreeNode nodoSelec = (DefaultMutableTreeNode) rutaNodoSelec.getLastPathComponent();
						if ( nodoSelec.isLeaf() ) {
							String nombreArchivo = nodoSelec.getUserObject().toString();
							if ( nombreArchivo.equals("Terminal") ) {
								int respuesta = JOptionPane.showConfirmDialog(
				                        panel, "¿Deseas abrir la Terminal?","Abrir elemento",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				                    if (respuesta == JOptionPane.YES_OPTION) {
				                        new JFrameTerminal();
				                    }
							}
							if ( nombreArchivo.equals("Chat") ) {
								int respuesta = JOptionPane.showConfirmDialog(
				                        panel, "¿Deseas abrir el Chat?","Abrir elemento",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				                    if (respuesta == JOptionPane.YES_OPTION) {
				                        new JFrameChat();
				                    }
							}
						}
					}
				}
			}
		};
		return click;
	}
	
	public static void main(String[] args) {
		JFrameExploradorArchivos exploradorArchivos = new JFrameExploradorArchivos();
	}

}
