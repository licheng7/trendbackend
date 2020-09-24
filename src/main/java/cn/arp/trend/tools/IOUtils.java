package cn.arp.trend.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class IOUtils {
	public static String readFromClassPath(String name, ClassLoader loader, String encode) throws IOException {
		Resource  itemResource= new ClassPathResource(name, loader);
		return readAll(itemResource.getInputStream(), Charset.forName(encode));
	}
	
	public static String readAll(InputStream in, Charset charset) throws IOException {
		try {
			Reader reader = new InputStreamReader(in, charset);
			StringBuilder builder = new StringBuilder();
			char[] buff = new char[1024];
			int readed = 0;
			while ((readed = reader.read(buff, 0, buff.length)) > 0) {
				builder.append(buff, 0, readed);
			}
			return builder.toString();
		} finally {
			in.close();
		}
	}
}
