public class excuter {

    //convert from binary to decimal
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int base = 1;
        int temp = binary.length() - 1;
        for (int i = binary.length()-1; i >=0 ; i--) {
            if (binary.charAt(i) == '1') {
                decimal += base;
            }
            base = base * 2;
        }
        return decimal;
    }
    //convert from decimal to binary
    public static String decimalToBinary(int decimal) {
        String binary = "";
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal = decimal / 2;
        }
        return binary;
    }
    //replace String 
    public String replace(String str, int index, char replace){     
        if(str==null){
            return str;
        }else if(index<0 || index>=str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        chars[index] = replace;
        return String.valueOf(chars);       
    }
    public  void ADD(Register R1, Register R2,Register status) {
        int R1_value = binaryToDecimal(R1.getData().substring(1,6));
        int R2_value = binaryToDecimal(R2.getData().substring(1,6));
        //check the fist bit if it is 1 it is signed else it is unsigned
        if (R1.getData().charAt(0) == '1') {
            R1_value *=-1;
        }
        if (R2.getData().charAt(0) == '1') {
             R2_value *=-1;
        }
        int result = R1_value + R2_value;
        if(result<0)
            replace(str, index, replace)
        String binary_res=decimalToBinary(result);
        String oldStatus = status.getData();
        String newStatus;
        if(binary_res.length()>6)
        {
           newStatus=replace(oldStatus, 3, '1');
        }
        else
        {
            newStatus=replace(oldStatus, 3, '0');
        }
        status.setData(newStatus);
        
    }
   
    public static void main(String[] args) {
        String s="1111111111111111111111111111111111111111111111111111111111110";
        int i=binaryToDecimal(s);
        System.out.println(i);

    }
}
