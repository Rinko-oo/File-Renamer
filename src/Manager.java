package src;
import java.io.File;

public class Manager {
    //method to get names of renamed files
    static File[] getRenamedFiles(String title, File[] files, String prefix, int start) {
        File[] renamedFiles = new File[files.length];
        int maxDigits = String.valueOf(start + files.length - 1).length();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            String fileType = fileName.substring(fileName.lastIndexOf('.'));
            String formattedNumber = String.format("%0" + maxDigits + "d", start + i);
            renamedFiles[i] = new File(title + " " + prefix + formattedNumber + fileType);
        }

        return renamedFiles;
    }

    //method to rename all the files
    static void renameFiles(File[] files, File[] renamedFiles) {
        for (int i = 0; i < files.length; i++) {
            if (files[i].renameTo(renamedFiles[i])) {
                System.out.println("Renamed: " + files[i].getName() + " to " + renamedFiles[i].getName());
            } else {
                System.out.println("Failed to rename: " + files[i].getName());
            }
        }
    }
}
