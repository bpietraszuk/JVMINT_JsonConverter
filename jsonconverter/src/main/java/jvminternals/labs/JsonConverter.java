package jvminternals.labs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JsonConverter implements JsonConverterInterface {
	StringBuffer jsonString = new StringBuffer(" { ");

	@Override
	public <T> String toJson(T obj) throws JsonConverterException {
		if (obj == null) {
			throw new JsonConverterException("null object converion");
		}
		int numFields = obj.getClass().getFields().length;
		int k = 0;
		Class<?> type;
		
		for (Field f : obj.getClass().getFields()) {
			type = f.getType();
			jsonString.append("\"");
			jsonString.append(f.getName());
			jsonString.append("\" : ");

			Object value = null;
			if (f.getType().isPrimitive() || f.getType().equals(String.class)
					|| f.getType().equals(Double.class)
					|| f.getType().equals(Integer.class)) {
				try {
					value = f.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (type.equals(int.class) || type.equals(Integer.class)
						|| type.equals(double.class)
						|| type.equals(Double.class)) {
					jsonString.append(value);
				} else {
					jsonString.append("\"");
					jsonString.append(value);
					jsonString.append("\"");
				}
			} else if(f.getType().isArray()){
				Object array;
				try {
					array = f.get(obj);
					String[] values = (String[])array;
					jsonString.append(" [ ");
					int j=0;
					int length = values.length;
					for(Object s : values){
						jsonString.append("\"");
						jsonString.append(s);
						jsonString.append("\"");
						j++;
						if(j!=length){
							jsonString.append(",");	
						}												
					}
					jsonString.append(" ] ");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		
			}
				
			else {
				jsonString.append(" { ");
				try {
					Object ob = f.get(obj);
					toJson(ob);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}

			k++;
			if (k != numFields) {
				jsonString.append(", ");
			}

		}
		jsonString.append(" }");

		return jsonString.toString();

	}

	@Override
	public <T> T fromJson(String json, Class<T> cls)
			throws JsonConverterException {
		try {
			return (T) cls.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new JsonConverterException(e);
		}
	}

}
