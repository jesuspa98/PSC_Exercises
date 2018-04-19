package Print;

public class Print extends Thread{
    private int value;
    private char character;

    public Print(int val, char charac){
        value = val;
        character = charac;
    }

    public void run(){
        for(int i = 0; i < value; i++){
            System.out.println("The character is: " + character);
        }
    }
}
