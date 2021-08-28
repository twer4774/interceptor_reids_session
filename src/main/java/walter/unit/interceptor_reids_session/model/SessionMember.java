package walter.unit.interceptor_reids_session.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionMember implements Serializable {

    private String name;

    private int age;
}
