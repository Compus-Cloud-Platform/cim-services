package com.college.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.NestedRuntimeException;

public class FileUtil
{
    private static final Logger log = Logger4j.getLogger(FileUtil.class);
    
    public static boolean copy(File inputFile, File outputFile) throws IOException
    {
        return copy(inputFile, outputFile, false);
    }
    
    public static boolean copy(File inputFile, File outputFile, boolean overwriteFlag) throws IOException
    {
        return copy(inputFile, outputFile, overwriteFlag, false);
    }
    
    public static boolean move(File inputFile, File outputFile) throws IOException
    {
        return copy(inputFile, outputFile, true, true);
    }
    
    public static boolean copyAndDeleteSouce(File sourceFile, File destFile)
    {
        boolean result = false;
        try
        {
            result = copy(sourceFile, destFile, false, true);
        }
        catch (IOException e)
        {
            log.error(e);
        }
        return result;

    }
    
    private static boolean copy(File inputFile, File outputFile,
            boolean overwriteFlag, boolean deleteOnCopy) throws FileNotFoundException, IOException
    {
        if (inputFile == null || !inputFile.exists() || !inputFile.isFile()
                || !inputFile.canRead())
        {
            log.error("Source file does not exist or can not be read: "+ inputFile.getAbsolutePath()+ ". Copy skipped." );
            return false;
        }
        
        if (!outputFile.getParentFile().exists())
        {
            if (!outputFile.getParentFile().mkdirs())
            {
                log.error("Can not make dir for file:" + outputFile.getAbsolutePath()+ ". Copy skipped.");
                return false;
            }
        }
        
        if (!outputFile.getParentFile().canWrite())
        {
            log.error("Out put file:" + outputFile.getAbsolutePath()
                    + " can not be write.");
            return false;
        }
        
        if (outputFile.exists() && !outputFile.canWrite())
        {
            log.error("Out put file:" + outputFile.getAbsolutePath()
                    + " can not be write.");
            return false;
        }
        
        if (overwriteFlag == false)
            if (outputFile.exists() && outputFile.length()==inputFile.length())
            {
                log.warn("Dest file: "+ outputFile.getAbsolutePath() +" already exists, copy operation skipped!");
                return true;
            }
        
        if (inputFile.getAbsolutePath().equalsIgnoreCase(
                outputFile.getAbsolutePath()))
        {
            log.warn("Source file and dest file are same file.");
            return false;
        }

        doCopy(inputFile, outputFile, deleteOnCopy);
        outputFile.setLastModified((new Date()).getTime());
        return true;
    }
    
    private static void doCopy(File sourceFile, File destFile, boolean deleteOnCopy)
            throws FileNotFoundException, IOException
    {
        if (deleteOnCopy)
        {
            boolean destFileExists = destFile.exists();
            if (destFileExists)
                destFileExists = (!destFile.delete());

            if (!destFileExists)
            {
                if (sourceFile.renameTo(destFile))
                {
                    log.info("File renamed from: " + sourceFile.getAbsolutePath()
                                + "\n to: " + destFile.getAbsolutePath());
                    return;
                }
                
                destFile.createNewFile();
            }
        }
        
        FileChannel source = null;
        FileChannel destination = null;
        try
        {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            int maxCount = (16 * 1024 * 1024) - (32 * 1024); // 16MB in buffer
            long size = source.size();
            long position = 0;
            while (position < size)
            {
                position += source.transferTo(position, maxCount, destination);
            }
        }
        finally
        {
            if (source != null)
                source.close();
            if (destination != null)
                destination.close();
        }
        
        try
        {
            if (deleteOnCopy)
                sourceFile.delete();
        }
        catch (Exception e)
        {
            log.warn("Failed to delete file: " + sourceFile + ": " + e);
            if (log.isDebugEnabled())
                log.debug(e);
        }
        
        log.info("File copy from:" + sourceFile.getAbsolutePath() + "\n to: "
                + destFile.getAbsolutePath());
    }
    
    public static void doWriteData(InputStream inputStream, OutputStream outputStream) throws IOException
    {
        InputStream in = new BufferedInputStream(inputStream, 512 * 10);
        OutputStream out = new BufferedOutputStream(outputStream, 512 * 10);
        try
        {
            pipe(in, out);
        }
        finally
        {
            IOUtil.close(in);
            IOUtil.close(out);
        }
    }
    
    private static void pipe(InputStream in, OutputStream out)
        throws IOException
    {
        byte[] buf = new byte[1024];
        int bytes;
        while ((bytes = in.read(buf)) > 0)
            out.write(buf, 0, bytes);
    }
    
    public static void deleteFile(String filePath)
    {
        if (filePath == null || filePath.length() == 0
                || filePath.endsWith("\\"))
        {
            log.warn("File path is null.");
            return;
        }

        File file = new File(filePath);
        if (file.exists() && file.canWrite())
        {
            file.delete();
            log.debug("File:" + filePath + " is deleted.");
        }
        else
        {
            log.warn("File:" + filePath + " not exists or can not be written.");
        }
    }
    
    public static void deleteAllFiles(String filePath)
    {
        if (filePath == null || filePath.length() == 0
                || filePath.endsWith("\\"))
        {
            log.warn("File path is null.");
            return;
        }

        File file = new File(filePath);
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                deleteAllFiles(files[i].getAbsolutePath());
            }
            file.delete();
        }
        else
        {
            deleteFile(file.getAbsolutePath());
        }
    }
    
    public static List getFileList(List resultList, File dir,
            FileFilter filter, int cutPos)
    {
        resultList = getFileList(resultList, dir, filter);
        resultList = getFileNameList(resultList, cutPos);
        return resultList;
    }

    public static List getFileNameList(List originList, int cutPos)
    {
        List resultList = new ArrayList();
        String path;

        for (Iterator iter = originList.iterator(); iter.hasNext();)
        {
            File file = (File) iter.next();
            path = file.getAbsolutePath().substring(cutPos).replaceAll("\\\\",
                    "/");
            resultList.add(path);
        }
        return resultList;
    }

    public static List getFileList(List resultList, File dir, FileFilter filter)
    {
        File[] files = null;
        
        if (filter != null)
        {
            files = dir.listFiles(filter);
        }
        else
        {
            files = dir.listFiles();
        }

        if (files != null && files.length > 0)
        {
            for (int i = 0; i < files.length; i++)
            {
                File tmpFile = files[i];

                if (!tmpFile.isDirectory())
                {
                    resultList.add(tmpFile);
                }
                else
                {
                    resultList = getFileList(resultList, tmpFile, filter);
                }
            }
        }
        return resultList;
    }
    
    public static String getFileContent(String absolutePath) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        String content = "";
        try
        {
            reader = new BufferedReader(new FileReader(absolutePath));
            while((content = reader.readLine()) != null)
                sb.append(content).append(System.getProperty("line.separator"));
        }
        finally
        {
            if(reader != null)
                reader.close();
        }
        return sb.toString();
    }
    
    private class FileModifiedDate
    {
        private File file;
        private long lastModified;
        private String fileName;

        public FileModifiedDate(File file, long lastModified, String fileName)
        {
            this.file = file;
            this.lastModified = lastModified;
            this.fileName = fileName;
        }

        /**
         * Gets the file.
         * @return file the file.
         */

        public File getFile()
        {
            return file;
        }

        /**
         * Gets the lastModified.
         * @return lastModified the lastModified.
         */
        public long getLastModified()
        {
            return lastModified;
        }

        /**
         * Sets the fileName.
         * @param fileName the fileName to set.
         */
        public String getFileName()
        {
            return fileName;
        }

    };

    public List sortFilesByDate(List originList)
    {
        List fileModifiedList = new ArrayList();
        for (Iterator iter = originList.iterator(); iter.hasNext();)
        {
            File file = (File) iter.next();
            FileModifiedDate fd = new FileModifiedDate(file, file
                    .lastModified(), file.getName());
            fileModifiedList.add(fd);
        }

        Comparator c = new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                FileModifiedDate item1 = (FileModifiedDate) o1;
                FileModifiedDate item2 = (FileModifiedDate) o2;
                return (item2.getLastModified() != item1.getLastModified()) ? ((item2
                        .getLastModified() - item1.getLastModified()) > 0 ? 1
                        : -1)
                        : item1.getFileName().compareTo(item2.getFileName());
            }
        };

        Collections.sort(fileModifiedList, c);
        originList = new ArrayList();
        for (Iterator iter = fileModifiedList.iterator(); iter.hasNext();)
        {
            FileModifiedDate fd = (FileModifiedDate) iter.next();
            originList.add(fd.getFile());
        }
        return originList;
    }
    
    public static boolean isType(String filename, String type)
    {
        if (filename.length() < type.length())
            return false;

        return filename.regionMatches(true, filename.length() - type.length(),
                type, 0, type.length());
    }
    
    public static void saveFileContent(File file, String content)
            throws NestedRuntimeException
    {
        saveFileContent(file, content, null);
    }
    
    public static void saveFileContent(File file, String content, String charset)
            throws NestedRuntimeException
    {
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs())
            throw new IllegalStateException("Can't make dirs for file: " + file);

        Writer writer = null;
        try
        {
            if (TextUtil.isEmpty(charset))
                writer = new BufferedWriter(new FileWriter(file));
            else
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file), charset));

            writer.write(content);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        finally
        {
            IOUtil.close(writer);
        }
    }
    
    public static File saveFile(InputStream is, String path)
    {
        if (is == null || TextUtil.isEmpty(path))
            return null;

        if (!path.endsWith(File.separator))
            path += File.separator;

        File file = new File(path);
        File dir = file.getParentFile();
        if (!dir.exists() && !dir.mkdirs())
        {
            log.error("Can not make dir:" + dir);
            return null;
        }

        try
        {
            doWriteData(is, new FileOutputStream(file));
        }
        catch (IOException ex)
        {
            log.error(ex);
            file = null;
        }

        return file;
    }
    
    public static void copyFiles(File source, File dest, boolean isOverwrite)
            throws IOException
    {
        if (source == null || !source.exists() || !source.canRead())
        {
            log.error("Source directory does not exist or can not be read: "
                    + source == null ? "null" : source.getAbsolutePath()
                    + ". Copy skipped.");
            return;
        }

        File[] files = source.listFiles();
        if (files.length == 0)
        {
            log.info("No sub dirs and files under dir: "
                    + source.getAbsolutePath());
            return;
        }

        if (!dest.exists())
        {
            if (!dest.mkdirs())
            {
                log.error("Can not make dest dir: " + dest.getAbsolutePath()
                        + ". Copy skipped.");
                return;
            }
        }

        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                File dest2 = new File(dest.getAbsolutePath(),
                        files[i].getName());
                copyFiles(files[i], dest2, isOverwrite);
            }
            else
            {
                if (files[i].isHidden())
                {
                    continue;
                }
                copy(files[i],
                        new File(dest.getAbsolutePath(), files[i].getName()),
                        isOverwrite);

            }
        }
    }

}
