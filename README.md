# Mx Transformer

Kafka Streams pipeline showing an easy processing flow for Swift Mx messages.
The following operations are executed by this flow:
- parsing and validation of messages against xsd schemas
- transformation of various fields

In application.yml is described the mapping between what messages are supported and their schemas; there are configured also the xpath queries
for what elements need to be transformed before being sent to an output topic.

