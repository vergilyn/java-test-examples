package org.junit.jupiter.params.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.util.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author vergilyn
 * @since 2022-02-14
 *
 * @see CsvFileArgumentsProvider
 */
public class JsonFileArgumentProvider implements ArgumentsProvider, AnnotationConsumer<JsonFileSource> {
	private static final String PREFIX_CLASSPATH = "classpath:";

	private JsonFileSource annotation;
	private String filepath;
	private String encoding;

	@Override
	public void accept(JsonFileSource annotation) {
		this.annotation = annotation;
		this.filepath = annotation.filepath();
		this.encoding = annotation.encoding();
	}

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		InputStream source = open(this.filepath, context.getRequiredTestClass());
		String jsonSource = IOUtils.toString(source, this.encoding);

		JSONArray jsonArray = JSON.parseArray(jsonSource);

		// 要返回 二维数组，例如 "[ [json-object, json-array...], [json-object, json-array...] ]"
		return jsonArray.stream().map(o -> {
			JSONArray value = (JSONArray) o;
			return Arguments.of(value.toArray());
		});
	}

	private InputStream open(String filepath, Class<?> testClass){
		int indexOf = filepath.indexOf(PREFIX_CLASSPATH);
		if (indexOf >= 0){
			filepath = filepath.substring(PREFIX_CLASSPATH.length());
			return classpathResource(filepath, testClass);
		}

		return openFile(filepath);
	}

	private InputStream classpathResource(String filepath, Class<?> testClass){
		Preconditions.notBlank(filepath, () -> "Classpath resource [" + filepath + "] must not be null or blank");
		InputStream inputStream = testClass.getResourceAsStream(filepath);
		return Preconditions.notNull(inputStream, () -> "Classpath resource [" + filepath + "] does not exist");
	}

	private InputStream openFile(String filepath){
		Preconditions.notBlank(filepath, () -> "File [" + filepath + "] must not be null or blank");
		try {
			return Files.newInputStream(Paths.get(filepath));
		}
		catch (IOException e) {
			throw new JUnitException("File [" + filepath + "] could not be read", e);
		}
	}
}
