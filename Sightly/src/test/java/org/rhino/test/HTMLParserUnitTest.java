/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rhino.test;

import org.rhino.test.config.TestConfigCatalog;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.rhino.Person;
import org.rhino.bridge.ClientBridge;
import org.rhino.factory.AttributeEngine;
import org.rhino.factory.AttributeFactory;
import org.rhino.factory.ScriptFactory;
import org.rhino.factory.ScriptRunnerEngine;
import org.rhino.parser.HTMLParserServlet;

/**
 *
 * @author u.sharma
 */
public class HTMLParserUnitTest extends TestConfigCatalog {

    public static String serviceURL;

    @BeforeClass
    public static void setUpClass() {
        serviceURL = REQUEST_TYPE + BASE_URL + PORT + RESOURCE_URL;
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pingServiceTest() throws IOException {

        try {
            URL url = new URL(REQUEST_TYPE + BASE_URL + PORT + RESOURCE_URL);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();

            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void streamHTMLTest() throws IOException {

        try {
            URL url = new URL(REQUEST_TYPE + BASE_URL + PORT + "/RhinoTesting");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
            ClientBridge client = new ClientBridge();
            String html = client.loadHTMLPage(url.toString());
            assertNotNull(html);
            assertTrue(html.contains("${") && html.contains("importClass(Packages.") && html.contains("data-if"));
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void factoryValidatorTest() throws IOException {

        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);

            String classMembers = "Maria,Pere,true,3,Anna,Berta,Clara";
            URL url = new URL(REQUEST_TYPE + BASE_URL + PORT + "/RhinoTesting?id=1");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
            ClientBridge client = new ClientBridge();
            String html = client.loadHTMLPage(url.toString());
            assertNotNull(html);
            assertTrue(html.contains("${") && html.contains("importClass(Packages.") && html.contains("data-if"));

            when(request.getParameter("id")).thenReturn("4");
            PrintWriter writer = new PrintWriter(System.out);
            when(response.getWriter()).thenReturn(writer);

            new HTMLParserServlet().doGet(request, response);
            assertFalse(writer.checkError());
            writer.flush();
//            ScriptFactory scriptFactory = new ScriptRunnerEngine();
//            String materialForSciptEngine = scriptFactory.scriptMaterialsFromRawData(html);
//            Object objectFromScriptFactory = scriptFactory.runScriptEngine(materialForSciptEngine, request);
//            assertTrue(objectFromScriptFactory.getClass().isInstance(Person.class));

        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        } catch (ServletException ex) {
            Logger.getLogger(HTMLParserUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
