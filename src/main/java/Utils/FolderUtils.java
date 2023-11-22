package Utils;

import jakarta.servlet.ServletContext;

import java.io.File;

public class FolderUtils {

    public static void createImagesFolder(ServletContext servletContext) {
        File imagesFolder = new File(getImagesFolderPath(servletContext));
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }
    }

    public static String getImagesFolderPath(ServletContext servletContext) {
        String realPath = servletContext.getRealPath("/"); // Get the real path on the server
        return realPath + File.separator + "images";
    }
}
