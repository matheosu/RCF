package rcfmodel.enums;

/**
 * Enum para representar as máquinas de estados existentes no objeto RequestFiles;
 * 
 * @author matheuscastro
 */
public enum StatusRequest {

	/**
	 * Status: Nenhum, Requisição Enviada, Requisição em Andamento, Requisição Concluida, Erro
	 */
	NONE(0), SEND(1), PROGRESS(2), DONE(3), ERROR(4);

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 4;

	private int value;

	private StatusRequest(int value) {
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
