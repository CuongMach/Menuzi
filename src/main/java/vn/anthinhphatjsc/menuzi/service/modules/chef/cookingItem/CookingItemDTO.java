package vn.anthinhphatjsc.menuzi.service.modules.chef.cookingItem;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;

import javax.persistence.Column;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CookingItemDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long processStatusId;

    private Long brandId;

    private Long storeId;

    private Long chefId;

    private Long itemId;

    private Double quantity;

    private Double quantityDoing;
}
