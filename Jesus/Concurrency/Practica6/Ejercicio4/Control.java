public class Control {
    private int resourcesNumber;
    private int waiting;
    private int askTurn, takeTurn;

    public Control(int resourcesNumber) {
        this.resourcesNumber = resourcesNumber;
        waiting = 0;
    }

    public synchronized void takeResources(int id, int number) throws InterruptedException {
        if (waiting > 0 || resourcesNumber < number) {
            if (waiting == 0) {
                takeTurn = askTurn = 0;
            }
            waiting++;
            int myTurn = askTurn;
            askTurn++;

            while (myTurn != takeTurn || resourcesNumber < number) {
                System.out.println("Task " + id + " (turn: " + myTurn + ") wait for " + number);
                wait();
            }
            waiting--;
        }
        resourcesNumber -= number;
        System.out.println("Task " + id + " acquires " + number + " [" + resourcesNumber + "]");

        if (waiting > 0) {
            takeTurn++;
            notifyAll();
        }
    }

    public synchronized void resourcesFree(int id, int number) {
        if (waiting > 0) {
            notifyAll();
        }

        System.out.println("Task " + id + " frees " + number);
        resourcesNumber += number;
    }
}
