/*
 * Copyright 2017 the original author or authors.
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
package org.springframework.security.web.server;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 *
 * @author Rob Winch
 * @since 5.0
 */
public class CacheControlHttpHeadersWriter implements HttpHeadersWriter {

	/**
	 *
	 */
	public static final String EXPIRES_VALUE = "0";
	/**
	 *
	 */
	public static final String PRAGMA_VALUE = "no-cache";
	/**
	 *
	 */
	public static final String CACHE_CONTRTOL_VALUE = "no-cache, no-store, max-age=0, must-revalidate";

	@Override
	public Mono<Void> writeHttpHeaders(ServerWebExchange exchange) {
		HttpHeaders headers = exchange.getResponse().getHeaders();

		if(headers.containsKey(HttpHeaders.EXPIRES) ||
				headers.containsKey(HttpHeaders.PRAGMA) ||
				headers.containsKey(HttpHeaders.CACHE_CONTROL)) {
			return Mono.empty();
		}

		headers.set(HttpHeaders.CACHE_CONTROL, CACHE_CONTRTOL_VALUE);
		headers.set(HttpHeaders.PRAGMA, PRAGMA_VALUE);
		headers.set(HttpHeaders.EXPIRES, EXPIRES_VALUE);
		return Mono.empty();
	}

}