/*
 * Copyright 2019 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fhws.fiw.fds.sutton.server.api.states.get;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.Response;

public abstract class AbstractGetState<T extends AbstractModel> extends AbstractState
{
	protected long requestedId;

	protected SingleModelResult<T> requestedModel;

	public AbstractGetState( final AbstractGetStateBuilder builder )
	{
		super( builder );
		this.requestedId = builder.requestedId;
	}

	@Override
	protected Response buildInternal( )
	{
		configureState( );

		this.requestedModel = loadModel( );

		if ( this.requestedModel.hasError( ) )
		{
			return Response.serverError( )
						   .build( );
		}

		if ( this.requestedModel.isEmpty( ) )
		{
			return Response.status( Response.Status.NOT_FOUND )
						   .build( );
		}

		this.responseBuilder = Response.ok( );

		return createResponse( );
	}

	protected abstract SingleModelResult<T> loadModel( );

	protected Response createResponse( )
	{
		defineHttpResponseBody( );

		return this.responseBuilder.build( );
	}

	protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( this.requestedModel.getResult( ) );
	}

	public static abstract class AbstractGetStateBuilder extends AbstractState.AbstractStateBuilder
	{
		protected long requestedId;

		public AbstractGetStateBuilder setRequestedId( final long requestedId )
		{
			this.requestedId = requestedId;
			return this;
		}
	}
}
