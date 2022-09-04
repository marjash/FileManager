package command;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Open extends Command {
    public Open(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        String everything = "";
        if (args.isEmpty()) {
            return "Incorrect argument. Use `open <file name>` to open the file";
        } else {
            File file = context.getCurrentDirectory();
            File[] allFiles = file.listFiles();
            if (allFiles != null) {
                for (File f : allFiles) {
                    if (f.getName().equals(args.get(0))) {
                        FileInputStream inputStream = new FileInputStream(args.get(0));
                        everything = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    }
                }
                }
            }
        return everything;
    }
}

