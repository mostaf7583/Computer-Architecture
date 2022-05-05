
public class Decoder {
	public Instruction decode(String instruction) {
		if (instruction.equals("")) {
			return null;
		} else {
			String[] splited = {instruction.substring(0,4),instruction.substring(4,10),instruction.substring(10,16)};
			if (splited[0].equals("0000") || splited[0].equals("0001")
					|| splited[0].equals("0010") || splited[0].equals("0101")
					|| splited[0].equals("0110") || splited[0].equals("0111"))
				return new Instruction(splited[0], splited[1], splited[2], 'R');
			else
				return new Instruction(splited[0], splited[1], splited[2], 'I');
		}

	}
}
