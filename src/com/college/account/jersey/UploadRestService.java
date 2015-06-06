package com.college.account.jersey;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.college.util.ServiceFactoryBean;


@Path("/upload")
public class UploadRestService
{
    @POST
    @Path("uploadfile")
    @Consumes("application/x-www-form-urlencoded")
    public String uploadFile(InputStream is, @Context HttpServletRequest request) throws Exception
    {
        /*byte[] buf = new byte[is.available()];
        is.read(buf);
        String content = new String(buf);*/
        String fileName = "video.mp4";
        String loginId = "luli.dai";
        
        String location = ServiceFactoryBean.getUploadService().getDestLocation();
        location = location.endsWith(File.separator) ? location.concat(loginId) : location.concat(File.separator).concat(loginId);
        
        ServiceFactoryBean.getUploadService().saveUploadFile(location, fileName, is);
        //FileUtil.saveFile(is, location.endsWith(File.separator) ? location : location.concat(File.separator) + "temtest.txt");
        //IOUtil.close(is);
        return "succes";
    }

}
