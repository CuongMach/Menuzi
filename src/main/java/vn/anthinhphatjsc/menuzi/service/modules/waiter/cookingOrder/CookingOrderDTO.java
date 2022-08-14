package vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder;

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
public class CookingOrderDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long cookingItemId;

    private Long itemId;

    private Long orderItemId;
}
