package mate.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.dao.MyCoolResource;

@WebServlet(urlPatterns = "/index")
public class IndexController extends HttpServlet {
    private MyCoolResource myResource;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        myResource.write(LocalDateTime.now().format(DateTimeFormatter
                .ofPattern("dd MM yyyy | HH:mm:ss")));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").include(req,resp);
    }

    @Override
    public void destroy() {
        myResource.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.myResource = MyCoolResource.openResource();
    }
}
