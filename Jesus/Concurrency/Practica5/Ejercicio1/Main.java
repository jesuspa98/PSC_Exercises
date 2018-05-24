public class Main {
    public static void main(String[] args) {
        Measurements measurements = new Measurements();
        Worker worker = new Worker(measurements);

        worker.start();
        Sensor []sensors = new Sensor[Measurements.NUM_SENSORES];
        for (int i = 0; i < sensors.length; i++){
            sensors[i] = new Sensor(i, measurements);
            sensors[i].start();
        }
    }
}
