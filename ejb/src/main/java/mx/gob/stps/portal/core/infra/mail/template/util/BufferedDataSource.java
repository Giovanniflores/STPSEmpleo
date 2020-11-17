package mx.gob.stps.portal.core.infra.mail.template.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

/**
 * La clase BufferedDataSource tiene por objetivo manejar objetos streams.
 */
public class BufferedDataSource implements DataSource { 
	
	/** Los datos en arreglo de bytes. */
	private byte[] data; 
	
	/** El nombre. */
	private String name; 

	/**
	 * Una instancia nueva de buffered data source.
	 * 
	 * @param data el data
	 * @param name el name
	 */
	public BufferedDataSource(byte[] data, String name) { 
		this.data = data; 
		this.name = name;
	} 

	/**
	 * Returns the content-type information required by a DataSource
	 * application/octet-stream in this case.
	 * 
	 * @return the content type
	 */ 
	public String getContentType() { 
		return "application/octet-stream";
	} 

	/* (non-Javadoc)
	 * @see javax.activation.DataSource#getInputStream()
	 */
	public InputStream getInputStream() throws IOException { 
		return new ByteArrayInputStream(data);
	} 

	/* (non-Javadoc)
	 * @see javax.activation.DataSource#getName()
	 */
	public String getName() { 
		return name;
	} 

	/* (non-Javadoc)
	 * @see javax.activation.DataSource#getOutputStream()
	 */
	public OutputStream getOutputStream() throws IOException { 
		OutputStream out = new ByteArrayOutputStream(); 
		out.write(data); 
		return out;
	}

}