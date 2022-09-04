package command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Tree extends Command {
    public Tree(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        StringBuilder result = new StringBuilder();
        if (allFiles != null) {
            for (File f : allFiles) {
                result.append("| +--");
                result.append(f.getName()).append("\n");
                getFilesFromDir(result, f, 1);
            }
        }
        return result.toString();
    }

    private void getFilesFromDir(StringBuilder result, File f, int j) throws IOException {
        if (f.isDirectory()) {
            if (!FileUtils.isEmptyDirectory(f)) {
                File[] files = f.listFiles();
                for (File f2 : files) {
                    int line = line(f2);
                    for (int i = 0; i < line; i++)
                        result.append("| ");
                    result.append("+--");
                    result.append(f2.getName()).append("\n");
                    getFilesFromDir(result, f2, j);
                    j++;

                }
            }
        }
    }

    private int line(File f2) {
        int i = 0;
        Path path = Paths.get(f2.getPath());
        int c = path.toString().indexOf('c');
        String s = path.toString().substring(c);
        for (char ch : s.toCharArray()) {
            if (ch == '\\')
                i++;
        }
        return i;
    }
}
