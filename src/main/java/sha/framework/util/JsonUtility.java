/**
 * @(#)JsonUtility.java
 * 
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * provides functionality for reading and writing JSON.
 * 
 * @author Fast Retailing
 * @version $Revision$
 */
@Slf4j
public class JsonUtility {

    /**
     * The ObjectMapper instance.
     */
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper().findAndRegisterModules();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * Serialize any Java value as a JSON String.
     * 
     * @param value any java value
     * @return The JSON content string
     */
    public static String toJson(Object value) {
        String result = "";
        try {
            result = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            // shouldn't really happen,but is declared as possibility so
            log.error("Failed to serialize Java value as a JSON String", e);
        }
        return result;
    }

    /**
     * Deserialize JSON content from given JSON content String.
     */
    public static <T> T toObject(String content, Class<T> valueType) throws IOException {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            log.error("Can not deserialize JSON content from given JSON content String");
            throw e;
        }
    }
}
