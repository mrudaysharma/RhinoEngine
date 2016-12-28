package org.rhino.bridge;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.rhino.common.HTMLCatalog;
import org.rhino.common.HTMLLoading;
import org.rhino.exception.BedRequestException;
import org.rhino.exception.ResourceNotFoundException;
import org.rhino.factory.AttributeEngine;

/**
 *
 * @author u.sharma
 * This class behave like bridge between Script and Attribute factories
 */
public class ClientBridge extends HTMLLoading {

    public ClientBridge() {
    }

    /**
     * @param htmlPagePath -- URL or java project resource path
     * @return String
     * Stream html page either from URL or project resources
     */
    public String loadHTMLPage(String htmlPagePath) {
        String htmlPage = "";
        try {
            if (htmlPagePath == null || htmlPagePath.isEmpty()) {
                throw new BedRequestException("HTML URL IS EITHER NULL OR EMPTY ", "loadHTMLPage", "ClientBridge");
            }
            if (htmlPagePath.matches(HTMLCatalog.URL_REGEX)) {
                htmlPage = loadHtmlFileFromURL(htmlPagePath);
            } else {
                htmlPage = loadHtmlFileFromResource(htmlPagePath);
            }
            if (htmlPage == null || htmlPage.isEmpty()) {
                throw new ResourceNotFoundException("NO RESOURCE FOUND ON GIVEN URL " + htmlPagePath, "loadHTMLPage", "ClientBridge");
            }
        } catch (BedRequestException | ResourceNotFoundException bx) {
            Logger.getLogger(AttributeEngine.class.getName()).log(Level.SEVERE, null, bx);
        }
        return htmlPage;
    }

}
