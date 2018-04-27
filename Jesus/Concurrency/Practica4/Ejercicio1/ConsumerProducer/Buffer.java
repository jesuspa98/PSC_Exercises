package ConsumerProducer;
import Peterson.Peterson;

public class Buffer{
    private int[] elements;
    private int producer, consumer;
    private volatile int numberElements;
    private Peterson peterson;

    public Buffer(int size){
        elements = new int[size];
        producer = consumer = 0;
        peterson = new Peterson();
        numberElements = 0;
    }

    public void place(int number){
        while (numberElements == elements.length){
            Thread.yield();
        }

        peterson.postProtocol0();
        elements[producer] = number;
        producer = (producer + 1)%elements.length;
        numberElements++;
        peterson.postProtocol0();
    }

    public int extract(){
        while (numberElements == 0){
            Thread.yield();
        }

        peterson.postProtocol1();
        int value = elements[consumer];
        consumer = (consumer + 1) % elements.length;
        numberElements--;
        peterson.postProtocol1();
        return value;
    }
}
