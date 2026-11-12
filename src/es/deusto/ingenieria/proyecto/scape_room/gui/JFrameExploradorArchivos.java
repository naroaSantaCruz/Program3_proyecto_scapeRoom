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
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private int dimensionImagenPeque = 25;
	private int dimensionImagenGrande = 40;
	
	private JButton btnSuperior;
	private JTextField barraBusqueda;
	
	private Map<String, List<String>> mapaTree;
	
	private ImageIcon flechaArriba = new ImageIcon("images/explorador_archivos/flecha-hacia-arriba.png");

	public JFrameExploradorArchivos() {
		this.setTitle("Explorador de archivos");
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		pSuperior.setBackground(new Color(220, 220, 220));
		
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
		pBoton.setOpaque(false);
		
		// Barra busqueda
		pBarraBusqueda = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
		pBarraBusqueda.setOpaque(false);
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
						this.setIcon(imagenEscalada("images/explorador_archivos/escritorio.png",dimensionImagenPeque));
					} else if ( nombreNodo.equals("Documentos") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/carpeta.png",dimensionImagenPeque));
					} else if ( nombreNodo.equals("Escritorio") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/escritorio (1).png",dimensionImagenPeque));
					} else if ( nombreNodo.equals("Papelera") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/papelera-de-reciclaje.png",dimensionImagenPeque));
					} else if ( nombreNodo.equals("Imágenes") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/imagen.png",dimensionImagenPeque));
					} else if ( nombreNodo.contains("Doc") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/google-docs.png",dimensionImagenPeque));
					} else if ( nombreNodo.toLowerCase().contains("borrado") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/borrar.png",dimensionImagenPeque));
					} else if ( nombreNodo.toLowerCase().contains("imagen") ) {
						this.setIcon(imagenEscalada("images/explorador_archivos/imagen.png",dimensionImagenPeque));
					} else if ( nombreNodo.equals("Chat") ) {
						this.setIcon(imagenEscalada("images/escritorio/chat.png",dimensionImagenPeque));
					} else if ( nombreNodo.equals("Terminal") ) {
						this.setIcon(imagenEscalada("images/escritorio/terminal.png",dimensionImagenPeque));
					} else {
						this.setIcon(imagenEscalada("images/explorador_archivos/expediente.png",dimensionImagenPeque));
					}
				}
				return this;
			}
		};
		treeArchivos.setCellRenderer(rendererTree);
		return treeArchivos;
	}
	
	private ImageIcon imagenEscalada(String path, int tamano) {
		ImageIcon imagenEscalada = new ImageIcon(path);
		return imagenEscalada = new ImageIcon(imagenEscalada.getImage().getScaledInstance(tamano, tamano, Image.SCALE_DEFAULT));
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
			if ( nodoParent.getUserObject().equals("Este equipo")) {
				JPanel nuevoPanel = crearPanelCentralEsteEquipo();
				panel.removeAll();
				panel.add(nuevoPanel, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();
			} else if ( nodoParent.getUserObject().equals("Escritorio")) {
				JPanel nuevoPanel = crearPanelEscritorio();
				panel.removeAll();
				panel.add(nuevoPanel, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();
			} else if ( nodoParent.getUserObject().equals("Documentos")) {
				JPanel nuevoPanel = crearPanelDocumentos();
				panel.removeAll();
				panel.add(nuevoPanel, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();
			} else if ( nodoParent.getUserObject().equals("Papelera")) {
				JPanel nuevoPanel = crearPanelPapelera();
				panel.removeAll();
				panel.add(nuevoPanel, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();
			} else if ( nodoParent.getUserObject().equals("Imágenes")) {
				JPanel nuevoPanel = crearPanelImagenes();
				panel.removeAll();
				panel.add(nuevoPanel, BorderLayout.CENTER);
				panel.revalidate();
				panel.repaint();
			}
		}
	}
	
	private MouseAdapter dobleClick(JTree tree,JPanel panel) {
		MouseAdapter click = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TreePath rutaNodoSelec = tree.getPathForLocation(e.getX(), e.getY());
				DefaultMutableTreeNode nodoSelec = (DefaultMutableTreeNode) rutaNodoSelec.getLastPathComponent();
				String nombreArchivo = nodoSelec.getUserObject().toString();
				if ( e.getClickCount() == 1 ) {
					if ( rutaNodoSelec != null ) {
						if ( !nodoSelec.isLeaf() ) {
							if ( nombreArchivo.equals("Este equipo")) {
								JPanel nuevoPanel = crearPanelCentralEsteEquipo();
								panel.removeAll();
								panel.add(nuevoPanel, BorderLayout.CENTER);
								panel.revalidate();
								panel.repaint();
							} else if ( nombreArchivo.equals("Escritorio")) {
								JPanel nuevoPanel = crearPanelEscritorio();
								panel.removeAll();
								panel.add(nuevoPanel, BorderLayout.CENTER);
								panel.revalidate();
								panel.repaint();
							} else if ( nombreArchivo.equals("Documentos")) {
								JPanel nuevoPanel = crearPanelDocumentos();
								panel.removeAll();
								panel.add(nuevoPanel, BorderLayout.CENTER);
								panel.revalidate();
								panel.repaint();
							} else if ( nombreArchivo.equals("Papelera")) {
								JPanel nuevoPanel = crearPanelPapelera();
								panel.removeAll();
								panel.add(nuevoPanel, BorderLayout.CENTER);
								panel.revalidate();
								panel.repaint();
							} else if ( nombreArchivo.equals("Imagenes")) {
								JPanel nuevoPanel = crearPanelImagenes();
								panel.removeAll();
								panel.add(nuevoPanel, BorderLayout.CENTER);
								panel.revalidate();
								panel.repaint();
							}
						}
					}
				} else if ( e.getClickCount() == 2 ) {
					if ( rutaNodoSelec != null ) {
						if ( nodoSelec.isLeaf() ) {
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
	
	private JPanel crearPanelCentralEsteEquipo() {
		JPanel panel = new JPanel( new GridLayout(2,1) );
		
		JPanel panelArriba = new JPanel( new BorderLayout() );
		panelArriba.setOpaque(false);
		JPanel panelElementos = new JPanel( new GridLayout(2,2) );
		panelElementos.setOpaque(false);
		JLabel labelTitulo = new JLabel("Acceso rápido");
		panelArriba.add(labelTitulo,BorderLayout.NORTH);
		JLabel panelEscritorio = new JLabel("Escritorio");
		establecerIconoLabel(panelEscritorio, true);
		JLabel panelDocumentos = new JLabel("Documentos");
		establecerIconoLabel(panelDocumentos, true);
		JLabel panelImagenes = new JLabel("Imágenes");
		establecerIconoLabel(panelImagenes, true);
		JLabel panelPapelera = new JLabel("Papelera");
		establecerIconoLabel(panelPapelera, true);
		panelElementos.add(panelEscritorio);
		panelElementos.add(panelDocumentos);
		panelElementos.add(panelImagenes);
		panelElementos.add(panelPapelera);
		panelArriba.setBorder(BorderFactory.createEmptyBorder(10,30,0,0));
		panelElementos.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		panelArriba.add(panelElementos, BorderLayout.CENTER);
		panel.add(panelArriba);

		JPanel panelAbajo = crearPanelArchivos("Archivos recientes");
		panel.add(panelAbajo);
		panel.setBackground(Color.WHITE);
		
		return panel;
	}
	
	private JPanel crearPanelEscritorio() {
		return crearPanelArchivos("Escritorio",mapaTree.get("Escritorio"));
	}
	
	private JPanel crearPanelDocumentos() {
		return crearPanelArchivos("Documentos",mapaTree.get("Documentos"));
	}
	
	private JPanel crearPanelPapelera() {
		return crearPanelArchivos("Papelera",mapaTree.get("Papelera"));
	}
	
	private JPanel crearPanelImagenes() {
		return crearPanelArchivos("Imágenes",mapaTree.get("Imágenes"));
	}
	
	private JPanel crearPanelArchivos(String titulo) {
		JLabel labelTitulo = new JLabel(titulo);
		JPanel panel = new JPanel( new BorderLayout() );
		panel.setOpaque(false);
		JPanel panelArchivos = new JPanel();
		panelArchivos.setBackground(Color.WHITE);
		panelArchivos.setLayout(new BoxLayout(panelArchivos, BoxLayout.Y_AXIS));
		for ( String carpeta: this.mapaTree.keySet() ) {
			for ( String archivo: mapaTree.get(carpeta) ) {
				JLabel labelArchivo = new JLabel(archivo);
				establecerIconoLabel(labelArchivo,false);
				labelArchivo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
				panelArchivos.add(labelArchivo);
			}
		}
		JScrollPane scroll = new JScrollPane(panelArchivos);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBackground(Color.WHITE);
		panel.add(labelTitulo,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		scroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		return panel;
	}
	
	private JPanel crearPanelArchivos(String titulo, List<String> archivos) {
		JLabel labelTitulo = new JLabel(titulo);
		JPanel panel = new JPanel( new BorderLayout() );
		panel.setOpaque(false);
		JPanel panelArchivos = new JPanel();
		panelArchivos.setBackground(Color.WHITE);
		panelArchivos.setLayout(new BoxLayout(panelArchivos, BoxLayout.Y_AXIS));
		for ( String archivo: archivos ) {
			JLabel labelArchivo = new JLabel(archivo);
			establecerIconoLabel(labelArchivo,false);
			labelArchivo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
			panelArchivos.add(labelArchivo);
		}
		JScrollPane scroll = new JScrollPane(panelArchivos);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBackground(Color.WHITE);
		panel.add(labelTitulo,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		scroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		return panel;
	}
	
	private void establecerIconoLabel( JLabel label, boolean grande ) {
		int tamano;
		if ( grande ) {
			tamano = dimensionImagenGrande;
		} else {
			tamano = dimensionImagenPeque;
		}
		String nombre = label.getText();
		if ( nombre.equals("Este equipo") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/escritorio.png",tamano));
		} else if ( nombre.equals("Documentos") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/carpeta.png",tamano));
		} else if ( nombre.equals("Escritorio") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/escritorio (1).png",tamano));
		} else if ( nombre.equals("Papelera") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/papelera-de-reciclaje.png",tamano));
		} else if ( nombre.equals("Imágenes") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/imagen.png",tamano));
		} else if ( nombre.contains("Doc") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/google-docs.png",tamano));
		} else if ( nombre.toLowerCase().contains("borrado") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/borrar.png",tamano));
		} else if ( nombre.toLowerCase().contains("imagen") ) {
			label.setIcon(imagenEscalada("images/explorador_archivos/imagen.png",tamano));
		} else if ( nombre.equals("Chat") ) {
			label.setIcon(imagenEscalada("images/escritorio/chat.png",tamano));
		} else if ( nombre.equals("Terminal") ) {
			label.setIcon(imagenEscalada("images/escritorio/terminal.png",tamano));
		} else {
			label.setIcon(imagenEscalada("images/explorador_archivos/expediente.png",tamano));
		}
	}
	
	public static void main(String[] args) {
		JFrameExploradorArchivos exploradorArchivos = new JFrameExploradorArchivos();
	}

}
