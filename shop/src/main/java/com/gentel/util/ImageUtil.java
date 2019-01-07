package com.gentel.util;

import com.gentel.dto.ImageHolder;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /*
     * 将commonsMultipartFile转换成File类
     *
     * @param cFile
     * @return cFile
     * */

    public static File transferComminsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());

        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            log.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /*
     * 处理缩略图，并返回新生成图片的相对值路径
     *
     * @Param thumbnail
     * @Param targetAddr
     * @return
     * */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        //获取不重复的随机名
        String realFileName = getRandomFileName();
        //获取文件扩展名，如png jpg
        String extension = getFileExtension(thumbnail.getImageName());
        //如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        //获取文件存储的相对路径（带文件名）
        String relativeAddr = targetAddr + realFileName + extension;
        log.debug("current relativeAddr is:" + relativeAddr);
        //获取文件要保存的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        log.debug("current complate addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        log.debug("basePath is :" + basePath);
        //调用ThumBnail生成水印
        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException("创建缩略图失败:" + e.toString());
        }

        return relativeAddr;
    }

    /*
     * 处理详情图片，并返回新生成图片的相对地址
     *
     * @Param
     * */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        //获取不重复的随机名
        String realFileName = getRandomFileName();
        //获取文件袋额扩展名
        String extension = getFileExtension(thumbnail.getImageName());
        //如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        //获取文件存储的相对路径（带文件名）
        String relativeAddr = targetAddr + realFileName + extension;
        log.debug("current relativeAddr is :" + relativeAddr);
        //获取文件要保存的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        log.debug("current complate addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        //调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException("创建缩略图失败:" + e.toString());
        }

        return relativeAddr;
    }

    /*
     * 创建目标路径所涉及到的目录，即是:/home/work/shop/xxx.jpg,那么 home work shop
     * 这三个文件夹都得创建
     *
     * @param targetAddr
     * */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /*
     * 获取输入文件流的扩展名
     *
     * @param fileName
     * @return
     * */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /*
     * 生成随机文件名，当前年月日时分秒+5位随机数
     *
     * @return
     * */
    private static String getRandomFileName() {
        //获取随机的5位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /*
    * storePath是文件的路径还是目录的路径，如果storePath是文件路劲则删除该文件
    * 如果storePath是目录则删除该目录下的所有文件
    *
    * @param storePath
    * */
    private static void deleteFileOrFath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }
}
