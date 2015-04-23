package rcfclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rcfclient.bean.ActualClient;
import rcfmodel.enums.TypeRequest;

public class Listener implements Runnable {

	@Override
	public void run() {
		
		/** Abre o inFromServer (O que o servidor vai mandar) */
		ObjectInputStream inFromServer = null;
		try {
			inFromServer = new ObjectInputStream(RunClient.getSocket()
					.getInputStream());
			System.out.println("Listener: inFromServer is open");
		} catch (IOException ioE) {
			System.err.println("Listener: " + ioE.getMessage());
			ioE.printStackTrace();
		}

		TypeRequest whatServerNeed = TypeRequest.NONE;

		/** Lê o que o servidor precisa */
		try {
			whatServerNeed = (TypeRequest) inFromServer.readObject();
			System.out.println("Listener: whatServerNeed is ready");
		} catch (IOException ioE) {
			System.err.println("Listener: " + ioE.getMessage());
			ioE.printStackTrace();
		} catch (ClassNotFoundException cnfE) {
			System.err.println("Listener: " + cnfE.getMessage());
			cnfE.printStackTrace();
		}
		
		/** Fecha o inFromServer */
		try {
			inFromServer.close();
			System.out.println("Listener: inFromServer is closed");
		} catch (IOException ioE) {
			System.err.println("Listener: " + ioE.getMessage());
			ioE.printStackTrace();
		}
		
		/** Abre o outToServer (o que vamos mandar para o servidor) */
		ObjectOutputStream outToServer = null;
		try {
			 outToServer = new ObjectOutputStream(RunClient.getSocket().getOutputStream());
			 System.out.println("Listener: outToServer is open");
		} catch (IOException ioE) {
			System.err.println("Listener: " + ioE.getMessage());
			ioE.printStackTrace();
		}
		
		/** De acordo com o que servidor precisa nos enviamos */
		switch (whatServerNeed) {
			case VERIFY_ACTIVE_CLIENT:
				/** Envia o StatusClient do atual client */
				try {
					outToServer.writeObject(ActualClient.getClient().getStatus());
					System.out.println("Listener: Send StatusClient("+ActualClient.getClient().getStatus()+") for Server");
				} catch (IOException ioE) {
					System.err.println("Listener: " + ioE.getMessage());
					ioE.printStackTrace();
				}
				break;
			case REQUEST_FILES:
				
				break;
			default:
				return;
		}
		
		/** Fecha o outToServer */
		try {
			outToServer.close();
			System.out.println("Listener: outToServer is closed");
		} catch (IOException ioE) {
			System.err.println("Listener: " + ioE.getMessage());
			ioE.printStackTrace();
		}
	}

}
