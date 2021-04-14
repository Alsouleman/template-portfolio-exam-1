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

package de.fhws.fiw.fds.sutton.server.api.queries;

import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.util.function.Predicate;

public abstract class AbstractQuery<T extends AbstractModel>
{
	protected CollectionModelResult<T> result;

	protected AbstractQuery( )
	{
	}

	public final CollectionModelResult<T> startQuery( )
	{
		/* DON'T OPTIMIZE THE FOLLOWING TWO LINES. WE NEED THE RESULT IN OTHER METHODS LATER. */
		this.result = executeQuery( );
		return this.result;
	}

	protected CollectionModelResult<T> executeQuery( )
	{
		CollectionModelResult<T> result;

		try
		{
			result = doExecuteQuery( );
		}
		catch ( final DatabaseException e )
		{
			result = new CollectionModelResult<>( );
		}

		return result;
	}

	protected abstract CollectionModelResult<T> doExecuteQuery( ) throws DatabaseException;

	protected Predicate<T> all( )
	{
		return p -> true;
	}
}
