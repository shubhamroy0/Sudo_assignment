package HexEditor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.*;
import java.text.DecimalFormat;
import java.util.Scanner; 

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
		//String filename = "DAT_files/filename.txt";
		String filename = "hackme.elf";
		RandomAccessFile raf;
		boolean canEdit = new File(filename).canWrite();
		if (canEdit) {
			raf= new RandomAccessFile(filename,"rw");
		} else {
			raf= new RandomAccessFile(filename,"r");
		}
		//printHexTableWithFileInteraction(raf ,0,80L);
		//interactiveDesign(raf,canEdit);
		
		if(findString(raf,"hackerman1337",0)) {
			System.out.printf("Found");
		} else {
			System.out.printf("not Found");
		}
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
	
	public static void printHexTableWithFileASCII(RandomAccessFile datFile,int rownum) throws IOException {
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
							if(byteExtracted == 00)
								System.out.printf("  -  ",(char)byteExtracted);
							else
								System.out.printf("  %c  ",(char)byteExtracted);
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
		System.out.printf("\nFile size in Hex = 0x%X\n", datFile.length());
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
		System.out.printf("\nFile size in Hex = 0x%X\n", datFile.length());
	}
	
	public static void printHexTableWithFileInteraction(RandomAccessFile datFile,int rownum, long selectedByte) throws IOException {
		datFile.seek(rownum *16);
		byte byteExtracted = 0, selectedValue=0;		
		for (int i=rownum;i<=(16+rownum);i++) {
			for (int j=0;j<=16;j++) {
				if (i == rownum) {
					System.out.printf((j == 0) ? "  H |" : " %02X |", (j-1));
				} else {
					if(j == 0) {
						System.out.printf(" %02X |",((i-1)*16));
					} else {
						byteExtracted = new Integer(datFile.read()).byteValue();
						long position = (((i-1)*16)+j-1);
						if(byteExtracted == -1) {
							System.out.printf("     ");
						}
						else if (position == selectedByte) {
							//System.out.printf("[%02d] ",position);
							System.out.printf("[%02X] ",byteExtracted);
							selectedValue = byteExtracted;
						}
						else {
							//System.out.printf(" %02X  ",position);
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
		System.out.printf("Byte address: 0x%X (%d)\n", selectedByte,selectedByte);
		System.out.printf("Byte value:   0x%X (%d signed) (%d unsigned)\n", selectedValue,selectedValue,Byte.toUnsignedInt(selectedValue));
		System.out.printf("File size:    0x%X (%d) %s\n", datFile.length(), datFile.length(), formatSize(datFile.length()));
	}
	
	public static void interactiveDesignWrite(RandomAccessFile datFile, long index, byte replaceText) throws IOException {
		datFile.seek(index);
		datFile.write(replaceText);
	}
	public static boolean findStringhex(RandomAccessFile datFile, int newfil, long start , byte[] findString , int length) throws IOException {
		if(length == findString.length) {
			return true;
		}
		byte b= new Integer(newfil).byteValue();
		if(newfil == -1){
			datFile.seek(start-1);
			return false;
		}else if (b == findString[length]) {
			length++;
			newfil=datFile.read();
			return findStringhex(datFile , newfil ,start, findString ,length);
		}
		return false;
	}
	public static boolean findString(RandomAccessFile datFile,String find,long startIndex) throws IOException {
		datFile.seek(startIndex);
		long ref = startIndex;
		boolean check= false;
		int fil = datFile.read();
		byte findarray[] = find.getBytes();
		while(fil != -1) {
			check= findStringhex(datFile,fil,ref,findarray,0);
			if(check)
				break;
			fil=datFile.read();
			ref++;
		}
		return check;
	}
	public static void interactiveDesign(RandomAccessFile datFile,boolean canEdit) throws IOException {
		printHexTableWithFile(datFile, 0);
		boolean continueFlag = true;
		String input = "",tokenString[],actionTaken="";
		long addr = 0L,refAddr=0L;
		byte replacechar = 0;
		while(continueFlag) {
			System.out.printf("Enter command :-");
			input = new Scanner(System.in).nextLine();
			tokenString = input.split(" ");
			if (tokenString.length > 3) {
				System.out.printf("False Input\n");
				continue;
			}
			if (tokenString[0].equalsIgnoreCase("exit")) {
				continueFlag = false;
				datFile.close();
				continue;
			}
			else if (tokenString[0].equalsIgnoreCase("hex")) {
				printHexTableWithFileASCII(datFile, 0);
				continue;
			}
			if (tokenString.length == 3) {
				if(tokenString[2].equalsIgnoreCase("d")) {
					refAddr = Long.parseLong(tokenString[1]);
				}
				else if (tokenString[2].equalsIgnoreCase("h")) {
					refAddr = Long.parseLong(tokenString[1], 16);
				} else {
					System.out.printf("False Input\n");
					continue;
				}
			} else if (tokenString.length == 2) {
				if(tokenString[0].equalsIgnoreCase("set") && tokenString[1].charAt(0)=='\'') {
					if(!canEdit) {
						System.out.printf("Cannot edit a readonly file\n");
						continue;
					}
					replacechar = (byte)tokenString[1].charAt(1);
					interactiveDesignWrite(datFile, refAddr, replacechar);
				} else {
					refAddr = Long.parseLong(tokenString[1], 16);
				}
			}
			if(tokenString[0].equalsIgnoreCase("goto")) {
				if(refAddr < 0) {
					System.out.printf("False Input\n");
					continue;
				}
				addr = refAddr;
				actionTaken = "Pointor on address number 0x"+Long.toHexString(refAddr);
			}
			else if (tokenString[0].equalsIgnoreCase("set")) {
				if(refAddr < 0) {
					System.out.printf("False Input\n");
					continue;
				}
				if(!canEdit) {
					System.out.printf("Cannot edit a readonly file\n");
					continue;
				}
				replacechar = (byte)refAddr;
				interactiveDesignWrite(datFile, addr, replacechar);
				actionTaken = "replaceed 0x"+Long.toHexString(refAddr);
			}
			else if (tokenString[0].equalsIgnoreCase("truncate")) {
				if(refAddr < 0) {
					System.out.printf("False Input\n");
					continue;
				}
				if(!canEdit) {
					System.out.printf("Cannot edit a readonly file\n");
					continue;
				}
				datFile.setLength(refAddr + 1);
				actionTaken = "replaceed 0x"+Long.toHexString(refAddr);
			}
			else if (tokenString[0].equalsIgnoreCase("jump")) {
				addr = addr + refAddr;
				if(refAddr < 0)
					actionTaken = "Jumped 0x"+Long.toHexString(Math.abs(refAddr))+" bytes backward ";
				else
					actionTaken = "Jumped 0x"+Long.toHexString(refAddr)+" bytes forward ";
			}
			else {
				System.out.printf("False Input\n");
				continue;
			}
			printHexTableWithFileInteraction(datFile, 0, addr);
			System.out.println(actionTaken);
		}
	}
}