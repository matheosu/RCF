package rcfmodel.enums;

public enum StatusClient {

	
	/**
	 * Status: Nenhum, Cliente Ativo, Client Inativo, Erro
	 */
	NONE(0), ACTIVE(1), INACTIVE(2), ERROR(3);

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 3;

	private int value;

	private StatusClient(int value) {
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
