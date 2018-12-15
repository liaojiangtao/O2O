package com.gentel.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.Logger;

@Slf4j
public class imageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

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
}
