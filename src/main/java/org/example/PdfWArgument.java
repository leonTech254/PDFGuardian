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
        String pdfPath = null;
        String password = null;

        for (int i = 0; i < args.length; i++) {
            if ("-p".equals(args[i])) {
                if (i + 1 < args.length) {
                    pdfPath = args[i + 1];
                } else {
                    System.out.println("Missing PDF file path.");
                    return;
                }
            } else if ("-pass".equals(args[i])) {
                if (i + 1 < args.length) {
                    password = args[i + 1];
                } else {
                    System.out.println("Missing password.");
                    return;
                }
            }
        }

        if (pdfPath == null || password == null) {
            System.out.println("Usage: java PDFEncryptionExample -p <path_to_pdf> -pass <password>");
            return;
        }

        try {
            PDDocument document = PDDocument.load(new File(pdfPath));

            StandardProtectionPolicy policy = new StandardProtectionPolicy(password, password, new AccessPermission());
            policy.setEncryptionKeyLength(128); // Set encryption key length

            document.protect(policy);

            String outputFilePath = "encrypted_" + new File(pdfPath).getName();
            document.save(outputFilePath);

            document.close();

            System.out.println("PDF file encrypted successfully and saved as " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadSecurityHandlerException e) {
            throw new RuntimeException(e);
        } catch (COSVisitorException e) {
            throw new RuntimeException(e);
        }
    }
}
