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
package test.bot;

import com.mc2.qobuz.api.v02.elements.Album;
import com.mc2.qobuz.api.v02.elements.Artist;
import com.mc2.qobuz.api.v02.elements.Track;
import com.mc2.qobuz.api.v02.query.AlbumGet;
import com.mc2.qobuz.api.v02.query.ArtistGet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author marco
 */
public class WorksByArtist {
	
	private static Long ARTSIT_ID = 720L;
	ArrayList<String> works = new ArrayList<>();
	
	
	@Test
    public void getArtistWorks() {
		
        try {
                ArtistGet artistGet = new ArtistGet(ARTSIT_ID,ArtistGet.EXTRA_ALBUMS, (long)0);
                Artist artist= artistGet.getArtist();
                artist.completeAlbumList();
				
				if ( artist.getAlbums() != null){

					for (int iAlbum = 0; iAlbum < artist.getAlbums().getItems().size(); iAlbum++) {
						
						Album album = artist.getAlbums().getItems().get(iAlbum);
						
						String albumId = album.getId();
						AlbumGet albumGet = new AlbumGet(albumId); 
						album = albumGet.getAlbum();
						album.completeTrackList();
						
						if (album.getTracks() != null){
							
							for (int iTracks = 0; iTracks < album.getTracks().getItems().size(); iTracks++) {
								
								Track track = album.getTracks().getItems().get(iTracks);
								
								String workTitle = track.geWorkGuessed();
								
								if (workTitle == null || workTitle.isEmpty()){
									workTitle = "*** - "+track.getTitle();
								}
								
								if (!works.contains(workTitle)) {works.add(workTitle);}
							} 	
						}	
					} 
				}
				System.out.println("======================================================================");
                System.out.println("= WORKS                                                              =");
                System.out.println("======================================================================");
				
				System.out.println();
				
				for (int iWorks = 0; iWorks < works.size(); iWorks++) {
					System.out.println(iWorks+" - "+works.get(iWorks));
				} 
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
