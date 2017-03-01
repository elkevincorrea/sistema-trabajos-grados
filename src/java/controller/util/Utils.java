/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author kecc
 */
public class Utils {
    public static String uploadFile(String fileName, Part part, Logger LOGGER) throws IOException{
        String res = null;
        OutputStream out = null;
        InputStream fileContent = null;
        try {
            File f = new File(fileName);
            out = new FileOutputStream(f);
            fileContent = part.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = fileContent.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
            LOGGER.log(Level.INFO, "File uploaded at {0}",f.getAbsolutePath());
            res = f.getAbsolutePath();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", e.getMessage());
        } finally{
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
        return res;
    }
}
