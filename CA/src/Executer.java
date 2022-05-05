import java.util.Iterator;

public class Executer {
	public Executer() {
		
	}
	public String ADD(String operand1,String operand2,Register status) {
		int c1 = 0,cOut = 0;
		String result="";
		for(int i=operand1.length()-1;i>=0;i--) {
			String r1 = String.valueOf(operand1.charAt(i));
			String r2 = String.valueOf(operand2.charAt(i));
			int intResult = Integer.parseInt(r1)+Integer.parseInt(r2)+cOut;
			if(i == 0) {
				c1 = cOut;	
			}
			if(intResult == 2) {
				cOut = 1;
				result='0'+result;
			}else if(intResult == 3) {
				cOut = 1;
				result = '1'+result;
			} else if(intResult == 1) {
				cOut = 0;
				result = '1'+result;
			}else {
				cOut = 0;
				result = '0'+result;
			}
		}
		char N = result.charAt(0);
		String V = String.valueOf(cOut^c1);
		char Z;
		if((result.equals("000000")))
			Z = '1';
		else
			Z='0'; 
		
		status.setData("000"+cOut+V+N+(N^V.charAt(0))+Z);
		return result;
	}
	public String SUB(String operand1,String operand2,Register status) {
		for(int i = 0;i<operand2.length();i++) {
			if(operand2.charAt(i)=='0') {
				operand2 = operand2.substring(0, i) + '1' + operand2.substring(i + 1);
			}else
				operand2 = operand2.substring(0, i) + '0' + operand2.substring(i + 1);
		}
		String add1 = ADD(operand2, "000001", status);
		String result = ADD(operand1,add1,status);
		return result;
	}

	public static void main(String[] args) {
		Executer e = new Executer();
		Register status = new Register("000000");
		System.out.println(e.ADD("000111", "111110",status));
		System.out.println(status.data);

		
		System.out.println(e.SUB("111110", "000111", status));
		System.out.println(status.getData());
	}
}
