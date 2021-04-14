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

import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

public interface PagingBehavior<T extends AbstractModel>
{
	void addSelfLink( PagingContext pagingContext );

	void addPrevPageLink( PagingContext pagingContext );

	void addNextPageLink( PagingContext pagingContext,
		CollectionModelResult<?> result );

	void addPageHeader( PagingContext pagingContext,
		CollectionModelResult<?> result );

	CollectionModelResult<T> page( CollectionModelResult<T> fullResult );
}
