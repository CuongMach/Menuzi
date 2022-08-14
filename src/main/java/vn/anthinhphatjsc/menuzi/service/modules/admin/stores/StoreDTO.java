package vn.anthinhphatjsc.menuzi.service.modules.admin.stores;

import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;
import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StoreDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long brandId;
    private String name;
    private String address;
    private String phone;
    private String videoUrl;
    private String image;
}
