package com.example.Spring_LevelTest.repository;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum JobType {
    DEVELOPER("개발자"),
    DESIGNER("디자이너"),
    MARKETER("마케터");

    private final String description;

    JobType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static JobType forDescription(String description) {
        for (JobType jobType : JobType.values()) {
            if (jobType.getDescription().equals(description)) {
                return jobType;
            }
        }
        throw new IllegalArgumentException("Invalid description : " + description);
    }
}
