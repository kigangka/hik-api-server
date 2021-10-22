package com.lyc.hik.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.URLUtil;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片处理工具类
 *
 * @author kisang
 * @date 2021年10月22日15:54:44
 */
public class PicUtils {

    /**
     * 根据图片地址将图片转成Base64（本地路径）
     *
     * @param imgPath
     * @return Base64字符串
     */
    public static String localImgToBase64(String imgPath) {
        URL url = null;
        try {
            url = new URL(imgPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream input = URLUtil.getStream(url);
        return Base64.encode(input);
    }

    /**
     * 网络地址图片转base64
     * @param imgUrl
     * @return Base64字符串
     */
    public static String netImgToBase64(String imgUrl) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            byte[] by = new byte[1024];
            InputStream is = URLUtil.getStream(new URL(imgUrl));
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

    /**
     * 读取本地文件转换成base64
     *
     * @return Base64字符串
     */
    public static String localFileToBase64() {
        String str = ResourceUtil.readUtf8Str("file/default.txt");
        return Base64.encode(str);
    }
}
