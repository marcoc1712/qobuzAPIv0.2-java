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
import org.junit.Test;
import com.mc2.qobuz.api.v02.elements.Album;
import com.mc2.qobuz.api.v02.query.AlbumGet;

import test.utils.TestUtils;

/**
 *
 * @author marco
 */
public class AlbumGetTest extends UnitTest {

    @Test
    public void getAlbum() {

        try {
                // AlbumGet q = new AlbumGet("0822189023645");
				// AlbumGet q = new AlbumGet("zcyp7kgzkrl3b"); //tracks with work NOT in title
				AlbumGet q = new AlbumGet("3760014193255"); //tracks with work in title
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
}