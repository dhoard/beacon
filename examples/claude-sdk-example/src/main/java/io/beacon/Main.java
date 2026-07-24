package io.beacon;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.Model;


import java.io.IOException;

import static io.beacon.PromptMatchers.any;
import static io.beacon.PromptMatchers.contains;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        MockAIServer server = new MockAIServer();
        server.start();

        server.when(contains("refund"))
                .respondWith("Your refund policy is you get you full refund after deducting 30% for charge");

        server.when(contains("weather"))
                .respondWith("Today's weather is sunny.");

        server.when(any())
                .respondWith("I don't know the answer.");

        AnthropicClient client = AnthropicOkHttpClient.builder()
                .apiKey("dummy-key")
                .baseUrl(server.getBaseUrl())
                .build();

        MessageCreateParams params = MessageCreateParams.builder()
                .model(Model.CLAUDE_FABLE_5)
                .maxTokens(1024)
                .addUserMessage("What is the refund policy?")
                .build();

        Message message = client.messages().create(params);

        String text = message.content()
                .get(0)
                .asText()
                .text();

        System.out.println(text);

        Thread.currentThread().join(); // Keep the application alive
    }
}