package command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows all files in current directory with some info
 */
public class Ls extends Command {

    public Ls(Context context) {
        super(context);
    }

    /**
     * Takes the attributes and shows in the table all the
     * files from the directory with the necessary information
     * @param args -p - permission -s - size
     * @return Shows size or/and permissions of files
     */
    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        StringBuilder result = new StringBuilder();
        table(header(args), result);
        if (allFiles != null) {
            for (File each : allFiles) {
                result.append(name(each));
                if (args.isEmpty())
                    result.append("\n");
                else {
                    if (args.get(0).length() == 2 && args.get(0).contains("-s")) {
                        result.append(size(each));
                        result.append("\n");
                    }
                    else if (args.get(0).length() == 2 && args.get(0).contains("-p")) {
                        result.append(permission(each));
                        result.append("\n");
                    }
                    else if (args.get(0).length() == 4 && args.get(0).contains("-p") && args.get(0).contains("-s")) {
                        result.append(size(each));
                        result.append(permission(each));
                        result.append("\n");
                    }
                    else
                        return "Unknown flag. Please use -s/-p";
                }
            }
        }
        return result.toString();
    }

    /**
     * Shows names of files
     * @param each Files that will be displayed
     * @return Names of all files
     */
    private String name(File each) {
        return String.format("%10s", each.getName());
    }

    /**
     * Shows size of files
     * @param each Files that will be displayed
     * @return Size of all files
     */
    @SneakyThrows
    private String size(File each){
        Path path = Paths.get(each.getPath());
        long size = Files.size(path);
        return String.format("%20d bytes", size);
    }

    /**
     * Shows permission of files
     * @param file Files that will be displayed
     * @return Permission of all files
     */
    private String permission(File file) {
        String r = "-";
        String w = "-";
        String x = "-";
        if (file.canRead())
            r = "r";
        if (file.canWrite())
            w = "w";
        if (file.canExecute())
            x = "x";
        return String.format("%20s %s %s", r, w, x);
    }

    /**
     * Create table
     * @param strings Table header
     * @param result table
     */
    private void table(List<String> strings, StringBuilder result) {
        String res = "";
        String res2 = "";
        String res3 = "";
        for (String s : strings) {
            int length = s.length() + 17;
            res += String.format("%" + length + "s", "").replace(" ", "*");
            res2 += String.format("%10s %10s %5s", s, "|", "");
            res3 += String.format("%" + length + "s", "").replace(" ", "*");
        }
        result.append(res);
        result.append("\n");
        result.append(res2);
        result.append("\n");
        result.append(res3);
        result.append("\n");
    }

    /**
     * Creates header for table
     * @param args Takes the attributes and creates a header accordingly
     * @return Header
     */
    private List<String> header(List<String> args) {
        List<String> list = new ArrayList<>();
        list.add("name");
        if (!args.isEmpty()) {
            if (args.get(0).contains("-s"))
                list.add("size");
            if (args.get(0).contains("-p"))
                list.add("permission");
        }
        return list;
    }
}
