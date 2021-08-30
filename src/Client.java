import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	// Interface Properties
	JFrame chat_win = null;
	JButton btn_send = null;
	JTextField txt_message = null;
	JTextArea area_chat = null;
	JPanel panel_area_chat = null;
	JPanel panel_btntxt = null;
	JScrollPane scroll = null;
	
	//Communication Attributes
	Socket socket = null;
	BufferedReader reader = null;
	PrintWriter writer = null;
	String last_message = null;
	int number = 0;
	
	public Client(Socket socket) throws IOException {
		//doInterface();
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		writer = new PrintWriter(this.socket.getOutputStream(), true);
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isConnected(){
		return !socket.isClosed();
	}
	
	public void setLastMessage(String message) {
		last_message = message;
		autoRespond(last_message);
	}
	
	public void autoRespond(String message) {
		String productValue = "", porcentage = "", productWeight = "";
		int n = 1;
		for(int i = 0; i < message.length(); i++) {
			
			if (Character.isDigit(message.charAt(i))) {
				switch(n) {
				case 1:
					productValue += String.valueOf(message.charAt(i));
					break;
				case 2:
					porcentage += String.valueOf(message.charAt(i));
					break;
				case 3:
					productWeight += String.valueOf(message.charAt(i));
					break;
				}
			}
			else if(message.charAt(i) == ',') {
				n++;
			}
			else if(message.charAt(i) == ' ') {
				
			}
			else {
				return;
			}
		}
		if(productValue.isEmpty()) {
			productValue = "0";
		}
		if(porcentage.isEmpty()) {
			productValue = "0";
		}
		if(productWeight.isEmpty()) {
			productValue = "0";
		}
		float value = (float) ((Float.parseFloat(productValue)*Float.parseFloat(porcentage)/100)+(Float.parseFloat(productWeight)*0.15));
		try {
			String response = String.valueOf(value);
			Server.getInstance().sendMessage(response, number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void doInterface() {
		// Constructors
		chat_win = new JFrame("Client");
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

}
