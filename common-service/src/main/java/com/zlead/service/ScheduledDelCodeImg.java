package com.zlead.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledDelCodeImg {

    Logger logger= LoggerFactory.getLogger(ScheduledDelCodeImg.class);
    /**
     * 每天凌晨一点执行删除过期图片的定时任务
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void deleteCodeImage() {
        try {
            String path= null;
            try {
                path = ResourceUtils.getURL("classpath:").getPath()+"META-INF/resources/";
            } catch (FileNotFoundException e) {
                logger.info("项目路径获取失败");
            }
            logger.info("删除过期图片开始:{}",path);
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    Arrays.asList(files).forEach(e -> {
                        if (e.lastModified() < Instant.now()
                                .minusMillis(TimeUnit.HOURS.toMillis(24))
                                .toEpochMilli()) {
                            e.delete();
                        }
                    });
                }
            }
        } catch (Exception e) {
            logger.info("文件删除失败" + e.getMessage());
        }
        logger.info("删除过期图片结束");
    }
}
