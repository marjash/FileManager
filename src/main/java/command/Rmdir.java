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
                        if (args.size() == 1 && f.getName().equals(args.get(0)))
                            return removeEmptyDir(f);
                        if (args.size() == 2 && f.getName().equals(args.get(1)))
                            return RemoveDirWithFiles(args, f);
                    }
                }
            }
        }
        return "failed to remove. Is not a directory";
    }


    @SneakyThrows
    private String removeEmptyDir(File f) {
        if (FileUtils.isEmptyDirectory(f)) {
            FileUtils.deleteDirectory(new File(f.getName()));
            return f.getName() + " was deleted";
        }
        return "failed to remove " + f.getName() + ": Directory not empty";
    }

    @SneakyThrows
    private String RemoveDirWithFiles(List<String> args, File f){
        if (args.get(0).equals("-r")) {
            FileUtils.deleteDirectory(new File(f.getName()));
            return f.getName() + " was deleted";
        }
        return "failed to remove " + f.getName() + ": Incorrect options";
    }
}