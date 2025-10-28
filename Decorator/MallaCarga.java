public class MallaCarga extends AccessoryDecorator {
    Car car;

    public MallaCarga(Car car) {
        this.car = car;
    }

    @Override
    public String getDescription() {
        return car.getDescription() + ", Malla de Carga";
    }

    @Override
    public double cost() {
        return 110000 + car.cost();
    }
    
}
