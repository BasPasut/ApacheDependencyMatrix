import java.io.*;

public class Utils {

    final static String jcdPath = "C:\\Users\\BasPasut\\Downloads\\apache-log4j-2.11.1-src\\apache-log4j-2.11.1-src\\log4j-core\\src\\main\\java\\";
    final static String jcdPackage = "org/apache/logging/log4j/core/";
    final static String packageName = "script";

    public static void main(String[] args){
        File file = new File(jcdPath + jcdPackage + packageName);

        PackageInfo pai = new PackageInfo();
        pai.packageName = packageName;

        //Get list of files and store in our array
        String[] jcdFiles = file.list();

        //Create fileReader
        MyFileReader fr = new MyFileReader();

        //Print out number of files
        System.out.println("Files Found: " + jcdFiles.length);

        //Add blank line
        System.out.println("");

        //Enhance loop through all files in the directory or folder
        for (String myFile : jcdFiles) {
            if(myFile.contains(".java")) {
                fr.readFile(file.getPath()+ "/" + myFile , jcdPackage+packageName, pai);
            }
            else{
                recurFolder(file.getPath(), myFile,pai);
            }
        }


        File fileCA = new File(jcdPath);
        String[] jcdFiles2 = fileCA.list();
        for (String myFile : jcdFiles2) {
            if(myFile.contains(".java")){
                fr.countCA(fileCA.getPath()+ "/" + myFile , jcdPackage+packageName, pai);
            }
            else{
                recurCa(fileCA.getPath(),myFile,jcdPackage,pai);
            }
        }


        System.out.println("Package: " + file.getName());
        System.out.println("CA: " + pai.ca);
        System.out.println("CE: " + pai.ce);
        System.out.println("NA: " + pai.na);
        System.out.println("NC: " + pai.nc);
        System.out.println("Instability: " + pai.getInstability());
        System.out.println("Abstractness: " + pai.getAbstractness());
    }

    public static void recurCa(String path, String folder, String outerPackage, PackageInfo pai){
        String newPath = path+"/"+folder;
        File jcdFile = new File(newPath);
        String[] jcdFiles = jcdFile.list();
        MyFileReader mfr = new MyFileReader();
        for(String myFile : jcdFiles)
        {
            if(myFile.endsWith(".java")){
                mfr.countCA(jcdFile.getPath()+"/"+myFile, outerPackage,pai);
            }
            else if(!myFile.contains(".")){
                recurCa(jcdFile.getPath(), myFile, outerPackage, pai);
            }
        }
    }

    public static void recurFolder(String path, String folder, PackageInfo pai){
        String newPath = path+"/"+folder;
        File jcdFile = new File(newPath);
        String[] jcdFiles = jcdFile.list();
        MyFileReader mfr = new MyFileReader();
        for(String myFile : jcdFiles)
        {
            if(myFile.endsWith(".java")){
                mfr.readFile(jcdFile.getPath()+"/"+myFile,jcdPackage,pai);
            }
            else if(!myFile.contains(".")){
                recurFolder(jcdFile.getPath(), myFile, pai);
            }
        }
    }

}
