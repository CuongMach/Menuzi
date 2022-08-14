package vn.anthinhphatjsc.menuzi.service.modules.admin.itemCategories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.PaginationRequest;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoriesPaginationRequest extends PaginationRequest {
    private String searchName;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchName != null && !searchName.isEmpty()) {
            list.add(new Filter("name", QueryOperator.LIKE, searchName, null));
        }
        return list;
    }
}
