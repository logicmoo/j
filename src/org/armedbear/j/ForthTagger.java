/*
 * ForthTagger.java
 *
 * Copyright (C) 2015 Peter Graves
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.armedbear.j;

import java.util.ArrayList;

public final class ForthTagger extends Tagger
{
  public ForthTagger(SystemBuffer buffer)
  {
    super(buffer);
  }

  public void run()
  {
    ArrayList tags = new ArrayList();
    Line line = buffer.getFirstLine();
    while (line != null)
      {
        String s = line.trim();
        if (s != null && s.startsWith(": "))
          {
            s = s.substring(2).trim();
            int index = s.indexOf(' ');
            String name;
            if (index > 0)
              name = s.substring(0, index);
            else
              name = s;
            tags.add(new LocalTag(name, line));
          }
        line = line.next();
      }
    buffer.setTags(tags);
  }
}
