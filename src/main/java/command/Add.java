package command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Add extends Command {
    public Add(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Incorrect argument. Use `open <file name> <text>` to open the file";
        } else {
            File file = context.getCurrentDirectory();
            File[] allFiles = file.listFiles();
            if (allFiles != null) {
                for (File f : allFiles) {
                    if (args.size() == 2) {
                        if (f.getName().equals(args.get(0))) {
                            FileUtils.writeStringToFile(
                                    f, "\r\n" + args.get(1), StandardCharsets.UTF_8, true);
                            return f.toString();

                        }
                    }
                    if (args.size() == 3) {
                        if (f.getName().equals(args.get(0))) {
                            int position = Integer.parseInt(args.get(2));

//                            FileUtils.writeByteArrayToFile(f, bytes, position, bytes.length - position, true);
                            RandomAccessFile rf = new RandomAccessFile(f.getPath(), "rw");
                            rf.seek(position);
//                            byte[] b = new byte[(int) (rf.length() - position)];
//                            for (int i = 0; i < b.length; i++) {
//                                b[i] = rf.readByte();
//                            }
                            byte[] bytes = args.get(1).getBytes(StandardCharsets.UTF_8);
//                            byte[] res = ArrayUtils.addAll(b, bytes);
                            rf.write(bytes);
                            rf.close();
                        }
                    }
                }
            }
            return null;
        }
    }
}
