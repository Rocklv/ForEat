package core;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class FileUpload {

    private static InputStream fileSource;
    private static OutputStream outputStream;
    private static byte b[] = new byte[1024];
    //访问临时文件
    private static RandomAccessFile accessTemp;

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
        //读取第二行字符串
        String str = accessTemp.readLine();
        int beginIndex = str.lastIndexOf("\\")+1;
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
        long startPosition = 0;
        int i = 1;
        while ((n = accessTemp.readByte()) != -1&&i<=4){
            if (n=='\n'){
                startPosition = accessTemp.getFilePointer();
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

    /**上载文件，并返回存于服务器端的该文件路径（用于存到数据库）
     * method: uploadFile()
     * @param req
     * @param id
     * @return
     * @throws IOException
     */
    public String uploadFile(HttpServletRequest req,String id) throws IOException {

        //初始化临时文件
        File tempFile = getTempFile(req,id);
        //获取用户上传的文件名
        String fileName = getFileName(tempFile);
        //上载到服务器的真实路径
        String realPath = Constants.FILE_ROOT + req.getContextPath() +
                File.separator + "file" + File.separator +
                id;
        File fileUploadUrl = new File(realPath);
        if (!fileUploadUrl.exists()){
            fileUploadUrl.mkdirs();
        }
        //初始化保存文件
        File saveFile = new File(realPath,fileName);
        //用于写到服务器端的文件中
        RandomAccessFile accessForSave = new RandomAccessFile(saveFile,"rw");
        //获取文件内容起始位置
        long startPosition = getStartPosition();
        //获取文件内容结束位置
        long endPosition = getEndPosition();
        //从临时文件中读取内容（依据起始位置）
        accessForSave.seek(startPosition);
        while (startPosition < endPosition){
            accessForSave.write(accessTemp.readByte());
            startPosition = accessTemp.getFilePointer();
        }

        //关闭文件访问对象，删除临时文件
        accessForSave.close();
        accessTemp.close();
        tempFile.delete();

        return realPath+fileName;
    }
}