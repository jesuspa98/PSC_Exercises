package exercise0;

public class GardenSync {
    public static class Counter {
        private volatile int cont = 0;

        public synchronized void increase(int c){
            cont += c;
        }

        public int cont(){
            return cont;
        }
    }

    public static class Door extends Thread{
        private Counter counter;

        public Door(Counter counter){
            this.counter = counter;
        }

        public void run(){
            for (int i = 0; i < 100; i++){
                counter.increase(1);
                System.out.println("Value increased... " + counter.cont());
            }
        }
    }

    public static void main(String[] args) {
        Counter count = new Counter();
        Door[] doors = new Door[25];
        for (int i = 0; i < 25; i++){
            doors[i] = new Door(count);
        }
        for (int i = 0; i < 25; i++){
            doors[i].start();
        }
        try{
            for (int i = 0; i < 25; i++){
                doors[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
