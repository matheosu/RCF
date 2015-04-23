package rcfmodel.enums;

public enum TypeRequest {

	NONE(0), VERIFY_ACTIVE_CLIENT(1), REQUEST_FILES(2);
	

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 2;

	private int value;

	private TypeRequest(int value) {
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
