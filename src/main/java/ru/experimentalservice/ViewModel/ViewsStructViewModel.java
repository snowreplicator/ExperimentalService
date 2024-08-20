package ru.experimentalservice.ViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ViewsStructViewModel {
    private List<ViewStructViewModel> views;
}
