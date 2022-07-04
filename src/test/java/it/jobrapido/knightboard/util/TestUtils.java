package it.jobrapido.knightboard.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readJson(String fileName, TypeReference<T> type) throws Exception {
        return objectMapper.readValue(readFileAsString(fileName), type);
    }

    public static String readFileAsString(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(TestUtils.class.getResourceAsStream(fileName)), StandardCharsets.UTF_8));
        for (int c = br.read(); c != -1; c = br.read()) {
            sb.append((char) c);
        }
        return sb.toString();
    }
}
