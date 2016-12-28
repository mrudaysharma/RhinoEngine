/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory.data;

import java.util.Map;

/**
 *
 * @author u.sharma
 * This class feeds the dynamic values inplace {person.name} and bind those attributes in html form
 */
public class DataDrivenCommon {
/**
 * 
 * @param tag -- which contains template code
 * @param loadedClass -- javascript included class
 * @param classPropertise -- getter methods of class
 * @param html -- raw html
 * @return String final html 
 * 
 * This method reads and feed the value inside template code
 */
    public String bindAttributes(String tag, String loadedClass, Map<String, Object> classPropertise, String html) {
        String[] splitedArguments = tag.split("\\$");
        for (String splitedArgument : splitedArguments) {
            if (splitedArgument.startsWith("{")) {
                String attrName = splitedArgument.substring(splitedArgument.indexOf(".") + 1, splitedArgument.indexOf("}"));
                if (loadedClass.toLowerCase().contains(splitedArgument.substring(1, splitedArgument.indexOf(".")))) {
                    String replaceableAttr = splitedArgument.substring(1, splitedArgument.indexOf("}") + 1);
                    String newTag = tag.replace("${" + replaceableAttr, classPropertise.get("get" + attrName).toString());
                    html = html.replace(tag, newTag);
                }
            }
        }
        return html;
    }

}
