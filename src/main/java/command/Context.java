package command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Map;

/**
 * Contains commands and current directory
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Context {

    private Map<String, Command> commandMap;
    private File currentDirectory;

}
