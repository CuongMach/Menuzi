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
@Table(name = "stores")
@EqualsAndHashCode(callSuper = true)
public class StoreEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long brandId;
    private String name;
    private String address;
    private String phone;
    private String videoUrl;
    private String image;

    public void setData(StoreEntity request) {
        this.brandId = request.getBrandId();
        this.name = request.getName();
        this.address = request.getAddress();
        this.phone = request.getPhone();
        this.videoUrl = request.getVideoUrl();
        this.image = request.getImage();
    }
}
