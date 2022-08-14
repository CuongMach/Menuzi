package vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem;

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
public class OrderItemDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long itemId;

    private Long orderId;

    private String categoriesName;

    private String unitName;

    private Double unitPrice;

    private Double quantity;

    private Double total;

    private String note;

    private Integer status;
}
