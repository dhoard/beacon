package io.beacon;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

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

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey("sk-test")
                .baseUrl(server.getBaseUrl())
                .build();

        ChatCompletionCreateParams params =
                ChatCompletionCreateParams.builder()
                        .model(ChatModel.GPT_4_1)
                        .addUserMessage("What is the?")
                        .build();

        ChatCompletion completion =
                client.chat().completions().create(params);

        System.out.println(
                completion.choices().get(0).message().content().get());

        Thread.currentThread().join(); // Keep the application alive
    }
}