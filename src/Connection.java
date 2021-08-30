import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Connection extends Thread{
	
	//Attributes
	Client client = null;
	int number;
	
	public Connection(Client client, int number) {
		this.client = client; 
		this.number = number;
	}
	
	@Override
	public void run() {
		client.writer.println("Bienvenido");
		while (client.isConnected()) {
			client.btn_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String message = client.txt_message.getText();
					Server.getInstance().sendMessage(message, number);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			});
		}
	}
	
}
