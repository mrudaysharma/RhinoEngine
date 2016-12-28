/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author u.sharma
 */
public interface ScriptFactory {
    /**
     * 
     * @param rawHtml -- Complete html 
     * @return -- String server/javascript code
     * This method gets the rawHtml. From this html it filters the server/javascript code
     */
    public String scriptMaterialsFromRawData(String rawHtml);
    
    /**
     * 
     * @param rawHtml -- Complete html
     * @param request -- HttpServletRequest helps to read parameter from URL
     * @return Object -- Java scirpt included Class Object
     * This method provides class object based on the java script code.
     */
    public Object runScriptEngine(String rawHtml,HttpServletRequest request);
   
}
