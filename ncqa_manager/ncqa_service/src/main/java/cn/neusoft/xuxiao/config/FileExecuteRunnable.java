package cn.neusoft.xuxiao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public class FileExecuteRunnable implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(FileExecuteRunnable.class);

    private MultipartFile file = null;
    private String path;

    public FileExecuteRunnable(MultipartFile file,String path){
        this.file = file;
        this.path = path;
    }
    @Override
    public void run() {
        synchronized (this.getClass()){
            try {
                file.transferTo(new File(path));
                logger.info("文件保存成功!");
            }catch(IOException e){
                e.printStackTrace();
                logger.error("文件保存失败!");
            }
        }
    }
}
