package core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于服务端获取session中的数据
 */
public class SessionUtil {

    public static String getId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPhone = (String) request.getSession().getAttribute("userPhone");
        String shopPhone = (String) request.getSession().getAttribute("shopPhone");
        //获取用户的id
        if (userPhone!=null) {
            return userPhone;
        }else if (shopPhone!=null) {
            return shopPhone;
        }else{
            request.getRequestDispatcher("TimeOut.jsp").forward(request,response);
            return "";
        }
    }
}