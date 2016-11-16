package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.FileObject;

public class FileManager {
	private static File DB = new File(PropertiesConfig.fileDB);
	public static ArrayList<FileObject> fileList = new ArrayList<FileObject>();

	@SuppressWarnings("unchecked")
	public static void initFileList(String fileName) {
		DB = new File(fileName);
		if (DB.exists()) {
			FileInputStream fi = null;
			ObjectInputStream oi = null;
			try {
				fi = new FileInputStream(DB);
				oi = new ObjectInputStream(fi);
				Object obj = oi.readObject();
				fi.close();
				oi.close();
				if (obj instanceof ArrayList)
					fileList = (ArrayList<FileObject>) obj;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(fileList == null){
			fileList = new ArrayList<FileObject>(); 
		}
	}

	/**
	 * @param name文件名
	 * @param path文件夹
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<FileObject> search(String name, String path) {
		ArrayList<FileObject> rlt = new ArrayList<FileObject>();
		DB = new File(PropertiesConfig.fileDB);
		if (DB.exists()) {
			FileInputStream fi = null;
			ObjectInputStream oi = null;
			try {
				fi = new FileInputStream(DB);
				oi = new ObjectInputStream(fi);
				Object obj = oi.readObject();
				fi.close();
				oi.close();
				if (obj instanceof ArrayList)
					fileList = (ArrayList<FileObject>) obj;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (fileList != null) {
			if (StringUtil.isEmptyStr(name) && StringUtil.isEmptyStr(path)) {
				for (FileObject f : fileList) {
					if (f.getFileName().contains(name) && f.getDirect().equals(path)) {
						rlt.add(f);
					}
				}
			} else if (StringUtil.isEmptyStr(name) && !StringUtil.isEmptyStr(path)) {
				for (FileObject f : fileList) {
					if (f.getFileName().contains(name)) {
						rlt.add(f);
					}
				}
			} else if (!StringUtil.isEmptyStr(name) && StringUtil.isEmptyStr(path)) {
				for (FileObject f : fileList) {
					if (f.getDirect().equals(path)) {
						rlt.add(f);
					}
				}
			} else {
				rlt = (ArrayList<FileObject>) fileList;
			}
		}
		return rlt;
	}

	public static void save(ArrayList<FileObject> list) {
		if (list == null || list.size() <= 0)
			return;
		if (!DB.exists()) {
			try {
				DB.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fo;
		try {
			fo = new FileOutputStream(DB);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(list);
			fo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File getDB() {
		return DB;
	}

	public static void setDB(File dB) {
		DB = dB;
	}
}
