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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public final class Track extends QobuzObject {

    public static final String ID = "id"; //no
    public static final String TITLE = "title";
    public static final String WORK = "work"; 
    public static final String COMPOSER = "composer"; 
    public static final String PERFORMERS = "performers"; //no
    public static final String DURATION = "duration"; //no
    public static final String ARTICLES = "articles"; //no
    public static final String ALBUM = "album"; 
    public static final String PERFORMER = "performer"; 
    public static final String COPYRIGHT = "copyright";
    public static final String MEDIA_NUMBER = "media_number"; //no
    public static final String TRACK_NUMBER = "track_number"; //no TRACK_NO
    public static final String VERSION = "version"; //no
    public static final String PURCHASABLE = "purchasable";//no
    public static final String STREAMABLE = "streamable";//no
    public static final String PREVIEWABLE = "previewable";//no
    public static final String SAMPLEABLE = "sampleable";//no
    public static final String DOWNLOADABLE = "downloadable";//no
    public static final String DISPLAYABLE = "displayable";//no
    public static final String PURCHASABLE_AT = "purchasable_at";//no
    public static final String STREAMABLE_AT = "streamable_at";//no
    public static final String MAXIMUM_SAMPLING_RATE = "maximum_sampling_rate";//no
    public static final String MAXIMUM_BIT_DEPTH = "maximum_bit_depth";//no
    public static final String HIRES = "hires";//no
	public static final String ISRC = "isrc";
    
	public static final String TITLEONLY = "titleOnly"; //no
	public static final String WORKGUESSED = "workGuessed";//no
	
	/* 9/9/19 */
	public static final String PARENTAL_WARNING = "parental_warning";//no
	public static final String HIRES_STREAMABLE = "hires_streamable";//no
	public static final String MAXIMUM_CHANNEL_COUNT = "maximum_channel_count";//no
	public static final String ARTICLE_IDS ="article_ids"; //no
	
    private Long id;
    private String title;
    private String work;
	private String titleOnly;
	private String workGuessed;
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
	private String isrc;
    
    private ArrayList<Article> articles = new ArrayList<>();
	
	/* 9/9/19 */
	private Boolean hires_streamable;
	private Boolean parental_warning;
	private Long  maximum_channel_count;
	//private ArrayList<String> article_ids;
    
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
		KeyList.add(ISRC);

		KeyList.add(PARENTAL_WARNING);
        KeyList.add(HIRES_STREAMABLE);
        KeyList.add(MAXIMUM_CHANNEL_COUNT);
		KeyList.add(ARTICLE_IDS);
		
        checkJSONObject(jsonObject);
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }

        try {
                
				id = this.getLong(ID, jsonObject);
				title = this.getString(TITLE, jsonObject);
				work = this.getString(WORK, jsonObject);
				performers = this.getString(PERFORMERS, jsonObject);
                duration = this.getLong(DURATION, jsonObject);
				copyright = this.getString(COPYRIGHT, jsonObject);
				media_number = this.getLong(MEDIA_NUMBER, jsonObject);
				track_number = this.getLong(TRACK_NUMBER, jsonObject);
				purchasable= this.getBoolean(PURCHASABLE, jsonObject);
				streamable= this.getBoolean(STREAMABLE, jsonObject);
				previewable= this.getBoolean(PREVIEWABLE, jsonObject);
				sampleable= this.getBoolean(SAMPLEABLE, jsonObject);
				downloadable= this.getBoolean(DOWNLOADABLE, jsonObject);
				displayable= this.getBoolean(DISPLAYABLE, jsonObject);
				purchasable_at = this.getDate(PURCHASABLE_AT, jsonObject);
				streamable_at = this.getDate(STREAMABLE_AT, jsonObject);
				maximum_sampling_rate = this.getDouble(MAXIMUM_SAMPLING_RATE, jsonObject);
                maximum_bit_depth = this.getLong(MAXIMUM_BIT_DEPTH, jsonObject);
                hires = this.getBoolean(HIRES, jsonObject);
                isrc = this.getString(ISRC, jsonObject);
                hires_streamable = this.getBoolean(HIRES_STREAMABLE, jsonObject);
				parental_warning = this.getBoolean(PARENTAL_WARNING, jsonObject);
				maximum_channel_count= this.getLong(MAXIMUM_CHANNEL_COUNT, jsonObject);
				version = this.getString(VERSION, jsonObject);
				
				/*
                version = jsonObject.has(VERSION) ? 
                            jsonObject.isNull(VERSION) ? 
                            null :  jsonObject.get(VERSION).toString() : null;
                */
				
				titleOnly =calcTitleOnly(workGuessed,title);
				//rawKeyValuePair.put(TITLEONLY, titleOnly);
				
				workGuessed= workFromTitle(work,title);
				//rawKeyValuePair.put(WORKGUESSED, workGuessed);
				
				if (jsonObject.has(ALBUM)&& !jsonObject.isNull(ALBUM)){
					album =  new Album(jsonObject.getJSONObject(ALBUM));
					rawKeyValuePair.put(ALBUM, album.getTitle());
				}
				if (jsonObject.has(COMPOSER)&& !jsonObject.isNull(COMPOSER)){
					composer =  new Artist(jsonObject.getJSONObject(COMPOSER));
					rawKeyValuePair.put(COMPOSER, composer.getName());
				}
				
				if (jsonObject.has(PERFORMER)&& !jsonObject.isNull(PERFORMER)){
					performer =  new Artist(jsonObject.getJSONObject(PERFORMER));
					rawKeyValuePair.put(PERFORMER, performer.getName());
				}
				if (jsonObject.has(ARTICLES)) {
                    JSONArray jArticles = jsonObject.getJSONArray(ARTICLES);
                    for (int i = 0; i < jArticles.length(); i++) {
                        articles.add(new Article(jArticles.getJSONObject(i)));
                    }
                }
				/*
				if (jsonObject.has(ARTICLE_IDS)) {
                    JSONArray jIds = jsonObject.getJSONArray(ARTICLE_IDS);
                    for (int i = 0; i < jIds.length(); i++) {
                        article_ids.add(jIds.getString(i));
                    }
                }
				*/
				
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
     * @return the work stored in work field or guessed from the title-.
     */
    public String geWorkGuessed() {
       return workGuessed;
	}
	/**
     * @return the title cleaned by the work part as per in workGuessed.
     */
    public String getTitleOnly() {
       return titleOnly;
	}
    /**
     * @return the composer
     */
    public Artist getComposer() {
        return composer;
    }

    /**
     * @return the performers as a string
     */
    public String getPerformers() {
        return performers;
    }
	/**
     * @return the performers as a list of strings
     */
	public ArrayList<String> getPerformerList(){
        
        ArrayList<String> out=new ArrayList<>();
        		
        if (this.getPerformers() == null) return out;
        if (this.getPerformers().isEmpty()) return out;
        
        if (!this.getPerformers().contains("-")){
            
			out.add(this.getPerformers());
        
		} else {
        
			String[] parts = this.getPerformers().split(" - ");

			for (String part : parts){
				out.add(part);
			}
		}

        return out; 
    }
	/**
     * @return the performers as a map of struing (role and performer name)
     */
	public Map<String,String> getPerformersMap(){
        
        Map<String,String> performersMap = new HashMap<>();
		
        if (this.getPerformerList().isEmpty()) return performersMap;
        
		for (String perf : this.getPerformerList()){
		
			String[] parts = perf.split(", ");
			
			if (parts.length > 1){
			
				String value = parts[0].trim();
				
				for (int i = 1; i < parts.length; i++) {
					
					String key = parts[i].trim();
					
					if (performersMap.containsKey(key)){
						performersMap.put(key, performersMap.get(key)+", "+value);
					} else{
						performersMap.put(key, value);
					}
				}
			} else if (parts.length == 1)	{
				
				String value = parts[0].trim();
				performersMap.put("Performer", value);
			}		
		}

        return performersMap; 
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
     * @return the isrc
     */
    public String getIsrc() {
        return isrc;
    }
	
    /**
     * @return the articles
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }
	
	/**
     * @return the hires streamable
     */
    public Boolean getHiresStreamable() {
        return hires_streamable;
    }
	
	 /**
     * @return the parental warning
     */
    public Boolean getParentalWarning() {
        return parental_warning;
    }
	
	/**
     * @return the maximum_channel_count
     */
    public Long getMaximumChannelCount() {
        return maximum_channel_count;
    }
	
	private String workFromTitle(String work, String title){
		
		if (work != null && !"".equals(work)) return work;
		if  (title == null || "".equals(title)) return "";
		
		if (title.split(":").length > 1){
		
			return 	title.split(":")[0].trim();
		
		}
		
		return "";
	}
	
	private String calcTitleOnly(String work, String title){
		
		/*
		Work: Cello Sonata in B-Flat Major, RV 46
		Title: Cello Sonata in B-Flat Major, RV 46: I. Preludio (Largo)
		Title only: I. Preludio (Largo)
		
		Work: Cello Sonata in B-Flat Major, RV 46
		Title: Cello Sonata in B-Flat Major, RV 46 : Cello Sonata in B-Flat Major, RV 46: I. Preludio (Largo)
		Title only: I. Preludio (Largo)
		*/
		
		if  (title == null || "".equals(title)) return "";
		if (work == null || "".equals(work)) return title;
		
		String out="";
	
		for (String el :  title.split(":")){
		
			if (!el.trim().toUpperCase().equals(work.trim().toUpperCase())){
				
				if (! out.isEmpty()) out= out.concat(" : ");
				out= out.concat(el.trim());
			}
		}
		if (out.isEmpty()) return title;
		return out;
	}
	
	/**
     * @return the article_Ids (not used)
     
    public List<String> getArticleIds() {
        return article_ids;
    }
	**/
}

