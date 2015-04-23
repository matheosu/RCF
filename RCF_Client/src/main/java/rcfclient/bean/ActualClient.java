package rcfclient.bean;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import rcfmodel.Client;
import rcfmodel.util.PropertiesUtil;

public class ActualClient {

	static final URL URL_PATH = ActualClient.class.getResource("ActualClient.class");
	static final String FOLDER = "META-INF/";
	static final String NAME_FILE = "UUID.properties";
	static final String PATH_AT_FOLDER = URL_PATH.getFile().substring(0, URL_PATH.getFile().indexOf("bin"))	+ FOLDER;
	static final String PATH_UUID = PATH_AT_FOLDER + NAME_FILE;
	static final String KEY = "UUID";

	/** Singleton */
	private static Client instance;

	/** Singleton */
	private ActualClient() {
	}

	/** The Only Way To Get An Instance */
	public static Client getClient() {
		if (ActualClient.instance == null) {
			try {
				String value = PropertiesUtil.getValue(PATH_UUID, KEY);
				ActualClient.instance = new Client(InetAddress.getLocalHost());

				if (value == null) {
					PropertiesUtil.setValue(PATH_UUID, KEY,
							ActualClient.instance.getUniqueId());
				}
				ActualClient.instance.setUniqueID(value);
			} catch (UnknownHostException uhE) {
				System.out.println("ActualClient: Error IPAddress "
						+ uhE.getMessage());
				uhE.printStackTrace();
			}
		}

		return ActualClient.instance;
	}
}
