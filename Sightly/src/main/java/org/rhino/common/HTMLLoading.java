/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.common;

import org.rhino.exception.BedRequestException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author u.sharma This class stream html file from URL or project resources
 */
public class HTMLLoading {

    /**
     * Load HTML page from resources
     *
     * @param filePath -- project resource path
     * @return String raw html
     */
    public String loadHtmlFileFromResource(String filePath) {
        String rawHtmlPage = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(filePath).getFile());
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                rawHtmlPage += sc.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HTMLLoading.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rawHtmlPage;
    }

    /**
     * Load HTML from (http|htts|ftp) URL
     *
     * @param url -- URL of html file
     * @return String raw html
     */
    public String loadHtmlFileFromURL(String url) {
        Document htmlDoc = null;
        try {
            if (!url.matches(HTMLCatalog.URL_REGEX)) {
                throw new BedRequestException("BED URL " + url, "loadHtmlFileFromURL", HTMLLoading.class.getName());
            }
            htmlDoc = Jsoup.connect(url).get();
            if (htmlDoc == null) {
                throw new BedRequestException("HTML PAGE IS NOT FIND ON GIVEN LOCATION " + url, "loadHtmlFileFromURL", HTMLLoading.class.getName());
            }

        } catch (IOException ex) {
            Logger.getLogger(HTMLLoading.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BedRequestException ex) {
            Logger.getLogger(HTMLLoading.class.getName()).log(Level.SEVERE, null, ex);
        }
        return htmlDoc.html();
    }

}
