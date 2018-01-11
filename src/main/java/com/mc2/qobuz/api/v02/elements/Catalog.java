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
import com.mc2.qobuz.api.v02.lists.AlbumList;
import com.mc2.qobuz.api.v02.lists.ArtistList;
import com.mc2.qobuz.api.v02.lists.TrackList;

/**
 *
 * @author marco
 */
public final class Catalog extends QobuzObject{

    public static final String QUERY = "query";
    public static final String ARTISTS = "artists";
    public static final String TRACKS = "tracks";
    public static final String ALBUMS = "albums";
    public static final String PLAYLISTS = "playlists";
    public static final String ARTICLES = "articles";
    public static final String FOCUS = "focus";

    private String query;      
    private ArtistList artists;
    private TrackList tracks;
    private AlbumList albums;
    //private PlaylistList playlists;
    //private ArticleList articles;
    //private FocusList foci;
    
    public Catalog() {
        super();
    }

    public Catalog (JSONObject jsonObject)throws QobuzAPIException {
        super(jsonObject);
        
        KeyList.add(QUERY);
        KeyList.add(ARTISTS);
        KeyList.add(TRACKS);
        KeyList.add(ALBUMS);
        KeyList.add(PLAYLISTS);
        KeyList.add(ARTICLES);
        KeyList.add(FOCUS);
        
        checkJSONObject(jsonObject);
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }

        try {
                query = jsonObject.has(QUERY) ? 
                            jsonObject.isNull(QUERY) ? 
                            null : jsonObject.getString(QUERY) : null;
            
                artists = jsonObject.has(ARTISTS) ? 
                        jsonObject.isNull(ARTISTS) ? 
                            null : new ArtistList(jsonObject.getJSONObject(ARTISTS)) : null;
                
                tracks = jsonObject.has(TRACKS) ? 
                        jsonObject.isNull(TRACKS) ? 
                            null : new TrackList(jsonObject.getJSONObject(TRACKS)) : null;
                
                albums = jsonObject.has(ALBUMS) ? 
                        jsonObject.isNull(ALBUMS) ? 
                            null : new AlbumList(jsonObject.getJSONObject(ALBUMS)) : null;
                
                /*
                playlists = jsonObject.has(PLAYLISTS) ? 
                        jsonObject.isNull(PLAYLISTS) ? 
                            null : new PlaylistList(jsonObject.getJSONObject(PLAYLISTS)) : null;
                
                articles = jsonObject.has(ARTICLES) ? 
                        jsonObject.isNull(ARTICLES) ? 
                            null : new ArticleList(jsonObject.getJSONObject(ARTICLES)) : null;
                
                foci = jsonObject.has(FOCUS) ? 
                        jsonObject.isNull(FOCUS) ? 
                            null : new FocusList(jsonObject.getJSONObject(FOCUS)) : null;
                
                */
                
            } catch (JSONException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                throw new QobuzAPIException(ex.getMessage(), ex);
        }
    }
     /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @return the artists
     */
    public ArtistList getArtists() {
        return artists;
    }

    /**
     * @return the tracks
     */
    public TrackList getTracks() {
        return tracks;
    }

    /**
     * @return the albums
     */
    public AlbumList getAlbums() {
        return albums;
    }
  
    
    /**
     * @return the playlists
     */
    //public PlaylistList getPlaylists() {
    //    return playlists;
    //}
    
    /**
     * @return the playlists
     */
    //public ArticleList getArticles() {
    //    return articles;
    //}
    /**
     * @return the playlists
     */
    //public FocusList getFoci() {
    //    return foci;
    //}

}
