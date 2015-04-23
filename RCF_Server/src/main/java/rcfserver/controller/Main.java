package rcfserver.controller;

import rcfmodel.Client;
import rcfserver.service.RunServer;

public class Main {

	public static void main(String[] args) {
		RunServer run = new RunServer(1234);
		Thread threadServer = new Thread(run);
		threadServer.start();

		if (RunServer.getClients() == null || RunServer.getClients().isEmpty())
			System.out.println("No Connected Client");

		if (RunServer.getClients() != null) {
			for (Client c : RunServer.getClients()) {
				System.out.println("New Client Connected" + c);
			}
		}
		
		
	}
}
