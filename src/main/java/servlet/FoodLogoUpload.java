package servlet;

import core.FileUpload;
import core.SessionUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FoodLogoUpload extends HttpServlet {

    public FoodLogoUpload(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取session中的用户电话号
        String id = SessionUtil.getId(req,resp);
        FileUpload fileUpload = new FileUpload();
        //设置给定路径，eg:file/id -> file下的id文件夹
        String url = "file" + File.separator + id;
        //设置文件名
        String filename = "foodLogo";
        String filePath = fileUpload.uploadFile(req,id,url,filename);
        //将文件路径封装成json格式
        JSONObject serverJson = new JSONObject();
        serverJson.element("filePath",filePath);
        //返回给前端
        PrintWriter out = resp.getWriter();
        out.write(serverJson.toString());
        out.close();
    }
}