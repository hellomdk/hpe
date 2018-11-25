package com.hpe.controller;

import java.io.InputStream;

public class MyThread extends Thread{

	int taskId;
	
	public MyThread(int taskId) {
		super();
		this.taskId=taskId;
	}
	
	@Override
	public void run() {
		executeLinuxCmd("spark-submit --master spark://node01:7077 --class com.hpe.test.WC /opt/software/spark-1.6.3/lib/WC.jar "+taskId+" --driver-class-path /opt/software/spark-1.6.3/lib/mysql-connector-java-5.1.32-bin.jar");
	}
	
	public static String executeLinuxCmd(String cmd) {
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec(cmd);
			InputStream in = process.getInputStream();
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[8192];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			System.out.println("job result [" + out.toString() + "]");
			in.close();
			process.waitFor();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
