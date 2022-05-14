import java.util.ArrayList;

public class Decoder {
	public Instruction decode(String instruction,ArrayList<Register> registerList) {
		if (instruction.equals("")) {
			return null;
		} else {
			String[] splited = { instruction.substring(0, 4), instruction.substring(4, 10),
					instruction.substring(10, 16) };
			Register r1 = searchRegister(splited[1], registerList);
	
			if (splited[0].equals("0000") || splited[0].equals("0001") || splited[0].equals("0010")
					|| splited[0].equals("0101") || splited[0].equals("0110") || splited[0].equals("0111")) {
				Register r2 = searchRegister(splited[2], registerList);
				return new Instruction(splited[0], r1 ,r2);
			}
			else
				return new Instruction(splited[0], r1, splited[2]);
		}
	}

	public Register searchRegister(String registerAddress, ArrayList<Register> registerList) {
		int decimal = Integer.parseInt(registerAddress, 2);
		for (int i = 0; i < registerList.size(); i++) {
			if (i == decimal) {
				return registerList.get(i);
			}
		}
		return null;
	}

//	public decodeInstruction (Instruction i,ArrayList<Register> registerList) {
//		if(i.getType() == ('R')) {
//		Register register1 = searchRegister(i.getOperand1(), registerList);
//		Register register2 = searchRegister(i.getOperand2(), registerList);
//		}else {
//			Register register1 = searchRegister(i.getOperand1(), registerList);
//		}
//	}

}
