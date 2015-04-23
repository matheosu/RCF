package rcfserver.service;

import java.net.ServerSocket;

import rcfserver.exception.ServiceException;

public class Server {
	
	private static final int MIN_PORT=1000;
	private static final int MAX_PORT=2000;

	private ServerSocket servidor;

	private int porta;
	
	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		if (Server.validarPorta(porta))
			this.porta = porta;
		else
			throw new ServiceException("Está porta é inválida");
	}

	public static boolean validarPorta(int porta) {
		if (porta == 0)
			return false;

		if (porta >= MIN_PORT && porta <= MAX_PORT)
			return true;

		return false;
	}
}
