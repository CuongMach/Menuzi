package vn.anthinhphatjsc.menuzi.service.entities;

import vn.anthinhphatjsc.menuzi.service.core.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String code;

    public void setData(RoleEntity request) {
        this.name = request.getName();
    }



}
