/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.parser;

import org.rhino.bridge.ClientBridge;
import org.rhino.exception.FactoryProductionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.rhino.factory.AttributeEngine;
import org.rhino.factory.AttributeFactory;
import org.rhino.factory.ScriptFactory;
import org.rhino.factory.ScriptRunnerEngine;

/**
 *
 * @author u.sharma
 */
public class HTMLParserServlet extends HttpServlet {

    private int val = -1;

    public void setValue(int x) {
        val = x;
    }

    public int getValue() {
        return val;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @return
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ScriptException {
        String buildedHtml = "";
        try {
            ClientBridge client = new ClientBridge();

            //Template URL
            String rawHtml = client.loadHTMLPage("http://localhost:8080/Sightly/index.html");

            ScriptFactory scriptFactory = new ScriptRunnerEngine();
            String materialForSciptEngine = scriptFactory.scriptMaterialsFromRawData(rawHtml);
            Object objectFromScriptFactory = scriptFactory.runScriptEngine(materialForSciptEngine, request);

            AttributeFactory attributeFactory = new AttributeEngine();
            String materialForAttibuteEngine = attributeFactory.runAttributeEngine(rawHtml, objectFromScriptFactory);
            buildedHtml = materialForAttibuteEngine;

        } catch (FactoryProductionException ex) {
            Logger.getLogger(HTMLParserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buildedHtml;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String html = processRequest(request, response);
            try (PrintWriter writeHTML = response.getWriter()) {
                writeHTML.print(html);

            }
        } catch (ScriptException ex) {
            Logger.getLogger(HTMLParserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String html = processRequest(request, response);
            try (PrintWriter writeHTML = response.getWriter()) {
                writeHTML.print(html);

            }
        } catch (ScriptException ex) {
            Logger.getLogger(HTMLParserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
