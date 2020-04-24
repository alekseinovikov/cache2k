package org.cache2k.test.util;

/*
 * #%L
 * cache2k implementation
 * %%
 * Copyright (C) 2000 - 2020 headissue GmbH, Munich
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.cache2k.test.core.TestingParameters;

/**
 * Helper methods that wait for some event for a reasonable amout of time.
 *
 * @author Jens Wilke
 */
public class ConcurrencyHelper {

  /**
   * Wait for the check become true.
   */
  public static void await(String _description, final long _timeoutMillis, final Condition c) {
    long t0 = System.currentTimeMillis();
    try {
      while (!c.check()) {
        if (t0 + _timeoutMillis < System.currentTimeMillis()) {
          if (_description != null) {
            throw new TimeoutException("waiting for " + _timeoutMillis + " milliseconds for event '" + _description + "'");
          } else {
            throw new TimeoutException("waiting for " + _timeoutMillis + " milliseconds");
          }
        }
        Thread.yield();
      }
    } catch (Exception ex) {
      throw new RethrownUnhandledException(ex);
    }
  }

  public static void await(long _timeoutMillis, final Condition c) {
    await(null, _timeoutMillis, c);
  }

  /**
   * Wait for an event the maximum test time.
   */
  public static void await(String _description, final Condition c) {
    await(_description, TestingParameters.MAX_FINISH_WAIT_MILLIS, c);
  }

  /**
   * Wait for an event the maximum test time, as defined at {@link TestingParameters#MAX_FINISH_WAIT_MILLIS}
   */
  public static void await(final Condition c) {
    await(null, TestingParameters.MAX_FINISH_WAIT_MILLIS, c);
  }

  static class TimeoutException extends RuntimeException {
    public TimeoutException(String message) {
      super(message);
    }
  }

  static class RethrownUnhandledException extends RuntimeException {

    public RethrownUnhandledException(Throwable cause) {
      super(cause);
    }

  }

}
