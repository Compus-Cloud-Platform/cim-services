package com.college.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.log4j.Logger;

import com.college.dao.basic.AbstractServiceDao;
import com.college.util.Logger4j;

public class DaoUploadService extends AbstractServiceDao implements UploadService
{
    private static final Logger log = Logger4j.getLogger(DaoUploadService.class);
    
    public void saveUploadFile(String realPath, String fileName, long fileSize, InputStream stream)
            throws FileNotFoundException, IOException
    {
        File dirFile = new File(realPath);
        boolean makeFlag = false;

        if (!dirFile.exists())
        {
            makeFlag = dirFile.mkdirs();

            if (!makeFlag || !dirFile.exists())
            {
                log.error("Can not create dir:" + dirFile.getAbsolutePath());
                return;
            }
        }

        File dest = new File(dirFile, fileName);// FileUtil.getCreatableFile(dirFile,
        
        if(fileSize == dest.length())
        {
            log.warn("File already exists, skip upload.");
            return;
        }
        
        // fileName);
        String destPath = dest.getAbsolutePath();

        // if exist, delete old one
        if (dest.exists())
        {
            dest.delete();
        }
        BufferedInputStream bufferInStream = new BufferedInputStream(stream,
                512 * 10);

        OutputStream bos = new BufferedOutputStream(new FileOutputStream(
                destPath), 512 * 10);

        while (true)
        {
            int data = bufferInStream.read();
            if (data == -1)
            {
                break;
            }
            bos.write(data);
        }

        bos.close();
        stream.close();
        dest.setLastModified((new Date()).getTime());
        log.info("File save to :" + destPath);
    }
}
