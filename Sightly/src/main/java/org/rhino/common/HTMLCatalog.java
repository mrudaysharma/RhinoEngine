/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.common;

/**
 * Contains all static values
 *
 * @author u.sharma
 */
public class HTMLCatalog {

    public static final String REQUEST_TYPE = "http://"; //https,ftp
    public static final String BASE_URL = "localhost:";
    public static final String PORT = "8080";
    public static final String RESOURCE_URL = "/Sightly/index.html";
    public static String SCRIPT_TAG = "script";
    public static String SCRITPT_TYPE = "type";
    public static String SERVER_SCRIPT = "server/javascript";
    public static String IMPORT_PACKAGES_FROM = "Packages";
    public static String IMPORT_PACKAGES_TO = ")";

    public static String IMPORTED_CLASS_REGEX = IMPORT_PACKAGES_FROM + "(.*?)" + IMPORT_PACKAGES_TO;
    public static String GET_GETTER_REGEX = "^get[A-Z].*";
    public static String IS_GETTER_REGEX = "^is[A-Z].*";
    public static final String IF_ATTRIBUTE = "IF_ATTRIBUTE";
    public static final String DATA_IF = "data-if";
    public static final String DATA_FOR = "data-for";
    public static final String FOR_ATTRIBUTE = "FOR_ATTRIBUTE";
    public static final String EMPTY_ATTRIBUTE = "EMPTY_ATTRIBUTE";

    public static String URL_REGEX = "^(https?|ftp)://.*$";

}
