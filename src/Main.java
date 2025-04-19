package src;
import java.io.File;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Get the current working folder
        String currentFolder = System.getProperty("user.dir");

        // Create a File object for the current directory
        File folder = new File(currentFolder);
        StringBuilder fileList = new StringBuilder();

        // List all files in the directory
        File[] filesTEMP = folder.listFiles();
        File[] files = new File[filesTEMP.length - 1];
        int i = 0;
        for (File file : filesTEMP) {
            if (file.getName().endsWith(".jar")) {
                continue;
            } else {
                files[i] = file;
                i++;
            }
        }
        fileList.append("PLEASE ENSURE ALL FILES ARE THE SAME TYPE\nFiles to rename:\n");
        for (File file : files) {
                fileList.append(file.getName()).append("\n");
            }
        
        // Display the file list in a dialog box
        JOptionPane.showMessageDialog(null, fileList.toString(), "File Renamer", JOptionPane.INFORMATION_MESSAGE);
        
        // Get new title, prefix, and starting installation from user
        String newTitle = JOptionPane.showInputDialog(null, "Enter title of the series:", "File Renamer", JOptionPane.QUESTION_MESSAGE);
        System.out.println("New title: " + newTitle);
        if (newTitle == null) {
            cancelProgram();
        }
        String prefix = JOptionPane.showInputDialog(null, "Enter prefix of each installation:", "File Renamer", JOptionPane.QUESTION_MESSAGE);
        if (prefix == null) {
            cancelProgram();
        }
        System.out.println("Prefix: " + prefix);
        String startString = JOptionPane.showInputDialog(null, "Enter first installation:", "File Renamer", JOptionPane.QUESTION_MESSAGE);
        if (startString == null) {
            cancelProgram();
        }
        int start = Integer.parseInt(startString);
        System.out.println("Start: " + start);
        // Get preview of renamed files
        File[] renamedFiles = Manager.getRenamedFiles(newTitle, files, prefix,  start);
        StringBuilder renamedFileList = new StringBuilder("Renamed files:\n");
        for (int j = 0; j < renamedFiles.length; j++) {
            renamedFileList.append(renamedFiles[j].getName()).append("\n");
        }
        JOptionPane.showMessageDialog(null, renamedFileList.toString(), "Renamed Files", JOptionPane.INFORMATION_MESSAGE);
        // Ask user for confirmation to rename files
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to rename the files?", "File Renamer", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Rename files
            Manager.renameFiles(files, renamedFiles);
            JOptionPane.showMessageDialog(null, "Files renamed successfully!", "File Renamer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Operation Cancelled", "File Renamer", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // method to cancel program if closed\
    static void cancelProgram() {
        JOptionPane.showMessageDialog(null, "Operation Cancelled", "File Renamer", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}