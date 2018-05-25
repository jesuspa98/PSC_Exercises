package exercise1;

public class SystemMonitoring extends Sensor{
    private Pressure pressureSensor;
    private Temperature temperatureSensor;

    public SystemMonitoring(Pressure pressureSensor, Temperature temperatureSensor) {
        super(5000);
        this.pressureSensor = pressureSensor;
        this.temperatureSensor = temperatureSensor;
    }

    protected void task(){
        pressureSensor.printState();
        temperatureSensor.printState();
    }

    public static void main(String[] args) {
        Pressure pressureSensor = new Pressure(100);
        Temperature temperatureSensor = new Temperature(100);
        SystemMonitoring monitoring = new SystemMonitoring(pressureSensor, temperatureSensor);
        pressureSensor.start();
        temperatureSensor.start();
        monitoring.start();
        try {
            pressureSensor.join(); temperatureSensor.join(); monitoring.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("Well, fuck");
        }
    }
}
