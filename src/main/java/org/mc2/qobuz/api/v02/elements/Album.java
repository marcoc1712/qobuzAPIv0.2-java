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

import org.mc2.qobuz.api.v02.lists.TrackList;
import org.mc2.qobuz.api.v02.exceptions.QobuzAPIException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import org.mc2.qobuz.api.v02.query.AlbumGet;
import org.mc2.qobuz.api.v02.utils.JsonUtils;

public final class Album extends QobuzObject {
    
    public static final String ID = "id";
    public static final String TITLE = "title";
    
    public static final String AREA = "area";
    public static final String ARTICLES = "articles";
    public static final String ARTIST = "artist";
    public static final String AWARDS = "awards";
    
    public static final String CATCHLINE = "catchline";
    public static final String COLLECTION = "collection";
    public static final String COMPOSER = "composer";
    public static final String COPYRIGHT = "copyright";
    public static final String CREATED_AT = "created_at";
    
    public static final String DESCRIPTION = "description";
    public static final String DISPLAYABLE = "displayable";
    public static final String DOWNLOADABLE = "downloadable";
    public static final String DURATION = "duration";

    public static final String GENRE = "genre";
    public static final String GENRES_LIST = "genres_list";
    public static final String GOODIES = "goodies";
    
    public static final String HIRES = "hires";
     
    public static final String IMAGE = "image";
    public static final String INSTRUMENT = "instrument";
    
    public static final String LABEL = "label";
    
    public static final String MAXIMUM_BIT_DEPTH = "maximum_bit_depth";
    public static final String MAXIMUM_SAMPLING_RATE = "maximum_sampling_rate";
    public static final String MAXIMUM_TECHNICAL_SPECIFICATIONS = "maximum_technical_specifications";
    public static final String MEDIA_COUNT = "media_count";
    
    public static final String PERIOD = "period";
    public static final String POPULARITY = "popularity";
    public static final String PREVIEWABLE = "previewable";
    public static final String PRODUCT_TYPE = "product_type";
    public static final String PRODUCT_SALES_FACTORS_MONTHLY  = "product_sales_factors_monthly";
    public static final String PRODUCT_SALES_FACTORS_WEEKLY = "product_sales_factors_weekly";
    public static final String PRODUCT_SALES_FACTORS_YEARLY = "product_sales_factors_yearly";
    public static final String PRODUCT_URL = "product_url";
    public static final String PROGRAM = "program";
    public static final String PURCHASABLE = "purchasable";
    public static final String PURCHASABLE_AT = "purchasable_at";

    public static final String QOBUZ_ID = "qobuz_id";
     
    public static final String RECORDING_INFORMATION = "recording_information";
    public static final String RELATIVE_URL = "relative_url";
    public static final String RELEASED_AT = "released_at";
    
    public static final String SAMPLEABLE = "sampleable";
    public static final String SLUG = "slug";
    public static final String STORE_RELATED = "store_related";
    public static final String STREAMABLE = "streamable";
    public static final String STREAMABLE_AT = "streamable_at";
    
    public static final String TRACKS = "tracks"; 
    public static final String TRACKSCOUNT = "tracks_count";
    
    public static final String URL = "url";
 
    private String id;
    private String title;
    
    private String slug;
    private Long tracks_count;
    private String recording_information;
    private Double product_sales_factors_weekly;
    private String description;
    private String catchline;
    private Long created_at;
    private Double product_sales_factors_monthly;
    private Double popularity;
    private Long  media_count;
    private String product_type;
    private String copyright;
    private String product_url;
    private URL url;
    private Long  duration;
    private Long  released_at;
    private String maximum_technical_specifications;
    private String program;
    private String relative_url;
    private Long  qobuz_id;
    private Double product_sales_factors_yearly;
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

    private Genre genre;
    private Collection collection;
    private Artist composer;
    private Area area;
    private Artist artist;
    private Image image;
    private Instrument instrument;
    private Label label;
    private Period period;
    private TrackList tracks;
    
    private ArrayList<Goody> goodies = new ArrayList<>();
    private ArrayList<String> genres_list = new ArrayList<>();
    private ArrayList<Article> articles = new ArrayList<>();
    private ArrayList<StoreRelated> store_related = new ArrayList<>();
    private ArrayList<Award> awards = new ArrayList<>();
    
     public  Album() {
        super();
     }
     
     public  Album(JSONObject jsonObject) throws QobuzAPIException {
        super(jsonObject);
        
        // fill the KeyList here;
        
        KeyList.add(ID);
        KeyList.add(TITLE);
        KeyList.add(SLUG);
        
        KeyList.add(GENRE);
        KeyList.add(TRACKSCOUNT);
        KeyList.add(RECORDING_INFORMATION);
        KeyList.add(COLLECTION);
        KeyList.add(PRODUCT_SALES_FACTORS_WEEKLY);
        KeyList.add(GOODIES);
        KeyList.add(COMPOSER);
        KeyList.add(AREA);
        KeyList.add(DESCRIPTION);
        KeyList.add(CATCHLINE);
        KeyList.add(CREATED_AT);
        KeyList.add(GENRES_LIST);
        KeyList.add(ARTIST);
        KeyList.add(PRODUCT_SALES_FACTORS_MONTHLY);
        KeyList.add(POPULARITY);
        KeyList.add(ARTICLES);
        KeyList.add(STORE_RELATED);
        KeyList.add(IMAGE);
        KeyList.add(MEDIA_COUNT);
        KeyList.add(PRODUCT_TYPE);
        KeyList.add(LABEL);
        KeyList.add(INSTRUMENT);
        KeyList.add(COPYRIGHT);
        KeyList.add(PERIOD);
        KeyList.add(PRODUCT_URL);
        KeyList.add(URL);
        KeyList.add(DURATION);
        KeyList.add(RELEASED_AT);
        KeyList.add(MAXIMUM_TECHNICAL_SPECIFICATIONS);
        KeyList.add(AWARDS);
        KeyList.add(PROGRAM);
        KeyList.add(RELATIVE_URL);
        KeyList.add(QOBUZ_ID);
        KeyList.add(PRODUCT_SALES_FACTORS_YEARLY);
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
        KeyList.add(TRACKS);
        
        
        checkJSONObject(jsonObject);
        /**
         * If there is no parameter there is no need to go further
         */
        if (jsonObject == null) {
            throw new NullPointerException();
        }

        try {
                id = jsonObject.getString(ID);
                title = jsonObject.getString(TITLE);
                
                slug = jsonObject.has(SLUG) ? 
                            jsonObject.isNull(SLUG) ? 
                            null : jsonObject.getString(SLUG) : null;
                
                genre = jsonObject.has(GENRE) ? 
                        jsonObject.isNull(GENRE) ? 
                            null : new Genre(jsonObject.getJSONObject(GENRE)) : null;
                
                tracks_count = jsonObject.has(TRACKSCOUNT) ? 
                        jsonObject.isNull(TRACKSCOUNT) ? 
                            null : jsonObject.getLong(TRACKSCOUNT) : null;
                
                recording_information = jsonObject.has(RECORDING_INFORMATION) ? 
                            jsonObject.isNull(RECORDING_INFORMATION) ? 
                            null : jsonObject.getString(RECORDING_INFORMATION) : null;

                collection = jsonObject.has(COLLECTION) ? 
                        jsonObject.isNull(COLLECTION) ? 
                            null : new Collection(jsonObject.getJSONObject(COLLECTION)) : null;
                
                product_sales_factors_weekly = jsonObject.has(PRODUCT_SALES_FACTORS_WEEKLY) ? 
                        jsonObject.isNull(PRODUCT_SALES_FACTORS_WEEKLY) ? 
                            null : jsonObject.getDouble(PRODUCT_SALES_FACTORS_WEEKLY) : null;
                
                if (jsonObject.has(GOODIES)) {
                    JSONArray jGoodies = jsonObject.getJSONArray(GOODIES);
                    for (int i = 0; i < jGoodies.length(); i++) {
                        goodies.add(new Goody(jGoodies.getJSONObject(i)));
                    }
                }
                
                composer = jsonObject.has(COMPOSER) ? 
                        jsonObject.isNull(COMPOSER) ? 
                            null : new Artist(jsonObject.getJSONObject(COMPOSER)) : null;
                
                area = jsonObject.has(AREA) ? 
                        jsonObject.isNull(AREA) ? 
                            null : new Area(jsonObject.getJSONObject(AREA)) : null;
                
                description = jsonObject.has(DESCRIPTION) ? 
                            jsonObject.isNull(DESCRIPTION) ? 
                            null : jsonObject.getString(DESCRIPTION) : null;
                
                catchline = jsonObject.has(CATCHLINE) ? 
                            jsonObject.isNull(CATCHLINE) ? 
                            null : jsonObject.getString(CATCHLINE) : null;
                
                created_at = jsonObject.has(CREATED_AT) ? 
                        jsonObject.isNull(CREATED_AT) ? 
                            null : jsonObject.getLong(CREATED_AT) : null;
                
                if (jsonObject.has(GENRES_LIST)) {
                    JSONArray jGenres = jsonObject.getJSONArray(GENRES_LIST);
                    for (int i = 0; i < jGenres.length(); i++) {
                        genres_list.add(jGenres.getString(i));
                    }
                }
                
                artist = jsonObject.has(ARTIST) ? 
                        jsonObject.isNull(ARTIST) ? 
                            null : new Artist(jsonObject.getJSONObject(ARTIST)) : null;
                 
                product_sales_factors_monthly = jsonObject.has(PRODUCT_SALES_FACTORS_MONTHLY) ? 
                        jsonObject.isNull(PRODUCT_SALES_FACTORS_MONTHLY) ? 
                            null : jsonObject.getDouble(PRODUCT_SALES_FACTORS_MONTHLY) : null;
                
                popularity = jsonObject.has(POPULARITY) ? 
                        jsonObject.isNull(POPULARITY) ? 
                            null : jsonObject.getDouble(POPULARITY) : null;
                
                if (jsonObject.has(ARTICLES)) {
                    JSONArray jArticles = jsonObject.getJSONArray(ARTICLES);
                    for (int i = 0; i < jArticles.length(); i++) {
                        articles.add(new Article(jArticles.getJSONObject(i)));
                    }
                }
                if (jsonObject.has(STORE_RELATED)) {
                    
                    JSONObject store = jsonObject.getJSONObject(STORE_RELATED);
                    for(int i = 0; i<store.names().length(); i++){
                        
                        String key = store.names().getString(i);
                        store_related.add(new StoreRelated(key, store.getJSONObject(key)));
                    }
                }
                image = jsonObject.has(IMAGE) ? 
                        jsonObject.isNull(IMAGE) ? 
                            null : new Image(jsonObject.getJSONObject(IMAGE)) : null;
                
                media_count = jsonObject.has(MEDIA_COUNT) ? 
                        jsonObject.isNull(MEDIA_COUNT) ? 
                            null : jsonObject.getLong(MEDIA_COUNT) : null;
                
                product_type = jsonObject.has(PRODUCT_TYPE) ? 
                            jsonObject.isNull(PRODUCT_TYPE) ? 
                            null : jsonObject.getString(PRODUCT_TYPE) : null;
                
                label = jsonObject.has(LABEL) ? 
                        jsonObject.isNull(LABEL) ? 
                            null : new Label(jsonObject.getJSONObject(LABEL)) : null;
                
                instrument = jsonObject.has(INSTRUMENT) ? 
                        jsonObject.isNull(INSTRUMENT) ? 
                            null : new Instrument(jsonObject.getJSONObject(INSTRUMENT)) : null;
                
                copyright = jsonObject.has(COPYRIGHT) ? 
                            jsonObject.isNull(COPYRIGHT) ? 
                            null : jsonObject.getString(COPYRIGHT) : null;
                
                period = jsonObject.has(PERIOD) ? 
                        jsonObject.isNull(PERIOD) ? 
                            null : new Period(jsonObject.getJSONObject(PERIOD)) : null;
                
                product_url = jsonObject.has(PRODUCT_URL) ? 
                            jsonObject.isNull(PRODUCT_URL) ? 
                            null : jsonObject.getString(PRODUCT_URL) : null;
                             
                url = JsonUtils.getURL(jsonObject, URL);
                                
                duration = jsonObject.has(DURATION) ? 
                            jsonObject.isNull(DURATION) ? 
                            null : jsonObject.getLong(DURATION) : null;

                released_at = jsonObject.has(RELEASED_AT) ? 
                            jsonObject.isNull(RELEASED_AT) ? 
                            null : jsonObject.getLong(RELEASED_AT) : null;
                
                maximum_technical_specifications = jsonObject.has(MAXIMUM_TECHNICAL_SPECIFICATIONS) ? 
                            jsonObject.isNull(MAXIMUM_TECHNICAL_SPECIFICATIONS) ? 
                            null : jsonObject.getString(MAXIMUM_TECHNICAL_SPECIFICATIONS) : null;
                
                if (jsonObject.has(AWARDS)) {
                    JSONArray jAwards = jsonObject.getJSONArray(AWARDS);
                    for (int i = 0; i < jAwards.length(); i++) {
                        awards.add(new Award(jAwards.getJSONObject(i)));
                    }
                }
                
                program = jsonObject.has(PROGRAM) ? 
                            jsonObject.isNull(PROGRAM) ? 
                            null : jsonObject.getString(PROGRAM) : null;
                
                slug = jsonObject.has(SLUG) ? 
                            jsonObject.isNull(SLUG) ? 
                            null : jsonObject.getString(SLUG) : null;
                                
                relative_url = jsonObject.has(RELATIVE_URL) ? 
                            jsonObject.isNull(RELATIVE_URL) ? 
                            null : jsonObject.getString(RELATIVE_URL) : null;
                
                qobuz_id = jsonObject.has(QOBUZ_ID) ? 
                            jsonObject.isNull(QOBUZ_ID) ? 
                            null : jsonObject.getLong(QOBUZ_ID) : null;
                
                product_sales_factors_yearly= jsonObject.has(PRODUCT_SALES_FACTORS_YEARLY) ? 
                            jsonObject.isNull(PRODUCT_SALES_FACTORS_YEARLY) ? 
                            null : jsonObject.getDouble(PRODUCT_SALES_FACTORS_YEARLY) : null;
                
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
                
                tracks = jsonObject.has(TRACKS) ? 
                        jsonObject.isNull(TRACKS) ? 
                            null : new TrackList(jsonObject.getJSONObject(TRACKS)) : null;
                        
        } catch (JSONException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new QobuzAPIException(ex.getMessage(), ex);
        }
   
    }
    public boolean isTracklistComplete(){
        
        if (tracks == null) return true; // no tracks to get.
        
        // API BUG: sometime reported total is different than the actual number
        // of records.
        
        // forget total if lesser than count (costs at least 1 more api call)
        if (tracks_count > tracks.getTotal()) return false;
        
        //Stop at total.
        return (tracks == null || tracks.getItems().size()>= tracks.getTotal());
    }
    public void completeTrackList() throws QobuzAPIException{
        
        long size = 0;
        if (tracks != null){
            
            size = tracks.getItems().size();
        }
        
        // API BUG: sometime reported total is wrong.
        // this avoid a neverending loop if total is greater than 
        // the actual number of records.
        
        boolean stop=false;
        
        while (!stop && !isTracklistComplete()) {
            
            loadNextTracksPage();
            stop = (tracks.getItems().size() == size); 
            size = tracks.getItems().size();
        } 
    }
    
    private void loadNextTracksPage() throws QobuzAPIException{
    
        if (!isTracklistComplete()){
            
            ArrayList<Track> extra = getNextTracksPage();
            tracks.getItems().addAll(extra);
        }
        
    }
    private ArrayList<Track> getNextTracksPage() throws QobuzAPIException{
    
        if (!isTracklistComplete()) {
            
            AlbumGet q = new AlbumGet(id, (long)tracks.getItems().size());
            Album extra = q.getAlbum();
            
            return extra.getTracks().getItems();
        }
        return new ArrayList<>();
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
     /**
     * @return the composer
     */
    public Artist getComposer() {
        return composer;
    }
    
    /**
     * @return the artist
     */
    public Artist getArtist() {
        return artist;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }
    /**
     * @return the genres_list
     */
    public ArrayList<String> getGenres_list() {
        return genres_list;
    }
    /**
     * @return the store_related
     */
    public ArrayList<StoreRelated> getStore_related() {
        return store_related;
    }
    /**
     * @return the instrument
     */
    public Instrument getInstrument() {
        return instrument;
    }
    /**
     * @return the period
     */
    public Period getPeriod() {
        return period;
    }
    /**
     * @return the area
     */
    public Area getArea() {
        return area;
    }
    /**
     * @return the recording_information
     */
    public String getRecording_information() {
        return recording_information;
    }
    /**
     * @return the label
     */
    public Label getLabel() {
        return label;
    }
    /**
     * @return the released_at
     */
    public Long getReleased_at() {
        return released_at;
    }
    /**
     * @return the copyright
     */
    public String getCopyright() {
        return copyright;
    }
    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }
    /**
     * @return the collection
     */
    public Collection getCollection() {
        return collection;
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
     * @return the maximum_technical_specifications
     */
    public String getMaximum_technical_specifications() {
        return maximum_technical_specifications;
    }
    
    /**
     * @return the awards
     */
    public ArrayList<Award> getAwards() {
        return awards;
    }
    
     /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the catchline
     */
    public String getCatchline() {
        return catchline;
    }

    /**
     * @return the created_at
     */
    public Long getCreated_at() {
        return created_at;
    }
    
/**
     * @return the product_type
     */
    public String getProduct_type() {
        return product_type;
    }

    /**
     * @return the product_url
     */
    public String getProduct_url() {
        return product_url;
    }

    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }
    
    /**
     * @return the relative_url
     */
    public String getRelative_url() {
        return relative_url;
    }

    /**
     * @return the qobuz_id
     */
    public Long getQobuz_id() {
        return qobuz_id;
    }
    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }
    
    /**
     * @return the popularity
     */
    public Double getPopularity() {
        return popularity;
    }
    /**
     * @return the product_sales_factors_weekly
     */
    public Double getProduct_sales_factors_weekly() {
        return product_sales_factors_weekly;
    }
    /**
     * @return the product_sales_factors_monthly
     */
    public Double getProduct_sales_factors_monthly() {
        return product_sales_factors_monthly;
    }
    /**
     * @return the product_sales_factors_yearly
     */
    public Double getProduct_sales_factors_yearly() {
        return product_sales_factors_yearly;
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
     * @return the articles
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }

    /**
     * @return the goodies
     */
    public ArrayList<Goody> getGoodies() {
        return goodies;
    }

    /**
     * @return the media_count
     */
    public Long getMedia_count() {
        return media_count;
    }
    /**
     * @return the tracks_count
     */
    public Long getTracks_count() {
        return tracks_count;
    }
    
    /**
     * @return the duration
     */
    public Long getDuration() {
        return duration;
    }
    
    /**
     * @return the tracks
     */
    public TrackList getTracks() {
        return tracks;
    }

}

