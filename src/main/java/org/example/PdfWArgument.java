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
            else if ("-h".equals(args[i]) || "-help".equals(args[i])) {
                Help();
                System.exit(0);
            }

        }



        if (pdfPath == null || password == null) {
//            System.out.println("Usage: java PDFEncryptionExample -p <path_to_pdf> -pass <password>");
            Help();
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

    public static void Help() {
        String description =Colors.YELLOW+"DESCRIPTION:\n "+Colors.GREEN+
                "\tThis is a straightforward Java command-line tool developed by the LeonteqSecurity team for encrypting PDF files using the Apache PDFBox library. It enables you to protect your PDF documents with a password and specify the encryption key length.\n";

        String previewUse = Colors.YELLOW+"USAGE:\n"+Colors.GREEN+
                "\tjava -jar PDFENCRYPTOR-1.0-SNAPSHOT.jar [Options] {value}\n";

        String example =Colors.YELLOW+ "EXAMPLE:\n"+Colors.GREEN+
                "\tjava -jar -pass password123 -p \"/home/leon/leonteqsecurity/files/myfile.pdf\"\n";

        String arguments =Colors.YELLOW+ "OPTIONS:\n" +Colors.GREEN+
                "\t-p: Specifies the path to your document or directory. This argument can take the PDF file or directory. If a directory is specified, all PDF documents within it will be listed, and you will be prompted to choose the one you want to encrypt.\n" +
                "\t-pass: Specifies the password with which you want to encrypt the document.\n" +
                "\t-h | -help: Displays this man page\n";
        System.out.println(description+previewUse+arguments+example);
    }


}
