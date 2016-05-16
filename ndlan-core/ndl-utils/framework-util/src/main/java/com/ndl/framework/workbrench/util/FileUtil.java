package com.ndl.framework.workbrench.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Checksum;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class FileUtil {

	public static String getTempDirectoryPath() {
		return FileUtils.getTempDirectoryPath();
	}

	public static File getTempDirectory() {
		return FileUtils.getTempDirectory();
	}

	public static String getUserDirectoryPath() {
		return FileUtils.getUserDirectoryPath();
	}

	public static File getUserDirectory() {
		return FileUtils.getUserDirectory();
	}

	public static FileInputStream openInputStream(File file) throws IOException {

		return FileUtils.openInputStream(file);
	}

	public static FileOutputStream openOutputStream(File file) throws IOException {
		return FileUtils.openOutputStream(file);
	}

	public static String byteCountToDisplaySize(long size) {
		return FileUtils.byteCountToDisplaySize(size);
	}

	public static void touch(File file) throws IOException {
		FileUtils.touch(file);
	}

	public static File[] convertFileCollectionToFileArray(Collection<File> files) {
		return FileUtils.convertFileCollectionToFileArray(files);
	}

	public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
		return FileUtils.listFiles(directory, fileFilter, dirFilter);
	}

	public static Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
		return FileUtils.iterateFiles(directory, fileFilter, dirFilter);
	}

	public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {
		return FileUtils.listFiles(directory, extensions, recursive);
	}

	public static Iterator<File> iterateFiles(File directory, String[] extensions, boolean recursive) {
		return FileUtils.iterateFiles(directory, extensions, recursive);
	}

	public static boolean contentEquals(File file1, File file2) throws IOException {
		return FileUtils.contentEquals(file1, file2);
	}

	public static File toFile(URL url) {
		return FileUtils.toFile(url);
	}

	public static File[] toFiles(URL[] urls) {
		return FileUtils.toFiles(urls);
	}

	public static URL[] toURLs(File[] files) throws IOException {
		return FileUtils.toURLs(files);
	}

	public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
		FileUtils.copyFileToDirectory(srcFile, destDir);
	}

	public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
		FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
	}

	public static void copyFile(File srcFile, File destFile) throws IOException {
		FileUtils.copyFile(srcFile, destFile);
	}

	public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
		FileUtils.copyFile(srcFile, destFile, preserveFileDate);
	}

	public static void copyDirectoryToDirectory(File srcDir, File destDir) throws IOException {
		FileUtils.copyDirectoryToDirectory(srcDir, destDir);
	}

	public static void copyDirectory(File srcDir, File destDir) throws IOException {
		FileUtils.copyDirectory(srcDir, destDir);
	}

	public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
		FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
	}

	public static void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
		FileUtils.copyDirectory(srcDir, destDir, filter);
	}

	public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate)
			throws IOException {
		FileUtils.copyDirectory(srcDir, destDir, filter, preserveFileDate);
	}

	public static void copyURLToFile(URL source, File destination) throws IOException {
		FileUtils.copyURLToFile(source, destination);
	}

	public static void copyURLToFile(URL source, File destination, int connectionTimeout, int readTimeout)
			throws IOException {
		FileUtils.copyURLToFile(source, destination, connectionTimeout, readTimeout);
	}

	public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
		FileUtils.copyInputStreamToFile(source, destination);
	}

	public static void deleteDirectory(File directory) throws IOException {
		FileUtils.deleteDirectory(directory);
	}

	public static boolean deleteQuietly(File file) {
		return FileUtils.deleteQuietly(file);
	}

	public static void cleanDirectory(File directory) throws IOException {
		FileUtils.cleanDirectory(directory);
	}

	public static boolean waitFor(File file, int seconds) {
		return FileUtils.waitFor(file, seconds);
	}

	public static String readFileToString(File file, String encoding) throws IOException {
		return FileUtils.readFileToString(file, encoding);
	}

	public static String readFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}

	public static byte[] readFileToByteArray(File file) throws IOException {
		return FileUtils.readFileToByteArray(file);
	}

	public static List<String> readLines(File file, String encoding) throws IOException {
		return FileUtils.readLines(file, encoding);
	}

	public static List<String> readLines(File file) throws IOException {
		return FileUtils.readLines(file);
	}

	public static void writeStringToFile(File file, String data, String encoding) throws IOException {
		FileUtils.writeStringToFile(file, data, encoding);
	}

	public static void writeStringToFile(File file, String data) throws IOException {
		FileUtils.writeStringToFile(file, data);
	}

	public static void write(File file, CharSequence data) throws IOException {
		FileUtils.write(file, data);
	}

	public static void write(File file, CharSequence data, String encoding) throws IOException {
		FileUtils.write(file, data, encoding);
	}

	public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
		FileUtils.writeByteArrayToFile(file, data);
	}

	public static void writeLines(File file, String encoding, Collection<?> lines) throws IOException {
		FileUtils.writeLines(file, encoding, lines);
	}

	public static void writeLines(File file, Collection<?> lines) throws IOException {
		FileUtils.writeLines(file, lines);
	}

	public static void writeLines(File file, String encoding, Collection<?> lines, String lineEnding)
			throws IOException {
		FileUtils.writeLines(file, encoding, lines);
	}

	public static void writeLines(File file, Collection<?> lines, String lineEnding) throws IOException {
		FileUtils.writeLines(file, lines, lineEnding);
	}

	public static void forceDelete(File file) throws IOException {
		FileUtils.forceDelete(file);
	}

	public static void forceDeleteOnExit(File file) throws IOException {
		FileUtils.forceDeleteOnExit(file);
	}

	public static void forceMkdir(File directory) throws IOException {
		FileUtils.forceMkdir(directory);
	}

	public static long sizeOf(File file) {
		return FileUtils.sizeOf(file);

	}

	public static long sizeOfDirectory(File directory) {
		return FileUtils.sizeOfDirectory(directory);
	}

	public static boolean isFileNewer(File file, File reference) {
		return FileUtils.isFileNewer(file, reference);
	}

	public static boolean isFileNewer(File file, Date date) {
		return FileUtils.isFileNewer(file, date);
	}

	public static boolean isFileNewer(File file, long timeMillis) {
		return FileUtils.isFileNewer(file, timeMillis);
	}

	public static boolean isFileOlder(File file, File reference) {
		return FileUtils.isFileOlder(file, reference);
	}

	public static boolean isFileOlder(File file, Date date) {
		return FileUtils.isFileOlder(file, date);
	}

	public static boolean isFileOlder(File file, long timeMillis) {
		return FileUtils.isFileOlder(file, timeMillis);
	}

	public static long checksumCRC32(File file) throws IOException {
		return FileUtils.checksumCRC32(file);
	}

	public static Checksum checksum(File file, Checksum checksum) throws IOException {
		return FileUtils.checksum(file, checksum);
	}

	public static void moveDirectory(File srcDir, File destDir) throws IOException {
		FileUtils.moveDirectory(srcDir, destDir);
	}

	public static void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
		FileUtils.moveDirectoryToDirectory(src, destDir, createDestDir);

	}

	public static void moveFile(File srcFile, File destFile) throws IOException {
		FileUtils.moveFile(srcFile, destFile);
	}

	public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws IOException {
		FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
	}

	public static void moveToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
		FileUtils.moveToDirectory(src, destDir, createDestDir);
	}

	public static boolean isSymlink(File file) throws IOException {
		return FileUtils.isSymlink(file);
	}

}
