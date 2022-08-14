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
@Table(name = "brands")
@EqualsAndHashCode(callSuper = true)
public class BrandEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String image;
    public void setData(BrandEntity request) {
        this.name = request.getName();
        this.image = request.getImage();
    }
}
