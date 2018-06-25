package ch.cpnv.angrywirds.Models.Data;

public class PostAssignmentsDatas {

    public int assignment_id;
    public String token;
    public int result;

    //To send value to backend
    public PostAssignmentsDatas(int assignment_id, int result, String token){
        this.assignment_id = assignment_id;
        this.token = token;
        this.result = result;
    }
}
