import java.util.Iterator;

public class Executer {
	public Executer() {

	}

	public void execute(Instruction instruction,Register status) {
			switch(instruction.getOpcode()) {
			case "0000":ADD(instruction.getR1(), instruction.getR2(), status);break;
			case "0001":SUB(instruction.getR1(), instruction.getR2(), status);break;
			case "0010":MULI(instruction.getR1(), instruction.getR2(), status);break;
			case "0011":LDI(instruction.getR1(), instruction.getR2(), status);break;
			case "0100":BEQI(instruction.getR1(), instruction.getR2(), status);break;
			case "0101":AND(instruction.getR1(), instruction.getR2(), status);break;
			case "0110":OR(instruction.getR1(), instruction.getR2(), status);break;
			case "0111":JR(instruction.getR1(), instruction.getR2(), status);break;
			case "1000":SLC(instruction.getR1(), instruction.getR2(), status);break;
			case "1001":SRC(instruction.getR1(), instruction.getR2(), status);break;
			case "1010":LB(instruction.getR1(), instruction.getR2(), status);break;
			case "1011":SB(instruction.getR1(), instruction.getR2(), status);break;
			}
	}

	public void ADD(Register register1, Register register2, Register status) {
		String operand1 = register1.getData();
		String operand2 = register2.getData();
		int c1 = 0, cOut = 0;
		String result = "";
		for (int i = operand1.length() - 1; i >= operand1.length() - 6; i--) {
			String r1 = String.valueOf(operand1.charAt(i));
			String r2 = String.valueOf(operand2.charAt(i));
			int intResult = Integer.parseInt(r1) + Integer.parseInt(r2) + cOut;
			if (i == 0) {
				c1 = cOut;
			}
			if (intResult == 2) {
				cOut = 1;
				result = '0' + result;
			} else if (intResult == 3) {
				cOut = 1;
				result = '1' + result;
			} else if (intResult == 1) {
				cOut = 0;
				result = '1' + result;
			} else {
				cOut = 0;
				result = '0' + result;
			}
		}
		char N = result.charAt(0);
		String V = String.valueOf(cOut ^ c1);
		char Z;
		if ((result.equals("000000")))
			Z = '1';
		else
			Z = '0';

		status.setData("000" + cOut + V + N + (N ^ V.charAt(0)) + Z);
		register1.setData(result);
	}

	public void SUB(Register register1, Register register2, Register status) {
		String operand1 = register1.getData();
		String operand2 = register2.getData();
		for (int i = 0; i < operand2.length(); i++) {
			if (operand2.charAt(i) == '0') {
				operand2 = operand2.substring(0, i) + '1' + operand2.substring(i + 1);
			} else
				operand2 = operand2.substring(0, i) + '0' + operand2.substring(i + 1);
		}
		Register one = new Register("00000001");
		String add1Data = ADD(register2, one, status);
		Register add1 = new Register(add1Data);
		String result = ADD(register1, add1, status);
		register1.setData(result);
	}

	public String MULI(Register r1, Register r2, Register status) {
		int d = Integer.parseInt(r2.data, 2);
		int f = Integer.parseInt(r1.data, 2);
		String p = r1.data;
		for (int i = 1; i < d; i++) {
			p = ADD(p, r1.data, status);
		}
		char Z;
		if ((p.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		r1.data = p;
		char N = p.charAt(0);
		return p;
	}

	public String AND(Register r1, Register r2, Register status) {
		String result = "";
		for (int i = 0; i < r1.data.length(); i++) {
			if (r1.data.charAt(i) == '1' && r2.data.charAt(i) == '1') {
				result = result + '1';
			} else {
				result = result + '0';
			}
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		r1.data = result;
		return result;

	}

	public String OR(Register r1, Register r2, Register status) {
		String result = "";
		for (int i = 0; i < r1.data.length(); i++) {
			if (r1.data.charAt(i) == '1' || r2.data.charAt(i) == '1') {
				result = result + '1';
			} else {
				result = result + '0';
			}
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		r1.data = result;
		return result;

	}

	public int JR(Register r1, Register r2, Register status) {
		String k = "";
		k = r1.data + r2.data;
		int result = Integer.parseInt(k, 2);
		return result; // to be inserted into PC
	}

	public void BEQZ(Register r1, String operand2, Register status, PC pc) {
		int k = 0;
		if (r1.data.equals("00000000")) {
			k = k + 1 + Integer.parseInt(operand2);
		}
		pc.setPc(k); // to be inserted into PC
	}

	public void LB(Register r1, String operand2, Register status, data_memory mem) {
		int k = Integer.parseInt(operand2, 2);
		mem = new data_memory();
		r1.data = mem.memory[k];
	}

	public void SB(Register r1, String operand2, Register status, data_memory mem) {
		int k = Integer.parseInt(operand2, 2);
		mem.memory[k] = r1.data;
	}

	public String SRC(String operand1, String operand2, Register status) {
		int r = Integer.parseInt(operand1, 2);
		int r2 = Integer.parseInt(operand2, 2);
		int x = (r >>> r2) | (r << (8 - r2));
		String k = Integer.toBinaryString(x);
		String result = "";

		if (k.length() > 8) {
			result = k.substring(k.length() - 8);
		} else {
			result = k;
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		return result;
	}

	public String SLC(String operand1, String operand2, Register status) {
		int r = Integer.parseInt(operand1, 2);
		int r2 = Integer.parseInt(operand2, 2);
		int x = (r << r2) | (r >>> (8 - r2));
		String k = Integer.toBinaryString(x);
		String result = "";

		if (k.length() > 8) {
			result = k.substring(k.length() - 8);
		} else {
			result = k;
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		return result;
	}

	public static void main(String[] args) {
		Executer e = new Executer();
		Register status = new Register("000000");
		System.out.println(e.ADD("111000111", "111111110", status));
		// System.out.println(status.data);
		// System.out.println(e.SUB("111110", "000111", status));
		// System.out.println(e.MULI("001001", "000011", status));
		// System.out.println(e.AND("100100", "111111", status));
		System.out.println(e.SLC("11110101", "00000001", status));
		Register r1 = new Register("00010000");
		Register r2 = new Register("11011010");
		System.out.println(e.AND(r1, r2, status));
	}
}
