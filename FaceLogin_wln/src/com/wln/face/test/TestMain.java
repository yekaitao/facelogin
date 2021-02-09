package com.wln.face.test;

import java.io.File;

import com.wln.util.FaceUtil;

public class TestMain {
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File file=new File("E:\\表情包\\7.jpg");
		try {
			String str=FaceUtil.detect(file);//人脸的检测
			System.out.println("face_token:"+str);
			//添加一个人脸到集合中
//			boolean addRes=FaceUtil.addFace(str);
//			System.out.println("添加结果："+addRes);
			boolean res=FaceUtil.search(str);//人脸的搜索
			System.out.println("人脸搜索的结果"+res);
//			//获取人脸的集合
//			res=FaceUtil.getDetail();
//			if(!res)//如果没有该人脸集合，就创建一个
//			{
//				res=FaceUtil.createFaceSet();
//				System.out.println("如果没有指定的outer_id为linaFaceSet人脸集合，则创建一个新的！");
//			}
//			System.out.println("outer_id为linaFaceSet人脸集合："+res);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
