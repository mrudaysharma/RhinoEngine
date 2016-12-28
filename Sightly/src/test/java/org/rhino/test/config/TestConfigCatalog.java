/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.test.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mockito.Mockito;

/**
 *
 * @author u.sharma
 */
public class TestConfigCatalog extends Mockito{

    public static final String REQUEST_TYPE = "http://"; //https,ftp
    public static final String BASE_URL = "localhost:";
    public static final String PORT = "8080";
    public static final String RESOURCE_URL = "/RhinoTesting/parser?id=2";
    private Pattern pattern;
    private Matcher matcher;

    private static final String HTML_TAG_FORMAT_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    public TestConfigCatalog() {
        pattern = Pattern.compile(HTML_TAG_FORMAT_PATTERN);
    }

    public boolean validate(final String tag) {

        matcher = pattern.matcher(tag);
        return matcher.matches();

    }
}
