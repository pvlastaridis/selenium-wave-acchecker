package uth.elke.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) {

       File file = new File("input.txt");

        try {
            Files.lines(file.toPath())
                    .forEach(line -> {
                        Functions.getACchecker(line);
                        Functions.getWaveWebAIM(line);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}