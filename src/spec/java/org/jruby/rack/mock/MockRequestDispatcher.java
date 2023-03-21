/*
 * Copyright (c) 2010-2012 Engine Yard, Inc.
 * Copyright (c) 2007-2009 Sun Microsystems, Inc.
 * This source code is available under the MIT license.
 * See the file LICENSE.txt for details.
 *
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
 */

package org.jruby.rack.mock;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * Mock implementation of the {@link jakarta.servlet.RequestDispatcher} interface.
 *
 * <p>Used for testing the web framework; typically not necessary for
 * testing application controllers.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public class MockRequestDispatcher implements RequestDispatcher {

	private final String url;


	/**
	 * Create a new MockRequestDispatcher for the given URL.
	 * @param url the URL to dispatch to.
	 */
	public MockRequestDispatcher(String url) {
		this.url = url;
	}


	public void forward(ServletRequest request, ServletResponse response) {
		if (response.isCommitted()) {
			throw new IllegalStateException("Cannot perform forward - response is already committed");
		}
		getMockHttpServletResponse(response).setForwardedUrl(this.url);
	}

	public void include(ServletRequest request, ServletResponse response) {
		getMockHttpServletResponse(response).addIncludedUrl(this.url);
	}

	/**
	 * Obtain the underlying MockHttpServletResponse,
	 * unwrapping {@link HttpServletResponseWrapper} decorators if necessary.
	 */
	protected MockHttpServletResponse getMockHttpServletResponse(ServletResponse response) {
		if (response instanceof MockHttpServletResponse) {
			return (MockHttpServletResponse) response;
		}
		if (response instanceof HttpServletResponseWrapper) {
			return getMockHttpServletResponse(((HttpServletResponseWrapper) response).getResponse());
		}
		throw new IllegalArgumentException("MockRequestDispatcher requires MockHttpServletResponse");
	}

}
