package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
	private JPanel mensaje; //JPanelMensaje
	private JPanel usu; //foto y nombre del usuario
	private JPanel escribir; //barra de escribir
	private JPanel panelConversacion;
	private JScrollPane scrollConversacion;
	
	private JLabel fotoPerfil;
	private JLabel fondoChat;
	private JLabel informacionChat;
	private JLabel nombreUsu;
	private JLabel emoji;
	private JLabel chatBloqueado;
	
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel principal
		chatApp = new JPanel(new BorderLayout());
		
		//panel lista de chats
		listaChats = new JPanel(new BorderLayout());
		listaChats.setBackground(Color.DARK_GRAY);
		listaChats.setPreferredSize(new Dimension(200,765));
		
		fotoPerfil = new JLabel();
		fotoPerfil.setIcon(imagenPerfil);
		fotoPerfil.setOpaque(false);
		
		nombreUsu = new JLabel("GhostKorp_Fj");
		nombreUsu.setForeground(Color.WHITE);
		nombreUsu.setFont(new Font("Romano", Font.ROMAN_BASELINE, 14));
		
		usu = new JPanel();
		usu.add(fotoPerfil, BorderLayout.WEST);
		usu.add(nombreUsu, BorderLayout.CENTER);
		usu.setOpaque(false);
		usu.setBorder(new LineBorder(Color.GREEN, 1));
		listaChats.add(usu, BorderLayout.NORTH);
		
		
		//barra info del chat
		infoChat = new JPanel();
		infoChat.setBackground(Color.BLACK);
		infoChat.setPreferredSize(new Dimension(1200, 35));
		
		informacionChat = new JLabel("Última conexión: hace 2 días. | Contacto desconocido. | Número de mensajes: 300.");
		informacionChat.setForeground(Color.GRAY);
		informacionChat.setFont(new Font("Arial", Font.HANGING_BASELINE, 16));
		
		infoChat.add(informacionChat);
		
		//panel del chat
		chat = new JPanel(new BorderLayout());
		
		panelConversacion = new JPanel();
		panelConversacion.setLayout(new BoxLayout(panelConversacion, BoxLayout.Y_AXIS));
		
		//añadir mensajes al JBox
		// (prueba)
		JPanelMensaje msg1 = new JPanelMensaje(0, "Hola, ¿tienes los códigos?");
		JPanelMensaje msg2 = new JPanelMensaje(1, "Sí, los tengo listos.");
	
		panelConversacion.add(msg1);
		panelConversacion.add(Box.createVerticalStrut(10));
		panelConversacion.add(msg2);
		panelConversacion.add(Box.createVerticalStrut(10));
		
		//añadir JBox al scroll
		scrollConversacion = new JScrollPane(panelConversacion);		
		
		//barra escribir
		escribir = new JPanel(new BorderLayout());
		escribir.setBackground(Color.WHITE);
		escribir.setPreferredSize(new Dimension(1000, 45));
		escribir.setBorder(new EmptyBorder(10,0,10,60));
		
		emoji = new JLabel();
		emoji.setIcon(imagenEmoji);
		emoji.setBorder(new EmptyBorder(10,20,10,60));
		
		chatBloqueado = new JLabel("GhostKorp_Fj te ha bloqueado");
		chatBloqueado.setForeground(Color.BLACK);
		chatBloqueado.setFont(new Font("chat", Font.TRUETYPE_FONT, 13));
		chatBloqueado.setBackground(Color.LIGHT_GRAY);
		chatBloqueado.setOpaque(true);
		chatBloqueado.setPreferredSize(new Dimension(600, 20));
		chatBloqueado.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		chatBloqueado.setHorizontalAlignment(SwingConstants.CENTER);
		chatBloqueado.setVerticalAlignment(SwingConstants.CENTER);
		
		escribir.add(emoji, BorderLayout.WEST);
		escribir.add(chatBloqueado, BorderLayout.CENTER);
		
		chat.add(escribir, BorderLayout.SOUTH);
		chat.add(scrollConversacion, BorderLayout.CENTER);
		
		//añadir elementos al panel principal
		chatApp.add(listaChats,BorderLayout.WEST);
		chatApp.add(infoChat, BorderLayout.NORTH);
		chatApp.add(chat, BorderLayout.CENTER);
		
		this.add(chatApp);
		
		this.setVisible(true);
	}

}
