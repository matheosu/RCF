package rcfserver.model.enums;

public enum StatusServer {

	NONE(0), ONLINE(1), OFFLINE(2), ERROR(3);
	
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 3;

	private int value;

	private StatusServer(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		if (value >= MIN_VALUE && value <= MAX_VALUE)
			this.value = value;
	}
	
	
	
}
