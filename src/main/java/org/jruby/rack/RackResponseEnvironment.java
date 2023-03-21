/*
 * Copyright (c) 2010-2012 Engine Yard, Inc.
 * Copyright (c) 2007-2009 Sun Microsystems, Inc.
 * This source code is available under the MIT license.
 * See the file LICENSE.txt for details.
 */

package org.jruby.rack;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Rack response environment (the response environment that is used to actually
 * handle and return the Rack response) interface.
 * It is likely to be (only) implemented as a HTTP servlet response.
 *
 * @see jakarta.servlet.ServletResponse
 * @see jakarta.servlet.http.HttpServletResponse
 * @see RackResponse
 *
 * @author nicksieger
 */
public interface RackResponseEnvironment {

    /**
     * @return whether the underlying response has been committed.
     * @see jakarta.servlet.ServletResponse#isCommitted()
     */
    boolean isCommitted();

    /**
     * Reset the response (buffer) so we can begin a new response.
     * @see jakarta.servlet.ServletResponse#reset()
     */
    void reset();

    /**
     * @see jakarta.servlet.ServletResponse#setContentType(String)
     */
    void setContentType(String type) ;

    /**
     * @see jakarta.servlet.ServletResponse#setContentLength(int)
     */
    void setContentLength(int length) ;

    /**
     * @see jakarta.servlet.ServletResponse#setCharacterEncoding(String)
     */
    void setCharacterEncoding(String charset) ;

    /**
     * Sets a (HTTP) header.
     * @param name
     * @param value
     */
    void setHeader(String name, String value) ;
    /**
     * Adds a (HTTP) header.
     * @param name
     * @param value
     */
    void addHeader(String name, String value) ;

    /**
     * Sets a (HTTP) header.
     * @param name
     * @param date
     */
    void setDateHeader(String name, long date) ;
    /**
     * Adds a (HTTP) header.
     * @param name
     * @param date
     */
    void addDateHeader(String name, long date) ;

    /**
     * Sets a (HTTP) header.
     * @param name
     * @param value
     */
    void setIntHeader(String name, int value) ;
    /**
     * Adds a (HTTP) header.
     * @param name
     * @param value
     */
    void addIntHeader(String name, int value) ;

    /**
     * Sets the response (HTTP) status.
     * @param code the (HTTP) status code
     */
    void setStatus(int code) ;

    /**
     * Send a simple error page response (based on the status code).
     * @param code the (HTTP) status code
     * @throws IOException
     */
    void sendError(int code) throws IOException ;

    /**
     * @see jakarta.servlet.ServletResponse#getOutputStream()
     */
    OutputStream getOutputStream() throws IOException ;

    /**
     * @see jakarta.servlet.ServletResponse#getWriter()
     */
    PrintWriter getWriter() throws IOException ;

    /**
     * @see RackResponse#respond(RackResponseEnvironment)
     * @deprecated (should) no longer (be) used
     */
    @Deprecated
    void defaultRespond(RackResponse response) throws IOException ;

}
