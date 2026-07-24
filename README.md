# Beacon

> A lightweight Java mock server for testing LLM applications.

Beacon allows you to test AI-powered Java applications without making real API calls. It provides a fluent API to define deterministic responses while remaining compatible with the official OpenAI Java SDK.

## Features

* Embedded mock AI server
* Compatible with the official OpenAI Java SDK
* Rule-based response matching
* Fluent Java API
* Fast local testing without external API calls

## Installation

### Maven

Add the JitPack repository:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the Beacon dependency:

```xml
<dependency>
    <groupId>com.github.kbpramod</groupId>
    <artifactId>beacon</artifactId>
    <version>v0.0.1</version>
</dependency>
```

## Quick Start

Create and start a mock server.

```java
import static io.beacon.matchers.PromptMatchers.*;

MockAIServer server = new MockAIServer();

server.when(contains("refund"))
      .respondWith("Refunds are processed within 30 days.");

server.when(any())
      .respondWith("I don't know the answer.");

server.start();
```

## Using with the OpenAI Java SDK

Point the OpenAI client to Beacon instead of the OpenAI API.

```java
OpenAIClient client = OpenAIOkHttpClient.builder()
        .baseUrl(server.getBaseUrl())
        .apiKey("dummy-key")
        .build();
```

Now any request sent through the SDK will be handled by Beacon.

```java
ChatCompletion chatCompletion = client.chat().completions().create(request);

String response = chatCompletion.choices()
        .getFirst()
        .message()
        .content()
        .orElse("");

System.out.println(response);
```

Output:

```text
Refunds are processed within 30 days.
```

## Matchers

### Contains Matcher

```java
server.when(contains("refund"))
      .respondWith("30 day refund policy.");
```

### Default Matcher

```java
server.when(any())
      .respondWith("I don't know.");
```

## Example

A complete working example is available in:

```text
examples/openai-sdk-example
```

## Roadmap

* ✅ OpenAI Java SDK support
* ⏳ Claude support
* ⏳ Gemini support
* ⏳ Additional matchers
* ⏳ Streaming responses
* ⏳ Scenario testing
* ⏳ Request verification

## Contributing

Contributions, ideas, and bug reports are welcome. Feel free to open an issue or submit a pull request.

## License

MIT License.
