package com.github.dibyaranjan.file.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;

public class FileHandler {
    private Filer filer;
    private List<String> lines = new ArrayList<>();
    private String fileName;

    public void setFiler(Filer filer) {
        this.filer = filer;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void test() {
        try {
            JavaFileObject javaFileObject = filer.createSourceFile("test");
            try (PrintWriter writer = new PrintWriter(javaFileObject.openWriter())) {
                lines.forEach(line -> writer.println(line));
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
