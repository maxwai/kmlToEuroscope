package de.MaxWai.Main;

import de.MaxWai.Interpreter.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class KmlToFreeText {

    private static File working_directory;
    private static final FilenameFilter onlyKMLFilesFilter = (root, child) -> !new File(root, child).isDirectory() && !new File(root, child).isHidden() && child.endsWith(".kml");

    public static void main(String[] args) throws URISyntaxException, IOException {
        working_directory = new File(KmlToFreeText.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        if (args != null && args.length > 0) {
            File tempPath = new File(args[0]);
            if (tempPath.exists() && tempPath.isDirectory()) {
                working_directory = tempPath;
            }
        }
        File kmlFile = findKmlFile();
        LinkedList<Point> points = parseXML(kmlFile);
        writeESEFile(points, kmlFile.getName().replace(".kml", ""));
    }

    private static LinkedList<Point> parseXML(File kmlFile) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(kmlFile);
            doc.getDocumentElement().normalize();
            LinkedList<Point> points = new LinkedList<Point>();
            NodeList nodeList = doc.getElementsByTagName("Placemark");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    Node eNode = eElement.getElementsByTagName("Point").item(0);
                    if (eNode != null) {
                        Element e2Element = (Element) eNode;
                        Node e2Node = e2Element.getElementsByTagName("coordinates").item(0);
                        points.add(new Point(eElement.getElementsByTagName("name").item(0).getTextContent(), e2Node.getTextContent()));
                    }
                }
            }
            return points;
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException("KML file not rightly parsed");
        }
    }

    private static File findKmlFile() {
        List<File> files = Arrays.stream(working_directory.list((onlyKMLFilesFilter))).map(child -> new File(working_directory, child)).collect(Collectors.toList());
        String listOfFiles = "Path scanned: " + working_directory + "\n";
        for (int i = 0; i < files.size(); i++) {
            listOfFiles += "(" + (i + 1) + ") " + files.get(i).getName() + "\n";
        }
        listOfFiles += "(" + (files.size() + 1) + ") scan other path" + "\n";
        System.out.println(listOfFiles);
        int choice = chooseFile(false);
        while (choice <= 0 || choice > files.size() + 1) {
            clearConsole();
            System.out.println(listOfFiles);
            choice = chooseFile(true);
        }
        if (choice == files.size() + 1) {
            clearConsole();
            File newPath = chooseNewPath(false);
            while (newPath == null || !newPath.isDirectory()) {
                clearConsole();
                newPath = chooseNewPath(true);
            }
            working_directory = newPath;
            return findKmlFile();
        }
        return files.get(choice - 1);
    }

    private static void writeESEFile(LinkedList<Point> points, String filename) {
        OutputStream os;
        try {
            os = new FileOutputStream(filename + ".ese");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            for (Point point: points) {
                writer.write(point.getParsedData("temp") + "\n");
            }
            writer.close();
            os.close();
        } catch (FileNotFoundException e) {
            System.err.println("couldn't create the" + filename + ".ese File");
        } catch (IOException e) {
            System.err.println("couldn't write to the File " + filename + ".ese ");
        }
    }

    private static int chooseFile(boolean repeat) {
        if (repeat)
            System.out.println("Please enter a valid number");
        System.out.print("Please choose the file wanted: ");
        Scanner scan = new Scanner(System.in);
        try {
            return scan.nextInt();
        } catch (InputMismatchException ignored) {
            return -1;
        }
    }

    private static File chooseNewPath(boolean repeat) {
        if (repeat)
            System.out.println("Please enter a valid folder path");
        System.out.print("Please enter the path to the folder where the file is located: ");
        Scanner scan = new Scanner(System.in);
        File newPath = new File(scan.next());
        if (newPath.exists())
            return newPath;
        return null;
    }

    private static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException ignored) {
        }
    }
}
