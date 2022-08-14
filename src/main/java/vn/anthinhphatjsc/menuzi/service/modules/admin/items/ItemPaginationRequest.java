package vn.anthinhphatjsc.menuzi.service.modules.admin.items;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.PaginationRequest;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPaginationRequest extends PaginationRequest {
    private String searchUnitName;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchUnitName != null && !searchUnitName.isEmpty()) {
            list.add(new Filter("unitName", QueryOperator.LIKE, searchUnitName, null));
        }
        return list;
    }
}
