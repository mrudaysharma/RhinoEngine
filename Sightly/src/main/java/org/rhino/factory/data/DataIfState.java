package org.rhino.factory.data;

import java.util.HashMap;
import java.util.Map;
import org.jsoup.nodes.Element;
import org.rhino.common.HTMLCatalog;

/**
 *
 * @author u.sharma
 * DataIfState execute the data-if containing tags.
 */
public class DataIfState extends DataDrivenCommon implements DataDrivenInterface {

    Class<?> loadedClass = null;
    Map<String, Object> classPropertise = new HashMap<String, Object>();
    Element element;
    String html;

    @Override
    public String parseAndReplaceTag() {
        String tagIf = element.outerHtml();

        String ifAttr = element.attr(HTMLCatalog.DATA_IF);
        if (loadedClass != null && loadedClass.getName().toLowerCase().contains(ifAttr.substring(0, ifAttr.indexOf(".")))) {

            if (classPropertise.containsKey("is" + ifAttr.substring(ifAttr.indexOf(".") + 1))) {

                if (Boolean.valueOf(classPropertise.get("is" + ifAttr.substring(ifAttr.indexOf(".") + 1)).toString())) {
                    html = bindAttributes(tagIf, loadedClass.getName(), classPropertise, html);
                } else {
                    html = html.replace(tagIf, "");
                }
            }
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
