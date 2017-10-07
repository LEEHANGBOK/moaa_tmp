package user.domain;
//import java.util.Scanner;

public class DevideDir {
	/*public String code(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the drive code: ");
		
		String inputCode = scan.nextLine();
		
		return inputCode;
	}*/
	
	public String divDir(String input) {
		String value = input;
		String before = value.substring(0,2);
		String after = value.substring(3,value.length()-0);
		
		return before + "/" + after;
	}
}
