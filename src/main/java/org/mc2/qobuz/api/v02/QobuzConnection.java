/*
 * JAVA client for QOBUZ.API (http://www.qobuz.com/fr-fr/page/labs).
 *
 * Copyright (C) 2017 Marco Curti (marcoc1712 at gmail dot com).
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.mc2.qobuz.api.v02;

import org.mc2.qobuz.api.v02.exceptions.ResourceNotFoundException;
import org.mc2.qobuz.api.v02.exceptions.RequestException;
import org.mc2.qobuz.api.v02.exceptions.QobuzConnectionException;
import org.mc2.qobuz.api.v02.exceptions.AuthorizationException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.logging.Level;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.HttpStatus;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.imageio.ImageIO;
//import org.scribe.model.Response;

public class QobuzConnection {
    
    private static Logger log = Logger.getLogger(QobuzConnection.class.getName());

    private static QobuzConnection _instance = null;
    private static DefaultHttpClient httpClient = new DefaultHttpClient();
    private static HttpURLConnection urlc;

    public static final String contentType = "application/x-www-form-urlencoded";
    public static final String userAgent = "QobuzAPIv0.2-java (marcoc1712@gmail.com)";
    public static final String acceptEncoding = "gzip";
    public static final String protocol = "http://";
    public static final String applicationId = QobuzAppId.APP_ID;
    

    private QobuzConnection() {
        HttpParams params = httpClient.getParams();

        HttpConnectionParams.setConnectionTimeout(params, 60000);
        HttpConnectionParams.setSoTimeout(params, 60000);
        params.setParameter("http.protocol.content-charset", "UTF-8");

    }

    public static QobuzConnection getInstance() {
        if (_instance == null)
            _instance = new QobuzConnection();

        return _instance;
    }

    /**
     * Gets data from URL as String throws {@link QobuzConnectionException} If anything goes wrong
     * 
     * @return The content of the URL as a String
     * @throws org.mc2.qobuz.api.v02.exceptions.QobuzConnectionException
     */
    public String getDataAsString(URL url) throws QobuzConnectionException {
        
        try{
                return getAnswerAsString(getAnswer(url));
        }
        catch (ResourceNotFoundException e){
            log.info(e.getMessage());
            return null;
        }catch (QobuzConnectionException e){
            log.log(Level.SEVERE, "Fatal connection error: {0}", e.getMessage());
            return null;
        }
        
    }


    private String getAnswerAsString(InputStream in) throws QobuzConnectionException {
        
        String responseBody="";
        try {
            
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    responseBody += line;
                    responseBody += "\n";
                }
                rd.close();
                return responseBody;
	    	
        } catch (IOException e) {
            throw new QobuzConnectionException(e.getMessage(), e);
        }
    }
    
    private BufferedImage getAnswerAsImage(InputStream in) throws QobuzConnectionException {
        
        try {
                BufferedImage img = ImageIO.read(in);
                return img;
	    	
        } catch (IOException e) {
            throw new QobuzConnectionException(e.getMessage(), e);
        }
    }
    
    private InputStream getAnswer(URL url) throws QobuzConnectionException {

        try {
            
            System.out.println("Hitting url: " + url.toString());
            
            urlc = (HttpURLConnection) url.openConnection();

            urlc.setUseCaches(false);
            urlc.setRequestMethod("GET");
            urlc.setRequestProperty("Content-Type", contentType);
            urlc.setRequestProperty("User-Agent", userAgent);
            urlc.setRequestProperty("Accept-Encoding", acceptEncoding);
            urlc.setRequestProperty("X-App-Id", applicationId);

            int statusCode = urlc.getResponseCode();
            String em;
            switch (statusCode)
            {
	      	case HttpStatus.SC_OK:
                            InputStream out = urlc.getInputStream();
                            if ("gzip".equals(urlc.getContentEncoding())) {
                                 out = new GZIPInputStream(out);
                            }

                            return out;
 
	      	case HttpStatus.SC_NOT_FOUND:
	      		em = "ERROR: web service returned " + statusCode + "', response was: " + urlc.getResponseMessage();
                                 throw new ResourceNotFoundException(em);
	      		
	      	case HttpStatus.SC_BAD_REQUEST:
                                 em = "ERROR: web service returned " + statusCode + "', response was: " + urlc.getResponseMessage();
	      		throw new RequestException(em);
	      		
	      	case HttpStatus.SC_FORBIDDEN:
	      		em = "ERROR: web service returned " + statusCode + "', response was: " + urlc.getResponseMessage();
	      		throw new AuthorizationException(em);
	      		
	      	case HttpStatus.SC_UNAUTHORIZED:
	      		em = "ERROR: web service returned " + statusCode + "', response was: " + urlc.getResponseMessage();
	      		throw new AuthorizationException(em);
	      		
	      	default:
	      		em = "ERROR: web service returned unknown status '" + statusCode + "', response was: " + urlc.getResponseMessage();
	      		log.severe(em);
	      		throw new QobuzConnectionException(em);
	      }
        } catch (IOException e) {
            throw new QobuzConnectionException("ERROR: web service returned unknown status, response was: " +e.getMessage(), e);
            
        }
    }
    /*
    public String getDataAsString(URL url,QobuzOAuthCredentials credentials) throws QobuzConnectionException {
        
        String path = url.toString();
        WorkFlow wf= WorkFlow.getInstance(credentials);

        Response resp = wf.getResponse(path);
        
        if (resp == null) throw new AuthorizationException ("Invalid request"); 
        if (resp.getCode() != 200) throw new AuthorizationException (resp.getCode()+" - "+resp.getMessage()); 

        //TODO: Implement the counter for remaining calls.
       resp.getHeaders();
       
       try{
                return getAnswerAsString(resp.getStream());
        }
        catch (ResourceNotFoundException e){
            log.info(e.getMessage());
            return null;
        }catch (QobuzConnectionException e){
            log.log(Level.SEVERE, "Fatal connection error: {0}", e.getMessage());
            return null;
        }
     }
   
    public BufferedImage getDataAsImage(URL url,QobuzOAuthCredentials credentials) throws QobuzConnectionException {
        
        String path = url.toString();
        WorkFlow wf= WorkFlow.getInstance(credentials);
        
        //InputStream in = wf.getInputstream(path);
        Response resp = wf.getResponse(path);
        
        if (resp == null) throw new AuthorizationException ("Invalid request"); 
        if (resp.getCode() != 200) throw new AuthorizationException (resp.getCode()+" - "+resp.getMessage()); 

        //TODO: Implement the counter for remaining calls.
        
        resp.getHeaders();
        
        InputStream in = resp.getStream();

        try{
            BufferedImage out =  getAnswerAsImage(in);
            return out;
        }catch (ResourceNotFoundException e)   {
            log.info(e.getMessage());
            return null;
        }
        catch (QobuzConnectionException e){
            log.log(Level.SEVERE, "Fatal connection error: {0}", e.getMessage());
            return null;
        }
    }
*/
    
}
