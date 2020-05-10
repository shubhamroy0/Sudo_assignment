package HexEditor;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MyProgram {

	public static void main(String[] args) throws IOException {
		if(args.length != 1) {
			System.out.printf("You have entered wrong number of arguments. Please enter a file name like 'filename.txt'");
			return;
		}
		if (new File(args[0]).exists()) {
			System.out.printf("The file exists");
		} else {
			System.out.printf("The file %S dosn't exists. Would you like to create it ? (yes/no)",args[0]);
			String input = new Scanner(System.in).nextLine();
			if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
				boolean fileCreated = new File(args[0]).createNewFile();
				if (fileCreated) 
					System.out.printf("File created !");
				else
					System.out.printf("File creation failed !");
			}
		}
	}

}
