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
import com.mc2.qobuz.api.v02.elements.Catalog;
import com.mc2.qobuz.api.v02.query.CatalogCount;
/**
 *
 * @author marco
 */
public class CatalogCountTest extends UnitTest{

    //@Test
    public void CatalogCountTest() {

        try {
                String query = ""; //use an empty string to count ALL the objects in Qobuz archives.
                //query = "Antonio Vivaldi"; 
                
                CatalogCount q = new CatalogCount(query);
                Catalog catalog = q.getCatalog();
                
                //System.setOut(new PrintStream(System.out, true, "utf-8"));
                
                System.out.println("======================================================================");
                System.out.println("= CATALOG COUNT                                                      =");
                System.out.println("======================================================================");
                
                Long totalArtist = (long)0;
                Long totalAlbum = (long)0;
                Long totalTrack = (long)0;
                
                System.out.println( "Query: "+catalog.getQuery());
                if (catalog.getArtists()!= null) {
                    totalArtist=catalog.getArtists().getTotal();   
                }
                if (catalog.getAlbums()!= null) {
                    totalAlbum=catalog.getAlbums().getTotal();
                }
                if (catalog.getTracks()!= null) {
                    totalTrack=catalog.getTracks().getTotal(); 
                }
                
                System.out.println( "- Artists: "+totalArtist);
                System.out.println( "- Albums : "+totalAlbum);
                System.out.println( "- Tracks : "+totalTrack);
                
                System.out.println("======================================================================");
                
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}