package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public Car getCar(String brand, String model, String licensePlate) {
        return new CarDao(sessionFactory.openSession()).getCar(brand, model, licensePlate);
    }

    public boolean buyCar(String brand, String model, String licensePlate) {
        Car car = getCar(brand,model,licensePlate);
        DailyReportService.getInstance().addCountCarAndPrice(car);
        return new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public boolean addCar(Car car) {
        if (getAllCarsFromBrand(car.getBrand()) < 10){
            return new CarDao(sessionFactory.openSession()).addCar(car);
        }
            return false;
    }

    private int getAllCarsFromBrand(String brand) {
        return new CarDao(sessionFactory.openSession()).getAllCarsFromBrand(brand);
    }

    public void clearCar() {
        new CarDao(sessionFactory.openSession()).clearCar();
    }
}
