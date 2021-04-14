package de.fhws.fiw.fds.sutton.server.api.queries;

import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

public class OnePageWithAllResults<T extends AbstractModel>
	extends PagingBehaviorUsingOffsetSize<T>
{
	public OnePageWithAllResults( )
	{
		super( 0, Integer.MAX_VALUE );
	}
}
