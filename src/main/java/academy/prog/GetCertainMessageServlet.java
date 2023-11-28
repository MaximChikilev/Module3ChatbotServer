package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetCertainMessageServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String messageIdStr = req.getParameter("messageId");
        int messageId = 0;
        try {
            messageId = Integer.parseInt(messageIdStr);
            if (messageId < 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            resp.setContentType("application/json");
            String json = msgList.getJsonMassageById(messageId);
            if (json != null) {
                OutputStream os = resp.getOutputStream();
                byte[] buf = json.getBytes(StandardCharsets.UTF_8);
                os.write(buf);
            }
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }
}
