package rcfmodel;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.UUID;

import rcfmodel.enums.StatusClient;
import rcfmodel.exception.ModelException;
import rcfmodel.util.Validator;

public class Client implements Serializable {

	private static final long serialVersionUID = 8385933644361683319L;

	private String uniqueId;
	private InetAddress actualIp;
	private StatusClient status;

	public Client(InetAddress ip) {
		this.generateUniqueId();
		this.setActualIp(ip);
		this.setStatus(StatusClient.NONE);
	}
	
	public Client(InetAddress ip, String uniqueId){
		this.setActualIp(ip);
		this.setUniqueID(uniqueId);
		this.setStatus(StatusClient.NONE);
	}

	public String getUniqueId() {
		return uniqueId;
	}
	
	public void setUniqueID(String uniqueId){
		if(validUniqueId(uniqueId))
			this.uniqueId = uniqueId;
		else
			throw new ModelException("Client:Invalid UniqueID");
	}

	public InetAddress getActualIp() {
		return actualIp;
	}

	public void setActualIp(InetAddress actualIp) {
		if (Request.validIpAddr(actualIp))
			this.actualIp = actualIp;
		else
			throw new ModelException("Client:Invalid ActualIP");
	}

	public StatusClient getStatus() {
		return status;
	}

	public void setStatus(StatusClient status) {
		this.status = status;
	}

	private void generateUniqueId() {
		UUID uniqueID = UUID.randomUUID();
		this.uniqueId = uniqueID.toString();
	}
	
	private boolean validUniqueId(String uniqueId){
		return Validator.validString(uniqueId);
	}

	@Override
	public String toString() {
		return this.getActualIp().getHostAddress() + " - "
				+ this.getActualIp().getHostName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}
}
