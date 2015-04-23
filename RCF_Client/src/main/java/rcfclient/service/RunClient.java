package rcfclient.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import rcfclient.bean.ActualClient;
import rcfmodel.enums.StatusClient;
import rcfmodel.util.PropertiesUtil;

public class RunClient {

	private static final URL URL_PATH = ActualClient.class.getResource("RunClient.class");
	private static final String FOLDER = "META-INF/";
	private static final String NAME_FILE = "Server.properties";
	private static final String PATH_AT_FOLDER = URL_PATH.getFile().substring(0, URL_PATH.getFile().indexOf("bin"))	+ FOLDER;
	private static final String PATH_SERVER = PATH_AT_FOLDER + NAME_FILE;
	private static final String KEY_IP = "IP";
	private static final String KEY_PORT="PORT";
	
	
	private static final String IP_SERVER = PropertiesUtil.getValue(PATH_SERVER, KEY_IP);
	private static final int PORT_SERVER = Integer.parseInt(PropertiesUtil.getValue(PATH_SERVER,KEY_PORT));
	
	private static Socket socket;
	
	private RunClient(){}

	private static boolean start(){
		if (socket == null) {
			try {
				socket = new Socket(InetAddress.getByName(IP_SERVER),PORT_SERVER);
				System.out.println("RunClient: Socket is connected to Server " + IP_SERVER +":" + PORT_SERVER);
				ActualClient.getClient().setStatus(StatusClient.ACTIVE);
			} catch (UnknownHostException uhE) {
				System.err.println("RunClient: " + uhE.getMessage());
				uhE.printStackTrace();
				return false;
			} catch (IOException ioE) {
				System.err.println("RunClient: " + ioE.getMessage());
				ioE.printStackTrace();
				return false;
			}
		}
			
		if(socket.isConnected())
			return true;
			
		return false;
	}
	
	public static Socket getSocket(){
		if(RunClient.socket == null)
			RunClient.start();
		
		return RunClient.socket;
	}
	
	public static void closedSocket(){
		if(RunClient.socket.isConnected())
			try {
				RunClient.socket.close();
			} catch (IOException ioE) {
				System.err.println("RunClient: " + ioE.getMessage());
				ioE.printStackTrace();
			}
	}
}
