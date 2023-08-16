import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        String setupPath = "C:/SuperGame/"; //корневая директория установки

        //массив с директориями, которые требуется создать в корневой директории
        String[] folders = {"src",
                "res",
                "savegames",
                "temp",
                "src/main",
                "src/test",
                "res/drawables",
                "res/vectors",
                "res/icons"};

        String logFile = "temp/temp.txt"; //относительное расположение файл-лога

        //массив с файлами, которые требуется создать при установке
        String[] files = {"src/main/Main.java",
                "src/main/Utils.java",
                logFile};

        StringBuilder log = new StringBuilder(); //строка-лог
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS   "); //для лога будем использовать дату/время в удобном формате

        //создаём директорию установки
        log.append(dateFormat.format(new Date())).append("Создание корневой установочной директории ").append(setupPath)
                .append(" Результат: ").append(new File(setupPath).mkdir()).append("\n");

        //создаём поддиректории
        for (String folder : folders) {
            log.append(dateFormat.format(new Date())).append("Создание директории ").append(setupPath).append(folder)
                    .append(" Результат: ").append(new File(setupPath + folder).mkdir()).append("\n");
        }

        //создаём файлы
        for (String file : files) {
            try {
                log.append(dateFormat.format(new Date())).append("Создание файла ").append(setupPath).append(file)
                        .append(" Результат: ").append(new File(setupPath + file).createNewFile()).append("\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        //блок переноса лога в файл
        try (FileWriter fileWriter = new FileWriter(setupPath + logFile, true)) {
            fileWriter.write(log.toString());
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}