package com.company;

import java.io.*;

public class Main {
    // создать директорию
    static String makeDir(String newdir) {
        File dir = new File(newdir);
        // директория уже существует
        if (dir.exists()) return "Директория уже существует " + newdir + "\n";
        // создание
        if (dir.mkdir()) return "Создана директория " + newdir + "\n";
        return "Не удалось создать директорию " + newdir + "\n";
    }
    // создать файл
    static String createFile(String pathName) {
        File newFile = new File(pathName);
        // файл уже существует
        if (newFile.exists()) return "Файл уже существует " + pathName + "\n";
        try {
            // создание
            if (newFile.createNewFile())
                return "Создан файл " + pathName + "\n";
        } catch (IOException exp) {
            return "Не удалось создать файл " + pathName + "\n" + exp.getMessage() + "\n";
        }
        return "Не удалось создать файл " + pathName + "\n";
    }

    public static void main(String[] args) {
        final String ROOT_DIR = "C://games//";
        String[] dirs = {"src", "res", "savegames", "temp", "src//main", "src//test",
                         "res//drawables", "res//vectors", "res//icons"};
        String[] files = {"src//main//Main.java", "src//main//Util.java", "temp//temp.txt"};
        String wrkDir;
        StringBuilder strBuilder = new StringBuilder();
        // создание директорий
        for(int i = 0; i < dirs.length; i++){
            strBuilder.append(makeDir(ROOT_DIR + dirs[i]));
        }
        // создание файлов
        for(int i = 0; i < files.length; i++){
            strBuilder.append(createFile(ROOT_DIR + files[i]));
        }
        // ЛОГ
        wrkDir = ROOT_DIR + "//temp";
        File tmp = new File(wrkDir, "temp.txt");
        // файл создан и доступен для записи
        if(tmp.exists() && tmp.canWrite()) {
            // потоковая запись в файл результата
            try(Writer writer = new FileWriter(wrkDir+"//temp.txt")){
                // запись
                writer.write(strBuilder.toString());
            } catch(IOException exp) {
                System.out.println("Не удалось записать в файл " + wrkDir + "temp.txt" + "\n" + exp.getMessage());
            }
        } else System.out.println("Не удалось открыть/создать файл " + wrkDir + "//temp.txt для записи");
    }
}
