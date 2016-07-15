package bbm.uw.com.simbbm.item;

/**
 * Created by jiy on 4/4/16.
 */
public class ContactItem {
    //[{"_id":"56fab1c51ca4b91d14e9916d","first_name":"Jone","last_name":"Snow","email":"jsnow0101@uw.ca","start_timestamp":"10:10","contact_kind_1":"cell phone","group_acronym":"group","console_task_display_name":"CT","group_task_name":"CT","group_task_abbreviation":"aa","end_timestamp":"11:10","contact_number_1":"226-876-2234","__v":0},
    private String first_name = "";
    private String last_name = "";
    private String email = "";
    private String group = "";
    private long start_timestamp = 0;
    private long end_timestamp = 0;
    private String contactNumber = "";

    public ContactItem(String first_name, String last_name, String email, String group, long start_timestamp, long end_timestamp, String contactNumber) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.group = group;
        this.start_timestamp = start_timestamp;
        this.end_timestamp = end_timestamp;
        this.contactNumber = contactNumber;
    }

    public ContactItem() {
    }

    public long getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(long start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public long getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(long end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                ", start_timestamp=" + start_timestamp +
                ", end_timestamp=" + end_timestamp +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
