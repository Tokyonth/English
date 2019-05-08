package com.tokyonth.english.pic;

import com.tokyonth.english.bean.BaiduImage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageTool {

    private static final String EnCode = "utf-8";

    private URL urlObject = null;
    private URLConnection uc = null;
    private InputStreamReader in = null;
    private BufferedReader reader = null;
    private StringBuffer buffer;

    //获取网页文本
    private String getResource(String url) {
        buffer = new StringBuffer();
        try {
            urlObject = new URL(url);
            uc = urlObject.openConnection();
            in = new InputStreamReader(uc.getInputStream(), EnCode);
            reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
        return buffer.toString();
    }

    //获取图片url列表
    private String getImags(String word) {
        String url = "https://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=" + word + "&pn=0&gsm=64&ct=&ic=0&lm=-1&width=0&height=0";
        List<BaiduImage> list = new ArrayList<>();
        String resource = null;
            list.clear();
            resource = getResource(url);
            BaiduImage image = null;
            int last = 0;
            String imageInfo;
            Pattern pattern = Pattern.compile("\"objURL\":\"http:\\\"?(.*?)(\\\"|>|\\\\s+)");
            Matcher matcher = pattern.matcher(resource);
            matcher.find();
           // while (matcher.find()) {
                image = new BaiduImage();
                imageInfo = matcher.group();
                last = imageInfo.lastIndexOf("/");
                image.setFilename(imageInfo.substring(last + 1, imageInfo.length() - 1));
                last = imageInfo.indexOf(":");
                image.setUrl(imageInfo.substring(last + 2, imageInfo.length() - 1));
                list.add(image);
                //System.out.println(image.getUrl() + "\n" + image.getFilename());
              //  Log.d("爬虫",image.getUrl() + "\n" + image.getFilename());
                return image.getUrl();
           // }
    }

    public String[] GetImageList(String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        for (String str : strings) {
            list.add(getImags(str));
        }
        String[] strArray = list.toArray(new String[list.size()]);
        return strArray;
    }

}
