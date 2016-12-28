/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory.data;

import java.util.HashMap;
import java.util.Map;
import org.jsoup.nodes.Element;

/**
 *
 * @author u.sharma
 * DataEmptyState reads the empty element
 */
public class DataEmptyState extends DataDrivenCommon implements DataDrivenInterface {

    Class<?> loadedClass = null;
    Map<String, Object> classPropertise = new HashMap<String, Object>();
    String html;
    Element element = null;

    @Override
    public String parseAndReplaceTag() {
        String attributeTag = element.outerHtml();
        if (attributeTag.contains("${")) {
            html = bindAttributes(attributeTag, loadedClass.getName(), classPropertise, html);
        }
        return html;
    }

    @Override
    public void setClassAndParameters(Class<?> loadedClass, Map<String, Object> classPropertise, String html, Element htmlElement) {
        this.loadedClass = loadedClass;
        this.classPropertise = classPropertise;
        this.html = html;
        element = htmlElement;
    }

}
