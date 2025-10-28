public class SensorParqueo extends AccessoryDecorator {
    Car car;

    public SensorParqueo(Car car) {
        this.car = car;
    }

    @Override
    public String getDescription() {
        return car.getDescription() + ", Sensor de Parqueo";
    }

    @Override
    public double cost() {
        return 1500000 + car.cost();
    }
    
}
