package com.college.account.jersey;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;


@Path("/upload")
public class UploadRestService
{
    @POST
    @Path("uploadfile")
    @Consumes("application/x-www-form-urlencoded") 
    public String getTest22222(InputStream is, @Context
    HttpServletRequest request) throws Exception
    {
        //is = request.getInputStream();
        byte[] buf = new byte[is.available()];
        is.read(buf);
        System.out.println("buf:" + new String(buf));
        String result;
        result = "--------" + request.getContextPath();
        return result;
    }

}
