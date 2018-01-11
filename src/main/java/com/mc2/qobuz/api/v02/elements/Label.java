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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.mc2.qobuz.api.v02.exceptions.QobuzAPIException;
import com.mc2.qobuz.api.v02.lists.AlbumList;
import com.mc2.qobuz.api.v02.query.LabelGet;
import com.mc2.qobuz.api.v02.utils.JsonUtils;

/**
 *
 * @author marco
 */
public final class  Label extends QobuzObject{  

    public static final String ID = "id";
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String ALBUM_COUNT = "albums_count";
    public static final String NAME = "name";
    public static final String SLUG = "slug";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String ALBUMS = "albums";
    
    private Long id;
    private Long supplier_id;
    private Long albums_count;
    private String name;
    private String slug;
    private String description;
    private URL image;
    private AlbumList albums;
    
    public Label() {
        super();
    }

    public Label (JSONObject jsonObject)throws QobuzAPIException {
        super(jsonObject);
        
        KeyList.add(ID);
        KeyList.add(SUPPLIER_ID);
        KeyList.add(ALBUM_COUNT);
        KeyList.add(NAME);
        KeyList.add(SLUG);
        KeyList.add(DESCRIPTION);
        KeyList.add(IMAGE);
        KeyList.add(ALBUMS);
        
        checkJSONObject(jsonObject);
        
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }

        try {
                id = jsonObject.getLong(ID);
                name = jsonObject.getString(NAME);
                slug = jsonObject.optString(SLUG);
                
                supplier_id = jsonObject.has(SUPPLIER_ID) ? 
                        jsonObject.isNull(SUPPLIER_ID) ? 
                            null : jsonObject.getLong(SUPPLIER_ID) : null; 
                
                albums_count = jsonObject.has(ALBUM_COUNT) ? 
                        jsonObject.isNull(ALBUM_COUNT) ? 
                            null : jsonObject.getLong(ALBUM_COUNT) : null;
                
                description = jsonObject.has(DESCRIPTION) ? 
                            jsonObject.isNull(DESCRIPTION) ? 
                            null : jsonObject.getString(DESCRIPTION) : null;
                
                image = JsonUtils.getURL(jsonObject, IMAGE);
                                
                albums = jsonObject.has(ALBUMS) ? 
                        jsonObject.isNull(ALBUMS) ? 
                            null : new AlbumList(jsonObject.getJSONObject(ALBUMS)) : null;

            } catch (JSONException ex) {
                System.out.println(jsonObject.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                throw new QobuzAPIException(ex.getMessage(), ex);
        }
    }
    public boolean isAlbumlistComplete(){
        
        if (albums == null) return true; //no album to get.
        
        // API BUG: sometime reported total is different than the actual number
        // of records, forget it and get all that we have.
        
        //return (albums.getItems().size()>= albums.getTotal());
        return false;
    }
    public void completeAlbumList() throws QobuzAPIException{

        long size = 0;
        if (albums != null){
            
            size = albums.getItems().size();
        }
        boolean stop = (albums == null); //no albums.
        
        while (!stop) {

            loadNextAlbumsPage();
            stop = (albums.getItems().size() == size); 
            size = albums.getItems().size();
            
        } 
    }
    public AlbumList loadNextAlbumsPage() throws QobuzAPIException{
    
        if (!isAlbumlistComplete()){
            
            AlbumList extra = getNexAlbumsPage();
            
            albums.getItems().addAll(extra.getItems());
            return extra;
        }
        return null;
    }
    private AlbumList getNexAlbumsPage() throws QobuzAPIException{
    
        if (!isAlbumlistComplete()) {
            
            LabelGet q = new LabelGet(id, (long)albums.getItems().size());
            Label extra = q.getLabel();
            
            return extra.getAlbums();
        }
        return null;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @return the supplier_id
     */
    public Long getSupplier_id() {
        return supplier_id;
    }
    /**
     * @return the albums_count
     */
    public Long getAlbums_count() {
        return albums_count;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }
        /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the image
     */
    public URL getImage() {
        return image;
    }

    /**
     * @return the albums
     */
    public AlbumList getAlbums() {
        return albums;
    }
}
