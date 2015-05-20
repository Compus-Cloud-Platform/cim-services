package com.college.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface UploadService
{
    public void saveUploadFile(String realPath, String fileName, long fileSize, InputStream stream)
            throws FileNotFoundException, IOException;
}
