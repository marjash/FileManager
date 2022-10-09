package command;

import java.io.File;
import java.util.List;

/**
 * Remove file
 */
public class Rmfile extends Command{
    public Rmfile(Context context) {
        super(context);
    }

    /**
     * Removes file if it exists
     * @param args The name of the file to be deleted
     * @return Removes file and displays a message that the file has been deleted
     * if it existed or failed it wasn't
     */
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
