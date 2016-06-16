package happymeal.entity;

/**
 * Created by anna on 2016-06-15.
 */
public class Tag {

    private String tname;

    public Tag(String tname){
        this.tname = tname;
    }

    public String getTname(){
        return this.tname;
    }

    public void setTname(String tname){
        this.tname = tname;
    }

}
