package es.deusto.ingenieria.proyecto.scape_room.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class JFrameChat extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel chatApp; //panel principal
	private JPanel listaChats; //panel izquierda
	private JPanel infoChat; //linea north con la informacion sobre el chat
	private JPanel chat; //conversacion y boton escribir
	private JPanel conversacion; //panel tipo scroll con mensajes
	private JPanel mensaje; //JPanelMensaje
	private JPanel usu; //foto y nombre del usuario
	
	private JLabel fotoPerfil;
	private JLabel fondoChat;
	private JLabel informacionChat;
	private JLabel nombreUsu;
	
	private ImageIcon imagenOriPerfil = new ImageIcon("images/chat_fjerda/fotoPerfilCuervo.png");
	private Image imagenRedimensionada = imagenOriPerfil.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	private ImageIcon imagenPerfil = new ImageIcon(imagenRedimensionada);
	

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
		
		
		//añadir elementos al panel principal
		chatApp.add(listaChats,BorderLayout.WEST);
		chatApp.add(infoChat, BorderLayout.NORTH);
		
		this.add(chatApp);
		
		this.setVisible(true);
	}

}
