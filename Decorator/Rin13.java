public class Rin13 extends AccessoryDecorator{
    Car car;

    public Rin13(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Rin 13 pulgadas PICANTO";
    }

    @Override
    public double cost(){
        return 350000 + car.cost();
    }
    
}
