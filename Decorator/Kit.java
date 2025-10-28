public class Kit extends AccessoryDecorator{
    Car car;

    public Kit(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Kit botón encendido + alarma + 2 controles tipo disparador";
    }

    @Override
    public double cost(){
        return 1500000 + car.cost();
    }
    
}
