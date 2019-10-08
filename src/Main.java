import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated metod stub

		Stack obj= new Stack();
		//the get the user Name
		File file=new File("/home");
		File[] filesName= file.listFiles();
		String userName=filesName[0].getName();
		Scanner scan= new Scanner(System.in);
		System.out.print(userName+":~$ ");
		String input=scan.nextLine();
		String[] inputBreak;
		
		while(!input.equalsIgnoreCase("exit")){
			
			obj.input(input);
			System.out.print(userName+":~"+obj.getHead().getAddress()+"$ ");
			input=scan.nextLine();
			
		}
		
	
	}

}
