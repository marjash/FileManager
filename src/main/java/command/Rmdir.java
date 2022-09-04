package command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Rmdir extends Command {
    public Rmdir(Context context) {
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
                    if (f.isDirectory()) {
                        if (args.size() == 1 && f.getName().equals(args.get(0))) {
                            if (FileUtils.isEmptyDirectory(f)) {
                                FileUtils.deleteDirectory(new File(f.getName()));
                                return f.getName() + " was deleted";
                            } else
                                return "failed to remove " + f.getName() + ": Directory not empty";
                        }
                        if (args.size() == 2) {
                            if (f.getName().equals(args.get(1))) {
                                if (args.get(0).equals("-r")) {
                                    FileUtils.deleteDirectory(new File(f.getName()));
                                    return f.getName() + " was deleted";
                                } else
                                    return "failed to remove " + f.getName() + ": Incorrect options";
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}