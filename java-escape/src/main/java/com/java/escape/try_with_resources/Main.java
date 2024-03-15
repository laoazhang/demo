package com.java.escape.try_with_resources;

import java.io.*;

/**
 * 解决使用 try finally 的资源泄露隐患
 */
public class Main {

    /**
     * 传统的方式实现对资源的关闭
     *
     * @return
     * @throws IOException
     */
    private String traditionalTryCatch() throws IOException {

        // 1. 单一资源的关闭
        // String line = null;
        // BufferedReader br = new BufferedReader(new FileReader(""));
        // try {
        //     line = br.readLine();
        // } finally {
        //     br.close();
        // }
        // return line;

        // 2. 多个资源的关闭
        InputStream in = new FileInputStream("");
        try {
            OutputStream out = new FileOutputStream("");
            try {
                byte[] buf = new byte[100];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
        return null;
    }

    /**
     * Java 7 引入的 try-with-resources 实现自动的资源关闭
     *
     * @return
     * @throws IOException
     */
    private String newTryWithResources() throws IOException {

        // 1. 单个资源的使用与关闭
        // try (BufferedReader br = new BufferedReader(new FileReader(""))) {
        //     return br.readLine();
        // }

        // 2. 多个资源的使用与关闭
        try (FileInputStream in = new FileInputStream("");
             FileOutputStream out = new FileOutputStream("")) {
            byte[] buf = new byte[100];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
        return null;
    }
}
