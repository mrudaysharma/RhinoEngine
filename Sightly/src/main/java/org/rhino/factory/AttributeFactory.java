/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.factory;

/**
 *
 * @author u.sharma
 */
public interface AttributeFactory {
   
    /**
     * 
     * @param rawHtml -- HTML template
     * @param classObject -- javascript import class 
     * @return String html
     * This engine builds the final html pages
     */ 
   public String runAttributeEngine(String rawHtml,Object classObject);
}
