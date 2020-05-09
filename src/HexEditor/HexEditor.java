package HexEditor;
import java.lang.*;
import java.text.DecimalFormat; 

public class HexEditor {
	public static void repeat(String str, int count) {
		if(count == 1) {
			System.out.printf(str);
		}
		else {
			System.out.printf(str);
			repeat(str,--count);
		}
	}
	public static void main (String args[]) throws Exception {
//		repeat("hello",3);
//		System.out.println();
//		formatSize(2147483647);
//		System.out.println(formatSize(2147483647));		
//		System.out.println(formatSize(123));
//		System.out.println(formatSize(83647));
//		long x = Long.parseLong("188900977659375");
//		System.out.println(formatSize(x));
//		long y = Long.parseLong("9585631");
//		System.out.println(formatSize(y));
		printHexTable(7);
	}
	public static String formatSize(long sizeInBytes) {
		String rep[] = {"bytes","KiB","MiB","GiB","TiB","PiB","EiB"};
		double exp = Math.log10((double)sizeInBytes)/Math.log10(2.0);
		int power = (int)Math.floor(exp/10);
		if (power == 0) {
			String Finalnum = sizeInBytes+" "+rep[power];
			return Finalnum;
		}
		float newnum = (float)(Math.round(Math.pow(2,exp - (10.0*power)) * 100) / 100.0);
		String Finalnum = String.format("%.02f",newnum)+" "+rep[power];
		return Finalnum;
	}
	public static void printHexTable(int rownum) {
		System.out.printf("  H |");
		for(int i=0;i<16;i++) {
			System.out.printf(" %02X |", i);
		}
		
		System.out.println("\n-------------------------------------------------------------------------------------");
		for(int i=rownum;i<(rownum + 16);i++) {
			System.out.printf(" %02X | \n", i);
		}
	}
}