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

package com.mc2.qobuz.api.v02.query;

import com.mc2.qobuz.api.v02.QobuzController;
import com.mc2.qobuz.api.v02.exceptions.QobuzConnectionException;
import com.mc2.qobuz.api.v02.exceptions.QobuzAPIException;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


public class QobuzObjectQuery {
    
    static final String URL_GET_TEMPLATE = QobuzController.BASEURL + "/[ENDPOINT]/get?[ENDPOINT]_id=[ID]";
    
    public static final String ENDPOINT_ALBUM = "album";
    public static final String ENDPOINT_ARTICLE = "article";
    public static final String ENDPOINT_ARTIST = "artist";
    public static final String ENDPOINT_CATALOG = "catalog";
    public static final String ENDPOINT_GENRE = "genre";
    public static final String ENDPOINT_LABEL = "label";
    public static final String ENDPOINT_PLAYLIST = "playlist";
    public static final String ENDPOINT_TRACK = "track";

    JSONObject data;
    
    public QobuzObjectQuery(){
        
    }
   
    public JSONObject getObject(){
        return data;
    }
    
    protected final void getAnswer(final String urlStr) throws QobuzAPIException{
        data= getAnswer2(QobuzController.getUrl(urlStr));
    }
    protected final void getAnswer(final URL url) throws QobuzAPIException{
        data= getAnswer2(url);
    }
    
    private JSONObject getAnswer2(final URL url) throws QobuzAPIException {
        
        String answer="{error: 'empty answer'}"; 
        JSONObject jsonObject = new JSONObject(answer);
        try {
            answer =QobuzController.makeApiCall(url);
            if (answer == null)return jsonObject;
            if (answer.isEmpty())return jsonObject;
            
            jsonObject = new JSONObject(answer);
            return jsonObject;

        }catch (QobuzConnectionException ex) {
                Logger.getLogger(QobuzObjectQuery.class.getName()).log(Level.SEVERE, null, ex);
                //throw new QobuzConnectionException(ex.getMessage(), ex);
                System.out.println(" *** ERROR : QobuzObjectQuery.getAnswer2 QobuzConnectionException Answer: "+answer);
               return new JSONObject("{error: "+ex.getMessage()+"}");
        }catch (org.json.JSONException ex) {
                Logger.getLogger(QobuzObjectQuery.class.getName()).log(Level.SEVERE, null, ex+answer);
                //throw new QobuzAPIException(ex.getMessage(), ex);
                System.out.println(" *** ERROR : QobuzObjectQuery.getAnswer2 JSONException Answer: "+answer);
                return new JSONObject("{error: "+ex.getMessage()+"}");
        }
    }
}