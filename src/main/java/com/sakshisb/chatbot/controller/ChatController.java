package com.sakshisb.chatbot.controller;

import com.sakshisb.chatbot.model.ChatMessage;
import com.sakshisb.chatbot.service.GeminiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GeminiService geminiService;

    public ChatController(SimpMessagingTemplate messagingTemplate, GeminiService geminiService) {
        this.messagingTemplate = messagingTemplate;
        this.geminiService = geminiService;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/public", chatMessage);

        if (chatMessage.getContent().startsWith("@gemini:")) {
            String prompt = chatMessage.getContent().replace("@gemini:", "").trim();
            String geminiReply = geminiService.callGeminiAPI(prompt);

            ChatMessage geminiMessage = new ChatMessage("Gemini", geminiReply);
            messagingTemplate.convertAndSend("/topic/public", geminiMessage);
        }
    }
}