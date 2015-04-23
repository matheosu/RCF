package rcfmodel.enums;

public enum OSType {
	LINUX("Linux"), MAC("Mac"), WINDOWS("Windows"), UNKNOW("Unknow");
	
	private String nameSystem;
	
	OSType(String nameSystem){
		this.setNameSystem(nameSystem);
	}
	
	public String getNameSystem(){
		return this.nameSystem;
	}
	
	public void setNameSystem(String nameSystem){
		this.nameSystem = nameSystem;
	}
}