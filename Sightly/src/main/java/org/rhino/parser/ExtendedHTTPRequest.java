/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.parser;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author u.sharma
 * This Class helps to read URL parameter provided by the user.
 */
public class ExtendedHTTPRequest {

    String id;
    HttpServletRequest request;

    public ExtendedHTTPRequest() {
    }

    /**
     * Constructor get the object of HttpServletRequest which contains URL parameter ex. ?id =1
     * @param request -- HTTPServletRequest to read URL parameter
     */ 
   public ExtendedHTTPRequest(HttpServletRequest request) {
        this.request = request;
    }

   /**
    * Reads the URL parameter and set and convert it into the Integer
    * @param id -- parameter value 
    * @return -- Integer parameter
    */
    public int getParameter(String id) {
        String parameter = request.getParameter(id);
        int number = Integer.parseInt(parameter);
        return number;
    }
}
