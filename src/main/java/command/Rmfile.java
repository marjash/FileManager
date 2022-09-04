package command;

import java.io.File;
import java.util.List;

public class Rmfile extends Command{
    public Rmfile(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty())
            return "Incorrect argument. Use `rm <file name>` to remove the file";
        else {
            File file = context.getCurrentDirectory();
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().equals(args.get(0))) {
                        if (f.isFile()) {
                            f.delete();
                            return f.getName() + " was deleted";
                        } else
                            return "Failed to delete. " + f.getName() + " is not a file";
                    }
                }
            }
        }
        return null;
    }
}
