package ru.ilevda.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import ru.ilevda.interfaces.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@ConfigurationProperties(prefix = "csvhandler")
public class CSVHandler implements FileHandler {

    private char separator;
    private char quotechar;
    private int line;

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public void setQuotechar(char quotechar) {
        this.quotechar = quotechar;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public List<String[]> read(File file) {

        List<String[]> listString = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(file), separator, quotechar, line)) {

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                listString.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listString;
    }

    public void write(File file, String[] head, List<String[]> list) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeNext(head);
            for (String[] array : list) {

                writer.writeNext(array);
            }
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}
