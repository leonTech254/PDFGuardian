package org.example.Test;

import java.io.File;

public class ReadFolderItems {
    static String path="/home/leon/";
  public  static  void main(String[] args)
  {
      File directory = new File(path);
      if(directory.isDirectory())
      {
          File[] files=directory.listFiles();
          if(files!=null)
          {
              for(File file:files)
              {
                  if(file.isFile())
                  {
                      System.out.println(file);
                      
                  }
              }
          }
      }


  }


}
