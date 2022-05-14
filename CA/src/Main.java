import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		int cycles = 1;
		Register status = new Register("0000000");
		PC pc = new PC();
		instruction_memory instructionMemory = new instruction_memory();
		data_memory dataMemory = new data_memory();
		ArrayList<Register> registerList = new ArrayList<Register>();
		for(int i = 0;i<64;i++) {
			registerList.add(new Register(""));
		}
		registerList.add(status);
		
		Parser parser = new Parser("1stProgram.txt");
		parser.readFile(instructionMemory);
		//System.out.println(instructionMemory.memory[0]);
		Fetcher fetcher = new Fetcher(pc, instructionMemory);
		Decoder decoder = new Decoder();	
		String instruction1="";
		Instruction instruction2;
		while(true) {
			String temp1 = fetcher.fetch();
			Instruction temp2 = decoder.decode(instruction1,registerList);
			System.out.println(temp1);
			System.out.println(temp2);
			instruction1 = temp1;
			instruction2 = temp2;
			if(cycles == 2)
				break;
			cycles++;
			
		}
		/* 
		 * while(program is running)
		 *	x=fetch
		 * 	y=decode(instruction1)
		 *
		 * execute(instruction2)
		 * 
		 * instruction1=x
		 * instruction2=y
		 * 
		 * end loop
		 */
	}
}
