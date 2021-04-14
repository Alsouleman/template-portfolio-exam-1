/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.sutton.server.api.states.get;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.Response;

public abstract class AbstractGetCollectionState<T extends AbstractModel> extends AbstractState
{
	protected AbstractQuery<T> query;

	protected CollectionModelResult<T> result;

	protected AbstractGetCollectionState( final AbstractGetCollectionStateBuilder<T> builder )
	{
		super( builder );
		this.query = builder.query;
	}

	@Override
	protected Response buildInternal( )
	{
		configureState( );

		this.result = loadModels( );

		if ( this.result.hasError( ) )
		{
			return Response.serverError( )
						   .build( );
		}

		return createResponse( );
	}

	protected final CollectionModelResult<T> loadModels( )
	{
		return this.query.startQuery( );
	}

	protected Response createResponse( )
	{
		defineHttpResponseBody( );

		return this.responseBuilder.build( );
	}

	protected abstract void defineHttpResponseBody( );

	public static abstract class AbstractGetCollectionStateBuilder<T extends AbstractModel> extends AbstractStateBuilder
	{
		protected AbstractQuery<T> query;

		public AbstractGetCollectionStateBuilder setQuery( final AbstractQuery<T> query )
		{
			this.query = query;
			return this;
		}
	}
}
