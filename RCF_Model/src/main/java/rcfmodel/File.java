package rcfmodel;

import java.io.Serializable;

import rcfmodel.enums.StatusFile;
import rcfmodel.exception.ModelException;
import rcfmodel.util.SystemUtil;
import rcfmodel.util.Validator;

/**
 * Classe Arquivo implementando Serializable para poder transitar via sockets;
 * @author matheuscastro
 */

public class File implements Serializable{

	private static final long serialVersionUID = -7723410987381496076L;
	private static final String DOT = "\\.";

	private Long id;
	private String sourcePath;
	private transient String name;
	private transient String extent;
	private byte[] data;
	
	private StatusFile status;
	
	private Request request;
	
	public File(String sourcePath){
		this.setSourcePath(sourcePath);
		this.setName(this.getNameOfPath(sourcePath));
		this.setExtent(this.getExtentOfName(this.getName()));
		this.setStatus(StatusFile.NONE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		if(Validator.validString(sourcePath))
			this.sourcePath = sourcePath;
		else
			throw new ModelException("File:Invalid Path");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public StatusFile getStatus() {
		return status;
	}

	public void setStatus(StatusFile status) {
		this.status = status;
	}
	
	public Request getRequest() {
		return request;
	}

	/** Relationship BiDirectional */
	public void setRequest(Request request) {
		if(this.request == request)
			return;
		
		if(request == null){
			Request old = this.request;
			this.request = null;
			old.removeFile(this);
		} else {
			if(this.request != null)
				this.request.removeFile(this);
			
			this.request = request;
			request.addFile(this);
		}
	}

	/**
	 * Método que retorna o tamanho do arquivo;
	 * @return long contendo o tamanho do arquivo;
	 */
	public long getSize() {
		if(this.getData() == null)
			return -1;

		return this.getData().length;
	}
	
	/**
	 * Descobre o nome do arquivo de um determinado caminho;
	 * @param path: caminho a buscar o nome do arquivo;
	 * @return String contendo o nome do arquivo juntamente com sua extensão;
	 */
	private String getNameOfPath(String path){
		if(!Validator.validString(path))
			return null;
		
		String array[] = path.split(SystemUtil.getDivisor()); // TODO Reflection Discover: UNIX/MAC/WINDOWS - DONE
		int i = array.length;
		
		if(i>1)
			return array[i-1];
			
		return null;
	}
	
	/**
	 * Descobre a extensão do nome do arquivo;
	 * @param name: nome do arquivo a procurar a extensão;
	 * @return String contendo a extensão do arquivo;
	 */
	private String getExtentOfName(String name){
		if(!Validator.validString(name))
			return null;
		
		String array[] = name.split(File.DOT);
		int i = array.length;
		
		if(i>1)
			return array[i-1];
		
		return null;
	}
	

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		File other = (File) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
