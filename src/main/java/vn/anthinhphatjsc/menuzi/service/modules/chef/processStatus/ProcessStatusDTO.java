package vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus;

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
public class ProcessStatusDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long orderItemId;

    private Long orderId;

    private Long itemId;

    private Double quantity;

    private Integer status;
}
