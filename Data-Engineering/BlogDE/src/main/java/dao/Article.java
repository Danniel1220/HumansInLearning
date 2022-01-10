package dao;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Article {
    private int id;
    private String title;
    private String tag;
    private String author;
    private String date;
    private String dateTimeStamp;
    private String imgUrl;
    private String content;
    private byte[] imgByte;
}
