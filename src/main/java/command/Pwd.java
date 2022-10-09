package command;

import java.util.List;

/**
 * Print working directory
 */
public class Pwd extends Command {

    public Pwd(Context context) {
        super(context);
    }

    /**
     *
     * @param args No arguments needed
     * @return Returns the path of the current directory
     */
    @Override
    public String execute(List<String> args) {
        return context.getCurrentDirectory().getAbsolutePath();
    }
}
