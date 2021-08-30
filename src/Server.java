package 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server {
	// Interface Attributes
	private JFrame chat_win = null;
	private JButton btn_send = null;
	private JTextField txt_message = null;
	private JTextArea area_chat = null;
	private JPanel panel_area_chat = null;
	private JPanel panel_btntxt = null;
	private JScrollPane scroll = null;
	
	//Communication Attributes
	private ServerSocket server = null;
	private Socket socket = null;
	
	 //Class Singleton Instance
	private static Server instance = null;
	
	
	public Server(int port) throws IOException {
		doInterface();
		this.server = new ServerSocket(port);
	}
	
	public static Server getInstance() throws IOException {
		if(instance == null) {
			instance = new Server(5555);
		}
		return instance;
	}
	
	public void doInterface() {
		// Constructors
		chat_win = new JFrame("Server");
		btn_send = new JButton("Send");
		txt_message = new JTextField(4);
		area_chat = new JTextArea(10, 12);
		scroll = new JScrollPane(area_chat);
		//First Panel
		panel_area_chat = new JPanel();
		panel_area_chat.setLayout(new GridLayout(1, 1));
		panel_area_chat.add(scroll);
		//Second Panel
		panel_btntxt = new JPanel();
		panel_btntxt.setLayout(new GridLayout(1, 2));
		panel_btntxt.add(txt_message);
		panel_btntxt.add(btn_send);
		//Main Frame
		chat_win.setLayout(new BorderLayout());
		chat_win.add(panel_area_chat, BorderLayout.NORTH);
		chat_win.add(panel_btntxt, BorderLayout.SOUTH);
		chat_win.setSize(300, 220);
		chat_win.setVisible(true);
		chat_win.setResizable(false);
		chat_win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Server.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
