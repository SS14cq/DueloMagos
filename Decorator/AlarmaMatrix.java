public class AlarmaMatrix extends AccessoryDecorator{
    Car car;

    public AlarmaMatrix(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Alarma Matrix General 2 Controles";
    }

    @Override
    public double cost(){
        return 205000 + car.cost();
    }
    
}
