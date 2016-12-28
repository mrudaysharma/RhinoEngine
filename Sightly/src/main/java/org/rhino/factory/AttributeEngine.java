/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory;

import org.rhino.factory.data.DataDrivenInterface;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.HashBiMap;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rhino.common.HTMLCatalog;
import org.rhino.exception.BedRequestException;

/**
 *
 * @author u.sharma
 * This engine reads the special attributes like data-if and data-for and 
 * build the html according to this
 */
public class AttributeEngine implements AttributeFactory {

    @Override
    public String runAttributeEngine(String buildedHtmlPage, Object classObject) {
        try {
            if (buildedHtmlPage == null || buildedHtmlPage.isEmpty() || classObject == null) {
                throw new BedRequestException("HTML CONTENT OR INCLUDED CLASS NOT AVAILABLE", "runAttributeEngine", "AttributeEngine");
            }
            Document htmlDoc = Jsoup.parse(buildedHtmlPage);
            Elements serverScriptElements = htmlDoc.getAllElements();
            Map<String, Object> elementMapper = collectElementsForEngine(classObject);
            buildedHtmlPage = passElementsToEngine(serverScriptElements, classObject.getClass(), elementMapper, buildedHtmlPage);

        } catch (BedRequestException bx) {
            Logger.getLogger(AttributeEngine.class.getName()).log(Level.SEVERE, null, bx);
        }
        return buildedHtmlPage;
    }

    /**
     * 
     * @param classObject -- javascript imported class
     * @return map collection of class getters
     * This method collects all method from javascript included class
     */
    private Map<String, Object> collectElementsForEngine(Object classObject) {
        Map<String, Object> classProperties = new HashMap<>();
        for (Method method : findGetters(classObject.getClass())) {
            try {
                Object oblist = method.invoke(classObject);
                classProperties.put(method.getName().toLowerCase(), oblist);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(AttributeEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return classProperties;
    }

    /**
     * @param serverScriptElements -- This is a jsoup element object which contains tags of html 
     * @param loadedClass -- javascript included class
     * @param classPropertise -- map collection of class getters
     * @param html -- rad html
     * @return  -- String html
     * This method calls enum class based on the tag type suchs data-if, data-for or emptly element
     */
    private String passElementsToEngine(Elements serverScriptElements, Class<?> loadedClass, Map<String, Object> classPropertise, String html) {
        for (Element element : serverScriptElements) {
            AttributeTypeEnum eventFactory = null;
            Attributes specialAttributes = element.attributes();

            if (identifyElementsAttribute(specialAttributes, HTMLCatalog.DATA_IF)) {
                eventFactory = AttributeTypeEnum.valueOf(HTMLCatalog.IF_ATTRIBUTE);
            } else if (identifyElementsAttribute(specialAttributes, HTMLCatalog.DATA_FOR)) {
                eventFactory = AttributeTypeEnum.valueOf(HTMLCatalog.FOR_ATTRIBUTE);
            } else if (element.children().isEmpty()) {
                eventFactory = AttributeTypeEnum.valueOf(HTMLCatalog.EMPTY_ATTRIBUTE);
            }

            if (eventFactory != null) {
                DataDrivenInterface ifDataDriver = eventFactory.get();
                ifDataDriver.setClassAndParameters(loadedClass, classPropertise, html, element);
                html = ifDataDriver.parseAndReplaceTag();
            }

        }
        return html;
    }

    //This methods gets the getter methods from class
    private static ArrayList<Method> findGetters(Class<?> c) {
        ArrayList<Method> list = new ArrayList<>();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                list.add(method);
            }
        }
        return list;
    }

    //Finds the getter methods
    private static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length == 0) {
            if (method.getName().matches(HTMLCatalog.GET_GETTER_REGEX)
                    && !method.getReturnType().equals(void.class)) {
                return true;
            }
            if (method.getName().matches(HTMLCatalog.IS_GETTER_REGEX)
                    && method.getReturnType().equals(boolean.class)) {
                return true;
            }
        }
        return false;
    }

    //Finds the element's attributes 
    private boolean identifyElementsAttribute(Attributes specialAttributes, String attributeType) {
        for (Attribute specialAttribute : specialAttributes) {
            if (specialAttribute.getKey().contains(attributeType)) {
                return true;
            }
        }
        return false;
    }

}
