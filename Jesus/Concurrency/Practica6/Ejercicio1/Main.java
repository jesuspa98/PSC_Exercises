public class Main {
    public static void main(String[] args) {
        try {
            PressureSensor pressureSensor = new PressureSensor();
            TemperatureSensor temperatureSensor = new TemperatureSensor();
            Monitoring monitoring = new Monitoring(pressureSensor, temperatureSensor);
            pressureSensor.start();
            temperatureSensor.start();
            monitoring.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
