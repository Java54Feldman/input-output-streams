package telran.io;

import java.io.*;

public class CodeCommentsSeparation {

	public static void main(String[] args) throws IOException {
		// args[0] - file path for file containing both Java class code and comments
		// args[1] - result file with only code
		// args[2] - result file with only comments
		// exapmle of args[0] "src/telran/io/test/InputOutputTest.java"
		// from one file containing code and comments to create two files
		// one with only comments and second with only code

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter codeWriter = new BufferedWriter(new FileWriter(args[1]));
             BufferedWriter commentsWriter = new BufferedWriter(new FileWriter(args[2]))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("//")) {
                    commentsWriter.write(line.trim() + System.lineSeparator());
                } else {
                    codeWriter.write(line + System.lineSeparator());
                }
            }
        }

	}

}
