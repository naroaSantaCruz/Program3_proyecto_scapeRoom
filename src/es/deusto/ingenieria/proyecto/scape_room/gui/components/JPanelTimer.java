package es.deusto.ingenieria.proyecto.scape_room.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class JPanelTimer extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel labelTimer;
	private HiloTimer hiloTimer;
	private final int TIEMPO_INICIO; //en segundos
	private int minutos;
	private int segundos;
	
	public JPanelTimer() {
		TIEMPO_INICIO = 1800;
		minutos = TIEMPO_INICIO/60;
		segundos = TIEMPO_INICIO%60;
		
		//Codigo extraido de una fuente externa (IA): ------------------
		//Fuente: Copilot
		//Código no modificado
		String tiempo = String.format("%02d:%02d", minutos, segundos);
		
		//---------------------------------------------------------------
		
		labelTimer = new JLabel(tiempo);
		labelTimer.setHorizontalAlignment(JLabel.CENTER);
		labelTimer.setVerticalAlignment(JLabel.CENTER);
		labelTimer.setForeground(Color.black);
		
		labelTimer.setFont(new Font("Console",Font.BOLD,30));
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.red.darker(),4));
		this.add(labelTimer, BorderLayout.CENTER);
		this.setOpaque(false);
		this.setSize(20,10);
		

	}
	
	public void iniciarTimer() {
		System.out.println("inicar hilo");
		hiloTimer = new HiloTimer();
		hiloTimer.start();
	}
	
	public void pausarReanudar() {
		if(hiloTimer.isPausado()) {
			System.out.println("Reanudar");
			hiloTimer.reanudar();
		}
		else {
			System.out.println("Pausar");
			hiloTimer.pausar();
		}
	}
		
	public void terminarHilo() {
		if (hiloTimer != null && hiloTimer.isAlive()) {
			System.out.println("Terminar hilo");
			hiloTimer.interrupt();
		}
	}
	
	public class HiloTimer extends Thread{
		private volatile boolean pausado;
		public HiloTimer() {
			
		}

		@Override
		public void run() {
			for (int i = TIEMPO_INICIO; i>=0; i--) {
				synchronized (this) {
					while(pausado) {
						try {
							wait();
						} catch (InterruptedException e) {
							return;
						}
					}
				}
				
				int minutos = i/60;
				int segundos = i%60;
				
				//Codigo extraido de una fuente externa (IA): ------------------
				//Fuente: Copilot
				//Código no modificado
				String tiempo = String.format("%02d:%02d", minutos, segundos);
				
				//---------------------------------------------------------------
				
				try {
					sleep(1000);
				}catch(InterruptedException e) {
					return;
				}
				SwingUtilities.invokeLater(()->labelTimer.setText(tiempo));
			}
			//PONER TEXTO FINAL DE EL ORDENADOR SE HA APAGADO
		}
		
		public synchronized void pausar() {
			pausado = true;
		}
		public synchronized void reanudar() {
			pausado = false;
			notify();
		}

		public boolean isPausado() {
			return pausado;
		}
	}
	
	public static void main(String[] args) {
		JFrame ventana = new JFrame("Prueba Timer");

        JPanelTimer panel = new JPanelTimer();

        ventana.add(panel);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setUndecorated(true);
        ventana.setSize(100, 50);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        panel.iniciarTimer();
	}
}
