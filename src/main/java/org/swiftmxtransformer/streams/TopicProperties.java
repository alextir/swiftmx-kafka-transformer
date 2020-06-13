package org.swiftmxtransformer.streams;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicProperties {
    @NotBlank private String swiftMxInboundTopic;
    @NotBlank private String swiftMxEventTopic;
}
