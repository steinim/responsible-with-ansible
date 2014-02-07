package no.bekk.jersey.filters;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

public class NoCacheResponseFilter implements ResourceFilter, ContainerResponseFilter {

	@Override
	public ContainerRequestFilter getRequestFilter() {
		return null;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return this;
	}

	@Override
	public ContainerResponse filter(final ContainerRequest request, final ContainerResponse response) {
		Response res = Response.fromResponse(response.getResponse()).cacheControl(getNoCacheNoStoreCacheControl()).build();
		response.setResponse(res);
		return response;
	}

	public static CacheControl getNoCacheNoStoreCacheControl() {
		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoCache(true);
		cacheControl.setNoStore(true);
		return cacheControl;
	}
}
