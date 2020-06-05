package service;

import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return new DailyReportDao(sessionFactory.openSession()).getDailyReport(getMaxValue() - 1);
    }

    public boolean addCountCarAndPrice(Car car) {
        DailyReport dailyReport = getDailyReport();
        dailyReport.setEarnings(car.getPrice() + dailyReport.getEarnings());
        dailyReport.setSoldCars(dailyReport.getSoldCars() + 1L);
        return new DailyReportDao(sessionFactory.openSession()).UpdateDailyReport(dailyReport);
    }

    private DailyReport getDailyReport() {
        if (new DailyReportDao(sessionFactory.openSession()).getDailyReport(getMaxValue()) == null) {
            new DailyReportDao((sessionFactory.openSession())).SaveDailyReport(new DailyReport(0L, 0L));
        }
        return new DailyReportDao(sessionFactory.openSession()).getDailyReport(getMaxValue());
    }


    public long getMaxValue() {
        List<DailyReport> list = new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
        if (list.size() == 0) {
            return 0;
        } else {
            return list.get(list.size() - 1).getId();
        }
    }

    public void createNewDay() {
        new DailyReportDao(sessionFactory.openSession()).SaveDailyReport(new DailyReport(0L, 0L));
    }

    public void clearDailyReport() {
        new DailyReportDao(sessionFactory.openSession()).clearDailyReport();
    }
}
