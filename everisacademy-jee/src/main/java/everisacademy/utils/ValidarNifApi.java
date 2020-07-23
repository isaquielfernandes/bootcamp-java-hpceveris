package everisacademy.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * @author Isaquiel Fernandes
 * Class para validar nif atraves de um chamada a servico web
 */
public class ValidarNifApi {
	
	private static final Logger logger = Logger.getLogger(ValidarNifApi.class);

	private ValidarNifApi() {
		
	}
	public static String validar(String wsEndPoint, String xmlInput) {
		// Code to make a webservice HTTP request
		String formattedSOAPResponse = null;
		try {
			String responseString = "";
			String outputString = "";
			URL url;

			url = new URL(wsEndPoint);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();

			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			String SOAPAction = "getServiceRequest";
			httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", SOAPAction);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			// Write the content of the request to the outputstream of the HTTP
			// Connection.
			out.write(b);
			out.close();
			// Ready with sending the request.
			// Read the response.
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
			BufferedReader in = new BufferedReader(isr);
			// Write the SOAP message response to a String.
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			// Write the SOAP message formatted to the console.
			formattedSOAPResponse = formatXML(outputString);
			
		} catch (IOException e) {
			logger.error(e);
			formattedSOAPResponse = null;
		}
		return formattedSOAPResponse;
	}

	// format the XML in pretty String
	private static String formatXML(String unformattedXml) {
		try {
			Document document = parseXmlFile(unformattedXml);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 3);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			transformer.transform(source, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}

	// parse XML
	private static Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getResponse(String soap) {
		String res = null;
		try {
			MessageFactory mf = MessageFactory.newInstance();
			
			SOAPMessage soapMesC = mf.createMessage(new MimeHeaders(),
					new ByteArrayInputStream(soap.getBytes(Charset.forName("UTF-8"))));
			
			SOAPBody soapBody = soapMesC.getSOAPBody();
			NodeList nodeList = soapBody.getElementsByTagName("getServiceResponse");
			NodeList nodeList2 = nodeList.item(0).getChildNodes();
			
			res = nodeList2.item(1).getTextContent();
		} catch (SOAPException | IOException e) {
			logger.error(e);
			res = null;
		}
		return res;
	}
}
