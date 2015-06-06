package com.college.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.college.dao.basic.AbstractServiceDao;
import com.college.util.Logger4j;
import com.college.util.TextUtil;

public class DaoUploadService extends AbstractServiceDao implements UploadService
{
    private static final Logger log = Logger4j.getLogger(DaoUploadService.class);
    private static List<String> FILE_TYPE_VIDEO = null;
    private static List<String> FILE_TYPE_DOC = null;
    
    static
    {
        FILE_TYPE_VIDEO = new ArrayList<String>();
        FILE_TYPE_VIDEO.add(".mp4");
        FILE_TYPE_VIDEO.add(".mp3");
        FILE_TYPE_VIDEO.add(".wmv");
        
        FILE_TYPE_DOC = new ArrayList<String>();
        FILE_TYPE_DOC.add(".pdf");
        FILE_TYPE_DOC.add(".txt");
        FILE_TYPE_DOC.add(".csv");
        FILE_TYPE_DOC.add(".xls");
    }
    
    private String destLocation;
    
    public String getDestLocation()
    {
        return destLocation;
    }

    public void setDestLocation(String destLocation)
    {
        this.destLocation = destLocation;
    }

    public void saveUploadFile(String realPath, String fileName, InputStream stream)
            throws FileNotFoundException, IOException
    {
        realPath = getDestPath(fileName, realPath);
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
        
        /*if(fileSize == dest.length())
        {
            log.warn("File already exists, skip upload.");
            return;
        }*/
        
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
    
    private String getDestPath(String fileName, String location)
    {
        String subDri = "others";
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if(FILE_TYPE_VIDEO.contains(suffix))
            subDri = "video";
        else if(FILE_TYPE_DOC.contains(suffix))
            subDri = "doc";
        return location.endsWith(File.separator) ? location.concat(subDri) : location.concat(File.separator).concat(subDri);
    }
}
