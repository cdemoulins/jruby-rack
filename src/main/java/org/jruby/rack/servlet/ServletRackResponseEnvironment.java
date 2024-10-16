/*
 * Copyright (c) 2010-2012 Engine Yard, Inc.
 * Copyright (c) 2007-2009 Sun Microsystems, Inc.
 * This source code is available under the MIT license.
 * See the file LICENSE.txt for details.
 */

package org.jruby.rack.servlet;

import org.jruby.rack.RackResponse;
import org.jruby.rack.RackResponseEnvironment;
import org.jruby.rack.DefaultErrorApplication;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * The default (servlet) {@link RackResponseEnvironment} implementation.
 *
 * @author nicksieger
 */
public class ServletRackResponseEnvironment extends HttpServletResponseWrapper
    implements RackResponseEnvironment {

    public ServletRackResponseEnvironment(HttpServletResponse response) {
        super(response);
    }

    @Deprecated
    public void defaultRespond(final RackResponse response) throws IOException {
        DefaultErrorApplication.defaultRespond(response, this);
    }

}
