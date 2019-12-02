/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.domain;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Ftp配置属性类，建立ftpClient时使用
 */
@Data
@ConfigurationProperties(prefix = "ftp")
public class FtpConfigProperties {
    /**
     * ftp地址
     */
    private String host = "localhost";
    /**
     * ftp 端口
     */
    private Integer port = FTPClient.DEFAULT_PORT;
    /**
     * ftp 用户名
     */
    private String username;
    /**
     * ftp 密码
     */
    private String password;
    /**
     * ftp 上传路径
     */
    private String uploadUrl;
    /**
     * 文件下载失败下次超时重试时间
     */
    private Integer downloadSleep;
    /**
     * 文件下载失败重试次数
     */
    private Integer downloadRetry;
    /**
     * 文件上传失败下次超时重试时间
     */
    private Integer uploadSleep;
    /**
     * 文件上传失败重试次数
     */
    private Integer uploadRettry;

    private Integer bufferSize = 8096;

    /**
     * 初始化连接数
     */
    private Integer initialSize = 0;

    /**
     * 最大连接数
     */
    private Integer maxTotal = 0;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getDownloadSleep() {
        return downloadSleep;
    }

    public void setDownloadSleep(Integer downloadSleep) {
        this.downloadSleep = downloadSleep;
    }

    public Integer getDownloadRetry() {
        return downloadRetry;
    }

    public void setDownloadRetry(Integer downloadRetry) {
        this.downloadRetry = downloadRetry;
    }

    public Integer getUploadSleep() {
        return uploadSleep;
    }

    public void setUploadSleep(Integer uploadSleep) {
        this.uploadSleep = uploadSleep;
    }

    public Integer getUploadRettry() {
        return uploadRettry;
    }

    public void setUploadRettry(Integer uploadRettry) {
        this.uploadRettry = uploadRettry;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }
}
