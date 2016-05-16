package com.ndl.framework.workbrench.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

public class IOUtil {
	public static void closeQuietly(Reader input) {
		IOUtils.closeQuietly(input);
	}

	public static void closeQuietly(Writer output) {
		IOUtils.closeQuietly(output);
	}

	public static void closeQuietly(InputStream input) {
		IOUtils.closeQuietly(input);
	}

	public static void closeQuietly(OutputStream output) {
		IOUtils.closeQuietly(output);
	}

	public static void closeQuietly(Closeable closeable) {
		IOUtils.closeQuietly(closeable);
	}

	public static void closeQuietly(Socket sock) {
		IOUtils.closeQuietly(sock);

	}

	public static InputStream toBufferedInputStream(InputStream input) throws IOException {
		return IOUtils.toBufferedInputStream(input);
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		return IOUtils.toByteArray(input);
	}

	public static byte[] toByteArray(Reader input) throws IOException {
		return IOUtils.toByteArray(input);
	}

	public static byte[] toByteArray(Reader input, String encoding) throws IOException {

		return IOUtils.toByteArray(input, encoding);
	}

	public static char[] toCharArray(InputStream is) throws IOException {
		return IOUtils.toCharArray(is);
	}

	public static char[] toCharArray(InputStream is, String encoding) throws IOException {
		return IOUtils.toCharArray(is, encoding);
	}

	public static char[] toCharArray(Reader input) throws IOException {
		return IOUtils.toCharArray(input);
	}

	public static String toString(InputStream input) throws IOException {
		return IOUtils.toString(input);
	}

	public static String toString(InputStream input, String encoding) throws IOException {
		return IOUtils.toString(input, encoding);
	}

	public static String toString(Reader input) throws IOException {
		return IOUtils.toString(input);
	}

	public static List<String> readLines(InputStream input) throws IOException {
		return IOUtils.readLines(input);
	}

	public static List<String> readLines(InputStream input, String encoding) throws IOException {
		return IOUtils.readLines(input, encoding);
	}

	public static List<String> readLines(Reader input) throws IOException {
		return IOUtils.readLines(input);
	}

	public static LineIterator lineIterator(Reader reader) {
		return IOUtils.lineIterator(reader);
	}

	public static LineIterator lineIterator(InputStream input, String encoding) throws IOException {
		return IOUtils.lineIterator(input, encoding);
	}

	public static InputStream toInputStream(CharSequence input) {
		return IOUtils.toInputStream(input);
	}

	/**
	 * Convert the specified CharSequence to an input stream, encoded as bytes
	 * using the specified character encoding.
	 * <p>
	 * Character encoding names can be found at
	 * <a href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 *
	 * @param input
	 *            the CharSequence to convert
	 * @param encoding
	 *            the encoding to use, null means platform default
	 * @throws IOException
	 *             if the encoding is invalid
	 * @return an input stream
	 * @since Commons IO 2.0
	 */
	public static InputStream toInputStream(CharSequence input, String encoding) throws IOException {
		return IOUtils.toInputStream(input, encoding);
	}

	// -----------------------------------------------------------------------
	/**
	 * Convert the specified string to an input stream, encoded as bytes using
	 * the default character encoding of the platform.
	 *
	 * @param input
	 *            the string to convert
	 * @return an input stream
	 * @since Commons IO 1.1
	 */
	public static InputStream toInputStream(String input) {
		return IOUtils.toInputStream(input);
	}

	public static InputStream toInputStream(String input, String encoding) throws IOException {
		return IOUtils.toInputStream(input, encoding);
	}

	public static void write(byte[] data, OutputStream output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(byte[] data, Writer output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(byte[] data, Writer output, String encoding) throws IOException {
		IOUtils.write(data, output, encoding);
	}

	public static void write(char[] data, Writer output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(char[] data, OutputStream output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(char[] data, OutputStream output, String encoding) throws IOException {
		IOUtils.write(data, output, encoding);
	}

	public static void write(CharSequence data, Writer output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(CharSequence data, OutputStream output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(CharSequence data, OutputStream output, String encoding) throws IOException {
		IOUtils.write(data, output, encoding);
	}

	public static void write(String data, Writer output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(String data, OutputStream output) throws IOException {
		IOUtils.write(data, output);
	}

	public static void write(String data, OutputStream output, String encoding) throws IOException {
		IOUtils.write(data, output, encoding);
	}

	public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) throws IOException {
		IOUtils.writeLines(lines, lineEnding, output);
	}

	public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String encoding)
			throws IOException {
		IOUtils.writeLines(lines, lineEnding, output, encoding);
	}

	public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
		IOUtils.writeLines(lines, lineEnding, writer);
	}

	public static int copy(InputStream input, OutputStream output) throws IOException {
		return IOUtils.copy(input, output);
	}

	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		return IOUtils.copyLarge(input, output);
	}

	public static void copy(InputStream input, Writer output) throws IOException {
		IOUtils.copy(input, output);
	}

	public static void copy(InputStream input, Writer output, String encoding) throws IOException {
		IOUtils.copy(input, output, encoding);
	}

	public static int copy(Reader input, Writer output) throws IOException {
		return IOUtils.copy(input, output);
	}

	public static long copyLarge(Reader input, Writer output) throws IOException {
		return IOUtils.copyLarge(input, output);
	}

	public static void copy(Reader input, OutputStream output) throws IOException {
		IOUtils.copy(input, output);
	}

	public static void copy(Reader input, OutputStream output, String encoding) throws IOException {
		IOUtils.copy(input, output, encoding);
	}

	public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
		return IOUtils.contentEquals(input1, input2);
	}

	public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
		return IOUtils.contentEquals(input1, input2);
	}

	public static long skip(InputStream input, long toSkip) throws IOException {
		return IOUtils.skip(input, toSkip);
	}

	public static long skip(Reader input, long toSkip) throws IOException {
		return IOUtils.skip(input, toSkip);
	}

	public static void skipFully(InputStream input, long toSkip) throws IOException {
		IOUtils.skipFully(input, toSkip);
	}

	public static void skipFully(Reader input, long toSkip) throws IOException {
		IOUtils.skipFully(input, toSkip);
	}

}