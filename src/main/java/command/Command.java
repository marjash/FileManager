package command;

import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Accepts arguments and executes commands
 */
@RequiredArgsConstructor
public abstract class Command {

    protected final Context context;
    public abstract String execute(List<String> args);
    }
