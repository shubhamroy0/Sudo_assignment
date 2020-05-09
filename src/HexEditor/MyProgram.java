package HexEditor;

import java.io.File;

public class MyProgram {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.printf("You have entered wrong number of arguments. Please enter a file name like 'filename.txt'");
			return;
		}
		if (new File(args[0]).exists()) {
			System.out.printf("The file exists");
		} else {
			System.out.printf("The file osn't exists");
		}
	}

}
