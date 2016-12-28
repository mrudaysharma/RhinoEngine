/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rhino.common.HTMLCatalog;
import org.rhino.parser.ExtendedHTTPRequest;


/**
 *
 * @author u.sharma
 */
public class ScriptRunnerEngine  implements ScriptFactory{

    //This method reads the server/javascript content using Java Rhino Engine
    @Override
    public Object runScriptEngine(String data, HttpServletRequest request) {
        Object classObject = null;
        try {
            //Contains request.Parameter("id") method
            ExtendedHTTPRequest httpRequest = new ExtendedHTTPRequest(request);
            //Rhino Script Manager
            ScriptEngineManager manager = new ScriptEngineManager();
            javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
            engine.put("request", httpRequest);

            engine.eval(data);
            //int returnedValue = (int) engine.get("id"); //To read ID
            classObject = (Object) engine.get("person");
        } catch (ScriptException ex) {
            Logger.getLogger(ScriptRunnerEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classObject;
    }

    @Override
    public String scriptMaterialsFromRawData(String rawHtml) {
        String data = null;
        Document htmlDoc = Jsoup.parse(rawHtml);
        //get elements between server/javascript tag
        Elements serverScriptElements = htmlDoc.getElementsByAttributeValue(HTMLCatalog.SCRITPT_TYPE, HTMLCatalog.SERVER_SCRIPT);
        if (serverScriptElements != null && !serverScriptElements.isEmpty()) {
            data = serverScriptElements.html();
            if (data.contains(")")) {
                //Rhino throws error if method do not terminated by ; 
                data = data.replace(")", "); ");
            }
        }
        return data;
    }

}
