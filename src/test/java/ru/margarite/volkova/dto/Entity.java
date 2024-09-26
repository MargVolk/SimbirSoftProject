package ru.margarite.volkova.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Entity {
    private final Addition addition;
    @SerializedName("important_numbers")
    private final List<Integer> importantNumbers;
    private final String title;
    private final boolean verified;
    private String id;
}