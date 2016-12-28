/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Element;
import org.rhino.common.HTMLCatalog;

/**
 *
 * @author u.sharma
 * DataForState provides data-for html tag values
 */
public class DataForState extends DataDrivenCommon implements DataDrivenInterface {

    Class<?> loadedClass = null;
    Map<String, Object> classPropertise = new HashMap<String, Object>();
    String html;
    Element element = null;

    @Override
    public String parseAndReplaceTag() {
        String data = element.html();
        System.out.println("tag : " + data);
        String forObj = data.substring(data.indexOf("${") + 2, data.indexOf("}"));

        String forAttr = element.attr(HTMLCatalog.DATA_FOR+"-" + forObj);

        StringBuffer sb = new StringBuffer();

        if (loadedClass != null && loadedClass.getName().toLowerCase().contains(forAttr.split("\\.")[0]) && classPropertise.containsKey("get" + forAttr.split("\\.")[1])) {
            for (Object ob : (List<Object>) classPropertise.get("get" + forAttr.split("\\.")[1])) {

                sb.append("<div>").append(data.substring(0, data.indexOf("${"))).append(ob).append("</div>");
            }

            html = html.replace(data, sb);

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
