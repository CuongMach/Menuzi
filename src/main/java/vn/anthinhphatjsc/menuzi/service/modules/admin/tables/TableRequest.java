package vn.anthinhphatjsc.menuzi.service.modules.admin.tables;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableRequest {
    private Long storeId;
    private Long brandId;
    @NotBlank(message = "không được để trống code")
    private String code;
    @NotBlank(message = "không được để trống name")
    private String name;

    public TableEntity toEntity() {
        TableEntity entity = new TableEntity();
        entity.setStoreId(this.storeId);
        entity.setBrandId(this.brandId);
        entity.setCode(this.code);
        entity.setName(this.name);
        return entity;
    }

}
