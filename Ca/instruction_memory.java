public class instruction_memory {
    int words = 1024;
    int[] memory = new int[words];
    


    public instruction_memory() {

    }
    public  void load_Data(int index,Registers Reg) {
        Reg.Data=this.memory[index];
        
    }
    public  void Store_Data(int index,Registers Reg) {
        this.memory[index]=Reg.Data;
    }
     
public static void main(String[] args) {
    instruction_memory im = new instruction_memory();
   
}
    
    
}
