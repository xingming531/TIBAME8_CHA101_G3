package tw.idv.petradisespringboot.roomreview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomReviewDTO {
    private Integer score;
    private String content;
    private String memberName;

    public RoomReviewDTO(Integer score, String content, String memberName) {
        this.score = score;
        this.content = content;
        this.memberName = memberName;
    }
}
