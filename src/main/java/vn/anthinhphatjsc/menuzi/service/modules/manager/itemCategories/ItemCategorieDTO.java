package vn.anthinhphatjsc.menuzi.service.modules.manager.itemCategories;

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
public class ItemCategorieDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long storeId;
    private Long brandId;
    private String name;
    private String image;
}
