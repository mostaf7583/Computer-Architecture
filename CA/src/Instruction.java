
public class Instruction {
	String Opcode, operand1, operand2;
	char type;

	public Instruction(String Opcode, String operand1, String operand2,char type) {
		this.Opcode = Opcode;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.type = type;
}

	public String getOpcode() {
		return Opcode;
	}

	public void setOpcode(String opcode) {
		Opcode = opcode;
	}

	public String getOperand1() {
		return operand1;
	}

	public void setOperand1(String operand1) {
		this.operand1 = operand1;
	}

	public String getOperand2() {
		return operand2;
	}

	public void setOperand2(String operand2) {
		this.operand2 = operand2;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	public String toString() {
		return Opcode +" "+operand1+" "+operand2+" "+ type;
		
	}
}
