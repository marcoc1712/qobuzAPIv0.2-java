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

package test.units;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.mc2.qobuz.api.v02.elements.Album;
import com.mc2.qobuz.api.v02.elements.Catalog;
import com.mc2.qobuz.api.v02.elements.Genre;
import com.mc2.qobuz.api.v02.elements.GenreListResult;
import com.mc2.qobuz.api.v02.elements.Label;
import com.mc2.qobuz.api.v02.elements.LabelListResult;
import com.mc2.qobuz.api.v02.elements.Track;
import com.mc2.qobuz.api.v02.lists.ArtistList;
import com.mc2.qobuz.api.v02.lists.TrackList;
import com.mc2.qobuz.api.v02.lists.AlbumList;
import com.mc2.qobuz.api.v02.lists.GenreList;
import com.mc2.qobuz.api.v02.lists.LabelList;
import com.mc2.qobuz.api.v02.query.AlbumsGetFeatured;
import com.mc2.qobuz.api.v02.query.GenreListGet;
import com.mc2.qobuz.api.v02.query.LabelListGet;
import com.mc2.qobuz.api.v02.query.AlbumGet;
import com.mc2.qobuz.api.v02.query.GenreGet;
import com.mc2.qobuz.api.v02.query.LabelGet;
import com.mc2.qobuz.api.v02.query.TrackGet;
import com.mc2.qobuz.api.v02.query.CatalogSearch;
import org.junit.Test;

import test.utils.TestUtils;

/**
 *
 * @author marco
 */
public class Others extends UnitTest {

    //@Test
    public void artistSearch() {
        
        //You could use this template to search any single type in the catalog.
        //It returns counts for all entites an in some case also items for type <> 
        //the required one.
        
        //In case of Artists it also reports Tracks.
        
        try {
                
                String [] typesToChooseFrom=CatalogSearch.suggestedTypes();
                //You could use anyone in the above array (time they add some new, so is just a suggestion) 
                // but to search artist you want to use 'artist'
                
                String type =typesToChooseFrom[0]; //albums
                String query = "Antonio Vivaldi"; //can't use an empty string here.
                
                CatalogSearch q = new CatalogSearch(query, type);
                Catalog catalog = q.getCatalog();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));

                System.out.println("======================================================================");
                System.out.println("= "+type.toUpperCase()+" SEARCH (with pagination)");
                System.out.println("======================================================================");

                Long totalArtist = (long)0;
                Long totalAlbum = (long)0;
                Long totalTrack = (long)0;

                ArtistList artists = catalog.getArtists();
                AlbumList albums = catalog.getAlbums();
                TrackList tracks = catalog.getTracks();
                
                System.out.println( "Query ("+type+"): "+catalog.getQuery());
                
                if (artists!= null) {
                    totalArtist=artists.getTotal();   
                }
                if (albums!= null) {
                    totalAlbum=albums.getTotal();
                }
                if (tracks!= null) {
                    totalTrack=tracks.getTotal(); 
                }
                
                System.out.println( "- Artists: "+totalArtist);
                System.out.println( "- Albums : "+totalAlbum);
                System.out.println( "- Tracks : "+totalTrack);
                
                System.out.println("");
                long size=0;
                
                if (artists!= null) {
                    System.out.println( "- ARTISTS: ");
                    TestUtils.printArtistList(artists,"   ");
                    long itemSize= artists.getItems().size();                   
                    size = itemSize > size ? itemSize : size;
                }
                if (albums!= null) {
                    System.out.println( "- ALBUMS: ");
                    TestUtils.printAlbumList(albums,"   ");
                    long itemSize= albums.getItems().size();
                    size = itemSize > size ? itemSize : size;
                }
                if (tracks!= null) {
                    System.out.println( "- TRACKS: ");
                    TestUtils.printTrackList(tracks,"   ");
                    long itemSize= tracks.getItems().size();
                    size = itemSize > size ? itemSize : size;
                }
                
                int page=1;
                
                boolean stop = (artists == null && albums == null && tracks == null);

                while (!stop ) {
                                       
                    System.out.println("= "+page+" =");
                    
                    long offset= size;
                    
                    q = new CatalogSearch(query, type, offset);
                    Catalog extra = q.getCatalog();
                    
                    if (artists!= null) {
                        artists.getItems().addAll(extra.getArtists().getItems());
                        System.out.println( "- ARTISTS: ");
                        TestUtils.printArtistList(extra.getArtists(),"   ");
                        long itemSize= artists.getItems().size();                   
                        size = itemSize > size ? itemSize : size;
                    }
                    if (albums!= null) {
                       albums.getItems().addAll(extra.getAlbums().getItems());
                       System.out.println( "- ALBUMS: ");
                       TestUtils.printAlbumList(extra.getAlbums(),"   ");
                       
                       long itemSize= albums.getItems().size();
                       size = itemSize > size ? itemSize : size;
                    }
                    if (tracks!= null) {
                       tracks.getItems().addAll(extra.getTracks().getItems());
                       System.out.println( "- TRACKS: ");
                       TestUtils.printTrackList(extra.getTracks(),"   ");
                       
                       long itemSize= tracks.getItems().size();
                       size = itemSize > size ? itemSize : size;
                    }
                    
                    // Sometime reported total is different than the actual number of records,  
                    // this way keep asking until no more records are reported.
                    
                    stop = (offset == size); 
                    
                    page++;
                    
                } 
                System.out.println("= "+page+" =");
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getLabel() {

        try {
                LabelGet q = new LabelGet((long)151717);
                Label label= q.getLabel();
                

                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= LABEL (with no Album)                                             =");
                System.out.println("======================================================================");
                
                TestUtils.printLabel(label,""); 
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getLabelWithAlbums() {
        // Deprecated, but still working.
        try {
                LabelGet q = new LabelGet((long)151717,(long)0);
                Label label= q.getLabel();
                
                label.completeAlbumList();

                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= LABEL (with complete Album list)                                  =");
                System.out.println("======================================================================");
                
                TestUtils.printLabel(label,""); 
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getLabelWithAlbumsAndPagination() {
        // Deprecated, but still working.
        try {
                LabelGet q = new LabelGet((long)151717,(long)0);
                Label label= q.getLabel();
                

                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= LABEL (with Albums and pagination)                                =");
                System.out.println("======================================================================");
                
                TestUtils.printLabel(label,""); 

                long size=0;
                
                if (label!= null) {         
                    size = label.getAlbums().getItems().size();     
                }

                int page=1;
                boolean stop = (label.getAlbums()== null);
                
                while (!stop ) {
                    
                    System.out.println("= "+page+" =");
                    
                    AlbumList extra = label.loadNextAlbumsPage();
                    stop = (label.getAlbums().getItems().size() == size); 
                    size = label.getAlbums().getItems().size();
                    
                    TestUtils.printAlbumList(extra, "    ");
                    
                    page++;
                } 
                
                System.out.println("= "+page+" =");
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getLableList() {
        //very long taking query, better use pagination here.
        try {
                LabelListGet q = new LabelListGet();
                LabelListResult result = q.getLabelListResult();
                
                result.completeLabelsList();

                LabelList labels = result.getLables();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= LABEL LIST (complete)                                              =");
                System.out.println("======================================================================");

                TestUtils.printLabelList(labels,""); 
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getLableListWithPagination() {

        try {
                LabelListGet q = new LabelListGet();
                LabelListResult result = q.getLabelListResult();
                
                LabelList labels = result.getLables();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= LABEL LIST (with pagination)                                       =");
                System.out.println("======================================================================");

                TestUtils.printLabelList(labels,""); 
                
                int page=1;
                // set an arbitrary limit here, we have > 100K labels...
                int limit = 1000;
                
                long size=0;
                
                if (labels != null) {         
                    size = labels.getItems().size();
                }
                
                boolean stop = (labels == null);
                
                while (!stop ) {
                    
                    System.out.println("= "+page+" =");
                    
                    LabelList extra  = result.loadNextLabelsPage();
                    stop = (labels.getItems().size() == size || labels.getItems().size() > limit); 
                    size = labels.getItems().size();
                    
                     TestUtils.printLabelList(extra, "");
                    
                    page++;
                } 
                System.out.println("= "+page+" =");
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getGenre() {

        try {
                GenreGet q = new GenreGet((long)22);
                Genre genre= q.getGenre();

                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= GENRE (with no Album)                                             =");
                System.out.println("======================================================================");
                
                TestUtils.printGenre(genre,""); 
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getGenreWithAlbums() {
        // Deprecated, not working anymore.
        try {
                GenreGet q = new GenreGet((long)22,(long)0);
                Genre genre= q.getGenre();
                
                genre.completeAlbumList();

                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= GENRE (with complete Album list)                                  =");
                System.out.println("======================================================================");
                
                TestUtils.printGenre(genre,""); 
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getGenreList() {

        try {
                Long genre =  (long)10;
                
                GenreListGet q = new GenreListGet(genre);
                GenreListResult result = q.getGenreListResult();
                
                result.completeGenresList();
                
                Genre parent = result.getParent();
                GenreList genres = result.getGenres();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= GENRE LIST (complete)                                              =");
                System.out.println("======================================================================");
                
                TestUtils.printGenre(parent,"");
                TestUtils.printGenreList(genres,""); 
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getGenreListWithPagination() {

        try {
                Long genre =  (long)10;
                
                GenreListGet q = new GenreListGet(genre);
                GenreListResult result = q.getGenreListResult();
                
                Genre parent = result.getParent();
                GenreList genres = result.getGenres();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= GENRE LIST (with pagination)                                       =");
                System.out.println("======================================================================");
                
                TestUtils.printGenre(parent,"");
                
                TestUtils.printGenreList(genres,""); 
                
                int page=1;
                long size=0;
                
                if (genres != null) {         
                    size = genres.getItems().size();
                }
                
                boolean stop = (genres == null);
                
                while (!stop) {
                    
                    System.out.println("= "+page+" =");
                    
                    GenreList extra = result.loadNextGenresPage();
                    stop = (genres.getItems().size() == size); 
                    size = genres.getItems().size();
                    
                    TestUtils.printGenreList(extra, "");
                    
                    page++;
                    
                } 
                System.out.println("= "+page+" =");
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //@Test
    public void getFeaturedAlbumsWithPagination() {

        try {
                String [] typesToChooseFrom=AlbumsGetFeatured.suggestedTypes();
                //should be one in aabove array, but time to time they add some new, so is not mandatory
                
                String type =typesToChooseFrom[1];
                Long genreId = (long)27;
      
                AlbumsGetFeatured q = new AlbumsGetFeatured(type, genreId);
                AlbumList featuredAlbums = q.getFeaturedAlbumResult().getAlbums();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= FEATURED ALBUMS (with pagination)                                  =");
                System.out.println("======================================================================");
                
                TestUtils.printAlbumList(featuredAlbums,""); 
                
                int page=1;
                long size=0;
                
                if (featuredAlbums!= null) {         
                    size = featuredAlbums.getItems().size();     
                }
                
                boolean stop = (featuredAlbums == null);
                
                while (!stop) {
                    
                    System.out.println("= "+page+" =");
                    
                    q = new AlbumsGetFeatured(type, genreId,(long)featuredAlbums.getItems().size());
                    AlbumList extra = q.getFeaturedAlbumResult().getAlbums();
                    featuredAlbums.getItems().addAll(extra.getItems());
                    stop = (featuredAlbums.getItems().size() == size); 
                    size = featuredAlbums.getItems().size();
                    
                    TestUtils.printAlbumList(extra,""); 
                    
                    page++;
                    
                } 
                System.out.println("= "+page+" =");
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getAlbum() {

        try {
                AlbumGet q = new AlbumGet("0822189023645");
                Album album = q.getAlbum();
                album.completeTrackList();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= ALBUM                                                              =");
                System.out.println("======================================================================");
                
                TestUtils.printAlbum (album, ""); 
 
                System.out.println("======================================================================");                
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Test
    public void getTrack() {

        try {
                TrackGet q = new TrackGet((long)34520243);
                Track track = q.getTrack();
                                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= TRACK                                                              =");
                System.out.println("======================================================================");
                
                TestUtils.printTrack (track, ""); 
 
                System.out.println("======================================================================");                
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

}