import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Register status = new Register("0000000");
		PC pc = new PC();
		instruction_memory instructionMemory = new instruction_memory();
		data_memory dataMemory = new data_memory();
		Parser parser = new Parser("Program_1.txt");
		parser.readFile(instructionMemory);
		//System.out.println(instructionMemory.memory[0]);
		Fetcher fetcher = new Fetcher(pc, instructionMemory);
		String instruction = fetcher.fetch();
		System.out.println(pc.getPc());
		Decoder decoder = new Decoder();
		Instruction i1 = decoder.decode(instruction);
		
		
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
