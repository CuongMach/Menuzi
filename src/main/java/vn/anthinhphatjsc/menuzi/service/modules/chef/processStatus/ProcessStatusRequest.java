package vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus;

import lombok.*;
import org.apache.tomcat.util.net.jsse.PEMFile;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessStatusRequest {

    private Long orderItemId;

    private Long orderId;

    private Long itemId;

    private Double quantity;

    private Integer status;

    public ProcessStatusEntity toEntity(){
        ProcessStatusEntity entity = new ProcessStatusEntity();
        entity.setOrderId(this.orderItemId);
        entity.setOrderId(this.orderId);
        entity.setItemId(this.itemId);
        entity.setQuantity(this.quantity);
        entity.setStatus(this.status);
        return entity;
    }
}
