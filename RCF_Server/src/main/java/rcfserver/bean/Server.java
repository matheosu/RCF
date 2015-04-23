package rcfserver.bean;

import java.io.IOException;
import java.net.ServerSocket;

import rcfserver.exception.BeanException;
import rcfserver.model.enums.StatusServer;

/**
 * Classe Server responsável por iniciar o servidor em uma determinada porta;
 * Singleton
 * 
 * @author matheuscastro
 */
public class Server {

	private static final int MIN_PORT = 1000;
	private static final int MAX_PORT = 2000;

	private ServerSocket serverSocket;
	private int port;
	private StatusServer status;

	/**Singleton*/
	private static Server instance;
	private Server(){}

	private void setPort(int port) {
		if (Server.validPort(port))
			this.port = port;
		else
			throw new BeanException("Server:Invalid Port");
		
	}

	public int getPort(){
		return this.port;
	}
	
	public static boolean validPort(int port) {
		if (port == 0)
			return false;

		if (port >= MIN_PORT && port <= MAX_PORT)
			return true;
		
		return false;
	}

	public ServerSocket getServerSocket() {
		if (this.serverSocket != null)
			return this.serverSocket;

		return null;
	}
	
	public StatusServer getStatus() {
		return status;
	}

	public void setStatus(StatusServer status) {
		this.status = status;
	}

	/** Singleton */
	public static Server getInstance(){
		if(Server.instance == null)
			Server.instance = new Server();
		
		return Server.instance;
	}
	
	public void start(int port) {
		if(this.getStatus() == StatusServer.ONLINE)
			throw new BeanException("Server is starter in " + this.getPort());
		
		this.setPort(port);
		try {
			this.serverSocket = new ServerSocket(port);
			this.setStatus(StatusServer.ONLINE);
			System.out.println("Server Started in " + port);
		} catch (IOException ioE) {
			System.out.println("Error Server: " + ioE.getMessage());
			this.setStatus(StatusServer.ERROR);
//			ioE.printStackTrace();
		}
	}
	

	@Override
	public String toString() {
		return "Port:" + this.port + " - " + this.getStatus();
	}
}
