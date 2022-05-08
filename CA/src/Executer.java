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
	
	public String MULI(Register r1, Register r2, Register status) {
		int d = Integer.parseInt(r2.data, 2);
		int f = Integer.parseInt(r1.data,2);
		String p = r1.data;
		for(int i = 1; i < d; i++) {
		p = ADD(p, r1.data, status);
		}
		char Z;
		if((p.equals("00000000")))
			Z ='1';
		else
			Z='0';
		r1.data = p;
		char N = p.charAt(0);
		return p;
	}
	
	public String AND(Register r1, Register r2, Register status) {
		String result = "";
		for(int i = 0 ; i < r1.data.length(); i++) {
		if(r1.data.charAt(i) == '1' && r2.data.charAt(i) == '1') {
			result = result + '1';	
		}
		else {
			result = result +'0';
		}
		}
		char Z;
		if((result.equals("00000000")))
			Z ='1';
		else
			Z='0'; 
		char N = result.charAt(0);
		r1.data = result;
		return result;
		
	}
	
	public String OR(Register r1, Register r2, Register status) {
		String result = "";
		for(int i = 0 ; i < r1.data.length(); i++) {
		if(r1.data.charAt(i) == '1' || r2.data.charAt(i) == '1') {
			result = result + '1';	
		}
		else {
			result = result +'0';
		}
		}
		char Z;
		if((result.equals("00000000")))
			Z ='1';
		else
			Z='0'; 
		char N = result.charAt(0);
		r1.data = result;
		return result;
		
	}
	
	public int JR(Register r1, Register r2, Register status) {
		String k = "";
		k = r1.data + r2.data;
		int result = Integer.parseInt(k,2);
		return result; //to be inserted into PC
	}
	
	public int BEQZ(Register r1, String operand2, Register status) {
		int k = 0;
		if(r1.data.equals("00000000")) {
			k = k + 1 + Integer.parseInt(operand2);
		}
		return k; //to be inserted into PC
	}
	
	public void LB(Register r1, String operand2, Register status) {
		int k = Integer.parseInt(operand2,2);
		data_memory mem = new data_memory();
		r1.data = mem.memory[k];
	}
	
	public void SB(Register r1, String operand2, Register status) {
		int k = Integer.parseInt(operand2,2);
		data_memory mem = new data_memory();
		mem.memory[k] = r1.data;
	}
	
	public String SRC(String operand1, String operand2, Register status) {
		int r = Integer.parseInt(operand1,2);
		int r2 = Integer.parseInt(operand2,2);
		int x = (r >>> r2) | (r << (8 -r2));
		String k = Integer.toBinaryString(x);
		String result = ""; 
		 
		if (k.length() > 8) 
		{
		    result = k.substring(k.length() - 8);
		} 
		else
		{
		   result = k;
		}
		char Z;
		if((result.equals("00000000")))
			Z ='1';
		else
			Z='0'; 
		char N = result.charAt(0);
		return result;
	}
	
	public String SLC(String operand1, String operand2, Register status) {
		int r = Integer.parseInt(operand1,2);
		int r2 = Integer.parseInt(operand2,2);
		int x = (r << r2) | (r >>> (8 -r2));
		String k = Integer.toBinaryString(x);
		String result = ""; 
		 
		if (k.length() > 8) 
		{
		    result = k.substring(k.length() - 8);
		} 
		else
		{
		   result = k;
		}
		char Z;
		if((result.equals("00000000")))
			Z ='1';
		else
			Z='0'; 
		char N = result.charAt(0);
		return result;
	}

	public static void main(String[] args) {
		Executer e = new Executer();
		Register status = new Register("000000");
		//System.out.println(e.ADD("000111", "111110",status));
		//System.out.println(status.data);
		//System.out.println(e.SUB("111110", "000111", status));
		//System.out.println(e.MULI("001001", "000011", status));
		//System.out.println(e.AND("100100", "111111", status));
		System.out.println(e.SLC("11110101", "00000001", status));
		Register r1 = new Register("00010000");
		Register r2 = new Register("11011010");
		System.out.println(e.AND(r1, r2, status));
	}
}
