package org.zero.ck.smucal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zero.ck.smucal.domain.UserVO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CalenderDTO {

    private int id;
    private UserVO author;
    private String content;
    private LocalDate date;

    @Builder
    public CalenderDTO(
            int id,
            UserVO author,
            String content,
            LocalDate date
    ) {
        this.id = id;
        this.author =  author;
        this.content = content;
        this.date = date;
    }

//    public Calender toEntity(User author) {
//        return Calender.CalenderSaveBuilder()
//                .author(author)  //엔티티 변환때 사용자 정보도 넘어옴.
//                .content(this.content)
//                .date(this.date)
//                .build();
//    }
//
//    // 정적 팩토리 메서드로 엔티티를 DTO로 변환 (id 포함)
//    public static CalenderDTO ofEntity(Calender calender) {
//        return CalenderDTO.builder()
//                .id(calender.getId()) // ID 포함
//                .author(calender.getAuthor())
//                .content(calender.getContent())
//                .date(calender.getDate())
//                .build();
//    }

    @Override
    public String toString() {
        return "CalenderDTO{" +
                "id= " + id +
                ", author= '" + author +
                ", content= '" + content +
                ", date= '" + date +
                '\'' +
                '}';
    }
}
