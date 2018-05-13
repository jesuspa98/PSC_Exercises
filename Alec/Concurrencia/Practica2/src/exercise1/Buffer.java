package exercise1;

public class Buffer {
    private int[] elems;
    private int producedIndex;
    private int consumedIndex;
    private volatile int producedButNotConsumedValues; // elements that are produced but not consumed
    private Peterson pete;

    public Buffer(int tam){
        elems = new int[tam];
        producedIndex = consumedIndex = 0;
        pete = new Peterson();
    }

    public void set(int value){
        while (producedButNotConsumedValues == elems.length){
            // we must avoid producing more elements than the consumed
            Thread.yield();
        }
        pete.preProtocole0();
        elems[producedIndex] = value;
        producedIndex = (producedIndex + 1) % elems.length;
        producedButNotConsumedValues++;
        pete.postProtocole0();
    }

    public int get(){
        while (producedButNotConsumedValues == 0){
            // we must avoid consume elements that are not produced
            Thread.yield();
        }
        pete.preProtocole1();
        int value = elems[consumedIndex];
        consumedIndex = (consumedIndex + 1) % elems.length;
        producedButNotConsumedValues--;
        pete.postProtocole1();
        return value;
    }
}
