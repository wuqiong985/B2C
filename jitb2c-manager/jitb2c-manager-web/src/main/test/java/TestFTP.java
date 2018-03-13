import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author wuqiong6
 * @Date 2018/3/13 21:31
 */
public class TestFTP {

    @Test
    public void testFtpClient() throws IOException {
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();

        //创建Ftp连接.默认是21端口
        ftpClient.connect("172.27.3.132",21);

        //登录ftp服务器，使用用户名和密码
        ftpClient.login("anonymous","");

        //上传文件
        //读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\wq\\Desktop\\新建文件夹\\wq-1.png"));
        //设置上传的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");

        //修改文件上传的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        //第一个参数：服务期端的文件名
        //第二个参数：上传文件的InputStream
        ftpClient.storeFile("hello1.jpg",inputStream);

        //关闭连接
        ftpClient.logout();
    }
}
