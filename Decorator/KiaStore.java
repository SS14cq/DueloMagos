public class KiaStore {
    public static void main(String[] args) {
        Car car1 = new ZenithMT();
        System.out.println(car1.getDescription() + " $" + car1.cost());

        Car car2 = new GTLineAT();
        car2 = new SensorParqueo(car2);
        car2 = new MallaCarga(car2);
        car2 = new Rin14(car2);
        System.out.println(car2.getDescription() + " $" + car2.cost());

        Car car3 = new VibrantMT();
        car3 = new Rin13(car3);
        car3 = new PortaBicicletas(car3);
        car3 = new Tapete(car3);
        System.out.println(car3.getDescription() + " $" + car3.cost());
    }
}
