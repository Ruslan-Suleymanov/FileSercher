package ru.filesearcher.controller;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by ruslaanko on 26.04.17.
 */
public class Searcher {

    public String search(String fileName) {

        final Path fileForSearch = Paths.get(fileName);

        Path startSearching = Paths.get("/");
        final StringBuilder result = new StringBuilder("Результаты поиска:\n");

        try {
            Files.walkFileTree(startSearching, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file.toAbsolutePath());
                    if (file.toString().contains(fileForSearch.toString())) {
                        result
                                .append(file.toAbsolutePath())
                                .append('\n');
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.toString().equals("Результаты поиска:\n")) {
            result
                    .append("Файл не найден\n")
                    .append("Проверьте правильность ввода имени файла");
        }
        return result.toString();
    }
}

