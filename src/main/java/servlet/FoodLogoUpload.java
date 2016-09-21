package servlet;

import core.Constants;
import core.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.rmi.server.UID;
import java.util.Date;

public class FoodLogoUpload extends HttpServlet {
    private static InputStream fileSource;
    private static OutputStream outputStream;
    private static byte b[] = new byte[1024];

    public FoodLogoUpload(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("foodName"));
        System.out.println(req.getParameter("foodPrice"));
        System.out.println(req.getParameter("foodDetail"));
        System.out.println(req.getParameter("foodLogo"));

        String userPhone = (String) req.getSession().getAttribute("userPhone");
        String shopPhone = (String) req.getSession().getAttribute("shopPhone");
        String id;
        if (userPhone != null) id = userPhone;
        else id = shopPhone;

        //接收请求参数的输入流
        fileSource = req.getInputStream();
        //初始化临时文件路径名称
        String tempFileName = Constants.FILE_ROOT + req.getContextPath() +
                File.separator + "file" + File.separator +
                id + File.separator + new Date().getTime()+id;
        //初始化临时文件对象
        File tempFile = new File(tempFileName);
        //初始化临时文件路径
        File tempFilePath = new File(tempFile.getParent());
        if (!tempFilePath.exists()){
            tempFilePath.mkdirs();
        }

        try {
            outputStream = new FileOutputStream(tempFile);
            int n;
            while ((n = fileSource.read(b)) != -1){
                outputStream.write(b,0,n);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭输入输出流
            outputStream.close();
            fileSource.close();
        }
    }
}
