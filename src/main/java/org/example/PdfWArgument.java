package org.example;

import org.example.Assets.Colors;
import org.pdfbox.exceptions.COSVisitorException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.encryption.AccessPermission;
import org.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PdfWArgument {

    public static void banner()
    {
        System.out.println(Colors.Banner());
    }

    public static void main(String[] args) {
        banner();

        String pdfPath = null;
        String password = null;

        for (int i = 0; i < args.length; i++) {
            if ("-p".equals(args[i])) {
                if (i + 1 < args.length) {
                    pdfPath = args[i + 1];
                    boolean isFolder=checkIfDirectory(pdfPath);
                    if (!isFolder)
                    {
                     pdfPath=ListPdfs(pdfPath);
                    }

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

    public static boolean checkIfDirectory(String path)
    {
        File directory = new File(path);
        if(directory.isDirectory())
        {
            return false;
        }else
        {
            return true;
        }
    }

    public static String ListPdfs(String path)
    {
        Scanner scanner=new Scanner(System.in);
        File directory= new File(path);
        File[] files=directory.listFiles();
        assert files != null;
        int pdfnumber=0;
        for(File file:files)
        {
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex>0)
            {
                pdfnumber++;
                String extension = fileName.substring(dotIndex + 1);
                if(extension.equals("pdf"))
                {
                    System.out.println(Colors.BRIGHT_GREEN+pdfnumber+Colors.RESET+". "+ Colors.BLUE +file.getName()+Colors.RESET);
                }
            }

        }
        System.out.println("Enter File to Encrypt: ".toUpperCase());
        int filetoEncrypt=scanner.nextInt();
        scanner.close();
        if(files.length<filetoEncrypt)
        {
            System.out.println(Colors.RED+"[!] Invalid file"+Colors.RESET);
            System.exit(0);
        }
        return files[filetoEncrypt].toString();
    }

}
