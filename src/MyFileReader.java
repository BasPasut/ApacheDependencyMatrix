import java.io.*;

public class MyFileReader {

    int ce;
    int ca;
    int nc;
    int na;
    String jcdPackage;

    public MyFileReader() {
        ce = 0;
        ca = 0;
        nc = 0;
        na = 0;
        jcdPackage = "";
    }

    public void readFile(String path, String jcdPackage, PackageInfo pai) {
        this.jcdPackage = jcdPackage;


        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                if (line.contains(" class ")) {
                    pai.nc++;
                    break;
                }
            }

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                if (line.contains(" abstract class ")) {
                    pai.na++;
                    break;
                }
            }

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                if (line.contains("import")) {
                    String[] checkImp = line.split(" ");
                    String printto = jcdPackage.replaceAll("/", ".");
                    if (!checkImp[1].contains(printto)) {
                        pai.ce++;
                        break;
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void countCA(String path, String jcdPackage, PackageInfo pai) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                if (line.contains("import ")) {
                    String[] temp = line.split(" ");
                    String startsWith = jcdPackage + pai.packageName;
                    String packageName = startsWith.replace('/', '.');

                    if (temp[1].startsWith(packageName)) {
                        pai.ca++;
                    }
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
