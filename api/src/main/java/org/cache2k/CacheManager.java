package org.cache2k;

/*
 * #%L
 * cache2k api only package
 * %%
 * Copyright (C) 2000 - 2013 headissue GmbH, Munich
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.Iterator;

/**
 * @author Jens Wilke; created: 2013-06-27
 */
public abstract class CacheManager implements Iterable<Cache> {

  private static CacheManager defaultManager;

  /**
   * Get the default cache manager for the class loader
   */
  public synchronized static CacheManager getInstance() {
    if (defaultManager == null) {
      try {
        defaultManager = (CacheManager)
          Class.forName("org.cache2k.impl.CacheManagerImpl").newInstance();
      } catch (Exception e) {
        throw new LinkageError("cache2k implementaiton not found, cache2k-core.jar missing?", e);
      }
    }
    return defaultManager;
  }

  public abstract String getName();

  public abstract Iterator<Cache> iterator();

  /** Clear all caches associated to this cache manager */
  public abstract void clear();

  /**
   * Destroy all caches associated to this cache manager.
   */
  public abstract void destroy();

}
