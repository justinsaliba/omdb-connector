/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.salibasoft.omdb;

import java.io.IOException;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestQueryParam;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="omdb", friendlyName="OMDB")
public abstract class OMDBConnector {

    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri = "http://www.omdbapi.com/", method = HttpMethod.GET )
    public abstract String findByIdOrTitle(
    		@RestQueryParam(value = "i", ignoreIfEmpty=true) @Optional @FriendlyName("IMDB Id") String imdbId,
    		@RestQueryParam(value = "t", ignoreIfEmpty=true) @Optional @FriendlyName("Title") String title,
    		@RestQueryParam("type") @Default("movie") String type,
    		@RestQueryParam(value = "y", ignoreIfEmpty=true) @FriendlyName("Year of Release") @Optional String yearOfRelease,
    		@RestQueryParam("plot") @Default("short") String plot,
    		@RestQueryParam("r") @Default(value = "json") String returnType,
    		@RestQueryParam("tomatoes") @Default(value = "false") Boolean includeRottenTomatoesRatings,
    		@RestQueryParam(value = "callback", ignoreIfEmpty=true) @Optional @FriendlyName("JSON-P Callback") String jsonpCallback,
    		@RestQueryParam("v") @Default("1") String version) throws IOException;
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri = "http://www.omdbapi.com/", method = HttpMethod.GET )
    public abstract String search(
    		@RestQueryParam("s") String movieTitle,
    		@RestQueryParam("type") @Default("movie") String type,
    		@RestQueryParam(value = "y", ignoreIfEmpty=true) @FriendlyName("Year of Release") @Optional String yearOfRelease,
    		@RestQueryParam("r") @Default(value="json") String returnType,
    		@RestQueryParam(value = "callback", ignoreIfEmpty=true) @Optional @FriendlyName("JSON-P Callback") String jsonpCallback,
    		@RestQueryParam("v") @Default("1") String version) throws IOException;
	
}