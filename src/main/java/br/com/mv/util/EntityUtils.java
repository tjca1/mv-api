package br.com.mv.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import br.com.mv.log.Logger;

@SuppressWarnings("unchecked")
public class EntityUtils {
	
	private static final Logger logger = Logger.getLogger(EntityUtils.class);
	
	private EntityUtils() {
		super();
	}
	
	public static <PK, T> List<PK> convertEntitiesToIds(List<T> entities) {
		List<PK> list = new ArrayList<>();
		try {
			for (T entity : entities) {
				Field[] fields = entity.getClass().getDeclaredFields();
				for (Field field : fields) {
					convertEntitiesToIds(list, entity, field);
				}
			}
			return list;
			
		} catch (Exception e){
			logger.error(e);
			return new ArrayList<>();
		}
	}


	private static <T, PK> void convertEntitiesToIds(List<PK> list, T entity, Field field) throws IllegalAccessException {
		for (Annotation annotation : field.getAnnotations()) {
			if (annotation instanceof  javax.persistence.Id) {
				field.setAccessible(true);
				Object id = field.get(entity);
				if (id != null) {
					list.add((PK) id);
				}
			}
		}
	}

}
