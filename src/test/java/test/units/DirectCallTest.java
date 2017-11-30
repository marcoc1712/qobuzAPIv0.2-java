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
import org.mc2.qobuz.api.v02.QobuzController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class DirectCallTest extends UnitTest {
    
    //@Test
    public void DirectCallTest(){
        
        try {
                //String urlString = QobuzController.BASEURL+"/album/get?album_id=0822189023645";
                String urlString = "http://www.qobuz.com/api.json/0.2/album/get?album_id=0822189023645";
                String answer =QobuzController.makeApiCall(urlString);
                System.out.println(answer);

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);

        }
    }
}