package command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Add text to the file
 */
public class Add extends Command {
    public Add(Context context) {
        super(context);
    }

    /**
     * Adds text to the end or a specific location of the
     * file, depending on the number of arguments
     * @param args The name of the file and the text to be added
     * @return The output text of the file with the added text
     */
    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File result = null;
        if (args.isEmpty())
            return "Incorrect argument. Use `add <file name> <text>` to add text to the file";
        else {
            File file = context.getCurrentDirectory();
            File[] allFiles = file.listFiles();
            if (allFiles != null) {
                for (File f : allFiles) {
                    if (f.getName().equals(args.get(0))) {
                        result = f;
                        if (args.size() == 2)
                            addTextToEnd(args, f);
                        if (args.size() == 3)
                            addTextToRandomPosition(args, f);
                    }
                }
            }
        }
        return FileUtils.readFileToString(result, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param args The text which will add to the end of file
     * @param f The file to which the text will be added
     */
    @SneakyThrows
    private void addTextToEnd(List<String> args, File f){
        FileUtils.writeStringToFile(
                f, "\r\n" + args.get(1), StandardCharsets.UTF_8, true);
    }

    /**
     *
     * @param args The text which will add to the special location of the file
     * @param f The file to which the text will be added
     */
    @SneakyThrows
    private void addTextToRandomPosition(List<String> args, File f){
        int position = Integer.parseInt(args.get(2));
        RandomAccessFile rf = new RandomAccessFile(f.getPath(), "rw");
        byte[] textToAdd = args.get(1).getBytes(StandardCharsets.UTF_8);
        rf.seek(position);
        byte[] text = new byte[(int) (rf.length() - position)];
        rf.readFully(text);
        byte[] res = ArrayUtils.addAll(textToAdd, text);
        rf = new RandomAccessFile(f.getPath(), "rw");
        rf.seek(position);
        rf.write(res);
        rf.close();
    }
}

