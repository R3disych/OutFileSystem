import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileSystem {
    public static void main(String[] args) {
        //hola
        Scanner userInput = new Scanner(System.in);
        System.out.println("Введите путь к директории(файлу):");

        String path = userInput.nextLine();
        File input = new File(path);

        try {
            outFileTree(input, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void outFileTree(File inputPath, int level) throws FileNotFoundException {
        //проверяем полученный в параметры путь на существование
        if(!inputPath.exists()) {
            throw new FileNotFoundException("Файла не сущетсвует");
        }

        //создаем массив вложенных файлов указанного пути и проверяем наличие доступа, если доступ отсутствует - files имеет значение null
        File[] files = inputPath.listFiles();
        if(files == null) {
            throw new FileNotFoundException("Отсутствует доступ к файлу");
        }

        //сортируем массив полученных файлов
        Arrays.sort(files);

        //выводим в консоль
        for(File currentFile : files) {
            if (currentFile.isDirectory()) {
                System.out.printf("%s|----%s %d байт%n", "\t".repeat(level), currentFile.getName(), currentFile.length());
                outFileTree(currentFile, level + 1);
            } else if (currentFile.isFile()){
                System.out.printf("%s|----%s %d байт%n", "\t".repeat(level), currentFile.getName(), currentFile.length());
            } else {
                throw new IllegalStateException("Unexpected entity: %s, expect file or directory".formatted(currentFile.getAbsolutePath()));
            }
        }
    }
}
