package org.graalphp.parser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TestUtils {

    public static final String ECLIPSE_PDT_FATJAR_JAR = "./eclipse-pdt-fatjar.jar";

    // return: path to result file
    public static String runFatJarParser(String code) throws IOException, InterruptedException {
        File file = File.createTempFile("fatjar-input", null);
        File output = File.createTempFile("fatjar-output", null);

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(code);
        bw.close();
        String jar = new File(ECLIPSE_PDT_FATJAR_JAR).getAbsolutePath();
        String exec = String.format("java -jar %s %s &> %s", jar,
                file.getAbsolutePath(), output.getAbsolutePath());
        System.out.println(exec);
        Process proc = Runtime.getRuntime().exec(new String[]{"bash", "-c", exec});
        proc.waitFor();
        System.out.println(proc.exitValue());

        return output.getAbsolutePath();
    }


    public static String readFileInClassPath(String path) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        try {
            return new Scanner(resource.openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            return null;
        }
    }

    public static String readFileToString(String filePath) throws IOException {
        String content = "";
        content = new String(Files.readAllBytes(Paths.get(filePath)));
        return content;
    }

    public static void toFile(File file, String in) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(in.toString());
        } finally {
            if (writer != null) writer.close();
        }
    }

    public static String toString(InputStream in) {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String trim(String input) {
        BufferedReader reader = new BufferedReader(new StringReader(input));
        StringBuffer result = new StringBuffer();
        try {
            String line;
            while ( (line = reader.readLine() ) != null)
                result.append(line.trim());
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
