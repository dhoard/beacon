# Beacon

A lightweight Java mock server for testing LLM applications.

## Features

- Official OpenAI Java SDK compatible
- Embedded HTTP server
- Fluent API
- Rule-based responses

## Example

```java
MockAiServer server = new MockAiServer();

server.when(contains("refund"))
      .respondWith("30 days");

server.when(any())
      .respondWith("I don't know.");

server.start();