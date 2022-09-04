package command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Rm extends Command {
    public Rm(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty())
            return "Incorrect argument. Use `rm <dir name>` to remove the dir";
        else {
            File file = context.getCurrentDirectory();
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().equals(args.get(0))) {
                        FileUtils.deleteDirectory(new File(f.getName()));
                        return f.getName() + " was deleted";
                    }
                }
            }
        }
        return null;
    }
}
