package utils;

import com.gihanz.dtos.RssMainDTO;

import javax.xml.bind.*;
import java.io.StringReader;

public class ConvertingXML  {

    public static RssMainDTO xmlToObject(String xml) throws Exception {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RssMainDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (RssMainDTO) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error while converting xml to object", e);
        }
    }
}
