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

package com.mc2.qobuz.api.v02.exceptions;

/**
 * A request exception indicates that the web service
 * request was invalid.
 * 
 */
public class RequestException extends QobuzConnectionException {
    private static final long serialVersionUID = 2L;

	/**
	 * 
	 */
	public RequestException() {
                super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RequestException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RequestException(Throwable cause) {
		super(cause);
	}
}