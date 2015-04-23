package rcfserver.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Vector;

import rcfmodel.Client;
import rcfmodel.enums.StatusClient;
import rcfserver.bean.Server;

public class RunServer implements Runnable {

	private static Collection<Client> listClients = new Vector<Client>();
	private final Server server = Server.getInstance();

	public RunServer(int port) {
			server.start(port);
	}

	@Override
	public void run() {
		while (true) {
			Socket socketClient = null;
			try {
				socketClient = server.getServerSocket().accept();
				ObjectInputStream inFromClient = new ObjectInputStream(socketClient.getInputStream());
				Client newClient = (Client) inFromClient.readObject();
				
				if(newClient.getActualIp() == socketClient.getInetAddress()){
					if(listClients.contains(newClient)){
						for(Client c : listClients){
							if(c.equals(newClient)){
								c.setStatus(StatusClient.ACTIVE);
								break;
							}
						}
						System.out.println("Update Status Client is connected:" + newClient);
					}else{
						newClient.setStatus(StatusClient.ACTIVE);
						listClients.add(newClient);
						System.out.println("New Client is connected:" + newClient);
					}
				}else{
					System.err.println("Diference IP");
				}
			} catch (IOException ioE) {
				System.err.println("RunServer Error: " + ioE.getMessage());
				ioE.printStackTrace();
			} catch (ClassNotFoundException cnfE) {
				System.err.println("RunServer Error: " + cnfE.getMessage());
				cnfE.printStackTrace();
			}
		}
	}

	public static Collection<Client> getClients() {
		if (!listClients.isEmpty())
			return listClients;
		return null;
	}

	public static void setClients(final Collection<Client> clients) {
		if (clients != null && clients.isEmpty())
			RunServer.listClients = clients;
	}

	public Server getServer() {
		return server;
	}

}
