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

import com.mc2.qobuz.api.v02.API.QobuzAPIException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import com.mc2.qobuz.api.v02.elements.CatalogFromApi;
import com.mc2.qobuz.api.v02.lists.ArtistListFromApi;
import com.mc2.qobuz.api.v02.lists.TrackListFromApi;
import com.mc2.qobuz.api.v02.lists.AlbumListFromApi;
import com.mc2.qobuz.api.v02.query.CatalogSearch;

import com.mc2.qobuz.api.v02.IMPL.TestUtils;
import com.mc2.qobuz.api.v02.QobuzApiController;
import com.mc2.qobuz.api.v02.QobuzAuth;
import com.mc2.qobuz.api.v02.QobuzAuthFromEnvVar;
/**
 *
 * @author marco
 */
public class CatalogueSearchTest extends UnitTest{
    
    //@Test
    public void searchAllCatalog() throws QobuzAPIException {
		
		QobuzApiController controller = 	QobuzApiController.findIstance();
		QobuzAuth auth = new QobuzAuthFromEnvVar();
		controller.setQobuzAuth(auth);

        try {
                //String query = "Antonio Vivaldi"; //can't use an empty string here.
                String query = "Cello Sonata in B-Flat Major, RV 46";
                CatalogSearch q = new CatalogSearch(query);
                CatalogFromApi catalog = q.getCatalog();
                                
                System.out.println("======================================================================");
                System.out.println("= CATALOG SEARCH (with pagination)                                   =");
                System.out.println("======================================================================");
                
                Long totalArtist = (long)0;
                Long totalAlbum = (long)0;
                Long totalTrack = (long)0;

                ArtistListFromApi artists = catalog.getArtists();
                AlbumListFromApi albums = catalog.getAlbums();
                TrackListFromApi tracks = catalog.getTracks();
                
                System.out.println( "Query: "+catalog.getQuery());
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
                
                while (!stop) {
                    
                    System.out.println("= "+page+" =");
                    
                    long offset = size;
                    
                    q = new CatalogSearch(query, offset);
                    CatalogFromApi extra = q.getCatalog();
                    
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
}