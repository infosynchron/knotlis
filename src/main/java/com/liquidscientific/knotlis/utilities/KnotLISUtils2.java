package com.liquidscientific.knotlis.utilities;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

public class KnotLISUtils2 {
	public static String convertUSToMySQLFormat(String usDate) {
		String mysqlDate = "";
		java.text.SimpleDateFormat usSDF = new java.text.SimpleDateFormat(
				"MM-dd-yyyy");
		java.text.SimpleDateFormat mysqlSDF = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		try {

			java.util.Date dd = usSDF.parse(usDate);
			mysqlDate = mysqlSDF.format(dd);

		} catch (ParseException e) {

		}

		return mysqlDate;
	}

	public static String convertMySQLToUSFormat(String mysqlDate) {
		String usDate = "";
		java.text.SimpleDateFormat usSDF = new java.text.SimpleDateFormat(
				"MM-dd-yyyy");
		java.text.SimpleDateFormat mysqlSDF = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		try {
			if (mysqlDate == null) {
				return usDate;
			} else {
				java.util.Date dd = mysqlSDF.parse(mysqlDate);
				usDate = usSDF.format(dd);
			}

		} catch (ParseException e) {

		}

		return usDate;
	}

	public static Integer getCurrentMonth() {

		Calendar calendar = Calendar.getInstance();
		Integer monthint = calendar.get(Calendar.MONTH) + 1;
		return monthint;

	}

	public static Integer getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);

	}

	public static Integer getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);

	}

	public static String getTimeStamp() {
		Calendar calendar = Calendar.getInstance();
		Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
		Integer minute = calendar.get(Calendar.MINUTE);
		Integer second = calendar.get(Calendar.SECOND);
		Integer milli = calendar.get(Calendar.MILLISECOND);
		return hour.toString() + "_" + minute.toString() + "_"
				+ second.toString() + milli;

	}

	public static String getNewUSDate() {
		String usDate = "";
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MM-dd-yyyy");
		usDate = sdf.format(dd);

		return usDate;
	}

	public static long getLongFromUSDate(String usDate) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"MM-dd-yyyy");
			java.util.Date dd1 = sdf.parse(usDate);
			return dd1.getTime();
		} catch (Exception e) {
			return 0L;
		}
	}

	public static String getUSDateFromLong(long usDate) {
		return "";
	}

	public static String getFileIndex(String mrn, String clientid) {
		String fileIndex = "KL" + "_" + clientid + "_" + mrn + "_"
				+ getCurrentYear() + "_" + getCurrentMonth() + "_"
				+ getCurrentDay() + "_" + getTimeStamp();
		return fileIndex;
	}

	public static String getFileExtension(String originalfilename) {
		String extension = originalfilename.substring(
				originalfilename.lastIndexOf("."), originalfilename.length());
		return extension;
	}

	public static void copyFileUsingApacheCommonsIO(File source, File dest)

	throws IOException {

		FileUtils.copyFile(source, dest);

	}
}
