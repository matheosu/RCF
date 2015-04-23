package rcfmodel.util;

public abstract class ExceptionUtil {

	public static String getMessage(String nameException, String msg) {
		String finalMsg = "[" + nameException + " - ";

		if (msg != null && !msg.isEmpty()) {
			String[] array = null;
			if (msg.contains(":")) {
				array = msg.split(":");
				finalMsg += array[0];
				finalMsg += "]";
				finalMsg += " " + array[1];
			} else {
				finalMsg += "]";
				finalMsg += msg;
			}
		} else {
			finalMsg += "]";
		}

		return finalMsg;

	}
}
