package com.myee.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray.Fu on 2016/4/22.
 */
public class ConverterUtil {
    private String toAnyConversion = "";

    //二维码格式参数
    private static final EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
    private static final Map<EncodeHintType, Object> hintss = new HashMap<EncodeHintType, Object>();

    static {
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        hintss.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    }

    public static void main(String[] args) {
//        BigInteger a = new BigInteger("359737606");
//        BigInteger b = new BigInteger("36");
//        System.out.println("36: "+toAnyConversion(a,b));
        String a = "a101";
        String temp = a.substring(1,4);
        System.out.println("temp: " + temp);
//        try {
//            //new一个URL对象
//            URL url = new URL("http://cdn.myee7.com/FszfZz-Xes4BiRLrZCHQlnfCaWqe");
//            //打开链接
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            //设置请求方式为"GET"
//            conn.setRequestMethod("GET");
//            //超时响应时间为5秒
//            conn.setConnectTimeout(5 * 1000);
//            //通过输入流获取图片数据
//            InputStream inStream = conn.getInputStream();
//            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
//            byte[] data = readInputStream(inStream);
//            //new一个文件对象用来保存图片，默认保存当前工程根目录
//            File imageFile = new File("D://BeautyGirl.jpg");
//            //创建输出流
//            FileOutputStream outStream = new FileOutputStream(imageFile);
//            //写入数据
//            outStream.write(data);
//            //关闭输出流
//            outStream.close();
//            BufferedImage image = ImageIO.read(imageFile);
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            Binarizer binarizer = new HybridBinarizer(source);
//            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
//            MultiFormatReader reader = new MultiFormatReader();
//            Result result = reader.decode(binaryBitmap, hints);
//            String content = result.getText();
//            System.out.println("result"+":"+result.toString());
//            System.out.println("getBarcodeFormat" + ":" + result.getBarcodeFormat());
//            System.out.println("content" + ":" + content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    public void reGenerationQRCode() {
        String content = null;
        String sFilePath = "D://dd.jpg";
        BufferedImage image;
        try {
            image = ImageIO.read(new File(sFilePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();


            String dFilePath = "D://";
            String dFileName = "ddd.png";
            int dWidth = 200; // 图像宽度
            int dHeight = 200; // 图像高度
            String format = "png";// 图像类型
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content + "&17uz88", BarcodeFormat.QR_CODE, dWidth, dHeight, hintss);// 生成矩阵
            Path path = FileSystems.getDefault().getPath(dFilePath, dFileName);
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
            System.out.println("输出成功.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    static char changToNum(BigInteger temp)
    {
        int n = temp.intValue();

        if(n >= 10 && n <= 35)
            return (char) (n - 10 + 'A');

        else if(n >= 36 && n <= 61)
            return (char)(n - 36 + 'a');

        else
            return (char)(n + '0');
    }

    //十进制转换为任意进制
    public static String toAnyConversion(BigInteger Bigtemp, BigInteger base)
    {
        String ans = "";
        while(Bigtemp.compareTo(BigInteger.ZERO) != 0)
        {
            BigInteger temp = Bigtemp.mod(base);
            Bigtemp = Bigtemp.divide(base);
            char ch = changToNum(temp);
            ans = ch + ans;
        }
        return ans;
//        this.setToAnyConversion(ans);
    }

    public void setToAnyConversion(String toAnyConversion) {
        this.toAnyConversion = toAnyConversion;
    }
}

