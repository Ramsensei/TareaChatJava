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
			try {
				String message = client.reader.readLine();
				Server.getInstance().sendMessage(message, number);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
