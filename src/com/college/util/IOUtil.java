package com.college.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IOUtil
{
    public static void close(Object obj)
    {
        if (obj == null)
            return;
        
        try
        {
            if (obj instanceof Reader)
                ((Reader) obj).close();
            else if (obj instanceof Writer)
                ((Writer) obj).close();
            else if (obj instanceof InputStream)
                ((InputStream) obj).close();
            else if (obj instanceof OutputStream)
                ((OutputStream) obj).close();
        }
        catch (IOException e)
        {
        }
    }
}
