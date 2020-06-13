package org.swiftmxtransformer.mx;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.kafka.streams.kstream.ValueMapperWithKey;
import org.swiftmxtransformer.model.MxType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.w3c.dom.Node.TEXT_NODE;

@RequiredArgsConstructor
public class MxTransformer implements ValueMapperWithKey<MxType, String, String> {

  private final Map<MxType, List<String>> typeToQueries;
  private final DocumentBuilderFactory documentBuilderFactory;
  private final TransformerFactory transformerFactory;

  @SneakyThrows
  @Override
  public String apply(final MxType mxType, final String mx) {
    return Optional.ofNullable(typeToQueries.get(mxType))
        .map(strings -> strings.stream().reduce(mx, this::transform))
        .orElse(mx);
  }

  @SneakyThrows
  private String transform(final String mx, final String query) {
    String transformed = mx;
    final Document xmlDocument =
        documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(mx)));
    final XPath xpath = XPathFactory.newInstance().newXPath();
    final NodeList nodeList = (NodeList) xpath.evaluate(query, xmlDocument, XPathConstants.NODESET);
    if (nodeList.getLength() > 0) {
      IntStream.range(0, nodeList.getLength())
          .forEach(
              i -> {
                final Node node = nodeList.item(i);
                Optional.ofNullable(node.getFirstChild())
                    .ifPresent(
                        child -> {
                          if (child.getNodeType() == TEXT_NODE) {
                            final String nodeValue = child.getNodeValue();
                            // Note: length 64 hash can invalidate mx
                            // format preserving encryption method needed
                            final String hash = DigestUtils.sha256Hex(nodeValue);
                            node.setTextContent(hash);
                          }
                        });
              });
      final StringWriter stringWriter = new StringWriter();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      transformer.transform(new DOMSource(xmlDocument), new StreamResult(stringWriter));
      transformed = stringWriter.getBuffer().toString();
    }
    return transformed;
  }
}
