import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CSVWriter {
    private static final String SAMPLE_CSV_FILE = "students.csv";

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Numele", "Universitatea", "Profesia"));
        ) {
            csvPrinter.printRecord("01", "Andronic Roman", "USM", "student");
            csvPrinter.printRecord("02", "Barbos Oleg", "USM", "student");
            csvPrinter.printRecord("03", "Diminet Ion", "USM", "student");
            csvPrinter.printRecord("04", "Fundureanu Adrian", "USM", "student");
            csvPrinter.printRecord(Arrays.asList("05", "Spinu Dan", "USM", "student"));

            csvPrinter.flush();
        }
    }
}
