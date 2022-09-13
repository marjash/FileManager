package command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Tree extends Command {

    public Tree(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        int depth = 0;
        int arg = 10;
        if (!args.isEmpty())
            arg = Integer.parseInt(args.get(0));
        File file = context.getCurrentDirectory();
        Path path = file.toPath();
        return getFilesFromDir(path, depth, arg);
    }

    @SneakyThrows
    private String getFilesFromDir(Path path, int depth, int args) {
        StringBuilder result = new StringBuilder();
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        if (attributes.isDirectory()) {
            result.append(print(depth)).append("├── ").append(path.getFileName()).append("\n");
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            for (Path p : paths) {
                if (args == 0)
                    return result.toString();
                result.append(getFilesFromDir(p, depth + 1, args - 1));
            }
        }
        else
            result.append(print(depth)).append("└── ").append(path.getFileName()).append("\n");
        return result.toString();
    }

    private String print(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("│  ");
        }
        return builder.toString();
    }
}
