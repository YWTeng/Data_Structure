public class MathExpr {
	
	public static double parse(String str){
		
		String s1, s2;
		Double Final;
		while (str.indexOf(' ') != -1) {
			int place = str.indexOf(' ');
			if (Character.isDigit(str.charAt(place - 1)) && Character.isDigit(str.charAt(place + 1))) {
				str = "NaN";
				break;
			}
			s1 = str.substring(0, place);
			s2 = str.substring(place + 1);
			str = s1 + s2;
		}
		
		try {
			while (str.indexOf(')') != -1) {
				int place1 = str.indexOf(')');
				int place2 = str.lastIndexOf('(', place1);
				s1 = str.substring(0, place2);
				if (place1!=str.length()) 
					s2 = str.substring(place1 + 1);
				else
					s2 = "";
				String content = str.substring(place2 + 1, place1);
				str = s1 + calculate(content) + s2;
			}
			Final = calculate(str);
		}
		catch (NumberFormatException ex) {
			Final =  0.0/0;
		}
		return Final;
	}
	
	public static double calculate(String content) {
		
		int place, i, place1, place2;
		String c1, c2;
		double number, sol, num1, num2;
		
		while (content.indexOf("sin") != -1) {
			place = content.indexOf("sin");
			c1 = content.substring(0, place);
			i = place + 3;
			if (content.charAt(i) == '-')
				i++;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == content.length() - 1) break;
				i++;
			}
			if (i != content.length() - 1) {
				c2 = content.substring(i);
				number = Double.parseDouble(content.substring(place + 3, i));
			}
			else {
				c2 = "";
				number = Double.parseDouble(content.substring(place + 3));
			}
			sol = Math.sin(number);
			content = c1 + sol + c2;
		}
		
		while (content.indexOf("cos") != -1) {
			place = content.indexOf("cos");
			c1 = content.substring(0, place);
			i = place + 3;
			if (content.charAt(i) == '-')
				i++;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == content.length() - 1) break;
				i++;
			}
			if (i != content.length() - 1) {
				c2 = content.substring(i);
				number = Double.parseDouble(content.substring(place + 3, i));
			}
			else {
				c2 = "";
				number = Double.parseDouble(content.substring(place + 3));
			}
			sol = Math.cos(number);
			content = c1 + sol + c2;
		}
		
		while (content.indexOf("tan") != -1) {
			place = content.indexOf("tan");
			c1 = content.substring(0, place);
			i = place + 3;
			if (content.charAt(i) == '-')
				i++;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == content.length() - 1) break;
				i++;
			}
			if (i != content.length() - 1) {
				c2 = content.substring(i);
				number = Double.parseDouble(content.substring(place + 3, i));
			}
			else {
				c2 = "";
				number = Double.parseDouble(content.substring(place + 3));
			}
			sol = Math.tan(number);
			content = c1 + sol + c2;
		}
		
		while (content.indexOf("sqrt") != -1) {
			place = content.indexOf("sqrt");
			c1 = content.substring(0, place);
			i = place + 4;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == content.length() - 1) break;
				i++;
			}
			if (i != content.length() - 1) {
				c2 = content.substring(i);
				number = Double.parseDouble(content.substring(place + 4, i));
			}
			else {
				c2 = "";
				number = Double.parseDouble(content.substring(place + 4));
			}
			sol = Math.sqrt(number);
			content = c1 + sol + c2;
		}
		
		while (content.indexOf('*') != -1 || content.indexOf('/') != -1) {
			place1 = content.indexOf('*');
			place2 = content.indexOf('/');
			if (place1 != -1 && place2 != -1)
				place = Math.min(place1, place2);
			else if (place1 == -1)
				place = place2;
			else
				place = place1;
			i = place - 1;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == 0) break;
				i--;
			}
			if (i != 0) {
				c1 = content.substring(0, i + 1);
				num1 = Double.parseDouble(content.substring(i+1, place));
			}
			else {
				c1 = "";
				num1 = Double.parseDouble(content.substring(0, place));
			}
			i = place + 1;
			if (content.charAt(i) == '-')
				i++;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == content.length() - 1) break;
				i++;
			}
			if (i != content.length() - 1) {
				c2 = content.substring(i);
				num2 = Double.parseDouble(content.substring(place + 1, i));
			}
			else {
				c2 = "";
				num2 = Double.parseDouble(content.substring(place + 1));
			}
			if (place1 != -1 && place2 != -1)
				if (place1 < place2)
					sol = num1 * num2;
				else
					sol = num1 / num2;
			else if (place1 == -1)
				sol = num1 / num2;
			else
				sol = num1 * num2;
			content = c1 + sol + c2;
		}
		
		while (content.indexOf('+', 1) != -1 || content.indexOf('-', 1) != -1) {
			place1 = content.indexOf('+', 1);
			place2 = content.indexOf('-', 1);
			if (place1 != -1 && place2 != -1)
				place = Math.min(place1, place2);
			else if (place1 == -1)
				place = place2;
			else
				place = place1;
			i = place - 1;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == 0) break;
				if (i == 1 && content.charAt(i) == '-') {
					i--;
					break;
				}
				i--;
			}
			if (i != 0) {
				c1 = content.substring(0, i + 1);
				num1 = Double.parseDouble(content.substring(i+1, place));
			}
			else {
				c1 = "";
				num1 = Double.parseDouble(content.substring(0, place));
			}
			i = place + 1;
			if (content.charAt(i) == '-')
				i++;
			while (Character.isDigit(content.charAt(i)) || content.charAt(i) == '.') {
				if (i == content.length() - 1) break;
				i++;
			}
			if (i != content.length() - 1) {
				c2 = content.substring(i);
				num2 = Double.parseDouble(content.substring(place + 1, i));
			}
			else {
				c2 = "";
				num2 = Double.parseDouble(content.substring(place + 1));
			}
			if (place1 != -1 && place2 != -1)
				if (place1 < place2)
					sol = num1 + num2;
				else
					sol = num1 - num2;
			else if (place1 == -1)
				sol = num1 - num2;
			else
				sol = num1 + num2;
			content = c1 + sol + c2;
		}
		
		return Double.parseDouble(content);
	}
	
}
