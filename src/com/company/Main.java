package com.company;

import java.io.*;

public class Main {
    static StringBuilder log;
    // создать директорию
    static void makeDir(String newdir) {
        File dir = new File(newdir);
        // директории не существует
        if (!dir.exists()) {
            // создание
            if (dir.mkdir())
                log.append("Создана директория ").append(newdir).append("\n");
            else
                log.append("Не удалось создать директорию ").append(newdir).append("\n");
        } else
            log.append("Директория уже существует ").append(newdir).append("\n");
    }
    // создать файл
    static void createFile(String pathName) {
        File newFile = new File(pathName);
        // файл уже существует
        if (newFile.exists()) log.append("Файл уже существует ").append(pathName).append("\n");
        else {
            try {
                // создание
                if (newFile.createNewFile())
                    log.append("Создан файл ").append(pathName).append("\n");
                else
                    log.append("Не удалось создать файл ").append(pathName).append("\n");
            } catch (IOException exp) {
                log.append("Не удалось создать файл ").append(pathName).append("\n").append(exp.getMessage()).append("\n");
            }
        }
    }

    public static void main(String[] args) {
        final String ROOT_DIR = "C://games//";
        String[] dirs = {"src", "res", "savegames", "temp", "src//main", "src//test",
                         "res//drawables", "res//vectors", "res//icons"};
        String[] files = {"src//main//Main.java", "src//main//Util.java", "temp//temp.txt"};
        String wrkDir;
        log = new StringBuilder();
        // создание директорий
        for(int i = 0; i < dirs.length; i++){
            makeDir(ROOT_DIR + dirs[i]);
        }
        // создание файлов
        for(int i = 0; i < files.length; i++){
            createFile(ROOT_DIR + files[i]);
        }
        // ЛОГ
        wrkDir = ROOT_DIR + "//temp";
        File tmp = new File(wrkDir, "temp.txt");
        // файл создан и доступен для записи
        if(tmp.exists() && tmp.canWrite()) {
            // потоковая запись в файл результата
            try(Writer writer = new FileWriter(wrkDir+"//temp.txt")){
                // запись
                writer.write(log.toString());
            } catch(IOException exp) {
                System.out.println("Не удалось записать в файл " + wrkDir + "temp.txt" + "\n" + exp.getMessage());
            }
        } else System.out.println("Не удалось открыть/создать файл " + wrkDir + "//temp.txt для записи");
    }
}
