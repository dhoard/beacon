# Beacon

> A lightweight Java mock server for testing LLM applications.

Beacon allows you to test AI-powered Java applications without making real API calls. It provides a fluent API to define deterministic responses and simulate common LLM failures while remaining compatible with the official Java SDKs of leading LLM providers.

## Features

* Embedded mock AI server
* Compatible with the official OpenAI Java SDK
* Compatible with the official Anthropic (Claude) Java SDK
* Compatible with the official Google Gemini Java SDK
* Rule-based response matching
* Fluent Java API
* Fault injection for testing LLM response handling
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
    <version>v0.0.2</version>
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

## Fault Injection

Beacon can simulate common LLM output issues to help validate your application's response handling.

### Empty Response

```java
server.when(any())
      .respondWith("Hello")
      .respondFault(Fault.emptyResponse());
```

### Invalid JSON

```java
server.when(any())
      .respondWith("""
          {
            "result": true
          }
          """)
      .respondFault(Fault.invalidJson());
```

### Truncated JSON

```java
server.when(any())
      .respondWith("""
          {
            "result": true
          }
          """)
      .respondFault(Fault.truncatedJson());
```

### Markdown JSON

```java
server.when(any())
      .respondWith("""
          {
            "result": true
          }
          """)
      .respondFault(Fault.markdownJson());
```

### Supported Faults

* `Fault.none()`
* `Fault.emptyResponse()`
* `Fault.invalidJson()`
* `Fault.truncatedJson()`
* `Fault.markdownJson()`

## Supported SDKs

Beacon currently supports the official Java SDKs for:

* OpenAI
* Anthropic (Claude)
* Google Gemini

Simply configure your SDK to use Beacon's base URL instead of the provider's API endpoint.

## SDK Configuration

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

After configuring the client, use the SDK normally. Beacon intercepts requests and returns the mocked responses.

## Matchers

### Contains Matcher

```java
server.when(contains("refund"))
      .respondWith("Refunds are processed within 30 days.");
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

### v0.0.1

* ✅ Embedded mock server
* ✅ Rule-based response matching
* ✅ OpenAI Java SDK support
* ✅ Anthropic Java SDK support
* ✅ Google Gemini Java SDK support

### v0.0.2

* ✅ Fault injection
* ✅ Empty response simulation
* ✅ Invalid JSON simulation
* ✅ Truncated JSON simulation
* ✅ Markdown JSON simulation

### Planned

* ⏳ JUnit 5 integration
* ⏳ Fault test suites
* ⏳ Streaming responses
* ⏳ Additional matchers
* ⏳ Request verification
* ⏳ Scenario testing
* ⏳ Tool/function calling support
* ⏳ Embeddings support

## Contributing

Contributions, ideas, and bug reports are welcome. Feel free to open an issue or submit a pull request.

## License

MIT License.
