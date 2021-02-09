package com.wln.face.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wln.util.FaceUtil;
import com.wln.util.ImageUtils;

/**
 * Servlet implementation class FaceController
 */
@WebServlet("/faceController")
public class FaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FaceController() {
        super();
    }
//    1.接受客户端的请求request
//    2.处理请求
//    3.返回数据给客户端response
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//登录步骤：
		//1、获取客户上传的图片一上传到指定的文件夹中upimg
		File file=ImageUtils.uploadImg(request, "imgData", "upimg");
		boolean res=false;
		boolean delFlag=true;
		try {
			//2、判断是否包含人脸信息，detect
			String faceToken=FaceUtil.detect(file);
			if(faceToken!=null)//2.1包含：
			{
				//在faceset中查找是否有相似度高的人脸信息，search
				res=FaceUtil.search(faceToken);//	有，登录成功；删除图片。没有，登录失败，删除照片
				//判定用户请求的类型
				String type=request.getParameter("type");
				if("register".equals(type))//如果是注册
				{
					if(res)//有，已经注册过；删除照片
					{
						res=false;
					}
					else//没有，可以注册，添加到facetoken到faceset中，保留照片
					{
						res=FaceUtil.addFace(faceToken);
						delFlag=false;//不删除照片
					}
				}
			}
			else//2.2不包含，登录失败；删除图片
			{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//统一在这里删除照片
			file.delete();
			//返回数据给客户端 response
			PrintWriter pw=response.getWriter();
			String msg="{\"success\":"+res+"}";
			pw.write(msg);
			pw.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
