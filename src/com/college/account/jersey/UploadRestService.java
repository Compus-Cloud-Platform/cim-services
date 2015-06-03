package com.college.account.jersey;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.college.util.ServiceFactoryBean;


@Path("/upload")
public class UploadRestService
{
    @POST
    @Path("uploadfile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(InputStream is, @Context HttpServletRequest request) throws Exception
    {
        /*byte[] buf = new byte[is.available()];
        is.read(buf);
        String content = new String(buf);*/
        
        String location = ServiceFactoryBean.getUploadService().getDestLocation();
        
        ServiceFactoryBean.getUploadService().saveUploadFile(location, "video.txt", is);
        //IOUtil.close(is);
        return "succes";
    }

}
