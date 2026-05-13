package com.lp.projects.lovable_clone.entity;

import com.lp.projects.lovable_clone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

//@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;

    ChatSession chatSession;

    String content;

    MessageRole role;

    String toolCalls; // JSON array of tools called

    Integer tokensUsed;

    Instant createdAt;
}
