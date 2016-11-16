package common;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitData implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// web停止时执行
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer = new Timer();
		//启动后延迟1分钟启动程序，并1小时循环运行
		timer.schedule(new Task(), 60 * 1000 , 60 * 1000 * 60);
	}
}