package com.tester.tester.Config;

import com.tester.tester.Constants.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class SysConfig {
    private static Properties sysConfig = new Properties();

    private static final Logger logger = LoggerFactory.getLogger(SysConfig.class);

    //读取配置文件
    static {
        InputStream inputStream = SysConfig.class.getResourceAsStream(ConfigConstants.SYSCONFIG_PATH);
        try {
            sysConfig.load(inputStream);
        } catch (IOException e) {
            logger.error("读取" + ConfigConstants.SYSCONFIG_PATH + "失败", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("读取" + ConfigConstants.SYSCONFIG_PATH + "失败", e);
            }
        }
    }

    /**
     * 根据属性读取值
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return sysConfig.getProperty(key);
    }

    /**
     * 写入值
     *
     * @param key
     * @param value
     */
    public static void setProperty(String key, String value) {

        sysConfig.setProperty(key, value);
    }

    /**
     * 读取指定文件
     *
     * @param filePath
     * @return
     */
    public static String readerFile(String filePath) {
        try {

            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                StringBuilder stringBuilder = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineTxt);
                }
                return stringBuilder.toString();
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            logger.error("Cannot find the file specified!", e);
        } catch (IOException e) {
            logger.error("Error reading file content!", e);
        }
        return null;
    }
}
