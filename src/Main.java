import java.io.IOException;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		try {
			while(Server.getInstance().isConected()) {
				Socket socketClient = Server.getInstance().acceptConection();
				Client client = new Client(socketClient);
				Connection connection = new Connection(client, Server.getInstance().getNumClients());
				connection.start();
				Server.getInstance().addClient(client);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
