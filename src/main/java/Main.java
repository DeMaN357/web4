import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

public class Main {

    public static void main(String[] args) throws Exception {
        CustomerServlet customerServlet = new CustomerServlet();
        ProducerServlet producerServlet = new ProducerServlet();
        NewDayServlet newDayServlet = new NewDayServlet();
        DailyReportDeleteServlet dailyReportDeleteServlet = new DailyReportDeleteServlet();
        DailyReportAllServlet dailyReportAllServlet = new DailyReportAllServlet();
        DailyReportLastServlet dailyReportLastServlet = new DailyReportLastServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(customerServlet),"/customer");
        context.addServlet(new ServletHolder(producerServlet),"/producer");
        context.addServlet(new ServletHolder(newDayServlet),"/newday");
        context.addServlet(new ServletHolder(dailyReportAllServlet),"/report/all");
        context.addServlet(new ServletHolder(dailyReportLastServlet),"/report/last");
        context.addServlet(new ServletHolder(dailyReportDeleteServlet),"/report/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
