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

import java.util.ArrayList;
import com.mc2.qobuz.api.v02.exceptions.QobuzAPIException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public final class Track extends QobuzObject {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String WORK = "work"; 
    public static final String COMPOSER = "composer"; 
    public static final String PERFORMERS = "performers"; 
    public static final String DURATION = "duration"; 
    public static final String ARTICLES = "articles";
    public static final String ALBUM = "album"; 
    public static final String PERFORMER = "performer"; 
    public static final String COPYRIGHT = "copyright";
    public static final String MEDIA_NUMBER = "media_number";
    public static final String TRACK_NUMBER = "track_number";
    public static final String VERSION = "version";
    public static final String PURCHASABLE = "purchasable";
    public static final String STREAMABLE = "streamable";
    public static final String PREVIEWABLE = "previewable";
    public static final String SAMPLEABLE = "sampleable";
    public static final String DOWNLOADABLE = "downloadable";
    public static final String DISPLAYABLE = "displayable";
    public static final String PURCHASABLE_AT = "purchasable_at";
    public static final String STREAMABLE_AT = "streamable_at";
    public static final String MAXIMUM_SAMPLING_RATE = "maximum_sampling_rate";
    public static final String MAXIMUM_BIT_DEPTH = "maximum_bit_depth";
    public static final String HIRES = "hires";
    
    private Long id;
    private String title;
    private String work;
    private Artist composer;
    private String performers;
    private Long  duration;
    private Album album;
    private Artist performer;
    private String copyright;
    private Long  media_number;
    private Long  track_number;
    private String version;
    private Boolean purchasable;
    private Boolean streamable;
    private Boolean previewable;
    private Boolean sampleable;
    private Boolean downloadable;
    private Boolean displayable;
    private Long  purchasable_at;
    private Long  streamable_at;
    private Double maximum_sampling_rate;
    private Long  maximum_bit_depth;
    private Boolean hires;
    
    private ArrayList<Article> articles = new ArrayList<>();
    
     public  Track() {
        super();
     }
     
     public  Track(JSONObject jsonObject) throws QobuzAPIException {
        super(jsonObject);
        
        // fill the KeyList here;
        
        KeyList.add(ID);
        KeyList.add(TITLE);
        KeyList.add(WORK);
        KeyList.add(COMPOSER);
        KeyList.add(PERFORMERS);
        KeyList.add(DURATION);
        KeyList.add(ALBUM);
        KeyList.add(ARTICLES);
        KeyList.add(PERFORMER);
        KeyList.add(COPYRIGHT);
        KeyList.add(MEDIA_NUMBER);
        KeyList.add(TRACK_NUMBER);
        KeyList.add(VERSION);
        KeyList.add(PURCHASABLE);
        KeyList.add(STREAMABLE);
        KeyList.add(PREVIEWABLE);
        KeyList.add(SAMPLEABLE);
        KeyList.add(DOWNLOADABLE);
        KeyList.add(DISPLAYABLE);
        KeyList.add(PURCHASABLE_AT);
        KeyList.add(STREAMABLE_AT);
        KeyList.add(MAXIMUM_SAMPLING_RATE);
        KeyList.add(MAXIMUM_BIT_DEPTH);
        KeyList.add(HIRES);
        
        
        checkJSONObject(jsonObject);
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }

        try {
                id = jsonObject.getLong(ID);
                title = jsonObject.getString(TITLE);
                
                work = jsonObject.has(WORK) ? 
                            jsonObject.isNull(WORK) ? 
                            null : jsonObject.getString(WORK) : null;
                
                composer = jsonObject.has(COMPOSER) ? 
                        jsonObject.isNull(COMPOSER) ? 
                            null : new Artist(jsonObject.getJSONObject(COMPOSER)) : null;
                
                performers = jsonObject.has(PERFORMERS) ? 
                            jsonObject.isNull(PERFORMERS) ? 
                            null : jsonObject.getString(PERFORMERS) : null;
                
                duration = jsonObject.has(DURATION) ? 
                            jsonObject.isNull(DURATION) ? 
                            null : jsonObject.getLong(DURATION) : null;
                
                if (jsonObject.has(ARTICLES)) {
                    JSONArray jArticles = jsonObject.getJSONArray(ARTICLES);
                    for (int i = 0; i < jArticles.length(); i++) {
                        articles.add(new Article(jArticles.getJSONObject(i)));
                    }
                }
                
                album = jsonObject.has(ALBUM) ? 
                        jsonObject.isNull(ALBUM) ? 
                            null : new Album(jsonObject.getJSONObject(ALBUM)) : null;
                 
                performer = jsonObject.has(PERFORMER) ? 
                        jsonObject.isNull(PERFORMER) ? 
                            null : new Artist(jsonObject.getJSONObject(PERFORMER)) : null;
                
                copyright = jsonObject.has(COPYRIGHT) ? 
                            jsonObject.isNull(COPYRIGHT) ? 
                            null : jsonObject.getString(COPYRIGHT) : null;
                
                media_number = jsonObject.has(MEDIA_NUMBER) ? 
                            jsonObject.isNull(MEDIA_NUMBER) ? 
                            null : jsonObject.getLong(MEDIA_NUMBER) : null;
                                
                track_number = jsonObject.has(TRACK_NUMBER) ? 
                            jsonObject.isNull(TRACK_NUMBER) ? 
                            null : jsonObject.getLong(TRACK_NUMBER) : null;        
                                
                version = jsonObject.has(VERSION) ? 
                            jsonObject.isNull(VERSION) ? 
                            null :  jsonObject.get(VERSION).toString() : null;
                
                purchasable= jsonObject.has(PURCHASABLE) ? 
                            jsonObject.isNull(PURCHASABLE) ? 
                            false : jsonObject.getBoolean(PURCHASABLE) : null;
                
                streamable= jsonObject.has(STREAMABLE) ? 
                            jsonObject.isNull(STREAMABLE) ? 
                            false : jsonObject.getBoolean(STREAMABLE) : null;
                                
                previewable= jsonObject.has(PREVIEWABLE) ? 
                            jsonObject.isNull(PREVIEWABLE) ? 
                            false : jsonObject.getBoolean(PREVIEWABLE) : null;
                                
                sampleable= jsonObject.has(SAMPLEABLE) ? 
                            jsonObject.isNull(SAMPLEABLE) ? 
                            false : jsonObject.getBoolean(SAMPLEABLE) : null;
                                
                downloadable= jsonObject.has(DOWNLOADABLE) ? 
                            jsonObject.isNull(DOWNLOADABLE) ? 
                            false : jsonObject.getBoolean(DOWNLOADABLE) : null;
                               
                displayable= jsonObject.has(DISPLAYABLE) ? 
                            jsonObject.isNull(DISPLAYABLE) ? 
                            false : jsonObject.getBoolean(DISPLAYABLE) : null;
                
                purchasable_at = jsonObject.has(PURCHASABLE_AT) ? 
                            jsonObject.isNull(PURCHASABLE_AT) ? 
                            null : jsonObject.getLong(PURCHASABLE_AT) : null;
               
                streamable_at = jsonObject.has(STREAMABLE_AT) ? 
                            jsonObject.isNull(STREAMABLE_AT) ? 
                            null : jsonObject.getLong(STREAMABLE_AT) : null;
                
                maximum_sampling_rate= jsonObject.has(MAXIMUM_SAMPLING_RATE) ? 
                            jsonObject.isNull(MAXIMUM_SAMPLING_RATE) ? 
                            null : jsonObject.getDouble(MAXIMUM_SAMPLING_RATE) : null;
                        
                maximum_bit_depth= jsonObject.has(MAXIMUM_BIT_DEPTH) ? 
                            jsonObject.isNull(MAXIMUM_BIT_DEPTH) ? 
                            null : jsonObject.getLong(MAXIMUM_BIT_DEPTH) : null;
                
                hires= jsonObject.has(HIRES) ? 
                            jsonObject.isNull(HIRES) ? 
                            false : jsonObject.getBoolean(HIRES) : null;
                
                        
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the work
     */
    public String getWork() {
        return work;
    }
    /**
     * @return the composer
     */
    public Artist getComposer() {
        return composer;
    }

    /**
     * @return the performers
     */
    public String getPerformers() {
        return performers;
    }

    /**
     * @return the duration
     */
    public Long getDuration() {
        return duration;
    }
    /**
     * @return the album
     */
    public Album getAlbum() {
        return album;
    }
    /**
     * @return the performer
     */
    public Artist getPerformer() {
        return performer;
    }

    /**
     * @return the copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * @return the media_number
     */
    public Long getMedia_number() {
        return media_number;
    }

    /**
     * @return the track_number
     */
    public Long getTrack_number() {
        return track_number;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the purchasable
     */
    public Boolean getPurchasable() {
        return purchasable;
    }

    /**
     * @return the streamable
     */
    public Boolean getStreamable() {
        return streamable;
    }

    /**
     * @return the previewable
     */
    public Boolean getPreviewable() {
        return previewable;
    }

    /**
     * @return the sampleable
     */
    public Boolean getSampleable() {
        return sampleable;
    }

    /**
     * @return the downloadable
     */
    public Boolean getDownloadable() {
        return downloadable;
    }

    /**
     * @return the displayable
     */
    public Boolean getDisplayable() {
        return displayable;
    }

    /**
     * @return the purchasable_at
     */
    public Long getPurchasable_at() {
        return purchasable_at;
    }

    /**
     * @return the streamable_at
     */
    public Long getStreamable_at() {
        return streamable_at;
    }

    /**
     * @return the maximum_sampling_rate
     */
    public Double getMaximum_sampling_rate() {
        return maximum_sampling_rate;
    }

    /**
     * @return the maximum_bit_depth
     */
    public Long getMaximum_bit_depth() {
        return maximum_bit_depth;
    }

    /**
     * @return the hires
     */
    public Boolean getHires() {
        return hires;
    }
    /**
     * @return the articles
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }
}

