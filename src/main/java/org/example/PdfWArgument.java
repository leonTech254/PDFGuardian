package org.example;

import org.pdfbox.exceptions.COSVisitorException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.encryption.AccessPermission;
import org.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class PdfWArgument {
    public static void main(String[] args) {
        if (args.length != 4 || !args[0].equals("-p") || !args[2].equals("-pass")) {
            System.out.println("Usage: java PDFEncryptionExample -p <path_to_pdf> -pass <password>");
            return;
        }

        String pdfPath = args[1];
        String password = args[3];

        try {
            PDDocument document = PDDocument.load(new File(pdfPath));

            StandardProtectionPolicy policy = new StandardProtectionPolicy(password, password, new AccessPermission());
            policy.setEncryptionKeyLength(128); // Set encryption key length

            document.protect(policy);

            String outputFilePath = "encrypted_" + new File(pdfPath).getName();
            document.save(outputFilePath);

            document.close();

            System.out.println("PDF file encrypted successfully and saved as k" + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadSecurityHandlerException e) {
            throw new RuntimeException(e);
        } catch (COSVisitorException e) {
            throw new RuntimeException(e);
        }
    }
}
