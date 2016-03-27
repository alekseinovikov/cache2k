package org.cache2k.integration;

/*
 * #%L
 * cache2k API only package
 * %%
 * Copyright (C) 2000 - 2016 headissue GmbH, Munich
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

import org.cache2k.WrappedCustomizationException;

/**
 * Wraps loader exceptions. Exceptions occurring in the loader are usually cached, which
 * means, there can be multiple instances thrown for one loader exception. The cause
 * contains the original exceptions from the loader.
 *
 * @author Jens Wilke
 */
public class CacheLoaderException extends WrappedCustomizationException {

  public CacheLoaderException(String _message, Throwable ex) {
    super(_message, ex);
  }

  public CacheLoaderException(final Throwable cause) {
    super(cause);
  }

}
