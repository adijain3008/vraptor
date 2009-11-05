package br.com.caelum.vraptor.view;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;

import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class XmlSerializerTest {


    private Mockery mockery;
	private XmlSerializer serializer;
	private ByteArrayOutputStream stream;

	@Before
    public void setup() {
        this.mockery = new Mockery();
        this.stream = new ByteArrayOutputStream();
        this.serializer = new XmlSerializer(stream);
    }
	
	
	public static class Client {
		String name;
		public Client(String name) {
			this.name = name;
		}
	}
	public static class Order {
		Client client;
		double price;
		String comments;
		public Order(Client client, double price, String comments) {
			this.client = client;
			this.price = price;
			this.comments = comments;
		}
		
	}
	
	@Test
	public void shouldSerializeAllBasicFields() {
		String expectedResult = "<order>\n  <price>15.0</price>\n  <comments>pack it nicely, please</comments>\n</order>";
		Order order = new Order(new Client("guilherme silveira"), 15.0, "pack it nicely, please");
		serializer.serialize(order);
		assertThat(result(), is(equalTo(expectedResult)));
		mockery.assertIsSatisfied();
	}

	private String result() {
		return new String(stream.toByteArray());
	}

}