import command.Command;
import command.Context;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        Context context = new Context(null, new File(s));
        CreateCommand command = new CreateCommand();
        command.setCommands(context);
    }
}