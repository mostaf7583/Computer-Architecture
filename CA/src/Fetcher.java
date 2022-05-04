
public class Fetcher {
	PC pc;
	instruction_memory instructionMemory;
	
	public Fetcher(PC pc, instruction_memory instructionMemory) {
		this.pc = pc;
		this.instructionMemory = instructionMemory;
	}

	public String fetch() {
		int temp = pc.getPc();
		pc.setPc(temp+1);
		return instructionMemory.getMemory()[temp];
		
	}
}
