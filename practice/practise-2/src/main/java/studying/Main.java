package studying;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarService car_service = new CarService();
        CustomerStorage customer_storage = new CustomerStorage();
        HseCarService hse_car_service = new HseCarService(car_service, customer_storage);
        PedalCarFactory pedal_car_factory = new PedalCarFactory();
        HandCarFactory hand_car_factory = new HandCarFactory();
        FlyCarFactory fly_car_factory = new FlyCarFactory();

        customer_storage.addCustomer(new Customer("First", 6, 4, 100));
        customer_storage.addCustomer(new Customer("Second", 4, 6, 200));
        customer_storage.addCustomer(new Customer("Third", 6, 6, 250));
        customer_storage.addCustomer(new Customer("Fourth", 4, 4, 100));
        customer_storage.addCustomer(new Customer("Fifth", 1, 1, 400));

        PedalEngineParams engineParams1 = new PedalEngineParams(10);
        EmptyEngineParams engineParams2 = new EmptyEngineParams();

        car_service.addCar(pedal_car_factory, engineParams1);
        car_service.addCar(pedal_car_factory, engineParams1);

        car_service.addCar(hand_car_factory, engineParams2);
        car_service.addCar(hand_car_factory, engineParams2);

        car_service.addCar(fly_car_factory, engineParams2);


        for (var temp_castomer : customer_storage.getCustomers()) {

            System.out.println(temp_castomer);
        }
        System.out.println("----------------------------------------");
        hse_car_service.sellCars();
        for (var temp_castomer : customer_storage.getCustomers()) {

            System.out.println(temp_castomer);
        }

    }
}
