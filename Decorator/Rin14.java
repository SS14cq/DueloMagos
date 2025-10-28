public class Rin14 extends AccessoryDecorator{
    Car car;

    public Rin14(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Rin 14 pulgadas PICANTO-SOLUTO_SEPHIA";
    }

    @Override
    public double cost(){
        return 500000 + car.cost();
    }
    
}
