package rico.demo.domain;

public class Validate {
	
	/**
	 * “空”
	 * NULL
	 * ""
	 * "      "
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		/**
		 * &&
		 * ||
		 * --------------------------
		 * &
		 * |
		 * -------------------------
		 * 
		 * x=5
		 * if(x < 10 || x++ < 12 ||  x++ > 0) {
		 * 	
		 * }
		 * x = ?
		 */
		if(str == null || str.trim().equals("") || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNumber(String str) {
		if(str == null) {
			return false;
		}
		
		try {
			Integer.parseInt(str);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isAgeOK(int age, int min, int max) {
		if(age >= min && age <= max) {
			return true;
		}
		
		return false;
	}

}
