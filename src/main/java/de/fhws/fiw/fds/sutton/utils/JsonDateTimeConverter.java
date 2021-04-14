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

package de.fhws.fiw.fds.sutton.utils;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonDateTimeConverter implements Converter<LocalDate>
{
	@Override
	public void serialize( final LocalDate convert, final ObjectWriter objectWriter, final Context context )
		throws Exception
	{
		objectWriter.writeString( convert.format( DateTimeFormatter.ISO_LOCAL_DATE ) );
	}

	@Override public LocalDate deserialize( final ObjectReader objectReader, final Context context )
		throws Exception
	{
		final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		return LocalDate.parse( objectReader.valueAsString( ), formatter );
	}
}
