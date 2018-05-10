package servlets;
import controller.StoreController;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by tudorstanila on 10/05/2018.
 */
public class AddServlet extends HttpServlet {
    private StoreController ctrl;

    @Override
    public void init() throws ServletException {
        ctrl = new StoreController();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ctrl.EmptyList();
        int code = Integer.parseInt(req.getParameter("code"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        RequestDispatcher rd = null;
        rd = req.getRequestDispatcher("/success.jsp");
        HttpSession session = req.getSession();
        try{
            ctrl.addProduct(new Product(code,name,category,quantity));
            session.setAttribute("result","Added");
        }catch(Exception e) {
            session.setAttribute("result", "Not added");
        }
        rd.forward(req,resp);
    }
}
