package com.search.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

public class Serializer {

	public static byte[] serialize(Object object) {
		try {
			ByteArrayOutputStream byteArrOs = new ByteArrayOutputStream();
			ObjectOutputStream objOs = new ObjectOutputStream(byteArrOs);
			objOs.writeObject(object);
			return byteArrOs.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String serializeToBase64(Object object) {
		return Base64.encodeBase64String(serialize(object));
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] source) {
		try {
			ByteArrayInputStream byteArrIs = new ByteArrayInputStream(source);
			ObjectInputStream objIs;
			objIs = new ObjectInputStream(byteArrIs);
			return (T) objIs.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T deserializeFromBase64(String base64Str) {
		byte[] byteDate = Base64.decodeBase64(base64Str);
		return deserialize(byteDate);
	}
}
