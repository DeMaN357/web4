package servlet;

import service.CarService;
import service.DailyReportService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DailyReportDeleteServlet extends HttpServlet {

    private DailyReportService dailyReportService = DailyReportService.getInstance();
    private CarService carService = CarService.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        dailyReportService.clearDailyReport();
        carService.clearCar();

    }
}
