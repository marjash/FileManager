package command;

import java.util.List;
import java.util.Map;

/**
 * Shows all available commands
 */
public class Help extends Command {

    public Help(Context context) {
        super(context);
    }

    /**
     *
     * @param args Help command
     * @return All available commands
     */
    @Override
    public String execute(List<String> args) {
        Map<String, Command> commands = context.getCommandMap();
        StringBuilder result = new StringBuilder("Available commands:\n");
        if (commands != null) {
            for (String each: commands.keySet()){
                result.append(each).append("\n");
            }
        }
        return result.toString();
    }
}
