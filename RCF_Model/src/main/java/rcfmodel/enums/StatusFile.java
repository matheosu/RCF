package rcfmodel.enums;

/**
 * Enum para representar as máquinas de estados existentes no objeto File;
 * 
 * @author matheuscastro
 */
public enum StatusFile {

	/**
	 * Status: Nenhum, Enviando Arquivo, Arquivo Enviado, Erro
	 */
	NONE(0), UPLOADING(1), UPLOADED(2), ERROR(3);

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 3;

	private int value;

	private StatusFile(int value) {
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
