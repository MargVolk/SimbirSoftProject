package ru.margarite.volkova.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@Builder
public class Entity {
    private Addition addition;
    @SerializedName("important_numbers")
    private List<Integer> importantNumbers;
    private String title;
    private boolean verified;
    private String id;
}