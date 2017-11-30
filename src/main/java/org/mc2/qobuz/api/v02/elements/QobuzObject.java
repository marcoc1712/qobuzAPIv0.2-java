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
package org.mc2.qobuz.api.v02.elements;

import org.mc2.qobuz.api.v02.exceptions.QobuzAPIException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QobuzObject {
        
    protected List<String> KeyList= new ArrayList();
    private static Logger log = Logger.getLogger(QobuzObject.class.getName());
    
    public QobuzObject() {
       
    }

    public  QobuzObject (JSONObject jsonObject) throws QobuzAPIException {
      
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }
    }
   protected final void checkJSONObject(JSONObject jsonObject){
        
       for ( Iterator<String> keys = jsonObject.keys(); keys.hasNext(); ) {
          String key= keys.next();
          
          //System.out.println(key);
          
          if (KeyList.contains(key) || key.isEmpty())continue;
          log.log(Level.WARNING, "Unrecognised element in result object: {0}",key);
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof QobuzObject)) {
            return false;
        }
        return super.equals(obj);
    }
}