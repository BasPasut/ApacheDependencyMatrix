public class PackageInfo {

    String  packageName;
    int ca;
    int ce;
    int na;
    int nc;

    public PackageInfo(){
        ca = 0;
        ce = 0;
        na = 0;
        nc = 0;
    }

    public double getInstability(){
        return (double)ce/((double)ca+(double)ce);
    }

    public double getAbstractness(){
        return (double)na/(double)nc;
    }
}
