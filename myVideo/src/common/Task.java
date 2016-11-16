package common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import util.FileManager;
import util.FileSystem;
import util.PropertiesConfig;

public class Task extends TimerTask {

	public void run() {
		System.out.println("定时任务执行");
		String filepath = PropertiesConfig.filepath;
		try {
			FileManager.initFileList(PropertiesConfig.fileDB);
			FileSystem.init(filepath);
			FileSystem.readfile(filepath,"/");
			FileManager.save(FileSystem.fileList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Timer t = new Timer();
		t.schedule(new Task(), 1,1000000);
	}
}