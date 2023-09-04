package org.example;
import org.pdfbox.exceptions.COSVisitorException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.encryption.AccessPermission;
import org.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            String baseURl="/home/leon/workspace/test/";
            String filename="test.pdf";
            String documentUrl=baseURl+filename;
            PDDocument document = PDDocument.load(new File(documentUrl));

            StandardProtectionPolicy policy = new StandardProtectionPolicy("myPassword", "myOwnerPassword", new AccessPermission());
            policy.setEncryptionKeyLength(128);

            document.protect(policy);

            document.save(baseURl+"encrypted_example.pdf");

            document.close();

            System.out.println("PDF file encrypted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadSecurityHandlerException e) {
            throw new RuntimeException(e);
        } catch (COSVisitorException e) {
            throw new RuntimeException(e);
        }
    }
}