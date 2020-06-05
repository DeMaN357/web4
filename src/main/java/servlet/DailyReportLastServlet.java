package servlet;

import com.google.gson.Gson;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportLastServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String string = gson.toJson(DailyReportService.getInstance().getLastReport());
        resp.getWriter().println(string);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
