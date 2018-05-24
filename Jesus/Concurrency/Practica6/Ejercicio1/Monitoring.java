public class Monitoring extends PeriodicT{
    private PressureSensor pressureSensor;
    private TemperatureSensor temperatureSensor;

    public Monitoring(PressureSensor pressureSensor, TemperatureSensor temperatureSensor) {
        super(5000);
        this.pressureSensor = pressureSensor;
        this.temperatureSensor = temperatureSensor;
    }

    protected void task(){
        pressureSensor.showState();
        temperatureSensor.showState();
    }
}
