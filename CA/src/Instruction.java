
public class Instruction {
	String Opcode;
	Register r1, r2;
	String immediate;
	char type;

	public Instruction(String Opcode, Register operand1, Register operand2) {
		this.Opcode = Opcode;
		this.r1 = operand1;
		this.r2 = operand2;
		this.type = 'R';
}
	public Instruction(String Opcode, Register operand1, String immediate) {
		this.Opcode = Opcode;
		this.r1 = operand1;
		this.immediate = immediate;
		this.type = 'I';
}


	public String getOpcode() {
		return Opcode;
	}
	public void setOpcode(String opcode) {
		Opcode = opcode;
	}
	public Register getR1() {
		return r1;
	}
	public void setR1(Register r1) {
		this.r1 = r1;
	}
	public Register getR2() {
		return r2;
	}
	public void setR2(Register r2) {
		this.r2 = r2;
	}
	public String getImmediate() {
		return immediate;
	}
	public void setImmediate(String immediate) {
		this.immediate = immediate;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String toString() {
		return Opcode +" "+r1+" "+r2+" "+ type;
		
	}
}
