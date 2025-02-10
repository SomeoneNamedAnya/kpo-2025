package hse.kpo;
import hse.kpo.domains.*;
import hse.kpo.factories.*;
import hse.kpo.interfaces.*;
import hse.kpo.params.*;
import hse.kpo.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KpoApplication {


	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(KpoApplication.class, args);
		CarService carService = context.getBean(CarService.class);
		CustomerStorage customerStorage = context.getBean(CustomerStorage.class);
		HseCarService hseCarService = context.getBean(HseCarService.class);
		PedalCarFactory pedalCarFactory = context.getBean(PedalCarFactory.class);
		HandCarFactory handCarFactory = context.getBean(HandCarFactory.class);
		FlyCarFactory flyCarFactory = context.getBean(FlyCarFactory.class);

		customerStorage.addCustomer(new Customer("Ivan1",6,4, 100));
		customerStorage.addCustomer(new Customer("Maksim",4,6, 150));
		customerStorage.addCustomer(new Customer("Petya",6,6, 250));
		customerStorage.addCustomer(new Customer("Nikita",4,4, 300));
		customerStorage.addCustomer(new Customer("Fifth", 1, 1, 400));

		carService.addCar(pedalCarFactory, new PedalEngineParams(6));
		carService.addCar(pedalCarFactory, new PedalEngineParams(6));

		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

		carService.addCar(flyCarFactory, EmptyEngineParams.DEFAULT);

		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

		hseCarService.sellCars();

		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
	}
}
