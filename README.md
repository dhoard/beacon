# Beacon

> A lightweight Java mock server for testing LLM applications.

Beacon allows you to test AI-powered Java applications without making real API calls. It provides a fluent API to define deterministic responses while remaining compatible with the official Java SDKs of leading LLM providers.

## Features

- Embedded mock AI server
- Compatible with the official OpenAI Java SDK
- Compatible with the official Anthropic (Claude) Java SDK
- Compatible with the official Google Gemini Java SDK
- Rule-based response matching
- Fluent Java API
- Fast local testing without external API calls

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
import static io.beacon.PromptMatchers.*;

MockAIServer server = new MockAIServer();

server.when(contains("refund"))
      .respondWith("Refunds are processed within 30 days.");

server.when(any())
      .respondWith("I don't know the answer.");

server.start();
```

## Supported SDKs

Beacon currently supports the official Java SDKs for:

- OpenAI
- Anthropic (Claude)
- Google Gemini

Simply configure your SDK to use Beacon's base URL instead of the provider's API endpoint.

## Example

### OpenAI

```java
OpenAIClient client = OpenAIOkHttpClient.builder()
        .baseUrl(server.getBaseUrl())
        .apiKey("dummy-key")
        .build();
```

### Claude

```java
AnthropicClient client = AnthropicOkHttpClient.builder()
        .baseUrl(server.getBaseUrl())
        .apiKey("dummy-key")
        .build();
```

### Gemini

```java
Client client = Client.builder()
        .apiKey("dummy-key")
        .httpOptions(HttpOptions.builder()
                .baseUrl(server.getBaseUrl())
                .build())
        .build();
```

After configuring the client, use the SDK normally. Beacon intercepts the requests and returns your mocked responses.

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

## Examples

Complete working examples are available in:

```text
examples/openai-sdk-example
examples/claude-sdk-example
examples/gemini-sdk-example
```

## Roadmap

- ✅ OpenAI Java SDK support
- ✅ Anthropic (Claude) Java SDK support
- ✅ Google Gemini Java SDK support
- ⏳ Streaming responses
- ⏳ Additional matchers
- ⏳ Request verification
- ⏳ Scenario testing
- ⏳ Tool/function calling support
- ⏳ Embeddings support

## Contributing

Contributions, ideas, and bug reports are welcome. Feel free to open an issue or submit a pull request.

## License

MIT License.