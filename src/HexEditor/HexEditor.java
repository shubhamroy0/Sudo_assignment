package HexEditor;
import java.io.IOException;
import java.io.RandomAccessFile;
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
		RandomAccessFile raf= new RandomAccessFile("DAT_files/a.dat","rw");
		printHexTableWithFile(raf ,5);
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
		for (int i=rownum;i<=(16+rownum);i++) {
			for (int j=0;j<=16;j++) {
				if (i == rownum) {
					System.out.printf((j == 0) ? "  H |" : " %02X |", (j-1));
				} else {
					if(j == 0) {
						System.out.printf(" %02X |",(i-1));
					}
				}
			}
			if(i == rownum) {
				System.out.println("\n-------------------------------------------------------------------------------------");
			} else {
				System.out.println();
			}
		}
	}
	
	public static void printHexTableWithFile(RandomAccessFile datFile,int rownum) throws IOException {
		datFile.seek(rownum *16);
		int byteExtracted = 0;
		for (int i=rownum;i<=(16+rownum);i++) {
			for (int j=0;j<=16;j++) {
				if (i == rownum) {
					System.out.printf((j == 0) ? "  H |" : " %02X |", (j-1));
				} else {
					if(j == 0) {
						System.out.printf(" %02X |",((i-1)*16));
					} else {
						byteExtracted = datFile.read();
						if(byteExtracted == -1) {
							System.out.printf("     ");
						}
						else {
							System.out.printf(" %02X  ",byteExtracted);
						}
					}
				}
			}
			if(i == rownum) {
				System.out.println("\n-------------------------------------------------------------------------------------");
			} else {
				System.out.println();
			}
		}
		System.out.printf("File size in decimal = %s", formatSize(datFile.length()));
		System.out.printf("\nFile size in Hex = %X", datFile.length());
	}
}