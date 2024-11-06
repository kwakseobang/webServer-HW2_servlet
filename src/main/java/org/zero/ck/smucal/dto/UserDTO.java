package org.zero.ck.smucal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int id;

    private String username;
    private String password;



    @Builder
    public UserDTO(int id,String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // DTO를 엔티티로 변환하는 메서드 (id 없음)
//    public User toEntity() {
//        return User.UserSaveBuilder()
//                .username(this.username) // ID 제외
//                .password(this.password)
//                .build();
//    }

    // 정적 팩토리 메서드로 엔티티를 DTO로 변환 (id 포함)
//    public static UserDTO ofEntity(User user) {
//        return UserDTO.builder()
//                .id(user.getId()) // ID 포함
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .build();
//    }
    @Override
    public String toString() {
        return "UserDTO{" +
                "id= " + id +
                ", username= '" + username +
                ", password= '" + password +
                '\'' +
                '}';
    }
}
