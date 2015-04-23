package rcfmodel.util;

public abstract class Validator {


	/**
	 * Valida se uma string é diferente de nula ou vazia;
	 * @param string: string a ser testada;
	 * @return true or false;
	 */
	public static boolean validString(String string){
		if(string == null)
			return false;

		if(string.isEmpty())
			return false;
		
		return true;
	}
	
}
