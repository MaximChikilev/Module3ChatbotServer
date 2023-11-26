package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangeAppearanceStatusServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("login");
        String status = req.getParameter("status");
        if (!msgList.getUsersList().containsKey(userLogin)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        msgList.changeUserStatus(userLogin, Status.valueOf(status));
    }
}
