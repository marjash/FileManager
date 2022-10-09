package command;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Opens and reads file
 */
public class Open extends Command {
    public Open(Context context) {
        super(context);
    }

    /**
     *
     * @param args The name of the file to be opened
     * @return The contents of the file, if it exists
     */
    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        String everything = "";
        if (args.isEmpty())
            return "Incorrect argument. Use `open <file name>` to open the file";
        else {
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

