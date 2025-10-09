import java.util.*;

public class Problem6_FileOrganizer {
    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String newName;
        String subCategory;
    }
    public static FileInfo extractFileInfo(String fileName) {
        FileInfo fi = new FileInfo();
        fi.originalName = fileName;

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            fi.baseName = fileName;
            fi.extension = "unknown";
        } else {
            fi.baseName = fileName.substring(0, dotIndex);
            fi.extension = fileName.substring(dotIndex + 1).toLowerCase();
        }
        return fi;
    }
    public static String categorize(String ext) {
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("mkv")) return "Video";
        return "Unknown";
    }
    public static String generateNewName(FileInfo fi, Map<String, Integer> nameCount) {
        String date = "20250825";
        StringBuilder sb = new StringBuilder();
        sb.append(fi.category).append("_").append(date);
        int count = nameCount.getOrDefault(fi.category, 0) + 1;
        nameCount.put(fi.category, count);

        sb.append("_").append(count).append(".").append(fi.extension);
        return sb.toString();
    }
    public static String analyzeContent(FileInfo fi) {
        if (fi.extension.equals("txt")) {
            if (fi.baseName.toLowerCase().contains("resume")) return "Resume";
            if (fi.baseName.toLowerCase().contains("report")) return "Report";
            if (fi.baseName.toLowerCase().contains("code")) return "Code";
            return "GeneralText";
        }
        return "N/A";
    }
    public static void displayReport(List<FileInfo> files) {
        System.out.println("\n--- File Organization Report ---");
        System.out.printf("%-20s %-12s %-20s %-15s\n", "Original Name", "Category", "New Name", "SubCategory");
        System.out.println("--------------------------------------------------------------------------------");
        Map<String, Integer> categoryCount = new HashMap<>();
        for (FileInfo f : files) {
            System.out.printf("%-20s %-12s %-20s %-15s\n", f.originalName, f.category, f.newName, f.subCategory);
            categoryCount.put(f.category, categoryCount.getOrDefault(f.category, 0) + 1);
        }

        System.out.println("\nCategory-wise Count:");
        for (String cat : categoryCount.keySet()) {
            System.out.println(cat + ": " + categoryCount.get(cat));
        }
    }
    public static void generateRenameCommands(List<FileInfo> files) {
        System.out.println("\n--- Batch Rename Commands ---");
        for (FileInfo f : files) {
            System.out.println("rename " + f.originalName + " -> " + f.newName);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();
        Map<String, Integer> nameCount = new HashMap<>();

        System.out.println("Enter number of files:");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter file name " + (i + 1) + ":");
            String fname = sc.nextLine();
            FileInfo fi = extractFileInfo(fname);
            fi.category = categorize(fi.extension);
            fi.subCategory = analyzeContent(fi);
            fi.newName = generateNewName(fi, nameCount);
            files.add(fi);
        }

        displayReport(files);
        generateRenameCommands(files);
    }
}
