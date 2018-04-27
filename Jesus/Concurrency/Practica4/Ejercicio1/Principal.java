import ConsumerProducer.Buffer;
import ConsumerProducer.Consumer;
import ConsumerProducer.Producer;

public class Principal {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(4);
        final int times = 100;
        Producer producer = new Producer(buffer, times);
        Consumer consumer = new Consumer(buffer, times);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
