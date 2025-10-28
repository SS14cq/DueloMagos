public class Tapete extends AccessoryDecorator{
    Car car;

    public Tapete(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Tapete tres piezas alfombra PICANTO";
    }

    @Override
    public double cost(){
        return 92000 + car.cost();
    }
    
}
