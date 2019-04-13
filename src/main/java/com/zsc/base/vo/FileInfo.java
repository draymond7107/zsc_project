package com.zsc.base.vo;

import com.zsc.base.utils.JsonUtils;

import java.io.File;

public class FileInfo {
    /**
     * preUrl  ：  http://**.**.com/news/thumb
     * preDir  :  /var/
     * virDir  :  /2015/08/17
     * fileName : 14123123123.jpg
     */
    /**
     * 访问URL的前缀
     **/
    private String preUrl;
    /**
     * 实际保存文件夹路径
     **/
    private String preDir;
    /**
     * 虚拟文件夹路径
     **/
    private String virDir;
    /**
     * 文件名称
     **/
    private String fileName;

    //ftp上传的根目录
    private String ftpRootDir;
    /**
     * 初始化物理路径
     */
    public void initPhyDir() {
        File file = new File(preDir + virDir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 获取保存到数据库的数据
     *
     * @return
     */
    public String getDbPath() {
        return virDir + "/" + fileName;
    }

    /**
     * 获取访问的HTTP地址
     *
     * @return
     */
    public String getHttpUrl() {
        return preUrl + virDir + "/" + fileName;
    }

    /**
     * 真实的保存全路径
     * @return
     */
    public String getFilePath() {
        return preDir + virDir + "/" + fileName;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getPreDir() {
        return preDir;
    }

    public void setPreDir(String preDir) {
        this.preDir = preDir;
    }

    public String getVirDir() {
        return virDir;
    }

    public void setVirDir(String virDir) {
        this.virDir = virDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFtpRootDir(String ftpRootDir) {
        this.ftpRootDir = ftpRootDir;
    }

    public String getFtpRootDir() {
        return ftpRootDir;
    }

    @Override
    public String toString() {
        return JsonUtils.toString(this);
    }
}
