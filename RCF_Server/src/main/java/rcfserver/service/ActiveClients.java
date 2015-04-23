package rcfserver.service;

import java.util.Collection;
import java.util.Vector;

import rcfmodel.Client;
import rcfmodel.enums.StatusClient;
import rcfserver.bean.Server;
import rcfserver.model.enums.StatusServer;

public class ActiveClients implements Runnable{

	private final Server server = Server.getInstance();
	private Collection<Client> activeClients;
	
	@Override
	public void run() {
		if (server.getStatus() != StatusServer.ONLINE)
			return;
		
		while(RunServer.getClients() != null || !RunServer.getClients().isEmpty()){
			this.activeClients = new Vector<Client>();
			
			for(Client client : RunServer.getClients()){
				if(client.getStatus() == StatusClient.ACTIVE){
					this.activeClients.add(client);
				}
			}
			
			RunServer.setClients(activeClients);
		}
	}

	
	
}
