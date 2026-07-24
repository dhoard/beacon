package io.beacon;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpOptions;

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

        Client client = Client.builder()
                .apiKey("test-key")
                .httpOptions(
                        HttpOptions.builder()
                                .baseUrl(server.getBaseUrl())
                                .build())
                .build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "What is the?",
                        null);

        System.out.println(response.text());

        Thread.currentThread().join(); // Keep the application alive
    }
}
