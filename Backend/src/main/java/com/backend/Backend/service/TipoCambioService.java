package com.backend.Backend.service;

import com.backend.Backend.entity.TipoCambio;
import com.backend.Backend.repository.TipoCambioRepository;
import dto.MessageDto;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TipoCambioService {

    private final TipoCambioRepository repository;
//1
    private static final String SOAP_ENDPOINT = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";
    private static final String SOAP_ACTION = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";
//2
    private static final String REQUEST_XML =
            """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
                           xmlns:web="http://www.banguat.gob.gt/variables/ws/">
               <soap:Body>
                  <web:TipoCambioDia/>
               </soap:Body>
            </soap:Envelope>
            """;

    public TipoCambioService(TipoCambioRepository repository) {
        this.repository = repository;
    }

//3
    public MessageDto consultarTipoCambio() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(SOAP_ENDPOINT).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("Accept", "text/xml");
            connection.setRequestProperty("SOAPAction", SOAP_ACTION);
            connection.setDoOutput(true);
//4
            try (OutputStream os = connection.getOutputStream()) {
                os.write(REQUEST_XML.getBytes(StandardCharsets.UTF_8));
            }
//5
            int status = connection.getResponseCode();
            InputStream responseStream = status == 200 ? connection.getInputStream() : connection.getErrorStream();
            String xml = new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);

            if (status != 200) {
                return new MessageDto(false, "Error HTTP " + status, xml);
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
//6
            XPath xPath = XPathFactory.newInstance().newXPath();
            String fechaStr = xPath.evaluate("//*[local-name()='fecha']/text()", doc).trim();
            String referenciaStr = xPath.evaluate("//*[local-name()='referencia']/text()", doc).trim();

            if (fechaStr.isEmpty() || referenciaStr.isEmpty()) {
                return new MessageDto(false, "No se encontró <fecha> o <referencia> en el XML.");
            }
//7
            LocalDate fechaCambio = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            BigDecimal tipoCambio = new BigDecimal(referenciaStr);
//8
            TipoCambio tc = new TipoCambio();
            tc.setFechaConsulta(LocalDateTime.now());
            tc.setFechaTipoCambio(fechaCambio);
            tc.setTipoCambio(tipoCambio);
            tc.setOrigenApi("TipoCambioDia");

            TipoCambio guardado = repository.save(tc);
//9
            return new MessageDto(true, "Consulta exitosa", guardado);

        } catch (Exception e) {
            return new MessageDto(false, "Error al consumir SOAP: " + e.getMessage());
        }
    }


    public List<TipoCambio> listar() {
        return repository.findAll();
    }
}
