package command;

import java.io.File;
import java.util.List;

public class Mkdir extends Command{
    public Mkdir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File newDir = null;
        if (args.isEmpty()) {
            return "Incorrect argument. Use `mkdir <file name>` to create the file";
        }
        File currentDirectory = context.getCurrentDirectory();
        newDir = new File(currentDirectory.getPath() + "/" +args.get(0));
        if (!newDir.exists())
            newDir.mkdirs();
        return newDir.getName();
    }
}
