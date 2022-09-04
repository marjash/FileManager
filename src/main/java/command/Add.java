package command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Add extends Command{
    public Add(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Incorrect argument. Use `open <file name> <text>` to open the file";
        } else {
            File file = context.getCurrentDirectory();
            File[] allFiles = file.listFiles();
            if (allFiles != null) {
                for (File f : allFiles) {
                    if (f.getName().equals(args.get(0))) {
                        FileUtils.writeStringToFile(
                                f, "\r\n" + args.get(1), StandardCharsets.UTF_8, true);
                        return f.toString();

                    }
                }
            }
        }
        return null;
    }
}
