import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Status status; //is ENUM

    @Column
    private Priority priority; //is ENUM

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @Column
    private Date finished_at;

    @Column
    private long user_id;


}