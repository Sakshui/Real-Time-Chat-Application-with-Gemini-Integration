package com.sakshisb.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String callGeminiAPI(String userMessage) {
        Map<String, Object> payload = Map.of(
                "contents", List.of(Map.of("parts", List.of(Map.of("text", userMessage)))),
                "generationConfig", Map.of("temperature", 0.7, "maxOutputTokens", 80)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    GEMINI_API_URL + "?key=" + googleApiKey,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                JsonNode root = objectMapper.readTree(response.getBody());
                if (root.has("candidates")) {
                    for (JsonNode candidate : root.path("candidates")) {
                        JsonNode content = candidate.path("content").path("parts");
                        for (JsonNode part : content) {
                            if (part.has("text")) {
                                return part.path("text").asText();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return "Error fetching Gemini response: " + e.getMessage();
        }
        return "Sorry, Gemini couldn't process the request.";
    }
}
