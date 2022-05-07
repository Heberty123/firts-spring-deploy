package transacao.Service;

public class Convert {

	public static Integer toInteger(String string) {
		if(string.isEmpty()) {
			return null;
		}
		return Integer.parseInt(string);
	}
	
	
	public static Double toDouble(String string) {
		if(string.isEmpty()) {
			return null;
		}
		return Double.parseDouble(string);
	}
}
