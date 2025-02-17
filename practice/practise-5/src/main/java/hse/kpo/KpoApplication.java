package hse.kpo;
import hse.kpo.Builders.ReportBuilder;
import hse.kpo.domains.*;
import hse.kpo.factories.cars.HandCarFactory;
import hse.kpo.factories.cars.LevitationCarFactory;
import hse.kpo.factories.cars.PedalCarFactory;
import hse.kpo.factories.ships.HandShipFactory;
import hse.kpo.factories.ships.LevitationShipFactory;
import hse.kpo.factories.ships.PedalShipFactory;
import hse.kpo.params.*;
import hse.kpo.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@Slf4j
@SpringBootApplication
public class KpoApplication {


	public static void main(String[] args) {


		ApplicationContext context = SpringApplication.run(KpoApplication.class, args);
		CarStorage carService = context.getBean(CarStorage.class);
		CustomerStorage customerStorage = context.getBean(CustomerStorage.class);
		ShipStorage shipStorage = context.getBean(ShipStorage.class);
		HseCarService hseCarService = context.getBean(HseCarService.class);
		PedalCarFactory pedalCarFactory = context.getBean(PedalCarFactory.class);
		HandCarFactory handCarFactory = context.getBean(HandCarFactory.class);
		LevitationCarFactory levitationCarFactory = context.getBean(LevitationCarFactory.class);
		HandShipFactory handShipFactory = context.getBean(HandShipFactory.class);
		PedalShipFactory pedalShipFactory = context.getBean(PedalShipFactory.class);
		LevitationShipFactory levitationShipFactory = context.getBean(LevitationShipFactory.class);
		HseShipService hseShipService = context.getBean(HseShipService.class);

		log.info("Подгрузились все зависимости");
		log.info("Загрузка покупателей началась");

		customerStorage.addCustomer(new Customer("Ivan1",6,4, 100));
		customerStorage.addCustomer(new Customer("Maksim",4,6, 150));
		customerStorage.addCustomer(new Customer("Petya",6,6, 250));
		customerStorage.addCustomer(new Customer("Nikita",4,4, 300));
		customerStorage.addCustomer(new Customer("Fifth", 1, 1, 400));

		log.info("Загрузка покупателей закончилась");
		log.info("Загрузка машин началась");

		carService.addCar(pedalCarFactory, new PedalEngineParams(6));
		carService.addCar(pedalCarFactory, new PedalEngineParams(6));
		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
		carService.addCar(levitationCarFactory, EmptyEngineParams.DEFAULT);

		log.info("Загрузка машин закончилась");
		log.info("Загрузка катамаранов началась");

		shipStorage.addShip(pedalShipFactory, new PedalEngineParams(6));
		shipStorage.addShip(pedalShipFactory, new PedalEngineParams(6));
		shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
		shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
		shipStorage.addShip(levitationShipFactory, EmptyEngineParams.DEFAULT);

		log.info("Загрузка катамаранов закончилась");



		var reportBuilderForCar = new ReportBuilder()
				.addOperation("Инициализация системы")
				.addCustomers(customerStorage.getCustomers());

		log.info("Продажа машин начата");
		hseCarService.sellCars();
		log.info("Продажа машин закончена");

		log.info("Составление отчета о продаже машин начато");
		var carReport = reportBuilderForCar
				.addOperation("Продажа автомобилей")
				.addCustomers(customerStorage.getCustomers())
				.build();

		log.info("Составление отчета о продаже машин закончено");

		var reportBuilderForShip = new ReportBuilder()
				.addOperation("Инициализация системы")
				.addCustomers(customerStorage.getCustomers());

		log.info("Продажа катамаранов начата");
		hseShipService.sellShip();
		log.info("Продажа катамаранов закончена");

		log.info("Составление отчета о продаже катамаранов начато");
		var shipReport = reportBuilderForShip
				.addOperation("Продажа катамаранов")
				.addCustomers(customerStorage.getCustomers())
				.build();

		log.info("Составление отчета о продаже катамаранов закончено");
		System.out.println(carReport.toString());
		System.out.println(shipReport.toString());

		log.info("Работа приложения закончилась");

		//customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
	}
}
