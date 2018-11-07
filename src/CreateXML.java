import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreateXML {
    public void create(ArrayList<File> listOfFiles) {
        try {
            int count = 0 ;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = factory.newDocumentBuilder().newDocument();


            String lengthOfFile = String.valueOf(listOfFiles.get(0).length());


            Element root = doc.createElement("root");
            //root.setAttribute("xmlns", "http://www.javacore.ru/schemas/");
            doc.appendChild(root);

            Element item0 = doc.createElement("file");
            root.appendChild(item0);

            Element item1 = doc.createElement("name");
            item1.appendChild(doc.createTextNode(listOfFiles.get(0).getName()));
            item0.appendChild(item1);

            Element item2 = doc.createElement("path");
            item2.appendChild(doc.createTextNode(listOfFiles.get(0).getAbsolutePath()));
            item0.appendChild(item2);

            Element item3 = doc.createElement("size");
            item3.appendChild(doc.createTextNode(lengthOfFile));
            item0.appendChild(item3);

            Element item4 = doc.createElement("datetime");
            item4.appendChild(doc.createTextNode("!"));
            item0.appendChild(item4);

            File fileCreate = new File("D:\\06,11,18\\project1\\src\\file.xml");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(fileCreate));
            for (File file : listOfFiles) {
                addNewBranch(listOfFiles,count);
                count++;
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewBranch(ArrayList<File> list,int count) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String lengthOfFile = String.valueOf(list.get(count).length());
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = documentBuilder.parse("D:\\06,11,18\\project1\\src\\file.xml");
        Node root = document.getDocumentElement();
        Element book = document.createElement("file");


        Element title = document.createElement("name");
        title.appendChild(document.createTextNode(list.get(count).getName()));
        book.appendChild(title);

        Element author = document.createElement("path");
        author.appendChild(document.createTextNode(list.get(count).getAbsolutePath()));
        book.appendChild(author);

        Element date = document.createElement("size");
        date.appendChild(document.createTextNode(lengthOfFile));
        book.appendChild(date);

        Element isbn = document.createElement("datetime");
        isbn.appendChild(document.createTextNode("!!!"));
        book.appendChild(author);

        book.appendChild(title);
        book.appendChild(author);
        book.appendChild(date);
        book.appendChild(isbn);
        root.appendChild(book);

        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("D:\\06,11,18\\project1\\src\\file.xml");
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);

    }
}
