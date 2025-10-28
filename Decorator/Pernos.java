public class Pernos extends AccessoryDecorator {
    Car car;

    public Pernos(Car car) {
        this.car = car;
    }

    @Override
    public String getDescription() {
        return car.getDescription() + ", Pernos de seguridad STARLOCK";
    }

    @Override
    public double cost() {
        return 156100 + car.cost();
    }
    
}
