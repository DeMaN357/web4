package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport",DailyReport.class).list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public DailyReport getDailyReport(Long idDailyReport) {
        Transaction transaction = session.beginTransaction();
        Query<DailyReport> dailyReportQuery = session.createQuery("FROM DailyReport WHERE id = :idDailyReport", DailyReport.class);
        dailyReportQuery.setParameter("idDailyReport", idDailyReport);
        List<DailyReport> dailyReport = dailyReportQuery.getResultList();
        transaction.commit();
        session.close();
        return (dailyReport.isEmpty()) ? null : dailyReport.get(0);
    }

    public void SaveDailyReport(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }


    public boolean UpdateDailyReport(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE DailyReport SET earnings = " + dailyReport.getEarnings() + ", soldCars = " + dailyReport.getSoldCars() + " where id = " + dailyReport.getId()).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    public void clearDailyReport() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport ").executeUpdate();
        transaction.commit();
        session.close();
    }
}
