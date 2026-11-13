package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import es.deusto.ingenieria.proyecto.scape_room.gui.components.JPanelMensaje;

public class JFrameChat extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel chatApp; //panel principal
	private JPanel listaChats; //panel izquierda
	private JPanel infoChat; //linea north con la informacion sobre el chat
	private JPanel chat; //conversacion y boton escribir
	private JButton usu; //foto y nombre del usuario
	private JPanel pUsu;
	private JPanel escribir; //barra de escribir
	private JPanel panelConversacion;
	private JScrollPane scrollConversacion;
	
	private JLabel fotoPerfil;
	private JLabel fondoChat;
	private JLabel informacionChat;
	private JLabel nombreUsu;
	private JLabel emoji;
	private JLabel chatBloqueado;
	private JLabel textoPrev;
	private JLabel textoPost;
	
	private String rutaFich = "documents/chat.csv";
	
	private ImageIcon imagenOriPerfil = new ImageIcon("images/chat_fjerda/fotoPerfilCuervo.png");
	private Image imagenRedimensionadaPerfil = imagenOriPerfil.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	private ImageIcon imagenPerfil = new ImageIcon(imagenRedimensionadaPerfil);
	
	private ImageIcon imagenOriEmoji = new ImageIcon("images/chat_fjerda/simboloEmojis.png");
	private Image imagenReEmoji = imagenOriEmoji.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private ImageIcon imagenEmoji = new ImageIcon(imagenReEmoji);
	
	
	

	public JFrameChat() {
		this.setTitle("BlackBox Chat");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//panel principal
		chatApp = new JPanel(new BorderLayout());
		
		//panel lista de chats
		listaChats = new JPanel(new BorderLayout());
		listaChats.setBackground(new Color(128, 71, 43));
		listaChats.setPreferredSize(new Dimension(200,765));
		
		fotoPerfil = new JLabel();
		fotoPerfil.setIcon(imagenPerfil);
		fotoPerfil.setOpaque(false);
		
		nombreUsu = new JLabel("GhostKorp_Fj");
		nombreUsu.setForeground(Color.WHITE);
		nombreUsu.setFont(new Font("Romano", Font.ROMAN_BASELINE, 14));
		
		usu = new JButton();
		usu.setPreferredSize(new Dimension(200, 67));
		usu.setBackground(new Color(128, 71, 43));
		pUsu = new JPanel();
		pUsu.add(fotoPerfil, BorderLayout.WEST);
		pUsu.add(nombreUsu, BorderLayout.CENTER);
		pUsu.setOpaque(false);
		usu.add(pUsu);
		
		//action listeners del boton para visualizar la conversacion
		usu.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				usu.setBackground(new Color(209, 116, 70));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				usu.setBackground(new Color(128, 71, 43));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				usu.setBackground(new Color(209, 116, 70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				usu.setBackground(new Color(128, 71, 43));
			}
			
		});
		
		usu.addActionListener(e -> {
			if(chat.isVisible()) {
				chat.setVisible(false);
				infoChat.setVisible(false);
			} else {
				chat.setVisible(true);
				infoChat.setVisible(true);
			}
			
		});
		listaChats.add(usu, BorderLayout.NORTH);
		
		
		//barra info del chat
		infoChat = new JPanel();
		infoChat.setBackground(Color.BLACK);
		infoChat.setPreferredSize(new Dimension(1200, 35));
		
		informacionChat = new JLabel("Última conexión: hace 2 días. | Contacto desconocido. | Número de mensajes: 300.");
		informacionChat.setForeground(Color.GRAY);
		informacionChat.setFont(new Font("Arial", Font.HANGING_BASELINE, 16));
		
		infoChat.add(informacionChat);
		infoChat.setVisible(false);
		
		//panel del chat
		chat = new JPanel(new BorderLayout());
		
		fondoChat = new JLabel();
		fondoChat.setIcon(new ImageIcon("images/chat_fjerda/fondoChat2.jpeg"));
		fondoChat.setLayout(new BorderLayout());
		
		panelConversacion = new JPanel();
		panelConversacion.setLayout(new BoxLayout(panelConversacion, BoxLayout.Y_AXIS));
		panelConversacion.setOpaque(false);
		
		//añadir mensajes al JBox
		textoPrev = new JLabel("--------------------------- Mensajes previos no disponibles ---------------------------");
		textoPrev.setForeground(Color.WHITE);
		textoPrev.setFont(new Font("Monospaced", Font.BOLD, 15));
		textoPrev.setAlignmentX(CENTER_ALIGNMENT);
		textoPrev.setBorder(new EmptyBorder(50,0,50,0));
		panelConversacion.add(textoPrev);
		
		File converFich = new File(rutaFich);
		try {
			Scanner leerFich = new Scanner(converFich);
			while (leerFich.hasNextLine()) {
				String[] mensajeData = leerFich.nextLine().split(";");
				Integer usu = Integer.valueOf(mensajeData[0]);
				String[] textoMensaje = mensajeData[1].split("_");
				JPanelMensaje msg = new JPanelMensaje(usu, textoMensaje);
				panelConversacion.add(msg);
				panelConversacion.add(Box.createVerticalStrut(10));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//añadir mensaje con archivo con clave
		
		textoPost = new JLabel("--------------------------- chat bloqueado ---------------------------");
		textoPost.setForeground(Color.WHITE);
		textoPost.setFont(new Font("Monospaced", Font.BOLD, 15));
		textoPost.setAlignmentX(CENTER_ALIGNMENT);
		textoPost.setBorder(new EmptyBorder(50,0,50,0));
		panelConversacion.add(textoPost);
		
		//añadir JBox al scroll
		scrollConversacion = new JScrollPane(panelConversacion);
		scrollConversacion.setOpaque(false);
		scrollConversacion.getViewport().setOpaque(false);
		
		fondoChat.add(scrollConversacion, BorderLayout.CENTER);
		
		//barra escribir
		escribir = new JPanel(new BorderLayout());
		escribir.setBackground(new Color(222, 195, 224));
		escribir.setPreferredSize(new Dimension(1000, 45));
		escribir.setBorder(new EmptyBorder(10,0,10,60));
		
		emoji = new JLabel();
		emoji.setIcon(imagenEmoji);
		emoji.setBorder(new EmptyBorder(10,20,10,60));
		
		chatBloqueado = new JLabel("GhostKorp_Fj te ha bloqueado");
		chatBloqueado.setForeground(Color.BLACK);
		chatBloqueado.setFont(new Font("chat", Font.TRUETYPE_FONT, 13));
		chatBloqueado.setBackground(new Color(198, 158, 201));
		chatBloqueado.setOpaque(true);
		chatBloqueado.setPreferredSize(new Dimension(600, 20));
		chatBloqueado.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		chatBloqueado.setHorizontalAlignment(SwingConstants.CENTER);
		chatBloqueado.setVerticalAlignment(SwingConstants.CENTER);
		
		escribir.add(emoji, BorderLayout.WEST);
		escribir.add(chatBloqueado, BorderLayout.CENTER);
		
		chat.add(escribir, BorderLayout.SOUTH);
		chat.add(fondoChat, BorderLayout.CENTER);
		chat.setVisible(false);
		
		//añadir elementos al panel principal
		chatApp.add(listaChats,BorderLayout.WEST);
		chatApp.add(infoChat, BorderLayout.NORTH);
		chatApp.add(chat, BorderLayout.CENTER);
		
		this.add(chatApp);
		
		this.setVisible(true);
	}


}
