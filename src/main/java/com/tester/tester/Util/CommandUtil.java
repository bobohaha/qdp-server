package com.tester.tester.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandUtil {

    private static String osname = System.getProperty("os.name").toLowerCase();

    private static String executeShell(String command) throws IOException {
        StringBuffer result = new StringBuffer();
        Process process = null;
        InputStream is = null;
        BufferedReader br = null;
        String line = null;
        try {

            if (osname.indexOf("windows") >= 0) {
                process = new ProcessBuilder("cmd.exe", "/c", command).start();
                System.out.println("cmd.exe /c " + command); //安装Cygwin，使windows可以执行linux命令
            } else {
                process = new ProcessBuilder("/bin/sh", "-c", command).start();
                System.out.println("/bin/sh -c " + command);
            }

            is = process.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
            process.destroy();
            is.close();
        }

        return result.toString();
    }
}
