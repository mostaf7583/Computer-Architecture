
public class Decoder {
	public Instruction decode(String instruction) {
		if (instruction.equals("")) {
			return null;
		} else {
			String[] splited = instruction.split(" ");
			if (splited[0].equalsIgnoreCase("ADD") || splited[0].equalsIgnoreCase("SUB")
					|| splited[0].equalsIgnoreCase("MUL") || splited[0].equalsIgnoreCase("AND")
					|| splited[0].equalsIgnoreCase("OR") || splited[0].equalsIgnoreCase("JR"))
				return new Instruction(splited[0], splited[1], splited[2], 'R');
			else
				return new Instruction(splited[0], splited[1], splited[2], 'I');
		}

	}
}
