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

import com.mc2.qobuz.api.v02.API.elements.UserAuth;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import com.mc2.qobuz.api.v02.QobuzAuth;
import com.mc2.qobuz.api.v02.QobuzAuthFromEnvVar;
import com.mc2.qobuz.api.v02.query.UserLogin;

/**
 *
 * @author marco
 */
public class LoginTest {
    @Test
    public void getUserAuthToken() {
    
        try {
			
			QobuzAuth auth = new QobuzAuthFromEnvVar();
			UserLogin login = new UserLogin(auth);
			
			UserAuth userAuth  = login.getUserAuth();
			
			            
            System.out.println("======================================================================");
            System.out.println("= USER AUTH                                                          =");
            System.out.println("======================================================================");
			System.out.println("");
			System.out.println("User: "+userAuth.getUser().getDisplayName());
            System.out.println("Token: "+userAuth.getUserAuthToken());

            
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
