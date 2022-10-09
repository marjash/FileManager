package command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

/**
 * Creates a file with the specified name
 */
public class Mkfile extends Command{
    public Mkfile(Context context) {
        super(context);
    }

    /**
     *
     * @param args The name of the file to be created
     * @return Creates file and returns its name
     */
    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Incorrect argument. Use `mkfile <file name>` to create the file";
        }
        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory.getPath() + "/" +args.get(0));
        file.createNewFile();
        return file.getName();
    }
}
