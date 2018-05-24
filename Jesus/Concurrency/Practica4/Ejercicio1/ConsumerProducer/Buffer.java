package ConsumerProducer;
import Peterson.Peterson;

public class Buffer{
    private int[] elements;
    private int producerIndex, consumerIndex;
    private volatile int numberElements;
    private Peterson peterson;

    public Buffer(int size){
        elements = new int[size];
        producerIndex = consumerIndex = 0;
        peterson = new Peterson();
        numberElements = 0;
    }

    public void place(int number){
        while (numberElements == elements.length){
            //We must avoid producing more elements than the consumed
            Thread.yield();
        }

        peterson.postProtocol0();
        elements[producerIndex] = number;
        producerIndex = (producerIndex + 1)%elements.length;
        numberElements++;
        peterson.postProtocol0();
    }

    public int extract(){
        while (numberElements == 0){
            //We must avoid consume more elements that are not produced
            Thread.yield();
        }

        peterson.postProtocol1();
        int value = elements[consumerIndex];
        consumerIndex = (consumerIndex + 1) % elements.length;
        numberElements--;
        peterson.postProtocol1();
        return value;
    }
}
