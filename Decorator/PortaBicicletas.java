public class PortaBicicletas extends AccessoryDecorator{
    Car car;

    public PortaBicicletas(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Porta Bicicletas X2 Puestos";
    }

    @Override
    public double cost(){
        return 910000 + car.cost();
    }
    
}
