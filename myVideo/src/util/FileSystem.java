package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import entity.FileObject;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;

public class FileSystem {

	public static ArrayList<FileObject> fileList = new ArrayList<FileObject>();
	public static String filePath = "";

	public static void init(String filepath) {
		fileList = new ArrayList<FileObject>();
		filePath = filepath;
	}

	public static boolean readfile(String filepath, String father) throws FileNotFoundException, IOException {
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());
			} else if (file.isDirectory()) {
				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "/" + filelist[i]);
					if (!readfile.isDirectory()) {
						long lastModified = FileManager.search(filelist[i], filepath.replace(filePath, "")).get(0).getLastModified();
						if(readfile.lastModified() > lastModified){
							FileObject item = new FileObject();
							item.setFileName(readfile.getName());
							item.setFilePath(readfile.getPath().replace(filePath, ""));
							item.setDirect(father);
							Encoder encoder = new Encoder();
							long time;
							try {
								time = encoder.getInfo(readfile).getDuration() / 1000;
								item.setTimes(getTimes(time));
								item.setType("视频");
							} catch (InputFormatException e) {
	//							e.printStackTrace();
								item.setTimes("");
								item.setType("文件");
							} catch (EncoderException e) {
	//							e.printStackTrace();
								item.setTimes("");
								item.setType("文件");
							}
							long length = readfile.length();
							item.setSize(getSize(length));
							item.setIsFile(true);
							item.setLastModified(readfile.lastModified());
							fileList.add(item);
						}
					} else if (readfile.isDirectory()) {
						long lastModified = FileManager.search(filelist[i], filepath.replace(filePath, "")).get(0).getLastModified();
						if(readfile.lastModified() > lastModified){
							FileObject direct = new FileObject();
							direct.setFileName(filelist[i]);
							direct.setFilePath(filepath.replace(filePath, ""));
							direct.setDirect(father);
							direct.setIsFile(false);
							direct.setTimes("");
							direct.setSize("");
							direct.setType("文件夹");
							direct.setLastModified(readfile.lastModified());
							fileList.add(direct);
							readfile(filepath + "/" + filelist[i],
									(father.equals("/") ? "/" : (father + "/")) + filelist[i]);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	private static String getTimes(long time) {
		String times = "";
		int hour = (int) (time / 3600);
		int minute = (int) (time % 3600 / 60);
		int second = (int) (time % 3600 % 60 % 60);
		times = (hour > 10 ? ("" + hour) : ("0" + hour)) + ":" + (minute > 10 ? ("" + minute) : ("0" + minute)) + ":"
				+ (second > 10 ? ("" + second) : ("0" + second));
		return times;
	}

	private static String getSize(long length) {
		long tb = (long) 1024 * 1024 * 1024 * 1024;
		long gb = 1024 * 1024 * 1024;
		long mb = 1024 * 1024;
		long kb = 1024;
		String size = length > tb ? ((length / tb) + "." + (length % tb / gb) + "TB")
				: (length > gb ? ((length / gb) + "." + (length % gb / mb) + "GB")
						: (length > mb ? ((length / mb) + "." + (length % mb / kb) + "MB")
								: ((length / kb) + "." + (length % kb) + "KB")));
		return size;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ArrayList<FileObject> list = new ArrayList<FileObject>();
		list = FileManager.search("", "/疑犯追踪第二季/新建文件夹/新建文件夹");
		if (list != null) {
			System.out.println(list.size());
			Iterator<FileObject> it = list.iterator();
			while (it.hasNext()) {
				FileObject fo = it.next();
				System.out.println("文件名：" + fo.getFileName() + "---路径：" + fo.getFilePath());
			}
		}
	}
}