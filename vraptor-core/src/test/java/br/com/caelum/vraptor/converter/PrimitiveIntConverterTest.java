/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */

package br.com.caelum.vraptor.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

public class PrimitiveIntConverterTest {

	private PrimitiveIntConverter converter;
	private ResourceBundle bundle;

	@Before
	public void setup() {
		this.converter = new PrimitiveIntConverter();
		this.bundle = ResourceBundle.getBundle("messages");
	}

	@Test
	public void shouldBeAbleToConvertNumbers() {
		assertThat((Integer) converter.convert("2", int.class, bundle), is(equalTo(2)));
	}

	@Test
	public void shouldComplainAboutInvalidNumber() {
		try {
			converter.convert("---", int.class, bundle);
		} catch (ConversionError e) {
			assertThat(e.getMessage(), is(equalTo("--- is not a valid integer.")));
		}
	}

	@Test
	public void shouldConvertToZeroWhenNull() {
		assertThat((Integer) converter.convert(null, int.class, bundle), is(equalTo(0)));
	}

	@Test
	public void shouldConvertToZeroWhenEmpty() {
		assertThat((Integer) converter.convert("", int.class, bundle), is(equalTo(0)));
	}

}
