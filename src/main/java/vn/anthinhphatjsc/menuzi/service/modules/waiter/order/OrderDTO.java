package vn.anthinhphatjsc.menuzi.service.modules.waiter.order;

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
public class OrderDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long tableId;
    private Long storeId;
    private Long brandId;
    private String code;
    private Double total;
    private Integer status;
    private String customer_name;
    private String customer_phone;
}
