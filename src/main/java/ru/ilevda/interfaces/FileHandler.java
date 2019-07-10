package ru.ilevda.interfaces;

import java.io.File;
import java.util.List;

public interface FileHandler {
    List<String[]> read(File file);

    void write(File file, String[] head, List<String[]> list);
}
