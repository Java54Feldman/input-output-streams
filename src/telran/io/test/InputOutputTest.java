package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

class InputOutputTest {

	private static final String STREAM_FILE = "stream-file";
	private static final String HELLO = "Hello";
	private static final String WRITER_FILE = "writer-file";
	
	@AfterAll
	static void tearDown() throws IOException {
		Files.deleteIfExists(Path.of(STREAM_FILE));
		Files.deleteIfExists(Path.of(WRITER_FILE));
	}

	@Test
	//Testing PrintStream class
	//creates PrintStream object and connects it to file
	void printStreamTest() throws Exception {
		//try resources block allows automatic closing existing from the buffer
		try (PrintStream printStream = new PrintStream(STREAM_FILE)) {
			printStream.println(HELLO);
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(STREAM_FILE))) {
			assertEquals(HELLO, reader.readLine());
			assertNull(reader.readLine()); // stream закончен
		}
	}
	@Test
	//The same test as for PrintStream but for PrintWriter
	//The difference between PrintStream and PrintWriter is that
	//PrintStream puts line immediately while PrintWriter puts line into a buffer
	//The buffer flushing is happening after closing the stream
	void printWriterTest() throws Exception {
		try (PrintWriter printWriter = new PrintWriter(WRITER_FILE)) {
			printWriter.println(HELLO);
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(WRITER_FILE))) {
			assertEquals(HELLO, reader.readLine());
			assertNull(reader.readLine());
		}
	}
	@Test
	void pathTest() {
		Path pathCurrent = Path.of(".");
//		System.out.printf("%s - path \".\"\n", pathCurrent);
//		System.out.printf("%s - normolized path \".\"\n", pathCurrent.normalize());
//		System.out.printf("%s - normolized absolute path \".\"\n", 
//				pathCurrent.toAbsolutePath().normalize());
		System.out.printf("%s - %s\n", 
				pathCurrent.toAbsolutePath().normalize(), 
				Files.isDirectory(pathCurrent) ? "directory" : "file");
		pathCurrent = pathCurrent.toAbsolutePath().normalize();
		System.out.printf("count of levels is %d\n", pathCurrent.getNameCount());
	}
	@Test
	void printDirectoryTest() throws IOException {
		printDirectory("..", 3);
	}

	private void printDirectory(String dirPathStr, int depth) throws IOException {
		// TODO
		//print directory content in the format with offset according to the level
		//if depth == -1 all levels should be printed out
		//<name> - <dir / file>
		//		<name>
		//using Files.walkFileTree with maxDepth
		Path path = Path.of(dirPathStr).toAbsolutePath().normalize();
		Files.walkFileTree(path, new HashSet<>(), depth, new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		
				
	}

}
