package rcfmodel;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.List;

import rcfmodel.enums.StatusClient;
import rcfmodel.enums.StatusRequest;
import rcfmodel.exception.ModelException;

/**
 * Classe Requisição de arquivos para um determinado ip;
 * @author matheuscastro
 */
public class Request implements Serializable{

	private static final long serialVersionUID = -5541051327361912960L;
	
	private Long id;
	private Client client;
	private Calendar date;
	private StatusRequest status;
	
	private List<File> files;
	
	public Request(Client client, List<File> files){
		this.setClient(client);
		this.setFiles(files);
		this.setTimeStamp(Calendar.getInstance());
		this.setStatus(StatusRequest.NONE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		if(Request.validClient(client))
			this.client = client;
		else
			throw new ModelException("Request:Client is null or is not active");
	}

	private static boolean validClient(Client client) {
		if(client == null)
			return false;
		
		if(client.getStatus() != StatusClient.ACTIVE )
			return false;
			
		return true;
	}

	public Calendar getTimeStamp() {
		return date;
	}

	public void setTimeStamp(Calendar timeStamp) {
		this.date = timeStamp;
	}
	
	public StatusRequest getStatus() {
		return status;
	}

	public void setStatus(StatusRequest status) {
		this.status = status;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		if(Request.validFiles(files))
			this.files = files;
		else
			throw new ModelException("Request:Files is empty or null");
	}
	
	/** Relationship BiDirectional */
	public void addFile(File file){
		if(this.files.contains(file))
			return;
		
		this.files.add(file);
		file.setRequest(this);
	}
	
	/** Relationship BiDirectional */
	public void removeFile(File file){
		if(! this.files.contains(file))
			return;
		
		this.files.remove(file);
		file.setRequest(null);
	}
	
	public static boolean validIpAddr(InetAddress ip){
		if(ip == null)
			return false;
		
		return true;
	}
	
	public static boolean validFiles(List<File> files){
		if(files == null)
			return false;
		
		if(files.isEmpty())
			return false;
		
		return true;
	}
	
}
