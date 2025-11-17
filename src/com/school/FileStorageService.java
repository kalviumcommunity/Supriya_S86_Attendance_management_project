package com.school;

import java.io.*;
import java.util.List;

public class FileStorageService {
    public void saveData(List<? extends Storable> items, String filename) {
        if (items == null) {
            System.err.println("No items to save for " + filename);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Storable item : items) {
                writer.println(item.toDataString());
            }
            System.out.println("Saved " + items.size() + " records to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}