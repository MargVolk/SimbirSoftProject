package ru.margarite.volkova.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Addition {
    @SerializedName("additional_info")
    private final String additionalInfo;
    @SerializedName("additional_number")
    private final int additionalNumber;
    private String id;
}
