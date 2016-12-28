/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory.data;

import java.util.Map;
import org.jsoup.nodes.Element;

/**
 *
 * @author u.sharma
 * State design pattern
 */
public interface DataDrivenInterface {

    /**
     * This method process the parsing mechanism which identifies the template and data-if or data-for method
     * @return String 
     */
    public String parseAndReplaceTag();
    
    /**
     * This method sets the objects 
     * @param loadedClass -- javascript class object
     * @param classPropertise -- class object map
     * @param html - raw html
     * @param htmlElement - jsoup element object
     */
    public void setClassAndParameters(Class<?> loadedClass, Map<String, Object> classPropertise, String html, Element htmlElement);
}
