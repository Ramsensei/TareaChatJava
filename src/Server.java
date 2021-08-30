import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	
	//Communication Attributes
	private ServerSocket server = null;
	private ArrayList<Client> clients;
	private int num_clients;
	
	//Class Singleton Instance
	private static Server instance = null;
	
	
	public Server(int port) throws IOException {
		this.server = new ServerSocket(port);
		clients = new ArrayList<Client>();
		num_clients = 0;
	}
	
	public static Server getInstance() throws IOException {
		if(instance == null) {
			instance = new Server(5555);
		}
		return instance;
	}
	
	public void close() throws IOException {
		server.close();
	}
	
	public boolean isConected() {
		return !server.isClosed();
	}
	
	public Socket acceptConection() throws IOException {
		return server.accept();
	}
	
	public int getNumClients() {
		return num_clients;
	}
	
	public void addClient(Client client) {
		clients.add(client);
		num_clients++;
	}
	
	public void sendMessage(String message, int emiter) {
		for(int i = 0; i<emiter; i++) {
			clients.get(i).writer.println("Client " + String.valueOf(emiter) + ":" + message);
			clients.get(i).setLastMessage(message);
		}
		for(int i = emiter+1; i<num_clients; i++) {
			clients.get(i).writer.println("Client " + String.valueOf(emiter) + ":" + message);
			clients.get(i).setLastMessage(message);
		}
	}
	

}
