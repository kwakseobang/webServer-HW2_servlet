package org.zero.ck.smucal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CalenderVO {

    private int id;
    private UserVO author;
    private String content;
    private LocalDate date;
}
