package ru.experimentalservice.Service.Common.Specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;
}
