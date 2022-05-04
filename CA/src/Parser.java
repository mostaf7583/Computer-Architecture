import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	String dataPath;
	public Parser(String dataPath) {
		this.dataPath = dataPath;
	}
	public void readFile(instruction_memory I) throws FileNotFoundException {
		File file = new File(this.dataPath);
		Scanner scan = new Scanner(file);
		int i = 0;
		while(scan.hasNextLine()) {
			I.memory[i] = scan.nextLine();
			i++;
		}
			
	}
	public static void main(String[] args) throws FileNotFoundException {
		
		
	}
}
