package com.springboot.demo.test.shiroTest;

import com.springboot.demo.utils.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.jnlp.IntegrationService;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@SpringBootTest
public class ShiroTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //登录
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("身份验证失败....---->" + e.getMessage());
        }

        Assert.assertEquals(true, subject.isAuthenticated());
        //退出
        subject.logout();
    }

    @Test
    public void testDownLoad() {
        String imageUrl = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4286489910,2283325935&fm=26&gp=0.jpg";

        try {
            URL url = new URL(imageUrl);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream inputStream = conn.getInputStream();
            File file = new File("upload");
            if(!file.exists()) {
                file.mkdir();
            }
            int index = imageUrl.lastIndexOf(".");
            String substring = imageUrl.substring(index);
            String imageName = genUUid() + substring;
            File file1 = new File(file.getPath() + File.separator + imageName);
            if(!file1.exists()) {
                file1.createNewFile();
            }

            OutputStream os = new FileOutputStream(new File("upload"+File.separator+imageName));
            int count = 0;
            byte[] bytes = new byte[1024];
            int len;
            while((len = inputStream.read(bytes)) != -1) {
                os.write(bytes,0,len);
                count++;
            }
            os.close();
            inputStream.close();
            System.out.println(count);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<List<?>> spiltData(List<?> data,int pageSize) {

        int size = data.size();

        List<List<?>> datas = new ArrayList<>();

        if(size <= pageSize) {
            datas.add(data);
            return datas;
        }else {
           int page = size / pageSize;
           int mo = size % pageSize;
           if(mo != 0) {
               page++;
           }
           int i = 0;
           while(i< page) {
               int offSet = i*pageSize;
               if(i == page-1) {
                   mo = mo == 0 ? pageSize : mo;
                   datas.add(data.subList(offSet, offSet + mo));
               }else{
                   datas.add(data.subList(offSet, offSet + pageSize));
               }
               i++;
           }
        }

        return datas;
    }

    private static String genUUid() {
        UUID uuid1 = UUID.randomUUID();
        return uuid1.toString().replace("-","");
    }
    public static void main(String[] args) throws IOException {
//        UUID uuid1 = UUID.randomUUID();
//        String str = uuid1.toString().replace("-", "");
//        String substring = str.substring(14);
//        String date = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
//        String uuidString = date + substring;
//        System.out.println("uuidString----->" + uuidString);
//        System.out.println(uuid1.toString().replace("-", ""));
//        System.out.println(localDateTime.toString());

//        File file = new File("upload");
//        if(!file.exists()) {
//            file.mkdir();
//        }
//        File file1 = new File(file.getPath() + File.separator + "1.jpg");
//        if(!file1.exists()) {
//            file1.createNewFile();
//        }
//        String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592125146432&di=3" +
//                "822c9620202ff8ea0e515f84cb976a9&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fa044ad345982b2b76cbfbd2133adcbef76099b37.jpg";
//        int index = imageUrl.lastIndexOf(".");
//        String substring = imageUrl.substring(index);
//        String imageName = genUUid() + substring;
//        System.out.println(imageName);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        List<List<?>> lists = spiltData(list, 10);
        for (List<?> objects : lists) {
            System.out.println(objects.size());
            for (Object object : objects) {
                System.out.println((Integer)object);
            }
            System.out.println("-------------------------");
        }

    }



}
