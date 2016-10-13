package core;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class FileUpload {

    private  InputStream fileSource;
    private  OutputStream outputStream;
    private  byte b[] = new byte[1024];
    //访问临时文件
    private  RandomAccessFile accessTemp;

    /**将数据流传入临时文件，并返回该文件的对象
     * method: getTempFile
     * @param req
     * @param id
     * @return
     * @throws IOException
     */
    public File getTempFile(HttpServletRequest req,String id) throws IOException {
        //接收请求参数的输入流
        fileSource = req.getInputStream();
        //初始化临时文件路径名称
        String tempFileName = Constants.FILE_ROOT + req.getContextPath() +
                        File.separator + "file" + File.separator +
                        id + File.separator + "tempFile";
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
        return tempFile;
    }

    /**获取用户上传的文件名称
     * method: getFileName()
     * @param tempFile
     * @return
     * @throws IOException
     */
    public String getFileName(File tempFile) throws IOException {
        //初始化
        accessTemp = new RandomAccessFile(tempFile,"r");
        //第一行无用，直接跳过
        accessTemp.readLine();
        //读取第二行字符串,为防止出现乱码，更改编码格式
        String str = new String(accessTemp.readLine().getBytes("iso8859-1"),"utf-8");

        int filenameIndex = str.indexOf("filename");
        //从filename的位置之后开始，第一个"的位置
        int beginIndex = str.indexOf("\"",filenameIndex)+1;
        int endIndex = str.lastIndexOf("\"");
        return str.substring(beginIndex,endIndex);
    }

    /**获取文件内容的开始位置
     * method: getStartPosition()
     * @return
     * @throws IOException
     */
    public long getStartPosition() throws IOException {
        int n;
        accessTemp.seek(0);
        long startPosition;
        int i = 1;
        while ((n = accessTemp.readByte()) != -1 && i<=4){
            if (n=='\n'){
                i++;
            }
        }
        startPosition = accessTemp.getFilePointer()-1;
        return startPosition;
    }

    /**获取文件内容的结束位置
     * method: getEndPosition()
     * @return
     * @throws IOException
     */
    public long getEndPosition() throws IOException {
        //定位并获取临时文件的末尾指针
        accessTemp.seek(accessTemp.length());
        long endPosition = accessTemp.getFilePointer();
        //控制行
        int i = 1;
        while (endPosition>=0 && i<=2){
            endPosition--;
            accessTemp.seek(endPosition);
            if (accessTemp.readByte() == '\n'){
                i++;
            }
        }
        endPosition -= 1;
        return endPosition;
    }

    /**得到指定的文件名
     * method: assignName()
     * @param filename
     * @param assignFilename
     * @return
     */
    public String getAssignName(String filename,String assignFilename){
        return assignFilename +filename.substring(filename.lastIndexOf("."),filename.length());
    }

    /**上载文件，并返回存于服务器端的该文件路径（用于存到数据库）
     * method: uploadFile()
     * @param req
     * @param id
     * @return
     * @throws IOException
     */
    public String uploadFile(HttpServletRequest req,String id,String url,String assignFilename) throws IOException {

        //初始化临时文件
        File tempFile = getTempFile(req,id);
        //获取上传的文件名
        String filename = getFileName(tempFile);
        //获取指定的文件名
        if (!"".equals(assignFilename) && assignFilename!=null)
            filename = getAssignName(filename,assignFilename);
        //创建存储图片的真实路径
        String realPath = req.getServletContext().getRealPath(File.separator) + File.separator + url;
        File fileUploadUrl = new File(realPath);
        if (!fileUploadUrl.exists()){
            fileUploadUrl.mkdirs();
        }
        //初始化保存文件
        File saveFile = new File(realPath,filename);
        //用于写到服务器端的文件中
        RandomAccessFile accessForSave = new RandomAccessFile(saveFile,"rw");
        //获取文件内容起始位置
        long start = getStartPosition();
        //获取文件内容结束位置
        long end = getEndPosition();
        //从临时文件中读取内容（依据起始位置）
        accessTemp.seek(start);
        while (start < end){
            accessForSave.write(accessTemp.readByte());
            start = accessTemp.getFilePointer();
        }

        //关闭文件访问对象，删除临时文件
        accessForSave.close();
        accessTemp.close();
        tempFile.delete();
        //路径处理
        url = url.replace(File.separator,"/");
        String path = req.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
        return basePath+url+"/"+filename;
    }
}