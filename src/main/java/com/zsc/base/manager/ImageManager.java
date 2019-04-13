package com.zsc.base.manager;
import com.zsc.base.Config;
import com.zsc.base.utils.DateUtils;
import com.zsc.base.vo.FileInfo;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageManager {
    //图片FTP上传目录
    public static String IMGSERVER_FTPPATH = null;
    //图片服务器物理路径
    public static String IMGSERVER_PHYSICAL = null;
    public static String IMGSERVER_URL = null;
    private static Map<IMAGE_KIND, Integer[]> SIZE_MAP = new HashMap<IMAGE_KIND, Integer[]>();
    //存放在images文件夹的子目录
    private static Map<IMAGE_KIND, String> VPATH_MAP = new HashMap<IMAGE_KIND, String>();
    static {
        init();
    }

    private static void init() {
        IMGSERVER_PHYSICAL = Config.getProperty("IMGSERVER.PHYSICAL");
        IMGSERVER_URL = Config.getProperty("IMGSERVER.URL");
        IMGSERVER_FTPPATH = Config.getProperty("IMGSERVER.FTPPATH");
        //处理一些防止配置人员出错的代码
        IMGSERVER_PHYSICAL = IMGSERVER_PHYSICAL.replace("\\", "/");
        if (IMGSERVER_PHYSICAL.endsWith("/")) {
            IMGSERVER_PHYSICAL = IMGSERVER_PHYSICAL.substring(0, IMGSERVER_PHYSICAL.length() - 1);
        }

        IMGSERVER_FTPPATH = IMGSERVER_FTPPATH.replace("\\", "/");
        if (IMGSERVER_FTPPATH.endsWith("/")) {
            IMGSERVER_FTPPATH = IMGSERVER_FTPPATH.substring(0, IMGSERVER_FTPPATH.length() - 1);
        }
        if (IMGSERVER_FTPPATH.startsWith("/")) {
            IMGSERVER_FTPPATH = IMGSERVER_FTPPATH.substring(1);
        }

        if (IMGSERVER_URL.endsWith("/")) {
            IMGSERVER_URL = IMGSERVER_URL.substring(0, IMGSERVER_URL.length() - 1);
        }
        VPATH_MAP.put(IMAGE_KIND.SLIDER, "/slider");
        VPATH_MAP.put(IMAGE_KIND.THUMB, "/thumb");
        VPATH_MAP.put(IMAGE_KIND.ARTICLE, "/artimg");
        VPATH_MAP.put(IMAGE_KIND.USER_ICON, "/user_icon");
        VPATH_MAP.put(IMAGE_KIND.FILE, "/files");
        //各种图片大小配置信息
        SIZE_MAP.put(IMAGE_KIND.THUMB, new Integer[]{270, 202});
        SIZE_MAP.put(IMAGE_KIND.SLIDER, new Integer[]{1080, 624});
    }

    /**
     * 创建各种保存的真实和虚拟路径
     * 0：D:/tomcat/webapps/ROOT/news/slider/2015/08/11/
     * 1：http://192.168.1.129/news/slider/2015/08/11/
     * 2：2015/08/11/
     *
     * @return
     */
    public static FileInfo createFileInfo(IMAGE_KIND kind, String fileTypeName) {
        Date time = new Date();
        String virDir = DateUtils.format("/yyyy/MM/dd", time);
        FileInfo fi = new FileInfo();
        setDirByKind(kind, fi);
        fi.setVirDir(virDir);
        fi.setFileName(createFileName(fileTypeName));
        return fi;
    }

    private static String createFileName(String fileTypeName) {
        if (!fileTypeName.startsWith(".")) {
            fileTypeName = "." + fileTypeName;
        }
        return String.valueOf(System.currentTimeMillis()) + fileTypeName;
    }

    /**
     * 删除文件
     *
     * @param virPath 虚拟路径 /开头 EX：/2015/08/17/14123123123.jpg
     */
    public static void deleteImg(IMAGE_KIND kind, String virPath) {
        String sp = getPrePath(kind);
        sp += sp.endsWith("/") ? "" : "/";
        String truePath = sp + virPath;
        File file = new File(truePath);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        file = null;
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteImg(File file) {
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        file = null;
    }

    /**
     * 虚拟路径 /开头 EX：/2015/08/17/14123123123.jpg
     *
     * @param dbPath
     * @return
     */
    public static String getUrl(IMAGE_KIND kind, String dbPath) {
        return getPreUrl(kind) + dbPath;
    }

    /**
     * 获取保存的物理路径 (D:/tomcat/webapps/ROOT/news/thumb)
     *
     * @param kind
     * @return
     */
    public static String getPrePath(IMAGE_KIND kind) {
        String vpath = VPATH_MAP.get(kind);
        if (vpath == null) {
            return IMGSERVER_PHYSICAL + "/images";
        } else {
            return IMGSERVER_PHYSICAL + vpath;
        }
    }

    public static String getPreUrl(IMAGE_KIND kind) {
        String vpath = VPATH_MAP.get(kind);
        if (vpath == null) {
            return IMGSERVER_URL + "/images";
        } else {
            return IMGSERVER_URL + vpath;
        }
    }

    /**
     * 设置当前的文件夹路径
     *
     * @param kind
     * @param fi
     */
    public static void setDirByKind(IMAGE_KIND kind, FileInfo fi) {
        String vpath = VPATH_MAP.get(kind);
        if (vpath == null) {
            fi.setPreDir(IMGSERVER_PHYSICAL + "/images");
            fi.setPreUrl(IMGSERVER_URL + "/images");
            fi.setFtpRootDir(IMGSERVER_FTPPATH+"/images");
        } else {
            fi.setPreDir(IMGSERVER_PHYSICAL + vpath);
            fi.setPreUrl(IMGSERVER_URL + vpath);
            fi.setFtpRootDir(IMGSERVER_FTPPATH+vpath);
        }
    }

    /**
     * 获取数量
     *
     * @return
     */
    public static int getCount(String str) {
        return StringUtils.isEmpty(str) ? 0 : str.split(",").length;
    }

    /**
     * 获取完整访问路径
     *
     * @return
     */
    public static String getThumbFullPathStr(IMAGE_KIND kind, String str) {
        List<String> lst = getThumbFullPath(kind, str);
        return StringUtils.join(lst, ",");
    }

    public static List<String> getThumbFullPath(IMAGE_KIND kind, String str) {
        List<String> lst = new ArrayList<String>();
        if (getCount(str) == 0) return lst;
        String[] images = str.split(",");
        for (int j = 0; j < images.length; j++) {
            lst.add(ImageManager.getPreUrl(kind) + images[j]);
        }
        return lst;
    }

    public static String getTeamNoLogo() {
        return IMGSERVER_URL + "/logo/error.png";
    }

    public static void main(String[] args) {
        System.out.println(replaceImgTag(":<p>${IMG#1$}<br /></p><p>&"));
    }

    // ${IMG#1$} 替换成>>> <!--IMG#0-->
    public static String replaceImgTag(String content) {
        if (content == null) return "";
        return content.replaceAll("\\$\\{IMG#", "<!--IMG#").replaceAll("\\$\\}", "-->");
    }

    //,缩略图,图集,文章,
    public enum IMAGE_KIND {
        MYTAG, THUMB, SLIDER, ARTICLE, USER_ICON, FILE
    }
}
