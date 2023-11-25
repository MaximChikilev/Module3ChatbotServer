package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class AddNewUserServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = Utils.requestBodyToArray(req); // json
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        User user = gson.fromJson(bufStr, User.class);
        user.setLastAppearanceDate(new Date());
        if (user != null) {
            msgList.addNewUser(user);
        } else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
    }
}
