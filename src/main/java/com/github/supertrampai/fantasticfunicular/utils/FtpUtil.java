/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.utils;

import com.github.supertrampai.fantasticfunicular.domain.FtpConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Ftp工具类
 *
 * @author wxyh
 */
public class FtpUtil {
    private static final Logger log = LoggerFactory.getLogger(FtpUtil.class);
    /**
     * ftpClient连接池初始化标志
     */
    private static volatile boolean hasInit = false;
    /**
     * ftpClient连接池
     */
    private static ObjectPool<FTPClient> ftpClientPool;

    private static FtpConfigProperties props;

    /**
     * 初始化ftpClientPool
     *
     * @param ftpClientPool
     */
    public static void init(ObjectPool<FTPClient> ftpClientPool, FtpConfigProperties props) {
        if (!hasInit) {
            synchronized (FtpUtil.class) {
                if (!hasInit) {
                    FtpUtil.ftpClientPool = ftpClientPool;
                    FtpUtil.props = props;
                    hasInit = true;
                }
            }
        }
    }

    /**
     * 获取指定路径下FTP文件
     *
     * @param remotePath
     *            路径
     * @return FTPFile数组
     * @throws IOException
     */
    public static FTPFile[] retrieveFTPFiles(String remotePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        try {
            return ftpClient.listFiles(encodingPath(remotePath + "/"), file -> file != null && file.getSize() > 0);
        } finally {
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 获取指定路径下FTP文件名称
     *
     * @param remotePath
     *            路径
     * @return ftp文件名称列表
     * @throws IOException
     */
    public static List<String> retrieveFileNames(String remotePath) throws IOException {
        FTPFile[] ftpFiles = retrieveFTPFiles(remotePath);
        if (ArrayUtils.isEmpty(ftpFiles)) {
            return new ArrayList<>();
        }
        return Arrays.stream(ftpFiles).filter(Objects::nonNull).map(FTPFile::getName).collect(Collectors.toList());
    }

    /**
     * 编码文件路径
     */
    private static String encodingPath(String path) throws UnsupportedEncodingException {
        // FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
        return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
    }

    /**
     * 获取ftpClient
     *
     * @return
     */
    private static FTPClient getFtpClient() {
        checkFtpClientPoolAvailable();
        FTPClient ftpClient = null;
        Exception ex = null;
        // 获取连接最多尝试3次
        for (int i = 0; i < 3; i++) {
            try {
                ftpClient = ftpClientPool.borrowObject();
                ftpClient.changeWorkingDirectory("/");
                break;
            } catch (Exception e) {
                ex = e;
            }
        }
        if (ftpClient == null) {
            throw new RuntimeException("未能从连接池中获取ftpClient", ex);
        }
        return ftpClient;
    }

    /**
     * 释放ftpClient
     */
    private static void releaseFtpClient(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }
        try {
            ftpClient.logout();
            ftpClient.disconnect();
            ftpClientPool.returnObject(ftpClient);
        } catch (Exception e) {
            if (ftpClient.isAvailable()) {
                try {
                    ftpClient.disconnect();
                } catch (Exception io) {

                }
            }
            log.error("连接池中没有要释放的ftpClient");
        }
    }

    /**
     * 检查ftpClientPool是否可用
     */
    private static void checkFtpClientPoolAvailable() {
        Assert.state(hasInit, "FTP未启用或连接失败！");
    }

    /**
     * 文件上传
     *
     * @param ftpPath
     *            文件上传的相对路径
     * @param fileName
     *            新文件名
     * @param oldFileName
     *            旧文件名
     * @param input
     *            上传的文件流
     * @return
     * @throws Exception
     */
    public static boolean doUpLoad(String ftpPath, String fileName, String oldFileName, InputStream input)
            throws Exception {
        boolean result = false;
        String path = props.getUploadUrl() + ftpPath;
        Integer i = 0;
        while (!result) {
            FTPClient ftpClient = getFtpClient();
            try {
                boolean fj = ftpClient.changeWorkingDirectory(path);
                if (!fj) {
                    ftpClient.makeDirectory(path);
                    ftpClient.changeWorkingDirectory(path);
                }
                ftpClient.storeFile(fileName, input);
                if (i > 0) {
                    log.error("ftp重试文件上传成功，ftp路径:" + path + ",文件名称:" + fileName);
                } else {
                    log.error("ftp文件上传成功，ftp路径为" + path + ",文件名称:" + fileName);
                }
                if(StringUtils.isNotEmpty(oldFileName)){
                    ftpClient.deleteFile(props.getUploadUrl() + oldFileName);
                }
                result = true;
            } catch (Exception ex) {
                i++;
                log.error("ftp文件上传失败，重试中。。。第" + i + "次，错误信息" + ex.getMessage());
                if (i > props.getUploadRettry()) {
                    log.error("ftp文件上传失败，超过重试次数结束重试，错误信息" + ex.getMessage());
                } else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(props.getUploadSleep());
                    } catch (Exception e1) {
                        log.error(e1.getMessage());
                    }
                }
            } finally {
                input.close();
                releaseFtpClient(ftpClient);
            }
        }
        return result;
    }

    public static boolean upLoadFile(String ftpPath, String fileName, InputStream input) throws Exception {
        boolean result = false;
        String path = props.getUploadUrl() + ftpPath;
        Integer i = 0;
        while (!result) {
            FTPClient ftpClient = getFtpClient();
            try {
                boolean fj = ftpClient.changeWorkingDirectory(path);
                if (!fj) {
                    ftpClient.makeDirectory(path);
                    ftpClient.changeWorkingDirectory(path);
                }
                ftpClient.storeFile(fileName, input);
                if (i > 0) {
                    log.error("ftp重试文件上传成功，ftp路径:" + path + ",文件名称:" + fileName);
                } else {
                    log.error("ftp文件上传成功，ftp路径为" + path + ",文件名称:" + fileName);
                }
                result = true;
            } catch (Exception ex) {
                i++;
                log.error("ftp文件上传失败，重试中。。。第" + i + "次，错误信息" + ex.getMessage());
                if (i > props.getUploadRettry()) {
                    log.error("ftp文件上传失败，超过重试次数结束重试，错误信息" + ex.getMessage());
                } else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(props.getUploadSleep());
                    } catch (Exception e1) {
                        log.error(e1.getMessage());
                    }
                }
            } finally {
                input.close();
                releaseFtpClient(ftpClient);
            }
        }
        return result;
    }

    /**
     * 下载文件
     * @param fileName
     * @param fileUrl
     * @param response
     * @throws Exception
     */
    public static void downloadFile(String fileName, String newName, String fileUrl, HttpServletResponse response) throws Exception{
        //文件路径
        String path = props.getUploadUrl() + fileUrl + "/" + fileName;
        //获取文件的后缀名
        fileName = "".equals(newName) ? fileName : newName;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String contentType = ContentTypeUtils.getContentType(suffixName);
        response.setContentType(contentType + ";charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FTPClient ftpClient = getFtpClient();
        try {
            InputStream in = ftpClient.retrieveFileStream(encodingPath(path));
            Throwable var5 = null;
            try {
                OutputStream out = response.getOutputStream();
                Throwable var7 = null;
                try {
                    byte[] buf = new byte[10240];
                    int size;
                    while((size = in.read(buf)) > 0) {
                        out.write(buf, 0, size);
                        out.flush();
                    }
                } catch (Throwable var43) {
                    var7 = var43;
                    throw var43;
                } finally {
                    if (out != null) {
                        if (var7 != null) {
                            try {
                                out.close();
                            } catch (Throwable var42) {
                                var7.addSuppressed(var42);
                            }
                        } else {
                            out.close();
                        }
                    }

                }
            } catch (Throwable var45) {
                var5 = var45;
                throw var45;
            } finally {
                if (in != null) {
                    if (var5 != null) {
                        try {
                            in.close();
                        } catch (Throwable var41) {
                            var5.addSuppressed(var41);
                        }
                    } else {
                        in.close();
                    }
                }

            }
        } finally {
            releaseFtpClient(ftpClient);
        }
    }



    /**
     * 下载 remote文件流
     *
     * @param remote
     *            远程文件
     * @return 字节数据
     * @throws Exception
     */
    public static byte[] doDownLoad(String remote) throws Exception{
        FTPClient ftpClient = getFtpClient();
        InputStream in = null;
        try {
            String path = props.getUploadUrl() + remote;
            in = ftpClient.retrieveFileStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while ((n = in.read(buffer)) > 0) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } finally {
            if (in != null) {
                in.close();
            }
            releaseFtpClient(ftpClient);
        }
    }

    private static byte[] inputStreamToByteArray(InputStream in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while ((n = in.read(buffer)) > 0) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
