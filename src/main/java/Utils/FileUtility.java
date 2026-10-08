package Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Stream;

public class FileUtility {
    /** Deletes all existing files in the download folder */
    public static void cleanDownloadFolder(String downloadDir) {
        Path downloadPath = Paths.get(downloadDir);

        if (!Files.exists(downloadPath)) return;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(downloadPath)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    Files.deleteIfExists(path);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to clean download directory", e);
        }
    }


    public static File waitForNewDownloadedFile(String downloadDir, int timeoutSeconds) {

        Path downloadPath = Paths.get(downloadDir);

        if (!Files.exists(downloadPath)) {
            System.out.println("❌ Download directory does not exist: " + downloadDir);
            return null;
        }

        int maxAttempts = timeoutSeconds * 2; // poll every 500ms

        for (int i = 0; i < maxAttempts; i++) {

            try (Stream<Path> files = Files.list(downloadPath)) {

                Optional<Path> newestFile = files
                        .filter(Files::isRegularFile)
                        .filter(f -> !f.toString().endsWith(".crdownload"))
                        .filter(f -> !f.toString().endsWith(".tmp"))
                        .filter(f -> f.toFile().length() > 0)
                        .filter(f -> f.getFileName().toString().endsWith(".xlsx"))
                        // 🔥 SORT BY LAST MODIFIED
                        .sorted((f1, f2) ->
                                Long.compare(
                                        f2.toFile().lastModified(),
                                        f1.toFile().lastModified()))
                        .findFirst();

                if (newestFile.isPresent()) {
                    File file = newestFile.get().toFile();
                    System.out.println("✅ File downloaded: " + file.getName());
                    return file;
                }

            } catch (IOException ignored) {}

            pauseMillis(500);
        }

        System.out.println("No downloaded file found after " + timeoutSeconds + " seconds.");
        return null;
    }


    public static String getResourceFilePath(String fileName) {
        URL resource = FileUtility.class.getClassLoader().getResource("testdata/" + fileName);

        if (resource == null) {
            throw new RuntimeException("❌ File not found in resources: " + fileName);
        }

        return new File(resource.getFile()).getAbsolutePath();
    }

    private static void pauseMillis(long millis) {
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(millis));
        if (Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("Interrupted while waiting for a download");
        }
    }


}
