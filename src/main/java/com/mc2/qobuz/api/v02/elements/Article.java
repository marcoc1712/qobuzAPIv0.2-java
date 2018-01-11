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


package com.mc2.qobuz.api.v02.elements;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.mc2.qobuz.api.v02.exceptions.QobuzAPIException;

/**
 *
 * @author marco
 */
public final class  Article extends QobuzObject  {

    public static final String ID = "id";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String LABEL = "label";
    public static final String TYPE = "type";
    public static final String URL = "url";
    public static final String CURRENCY = "currency";
    public static final String PROMOTION = "promotion";
    
    private Long id;
    private Double price;
    private String description;
    private String label;
    private String type;
    private String url;
    private String currency;
    private Promotion promotion;
    
     public Article() {
        super();
    }

    public Article (JSONObject jsonObject)throws QobuzAPIException {
        super(jsonObject);
         
        KeyList.add(ID);
        KeyList.add(PRICE);
        KeyList.add(DESCRIPTION);
        KeyList.add(LABEL);
        KeyList.add(TYPE);
        KeyList.add(URL);
        KeyList.add(CURRENCY);
        KeyList.add(PROMOTION);
        
        checkJSONObject(jsonObject);
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }
         try {
                id = jsonObject.getLong(ID);

                price = jsonObject.has(PRICE) ? 
                            jsonObject.isNull(PRICE) ? 
                            null : jsonObject.getDouble(PRICE) : null;
                
                description = jsonObject.has(DESCRIPTION) ? 
                            jsonObject.isNull(DESCRIPTION) ? 
                            null : jsonObject.getString(DESCRIPTION) : null;
                
                label = jsonObject.has(LABEL) ? 
                            jsonObject.isNull(LABEL) ? 
                            null : jsonObject.getString(LABEL) : null;
                                                
                type = jsonObject.has(TYPE) ? 
                            jsonObject.isNull(TYPE) ? 
                            null : jsonObject.getString(TYPE) : null;
                                                                
                url = jsonObject.has(URL) ? 
                            jsonObject.isNull(URL) ? 
                            null : jsonObject.getString(URL) : null;      
                
                currency = jsonObject.has(CURRENCY) ? 
                            jsonObject.isNull(CURRENCY) ? 
                            null : jsonObject.getString(CURRENCY) : null;
                
                promotion = jsonObject.has(PROMOTION) ? 
                        jsonObject.isNull(PROMOTION) ? 
                            null : new Promotion(jsonObject.getJSONObject(PROMOTION)) : null;
                
                
            } catch (JSONException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                throw new QobuzAPIException(ex.getMessage(), ex);
        }
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }
    
        /**
     * @return the promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }
}
