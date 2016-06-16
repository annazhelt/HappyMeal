package happymeal.entity;

/**
 * Created by anna on 2016-06-15.
 */
public class DishTag {
    private int rid;
    private String dname;
    private String tname;

    public DishTag(int rid, String dname, String tname){
        this.rid = rid;
        this.dname = dname;
        this.tname = tname;
    }

    public void setRid(int rid){
        this.rid = rid;
    }

    public void setDname(String dname){
        this.dname = dname;
    }

    public void setTname(String tname){
        this.tname = tname;
    }

    public String getTname() {
        return tname;
    }

    public String getDname() {
        return dname;
    }


    public int getRid() {
        return rid;
    }

}
