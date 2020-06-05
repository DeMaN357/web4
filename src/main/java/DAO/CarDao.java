package DAO;

import model.Car;
import org.hibernate.query.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        List<Car> carList = session.createQuery("FROM Car", Car.class).list();
        transaction.commit();
        session.close();
        return carList;
    }

    public boolean deleteCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
        return true;
    }

    public Car getCar(String brand, String model, String licensePlate) {
        Transaction transaction = session.beginTransaction();
        Query<Car> query = session.createQuery("FROM Car WHERE brand = :brand AND model = :model AND licensePlate = :licensePlate", Car.class);
        query.setParameter("brand", brand);
        query.setParameter("model", model);
        query.setParameter("licensePlate", licensePlate);
        List<Car> car = query.getResultList();
        transaction.commit();
        session.close();
        return car.get(0);
    }

    public boolean addCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
        return true;
    }

    public int getAllCarsFromBrand(String brand) {
        Transaction transaction = session.beginTransaction();
        Query<Car> result = session.createQuery("FROM Car WHERE brand = :brand", Car.class);
        result.setParameter("brand", brand);
        List<Car> cars = result.list();
        transaction.commit();
        session.close();
        return cars.size();
    }

    public void clearCar() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Car ").executeUpdate();
        transaction.commit();
        session.close();
    }
}
