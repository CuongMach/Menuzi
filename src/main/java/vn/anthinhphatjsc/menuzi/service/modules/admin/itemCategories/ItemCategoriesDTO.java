package vn.anthinhphatjsc.menuzi.service.modules.admin.itemCategories;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemCategoriesDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long storeId;
    private Long brandId;
    private String name;
    private String image;
}
