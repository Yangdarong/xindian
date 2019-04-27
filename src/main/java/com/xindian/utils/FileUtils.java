package com.xindian.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {
    public static final String TYPE_JPG = "jpg";
    public static final String TYPE_GIF = "gif";
    public static final String TYPE_PNG = "png";
    public static final String TYPE_BMP = "bmp";
    public static final String TYPE_UNKNOWN = "unknown";

    public static String uploadPicture(MultipartFile pictureFile, HttpServletRequest request, String type) {
        // 使用UUID给图片重命名
        String name = UUID.randomUUID().toString().replace("-", "");
        // 获取文件的拓展名
        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        // 设置文件上传的路径
        String url = request.getSession().getServletContext().getRealPath("/upload/" + type);
        String fUrl = "/upload/"+ type + "/" + name + "." + ext;

        //System.out.println(url);
        File file = new File(url + "/" + name + "." + ext);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        // 以绝对路径保存重命名后的图片
        try {
            pictureFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fUrl;
    }

    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        if (src == null) {
            return null;
        }

        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);

            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }

        return sb.toString();
    }

    /**
     * 根据文件流判断图片类型
     * @param fis
     * @return jpg/png/git/bmp
     */
    public static String getPicType(FileInputStream fis) {
        // 读取文件的前几个字节来判断图片格式
        byte[] b = new byte[4];
        try {
            fis.read(b, 0, b.length);
            String type = bytesToHexString(b).toUpperCase();
            if (type.contains("FFD8FF")) {
                return TYPE_JPG;
            } else if (type.contains("89504E47")) {
                return TYPE_PNG;
            } else if (type.contains("47494638")) {
                return TYPE_GIF;
            } else if (type.contains("424D")) {
                return TYPE_BMP;
            }else{
                return TYPE_UNKNOWN;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
