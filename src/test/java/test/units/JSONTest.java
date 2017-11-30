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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.junit.Test;

/**
 *
 * @author marco
 */
public class JSONTest extends UnitTest {

    @Test
    public void zzz() throws FileNotFoundException, IOException {
        
        String path = "F:/SVILUPPO/Mymusic v. 1.2/maven/qobuzAPIv0.2-java/answer.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String answer="";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            answer= sb.toString();
        } finally {
            br.close();
        }

        JSONObject  jsonObject = new JSONObject(answer);

        System.out.println(jsonObject);
    }
         
}
