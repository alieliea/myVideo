package com.mytest.action.system;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.jfinal.core.Controller;
import com.mytest.common.StaticObject;

public class UploadFiles extends Controller{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void upload(){
		String url = "";
		JSONObject result = new JSONObject();
		long fileSize = 0;
		
		HashMap<String, String> extMap = StaticObject.extMap;
		String savePath = StaticObject.savePath;
		String saveUrl = StaticObject.saveUrl;
		String fileName = "";
		long maxSize = StaticObject.maxSize;
		
		HttpServletResponse response = getResponse();
		response.setContentType("text/html; charset=UTF-8");
		HttpServletRequest request = getRequest();
		
		if(!ServletFileUpload.isMultipartContent(request)){
			renderJson(getError("请选择文件。!!"));
			return;
		}
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			renderJson(getError("上传目录不存在。"));
			return;
		}
		String dirName = getPara("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			renderJson(getError("目录名不正确。"));
			return;
		}
					//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String ymd = sdf.format(new Date());
//		savePath += ymd + "/";
//		saveUrl += ymd + "/";
//		File dirFile = new File(savePath);
//		if (!dirFile.exists()) {
//			dirFile.mkdirs();
//		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items;
		try {
			items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				fileName = item.getName();
				fileSize = item.getSize();
				if (!item.isFormField()) {
					//检查文件大小
					if(item.getSize() > maxSize){
						renderJson(getError("上传文件大小超过限制。"));
						return;
					}
						//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
						renderJson(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
						return;
					}

						SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
						String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
						try{
							File uploadedFile = new File(savePath, newFileName);
							item.write(uploadedFile);
						}catch(Exception e){
							e.printStackTrace();
							renderJson(getError("上传文件失败。"));
							return;
						}
						url = saveUrl + newFileName;
					}
				}
		} catch (FileUploadException e) {
			e.printStackTrace();
			renderJson(getError("上传文件失败。"));
			return;
		}
		result.put("error", 0);
		result.put("url", url);
		result.put("fileSize", fileSize);
		result.put("fileName", fileName);
		renderJson(result.toJSONString());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void fileManager(){
		String rootPath = StaticObject.savePath;
		String rootUrl  = StaticObject.saveUrl;
		JSONObject result = new JSONObject();
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
		
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		
		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				renderJson(getError("Invalid Directory name."));
				return;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			renderJson(getError("Access is not allowed."));
			return;
		}
		if (!"".equals(path) && !path.endsWith("/")) {
			//最后一个字符不是/
			renderJson(getError("Parameter is not valid."));
			return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			renderJson(getError("Directory does not exist."));
			return;
		}

		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		result.put("status", 0);

		response.setContentType("application/json; charset=UTF-8");
		renderJson(result);
	}

	@SuppressWarnings("rawtypes")
	private class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}
	@SuppressWarnings("rawtypes")
	private class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	@SuppressWarnings("rawtypes")
	private class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private String getError(String msg) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", msg);
		return obj.toJSONString();
	}
}