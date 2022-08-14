package vn.anthinhphatjsc.menuzi.service.modules.admin.items;

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
public class ItemDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long categorieId;
    private Long storeId;
    private Long brandId;
    private String unitName;
    private String unitPrice;
    private String image;
    private String videoUrl;
    private String description;
    private Integer status;
}
