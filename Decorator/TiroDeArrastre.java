public class TiroDeArrastre extends AccessoryDecorator{
    Car car;

    public TiroDeArrastre(Car car){
        this.car = car;
    }

    @Override
    public String getDescription(){
        return car.getDescription() + ", Tiro de arrastre";
    }

    @Override
    public double cost(){
        return 810000 + car.cost();
    }
    
}
